# DES EXEMPLES D'AUTOMATES DANS LA SYNTAXE GAL

## Un automate qui ne fait rien

Un seul état, pas de transition.

```haskell
Philosopher(Think){
  * (Think)
}
```
<BLOCKQUOTE>
Cet automate ne fait rien, indéfiniment, sans mourrir. 
</BLOCKQUOTE>


## Une mine 

```haskell
Mine(Banzai){
  * (Banzai) 
  | Cell(F,A) ? Explode  :()
  | True ?               :(Banzai)
  * ()
}
```
<BLOCKQUOTE>
Cet automate attend patiemment qu'un adversaire se place devant lui pour exploser et s'autodétruire en allant dans l'état ().
</BLOCKQUOTE>


## Une poule

```haskell
Poule(Pondre){
  * (Pondre)
  | Cell(F,V) ? Egg(F)  :(Couver)

  * (Couver)
  | Cell(F,T) ? Move    :(Attendre)

  * (Attendre)
  | Cell(H,V) ?          :(Pondre) 
}
```
<BLOCKQUOTE>
Cet automate pond un oeuf sur la case libre devant lui, avance sur l'oeuf et couve jusqu'à l'éclosion (= lorsque la case se libère). 
</BLOCKQUOTE>


## Une trainée de boules de feu

```haskell
Fire(Forward){
 * (Forward)
 | Cell(F,V) ? Move(F)    :(Copy)
 | Cell(F,O) ? Explode(H) :()
 | True      ?            :()

 * (Copy)
 | Cell(F,O) ? Explode(H) :()
 | Cell(B,V) ? Egg(B)     :(Forward) 
}
```
<BLOCKQUOTE>
Cet automate avance et crée une copie derrière lui, il explose puis disparaît à la rencontre d'un obstacle.
Sur les autres éléments il n'a pas d'effet  et disparaît sans faire de dégat.
</BLOCKQUOTE>



## Un egyptologue qui marque les endroits où il est passé et se suicide s'il tourne en rond

```haskell
Egyptologue(Explore){
 * (Explore)
 | Cell(H,C) ?          :()
 | Cell(F,V) ? Move(F)  :(Paint)
 | Cell(L,V) ? Turn(L)  :(Explore)
 | Cell(R,V) ? Turn(R)  :(Explore)
 | Cell(B,V) ? Turn(B)  :(Explore)

 * (Paint): True ? Pop(B) :(Explore)
}
```
<BLOCKQUOTE>
Cet automate regarde en priorité s'il y a une marque là où il se trouve, auquel cas il comprend qu'il tourne en rond et se suicide.
Sinon il avance sur une case libre et la marque au moyen de l'action Pop.
</BLOCKQUOTE>

`Important` l'action Pop ne signifie pas "marquer" de manière générale, mais dans ce jeu de labyrinthe dans des pyramides 
c'est l'interprétation qu'ont choisie les concepteurs du jeu.




## Un VIP qui se déplace avec ses gardes du corps

```haskell
VIP(Init){
 * (Init): Cell(L,T) & Cell(R,T) & Cell(B,T) & Cell(F,V) ? Move :(Init)
}
```

<BLOCKQUOTE>
Cet automate avance uniquement s'il est encadré d'entités de son équipe.
</BLOCKQUOTE>



## Introduire de l'aléatoire (1/2)

```haskell
PopOrWizz1(Init){
  * (Init): True ? 49%Pop / 49% Wizz / Power :(Init)
}
```
<BLOCKQUOTE>
L'automate fait Pop 49% de ses actions, Wizz à 49%  et Power les 2 % restants.
</BLOCKQUOTE>


## Introduire de l'aléatoire (2/2)

```haskell
PopOrWizz2(State1){
  * (State1): True ? Pop  :(_)
  * (State2): True ? Wizz :(_)
}
```
<BLOCKQUOTE>
L'automate commence forcément par un Pop puis saute dans un de ses états (State1 ou State2) tiré aléatoirement. C'est la sémantique de l'état cible (_).
</BLOCKQUOTE>



## Suit les indices (**Clues**) laissés au sol lors d'un précédent passage et avance sinon

```haskell
Fourmi(Init){
 * (Init):
 | Cell(F,C) ? Move(F) :(Init)
 | Cell(R,C) ? Turn(R) :(Init)
 | Cell(L,C) ? Turn(L) :(Init)
 | Cell(F,V) ? Move    :(Init)
}
```
<BLOCKQUOTE>
L'automate repère les Indices au sol C (pour Clue), de préférence devant sinon à droite sinon à gauche, et les suit.
S'il ne trouve aucun indice il avance tout droit.
</BLOCKQUOTE>


## Que fait cet automate ?

```haskell
Blocker(Go){
* (Go):
    | Cell(F,V) ? Move :(Go)
    | True ?           :(Block)
* (Block)
}
```
<BLOCKQUOTE>
En exercice
</BLOCKQUOTE>


## Que fait cet automate ?

```haskell
Escape(Init){
* (Init):
  | Cell(N,V) & Cell(S,V) & Cell(E,V) & Cell(W,V) ? :(Init)
  | Cell(F,V) ? Move(F) :(Init)
  | Cell(L,V) ? Turn(L) :(Init)
  | Cell(R,V) ? Turn(R) :(Init)
  | Cell(B,V) ? Move(B) :(Init)
}
```
<BLOCKQUOTE>
En exercice
</BLOCKQUOTE>



## L'automate du joueur

```haskell
Player(Init){
  * (Init):
  | Key(FU) ? Move(N) :(Init)
  | Key(FD) ? Move(S) :(Init)
  | Key(FL) ? Move(O) :(Init)
  | Key(FR) ? Move(E) :(Init)
  | Key(SPACE) ? Hit  :(Init)
  | Key(ENTER) ? Jump :(Init)
  | Key(b)  ? Jump(B) :(Init)
  | Key(d)  ? Move(D) :(Init)
  | Key(e)  ? Move(E) :(Init)
  | Key(f)  ? Turn(B) :(Init)
  | Key(p)  ? Pop     :(Init)
  | Key(w)  ? Wizz    :(Init)
  | Key(g)  ? Get     :(Init)
  | Key(t)  ? Throw   :(Init)
  | True    ? Power   :(Init)
}
```
<BLOCKQUOTE>
Facile...
</BLOCKQUOTE>



## Exercices

Afin de prendre en main le langage GAL de descriptiont d'automates et de tester votre interpréteur de comportement, on vous conseille de réaliser les automates suivants :

  1. une entité qui tourne sur elle même et frappe 
  2. une entité qui parcourt une ligne et fait demi-tour quand elle touche un bord
  3. une entité qui suit l'entité du joueur se déplaçant au clavier
  4. une entité qui se réplique et remplit les cases vides autour d'elle.


# D'autres [automates écrits par les élèves des années passées](automata.gal) 


# [SUITE](../README.md)

---
    AUTHOR: Michaël PÉRIN, Polytech'Grenoble, Univ. Grenoble Alpes 
    DATE: 2021
