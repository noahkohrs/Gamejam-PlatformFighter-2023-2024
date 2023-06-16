# Journal : 

## 05/06:
### Discussion du concept du jeu
Ce jeu consiste en une phase de jeu classique d'action se déroulant dans une grande arène. Cette arène est située dans un espace torique, ce qui signifie que les joueurs réapparaissent du côté droit lorsqu'ils traversent le côté gauche. Le viewport est centré sur les deux joueurs et se déplace, se réduit ou s'agrandit en fonction de la distance entre les joueurs. Les joueurs sont armés et peuvent tirer dans différentes directions. L'objectif du jeu est d'éliminer l'adversaire autant de fois que possible dans une durée limitée. Le joueur ayant le plus de kills remporte la partie. 

## 06/06:

### Mise au point sur les challenges techniques

#### Répartissent des tâches pour le prototype :
- Noah: Viewport/Caméra + Gestion de la map (Map Editor / Map Loader)
- Anastasios: Automates
- Clément: Collisions
- Bastien: Gestion des balles, armes et essai d'une hitbox spécifiques aux balles
- Gwénolé: Mouvement
- Noé: Apprentissage de la programmation événementielles et GUI

#### Travail sur ses tâches :
- Anastasios: Travail sur les classes d’automates (States, Automates, Transitions, Entity, Action).
- Noah : Création d’un Level Editor basique.

#### Nouvelles idées:
- Construction de blocs comme Fortnite. 
  
## 07/06:
### Travail sur ses tâches:

- Noah : Création du Level Loader et LevelSaver sous format Json.
- Gwénolé: Travail sur l’idée de déplacement régies par un modèle physique réaliste gérée par les équations différentielles du mouvement selon le principe de Newton et résolution de ces calculs par la méthode d’euler
- Clément : création d’un prototype de la map
- Noé: Apprentissage de la programmation événementielles
- Anastasios: Plusieurs tests avec des conditions/actions différentes, comme l'entité Rien et l’action Move. Pour le moment, ce sont des fonctions simples qui ne touchent pas le Canva.
- Noah : Création du système de sauvegarde et de chargement de niveau par interférence d’un fichier json.

### TODO:
- Penser aux problèmes techniques. 

### Nouvelles idées:
- Deux personnages différents avec des compétences différentes.
- Construction de blocs -> non, beaucoup de problèmes techniques. 
## 08/06:

-Gwénolé : Abandon de l’idée de mouvement par équation physique car posant des problèmes sur les oscillements autour de la position d'arrivée et de la gestion de la position d’arrivée lors du passage dans le trou de ver reliant les bords.
Partie sur l’idée d’avoir un système de mouvement propre au jeu vidéo, ayant pour but de simuler le mouvement réel.
- Clément : réalisation du prototype de collision.
- Noé: Apprentissage de la programmation événementielles en réalisant une barre de vie
- Anastasios: Travail sur le parseur d’automate. Test avec un automate simple avec 3 états avec 1 transition chacun. Que de conditions True(), et d' actions Move() qui font incrémenter juste une variable x.Toujours pas au Canvas.
- Noah : proto Viewport display dans un paint classique + Théorie sur l’environnement.

### Nouvelles idées:
- Avoir un système de dash.
- Avoir de bonus après un killstreak.  

## 09/06 :
### Commencement du proposal. 
- Gwénolé :  Finition des mouvements au sol, fluidification des contrôles, et des déplacements au sol. Début du travail sur la gravité. Complétion du système de mouvement, gestion de saut et gravités. Début du travail sur les dashs.
Clément : fin de la réalisation du prototype de collision
- Noé: Finilisation de la barre de vie
- Anastasios: Tester avec des conditions et actions différentes, qui contient plusieurs arguments pour voir si tout marche bien. Correction d’un bug ou on ne pouvait pas avoir plusieurs transitions.
- Noah : Camera (Viewport) fonctionnelle + définition des bases du système de coordonnées des normes. 

### TODO:
- Finir les prototypes pendant le week-end. 

#### Nouvelles idées:
- Avoir un mexican et un ingénieur comme personnages avec deux compétences différentes chacun.
- Le killstreak est devenu de Power-Up car c’est plus intéressant niveau gameplay.

## 12/06 : 
### Discussion avec le prof du 1er proposal. 
Tous : Discussion et précision du gameplay pour le contrat.
- Gwénolé et Noé : réécriture du proposal 
- Gwénolé : Fine-tuning des mouvements
- Anastasios et Noah : Préparation de la structure globale du code pour tout le monde.
- Noah : Recréation propre du système de caméra.
- Clément et Bastien : Début de l’implémentation des collisions dans la version finale du projet.

### TODO:
- Combiner les prototypes.

### Nouvelles idées:
- Plusieurs armes -> On a décidé que c'est plus simple d’avoir la même arme et que ça ne sert à rien car on a déjà des compétences différentes.
- en option : possibilité de passer à travers les plateforme depuis le bas
- en option : inversion de la gravité 


## 13/06

tous : Discussions sur le proposal, précisions des classes entités et entity view. Séparation du model et de la view pour chaque classe. 
Exemple LifeBar, LifeBarView. 
On a commencé à git Merge certains protos.

- Noé : a fini et tester le LifeBar.
- Clément : création des sprites des deux personnages.
- Anastasios: adaptation d'automates avec la nouvelle structure. Jeu jouable avec des mouvements gérés par les automates. 
- Noah : Séparation de la partie View et Model + Explications à tout le monde sur l’utilisation de l’environnement et support.
- Gwénolé: adaptation des mouvements, pour ne plus etre dépendant du tickrate. Début des merges
- Bastien : peaufinage des hitbox

## 14/06

Tous : discussion avec Emmanuel DUFOUR sur le projet, discussions de l’attente de chacun. Sur les points de doute (jeu de base), particularité du groupe.


- Noah : Prototype de Plateforme + Support pour utilisation de l’environnement. Amélioration du système de gestion des entités.
- Noé : création sprite powerup
- Clément et Noé : début de la gestion des powerUps. (résolution de conflit)
- Anastasios: Amélioration du parseur, plus merge pour avoir les collisions et les balles. (résolution de conflits).
- Gwénolé: continuation de rendre les mouvements identiques sur tous les pcs. Début du travail avec les collisions pour les hitboxs.
- Bastien : Refactoring pour encore mieux séparé en MVC, créer une entité est maintenant beaucoup plus simple

## 15/06

