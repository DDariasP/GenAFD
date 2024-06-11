package proleg.arpv;

import proleg.ast.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Expresion {

    public static String[] nulos = {"[", "]", ","};
    public String[] tokens;
    public int punto;
    public Estado destino;

    public Expresion(AST ast) {
        ArrayList<INodo> lista = ast.arbol.getListaH();
        tokens = getTokens(lista);
        punto = 0;
        destino = null;
    }

    public Expresion(Expresion exp) {
        tokens = new String[exp.tokens.length];
        for (int i = 0; i < exp.tokens.length; i++) {
            tokens[i] = exp.tokens[i];
        }
        punto = exp.punto;
        destino = exp.destino;
    }

    public final String[] getTokens(ArrayList<INodo> lista) {
        String nodos = lista.toString();
        nodos = ". ".concat(nodos);
        String[] tk = nodos.split(" ");
        for (int t = 0; t < tk.length; t++) {
            StringBuilder sb = new StringBuilder(tk[t]);
            for (int i = 0; i < sb.length(); i++) {
                String s = String.valueOf(sb.charAt(i));
                if (Arrays.asList(nulos).contains(s)) {
                    sb.deleteCharAt(i);
                }
            }
            tk[t] = sb.toString();
        }
        return tk;
    }

    public int getPunto() {
        int numP = -1;
        int pos = 0;
        boolean encontrado = false;
        while (!encontrado && pos < tokens.length) {
            String s = tokens[pos];
            if (s.equals(".")) {
                encontrado = true;
                numP = pos;
            }
            pos++;
        }
        return numP;
    }

    public static void clausura(Expresion exp, ArrayList<Expresion> lista) {

    }

    public static boolean sonIguales(ArrayList<Expresion> listaS, ArrayList<Expresion> proto) {
        boolean iguales = true;
        if (listaS.isEmpty()) {
            iguales = false;
        } else {
            if (listaS.size() != proto.size()) {
                iguales = false;
            } else {
                int cont = 0;
                for (int i = 0; i < listaS.size(); i++) {
                    for (int j = 0; j < proto.size(); j++) {
                        if (listaS.get(i).equals(proto.get(j))) {
                            cont++;
                        }
                    }
                }
                if (cont != listaS.size()) {
                    iguales = false;
                }
            }
        }
        return iguales;
    }

    public static boolean contiene(ArrayList<Expresion> proto, Expresion exp) {
        boolean encontrado = false;
        int pos = 0;
        while (!encontrado && pos < proto.size()) {
            if (proto.get(pos).equals(exp)) {
                encontrado = true;
            }
            pos++;
        }
        return encontrado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Expresion)) {
            return false;
        }

        Expresion obj = (Expresion) o;
        boolean iguales = true;
        if (punto != obj.punto) {
            iguales = false;
        }
        if (!Arrays.equals(tokens, obj.tokens)) {
            iguales = false;
        }
        return iguales;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Arrays.deepHashCode(this.tokens);
        hash = 67 * hash + this.punto;
        return hash;
    }

    @Override
    public String toString() {
        int numP = -1;
        String output = "[";
        for (int i = 0; i < tokens.length; i++) {
            output = output + " " + tokens[i];
            if (tokens[i].equals(".")) {
                numP = i;
            }
        }
        output = output + " ] => ";
        if (numP < tokens.length - 1) {
            output = output + tokens[numP + 1];
        } else {
            output = output + " => lambda ";
        }
        if (destino != null) {
            output = output + " => " + destino.nombre;
        } else {
            output = output + " => null";
        }
        return output;
    }

}
