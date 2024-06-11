package proleg.arpv;

import proleg.ast.*;
import proleg.lexico.*;
import java.util.ArrayList;

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

        //lista de Estado sucesivos
        Estado.transiciones(s0, listaS);
        System.out.println("\nsize=" + listaS.size() + "\n");
        for (int i = 0; i < listaS.size(); i++) {
            System.out.println(listaS.get(i).nombre);
            for (int j = 0; j < listaS.get(i).elem.size(); j++) {
                System.out.println(listaS.get(i).elem.get(j).toString());
            }
            System.out.println("");
        }
    }

}
