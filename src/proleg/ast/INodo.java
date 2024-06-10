package proleg.ast;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public interface INodo {

    public int getID();

    public void setID(int n);

    public int getNumHijos();

    public INodo getHijoN(int n);

    public void addHijo(INodo n);

}
