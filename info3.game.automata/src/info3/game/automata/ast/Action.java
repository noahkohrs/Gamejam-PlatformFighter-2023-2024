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

import java.util.LinkedList;
import java.util.List;

import info3.game.automata.util.Pretty;

public class Action extends Node {

  public List<FunCall> calls;

  public Action(List<FunCall> calls) {    
    this.calls = calls;
  }
  
  Object accept(IVisitor visitor) {
	visitor.enter(this);  
    LinkedList<Object> list = new LinkedList<Object>();
    for (FunCall call : this.calls) {
      Object o = call.accept(visitor);
      list.add(o);
    }
    return visitor.exit(this, list);
  }
  
  public String toString() {
	  List <String> strings = new LinkedList<String>();
	  for(FunCall funcall : this.calls) {
		  strings.add( funcall.percent() + funcall.toString() );
	  }
	  return Pretty.separate_with(strings," / ");
  }
  
}