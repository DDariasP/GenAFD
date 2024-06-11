package proleg.ast;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Base implements INodo {

    private static boolean terminal = true;
    private int ID;
    private String nombre;

    public Base() {
        //
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
        return 0;
    }

    @Override
    public INodo getHijoN(int n) {
        return null;
    }

    @Override
    public ArrayList<INodo> getListaH() {
        return null;
    }

    @Override
    public void addHijo(INodo n) {
        //
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

        if (!(n instanceof Base)) {
            return false;
        }

        Base obj = (Base) n;
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
        int limite = nombre.length() - 1;
        String output = nombre.substring(1, limite);
        return output;
    }

}
