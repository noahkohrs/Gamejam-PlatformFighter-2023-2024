package info3.game.automata.ast;

import java.util.LinkedList;
import java.util.List;

public class Behaviour  extends Node {

	public List<Transition> transitions;

	public Behaviour(List<Transition> transitions) {
		this.transitions = transitions;
	}

	Object accept(IVisitor visitor) {
		LinkedList<Object> list = new LinkedList<Object>();
		for (Transition transition : this.transitions) {
			Object o = transition.accept(visitor);
			list.add(o);
		}
		return visitor.visit(this, list);
	}

}