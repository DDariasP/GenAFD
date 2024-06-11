package proleg.ast;

import java.util.ArrayList;
import proleg.lexico.*;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Operacion implements INodo {

    private static boolean terminal = false;
    private int ID;
    private String nombre;
    private ArrayList<INodo> hijos;

    public Operacion() {
        hijos = new ArrayList<>();
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int n) {
        ID = n;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String s) {
        nombre = s;
    }

    @Override
    public int getNumHijos() {
        return hijos.size();
    }

    @Override
    public INodo getHijoN(int n) {
        return hijos.get(n);
    }

    @Override
    public ArrayList<INodo> getListaH() {
        return hijos;
    }

    @Override
    public void addHijo(INodo n) {
        hijos.add(n);
    }

    @Override
    public boolean esTerminal() {
        return terminal;
    }

    @Override
    public boolean igual(INodo n) {
        if (n == this) {
            return true;
        }

        if (!(n instanceof Operacion)) {
            return false;
        }

        Operacion obj = (Operacion) n;
        boolean iguales = true;
        if (ID != obj.ID) {
            iguales = false;
        }
        if (!nombre.equals(obj.nombre)) {
            iguales = false;
        }
        return iguales;
    }

    @Override
    public String toString() {
        String output = "";
        switch (ID) {
            case MyConstants.OR:
                output = nombre;
                break;
            case MyConstants.STAR:
            case MyConstants.PLUS:
            case MyConstants.HOOK:
                output = "( ";
                for (int i = 0; i < hijos.size(); i++) {
                    output = output + hijos.get(i) + " ";
                }
                output = output + ")" + nombre;
                break;
            default:
                throw new AssertionError();
        }
        return output;
    }

}
