package info3.game.automate;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;

public class TestAutomate {
    public static void main(String[] args) throws Exception{
        
        ParserToAutomate parser= new ParserToAutomate();
        AST ast;
        ast=AutomataParser.from_file(args[0]);
        ast.accept(parser);
        System.out.println("\n\n\nAutomate du parseur\n");
        parser.autos.get(0).prettyPrint();

        // System.out.println("\nEtat initial ->"+parser.autos.get(0).initalState.name+" avec e.x="+parser.autos.get(0).e.x);
        // parser.autos.get(0).step();
        // System.out.println("Etat apres step ->"+parser.autos.get(0).initalState.name+" avec e.x="+parser.autos.get(0).e.x);
        // parser.autos.get(0).step();
        // System.out.println("Etat apres step ->"+parser.autos.get(0).initalState.name+" avec e.x="+parser.autos.get(0).e.x);
        // parser.autos.get(0).step();
        // System.out.println("Etat apres step ->"+parser.autos.get(0).initalState.name+" avec e.x="+parser.autos.get(0).e.x);
        // parser.autos.get(0).step();

        // Entity finale_Rien=parser.autos.get(0).e;
        // System.out.println("\nFin");
    }
}
