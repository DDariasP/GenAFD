package proleg.arpv;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Estado {

    public static int NE = 0;
    public String nombre;
    public ArrayList<Regla> elem;

    public Estado(String s) {
        nombre = s;
        elem = new ArrayList<>();
        NE++;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < elem.size(); i++) {
            output = output + "\n" + elem.get(i) + " - Punto=" + elem.get(i).punto;
        }
        return output;
    }

}
