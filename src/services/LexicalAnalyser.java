package services;

import java.util.Iterator;
import java.util.Map;

import entities.Lexical;

public class LexicalAnalyser {
	Map<Integer,String> tokens = Lexical.getTokens();
	
	
	public void analyse(String toBeAnalysed) {
		Iterator it = tokens.entrySet().iterator(); 
		while (it.hasNext()) { 
			Map.Entry pairs = (Map.Entry)it.next(); 
			if(pairs.getValue().equals("Label")){ 
				System.out.println(pairs.getKey()); /* pega a chave da palavra X*/
			}else {
				if(Character.isDigit(toBeAnalysed.charAt(0))){ /* começa com número */
				
				}else {
					/* adicionar o elemento como identificador */
				}
					
			}
		}
	}
	
	public void split(String text) {
		String toBeAnalysed = "";
		char c[] = text.toCharArray();
		for(char aux : c) {
			if(Character.getType(aux) == Character.SPACE_SEPARATOR) {
				if(!(toBeAnalysed.equals(""))) {
					analyse(toBeAnalysed);
					toBeAnalysed = "";
				}
			}else {
				toBeAnalysed += aux;
			}
		}
		
	}
}
