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

package info3.game.automata.ast;

import info3.game.automata.util.Dot;
import java.util.List;

public class AstPrinter implements IVisitor {

	StringBuilder nodes;
	StringBuilder edges;

	public AstPrinter() {
		nodes = new StringBuilder();
		edges = new StringBuilder();
	}

	public String to_dot() {
		return Dot.graph("ast", nodes + "\n" + edges);
	}

	// NODES

	protected void dot_node(int id, String label, String options) {
		nodes.append(Dot.declare_node(id, label, options));
	}

	protected void dot_non_terminal_node(int id, String label) {
		dot_node(id, label, "shape=box, fontsize=8");
	}

	protected void dot_terminal_node(int id, String label) {
		dot_node(id, Dot.asString(label), "shape=none, fontname=times,  fontsize=12, fontcolor=blue");
	}

	protected void dot_keyword_node(int id, String label) {
		dot_node(id, label, "shape=none, fontname=comic, fontsize=12, fontcolor=orange");
	}

	// TERMINAL, NON TERMINAL and EDGES

	protected void edge(int src_id, int tgt_id) {
		edges.append(Dot.edge(src_id, tgt_id));
	}

	protected void non_terminal(Node node, String name) {
		dot_non_terminal_node(node.id, name);
	}
	
	protected void identifier(Node node, String name) {
		dot_non_terminal_node(node.id, name);
		edge(node.id, -node.id);
		dot_terminal_node(-node.id, node.toString());
	}

	protected void keyword(Node node, String name) {
		dot_non_terminal_node(node.id, name);
		edge(node.id, -node.id);
		dot_keyword_node(-node.id, node.toString());
	}

	
	protected void subtree(Node node, List<Integer> son_ids) {
		for (Integer id : son_ids) {
			edge(node.id, id);
		}
	}
	
	// THE METHODES OF IVisitor

	public Object visit(BinaryOp operator, Object left, Object right) {
		non_terminal(operator, "BinaryOp");
		edge(operator.id, (Integer) left);
		edge(operator.id, (Integer) right);
		return operator.id;
	}

	public Object visit(UnaryOp operator, Object expr) {
		non_terminal(operator, "UnaryOp");
		edge(operator.id, (Integer) expr);
		return operator.id;
	}

	public void enter(FunCall funcall) {
		non_terminal(funcall, "FunCall");
		edge(funcall.id, -funcall.id);
		dot_keyword_node(-funcall.id, funcall.name);		
	}
	
	public Object exit(FunCall funcall, List<Object> parameters) {
		subtree(funcall, (List<Integer>) (Object) parameters);
		return funcall.id;
	}

	public Object visit(Category terminal) {
		keyword(terminal, "Category");
		return terminal.id;
	}

	public Object visit(Direction terminal) {
		keyword(terminal, "Direction");
		return terminal.id;
	}

	public Object visit(Key terminal) {
		keyword(terminal, "Key");
		return terminal.id;
	}

	public Object visit(Underscore u) {
		return null;
	}

	public Object visit(Value value) {
		return null;
	}

	public void enter(Action action) {}
	
	public Object exit(Action action, List<Object> funcalls) {
		non_terminal(action,"Action");
		subtree(action, (List<Integer>) (Object) funcalls);
		return action.id;
	}

	public Object visit(Behaviour behaviour, List<Object> transitions) {
		non_terminal(behaviour, "Behaviour");
		subtree(behaviour, (List<Integer>) (Object) transitions);
		return behaviour.id;
	}

	public Object visit(State state) {
		identifier(state,"State");
		return state.id;
	}
	
	public Object visit(Transition transition, Object condition, Object action, Object target) {
		non_terminal(transition, "Transition");
		edge(transition.id, (Integer) condition);
		edge(transition.id, (Integer) action);
		edge(transition.id, (Integer) target);
		return transition.id;
	}

	public void enter(Automaton automaton) {
		non_terminal(automaton,"Automaton");
		edge(automaton.id,-automaton.id);
		dot_terminal_node(-automaton.id, automaton.name);		
	}

	public Object exit(Automaton automaton, Object initial_state, List<Object> modes) {
		edge(automaton.id, (Integer) initial_state);
		subtree(automaton, (List<Integer>) (Object) modes);
		return automaton.id;
	}

	public Object visit(AST bot, List<Object> automata) {
		non_terminal(bot,"AST");
		subtree(bot, (List<Integer>) (Object) automata);
		return bot.id;
	}
	
	public void enter(Mode mode) {}

	public Object exit(Mode mode, Object state, Object behaviour) {
		non_terminal(mode,"Mode");
		edge(mode.id, (Integer) state);
		edge(mode.id, (Integer) behaviour);
		return mode.id; 
	}

	public void enter(Condition condition) {}

	public Object exit(Condition condition, Object expr) {
		non_terminal(condition, "Condition");
		edge(condition.id, (Integer) expr);
		return condition.id;
	}

}
