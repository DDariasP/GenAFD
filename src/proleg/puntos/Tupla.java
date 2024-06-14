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
    public final boolean terminal;
    public final int pos;
    public Tupla par1, par2, parOR;
    public boolean paired;

    public Tupla(String s, int n) {
        sym = s;
        terminal = !Arrays.asList(Operador.lista).contains(sym);
        pos = n;
        par1 = null;
        par2 = null;
        parOR = null;
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
        firstR.par1 = ante;
        firstR.paired = true;
        ante.par1 = firstR;
        ante.paired = true;
    }

    public static void asociarOR(Tupla[] array) {
        for (int i = 0; i < array.length; i++) {
            Tupla buscador = array[i];
            if (buscador.sym.equals("|(")) {
                boolean encontrado = false;
                int pos = i + 1;
                int contL = 0;
                int contR = 0;
                while (!encontrado && pos < array.length) {
                    Tupla sig = array[pos];
                    switch (sig.sym) {
                        case "|(":
                            contL++;
                            break;
                        case ")|":
                            contR++;
                            break;
                        case "|":
                            if (contL == contR) {
                                encontrado = true;
                                buscador.parOR = sig;
                                buscador.par1.parOR = sig;
                                sig.par1 = buscador;
                                sig.par2 = buscador.par1;
                                sig.paired = true;
                                break;
                            }
                        default:
                    }
                    pos++;
                }
            }
        }
    }

    @Override
    public String toString() {
        String output = "['" + sym + "'," + pos + "]";
        if (par1 != null) {
            output = output + " ['" + par1.sym + "'," + par1.pos + "]";
        }
        if (par2 != null) {
            output = output + " ['" + par2.sym + "'," + par2.pos + "]";
        }
        if (parOR != null) {
            output = output + " ['" + parOR.sym + "'," + parOR.pos + "]";
        }
        return output;
    }

}
