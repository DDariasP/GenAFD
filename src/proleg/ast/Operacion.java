package proleg.ast;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Operacion implements INodo {

    private int ID;
    private String tipo;
    private ArrayList<INodo> hijos;

    public Operacion() {
        hijos = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String s) {
        tipo = s;
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
