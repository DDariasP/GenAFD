package proleg.ast;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Fichero implements INodo {

    private int ID;
    private String nombre;
    private ArrayList<INodo> hijos;

    public Fichero() {
        hijos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String s) {
        nombre = s;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int n) {
        ID = n;
    }

    @Override
    public int getNumHijos() {
        return hijos.size();
    }

    @Override
    public INodo getHijoN(int n) {
        return hijos.get(n);
    }

    @Override
    public void addHijo(INodo n) {
        hijos.add(n);
    }

}
