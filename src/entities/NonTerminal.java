package entities;

import java.util.HashMap;
import java.util.Map;

public class NonTerminal {

    private static Map<String,Integer> tokens = new HashMap<String,Integer>(){{
        put("PROGRAMA", 52);
        put("BLOCO",53);
        put("DCLROT",54);
        put("LID",55);
        put("REPIDENT",56);
        put("DCLCONST",57);
        put("LDCONST",58);
        put("DCLVAR",59);
        put("LDVAR",60);
        put("TIPO", 61);
        put("DCLPROC",62);
        put("DEFPAR",63);
        put("CORPO",64);
        put("REPCOMANDO",65);
        put("COMANDO",66);
        put("RCOMID",67);
        put("RVAR", 68);
        put("PARAMETROS",69);
        put("REPPAR",70);
        put("ELSEPARTE",71);
        put("VARIAVEL",72);
        put("VARIAVEL1",73);
        put("REPVARIAVEL",74);
        put("ITEMSAIDA",75);
        put("REPITEM",76);
        put("EXPRESSAO",77);
        put("REPEXPSIMP",78);
        put("EXPSIMP",79);
        put("REPEXP",80);
        put("TERMO",81);
        put("REPTERMO",82);
        put("FATOR",83);
        put("CONDCASE",84);
        put("CONTCASE",85);
        put("RPINTEIRO",86);
        put("SEM EFEITO",87);
        }
    };

    public static Map<String, Integer> getTokens() {
        return tokens;
    }

    public static void setTokens(Map<String, Integer> tokens) {
        NonTerminal.tokens = tokens;
    }


}