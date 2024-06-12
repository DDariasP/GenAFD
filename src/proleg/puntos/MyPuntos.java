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

    private ArrayList<Expresion> listaCanon;

    MyPuntos(AST ast) {

        //lista de Expresion del algoritmo
        listaCanon = new ArrayList<>();
        //Expresion inicial
        Expresion exp0 = new Expresion(ast);

        //lista de Expresion canonicas
        Expresion.transiciones(exp0,listaCanon);

        
    }
}
