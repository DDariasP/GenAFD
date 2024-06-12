package proleg.puntos;

import java.io.*;
import proleg.semantico.*;
import proleg.sintactico.*;

/**
 * Clase que desarrolla el punto de entrada al compilador.
 *
 * @author Diego Francisco Darias Pino
 *
 */
public class MyCompilerPuntos {

    /**
     * Punto de entrada de la aplicacion
     *
     * @param args
     * @throws java.io.IOException
     * @throws proleg.sintactico.SintaxException
     */
    public static void main(String[] args) throws IOException, SintaxException {
        File mainfile = new File("Ejemplo.txt");
        MyETDSDesc parser = new MyETDSDesc();
        if (parser.parse(mainfile)) {
            System.out.println("Correcto");
            MyPuntos arpv = new MyPuntos(parser.getAST());
        } else {
            System.out.println("Incorrecto");
        }
    }

}
