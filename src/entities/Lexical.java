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
	
	private static Map<Integer,String> tokens = new HashMap<Integer,String>(){{
			put(1,"Program"); put(2,"Label"); put(3,"Const"); put(4,"Var");
			put(5,"Procedure"); put(6,"Begin"); put(7,"End"); put(8,"Integer");
			put(9,"Array"); put(10,"Of"); put(11,"Call"); put(12,"Goto");
			put(13,"If"); put(14,"Then"); put(15,"Else"); put(16,"While");
			put(17,"Do"); put(18,"Repeat"); put(19,"Until"); put(20,"Readln");
			put(21,"Writeln"); put(22,"Or"); put(23,"And"); put(24,"Not");
			put(25,"Identificador"); put(26,"Inteiro"); put(27,"For"); put(28,"To");
			put(29,"Case"); put(30,"+"); put(31,"-"); put(32,"*");
			put(33,"/"); put(34,"["); put(35,"]"); put(36,"(");
			put(37,")"); put(38,":="); put(39,":"); put(40,"=");
			put(41,">"); put(42,">="); put(43,"<"); put(44,"<=");
			put(45,"< >"); put(46,","); put(47,";"); put(48,"literal");
			put(49,"."); put(50,".."); put(51,"$");
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

	public static Map<Integer, String> getTokens() {
		return tokens;
	}
	
	
	
	
	
}
