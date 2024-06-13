package proleg.puntos;

import java.util.ArrayList;

/**
 * Clase que define los estados del AFD
 * 
 * @author Diego Francisco Darias Pino
 */
public class Estado {

    public static Estado fin = new Estado("final");
    public static String[] Nterminal = {"(", ")*", ")+", ")?", "|", "lambda"};
    public static int NE = 0;
    public String nombre;
    public ArrayList<Expresion> elem;

    public Estado() {
        nombre = "s" + NE;
        NE++;
        elem = new ArrayList<>();
    }

    public Estado(String s) {
        nombre = s;
        elem = new ArrayList<>();
    }

    @Override
    public String toString() {
        String output = nombre;
        for (int i = 0; i < elem.size(); i++) {
            output = output + "\n" + elem.get(i);
        }
        return output;
    }

}
