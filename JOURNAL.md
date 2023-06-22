# Journal : 

## 05/06:
### Discussion du concept du jeu
Ce jeu consiste en une phase de jeu classique d'action se déroulant dans une grande arène. Cette arène est située dans un espace torique, ce qui signifie que les joueurs réapparaissent du côté droit lorsqu'ils traversent le côté gauche. Le viewport est centré sur les deux joueurs et se déplace, se réduit ou s'agrandit en fonction de la distance entre les joueurs. Les joueurs sont armés et peuvent tirer dans différentes directions. L'objectif du jeu est d'éliminer l'adversaire autant de fois que possible dans une durée limitée. Le joueur ayant le plus de kills remporte la partie. 

## 06/06:

### Répartissent des tâches pour le prototype :

Développeur   | Tâche
------------- | -------------
Clément       | Collisions
Anastasios    | Travail sur les classes d’automates (States, Automates, Transitions, Entity, Action).
Bastien       | Apprentissage de la programmation événementielles et armes
Gwénolé       | Mouvement
Noah          | Viewport/Caméra + Gestion de la map (Map Editor / Map Loader). Création d'un level editor
Noé           | Apprentissage de la programmation événementielles et GUI

#### Nouvelles idées:
- Construction de blocs comme Fortnite. 
  
## 07/06:

Développeur   | Tâche
------------- | -------------
Clément       | création d’un prototype de la map
Anastasios    | Plusieurs tests avec des conditions/actions différentes, comme l'entité Rien et l’action Move. Pour le moment, ce sont des fonctions simples qui ne touchent pas le Canva.
Bastien       | Gestion des balles, armes et essai d'une hitbox spécifiques aux balles
Gwénolé       | Travail sur l’idée de déplacement régies par un modèle physique réaliste gérée par les équations différentielles du mouvement selon le principe de Newton et résolution de ces calculs par la méthode d’euler
Noah          | Création du Level Loader et LevelSaver sous format Json. Création du système de sauvegarde et de chargement de niveau par interférence d’un fichier json.
Noé           | Apprentissage de la programmation événementielles
### TODO:
- Penser aux problèmes techniques. 

### Nouvelles idées:
- Deux personnages différents avec des compétences différentes.
- Construction de blocs -> non, beaucoup de problèmes techniques. 
## 08/06:

  
  Développeur   | Tâche
------------- | -------------
Clément       | Réalisation du prototype de collision.
Anastasios    | Travail sur le parseur d’automate. Test avec un automate simple avec 3 états avec 1 transition chacun. Que de conditions True(), et d' actions Move() qui font incrémenter juste une variable x.Toujours pas au Canvas.
Bastien       | Prototype des armes terminé
Gwénolé       | Abandon de l’idée de mouvement par équation physique car posant des problèmes sur les oscillements autour de la position d'arrivée et de la gestion de la position d’arrivée lors du passage dans le trou de ver reliant les bords.
Partie sur l’idée d’avoir un système de mouvement propre au jeu vidéo, ayant pour but de simuler le mouvement réel.
Noah          | Apprentissage de la programmation événementielles en réalisant une barre de vie
Noé           | proto Viewport display dans un paint classique + Théorie sur l’environnement.

### Nouvelles idées:
- Avoir un système de dash.
- Avoir de bonus après un killstreak.  

## 09/06 :
### Commencement du proposal. 
Développeur   | Tâche
------------- | -------------
Clément       | fin de la réalisation du prototype de collision
Anastasios    | Tester avec des conditions et actions différentes, qui contient plusieurs arguments pour voir si tout marche bien. Correction d’un bug ou on ne pouvait pas avoir plusieurs transitions.
Bastien       | Prototype des armes tester complétement et intégré
Gwénolé       | Finition des mouvements au sol, fluidification des contrôles, et des déplacements au sol. Début du travail sur la gravité. Complétion du système de mouvement, gestion de saut et gravités. Début du travail sur les dashs.
Noah          | Camera (Viewport) fonctionnelle + définition des bases du système de coordonnées des normes. 
Noé           | Finilisation de la barre de vie

### TODO:
- Finir les prototypes pendant le week-end. 

#### Nouvelles idées:
- Avoir un mexican et un ingénieur comme personnages avec deux compétences différentes chacun.
- Le killstreak est devenu de Power-Up car c’est plus intéressant niveau gameplay.

## 12/06 : 
### Discussion avec le prof du 1er proposal. 
Tous : Discussion et précision du gameplay pour le contrat.
  
Développeur   | Tâche
------------- | -------------
Clément       | Début de l’implémentation des collisions dans la version finale du projet.
Anastasios    | Préparation de la structure globale du code pour tout le monde.
Bastien       | Début de l’implémentation des collisions dans la version finale du projet.
Gwénolé       | Réécriture du proposal et fine-tuning des mouvements
Noah          | Préparation de la structure globale du code pour tout le monde. Recréation propre du système de caméra.
Noé           | Réécriture du proposal 

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

Développeur   | Tâche
------------- | -------------
Clément       | création des sprites des deux personnages.
Anastasios    | Adaptation d'automates avec la nouvelle structure. Jeu jouable avec des mouvements gérés par les automates. 
Bastien       | Peaufinage des hitbox
Gwénolé       | Adaptation des mouvements, pour ne plus etre dépendant du tickrate. Début des merges
Noah          | Séparation de la partie View et Model + Explications à tout le monde sur l’utilisation de l’environnement et support.
Noé           | Finition et test de LifeBar.



## 14/06

Tous : discussion avec Emmanuel DUFOUR sur le projet, discussions de l’attente de chacun. Sur les points de doute (jeu de base), particularité du groupe.

  
Développeur   | Tâche
------------- | -------------
Clément       | début de la gestion des powerUps. (résolution de conflit)
Anastasios    | Amélioration du parseur (random state handler), + merge pour avoir les collisions et les balles. (résolution de conflits). 
Bastien       | Refactoring pour encore mieux séparé en MVC, créer une entité est maintenant beaucoup plus simple
Gwénolé       | continuation de rendre les mouvements identiques sur tous les pcs. Début du travail avec les collisions pour les hitboxs.
Noah          | Prototype de Plateforme + Support pour utilisation de l’environnement. Amélioration du système de gestion des entités.
Noé           | Création sprite powerup. Début de la gestion des powerUps. (résolution de conflit)

## 15/06

Développeur    | Tâche
-------------  | -------------
Clément        | Réalisation des powerUps
Anastasios&Noah| Amelioration du systeme d'automates. Maintenant loadAutomate est automatique chaque fois qu'on cree une nouvelle entite. De plus, deux entites peuvent avoir la même automate.
Anastasios     |Correction d'un bug de jump. 
Bastien        | c
Gwénolé        | Travail sur les mouvements, Ajout de courbe de déceleration/accéleration pour les sauts et déplacements,
Noah           | e
Noé            | power up

## 16/06

Développeur   | Tâche
------------- | -------------
Clément       | Réalisation des powerUps et ajout de sprite de texture.
Anastasios    | Correction d'un bug du parser dans lequel le initial state etait le state dest de la derniere transition. Aide les autres avec les automates.
Bastien       | c
Gwénolé       | Travail sur l'animation des playerSprites.
Noah          | e
Noé           | power up

## 19/06

Développeur   | Tâche
------------- | -------------
Clément       | Fix de 2 bugs sur les automates liées aux transitions et aux changements d'états avec Anastasios(lié au développement des powerUps).
Anastasios    | Création du raptor (pour Mexican) avec tous les collisions, et les mouvements. Ajout aussi de MyDir comme condition (et utilisation pour raptor)
Bastien       | c
Gwénolé       | Création de sprites pour les attaques des persos, Travail sur les hitboxs
Noah          | e
Noé           | Malade, je n'ai pas peux avancer


## 20/06

Développeur   | Tâche
------------- | -------------
Clément       | Optimisation du code des powerUps
Anastasios    | b
Bastien       | c
Gwénolé       | Travail sur les hitboxs et les dashs
Noah          | e
Noé           | malade, travaille sur le timer.


## 21/06

### Nouvelles idées:
- Ajout d'un menu pour intégrer l'éditeur de map, le lancement d'une partie et le choix des personnages.

Développeur   | Tâche
------------- | -------------
Clément       | Implémentation de l'attaque bazooka de l'ingenieur
Anastasios    | b
Bastien       | c
Gwénolé       | Travail sur les dashs, et animation.
Noah          | e
Noé           | finition timer, création sprite flasque revigorante 



## 22/06

### Nouvelles idées:
- Ajout d'un bloc pouvant aller de haut en bas

Développeur   | Tâche
------------- | -------------
Clément       | Implémentation du bloc pouvant bouger de haut en bas.
Anastasios    | b
Bastien       | c
Gwénolé       | Implémentation des dashs, Modification du fonctionnement du bazooka, animation de celui-ci et level design
Noah          | e
Noé           | f
