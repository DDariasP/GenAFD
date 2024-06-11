package proleg.arpv;

import proleg.ast.*;
import proleg.lexico.*;
import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Estado {

    public static int NE = 0;
    public String nombre;
    public ArrayList<Expresion> elem;

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
        ArrayList<Expresion> proto = new ArrayList<>();
        //mira todas las Expresion del Estado
        for (int i = 0; i < s.elem.size(); i++) {
            //analiza la Expresion tomada
            Expresion sigExp = s.elem.get(i);
            //si el punto no esta al final
            if (sigExp.punto < sigExp.elem.size()) {
                //avanza el punto en la Expresion
                Expresion nuevaExp = new Expresion(sigExp);
                nuevaExp.punto++;
                //actualiza el simbolo de Trasicion
                if (nuevaExp.punto < nuevaExp.elem.size()) {
                    nuevaExp.simT = nuevaExp.elem.get(nuevaExp.punto);
                } else {
                    nuevaExp.simT = nuevaExp.elem.get(nuevaExp.punto - 1);
                }
                //si la Expresion no esta ya en el proto-Estado
                if (!Expresion.contiene(proto, nuevaExp)) {
                    //añade la Expresion al proto-Estado
                    proto.add(nuevaExp);
                }
            } else {
                //fin de la Expresion
                sigExp.simT = new Base();
                sigExp.simT.setID(MyConstants.BLANK);
                sigExp.simT.setNombre("'lambda'");
                sigExp.destino = new Estado("fin");
                //si la Expresion no esta ya en el proto-Estado
                if (!Expresion.contiene(proto, sigExp)) {
                    //añade la Regla al proto-Estado
                    proto.add(sigExp);
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

    public static boolean contiene(ArrayList<Estado> listaS, ArrayList<Expresion> proto) {
        boolean encontrado = false;
        int pos = 0;
        while (!encontrado && pos < listaS.size()) {
            Estado sig = listaS.get(pos);
            if (Expresion.iguales(sig.elem, proto)) {
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
