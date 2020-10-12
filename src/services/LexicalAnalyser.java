package services;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.Map;

import entities.Lexical;
import windows.CompilerWindows;

public class LexicalAnalyser {
	Map<String, Integer> tokens = Lexical.getTokens();
	String separator = "//";

	public int analyse(String toBeAnalysed) {
		int value;

		if (tokens.containsKey(toBeAnalysed)) {
			value = tokens.get(toBeAnalysed);
		} else {
			value = 25;
		}

		return value;
	}

	private static boolean isDigitOrIsLetter(char charTest) {
		return Character.isDigit(charTest) || Character.isLetter(charTest);
	}

	public void split(String text) {
		int numberOfLines = 0;
		String toBeAnalysed = "";
		String lexicalNumbers = "";
		char c[] = text.toCharArray();
		for (char aux : c) {

			System.out.println("Letra atual: " + aux);

			if (isDigitOrIsLetter(aux)) {
				if (!isDigitOrIsLetter(toBeAnalysed.charAt(0))) {
					lexicalNumbers += analyse(toBeAnalysed) + separator;
					toBeAnalysed = "";
				}
				System.out.println("Aux é letra: " + aux);
				toBeAnalysed += aux;
			} else {
				if(aux == '\n') {
					numberOfLines++;
				}
				
				if (!(toBeAnalysed.equals(""))) {
					System.out.println("Aux diferente: " + aux);
					lexicalNumbers += analyse(toBeAnalysed) + separator;
					toBeAnalysed = "";
					if (aux != ' ' && aux != '\n'  && aux != '\r') {
						System.out.println("Aqui o:" + aux);
						toBeAnalysed += aux;
					}
				} else {
					if (Character.getType(aux) != 12) {
						toBeAnalysed += aux;
					}
				}

			}

		}
		lexicalNumbers += analyse(toBeAnalysed);

	}
}
