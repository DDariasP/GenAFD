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

    public Estado() {
        nombre = "s" + NE;
        NE++;
        elem = new ArrayList<>();
    }

    public Estado(String s) {
        nombre = s;
        elem = new ArrayList<>();
    }

    public static void transiciones(Estado s, ArrayList<Estado> listaS) {
        //guarda el Estado en la tabla
        listaS.add(s);
        //crea un proto-Estado
        ArrayList<Regla> proto = new ArrayList<>();
        //mira todas los Elemento del Estado
        for (int i = 0; i < s.elem.size(); i++) {
            //analiza la Regla del Elemento tomado
            Regla sigR = s.elem.get(i);
            Simbolo simR;
            //si el punto no esta al final
            if (sigR.punto < sigR.simR.size()) {
                //el punto marca el Simbolo de transicion
                simR = sigR.simR.get(sigR.punto);
                //si el Simbolo de transicion es no terminal
                if (!simR.terminal) {
                    //expande la lista de Regla
                    for (int j = 0; j < Regla.listaR.size(); j++) {
                        Regla nuevaR = Regla.listaR.get(j);
                        //añade las coincidencias
                        if (simR.nombre.equals(nuevaR.simL.nombre)) {
                            //si la regla no esta ya en el proto-Estado
                            if (!Regla.contiene(proto, nuevaR)) {
                                //añade el Elemento al proto-Estado
                                proto.add(nuevaR);
                            }
                        }
                    }
                }
                //avanza el punto en la Regla
                Regla nuevaR = new Regla(sigR);
                nuevaR.punto++;

                if (nuevaR.punto < nuevaR.simR.size()) {
                    nuevaR.simP = nuevaR.simR.get(nuevaR.punto);
                } else {
                    nuevaR.simP = nuevaR.simR.get(nuevaR.punto - 1);
                }
                //si la Regla no esta ya en el proto-Estado
                if (!Regla.contiene(proto, nuevaR)) {
                    //añade la Regla al proto-Estado
                    proto.add(nuevaR);
                }
            } else {
                Simbolo simX = new Simbolo(true, "lambda");
                sigR.simP = simX;
                //si la Regla no esta ya en el proto-Estado
                if (!Regla.contiene(proto, sigR)) {
                    //añade la Regla al proto-Estado
                    proto.add(sigR);
                }

            }
        }

        //si la lista de Estado NO esta vacia
        if (!proto.isEmpty()) {
            //si NO contiene al proto-Estado
            if (!proto.isEmpty() && !Estado.contiene(listaS, proto)) {
                //crea Estado a partir de proto-Estado
                Estado nuevoS = new Estado();
                nuevoS.elem = proto;
                //actualiza los destinos
                for (int i = 0; i < s.elem.size(); i++) {
                    s.elem.get(i).destino = nuevoS;
                }
                //analiza el nuevo Estado
                transiciones(nuevoS, listaS);
            }
        }
    }

    public static boolean contiene(ArrayList<Estado> listaS, ArrayList<Regla> proto) {
        boolean encontrado = false;
        int pos = 0;
        while (!encontrado && pos < listaS.size()) {
            Estado sig = listaS.get(pos);
            if (Regla.iguales(sig, proto)) {
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
