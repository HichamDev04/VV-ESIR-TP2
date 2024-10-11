package fr.istic.vv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.SourceRoot;
import com.opencsv.CSVWriter;

public class NoGetterFinder {

    // Classe interne pour représenter les résultats
    static class FieldInfo {
        String packageName;
        String className;
        String fieldName;

        FieldInfo(String packageName, String className, String fieldName) {
            this.packageName = packageName;
            this.className = className;
            this.fieldName = fieldName;
        }
        
        @Override
        public String toString() {
            return "Package: " + packageName + ", Class: " + className + ", Field: " + fieldName;
        }
    }

    // Méthode pour lancer l'analyse
    public void analyzeProject(Path path) throws IOException {
        List<FieldInfo> privateFieldsWithoutGetters = new ArrayList<>();
        
        // Utilisation de SourceRoot pour parcourir les fichiers Java
        SourceRoot sourceRoot = new SourceRoot(path);
        sourceRoot.parse("", (localPath, absolutePath, result) -> {
            result.ifSuccessful(unit -> {
                unit.accept(new ClassVisitor(), privateFieldsWithoutGetters);
            });
            return SourceRoot.Callback.Result.DONT_SAVE;
        });

        // Génération du rapport en CSV
        generateCSVReport(privateFieldsWithoutGetters, "nogetter_report.csv");
    }

    // Méthode pour générer un rapport CSV
    private void generateCSVReport(List<FieldInfo> fields, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // En-tête du fichier CSV
            String[] header = {"Package", "Class", "Field"};
            writer.writeNext(header);

            // Écriture des informations de chaque champ
            for (FieldInfo field : fields) {
                String[] data = {field.packageName, field.className, field.fieldName};
                writer.writeNext(data);
            }
        }
        System.out.println("Le rapport a été généré : " + filePath);
    }

    // Visiteur pour parcourir les classes et trouver les champs privés sans getter
    private static class ClassVisitor extends VoidVisitorAdapter<List<FieldInfo>> {
        @Override
        public void visit(ClassOrInterfaceDeclaration declaration, List<FieldInfo> collector) {
            // Récupération du nom du package
            String packageName = declaration.findCompilationUnit()
                                             .flatMap(cu -> cu.getPackageDeclaration())
                                             .map(pd -> pd.getNameAsString())
                                             .orElse("default package");

            // Récupération des champs privés
            List<FieldDeclaration> privateFields = new ArrayList<>();
            declaration.getFields().forEach(field -> {
                if (field.isPrivate()) {
                    privateFields.add(field);
                }
            });

            // Vérification des getters
            List<String> getterNames = new ArrayList<>();
            declaration.getMethods().forEach(method -> {
                if (isGetter(method)) {
                    getterNames.add(method.getNameAsString());
                }
            });

            // Pour chaque champ privé, vérifier s'il a un getter public
            for (FieldDeclaration field : privateFields) {
                field.getVariables().forEach(variable -> {
                    String fieldName = variable.getNameAsString();
                    String expectedGetterName = "get" + capitalize(fieldName);
                    if (!getterNames.contains(expectedGetterName)) {
                        collector.add(new FieldInfo(packageName, declaration.getNameAsString(), fieldName));
                    }
                });
            }

            super.visit(declaration, collector);
        }

        // Vérifie si une méthode est un getter
        private boolean isGetter(MethodDeclaration method) {
            return method.isPublic() && method.getNameAsString().startsWith("get") && method.getParameters().isEmpty();
        }

        // Capitalise la première lettre d'un mot
        private String capitalize(String str) {
            if (str == null || str.isEmpty()) return str;
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }
}
