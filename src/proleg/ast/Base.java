package proleg.ast;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Base implements INodo {

    private final int ID;
    private final char simbolo;

    public Base(int n, char c) {
        ID = n;
        simbolo = c;
    }

    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public int getID() {
        return ID;
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
    public void setHijoN(INodo n) {
        //
    }

}
