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

    MyARPV(AST ast) {
        //Lista de reglas de la expresion
        listaR = MyEspecificacion.crear();

    }

}
