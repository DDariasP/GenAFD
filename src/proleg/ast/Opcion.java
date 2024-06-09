package proleg.ast;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Opcion implements INodo {

    private final int ID;
    private final String tipo;
    private final ArrayList<INodo> hijos;

    public Opcion(int n, String s) {
        ID = n;
        tipo = s;
        hijos = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
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
