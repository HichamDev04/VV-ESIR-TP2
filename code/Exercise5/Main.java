package fr.istic.vv;

import java.io.File;
import java.io.IOException;

import com.github.javaparser.utils.SourceRoot;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Veuillez fournir le chemin vers le projet Java.");
            System.exit(1);
        }

        File projectDir = new File(args[0]); 
        if (!projectDir.exists() || !projectDir.isDirectory()) {
            System.err.println("Le chemin fourni doit être un répertoire valide.");
            System.exit(2);
        }

        SourceRoot sourceRoot = new SourceRoot(projectDir.toPath());
        CyclomaticComplexityCalculator calculator = new CyclomaticComplexityCalculator();

        sourceRoot.parse("", (localPath, absolutePath, result) -> {
            result.ifSuccessful(unit -> {
                calculator.calculateComplexity(unit);
            });
            return SourceRoot.Callback.Result.DONT_SAVE;
        });

        // Générer le rapport CSV
        calculator.generateCSVReport("complexity_report.csv");
    }
}
