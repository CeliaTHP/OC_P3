<h1>Entrevoisins</h1>

<h1>Comment lancer l'application ?</h2>

<h2>Prérequis : Assurez vous d'avoir installé Android Studio (disponible ici : https://developer.android.com/studio)</h3>
<br>
<h3>1. Récupérer le répertoire github, en le clonant, ou en le téléchargeant</h3>
<img src= "https://github.com/CeliaTHP/OC_P3/blob/main/clonerepo.png?raw=true" alt="reposcreenshot" style="max-width:100%;">
<br>
<h3>2. Au lancement d'Android Studio, sélectionnez "Check out project from version control" et précisez l'url du répertoire </h3>
<img src= "https://github.com/CeliaTHP/OC_P3/blob/main/androidstudioclone.png" alt="repoandroidstudio" style="max-width:100%;">
<br>
<h3>3. Patientez le temps du build (initialisation des fichiers) </h3>
<br>
<h3>4.Une fois tous les dossiers crées, vous pouvez sélectionner un émulateur et lancer l'application avec la flèche verte (ça peut prendre quelques minutes)</h3>
<img src= "https://github.com/CeliaTHP/OC_P3/blob/main/launchapp.png" alt="launchscreenshot" style="max-width:100%;">
<br>
<h1>Projet N°3 : Développez une nouvelle fonctionnalité pour l'application Entrevoisins et testez-la</h1>

Entrevoisins est une application qui permet d'afficher une liste de voisins et d'en supprimer, cependant elle n'est pas complète et des fonctionnalités doivent être ajoutées : 
<ul>
  <li>l'affichage d'une page détaillée correspondant au voisin choisit </li>
  <li>la possibilité d'ajouter un voisin en favoris</li>
  <li>la liste des voisins favoris dans un deuxième onglet</li>
  <li>sur la page des détails, un bouton retour à la liste</li>
  </ul>
  
  <h2>Dans le cadre de l’application :</h2>

<b>Les tests unitaires </b> isolent une seule méthode pour vérifier son comportement, dans le cadre de l'application les méthodes liées à la manipulation de la liste de voisins (création, génération aléatoire, suppression, ajout en favoris).

<b>Les tests d'instrumentation</b> sont des suites d'instructions assurant le bon fonctionnement de l'application (la liste, ou le fait que les actions sur un voisin correspondent aux consignes de développement). 

