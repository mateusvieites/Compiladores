package services;

import java.util.Iterator;
import java.util.Map;

import entities.Lexical;

public class LexicalAnalyser {
	Map<String, Integer> tokens = Lexical.getTokens();

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
		String toBeAnalysed = "";
		char c[] = text.toCharArray();
		for (char aux : c) {

			System.out.println("Letra atual: " + aux);

			if (isDigitOrIsLetter(aux)) {
				if (!isDigitOrIsLetter(toBeAnalysed.charAt(0))) {
					// #ToDo analisar
					toBeAnalysed = "";
				}
				System.out.println("Aux é letra: " + aux);
				toBeAnalysed += aux;
			} else {
				if (!(toBeAnalysed.equals(""))) {
					System.out.println("Aux diferente: " + aux);
					// #ToDo analisar
					toBeAnalysed = "";
					if (aux != ' ') {
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

	}
}
