# FAQ - DESIGN DU JEU 


## Quelle est la différence entre `Entity` et `Category` ?

- Une Entity peut appartenir à plusieurs Category.

Par exemple, une entité Bombe appartient aux catégories :
*Obstacle, Danger, Jumpable, Pickable, Throwable*

- Une Category concerne plusieurs Entity.

Par exemple, la categorie *Ennemi* contient le joueur adverse et tous ses co-équipiers au comportement automatique.



## Quelle est la différence entre `Entity` et `Avatar` ?

L'objectif pédagoqique est de vous amener à réfléchir à une architecture MVC où l'effet graphique des actions n'est pas défini directement dans l'entité.

- Tous les éléments du jeu (joueur, personnage, décor, objet, obstable, mur, ...) sont des `Entity`
  qui ont en commun (entre autres) d'avoir une position dans le model, un automate, un état courant et de définir 
  l'effet des actions sur le model.

- Chaque entity possède un (ou plusieurs) `Avatar` qui est l'acteur qui joue l'Entité à l'écran.

- un `Avatar` possède une *sprite sheet* et des *animations graphiques des actions* 



## Des `Entity`, des `Stunts` et des `Avatars` ?

Considérons le cas concret d'un coéquipier automatique (un bot) qui se déplace via une action `move` dans deux mondes différents : 
  - **un monde terrestre** où `move` signifie marcher
  - **un monde aquatique** où `move` signifie nager et donc le déplacement est moins rapide et consomme plus d'énergie. 

L'extrait de code suivant **N'EST PAS** le codage qu'on vous recommande.
```java
class Bot extends Entity{
  void move(){ 
     if (aquatique) {...} else {...} 
  }
```       
On vous conseille de définir deux `Stunts` (Doublure au cinéma)
l'un pour le monde aquatique, l'autre pour le monde terrestre,
et de changer de Doublure en fonction du monde lors de la mise à jour de l'Entity.

```java
class Aquaman extends Stunt{
   void move(){ // nage lentement en se fatiquant }
   void jump(){ // plonger pour éviter le danger }
}

class Groundman extends Stunt{
   void move(){ // marche vite sans se fatiguer }
   void jump(){ // sauter pour éviter le danger }
}   

class Bot extends Entity{
  Stunt stunt;

  void update(){
  // on change de doublure selon le monde dans lequel on évolue 
  //   stunt = groundman; // quand le bot est sur terre
  //   stunt = aquaman  ; // quand le bot est dans l'eau
  }
  
  // les actions sont définies une fois pour toute indépendamment du nombre de monde.
  void move(){ stunt.move(); }
  void jump(){ stunt.jump(); } 
}
```

Par ailleurs, chaque Stunt a un Avatar qui est sa version graphique et sait comment effectue (graphiquement) un saut.
<BLOCKQUOTE>
- L'entité Bot du Model a deux doublures Aquaman et Groundman
- Chaque doublure a un Avatar (son déguisement) dans la View
</BLOCKQUOTE>


## Où doit-on mettre les `automates` ? 

Dans l'exemple précédent, le comportement automatique d'un d'Aquaman est différent de celui d'un Groundman.
Face à un danger Aquaman plonge tandis que Groundman attaque. Ils auront donc des automates différents.

*Deux solutions*
- associer un `Automate à chaque Doublure`
- affecter `plusieurs Autoamtes à l'Entité`, dans ce cas, il faudra changer d'automate lorsque l'entité change de doublure.

Les` HashMap` sont une façon élégante de construire ces associations entre type de terrain -> doublure, doublure -> automate.


# [SUITE](README.md)


---
    AUTHOR: Michaël PÉRIN, Polytech'Grenoble, Univ. Grenoble Alpes 
    DATE: mars 2020



