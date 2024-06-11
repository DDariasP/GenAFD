package proleg.arpv;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Simbolo)) {
            return false;
        }

        Simbolo obj = (Simbolo) o;
        boolean iguales = true;
        if (terminal != obj.terminal) {
            iguales = false;
        }
        if (!nombre.equals(obj.nombre)) {
            iguales = false;
        }
        return iguales;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.terminal ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
