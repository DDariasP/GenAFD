package proleg.arpv;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class Estado {

    public static Estado fin = new Estado("final");
    public static String[] Nterminal = {"(", ")*", ")+", ")?", "|", "lambda"};
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
        //añade el Estado a la lista
        listaS.add(s);
        //crea un proto-Estado
        ArrayList<Expresion> proto = new ArrayList<>();
        //recorre el Estado
        for (int i = 0; i < s.elem.size(); i++) {
            //toma un elemento
            Expresion exp = s.elem.get(i);
            //comprueba el simbolo de transicion
            String simT;
            if (exp.punto + 1 < exp.tokens.length) {
                simT = exp.tokens[exp.punto + 1];
            } else {
                simT = "lambda";
            }
            //si es no terminal
            if (Arrays.asList(Nterminal).contains(simT)) {
                //si esta al final
                if (simT.equals("lambda")) {
                    //actualiza la Expresion
                    exp.destino = fin;
                    //la añade al proto-Estado
                    if (!Expresion.contiene(proto, exp)) {
                        proto.add(exp);
                    }
                } //si no esta al final
                else {

                }
            } //si es terminal
            else {
                //crea una nueva Expresion
                Expresion nuevaExp = new Expresion(exp);
                //mueve el punto
                nuevaExp.punto++;
                nuevaExp.tokens[nuevaExp.punto - 1] = nuevaExp.tokens[nuevaExp.punto];
                nuevaExp.tokens[nuevaExp.punto] = ".";
                //la añade al proto-Estado
                if (!Expresion.contiene(proto, nuevaExp)) {
                    proto.add(nuevaExp);

                }
            }

        }
        //cuando completa el proto-Estado
        if (!proto.isEmpty()) {
            //si es nuevo
            if (!Expresion.sonIguales(s.elem, proto)) {
                //lo crea
                Estado sig = new Estado();
                sig.elem = proto;
                //lo analiza
                transiciones(sig, listaS);
            }
        }
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
