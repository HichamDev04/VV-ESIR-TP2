# Cyclomatic Complexity with JavaParser

With the help of JavaParser implement a program that computes the Cyclomatic Complexity (CC) of all methods in a given Java project. The program should take as input the path to the source code of the project. It should produce a report in the format of your choice (TXT, CSV, Markdown, HTML, etc.) containing a table showing for each method: the package and name of the declaring class, the name of the method, the types of the parameters and the value of CC.
Your application should also produce a histogram showing the distribution of CC values in the project. Compare the histogram of two or more projects.


Include in this repository the code of your application. Remove all unnecessary files like compiled binaries. Do include the reports and plots you obtained from different projects. See the [instructions](../sujet.md) for suggestions on the projects to use.

You may use [javaparser-starter](../code/javaparser-starter) as a starting point.

# Answer
Nous avons créé un programme en Java à l'aide de la bibliothèque JavaParser pour calculer la complexité cyclomatique des méthodes dans un projet Java. Le processus commence par l'initialisation d'une instance de la classe CyclomaticComplexityCalculator, qui traverse les fichiers Java et collecte des informations sur chaque méthode, notamment les structures de contrôle qui influencent la complexité cyclomatique, comme les boucles et les conditions. Une fois cette information collectée, nous avons généré un fichier CSV contenant le nom du package, la classe, la méthode, les paramètres, et la valeur de la complexité pour chaque méthode. Ce fichier CSVnous a permis de créer un histogramme à l'aide d'un script Python qui lit les valeurs de complexité du fichier CSV et génère un graphique représentant la distribution des complexités cyclomatiques à travers l'ensemble des méthodes du projet.

Pour compiler notre projet java, nous avons utilisé : 
```bash
javac -cp ".;C:/Users/PC/.gradle/wrapper/dists/gradle-7.6.1-all/942lu1p9i6mhoyzmt401s4g74/gradle-7.6.1/lib/javaparser-core-3.17.0.jar;C:/Users/PC/Downloads/opencsv-5.3.jar" fr/istic/vv/Main.java fr/istic/vv/CyclomaticComplexityCalculator.java
```

Après l'avoir compilé, nous avons testé le projet **commons-cli** et généré le fichier csv grace à la commande :
```bash
java -cp ".;C:/Users/PC/.gradle/wrapper/dists/gradle-7.6.1-all/942lu1p9i6mhoyzmt401s4g74/gradle-7.6.1/lib/javaparser-core-3.17.0.jar;C:/Users/PC/Downloads/opencsv-5.3.jar" fr.istic.vv.Main "C:/Users/PC/commons-cli/src/main/java/org/apache/commons/cli"
```

Une fois le fichier **complexity_report.csv** généré, nous avons exécuté le script **histogram.py** pour produire un histogramme et sauvegarder l'image avec la commande :
```bash
python histogram.py
```

Nous en avons ensuite fait de meme pour le projet commons-collection.

**Comparaison :** En comparant les histogrammes de deux projets différents, nous pouvons voir que le projet commons-cli a une distribution principalement centrée autour de la complexité 2, tandis que le projet commons-collection présente quelques méthodes avec une complexité plus élevée, bien que la majorité reste dans une plage basse. Cette comparaison montre que commons-cli est relativement simple, tandis que commons-collection comporte quelques méthodes plus complexes, probablement en raison d'algorithmes plus sophistiqués ou d'une logique métier plus dense.

