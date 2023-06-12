# SÉMANTIQUE DES ACTIONS ET CONDITIONS

Une entité est forcément orientée dans une direction Up, Down, Right, Left.

> *On peut imaginer plus fin, y compris au degré près, mais c'est plus compliqué.*

## LES DIRECTIONS

### Absolues
- N = North
- S = South
- E = East
- W = West
- le parseur accepte les diagonales NE, NW, SE, SW

### Relatives
- F = front
- B = back
- L = on my left
- R = on my right


## LES TOUCHES

- les lettres a,...,z
- les chiffres 0...9
- SPACE, ENTER,
- les flêches: FU, FD, FR, FL


## LES CATÉGORIES

- A = un **Adversaire**
- C = un indice d'un précédent passage (**Clue**)
- D = un **Danger**
- G = un **Gate** (passage)
- J = un élément sur lequel on peut sauter (**Jumpable**)
- M = un **Missile**
- O = un **Obstacle**
- P = un élément que l'on peut **Prendre**, stocker, lancer, déposer
- T = **Team** = une entité de mon équipe
- V = **Void**
- @ = Le joueur de mon équipe
- _ = n'importe quelle entité *sauf Void*


## LES CONDITIONS

### Les conditions booléennes de base
-  True : toujours vraie
-  Key(Touche) : vrai si une touche est enfoncée et c'est Touche
-  MyDir(Direction) : vraie si l'entité est orientée dans la Direction
-  Cell(Direction, Catégorie) : vraie si la cellule dans la Direction contient une Entité
-  GotPower : vraie s'il reste de l'énergie à l'entité
-  GotStuff : vraie s'il reste des choses dans le store
-  Closest(Categorie, Direction) : vraie si l'entité de la Catégorie demandée, la plus proche est dans la Direction

### Les opérateurs sur les conditions
- conjonction: Condition1 & Condition2
- disjunction: Condition1 / condition2
- negation: not(Condition)


## LES ACTIONS

### Les actions peuvent avoir ou non une direction.


### Wizz et Pop = deux actions essentielles pour votre jeu (la direction est optionnelle, par défaut F)
-  Wizz(Direction) = ?
-  Pop(Direction)  = ?

**Remarque importante**
  - Si dans votre jeu une action Wizz n'a pas de direction.
    alors vous interpréterait Wizz(d) comme Wizz.
  - Si, au contraire, l'action Wizz doit avoir une direction
    et que l'automate n'en donne pas. Vous interpreterez Wizz comme Wizz(F).


### Wait
- Wait = attente active, c'est une action qui prend du temps


### Déplacements (direction optionnelle, par défaut F)
- Move(Direction) = déplacement
- Move    : sans paramètre avance suivant l'orientation actuelle de l'entité
- Move(N) :avance dans la direction N

### Saut (direction optionnelle, par défaut F)
- Jump(Direction) = saut
- Jump   : saut vers l'avant, dessus ou par delà d'un obstable
- Jump(B): saut vers l'arrière
- Jump(N): saut vers le nord

### Rotation (direction optionnelle, par défaut R)
+ Turn(Direction) = changement de direction (sans déplacement)
+ Turn    : tourne dans le sens horaire
+ Turn(R) : +90 degrés
+ Turn(L) : tourne dans le sens anti-horaire = -90 degrés
+ Turn(B) : fait demi-tour par rapport à l'orientation actuelle de l'entité = 180 degrés
+ Turn(S) : tourne l'entité vers le sud

### Affrontements (direction optionnelle, par défaut F)
+  Hit(Direction) = frapper
   - Hit   : frappe selon l'orientation actuelle de l'entité
   - Hit(B): donne un coup en arrière
   - Hit(N): donne un coup au nord
+  Protect(Direction) = protection

### Collecte (direction optionnelle, par défaut F)
+  Pick(Direction) = ramasser une chose
+  Throw(Direction) = lancer/déposer ce que l entité a dans la main.

### Stockage (action sans argument)
+  Store = mettre en réserve (dans son sac)
+  Get   = prendre une entité dans sa réserve si aucune en main, changer d'entité si une en main (elle est remise dans le sac)

### Puissance et Transformation (action sans argument)
+  Power   = pas d'action, mais récupération d'énergie.
+  Explode = disparition, suicide, explosion, tranformation en autre chose, ...

### Reproduction
+  Egg(F) = crée une nouvelle entité de même nature que l'entité qui a effectué l'action sur la case devant.


# [SUITE](README.md)


---
    AUTHOR: Michaël PÉRIN, Polytech'Grenoble, Univ. Grenoble Alpes
    DATE: 2021
