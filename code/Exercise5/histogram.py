import csv
import matplotlib.pyplot as plt

complexities = []

# Ouvrir et lire le fichier CSV
with open('C:/VV-ESIR-TP2/code/javaparser-starter/src/main/java/complexity_report.csv', 'r') as csvfile:

    reader = csv.DictReader(csvfile)
    for row in reader:
        try:
            # Ajouter la complexité à la liste après conversion en entier
            complexities.append(int(row['Complexity']))
        except ValueError:
            # Gérer les erreurs de conversion si des valeurs invalides se trouvent dans le fichier
            print(f"Valeur non valide pour la complexité dans la ligne : {row}")

# Générer l'histogramme
plt.hist(complexities, bins=10, edgecolor='black')
plt.xlabel('Complexité Cyclomatique')
plt.ylabel('Nombre de Méthodes')
plt.title('Distribution de la Complexité Cyclomatique')

# Sauvegarder l'histogramme sous forme de fichier PNG
plt.savefig('complexity_histogram.png')

# Afficher l'histogramme à l'écran
plt.show()
