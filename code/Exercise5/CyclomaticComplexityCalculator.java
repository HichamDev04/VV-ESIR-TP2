package fr.istic.vv;

import java.util.HashMap;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.WhileStmt;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.opencsv.CSVWriter;

public class CyclomaticComplexityCalculator {

    // Map pour stocker la complexité de chaque méthode
    private Map<String, Integer> complexityMap = new HashMap<>();

    // Méthode pour calculer la complexité cyclomatique d'une unité de compilation (fichier Java)
    public void calculateComplexity(CompilationUnit cu) {
        cu.findAll(MethodDeclaration.class).forEach(method -> {
            int complexity = 1;  // Complexité initiale de 1 pour chaque méthode
            
            // Ajouter à la complexité pour chaque structure de contrôle
            complexity += method.findAll(IfStmt.class).size();
            complexity += method.findAll(ForStmt.class).size();
            complexity += method.findAll(WhileStmt.class).size();
            complexity += method.findAll(DoStmt.class).size();
            complexity += method.findAll(SwitchEntry.class).size();

            // Récupération du nom de la classe
            String className = method.findAncestor(com.github.javaparser.ast.body.ClassOrInterfaceDeclaration.class)
                                     .map(coid -> coid.getNameAsString()).orElse("Unknown Class");

            // Construction de la signature de la méthode
            String methodSignature = className + "." + method.getNameAsString();

            // Stockage dans la Map
            complexityMap.put(methodSignature, complexity);
        });
    }

    // Méthode pour générer un rapport de la complexité cyclomatique dans un fichier CSV
    public void generateCSVReport(String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Écriture de l'en-tête
            String[] header = { "Class", "Method", "Complexity" };
            writer.writeNext(header);

            // Écriture des données
            for (Map.Entry<String, Integer> entry : complexityMap.entrySet()) {
                String[] data = { entry.getKey().split("\\.")[0], entry.getKey().split("\\.")[1], String.valueOf(entry.getValue()) };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
