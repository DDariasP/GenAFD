package proleg.ast;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public interface INodo {

    public int getID();

    public int getNumHijos();

    public INodo getHijoN(int n);

    public void setHijoN(INodo n);

}
