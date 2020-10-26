package services;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import entities.Lexical;
import windows.CompilerWindows;

public class LexicalAnalyser {
	Map<String, Integer> tokens = Lexical.getTokens();
	Stack<String> stack = new Stack();;
	
	public String analyse(String toBeAnalysed) {
		System.out.println("Para ser analisado: " + toBeAnalysed);
		
		if(Character.isDigit(toBeAnalysed.charAt(0))) {
			try{
	            Integer.parseInt(toBeAnalysed);
	            toBeAnalysed = "Inteiro";
	        }catch(Exception e){
	            
	        }
		}
		
		int value;
		if (tokens.containsKey(toBeAnalysed)) {
			value = tokens.get(toBeAnalysed);
		} else {
			value = 25;
		}

		return String.valueOf(value);
	}
	
	private boolean isSpecialCase(char c, char compare) {
		return c == compare ? true : false;
	}

	private boolean isDigitOrIsLetter(char charTest) {
		return Character.isDigit(charTest) || Character.isLetter(charTest);
	}

	public String split(String text) {
		String lexicalNumbers = "";
		StringBuilder toBeAnalysed = new StringBuilder();
		char c[] = text.toCharArray();
		for (char aux : c) {
			System.out.println(aux);
			
			if(isDigitOrIsLetter(aux)) {
				if(!toBeAnalysed.equals("") && toBeAnalysed.length() > 0) {
					if(!isDigitOrIsLetter(toBeAnalysed.charAt(0))) {
						stack.push(analyse(toBeAnalysed.toString()));
						toBeAnalysed.setLength(0);
					}
				}
				toBeAnalysed.append(aux);
			}else {
				if(aux != ' ' && aux != '\n' && aux != '\r') {
					if(!toBeAnalysed.equals("")) {
						if(aux == '_' && isDigitOrIsLetter(toBeAnalysed.charAt(0))) {
							toBeAnalysed.append(aux);
						}else if((aux == '.' || aux == '=') && toBeAnalysed.length() > 0){
							
							//#ToDo polish
							if(isSpecialCase(toBeAnalysed.charAt(0),'.')) {
								toBeAnalysed.append(aux);
							}else if(isSpecialCase(toBeAnalysed.charAt(0),':')){
								toBeAnalysed.append(aux);
							}else if(isSpecialCase(toBeAnalysed.charAt(0),'<')){
								toBeAnalysed.append(aux);
							}else if(isSpecialCase(toBeAnalysed.charAt(0),'>')){
								toBeAnalysed.append(aux);
							}else {
								stack.push(analyse(toBeAnalysed.toString()));
								toBeAnalysed.setLength(0);
								toBeAnalysed.append(aux);
							}
						}else {
							if(!toBeAnalysed.equals("") && toBeAnalysed.length() > 0){
								stack.push(analyse(toBeAnalysed.toString()));
								toBeAnalysed.setLength(0);
								toBeAnalysed.append(aux);
							}else {
								toBeAnalysed.append(aux);
							}
						}
					}
				}else {
					if(toBeAnalysed.length() > 0) {
							stack.push(analyse(toBeAnalysed.toString()));
							toBeAnalysed.setLength(0);
					}
				}
			}
		}
		if(toBeAnalysed.length() > 0) {
			stack.push(analyse(toBeAnalysed.toString()));	
		}
		while (!stack.isEmpty()) 
		{ 
		    System.out.println(stack.pop()); 
		}
		String message = "Sucess!";
		return message;

	}
}
