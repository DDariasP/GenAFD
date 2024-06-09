package proleg.ast;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Fichero implements INodo {

    private final int ID;
    private final String nombre;
    private final ArrayList<INodo> hijos;

    public Fichero(int n, String s) {
        ID = n;
        nombre = s;
        hijos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int getID() {
        return ID;
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
    public void setHijoN(INodo n) {
        hijos.add(n);
    }

}
