package proleg.arpv;

import java.io.*;
import proleg.semantico.*;
import proleg.sintactico.*;

/**
 * Clase que desarrolla el punto de entrada al compilador.
 *
 * @author Diego Francisco Darias Pino
 *
 */
public class MyCompilerARPV {

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
            MyARPV arpv = new MyARPV(parser.getAST());
        } else {
            System.out.println("Incorrecto");
        }
    }

}
