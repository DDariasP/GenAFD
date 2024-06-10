package proleg.arpv;

import proleg.ast.*;
import proleg.lexico.*;
import proleg.sintactico.*;
import java.util.ArrayList;

/**
 * Analizador sintactico basado en una gramatica BNF y LL(1)
 *
 * @author Diego Francisco Darias Pino
 *
 */
public class MyARPV implements MyConstants {

    private ArrayList<Regla> listaR;
    private ArrayList<Estado> listaE;

    MyARPV(AST ast) {
        //Lista de reglas de la expresion
        listaR = MyEspecificacion.crear();
        //Lista de estados del ARPV
        listaE = new ArrayList<>();
        
        
        for (int n = 0; n < listaR.size(); n++) {
            Regla Rx = listaR.get(n);
            System.out.println(Rx.nombre);
            ArrayList<Regla> elemRx = new ArrayList<>();
            Regla.elementos(elemRx, Rx);
            for (int i = 0; i < elemRx.size(); i++) {
                System.out.println(elemRx.get(i));
            }
            System.out.println("\n\n");
        }

    }

}
