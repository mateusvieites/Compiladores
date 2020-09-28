package services;

import java.util.Map;

import entities.Lexical;

public class LexicalAnalyser {
	Map<Integer,String> tokens = Lexical.getTokens();
	
	public void split(String text) {
		String k[] = text.trim().split(" ");
		
		/* Caso o professor aceite regex no split
		  String m[] = j.trim().split("[_] *| +"); for(int i = 0;i < m.length;i++) {
		  System.out.println(i + " " + m[i]); }
		 */

		
		/*Caso professor não aceite
		 for(int i = 0;i < k.length;i++) {
			if(!(k[i].equals(" ")) && !(k[i].equals("\n")) && !(k[i].equals(null)) && !(k[i].equals(""))) {
				System.out.println("Aqui");
				System.out.println(k[i]);
			}
		}*/
		
	}
}
