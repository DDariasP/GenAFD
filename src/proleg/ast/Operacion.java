package proleg.ast;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Operacion implements INodo {

    private int ID;
    private String nombre;
    private ArrayList<INodo> hijos;

    public Operacion() {
        hijos = new ArrayList<>();
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
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String s) {
        nombre = s;
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
    public ArrayList<INodo> getListaH() {
        return hijos;
    }

    @Override
    public void addHijo(INodo n) {
        hijos.add(n);
    }

}
