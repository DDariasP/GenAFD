package proleg.puntos;

import proleg.ast.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import proleg.lexico.*;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Expresion {

    public static String[] nulos = {"[", "]", ","};
    public static int NUM = 0;
    public String id;
    public ArrayList<INodo> listaOri;
    public String[] tokens;
    public ArrayList<Tupla> vector;
    public int punto;

    public Expresion(AST ast) {
        id = "x" + NUM;
        NUM++;
        listaOri = ast.arbol.getListaH();
        tokens = getTokens();
        vector = getVector(tokens);
        punto = 0;
    }

    public Expresion(Expresion exp) {
        id = "x" + NUM;
        NUM++;
        listaOri = exp.listaOri;
        tokens = new String[exp.tokens.length];
        for (int i = 0; i < exp.tokens.length; i++) {
            tokens[i] = exp.tokens[i];
        }
        tokens[exp.punto] = tokens[exp.punto + 1];
        tokens[exp.punto + 1] = ".";
        vector = getVector(tokens);
        punto = exp.punto + 1;
    }

    public final String[] getTokens() {
        String nodos = listaOri.toString();
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

    public final ArrayList<Tupla> getVector(String[] tokens) {
        ArrayList<Tupla> sym = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++) {
            String tk = tokens[i];
            Tupla tp = new Tupla(tk, i);
            sym.add(tp);
        }
        Tupla[] array = sym.toArray(Tupla[]::new);
        Tupla.asociarPar(array);
        return sym;
    }

    public static void transiciones(Expresion exp, ArrayList<Expresion> listaCanon) {
        if (!listaCanon.contains(exp)) {
            listaCanon.add(exp);
            System.out.println("");
            System.out.println(exp);
            System.out.println("");
            if (exp.punto < exp.vector.size()-1) {
                Expresion sig = new Expresion(exp);
                transiciones(sig, listaCanon);
            }
        }

    }

    public static void clausura(Expresion exp0, Tupla sym, ArrayList<Expresion> listaCanon) {
        switch (sym.symID) {
            case MyConstants.STAR:

                break;
            case MyConstants.PLUS:
                break;
            case MyConstants.HOOK:
                break;
            default:
                throw new AssertionError();

        }

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
        if (!listaOri.equals(obj.listaOri)) {
            iguales = false;
        }
        if (punto != obj.punto) {
            iguales = false;
        }
        return iguales;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.listaOri);
        hash = 13 * hash + this.punto;
        return hash;
    }

    @Override
    public String toString() {
        String output = " " + id + ": ";
        for (int i = 0; i < tokens.length; i++) {
            output = output + tokens[i] + " ";
        }
        return output;
    }

}
