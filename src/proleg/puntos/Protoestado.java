package proleg.puntos;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Protoestado {

    public ArrayList<Expresion> e;
    public String symT;
    public String origen;

    public Protoestado() {
        e = new ArrayList<>();
    }

    public Protoestado(Expresion exp) {
        e = new ArrayList<>();
        e.add(exp);
    }

    public Protoestado(ArrayList<Expresion> pe) {
        e = pe;
    }

}
