package proleg.puntos;

import proleg.ast.*;
import proleg.lexico.*;
import java.util.Arrays;

/**
 * Clase para la informacion de cada token
 *
 * @author Diego Francisco Darias Pino
 */
public class Tupla {

    public static Tupla EOF = new Tupla("eof", -1);
    public static Tupla LAMBDA = new Tupla("lambda", -1);
    public static String[] listaL = {"*(", "+(", "?(", "|("};
    public static String[] listaR = {")*", ")+", ")?", ")|"};
    public final String sym;
    public final int symID;
    public final boolean terminal;
    public final int pos;
    public Tupla par;
    public boolean paired;

    public Tupla(String s, int n) {
        sym = s;
        switch (sym) {
            case "*(":
            case ")*":
                symID = MyConstants.STAR;
                break;
            case "+(":
            case ")+":
                symID = MyConstants.PLUS;
                break;
            case "?(":
            case ")?":
                symID = MyConstants.HOOK;
                break;
            case "|(":
            case ")|":
            case "|":
                symID = MyConstants.OR;
                break;
            default:
                symID = MyConstants.SYMBOL;
        }
        terminal = !Arrays.asList(Operador.lista).contains(sym);
        pos = n;
        par = null;
        paired = false;
    }

    public static void asociarPares(Tupla[] array) {
        Tupla sig = array[0];
        int puntero = 0;
        Tupla lastL = null;
        Tupla firstR = null;
        avanza(array, puntero, lastL, firstR, sig);
    }

    private static void avanza(Tupla[] array, int puntero,
            Tupla lastL, Tupla firstR, Tupla sig) {
        if (sig != EOF) {
            if (Arrays.asList(listaL).contains(sig.sym)) {
                guardaL(array, puntero, lastL, firstR, sig);
            } else if (Arrays.asList(listaR).contains(sig.sym)) {
                paraR(array, puntero, lastL, firstR, sig);
            } else {
                puntero++;
                if (puntero < array.length) {
                    sig = array[puntero];
                } else {
                    sig = EOF;
                }
                avanza(array, puntero, lastL, firstR, sig);
            }
        }
    }

    private static void guardaL(Tupla[] array, int puntero,
            Tupla lastL, Tupla firstR, Tupla sig) {
        lastL = array[puntero];
        puntero++;
        if (puntero < array.length) {
            sig = array[puntero];
        } else {
            sig = EOF;
        }
        avanza(array, puntero, lastL, firstR, sig);
    }

    private static void paraR(Tupla[] array, int puntero,
            Tupla lastL, Tupla firstR, Tupla sig) {
        firstR = sig;
        retrocede(array, puntero, lastL, firstR, sig);
        puntero++;
        if (puntero < array.length) {
            sig = array[puntero];
        } else {
            sig = EOF;
        }
        avanza(array, puntero, lastL, firstR, sig);
    }

    private static void retrocede(Tupla[] array, int puntero,
            Tupla lastL, Tupla firstR, Tupla ante) {
        puntero--;
        ante = array[puntero];
        boolean encontrado = false;
        while (!encontrado) {
            if (!Arrays.asList(listaL).contains(ante.sym)) {
                puntero--;
                ante = array[puntero];
            } else {
                if (ante.sym.charAt(0) == firstR.sym.charAt(1)
                        && !ante.paired) {
                    encontrado = true;
                } else {
                    puntero--;
                    ante = array[puntero];
                }

            }
        }
        firstR.par = ante;
        firstR.paired = true;
        ante.par = firstR;
        ante.paired = true;
    }

    public static void asociarOR(Tupla[] array) {
 
        
        
        
        
    }

    @Override
    public String toString() {
        String output = "< " + sym + " / " + pos;
        if (par != null) {
            output = output + " / <" + par.sym + "/" + par.pos + ">";
        }
        output = output + " >";
        return output;
    }

}
