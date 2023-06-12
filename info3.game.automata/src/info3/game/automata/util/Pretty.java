package info3.game.automata.util;

import java.util.List;
import java.util.ListIterator;

public class Pretty {

	public static String separate_with(List<String> stringlist, String sep) {
		  String string = new String();
		  ListIterator<String> iterator = stringlist.listIterator();
		  while (iterator.hasNext()) {
			  string += iterator.next() ;
			  if (iterator.hasNext()) string += sep;
		  }
		  return string;	
	  }

	public static String funcall(String name, Object[] parameters) {
		String s = name;
		if (parameters.length>0) {
			s += "(";
			s += parameters[0].toString();
			for (int i=1; i<parameters.length; i++) {
				s += "," + parameters[i];
			}
			s += ")";
		}
		return s;
	}
}
