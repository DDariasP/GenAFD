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
        arbol = new Fichero();
    }

    public void print() {
        Fichero ast = (Fichero) arbol;
        try {
            String filename = ast.getNombre() + ".txt";
            File file = new File(filename);
            if (file.exists()) {
                file.delete();
                System.out.println("\nArchivo " + file.getName() + " sobreescrito.\n");
            } else {
                System.out.println("\nArchivo " + file.getName() + " creado.\n");
            }
            file.createNewFile();
            FileWriter writer = new FileWriter(filename);

            writer.write(ast.getNombre());
            for (int numh = 0; numh < ast.getNumHijos(); numh++) {
                writer.write(pintarHijo(ast, numh, 0));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error en File.");
        }
    }

    private static String pintarHijo(INodo a, int numh, int nivel) {
        String tab = "\n|";
        for (int i = 0; i < nivel; i++) {
            tab = tab + "   ";
        }
        String output = tab + "|--- ";

        INodo hijo = a.getHijoN(numh);
        switch (hijo.getID()) {
            case MyConstants.SYMBOL:
                output = output + pintarBase((Base) hijo);
                break;
            case MyConstants.OR:
            case MyConstants.STAR:
            case MyConstants.PLUS:
            case MyConstants.HOOK:
                output = output + pintarOperacion(((Operacion) hijo), nivel + 1);
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

    private static String pintarOperacion(Operacion h, int nivel) {
        String output = "Operacion: " + h.getTipo();
        for (int numh = 0; numh < h.getNumHijos(); numh++) {
            output = output + pintarHijo(((INodo) h), numh, nivel + 1);
        }
        return output;
    }

}
