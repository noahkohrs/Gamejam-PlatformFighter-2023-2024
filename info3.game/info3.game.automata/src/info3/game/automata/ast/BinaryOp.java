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

public class BinaryOp extends Expression {

	public String operator;
	public Expression left_operand, right_operand;

	public BinaryOp(String o, Expression l, Expression r) {
		this.operator = o;
		this.left_operand = l;
		this.right_operand = r;
	}

	public String toString() {
		return left_operand.toString() + " " + operator + " " + right_operand.toString();
	}

	public String name() {
		return "";
	}
	
	Object accept(IVisitor visitor) {
		Object left = left_operand.accept(visitor);
		Object right = right_operand.accept(visitor);
		return visitor.visit(this, left, right);
	}

}
