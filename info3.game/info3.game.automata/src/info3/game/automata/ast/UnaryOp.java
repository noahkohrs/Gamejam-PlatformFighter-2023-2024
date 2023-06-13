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
 *      Author: Pr. Olivier Gruber <olivier dot gruber at acm dot org>
 *              Dr. Michaël Périn, Verimag / Univ. Grenoble-Alpes 
 */
package info3.game.automata.ast;

public class UnaryOp extends Expression {

	public String operator;
	public Expression operand;

	public UnaryOp(String op, Expression arg) {
		this.operator = op;
		this.operand = arg;
	}
	
	public String toString() {
		return operator + " " + operand.toString();
	}

	public String name() {
		return "";
	}
	
	Object accept(IVisitor visitor) {
		Object o = operand.accept(visitor);
		return visitor.visit(this, o);
	}
}
