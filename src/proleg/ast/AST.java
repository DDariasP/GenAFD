package proleg.ast;

import proleg.lexico.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class AST {

    public INodo arbol;

    public AST() {
        arbol = new Operacion();
    }

    public void print() {
        try {
            String filename = arbol.getNombre() + ".txt";
            File file = new File(filename);
            if (file.exists()) {
                file.delete();
                System.out.println("\nArchivo " + file.getName() + " sobreescrito.");
            } else {
                System.out.println("\nArchivo " + file.getName() + " creado.");
            }
            file.createNewFile();
            FileWriter writer = new FileWriter(filename);

            writer.write(arbol.getNombre());
            for (int numh = 0; numh < arbol.getNumHijos(); numh++) {
                writer.write(pintarHijo(arbol, numh, 0));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error en File.");
        }
    }

    private static String pintarHijo(INodo a, int numh, int nivel) {
        String tab = "\n";
        for (int i = 0; i < nivel; i++) {
            if (i % 2 == 0) {
                tab = tab + "|   ";
            } else {
                tab = tab + "    ";
            }
        }
        String output = tab + "|---";

        INodo hijo = a.getHijoN(numh);
        switch (hijo.getID()) {
            case MyConstants.SYMBOL:
                output = output + pintarBase(hijo);
                break;
            case MyConstants.OR:
            case MyConstants.STAR:
            case MyConstants.PLUS:
            case MyConstants.HOOK:
                output = output + pintarOperacion(hijo, nivel + 1);
                break;
            default:
                throw new AssertionError();
        }
        return output;
    }

    private static String pintarBase(INodo h) {
        String output = "Base: " + h.getNombre();
        return output;
    }

    private static String pintarOperacion(INodo h, int nivel) {
        String output = "Operacion: " + h.getNombre();
        for (int numh = 0; numh < h.getNumHijos(); numh++) {
            output = output + pintarHijo(h, numh, nivel + 1);
        }
        return output;
    }

}
