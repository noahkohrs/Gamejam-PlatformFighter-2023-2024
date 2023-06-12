# FAQ - AUTOMATES 

## Comment organiser les `automates` dans les `fichiers` ?

Un bot (coéquipier au comportement automatique) peut avoir plusieurs `Stunts` (**doublures**) (par ex. Aquaman et Groundman)
et donc plusieurs automates (au moins un par doublure).

On rangera donc dans un fichier `bot.gal` deux automates

```haskell
Aquaman(Init){
* (Init): ...
} 

Groundman(Init){
* (Initi): ...
}
```

Le parser de fichier `.gal` génère un ast `Bot` qui contient la liste des automates du fichier.

À vous d'associer 
- l'automate Aquaman à l'avatar Aquaman et cet avatar au monde Aquatique
- l'automate Groundman à l'avatar Groundman et cet avatar au monde Terrestre



## Où doit-on stocker l'`état courant` de l'`automate` ?

- pas dans l'automate (cf. question suivante).
- dans la classe qui possède l'autoamte (soit Stunt, soit Entity)


## Un même `Automate` peut-il être `partagé` par plusieurs `Entités` ?

Plusieurs Entités peuvent utiliser la même instance de l'automate car la consultation des transitions ne modifie pas l'automate.

`TIPS` Pour diminuer la consommation mémoire il faut que les entités qui ont le même automate se réfèrent à la même *unique* instance de l'automate.


## `AutBuilder` : Comment associer un entier à chaque un nom d'état ?

On vous conseille d'utiliser un HashMap<String, Integer> qui permet d'enregister et de retrouver l'entier associer à un nom.


## `AutBuilder` : Comment représenter le choix entre des actions avec pourcentage ?

Considérons la transition
```haskell
Condition ? 30% Pop / Move / 20% Wizz / Turn
```
Puisque l'opérateur `/` sur les actions est associatif et commutatif, on vous conseille de représenter en mémoire
la partie action sous la forme d'une collection  (tableau, liste, ...) d'actions. 
```haskell
  Pop(30) ; Move(?) ; Wizz(20) ; Turn(?) 
```
Les actions dont le pourcentage n'est pas spécifiée doivent recevoir une part équitable des 50% restants, soit
```haskell
  Pop(30) ; Move(25) ; Wizz(20) ; Turn(25)
```

### Cas où la somme des % ne fait pas 100 ?

Dans le cas d'un transition
```haskell
Condition ? 10% Pop / 10% Wizz
```
Dans 80% des cas la transition n'effectuera aucune action. Dans ce cas elle échoue et reste dans l'état de départ.


## Comment choisir aléatoirement une action respectant les pourcentages ?

Considérons un liste d'actions avec leurs pourcentages respectifs
```haskell
  Pop(30) ; Move(25) ; Wizz(20) ; Turn(25) 
```

À partir de ces valeurs et de l'ordre des actions dans la liste, on associe à chaque action un intervalle :
```haskell
  Pop([0,30]) , Move([31,30+25]) , Wizz([56,55+20]) , Turn([76,75+25) 
```

  Maintenant il devient simple de choisir l'action aléatoirement l'action à effectuer :
  + On tire aléatoirement un entier dans l'intervalle [0,100], supposons qu'on obtiennent 62.
  + On choisit l'action qui contient 62 dans son intervalle (Wizz dans l'exemple).




# [SUITE](../README.md)

---
    AUTHOR: Michaël PÉRIN, Polytech'Grenoble, Univ. Grenoble Alpes 
    DATE: mars 2020
