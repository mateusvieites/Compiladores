package services;

import java.util.Map;
import java.util.Stack;

import entities.Lexical;

public class LexicalAnalyser {
	Map<String, Integer> tokens = Lexical.getTokens();
	Stack<String> stack = new Stack<String>();
	Stack<String> word = new Stack<String>();
	boolean comment = false;
	
	public String analyse(String toBeAnalysed,int line) {
		System.out.println("Para ser analisado: " + toBeAnalysed);
		word.push(toBeAnalysed);
		if(Character.isDigit(toBeAnalysed.charAt(0))) {
			try{
	            int i = Integer.parseInt(toBeAnalysed);
	            if(i > -32767 && i < 32767) {
	            	toBeAnalysed = "Inteiro";
	            }else {
	            	 return toBeAnalysed + "_" + "illegal" + "_" + String.valueOf(line);
	            }
	        }catch(Exception e){
	            return toBeAnalysed + "_" + "illegal" + "_" + String.valueOf(line);
	        }
		}
		
		if(toBeAnalysed.length() > 35) {
			return toBeAnalysed + "_" + "illegal" + "_" + String.valueOf(line);
		}
		
		int value;
		String temp = toBeAnalysed.toUpperCase();
		if (tokens.containsKey(temp)) {
			value = tokens.get(temp);
		} else {
			value = 25;
		}

		return  toBeAnalysed + "_" +String.valueOf(value)  + "_" + String.valueOf(line);
	}
	
	private boolean isSpecialCase(char c, char compare) {
		return c == compare ? true : false;
	}

	private boolean isDigitOrIsLetter(char charTest) {
		return Character.isDigit(charTest) || Character.isLetter(charTest);
	}

	public Stack split(String text) {
		int numberOfLines = 1;
		StringBuilder toBeAnalysed = new StringBuilder();
		char c[] = text.toCharArray();
		for (char aux : c) {
			System.out.println(aux);
			
		if(comment) {
			if(isDigitOrIsLetter(aux)) {
				if(toBeAnalysed.length() > 0) {
					toBeAnalysed.setLength(0);
					toBeAnalysed.append(aux);
				}else {
					toBeAnalysed.append(aux);
				}
			}else {
				if(toBeAnalysed.length()>0) {
					if(aux == '>' && isSpecialCase(toBeAnalysed.charAt(0), '*')) {
						
						stack.push(analyse(toBeAnalysed.toString(),numberOfLines));
						toBeAnalysed.setLength(0);
						toBeAnalysed.append(aux);
						comment = false;
					}else {
						toBeAnalysed.setLength(0);
						toBeAnalysed.append(aux);
					}
				}else {
					toBeAnalysed.setLength(0);
					toBeAnalysed.append(aux);
				}
			}
		}else {
			if(isDigitOrIsLetter(aux)) {
				if(!toBeAnalysed.equals("") && toBeAnalysed.length() > 0) {
					if(!isDigitOrIsLetter(toBeAnalysed.charAt(0))) {
						stack.push(analyse(toBeAnalysed.toString(),numberOfLines));
						toBeAnalysed.setLength(0);
					}
				}
				toBeAnalysed.append(aux);
			}else {
				if(aux != ' ' && aux != '\n' && aux != '\r' && aux !='\t') {
					if(!toBeAnalysed.equals("")) {
						if(aux == '_' && isDigitOrIsLetter(toBeAnalysed.charAt(0))) {
							toBeAnalysed.append(aux);
						}else if(aux == '=' && toBeAnalysed.length() > 0){
							if(isSpecialCase(toBeAnalysed.charAt(0),'.')) {
								toBeAnalysed.append(aux);
							}else if(isSpecialCase(toBeAnalysed.charAt(0),':')){
								toBeAnalysed.append(aux);
							}else if(isSpecialCase(toBeAnalysed.charAt(0),'<')){
								toBeAnalysed.append(aux);
							}else if(isSpecialCase(toBeAnalysed.charAt(0),'>')){
								toBeAnalysed.append(aux);
							}else {
								stack.push(analyse(toBeAnalysed.toString(),numberOfLines));
								toBeAnalysed.setLength(0);
								toBeAnalysed.append(aux);
							}
						}else if(aux == '.' && toBeAnalysed.length() > 0 ){
							if(isSpecialCase(toBeAnalysed.charAt(0), '.')) {
								toBeAnalysed.append(aux);
							}else {
								stack.push(analyse(toBeAnalysed.toString(),numberOfLines));
								toBeAnalysed.setLength(0);
								toBeAnalysed.append(aux);
							}
						}else if(aux == '*' && toBeAnalysed.length() > 0) {
							if(isSpecialCase(toBeAnalysed.charAt(0), '<')) {
								comment = true;
								stack.push(analyse(toBeAnalysed.toString(),numberOfLines));
								toBeAnalysed.setLength(0);
								toBeAnalysed.append(aux);
								stack.push(analyse(toBeAnalysed.toString(),numberOfLines));
								
							}else {
								stack.push(analyse(toBeAnalysed.toString(),numberOfLines));
								toBeAnalysed.setLength(0);
								toBeAnalysed.append(aux);
							}
							
						}else {
							if(!toBeAnalysed.equals("") && toBeAnalysed.length() > 0){
								stack.push(analyse(toBeAnalysed.toString(),numberOfLines));
								toBeAnalysed.setLength(0);
								toBeAnalysed.append(aux);
							}else {
								toBeAnalysed.append(aux);
							}
						}
					}
				}else {
					if(aux == '\n') {
						numberOfLines++;
					}
			
						
						if(toBeAnalysed.length() > 0) {
								stack.push(analyse(toBeAnalysed.toString(),numberOfLines));
								toBeAnalysed.setLength(0);
						}
					}
				}
		}
		
		}
		if(toBeAnalysed.length() > 0 && !comment) {
			stack.push(analyse(toBeAnalysed.toString(),numberOfLines));	
		}
		
		Stack aux = new Stack();
        
        while(!stack.isEmpty()){
            	aux.push(stack.pop());
        }
		
		return aux;

	}
}
