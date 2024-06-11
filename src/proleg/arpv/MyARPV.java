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
    private ArrayList<Estado> listaS;

    MyARPV(AST ast) {
        //lista de Regla de la expresion
        listaR = MyEspecificacion.crear();
        //lista de Estado del ARPV
        listaS = new ArrayList<>();
        //Regla del simbolo inicial
        Regla R0 = listaR.get(0);
        //elementos del Estado inicial
        ArrayList<Regla> elem0 = new ArrayList<>();
        Regla.clausura(elem0, R0);
        //Estado inicial
        Estado s0 = new Estado();
        s0.elem = elem0;

        //lista de Estado sucesivos
        Estado.transiciones(s0, listaS);
        System.out.println("\nsize=" + listaS.size() + "\n");
        for (int i = 0; i < listaS.size(); i++) {
            System.out.println(listaS.get(i).nombre);
            for (int j = 0; j < listaS.get(i).elem.size(); j++) {
                System.out.println(listaS.get(i).elem.get(j));
            }
            System.out.println("");
        }
    }

}
