package entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Lexical {
	public static void main(String[] args) {
		Iterator it = tokens.entrySet().iterator(); 
		while (it.hasNext()) { 
			Map.Entry pairs = (Map.Entry)it.next(); 
			if(pairs.getValue().equals("Label")){ 
				System.out.println(pairs.getKey()); 
			} 
		}
	}
	
	private static Map<String,Integer> tokens = new HashMap<String,Integer>(){{
        put("Program", 1); put("Label", 2); put("Const", 3); put("Var", 4);
        put("Procedure", 5); put("Begin", 6); put("End", 7); put("Integer", 8);
        put("Array", 9); put("Of", 10); put("Call", 11); put("Goto", 12);
        put("If", 13); put("Then", 14); put("Else", 15); put("While", 16);
        put("Do", 17); put("Repeat", 18); put("Until", 19); put("Readln", 20);
        put("Writeln", 21); put("Or", 22); put("And", 23); put("Not", 24);
        put("Identificador", 25); put("Inteiro", 26); put("For", 27); put("To", 28);
        put("Case", 29); put("+", 30); put("-", 31); put("*", 32);
        put("/", 33); put("[", 34); put("]", 35); put("(", 36);
        put(")", 37); put(":=", 38); put(":", 39); put("=", 40);
        put(">", 41); put(">=", 42); put("<", 43); put("<=", 44);
        put("< >", 45); put(",", 46); put(";", 47); put("literal", 48);
        put(".", 49); put("..", 50); put("$", 51);
    }
};
	
	private Lexical() {
	}

	public static synchronized Lexical getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new Lexical();

		return uniqueInstance;
	}

	private static Lexical uniqueInstance;

	public static Map<String, Integer> getTokens() {
		return tokens;
	}
	
	
	
	
	
}