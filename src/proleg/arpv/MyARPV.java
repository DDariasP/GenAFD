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
        //elementos de la Regla
        ArrayList<Regla> e0elem = new ArrayList<>();
        Regla.clausura(e0elem, R0);
        //guarda el Estado
        Estado s0 = new Estado();
        for (int i = 0; i < e0elem.size(); i++) {
            s0.elem.add(new Elemento(new Simbolo(true, "lambda"), e0elem.get(i)));
        }
        
        //lista de Estado sucesivos
        Estado.transiciones(s0, listaS);
        System.out.println("\nsize="+listaS.size());
        for (int i = 0; i < listaS.size(); i++) {
            System.out.println(listaS.get(i).nombre);
            for (int j = 0; j < listaS.get(i).elem.size(); j++) {
                System.out.println(listaS.get(i).elem.get(j));
            }
        }

    }

}
