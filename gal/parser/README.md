# DES AUTOMATES ÉCRITS EN `GAL`

- Le parser d'automates est une partie clef du projet.

- Le comportement de chaque élément du jeu sera défini par un automate décrit dans la syntaxe GAL (Game Automata Language) ;
ce qui permet aux différentes équipes de s'échanger leurs automates.


## LE PARSER 

- Le parser est écrit en JavaCC, il est fourni par les enseignants [package parser]

- il produit un AST (*Abstract Syntaxe Tree*) qui correspond à l'arbre de dérivation construit lors du parsing.

- ``TODO`` À partir de l'AST vous devrez générer une représentation exécutable des automates


## PRISE EN MAIN

Pour se familiariser avec le structure de l'AST que génère le parser, vous pouvez tester le parser en ligne de commande.


### Le parser accepte deux sortes d'entrées

- un fichier

``java -cp ./bin parser.AutomataParser -file gal/exemples/exemples.gal``

- une chaîne de caractères

``java -cp ./bin parser.AutomataParser -string "Aut(Idle){ * (Idle)}"``


### Le parser propose deux sorties à partir d'un [fichier .gal](../exemples/exemples.gal)


- avec l'option `-ast`: il produit, au format `.dot`, l'arbre de dérivation généré par le parser 
  (voir [exemples_ast.pdf](../exemples/exemples_ast.pdf) généré par graphviz à partir du fichier `.dot`).

``java -cp ./bin info3.game.automata.parser.AutomataParser -file ../../projet/gal/exemples/exemples.gal -ast > exemples_ast.dot``

- avec l'option `-aut`: il produit la représentation graphique de l'automate au format `.dot`
  (voir [exemples_aut.pdf](../exemples/exemples_aut.pdf) généré par graphviz à partir du fichier `.dot`).

``java -cp ./bin info3.game.automata.parser.AutomataParser -file ../../projet/gal/exemples/exemples.gal -aut > exemples_aut.dot``

- Le format `.dot` peut être visualisé avec l'outil [graphviz](https://www.graphviz.org).


<BLOCKQUOTE>
Ces deux sorties vous montre, de manière graphique, la différence entre
l'Arbre Syntaxique (AST) construit par la parser et
l'automate qu'on a en tête en qu'il faut reconstruire à partir de l'AST.
</BLOCKQUOTE>


## UTILISATION DU PARSER DANS UN DÉVELOPPEMENT

- `TODO` Dans le menu *BuildPath -> javaCC -> javaCC options*, **désactivez l'option STATIC**

- Ci-dessous une utilisation possible du parser pour charger des automates à partir d'un fichier `.gal`

```java
List<Automaton> loadAutomata(String filename) {
    try {
      AST ast = (AST) AutomataParser.from_file(filename);
      ...
      // TODO à vous de constuire les automates à partir de l'AST
      ...
      return automata ;
    } catch (Exception ex) {
      return null;
    }
  }
```

## `BotBuilder`: CONSTRUCTION DES AUTOMATES À PARTIR DE L'AST

Deux manières de construire les automates à partir de l'AST généré par le parser:

### 1. L'AST est un arbre

que vous pouvez parcourir en explorant ses champs pour y piocher les informations nécessaires à la construction des automates.

### 2. Le package AST est fourni avec un Visitor

La méthode `accept(Visitor)` de l'AST déclenche un parcours d'arbre et appelle les fonctions du Visitor (soit *entry* puis *exit*, soit *visit*) sur chacun des noeuds de l'arbre.
Pour générer les automates il suffit donc de fournir l'implémentation de ces fonctions en respectant l'interface suivante.


```java
public interface IVisitor {

  public Object visit(Category cat);
  public Object visit(Direction dir);
  public Object visit(Key key);
  public Object visit(Value v);
  public Object visit(Underscore u);
  
  public void enter(FunCall funcall);
  public Object exit(FunCall funcall, List<Object> parameters);

  public Object visit(BinaryOp operator, Object left, Object right);
  public Object visit(UnaryOp operator, Object expression);
  public Object visit(State state);

  public void enter(Mode mode);
  public Object exit(Mode mode, Object source_state, Object behaviour);

  public Object visit(Behaviour behaviour, List<Object> transitions);

  public Object enter(Condition condition);
  public Object exit(Condition condition, Object expression);

  public Object enter(Action action);
  public Object exit(Action action, List<Object> funcalls);

  public Object visit(Transition transition, Object condition, Object action, Object target_state);

  public void enter(Automaton automaton);
  public Object exit(Automaton automaton, Object initial_state, List<Object> modes);

  public Object visit(AST bot, List<Object> automata);  
}
```

Voici deux exemples d'implémentation de l'interface IVisitor utilisées dans le package *info3.game.automata.ast*

- [AstPrinter.java](AstPrinter.java) génère la sortie `-ast` au format `.dot` du parser
- [AutPrinter.java](AutPrinter.java) génère la sortie `-ast` au format `.dot` du parser


# [SUITE](../../README.md)

---

	AUTHOR: Michaël PÉRIN, Polytech'Grenoble, Univ. Grenoble Alpes
	DATE: mars 2020
