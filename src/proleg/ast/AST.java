package proleg.ast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import proleg.lexico.*;

/**
 *
 * @author diego
 */
public class AST {

    private final Fichero arbol;

    public AST(Fichero a) {
        arbol = a;
    }

    public void print() {
        try {
            String filename = arbol.getNombre() + "txt";
            File ast = new File(filename);
            if (ast.exists()) {
                ast.delete();
                System.out.println("\nArchivo " + ast.getName() + " sobreescrito.\n");
            } else {
                System.out.println("\nArchivo " + ast.getName() + " creado.\n");
            }
            ast.createNewFile();
            FileWriter writer = new FileWriter(filename);

            writer.write(arbol.getNombre());
            for (int numh = 1; numh < arbol.getNumHijos(); numh++) {
                writer.write(pintarHijo(arbol, numh, 0));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error en File.");
        }
    }

    private static String pintarHijo(INodo a, int numh, int nivel) {
        String tab = "\n ";
        for (int i = 0; i < nivel; i++) {
            tab = tab + "   ";
        }
        String output = tab + "|--- ";
        INodo hijo = a.getHijoN(numh);
        switch (hijo.getID()) {
            case MyConstants.SYMBOL:
                output = pintarBase((Base) a);
                break;
            case MyConstants.OR:
            case MyConstants.HOOK:
                output = pintarOpcion(((Opcion) a), nivel + 1);
                break;
            case MyConstants.STAR:
            case MyConstants.PLUS:
                output = pintarRepeticion(((Repeticion) a), nivel + 1);
                break;
            case MyConstants.RPAREN:
                output = pintarSecuencia(((Secuencia) a), nivel + 1);
                break;
            default:
                throw new AssertionError();
        }
        return output;
    }

    private static String pintarBase(Base h) {
        String output = "Base: " + h.getSimbolo();
        return output;
    }

    private static String pintarOpcion(Opcion h, int nivel) {
        String output = "Opcion: " + h.getTipo();
        for (int numh = 1; numh < h.getNumHijos(); numh++) {
            output = output + pintarHijo(((INodo) h), numh, nivel + 1);
        }
        return output;
    }

    private static String pintarRepeticion(Repeticion h, int nivel) {
        String output = "Repeticion: " + h.getTipo();
        for (int numh = 1; numh < h.getNumHijos(); numh++) {
            output = output + pintarHijo(((INodo) h), numh, nivel + 1);
        }
        return output;
    }

    private static String pintarSecuencia(Secuencia h, int nivel) {
        String output = "Secuencia";
        for (int numh = 1; numh < h.getNumHijos(); numh++) {
            output = output + pintarHijo(((INodo) h), numh, nivel + 1);
        }
        return output;
    }

}
