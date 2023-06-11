# PROPOSITION DE JEU - Projet R+L

## PROJET R+L


### Phase de jeu classique : `jeu d'action`
- Une grande `arène` dans un espace torique, retour au côté droit quand on passe par le côté gauche. 
- `Viewport` centré sur les deux joueurs, se déplace, se réduit et s'agrandit selon la distance entre les joueurs
- Deux joueurs équipés d'armes et capables de tirer dans différentes directions (haut, bas, gauche, droite, diagonales)

#### Objectif    
- Éliminer l'adversaire autant de fois que possible pendant une durée limitée modifiable (3min 30 par défaut)
- Le joueur qui a le plus de kills gagne la partie.
- En cas d'égalité, une manche de mort subite à lieu.

    
#### Jeu
- Plusieurs personnages jouables (2 prévus pour le moment)
- Éviter les tirs adverses et les pièges environnementaux
- Une barre de vie et un compteur de morts pour chaque joueur  
- Les personnages possèdes des pouvoirs qui leurs sont propres.

#### L'arène
- L'arène est une tilemap (arène en cases) de taille propre à chaque partie.
- L'arène est essentiellement de blocks, cependant elle pourras aussi possèder des platformes mouvantes.
- L'arène est unique à chaque partie, elle est crée au préalable.

#### Originalité       
- Le système de mouvement contenant de nombreuses options.
- Le comportement des différents objets mouvants, compétences guidées, joueurs est régie par des automates depuis un fichier .gal 
- Les personnages auront des capacités spéciales uniques et leurs armes uniques (par exemple, un personnage peut envoyer un raptor qui attaque l'adversaire, un autre peut avoir unee boisson de régeneration, etc.)


 ## Personnages (TODO)
 - Chaque personnage possédant deux compétences chaque compétence sera gérée par Pop et Wizz respectivement
  ### Mr.Klaxon - Ingénieur :
    - Spell 1 : Tourelle (DPS)
    - Spell 2 : Missile (DMG)
 ### El Chapo - Mexicain :
    - Spell 1 : Raptor téléguidé (DMG)
    - Spell 2 : Flasque revigorante [40°] (HEAL)



## Extension expérimentale : `Gameplay corps à corps`
- Les joueurs ont la possibilité d'attaquer leur adversaire au corps à corps avec un couteau, ajoutant une dimension stratégique supplémentaire au jeu.
- ajou d'avantages d'options pour se déplacer

#### Points techniques :
- Gérer les déplacements des joueurs et des balles
- Détection de collision pour les tirs et les joueurs
- Viewport avec capacités de zoom/dezoom en fonction de la distance entre les deux joueurs
- Comment détecter la collision d'une balle (si elle se déplace vite elle peut sauter après notre personnage, comment détecter ?)

## Extension: Création d'une arène personnalisée

Donnez aux joueurs la possibilité de créer et de personnaliser leurs propres arènes de jeu, avec une variété de pièges et de terrains à leur disposition.

- Les joueurs peuvent placer des éléments sur le terrain à l'aide de la souris
- Un outil de dessin et d'édition intuitif pour la personnalisation de l'arène
- L'opportunité de partager et de jouer sur les arènes créées par d'autres joueurs

## Extension: Améliorations et Power-ups

Introduisez une variété d'améliorations et de power-ups que les joueurs peuvent obtenir pendant le jeu pour augmenter leur puissance de feu, leur vitesse, leur santé, etc.

- Ces améliorations peuvent être obtenues en éliminant l'adversaire, ou en les trouvant disséminées dans l'arène
- Les power-ups ont des effets temporaires, ajoutant une couche stratégique supplémentaire

---
    AUTHOR: GROUPE B
    Polytech'Grenoble, Univ. Grenoble Alpes 
