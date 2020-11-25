package services;

import java.util.Map;
import java.util.Stack;

import entities.Info;
import entities.Lexical;
import entities.NonTerminal;

public class SyntaxAnalysis {
	Map<String, Integer> lexTokens = Lexical.getTokens(); //terminais
	Map<String, Integer> synTokens = NonTerminal.getTokens(); // não terminais
	
	public Stack<String> name(Stack<String> tokens) {
		/* tokens = palavras validadas do léxico */
		/*   String aux = tokens.pop().toString(); pega elemento do topo da pilha.
		 * 	String split[]	= aux.splot("_"); 
		 *	split[0] = palavra
		 *  split[1] = key
		 *  split[2] = linha
		 *  
		 *   ele só vai entrar nessa classe quando estiver tudo okay, então não precisa lidar
		 *   com erros.
		 *   Ps: veja a classe de não terminais. eles são as derivação de terminais (léxico)
		 *  logo um terminal -> não terminal
		 **/
		
		/* retorno quando estiver feito tudo precisa mudar o nome*/
		System.out.println("Dentro do syntatico: ");
		
		while(!tokens.empty()) {
			System.out.println("Elemento dentro: " + tokens.pop());
		}
		
		return tokens;
		
	}
}
