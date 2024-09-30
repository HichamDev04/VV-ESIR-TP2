
# Using PMD


Pick a Java project from Github (see the [instructions](../sujet.md) for suggestions). Run PMD on its source code using any ruleset (see the [pmd install instruction](./pmd-help.md)). Describe below an issue found by PMD that you think should be solved (true positive) and include below the changes you would add to the source code. Describe below an issue found by PMD that is not worth solving (false positive). Explain why you would not solve this issue.


## Answer

Pour cet exercice nous avons utilisé le projet **commons-cli** disponible depuis https://github.com/apache/commons-cli . 

**True Positive** : 
Problème détecté par PMD : `ReturnEmptyCollectionRatherThanNull` dans `CommandLine.java` (lignes 442 et 465).
Solution : Modifier la méthode pour retourner une collection vide plutôt que `null`. Cela évite les erreurs `NullPointerException` et améliore la robustesse du code.

Modification proposée :

**Avant**
```
return null;
```
**Après**
```
return Collections.emptyList();
```


**False Positive** : 
Problème détecté par PMD : `UncommentedEmptyMethodBody` dans `BasicParserTest.java`.
Raison de ne pas corriger : Ces méthodes vides peuvent être en attente d'implémentation ultérieure (tests "stubs") ou être utilisées comme squelette. Les commenter n'apporterait pas de valeur significative, car il est courant d'avoir des tests non encore implémentés dans les premières phases du développement.


