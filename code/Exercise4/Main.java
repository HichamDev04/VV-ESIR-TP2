package fr.istic.vv;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Veuillez fournir le chemin du projet Java.");
            System.exit(1);
        }

        try {
            String pathToProject = args[0];  // Chemin vers le projet passé en argument
            NoGetterFinder finder = new NoGetterFinder();
            finder.analyzeProject(Paths.get(pathToProject));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
