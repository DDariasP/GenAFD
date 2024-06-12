package proleg.puntos;

import proleg.ast.*;
import proleg.lexico.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Analizador sintactico basado en una gramatica BNF y LL(1)
 *
 * @author Diego Francisco Darias Pino
 *
 */
public class MyPuntos implements MyConstants {

    private ArrayList<Estado> listaS;

    MyPuntos(AST ast) {

        
        
        
        
        
        //lista de Estado del ARPV
        listaS = new ArrayList<>();
        //Expresion inicial
        Expresion exp0 = new Expresion(ast);
        //Estado inicial
        Estado s0 = new Estado();
        s0.elem.add(exp0);

        System.out.println("");
        System.out.println(s0);
        System.out.println("");
        
                System.out.println("");
        System.out.println(s0.elem.get(0).vectorNT);
        System.out.println("");



    }
}
