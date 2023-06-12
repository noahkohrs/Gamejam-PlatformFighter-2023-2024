/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Dr. Michaël Périn, Univ. Grenobles-Alpes
 */

/* USAGE (see Automaton.java)
 *  Ast ast;
 *  ...; 
 *  Aut_to_Dot_Visitor visitor = new Aut_to_Dot_Visitor(); 
 *  ast.accept(visitor);
 *  System.out.println(visitor.to_dot());  
 */

package info3.game.automata.ast;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import info3.game.automata.ast.Node;
import info3.game.automata.util.Dot;

public class AutPrinter implements IVisitor {

	PrintStream ps;
	Integer current_source_state;

	/**
	 * /!\ States appear as source and target of transitions.
	 * 
	 * A naive implementation would create distinct copies of the same state: - one
	 * when it is a source, - one when it is a target resulting into disconnected
	 * automaton with floating transitions.
	 * 
	 * SOLUTION We need to build a mapping from State name -->
	 * DoState(id,name,options). Thus, when encountering a state that has already
	 * been stored in the mapping we can ask the mapping what is the id we must use
	 * for that state.
	 */

	private Map<String, State> state_map;

	Integer state_id(State state) {
		State stored_state = state_map.get(state.name);
		if (stored_state != null) {
			return stored_state.id;
		} else {
			state_map.put(state.name, state);
			return state.id;
		}
	}

	void print_state_map() {
		for (State state : state_map.values()) {
			state_node(state);
		}
	}

	// CONSTRUCTOR

	public AutPrinter(PrintStream ps, AST bot) {
		this.ps = ps;
		this.state_map = new HashMap<String, State>();
		Dot.start_graph(this.ps, "bot");
		bot.accept(this);
		Dot.end_graph(this.ps, "bot");
	}

	// DOT GRAPH, SUBGRAPH

	public void new_subgraph(Automaton automaton) {
		Dot.start_subgraph(this.ps, automaton.name);
	}

	public void end_subgraph(Automaton automaton) {
		Dot.end_graph(this.ps, automaton.name);
	}

	// EDGE, NODES

	void edge(int source, int target) {
		this.ps.print(Dot.edge(source, target));
	}

	void node(Node node, String options) {
		this.ps.println(Dot.declare_node(node.id, node.toString(), options));
	}

	void state_node(State state) {
		node(state, "shape=circle, style=filled, fontsize=5");
	}

	void automaton_node(Automaton automaton) {
		node(automaton, "shape=none, fontname=times, fontsize=12, fontcolor=blue");
	}

	void transition_node(Transition transition) {
		node(transition, "shape=box, fontname=comic, fontsize=10");
	}

	// THE METHODS REQUIRED BY IVisitor

	public Object visit(Category cat) {
		return null;
	}

	public Object visit(Direction dir) {
		return null;
	}

	public Object visit(Key key) {
		return null;
	}

	public Object visit(Value v) {
		return null;
	}

	public Object visit(Underscore u) {
		return null;
	}

	public void enter(FunCall funcall) {
	}

	public Object exit(FunCall funcall, List<Object> params) {
		return null;
	}

	public Object visit(BinaryOp operator, Object left, Object right) {
		return null;
	}

	public Object visit(UnaryOp operator, Object exp) {
		return null;
	}

	public Object visit(State state) {
		return state_id(state);
	}

	public void enter(Mode mode) {
		this.current_source_state = state_id(mode.state);
	}

	public Object exit(Mode mode, Object source_state, Object behaviour) {
		return null;
	}

	public void enter(Condition condition) {}

	public Object exit(Condition condition, Object exp) {
		return null;
	}

	public void enter(Action action) {}

	public Object exit(Action action, List<Object> funcalls) {
		return null;
	}

	public Object visit(Transition transition, Object condition, Object action, Object target) {
		transition_node(transition);
		edge(this.current_source_state, transition.id);
		edge(transition.id, (Integer) target);
		return transition.id;
	}

	public Object visit(Behaviour behaviour, List<Object> transitions) {
		return null;
	}

	public void enter(Automaton automaton) {
		this.state_map.clear();
		Dot.start_subgraph(this.ps, automaton.name);
		automaton_node(automaton);
	}

	public Object exit(Automaton automaton, Object initial_state, List<Object> modes) {
		edge(automaton.id, (Integer) initial_state);
		print_state_map();
		Dot.end_graph(this.ps, automaton.name);
		return null;
	}

	public Object visit(AST bot, List<Object> automata) {
		return null;
	}

}
