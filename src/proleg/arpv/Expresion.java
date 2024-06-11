package proleg.arpv;

import java.util.ArrayList;
import java.util.Objects;
import proleg.ast.*;
import proleg.lexico.*;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Expresion {

    public ArrayList<INodo> elem;
    public int punto;
    public INodo simT;
    public Estado destino;

    public Expresion(AST ast) {
        elem = ast.arbol.getListaH();
        punto = 0;
        simT = new Base();
        simT.setID(elem.get(punto).getID());
        simT.setNombre(elem.get(punto).getNombre());
        destino = null;
    }

    Expresion(Expresion exp) {
        elem = exp.elem;
        punto = exp.punto;
        simT = exp.simT;
        destino = exp.destino;
    }

    public static boolean iguales(ArrayList<Expresion> listaA, ArrayList<Expresion> listaB) {
        boolean iguales = true;
        if (listaA.size() != listaB.size()) {
            iguales = false;
        } else {
            int size = listaB.size();
            int cont = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (listaA.get(i).equals(listaB.get(j))) {
                        cont++;
                    }
                }
            }
            if (cont != size) {
                iguales = false;
            }
        }
        return iguales;
    }

    public static boolean contiene(ArrayList<Expresion> proto, Expresion exp) {
        boolean encontrado = false;
        int pos = 0;
        while (!encontrado && pos < proto.size()) {
            if (proto.get(pos).equals(exp)) {
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

        if (!(o instanceof Expresion)) {
            return false;
        }

        Expresion obj = (Expresion) o;
        boolean iguales = true;
        if (punto != obj.punto) {
            iguales = false;
        }
        if (!INodo.iguales(elem, obj.elem)) {
            iguales = false;
        }
        return iguales;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.elem);
        hash = 79 * hash + this.punto;
        return hash;
    }

    @Override
    public String toString() {
        String output = "[";
        for (int i = 0; i < elem.size(); i++) {
            if (i == punto) {
                output = output + " .";
            }
            output = output + " " + elem.get(i);
        }
        if (punto == elem.size()) {
            output = output + " .";
        }
        output = output + " ] => " + simT;
        if (destino != null) {
            output = output + " => " + destino.nombre;
        } else {
            output = output + " => null";
        }
        return output;
    }

}
