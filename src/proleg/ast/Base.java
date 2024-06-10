package proleg.ast;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Base implements INodo {

    private int ID;
    private String simbolo;

    public Base() {
        //
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String s) {
        simbolo = s;
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
        return 0;
    }

    @Override
    public INodo getHijoN(int n) {
        return null;
    }

    @Override
    public void addHijo(INodo n) {
        //
    }

}
