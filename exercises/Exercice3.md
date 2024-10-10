# Extending PMD

Use XPath to define a new rule for PMD to prevent complex code. The rule should detect the use of three or more nested `if` statements in Java programs so it can detect patterns like the following:

```Java
if (...) {
    ...
    if (...) {
        ...
        if (...) {
            ....
        }
    }

}
```
Notice that the nested `if`s may not be direct children of the outer `if`s. They may be written, for example, inside a `for` loop or any other statement.
Write below the XML definition of your rule.

You can find more information on extending PMD in the following link: https://pmd.github.io/latest/pmd_userdocs_extending_writing_rules_intro.html, as well as help for using `pmd-designer` [here](./designer-help.md).

Use your rule with different projects and describe you findings below. See the [instructions](../sujet.md) for suggestions on the projects to use.

## Answer

Avec XPath, nous avons créé un ruleset pour PMD afin de détecter les if imbriqués à trois niveaux ou plus dans les programmes Java.

Nous avons ensuite appliqué cette règle au projet commons-cli disponible depuis https://github.com/apache/commons-cli et nous avons constaté de nombreuses violations, notamment dans les fichiers `DefaultParser.java`, `GnuParser.java`, et `PosixParser.java`. Pour en citer quelques unes :

Dans `DefaultParser.java`, les lignes 391, 452, 486 montrent des if imbriqués à trois niveaux ou plus.

Dans `GnuParser.java`, des violations ont été détectées aux lignes 55, 57, 59.

Dans `PosixParser.java`, des lignes comme 132, 139, 154 présentent également des imbrications excessives.

Ces imbrications de if indiquent une complexité qui pourrait être réduite avec des techniques comme l'extraction de méthodes ou l'utilisation de clauses de garde afin d'améliorer la lisibilité et la maintenabilité du code.
