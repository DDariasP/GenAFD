package proleg.arpv;

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
public class MyARPV implements MyConstants {

    private ArrayList<Estado> listaS;

    MyARPV(AST ast) {
        //lista de Estado del ARPV
        listaS = new ArrayList<>();
        //Expresion inicial
        Expresion exp0 = new Expresion(ast);
        //Estado inicial
        Estado s0 = new Estado();
        s0.elem.add(exp0);

//        System.out.println();
//        for (int i = 0; i < exp0.lista.size(); i++) {
//            System.out.println(exp0.lista.get(i));
//
//        }
//        System.out.println();
//        for (int i = 0; i < exp0.tokens.length; i++) {
//            System.out.println(exp0.tokens[i]);
//
//        }
//        System.out.println();

        //lista de Estado sucesivos
        Estado.transiciones(s0, listaS);

        for (int pos = 0; pos < listaS.size(); pos++) {
            
        
        System.out.println("\n\n");
                System.out.println(listaS.get(pos).nombre);
        System.out.println(listaS.get(pos));
        System.out.println( Arrays.toString( listaS.get(pos).elem.get(0).tokens ) );
        System.out.println("\n\n");
        }
        
    }

}
