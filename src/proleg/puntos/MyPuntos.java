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

    private ArrayList<Expresion> listaCanon;

    MyPuntos(AST ast) {

        //lista de Expresion del algoritmo
        listaCanon = new ArrayList<>();
        //Expresion inicial
        Expresion exp0 = new Expresion(ast);
        for (int i = 0; i < exp0.vector.size(); i++) {
            Tupla tp = exp0.vector.get(i);
            System.out.println(tp);
        }

        //lista de Expresion canonicas
        Expresion.transiciones(exp0,listaCanon);

        
    }
}
