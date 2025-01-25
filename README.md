# R4A11_TP1

## Question 1 : Où se trouve le fichier .xml ?

Le fichier se trouve dans /app/src/main/res/layout/activity_main.xml.

## Question 2 : Où changer le texte de base ?

C'est dans la ligne ```android:text="Votre Texte"```.

## Question 3 : Qu'avez vous modifié pour changer l'icone ?

Il faut télécharger l'image dont on veut qu'elle soit l'icone dans le dossier ```app/res/mipmap``` puis changer la variable ```android:icon=@mipmap/Nom_Image``` dans le fichier ```androidManifest.xml```.

## Question 4 : Est-ce nécessaire de cliquer sur le bouton valider pour afficher le texte saisi sur la seconde activité ? Pourquoi ?

Non car lorsque l'on veut récupérer le texte, on le récupère directement dans le view ```EditText```.

## Question 5 : Le comportement de la question 4 vous semble-t-il normal ?

Oui je le trouve logique.

## Question 6 : Comment faire pour ne pas afficher le nouveau texte sur la deuvième activité tant que le bouton valider n'a pas été cliqué ?

Il faut faire remarquer l'input à l'EditText.