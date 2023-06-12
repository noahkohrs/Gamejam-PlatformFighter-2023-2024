# IMPLANTATION D'AUTOMATES EXÉCUTABLES

Le comportement des entités du jeu est décrit sous forme d'automates GAL.
Le parser construit un AST (Arbre de Syntaxe Abstraite) à partir d'un fichier `.gal`.
L'arbre généré par le parser correspond à l'arbre de dérivation issue de la grammaire. Ce n'est pas une représentation adaptée pour faire fonctionner l'automate.

À partir de l'arbre il faut donc constuire un objet de la classe `Automaton` qui possède une méthode `step` lui ordonnant d'effectuer une transition.


### Chaque Entité du jeu possède
- un automate
- l'état courant de son automate

### Chaque automate doit posséder

- un état initial
- des transitions
- une méthode `step(Entity,...)`

### Chaque transition doit posséder
- l'état source
- sa condtion
- l'action associée
- l'état cible

### Les Conditions devront fournir une méthode
```java
boolean eval(Entity ...)
```
<BLOCKQUOTE>
qui utilise les données de l'entité passée en paramètre (position, voisinage, ... du modèle) pour déterminer si la condition est statisfaite ou non
</BLOCKQUOTE>

### Les Actions devront fournir une méthode
```java
boolean/void apply(Entity ...)
```
<BLOCKQUOTE>
On peut opter pour une méthode apply qui retourne un booléen indiquant si l'action a pû s'effectuer ou non.
</BLOCKQUOTE>


## Principe de fonctionnement

Un jeu est un simulateur d'un monde artificiel.

1. Le Controller demande au Model d'effectuer un pas de simulation
2. Le Model demande à chaque Entité d'effectuer un pas de simulation
3. Chaque entité demande à son automate d'effectuer une transition à partir de l'*état courant* (`step`)
4. **Que fait la méthode step de l'automate ?**
   - elle examine les transitions sortant de l' *état courant*
   - elle évalue leurs conditions pour savoir celle(s) qui sont satisfaites via la méthode `eval(Entity ...)`
   - s'il y a plusieurs  transitions faisables (**voir non-déterminisme**) elle en choisit une
   - elle demande à l'action de la transition choisie de s'appliquer à l'entité via la méthode `exec(Entity...)`
   - l'action invoquée modifie les caractéristiques de l'entité et le modèle
   - la méthode step fournit à l'entité l'état cible de la transition
5. l'entité met à jour son état courant
6. Lorsque toutes les entités ont fini leur mise à jour du Model on lancer un nouveau pas de simulation.

## Interprétation déterministe ou non-déterministe ?

<BLOCKQUOTE>
Quelle transition prendre si plusieurs conditions sont satisfaites ?
</BLOCKQUOTE>

Considérez par exemple l'automate suivant
```haskell
PowOrWizz(Init){
* (Init):
    | True ? Pop   :(Init)
    | True ? Wizz  :(Init)
}
```

À vous de choisir parmi les deux possibilités :

* Si les transitions sont évaluées dans l'ordre. La première transition dont la condition est satisfaite sera sélectionnée.
  Dans ce cas les transitions située après la première condition "True" ne seront jamais prises (ici, Wizz ne sera jamais exécutée).

* Vous pouvez opter pour un interpréteur non-déterministe qui tire au sort parmi les transitions dont les conditions sont satisfaites.


# [SUITE](../README.md)

---
    AUTHOR: Michaël PÉRIN, Polytech'Grenoble, Univ. Grenoble Alpes
    DATE: 2021
