package proleg.puntos;

import proleg.ast.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Clase que define las expresiones a analizar
 *
 * @author Diego Francisco Darias Pino
 */
public class Expresion {

    public static String[] listaNulos = {"[", "]", ","};
    public int posP;
    public ArrayList<INodo> listaOri;
    public ArrayList<Tupla> array;

    public Expresion(AST ast) {
        posP = 0;
        listaOri = ast.arbol.getListaH();
        array = getVector(getTokens());
    }

    public Expresion(Expresion exp) {
        posP = exp.posP + 1;
        listaOri = exp.listaOri;
        array = exp.array;
    }

    private String[] getTokens() {
        String nodos = listaOri.toString();
        String[] tk = nodos.split(" ");
        for (int t = 0; t < tk.length; t++) {
            StringBuilder sb = new StringBuilder(tk[t]);
            for (int i = 0; i < sb.length(); i++) {
                String s = String.valueOf(sb.charAt(i));
                if (Arrays.asList(listaNulos).contains(s)) {
                    sb.deleteCharAt(i);
                }
            }
            tk[t] = sb.toString();
        }
        return tk;
    }

    private ArrayList<Tupla> getVector(String[] tokens) {
        ArrayList<Tupla> sym = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++) {
            String tk = tokens[i];
            Tupla tp = new Tupla(tk, i);
            sym.add(tp);
        }
        Tupla[] vector = sym.toArray(Tupla[]::new);
        Tupla.asociarPares(vector);
        Tupla.asociarOR(vector);
        return sym;
    }

    public static void clausuraLambda(Expresion exp, ArrayList<Expresion> listaCanon) {
        ArrayList<Tupla> v = exp.array;
        int p = exp.posP;

        if (p < v.size()) {
            if (!v.get(p).terminal) {

                if (p < v.size() - 1) {
                    Expresion sig = new Expresion(exp);
                    clausuraLambda(sig, listaCanon);
                }
            } else {
                listaCanon.add(exp);

            }
        } else {

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
                if (cont == 0 || cont != listaS.size()) {
                    iguales = false;
                }
            }
        }
        return iguales;
    }

    public static void vertir(ArrayList<Expresion> listaCanon, ArrayList<Expresion> proto) {
        for (int i = 0; i < proto.size(); i++) {
            Expresion exp = proto.get(i);
            if (!contiene(listaCanon, exp)) {
                listaCanon.add(exp);
            }
        }
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

        if (posP != obj.posP) {
            return false;
        }

        return listaOri.equals(obj.listaOri);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.posP;
        hash = 29 * hash + Objects.hashCode(this.listaOri);
        return hash;
    }

    @Override
    public String toString() {
        String output = " ";
        for (int i = 0; i < array.size(); i++) {
            Tupla tp = array.get(i);
            if (i == posP) {
                output = output + ". ";
            }
            output = output + tp.sym + " ";
        }
        return output;
    }

}
