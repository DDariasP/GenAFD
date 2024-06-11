package proleg.arpv;

import proleg.sintactico.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Regla {

    public static ArrayList<Regla> listaR = MyEspecificacion.listaR;
    public String nombre;
    public Simbolo simL;
    public ArrayList<Simbolo> simR;
    public int punto;

    public Regla(String s) {
        nombre = s;
        simR = new ArrayList<>();
        punto = 0;
    }

    public Regla(Regla Rx) {
        nombre = Rx.nombre;
        simL = Rx.simL;
        simR = Rx.simR;
        punto = Rx.punto;
    }

    public static void clausura(ArrayList<Regla> elemRx, Regla Rx) {
        elemRx.add(Rx);
        if (Rx.punto < elemRx.size()) {
            Simbolo sigS = Rx.simR.get(Rx.punto);
            if (!sigS.terminal) {
                for (int i = 0; i < listaR.size(); i++) {
                    Regla sigR = new Regla(listaR.get(i));
                    if (!sigR.equals(Rx)) {
                        if (sigS.nombre.equals(sigR.simL.nombre) && !contiene(elemRx, sigR)) {
                            clausura(elemRx, sigR);
                        }
                    }
                }
            }
        }
    }

    public static boolean contiene(ArrayList<Regla> lista, Regla r) {
        boolean encontrado = false;
        int pos = 0;
        while (!encontrado && pos < lista.size()) {
            Regla sig = lista.get(pos);
            if (sig.equals(r)) {
                encontrado = true;
            }
            pos++;
        }
        return encontrado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Regla)) {
            return false;
        }

        Regla obj = (Regla) o;
        boolean iguales = true;
        if (!nombre.equals(obj.nombre)) {
            iguales = false;
        }
        if (punto != obj.punto) {
            iguales = false;
        }
        return iguales;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + this.punto;
        return hash;
    }

    @Override
    public String toString() {
        String output = nombre + "[ ";
        output = output + simL.nombre + " ::= ";
        for (int i = 0; i < simR.size(); i++) {
            if (i == punto) {
                output = output + " .";
            }
            output = output +" "+simR.get(i);
        }
        if (punto == simR.size()) {
            output = output + " .";
        }
        output=output+" ]";
        return output;
    }

}
