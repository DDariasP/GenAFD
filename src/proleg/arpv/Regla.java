package proleg.arpv;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Regla {

    public String nombre;
    public Simbolo simL;
    public ArrayList<Simbolo> simR;

    public Regla(String s) {
        nombre = s;
        simR = new ArrayList<>();
    }

    @Override
    public String toString() {
        String output = nombre + ": ";
        output = output + simL.nombre + " ::= ";
        for (int i = 0; i < simR.size(); i++) {
            output = output + simR.get(i) + " ";
        }
        return output;
    }

}
