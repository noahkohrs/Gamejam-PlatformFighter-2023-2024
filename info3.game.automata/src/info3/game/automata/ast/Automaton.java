package info3.game.automata.ast;
import java.util.LinkedList;
import java.util.List;

public class Automaton extends Node {

	public String name;
	public State initial_state;
	public List<Mode> modes;

	public Automaton(String name, State initial_state, List<Mode> modes) {
		this.name = name;
		this.initial_state = initial_state;
		this.modes = modes;
	}
	
	public String toString() {
		return name;
	}

	/** VISITOR */

	public Object accept(IVisitor visitor) {	
		visitor.enter(this);
		Object o_initial_state = initial_state.accept(visitor);
		LinkedList<Object> list = new LinkedList<Object> ();
		for(Mode mode : modes) {
			Object o = mode.accept(visitor);
			list.add(o);
		}
		return visitor.exit(this, o_initial_state, list);
	}
	
}