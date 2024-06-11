package proleg.arpv;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author diego
 */
public class Elemento {

    public static int NE = 0;
    public int id;
    public Simbolo simbolo;
    public Regla regla;

    public Elemento(Simbolo s, Regla r) {
        id = NE;
        NE++;
        simbolo = s;
        regla = r;
    }

    public static boolean contiene(ArrayList<Elemento> lista, Regla r) {
        boolean encontrado = false;
        int pos = 0;
        while (!encontrado && pos < lista.size()) {
            Elemento sig = lista.get(pos);
            if (sig.regla.equals(r)) {
                encontrado = true;
            }
            pos++;
        }
        return encontrado;
    }

    public static boolean contiene(ArrayList<Elemento> lista, Elemento e) {
        boolean encontrado = false;
        int pos = 0;
        while (!encontrado && pos < lista.size()) {
            Elemento sig = lista.get(pos);
            if (sig.equals(e)) {
                encontrado = true;
            }
            pos++;
        }
        return encontrado;
    }

    public static boolean iguales(ArrayList<Elemento> listaA, ArrayList<Elemento> listaB) {
        boolean iguales = true;
        if (listaA.size() != listaB.size()) {
            iguales = false;
        } else {
            ordenar(listaA);
            ordenar(listaB);
            int pos = 0;
            boolean encontrado = false;
            while (!encontrado && pos < listaA.size()) {
                Elemento eA = listaA.get(pos);
                Elemento eB = listaB.get(pos);
                if (!eA.equals(eB)) {
                    encontrado = true;
                    iguales = false;
                }
                pos++;
            }
        }
        return iguales;
    }

    public static void ordenar(ArrayList<Elemento> lista) {
        if (lista == null || lista.isEmpty()) {
            return;
        }
        quickSort(lista, 0, lista.size() - 1);
    }

    private static void quickSort(ArrayList<Elemento> lista, int menor, int mayor) {
        if (menor < mayor) {
            int indexPivote = partition(lista, menor, mayor);
            quickSort(lista, menor, indexPivote - 1);
            quickSort(lista, indexPivote + 1, mayor);
        }
    }

    private static int partition(ArrayList<Elemento> lista, int menor, int mayor) {
        Elemento pivote = lista.get(mayor);
        int i = menor - 1;
        for (int j = menor; j < mayor; j++) {
            if (lista.get(j).id - pivote.id <= 0) {
                i++;
                swap(lista, i, j);
            }
        }
        swap(lista, i + 1, mayor);
        return (i + 1);
    }

    private static void swap(ArrayList<Elemento> lista, int i, int j) {
        Elemento tmp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Regla)) {
            return false;
        }

        Elemento obj = (Elemento) o;
        boolean iguales = true;
        if (!simbolo.equals(obj.simbolo)) {
            iguales = false;
        }
        if (!regla.equals(obj.regla)) {
            iguales = false;
        }
        return iguales;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.simbolo);
        hash = 43 * hash + Objects.hashCode(this.regla);
        return hash;
    }

    @Override
    public String toString() {
        String output = simbolo + " -> " + regla;

        return output;
    }

}
