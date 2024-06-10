package proleg.arpv;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Expresion {

    private String nombre;
    private ArrayList<Expresion> simbolos;
    private int punto;

    public Expresion(String s) {
        nombre = s;
        simbolos = new ArrayList<>();
        punto = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int numSimbolos() {
        return simbolos.size();
    }

    public Expresion getSimboloN(int n) {
        return simbolos.get(n);
    }

    public void addSimbolo(Expresion e) {
        simbolos.add(e);
    }

    public int posPunto() {
        return punto;
    }

    public void movPunto(int n) {
        punto = n;
    }

    public ArrayList<Expresion> clausura() {
        ArrayList<Expresion> lista = new ArrayList<>();
        lista.add(this);
        
        

        return lista;
    }

}
