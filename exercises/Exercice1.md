# TCC *vs* LCC

Explain under which circumstances *Tight Class Cohesion* (TCC) and *Loose Class Cohesion* (LCC) metrics produce the same value for a given Java class. Build an example of such as class and include the code below or find one example in an open-source project from Github and include the link to the class below. Could LCC be lower than TCC for any given class? Explain.

A refresher on TCC and LCC is available in the [course notes](https://oscarlvp.github.io/vandv-classes/#cohesion-graph).

## Answer

TCC (Tight Class Cohesion) et LCC (Loose Class Cohesion) ont la même valeur si toutes les méthodes accèdent directement aux mêmes attributs.

Voici un exemple de classe où TCC et LCC sont égales :
```
  public class BankAccount {
      private double balance;

      public void deposit(double amount) {
          balance += amount;
      }

      public void withdraw(double amount) {
          balance -= amount;
      }

      public void printBalance() {
          System.out.println("Current balance: " + balance);
      }
  }
``` 
Dans la classe `BankAccount`, les trois méthodes (`deposit`, `withdraw`, `printBalance`) accèdent toutes à l'attribut `balance`. Ainsi, chaque paire de méthodes est directement connectée par l'attribut `balance`, ce qui fait que TCC et LCC sont égales.

Non, LCC ne peut jamais être inférieure à TCC car TCC ne prend en compte que les connexions directes tandis que LCC inclut, en plus des connexion directes, les connexion indirectes entre les méthodes. La LCC est donc toujours supérieure ou égale à la TCC.
