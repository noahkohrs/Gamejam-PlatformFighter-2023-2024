package info3.game.automata.ast;

import java.util.LinkedList;
import java.util.List;

public class AST extends Node  {

	public List<Automaton> automata;

	public AST(List<Automaton> list) {
		this.automata = list;
	}

	public Object accept(IVisitor visitor) {
		LinkedList<Object> list = new LinkedList<Object> ();
		for (Automaton automaton : this.automata) {
			Object o = automaton.accept(visitor);
			list.add(o);
		}
		return visitor.visit(this,list);
	}

}