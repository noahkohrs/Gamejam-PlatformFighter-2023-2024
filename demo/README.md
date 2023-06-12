# DÉMO du moteur AUTOMATA GAME

## Un exemple de jeu réalisé avec le moteur Automata Game

Une sorte de mix entre PacMan, Boulder Dash et Pengo : [Vidéo de démonstration](AutomataGame.mp4)

## Les automates

À partir d'un tore et des entités disponibles (pac man, cailloux et cristaux)
j'ai proposé à deux enfants de 11 ans de réaliser leur jeu.

- Je leur ai expliqué le langage des automates
- Je les ai laissé expérimenter, en les aidant à corriger leurs bugs de syntaxe.

Au bout de 2h, avec un peu d'aide pour `Pushed` et `Cell(A,_)`,  ils avaient réalisé le jeu de la démo.
Voici leur automates.


#### Les PacMen

```haskell
PacMen(Explore){

* (Explore):
  | Cell(N,@) ? :(PushedS)
  | Cell(S,@) ? :(PushedN)
  | Cell(E,@) ? :(PushedW)
  | Cell(W,@) ? :(PushedE)
 
  | Cell(F,V) ? 50% Move  :(Explore)
  | Cell(L,V) ? 10% Turn(L)  :(Explore) 
  | Cell(R,V) ? 10% Turn(R)  :(Explore) 

  | Cell(N,V) & Closest(@,N) ? Turn(N) :(Explore)
  | Cell(S,V) & Closest(@,S) ? Turn(S) :(Explore)
  | Cell(E,V) & Closest(@,E) ? Turn(E) :(Explore)
  | Cell(W,V) & Closest(@,W) ? Turn(W) :(Explore)

  | Cell(B,V) ? 10% Turn(B)  :(Explore) 
  
  | Cell(F,P) ?  Pick   :(Explore)
  | Cell(R,P) ?  Pick   :(Explore)
  | Cell(L,P) ?  Pick   :(Explore)
  
  // Trapped  	
  | ! Cell(_,V) ?  :()
 
  * (PushedN): 
  | Cell(N,T) ? :()
  | Cell(N,V) ? Move(N) :(PushedN)
  | True ? :(Explore)
  
  * (PushedS): 
  | Cell(S,T) ? :()
  | Cell(S,V) ? Move(S) :(PushedS)
  | True ? :(Explore)
  
  * (PushedE): 
  | Cell(E,T) ? :()
  | Cell(E,V) ? Move(E) :(PushedE)
  | True ? :(Explore)
  
  * (PushedW): 
  | Cell(W,T) ? :()
  | Cell(W,V) ? Move(W) :(PushedW)
  | True ? :(Explore)  
}
```

#### Les cristaux

```haskell
Gem(Escape){

 * (Escape):

 | Cell(N,V) & Closest(@,S) ? Move(N) :(Escape) 
 | Cell(S,V) & Closest(@,N) ? Move(S) :(Escape) 
 | Cell(E,V) & Closest(@,W) ? Move(E) :(Escape) 
 | Cell(W,V) & Closest(@,E) ? Move(W) :(Escape) 

 | Cell(N,V) & Closest(A,S) ? Move(N) :(Escape) 
 | Cell(S,V) & Closest(A,N) ? Move(S) :(Escape) 
 | Cell(E,V) & Closest(A,W) ? Move(E) :(Escape) 
 | Cell(W,V) & Closest(A,E) ? Move(W) :(Escape) 
}
```

#### Les cailloux qui tombent

```haskell
GravityV(Tomber){

 * (Tomber):
 | Cell(A,_) ?         :(Tomber)
 | Cell(S,V) ? Move(S) :(Tomber) 
 | Cell(E,V) ? Move(E) :(Tomber) 
 | Cell(W,V) ? Move(W) :(Tomber) 
 }
```

#### Les cailloux qui tombent vers la gauche

```haskell
GravityH(Tomber){

 * (Tomber):
 | Cell(A,_) ?         :(Tomber)
 | Cell(W,V) ? Move(W) :(Tomber) 
 | Cell(S,V) ? Move(S) :(Tomber) 
 | Cell(N,V) ? Move(N) :(Tomber) 
 
 }
```

#### Les cailloux de la bordure

```haskell
Mine(Banzai){
  * (Banzai) 
  | Cell(N,@) ? Explode :()
  | Cell(S,@) ? Explode :()
  | Cell(W,@) ? Explode :()
  | Cell(E,@) ? Explode :()
}
```


---
    AUTHOR: Michaël PÉRIN, Polytech'Grenoble, Univ. Grenoble Alpes 
    DATE: mai 2020
