package proleg.ast;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public interface INodo {

    public int getID();

    public void setID(int n);

    public String getNombre();

    public void setNombre(String s);

    public int getNumHijos();

    public INodo getHijoN(int n);

    public ArrayList<INodo> getListaH();

    public void addHijo(INodo n);

    public boolean esTerminal();

    public boolean igual(INodo n);

    public static boolean iguales(ArrayList<INodo> listaA, ArrayList<INodo> listaB) {
        boolean iguales = true;
        if (listaA.size() != listaB.size()) {
            iguales = false;
        } else {
            int size = listaB.size();
            int cont = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (listaA.get(i).igual(listaB.get(j))) {
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

}
