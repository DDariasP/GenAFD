package proleg.lexico;

import java.io.*;

/**
 * Clase que desarrolla el punto de entrada al compilador.
 *
 * @author Diego Francisco Darias Pino
 *
 */
public class MyCompilerLex {

    /**
     * Punto de entrada de la aplicacion
     *
     * @param args
     */
    public static void main(String[] args) {
        File mainfile = new File("Ejemplo.txt");
        try {
            File outputfile = new File("Output.txt");
            if (outputfile.exists()) {
                outputfile.delete();
                System.out.println("\nArchivo " + outputfile.getName() + " sobreescrito.");
            } else {
                System.out.println("\nArchivo " + outputfile.getName() + " creado.");
            }
            outputfile.createNewFile();
            PrintStream stream = new PrintStream(outputfile);

            MyLexer lexer = new MyLexer(mainfile);
            Token tk;
            do {
                tk = lexer.getNextToken();
                stream.println(tk.toString());
            } while (tk.getKind() != Token.EOF);
            stream.close();

            File errorfile = new File("Errors.txt");
            if (errorfile.exists()) {
                errorfile.delete();
            }
        } catch (Error err) {
            printError(mainfile.getName(), err);
        } catch (Exception ex) {
            printError(mainfile.getName(), ex);
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
                System.out.println("\nArchivo " + errorfile.getName() + " sobreescrito.");
            } else {
                System.out.println("\nArchivo " + errorfile.getName() + " creado.");
            }
            errorfile.createNewFile();
            PrintStream errorStream = new PrintStream(errorfile);
            errorStream.println("[File " + filename + "] 1 error found:");
            errorStream.println(e.toString());
            errorStream.close();
        } catch (Exception ex) {
        }
    }
}
