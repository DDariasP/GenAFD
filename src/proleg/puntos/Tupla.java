package proleg.puntos;

import java.util.Arrays;
import proleg.ast.*;

/**
 *
 * @author diego
 */
public class Tupla {

    public static int C = 0;
    public static Tupla eof = new Tupla("eof", -1);
    public static String[] listaL = {"*(", "+(", "?("};
    public static String[] listaR = {")*", ")+", ")?"};
    public String sym;
    public boolean terminal;
    public int pos;
    public Tupla par;
    public boolean paired;

    public Tupla(String s, int n) {
        sym = s;
        if (Arrays.asList(Operacion.lista).contains(sym)) {
            terminal = false;
        } else {
            terminal = true;
        }
        pos = n;
        par = null;
        paired = false;
    }

    public static void asociarPar(Tupla[] tokens) {
        Tupla sig = tokens[0];
        int puntero = 0;
        Tupla lastL = null;
        Tupla firstR = null;
        System.out.println(Arrays.toString(tokens));
        avanza(tokens, puntero, lastL, firstR, sig);
    }

    private static void avanza(Tupla[] tokens, int puntero,
            Tupla lastL, Tupla firstR, Tupla sig) {
        System.out.println(C + "-avan");
        System.out.println(sig);
        C++;
        if (sig != eof) {
            if (Arrays.asList(listaL).contains(sig.sym)) {
                guardaL(tokens, puntero, lastL, firstR, sig);
            } else if (Arrays.asList(listaR).contains(sig.sym)) {
                paraR(tokens, puntero, lastL, firstR, sig);
            } else {
                puntero++;
                if (puntero < tokens.length) {
                    sig = tokens[puntero];
                } else {
                    sig = eof;
                }
                avanza(tokens, puntero, lastL, firstR, sig);
            }
        }
    }

    private static void guardaL(Tupla[] tokens, int puntero,
            Tupla lastL, Tupla firstR, Tupla sig) {
        System.out.println(C + "-guar");
        System.out.println(sig);
        C++;
        lastL = tokens[puntero];
        puntero++;
        if (puntero < tokens.length) {
            sig = tokens[puntero];
        } else {
            sig = eof;
        }
        avanza(tokens, puntero, lastL, firstR, sig);
    }

    private static void paraR(Tupla[] tokens, int puntero,
            Tupla lastL, Tupla firstR, Tupla sig) {
        System.out.println(C + "-para");
        System.out.println(sig);
        C++;
        firstR = sig;
        retrocede(tokens, puntero, lastL, firstR, sig);
        puntero++;
        if (puntero < tokens.length) {
            sig = tokens[puntero];
        } else {
            sig = eof;
        }
        avanza(tokens, puntero, lastL, firstR, sig);
    }

    private static void retrocede(Tupla[] tokens, int puntero,
            Tupla lastL, Tupla firstR, Tupla sig) {
        System.out.println(C + "-retr");
        System.out.println(sig);
        C++;
        puntero--;
        sig = tokens[puntero];
        System.out.println(sig);
        boolean encontrado = false;
        while (!encontrado) {
            if (!Arrays.asList(listaL).contains(sig.sym)) {
                System.out.println("no L");
                System.out.println(sig);
                puntero--;
                sig = tokens[puntero];
            } else {
                System.out.println("si L");
                System.out.println(sig);
                System.out.println(firstR);
                if (sig.sym.charAt(0) == firstR.sym.charAt(1)
                        && !sig.paired) {
                    encontrado = true;
                } else {
                    puntero--;
                    sig = tokens[puntero];
                }

            }
        }
        firstR.par = sig;
        firstR.paired = true;
        sig.par = firstR;
        sig.paired = true;
        System.out.println("");
        System.out.println(sig);
        System.out.println(firstR);
        System.out.println("");
    }

    @Override
    public String toString() {
        String output = "< " + sym + "," + pos;
        if (par != null) {
            output = output + ",[" + par.sym + "," + par.pos + "]";
        }
        output = output + " >";
        return output;
    }

}
