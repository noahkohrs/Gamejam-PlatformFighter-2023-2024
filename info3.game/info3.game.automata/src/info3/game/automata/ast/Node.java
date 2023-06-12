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

public abstract class Node {

  static int idGenerator = 10;

	public int id ;          // a unique id used as a graph node for pretty printing the AST in dot format

	public Node() {
		this.id = idGenerator++;
	}

	/** La terminologie Wikipédia distingue les noms de méthodes 
	 * - le visitor visit(node) 
	 * - le noeud accept(visitor)
	 */
	
	abstract Object accept(IVisitor visitor); 

}
