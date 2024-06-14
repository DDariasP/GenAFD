package proleg.puntos;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Reglas {

    public static void R1(Expresion exp, ArrayList<Expresion> listaCanon) {
        Expresion nuevaE = new Expresion(exp);
        nuevaE.posP++;
        Expresion.clausuraLambda(nuevaE, listaCanon);
    }

    public static void R2(Expresion exp, ArrayList<Expresion> listaCanon) {
        Expresion nuevaE = new Expresion(exp);
        ArrayList<Tupla> v = nuevaE.array;
        int p = nuevaE.posP;
        Tupla par = v.get(p).par1;
        nuevaE.posP = par.pos + 1;
        Expresion.clausuraLambda(nuevaE, listaCanon);
    }

    public static void R3(Expresion exp, ArrayList<Expresion> listaCanon) {

    }

    public static void R4(Expresion exp, ArrayList<Expresion> listaCanon) {

    }

    public static void R5(Expresion exp, ArrayList<Expresion> listaCanon) {

    }

}
