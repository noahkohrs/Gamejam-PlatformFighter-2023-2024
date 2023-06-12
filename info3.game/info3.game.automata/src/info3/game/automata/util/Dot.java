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
package info3.game.automata.util;

import java.io.PrintStream;

import info3.game.automata.ast.Parameter;

public final class Dot {

  // using String	

  public static String graph(String name, String content) {
			String output = new String();
			output += "digraph " + name + "{" ;
			output += "\n" + "node[shape=box, fontsize=16, color=gray];" ;
			output += "\n" + content ;
			output += "\n}" ; 
			return output ;
		}
		
  public static String subgraph(String name, String content){
			String output = new String();
			output += "\n\nsubgraph cluster_" + name ;
			output += "{\n" ;
			output += content ;
			output += "\n}\n" ;
			return output ;
  }
		
  public static String subgraph(int id, String content){
			return subgraph("" + id, content);
  }
  
  // using PrintStream
  
  public static void start_graph(PrintStream ps, String name) {
		String string = new String();
		string += "digraph " + name + "{" ;
		string += "\n" + "node[shape=box, fontsize=16, color=gray];" ;
		ps.println(string);
  }
  
  public static void start_subgraph(PrintStream ps, String name) {
	  ps.println("\n\nsubgraph cluster_" + name + "{") ;
  }
  
  public static void end_graph(PrintStream ps, String name) {
	  ps.println("\n} // end of " + name) ;
  }
  
  public static String asString(String string) {
	  return "\\\"" + string + "\\\"" ;
  }
  
  public static String quote(String string) {
	  return "\"" + string + "\"" ;
  }

  
  		private static String quote(int i) {
			return quote(""+ i) ;
		}
		private static String node_label(String label, String options) {
			return " [label=" + quote(label) + ", " + options + "]" ;
		}
		
		public static String declare_node(Integer id, String label, String options){
			return "\n" + quote(id) + node_label(label, options) + ";" ;
		}
		public static String declare_node(String id, String label, String options){
			return "\n" + quote(id) + node_label(label, options) + ";" ;
		}
		public static String edge(String id1, String id2) {
			return "\n" + quote(id1) + " -> " + quote(id2) + ";" ;
		}
		public static String edge(Integer id1, Integer id2) {
			return "\n" + quote(id1) + " -> " + quote(id2) + ";" ;
		}
		public static String edge(String id1, Integer id2) {
			return "\n" + quote(id1) + " -> " + quote(id2) + ";" ;
		}
		public static String edge(Integer id1, String id2) {
			return "\n" + quote(id1) + " -> " + quote(id2) + ";" ;
		}
		
    public static String edge(String id1, String id2, String options) {
      return "\n" + quote(id1) + " -> " + quote(id2) + "["+options+"];" ;
    }
    public static String edge(Integer id1, Integer id2, String options) {
      return "\n" + quote(id1) + " -> " + quote(id2) + "["+options+"];" ;
    }
    public static String edge(String id1, Integer id2, String options) {
      return "\n" + quote(id1) + " -> " + quote(id2) + "["+options+"];" ;
    }
    public static String edge(Integer id1, String id2, String options) {
      return "\n" + quote(id1) + " -> " + quote(id2) + "["+options+"];" ;
    }

}
