package proleg.arpv;

import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Estado {

    public static int NE = 0;

    public String nombre;
    public ArrayList<Elemento> elem;

    public Estado() {
        nombre = "s" + NE;
        NE++;
        elem = new ArrayList<>();
    }

    public static void transiciones(Estado s, ArrayList<Estado> listaS) {
        //guarda el Estado en la tabla
        listaS.add(s);
        //crea un proto-Estado
        ArrayList<Elemento> listaE = new ArrayList<>();
        //mira todas los Elemento del Estado
        for (int i = 0; i < s.elem.size(); i++) {
            //analiza la Regla del Elemento tomado
            Regla sigR = s.elem.get(i).regla;
            Simbolo simR;
            //si el punto no esta al final
            if (sigR.punto < sigR.simR.size()) {
                //el punto marca el Simbolo de transicion
                simR = sigR.simR.get(sigR.punto);
                //si el Simbolo de transicion es no terminal
                if (!simR.terminal) {
                    //expande la lista de Regla
                    for (int j = 0; j < Regla.listaR.size(); j++) {
                        Regla r = Regla.listaR.get(j);
                        //añade las coincidencias
                        if (simR.nombre.equals(r.simL.nombre)) {
                            Elemento nuevoE = new Elemento(new Simbolo(true,"lambda"), r);
                            //si el Elemento no esta ya en el proto-Estado
                            if (!Elemento.contiene(listaE, nuevoE)) {
                                //añade el Elemento al proto-Estado
                                listaE.add(nuevoE);
                                System.out.println(nuevoE);
                            }
                        }
                    }
                }
                //avanza el punto en la Regla
                Regla nuevaR = new Regla(sigR);
                nuevaR.punto++;
                //crea el Elemento con su Simbolo y su Regla
                Elemento nuevoE = new Elemento(simR, nuevaR);
                //si el Elemento no esta ya en el proto-Estado
                if (!Elemento.contiene(listaE, nuevoE)) {
                    //añade el Elemento al proto-Estado
                    listaE.add(nuevoE);
                }
            }
        }

        //si la lista de Estado NO esta vacia y NO contiene al proto-Estado
        if (!listaE.isEmpty() && !Estado.contiene(listaS, listaE)) {
            //crea Estado a partir de proto-Estado
            Estado nuevoS = new Estado();
            nuevoS.elem = listaE;
            //analiza el nuevo Estado
            transiciones(nuevoS, listaS);
        }
    }

    public static boolean contiene(ArrayList<Estado> listaS, ArrayList<Elemento> listaE) {
        boolean encontrado = false;
        int pos = 0;
        while (!encontrado && pos < listaS.size()) {
            Estado sig = listaS.get(pos);
            ArrayList<Elemento> siglistaE = sig.elem;
            if (Elemento.iguales(siglistaE, listaE)) {
                encontrado = true;
            }
            pos++;
        }
        return encontrado;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < elem.size(); i++) {
            output = output + "\n" + elem.get(i);
        }
        return output;
    }

}
