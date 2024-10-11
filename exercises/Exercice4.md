# No getter!

With the help of JavaParser implement a program that obtains the private fields of public classes that have no public getter in a Java project. 

A field has a public getter if, in the same class, there is a public method that simply returns the value of the field and whose name is `get<name-of-the-field>`.

For example, in the following class:

```Java

class Person {
    private int age;
    private String name;
    
    public String getName() { return name; }

    public boolean isAdult() {
        return age > 17;
    }
}
```

`name` has a public getter, while `age` doesn't.

The program should take as input the path to the source code of the project. It should produce a report in the format of your choice (TXT, CSV, Markdown, HTML, etc.) that lists for each detected field: its name, the name of the declaring class and the package of the declaring class.

Include in this repository the code of your application. Remove all unnecessary files like compiled binaries. See the [instructions](../sujet.md) for suggestions on the projects to use.

*Disclaimer* In a real project not all fields need to be accessed with a public getter.

# Answer

Nous avons développé un programme en Java à l'aide de JavaParser pour identifier les champs privés des classes publiques ne disposant pas d'un getter public dans un projet Java. Pour ce faire, nous avons créé deux listes distinctes, l'une contenant tous les champs privés et l'autre contenant les getters publics de ces champs. Ensuite, nous avons comparé ces deux listes pour détecter les champs privés sans getter correspondant.
Le programme prend en entrée le chemin du projet source et génère un rapport sous format CSV, listant pour chaque champ détecté son nom, le nom de sa classe et le package auquel il appartient.

Nous avons donc testé notre code sur le projet **commons-cli** que nous avons compilé en utilisant la commande : 
```bash
javac -d target/classes -cp ".;C:/Users/PC/.gradle/wrapper/dists/gradle-7.6.1-all/942lu1p9i6mhoyzmt401s4g74/gradle-7.6.1/lib/javaparser-core-3.17.0.jar;C:/Users/PC/Downloads/opencsv-5.3.jar" src/main/java/fr/istic/vv/*.java
```
Nous avons ensuite testé notre programme avec la commande suivante :
```bash
java -cp "target/classes;C:/Users/PC/.gradle/wrapper/dists/gradle-7.6.1-all/942lu1p9i6mhoyzmt401s4g74/gradle-7.6.1/lib/javaparser-core-3.17.0.jar;C:/Users/PC/Downloads/opencsv-5.3.jar" fr.istic.vv.Main C:/Users/PC/commons-cli/src/main/java/org/apache/commons/cli
```
En observant les résultats obtenus, nous avons remarqué plusieurs exemples comme le champ `longOpt` dans la classe `Option` du projet (qui est privé) sans getter public associé. Ceci illustre que ce champ est potentiellement destiné à un usage interne ou qu'il pourrait bénéficier d'un accès plus explicite. 
