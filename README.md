# Projet pour le cours de Java du M2 TAL IM de l'Inalco
### PODER Solveig - REY Camille

## Présentation

L’objectif du présent projet est de créer un programme java qui puisse opérer des transformations sur des documents XML de type *bibliothèque* vers des formats PDF ou HTML, grâce à des **feuilles de style XSLT**.

## Librairies externes

Les librairies utilisées dans ce projet sont placées dans le dossier *lib*. Leur chemin est directement spécifié dans le *.classpath* pour Eclipse. Il faut cependant indiquer leur chemin manuellement pour une exécution en ligne de commande.

## Exécution du programme

La classe qui contient le main à exécuter est la classe **TransformateurBibli**.
Elle peut s’exécuter de plusieurs manières :
- avec aucun argument : chemin du fichier d'entrée, format souhaité pour la conversion (PDF ou HTML) et nom du fichier de sortie sans l'extension (facultatif) sont demandés à l’utilisateur qui doit les saisir manuellement au cours de l’exécution
- avec 2 arguments : fichier d'entrée, format (sortie générée automatiquement dans le répertoire courant)
- avec 3 arguments : fichier d'entrée, format, fichier de sortie

Si la sortie HTML est générée dans un autre répertoire que celui du projet, ou bien déplacée après sa génération, il est conseillé de copier-coller le dossier « css » dans le même répertoire afin de conserver un rendu optimal dans le navigateur.

#### Exécution sous éclipse

Il suffit d’ouvrir la class **TransformateurBibli** puis de lancer l’exécution avec *Run > run*.
Pour passe directement des arguments, il faut les ajouter dans *Run > Run Configurations…*.

#### Exécution en ligne de commande

Pour exécuter en ligne de commande, il faut ouvrir un terminal, se placer dans la racine du répertoire du projet, puis exécuter la ligne suivante :
```java -cp ./bin:./lib/* projetXSLT.TransformateurBibli```
Les arguments peuvent être rajoutés directement à la suite de cette ligne, par exemple :
```java -cp ./bin:./lib/* projetXSLT.TransformateurBibli bibliotheque.xml html sortieHtml```