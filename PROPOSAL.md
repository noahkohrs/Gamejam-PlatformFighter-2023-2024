# PROPOSITION DE JEU - Projet R +L

## PROJET R +L


### Phase de jeu classique : `jeu d'action`
- Une grande `arène` (faite de case, elle a une taille totale a partir de 60 * 34) dans un espace torique, retour au côté droit quand on passe par le côté gauche.
- `Viewport` centré sur les deux joueurs, se déplace, se réduit et s'agrandit selon la distance entre les joueurs
- Deux joueurs équipés d'armes et capables de tirer dans différentes directions (haut, bas, gauche, droite, diagonales)

#### Objectif    
- Éliminer l'adversaire autant de fois que possible pendant une durée limitée modifiable (3min 30 par défaut)
- Le joueur qui a le plus de kills gagne la partie.
- En cas d'égalité, une manche de `mort subite` à lieu, c'est à dire le premier a mourir perd.

    
#### Jeu
- Plusieurs personnages jouables (2 prévus pour le moment)
- Éviter les tirs adverses et les pièges environnementaux, ainsi que les compétences adverses
- Des Powers-up ainsi que des malus seront disponible sur la carte à des endroits prédéfinis. Mais lequel apparaitra a cet endroit est aléatoire(cf power-ups).
- Les joueurs auront les mêmes armes et les mêmes déplacements : les armes possèdent des chargeurs avec un nombre de balles (récupérable par un power-up)
- Les personnages possèdes des pouvoirs qui leurs sont propres et une barre de vie(en pourcentage) ainsi qu'un compteur de morts pour chaque joueur.(cf definition des personages)
- Si un personnage mort, il respawn à un des points prédéfinis et ce de manière aléatoire.

#### L'arène
- L'arène est une tilemap (arène en cases) de taille propre à chaque partie.
- L'arène est essentiellement de blocks, certaines possederont des platformes mouvantes.
- L'arène est crée au préalable.

#### Originalité       
- Le système de mouvement contenant de nombreuses options dont le dash.
- Le comportement des différents objets mouvants, compétences guidées, joueurs est régie par des automates depuis un fichier .gal 
- Les personnages auront des capacités spéciales uniques (cf section personnage)

## Déplacements
- La caméra donne un point de vue de profil
- Les joueurs sont soumis à la gravité qui est orientée vers le bas de l'écran( y croisssant)
- Les joueurs peuvent sauter, et se déplacer de droite à gauche en sautant et au sol.
- Les joueurs peuvent sauter à différentes hauteurs et se déplacer a une vitesse croissante qui augmente avec le temps qu'on a passé à se déplacer(vitesse maximale cappé)
- les joueurs sont dotés d'un dash qu'ils peuvent orienter pour se déplacer dans une direction de leur choix

 ## Personnages (TODO)
 - Chaque personnage possédant deux compétences avec un temps de rechargement unique. Chaque compétence sera gérée par Pop et Wizz respectivement ils possèdent aussi des sprites différents
  ### Mr.Klaxon - Ingénieur :
    - Spell 1 : Tourelle (DPS)
      - posé à Terre sur le joueur, son placement est soumit à la gravité
      - fixe et pas récupérable
      - durée de vie ainsi que des point de vie, elle subit des dégats en fonction du temps et des missilles. 
      - zone de détection -->tire en continue sur toutes entités adverses
    - Spell 2 : Missile (DMG)
      - même comportement que les balles mais se déplace plus lentemement
      - oneshot l'adversaire
 ### El Chapo - Mexicain :
    - Spell 1 : Raptor téléguidé (DMG)
      - oneshot l'adversaire
      - durée de vie ainsi que des points de vie, elle subit des dégats en fonction du temps et des missilles.
      - il explose sur le joueur si il le touche ou à la fin de sa durée de vie
      - cours en ligne droite grâce a un automate et rebondit sur les murs
    - Spell 2 : Flasque revigorante [40°] (HEAL)
      - le joueur peut soigner tous les dégats qu'il a subi

## Améliorations et Power-ups(TODO)

Introduisez une variété de power-ups et de malus que les joueurs peuvent obtenir pendant le jeu pour augmenter leur puissance de feu, leur vitesse, leur santé, etc.

- Ces améliorations peuvent être obtenues en les trouvant disséminées dans l'arène
- Les power-ups ont des effets temporaires, ajoutant une couche stratégique supplémentaire
- Exemple de power-ups :
  - vitesse x2 : (le joueur se déplace 2x plus vite)
  - degats x2
  - bouclier : (le joueur est immunisé a la plupart des dégats pendnt une courte durée)
  - munition :  le joueur recupere un nombre de chargeur 
- Exemple de malus :
  - vitesse /2 le joueur adverse voit sa vitesse divisé par 2
  - degats /2 le joueur adverse voit ses dégats /2
  - vulnérabilité le joueur adverse prend 2x plus de dégats


## Extension expérimentale : `Gameplay corps à corps`
- Les joueurs ont la possibilité d'attaquer leur adversaire au corps à corps avec un couteau, ajoutant une dimension stratégique supplémentaire au jeu.
- ajou d'avantages d'options pour se déplacer

## Extension power-up gravité : 
- change la gravité du jeu pendant une durée.

#### Points techniques :
- Gérer les déplacements des joueurs et des balles
- Détection de collision pour les tirs et les joueurs
- Viewport avec capacités de zoom/dezoom en fonction de la distance entre les deux joueurs
- Comment détecter la collision d'une balle (si elle se déplace vite elle peut sauter après notre personnage, comment détecter ?)


---
    AUTHOR: GROUPE B
    Polytech'Grenoble, Univ. Grenoble Alpes 
