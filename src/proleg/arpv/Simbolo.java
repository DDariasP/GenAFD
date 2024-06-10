package proleg.arpv;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Simbolo {

    public final boolean terminal;
    public final String nombre;

    public Simbolo(boolean t, String s) {
        terminal = t;
        nombre = s;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
