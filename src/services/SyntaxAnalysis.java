package services;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.JOptionPane;

import entities.Info;
import entities.Lexical;
import entities.NonTerminal;

public class SyntaxAnalysis {
	Map<String, Integer> lexTokens = Lexical.getTokens(); //terminais
	Map<String, Integer> synTokens = NonTerminal.getTokens(); // não terminais
	Map<String, String> parsing = new HashMap<String, String>(){{
		put("52,1", "PROGRAM|IDENTIFICADOR|;|BLOCO|.");	put("53,2", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO"); put("53,3", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
		put("53,4", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO"); put("53,5", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO"); put("53,6", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
		put("54,2", "LABEL|LID|;");	put("54,3", "NULL"); put("54,4", "NULL"); put("54,5", "NULL"); put("54,6", "NULL");	put("55,25", "IDENTIFICADOR|REPIDENT");
		put("56,39", "NULL"); put("56,46", ",|IDENTIFICADOR|REPIDENT"); put("56,47", "NULL"); put("57,3", "CONST|IDENTIFICADOR|=|INTEIRO|;|LDCONST");
		put("57,4", "NULL"); put("57,5", "NULL"); put("57,6", "NULL"); put("58,4", "NULL"); put("58,5", "NULL"); put("58,6", "NULL");
		put("58,25", "IDENTIFICADOR|=|INTEIRO|;|LDCONST"); put("59,4", "VAR|LID|:|TIPO|;|LDVAR"); put("59,5", "NULL"); put("59,6", "NULL"); put("60,5", "NULL");
		put("60,6", "NULL"); put("60,25", "LID|:|TIPO|;|LDVAR"); put("61,8", "INTEGER"); put("61,9", "ARRAY|[|INTEIRO|..|INTEIRO|]|OF|INTEGER");
		put("62,5", "PROCEDURE|IDENTIFICADOR|DEFPAR|;|BLOCO|;|DCLPROC"); put("62,6", "NULL"); put("63,36", "(|LID|:|INTEGER|)"); put("63,39", "NULL");
		put("64,6", "BEGIN|COMANDO|REPCOMANDO|END"); put("65,7", "NULL"); put("65,47", ";|COMANDO|REPCOMANDO"); put("66,6", "CORPO"); put("66,7", "NULL");
		put("66,11", "CALL|IDENTIFICADOR|PARAMETROS"); put("66,12", "GOTO|IDENTIFICADOR"); put("66,13", "IF|EXPRESSAO|THEN|COMANDO|ELSEPARTE"); put("66,15", "NULL");
		put("66,16", "WHILE|EXPRESSAO|DO|COMANDO"); put("66,18", "REPEAT|COMANDO|UNTIL|EXPRESSAO"); put("66,19", "NULL"); put("66,20", "READLN|(|VARIAVEL|REPVARIAVEL|)");
		put("66,21", "WRITELN|(|ITEMSAIDA|REPITEM|)"); put("66,25", "IDENTIFICADOR|RCOMID"); put("66,27", "FOR|IDENTIFICADOR|:=|EXPRESSAO|TO|EXPRESSAO|DO|COMANDO");
		put("66,29", "CASE|EXPRESSAO|OF|CONDCASE|END"); put("66,47", "NULL"); put("67,34", "RVAR|:=|EXPRESSAO"); put("67,38", "RVAR|:=|EXPRESSAO"); put("67,39", ":|COMANDO");
		put("68,34", "[|EXPRESSAO|]"); put("68,38", "NULL"); put("69,7", "NULL");put("69,15", "NULL"); put("69,19", "NULL"); put("69,36", "(|EXPRESSAO|REPPAR|)");
		put("69,47", "NULL"); put("70,37", "NULL"); put("70,46", ",|EXPRESSAO|REPPAR"); put("71,7", "NULL"); put("71,15", "ELSE|COMANDO");put("71,19", "NULL");
		put("71,47", "NULL"); put("72,25", "IDENTIFICADOR|VARIAVEL1"); put("73,7", "NULL"); put("73,10", "NULL"); put("73,14", "NULL"); put("73,15", "NULL");
		put("73,17", "NULL"); put("73,19", "NULL"); put("73,22", "NULL"); put("73,23", "NULL"); put("73,28", "NULL"); put("73,30", "NULL"); put("73,31", "NULL");
		put("73,32", "NULL"); put("73,33", "NULL"); put("73,34", "[|EXPRESSAO|]"); put("73,35", "NULL"); put("73,37", "NULL"); put("73,40", "NULL"); put("73,41", "NULL");
		put("73,42", "NULL"); put("73,43", "NULL"); put("73,44", "NULL"); put("73,45", "NULL"); put("73,46", "NULL"); put("73,47", "NULL"); put("74,37", "NULL");
		put("74,46", ",|VARIAVEL|REPVARIAVEL"); put("75,24", "EXPRESSAO"); put("75,25", "EXPRESSAO"); put("75,26", "EXPRESSAO"); put("75,30", "EXPRESSAO");
		put("75,31", "EXPRESSAO"); put("75,36", "EXPRESSAO"); put("75,48", "LITERAL"); put("76,37", "NULL"); put("76,46", ",|ITEMSAIDA|REPITEM");
		put("77,24", "EXPSIMP|REPEXPSIMP"); put("77,25", "EXPSIMP|REPEXPSIMP"); put("77,26", "EXPSIMP|REPEXPSIMP"); put("77,30", "EXPSIMP|REPEXPSIMP");
		put("77,31", "EXPSIMP|REPEXPSIMP"); put("77,36", "EXPSIMP|REPEXPSIMP"); put("78,7", "NULL"); put("78,10", "NULL"); put("78,14", "NULL"); put("78,15", "NULL");
		put("78,17", "NULL"); put("78,19", "NULL"); put("78,28", "NULL"); put("78,35", "NULL"); put("78,37", "NULL"); put("78,40", "=|EXPSIMP"); put("78,41", ">|EXPSIMP");
		put("78,42", ">=|EXPSIMP"); put("78,43", "&lt;|EXPSIMP"); put("78,44", "&lt;=|EXPSIMP"); put("78,45", "&lt;&gt;|EXPSIMP"); put("78,46", "NULL"); put("78,47", "NULL");
		put("79,24", "TERMO|REPEXP"); put("79,25", "TERMO|REPEXP"); put("79,26", "TERMO|REPEXP"); put("79,30", "+|TERMO|REPEXP"); put("79,31", "-|TERMO|REPEXP");
		put("79,36", "TERMO|REPEXP"); put("80,7", "NULL"); put("80,10", "NULL"); put("80,14", "NULL"); put("80,15", "NULL"); put("80,17", "NULL"); put("80,19", "NULL");
		put("80,22", "OR|TERMO|REPEXP"); put("80,28", "NULL"); put("80,30", "+|TERMO|REPEXP"); put("80,31", "-|TERMO|REPEXP"); put("80,35", "NULL"); put("80,37", "NULL");
		put("80,40", "NULL"); put("80,41", "NULL"); put("80,42", "NULL"); put("80,43", "NULL"); put("80,44", "NULL"); put("80,45", "NULL"); put("80,46", "NULL");
		put("80,47", "NULL"); put("81,24", "FATOR|REPTERMO"); put("81,25", "FATOR|REPTERMO"); put("81,26", "FATOR|REPTERMO"); put("81,36", "FATOR|REPTERMO");
		put("82,7", "NULL"); put("82,10", "NULL"); put("82,14", "NULL"); put("82,15", "NULL"); put("82,17", "NULL"); put("82,19", "NULL"); put("82,22", "NULL");
		put("82,23", "AND|FATOR|REPTERMO"); put("82,28", "NULL"); put("82,30", "NULL"); put("82,31", "NULL"); put("82,32", "*|FATOR|REPTERMO");
		put("82,33", "/|FATOR|REPTERMO"); put("82,35", "NULL"); put("82,37", "NULL"); put("82,40", "NULL"); put("82,41", "NULL"); put("82,42", "NULL"); put("82,43", "NULL");
		put("82,44", "NULL"); put("82,45", "NULL"); put("82,46", "NULL"); put("82,47", "NULL"); put("83,24", "NOT|FATOR"); put("83,25", "VARIAVEL"); put("83,26", "INTEIRO");
		put("83,36", "(|EXPRESSAO|)"); put("84,26", "INTEIRO|RPINTEIRO|:|COMANDO|CONTCASE"); put("85,7", "NULL"); put("85,47", ";|CONDCASE"); put("86,39", "NULL");
		put("86,46", ",|INTEIRO|RPINTEIRO");
	}
	};
	Stack<String> X = new Stack<>();
	
	public void name(Stack<String> tokens) {
		
		// inicia a pilha X com 52
		X.push("52");
		String aux;
		String split[];
		String a;
		String M;
		String value;
		
		do {
			if (tokens.empty()) {
				// Erro
				JOptionPane.showMessageDialog(null, "Pilha tokens vazia. Pilha X ainda possui itens a serem análisados!");
				return;
			}
			// pega a key da pilha tokens e armazena na variável A
			aux = tokens.peek().toString();
			split = aux.split("_");
			a = split[1];
			
			// verifica se o valor da pilha A é um número
			if (!a.matches("[0-9]*")) {
				// caso não for um número, a recebe o valor correto do split
				a = split[2];
			}
			
			// Se X for um terminal (menor que 52) ou for igual ao operador de final de programa ($ = 51)
			if (Integer.parseInt(X.peek()) < 52 || X.peek().equals("51")) {
				if (X.peek().equals(a)) {
					X.pop();
					tokens.pop();
				} else {
					// Erro
					JOptionPane.showMessageDialog(null, "Erro! Valor de X (" + X.peek() + ") é terminal mas não condiz com a pilha A(" + a + ")!");
					return;
				}
			} else {
				// faz o cruzamento de X e A e atribui o valor à M
				M = X.peek() + "," + a;
				if (parsing.containsKey(M)) {
					X.pop();
					value = parsing.get(M);
					split = value.split("\\|");
					// Adiciona os novos valores da tabela de parsing à pilha X
					for (int i = split.length; i > 0; i--) {
						if (lexTokens.containsKey(split[i-1])) {
							if(lexTokens.get(split[i-1]) != null) {
								X.push(lexTokens.get(split[i-1]).toString());
							}
						} else {
							if(synTokens.get(split[i-1]) != null) {
								X.push(synTokens.get(split[i-1]).toString());
							}
						}
					}
				} else {
					// Erro
					JOptionPane.showMessageDialog(null, "Erro! Valor de M (" + M + ") não consta na tabela de Parsin!");
					return;
				}
			}
		} while(!X.equals(tokens));
		
		JOptionPane.showMessageDialog(null, "saiu do while");
		
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
		
		
	}
}