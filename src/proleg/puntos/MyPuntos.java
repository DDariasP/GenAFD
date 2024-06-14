package proleg.puntos;

import proleg.ast.*;
import proleg.lexico.*;
import java.util.ArrayList;

/**
 * Algoritmo de transformación de expresiones regulares en AFD
 *
 * @author Diego Francisco Darias Pino
 *
 */
public class MyPuntos implements MyConstants {

    MyPuntos(AST ast) {
        //Expresion inicial
        Expresion exp0 = new Expresion(ast);

//        for (int i = 0; i < exp0.array.size(); i++) {
//            Tupla tp = exp0.array.get(i);
//            System.out.println(tp);
//            System.out.println(tp.symID);
//        }

        //Estado inicial
        Estado s0 = new Estado(exp0);

        //Automata completo
        ArrayList<Estado> AFD = Estado.generarAFD(s0);
        for (int i = 0; i < AFD.size(); i++) {
            System.out.println(AFD.get(i));
        }
    }
}
