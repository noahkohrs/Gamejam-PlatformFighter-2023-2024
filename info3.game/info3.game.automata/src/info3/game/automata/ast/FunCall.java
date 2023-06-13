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
import java.util.ListIterator;

public class FunCall extends Expression {

	static final int NO_PERCENT = -1; 
	public int percent;
	public String name;
	public List<Parameter> parameters;

	public FunCall(String name, List<Parameter> parameters) {
		this.percent = NO_PERCENT; 
		this.name = name;
		this.parameters = parameters;
	}

	/**
	 * @param percent
	 *            is used by probabilistic action
	 */
	public FunCall(int percent, String name, List<Parameter> parameters) {
		this.percent = percent;
		this.name = name;
		this.parameters = parameters;
	}

	public String percent() {
		switch (percent) {
		case 100:
			return "";
		case -1:
			return "_%";
		default:
			return percent + "%";
		}
	}

	public String toString() {
		String s = percent+"%"+name;
		if (parameters.size() > 0) {
			s += "(";
			ListIterator<Parameter> iterator = parameters.listIterator();
			while (iterator.hasNext()) {
				s += iterator.next().toString();
				if (iterator.hasNext())
					s += ",";
			}
			s += ")";
		}
		return s;
	}

	public String name(){
		return name;
	}
	
	Object accept(IVisitor visitor) {
		visitor.enter(this);
		LinkedList<Object> list = new LinkedList<Object>();
		for (Parameter parameter : parameters) {
			Object o = parameter.accept(visitor);
			list.add(o);
		}
		return visitor.exit(this, list);
	}

}
