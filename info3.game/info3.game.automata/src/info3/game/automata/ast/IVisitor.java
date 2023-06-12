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
 *      Author: Dr. Michael PÉRIN, Verimag / Univ. Grenoble-Alpes
 */
package info3.game.automata.ast;

import java.util.List;

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

  public void enter(Condition condition);
  public Object exit(Condition condition, Object expression);

  public void enter(Action acton);
  public Object exit(Action action, List<Object> funcalls);

  public Object visit(Transition transition, Object condition, Object action, Object target_state);  

  public void enter(Automaton automaton);
  public Object exit(Automaton automaton, Object initial_state, List<Object> modes);

  public Object visit(AST bot, List<Object> automata);
  
}
