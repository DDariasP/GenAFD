package proleg.semantico;

import java.io.*;
import proleg.ast.*;

/**
 * Clase que desarrolla el punto de entrada al compilador.
 *
 * @author Diego Francisco Darias Pino
 *
 */
public class MyCompilerSem {

    /**
     * Punto de entrada de la aplicacion
     *
     * @param args
     */
    public static void main(String[] args) {
        File mainfile = new File("Ejemplo.txt");
        try {
            MyETDSDesc parser = new MyETDSDesc();
            if (parser.parse(mainfile)) {
                printOutput("Correcto");
            } else {
                printOutput("Incorrecto");
            }
        } catch (Error err) {
            printError(mainfile.getName(), err);
            printOutput("Incorrecto");

        } catch (Exception ex) {
            printError(mainfile.getName(), ex);
            printOutput("Incorrecto");
        }
    }

    /**
     * Genera el fichero de error
     *
     * @param e Error a mostrar
     */
    private static void printError(String filename, Throwable e) {
        try {
            File errorfile = new File("Errors.txt");
            if (errorfile.exists()) {
                errorfile.delete();
                System.out.println("\nArchivo " + errorfile.getName() + " sobreescrito.\n");
            } else {
                System.out.println("\nArchivo " + errorfile.getName() + " creado.\n");
            }
            errorfile.createNewFile();
            PrintStream errorStream = new PrintStream(errorfile);
            errorStream.println("[File " + filename + "] 1 error found:");
            errorStream.println(e.toString());
            errorStream.close();
        } catch (Exception ex) {
        }
    }

    /**
     * Genera el fichero de salida
     *
     * @param e Error a mostrar
     */
    private static void printOutput(String msg) {
        try {
            File outputfile = new File("Output.txt");
            if (outputfile.exists()) {
                outputfile.delete();
                System.out.println("\nArchivo " + outputfile.getName() + " sobreescrito.\n");
            } else {
                System.out.println("\nArchivo " + outputfile.getName() + " creado.\n");
            }
            outputfile.createNewFile();
            PrintStream stream = new PrintStream(outputfile);
            stream.println(msg);
            stream.close();
        } catch (Exception ex) {
        }
    }
}
