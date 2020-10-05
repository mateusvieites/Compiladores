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
				System.out.println(pairs.getKey()); /* pegar a chave da palavra X*/
			}else {
				if(Character.isDigit(toBeAnalysed.charAt(0))){ /* começa com número */
				
				}else {
					/* adicionar o elemento como identificador */
				}
					
			}
		}
	}
	
	private static boolean isDigitOrIsLetter(char charTest) {
		return Character.isDigit(charTest) || Character.isLetter(charTest);
	}
	
	public void split(String text) {
		String toBeAnalysed = "";
		char c[] = text.toCharArray();
		for(char aux : c) {
			
			System.out.println("Letra atual: " + aux);
			
			if(isDigitOrIsLetter(aux)) {
				System.out.println("Aux é letra: " + aux);
				toBeAnalysed += aux;
			}else {
				if(!(toBeAnalysed.equals(""))) {
					System.out.println("Aux diferente: " + aux);
					//#ToDo analisar
					toBeAnalysed = "";
					if(aux != ' ') {
						System.out.println("Aqui o:" + aux);
						toBeAnalysed += aux;
					}
				}else {
					if(Character.getType(aux) != 12) {
						toBeAnalysed += aux;
					}
				}
					
			}
			
		}
		
	}
}
