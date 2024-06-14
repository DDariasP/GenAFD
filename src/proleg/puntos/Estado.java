package proleg.puntos;

import java.util.ArrayList;

/**
 * Clase que define los estados del AFD
 *
 * @author Diego Francisco Darias Pino
 */
public class Estado {

    public static Estado FIN = new Estado("final");
    public static int NUM = 1;
    public String nombre;
    public String symOri;
    public ArrayList<Expresion> listaE;

    //Estado generico
    public Estado() {
        nombre = "s" + NUM;
        NUM++;
        symOri = null;
        listaE = new ArrayList<>();
    }

    //Estado con nombre especifico
    public Estado(String s) {
        nombre = s;
        symOri = null;
        listaE = new ArrayList<>();
    }

    //Estado inicial
    public Estado(Expresion exp0) {
        nombre = "s0";
        symOri = Tupla.LAMBDA.sym;
        listaE = new ArrayList<>();
        listaE.add(exp0);
    }

    //Algoritmo de avanzar un punto
    public static ArrayList<Estado> generarAFD(Estado s0) {
        //Lista de Estado del AFD
        ArrayList<Estado> listaS = new ArrayList<>();
        //Comprueba punto delante de no terminal
        ArrayList<Expresion> listaCanon = new ArrayList<>();
        for (int i = 0; i < s0.listaE.size(); i++) {
            Expresion exp = s0.listaE.get(i);
            Expresion.clausuraLambda(exp, listaCanon);
        }


        //Actualiza el Estado con las expresiones correctas
        s0.listaE = listaCanon;
        //Guarda el Estado inicial
        listaS.add(s0);
        //Debe mantenerse un orden FIFO en el analisis de Estado
        ArrayList<Estado> pilaS = new ArrayList<>();
        //Avanza el punto recursivamente
        transicion(s0, pilaS, listaS);
        //Devuelve la lista de Estado del AFD
        return listaS;
    }

    //Parte recursiva del algoritmo del punto
    private static void transicion(Estado s, ArrayList<Estado> pilaS, ArrayList<Estado> listaS) {
        //Busca los simbolos no terminales detras de puntos
        ArrayList<String> listaST = new ArrayList<>();
        for (int i = 0; i < s.listaE.size(); i++) {
            Expresion exp = s.listaE.get(i);
            String sym = exp.array.get(exp.posP).sym;
            listaST.add(sym);
        }
        //Comprueba las transiciones con cada simbolo
        for (int i = 0; i < listaST.size(); i++) {
            //Genera un proto-Estado por simbolo
            ArrayList<Expresion> proto = new ArrayList<>();
            //Toma un simbolo
            String st = listaST.get(i);
            //Comprueba todas las Expresion con el simbolo
            for (int j = 0; j < s.listaE.size(); j++) {
                Expresion exp = s.listaE.get(i);
                String sym = exp.array.get(exp.posP).sym;
                //Si lo usa para avanzar
                if (sym.equals(st)) {
                    //Genera una nueva expresion consumiendo el simbolo
                    Expresion nuevaE = new Expresion(exp);
                    //Hace su clausura lambda
                    ArrayList<Expresion> listaCanon = new ArrayList<>();
                    Expresion.clausuraLambda(nuevaE, proto);
                    //Añade al proto-Estado las Expresion no repetidas
                    Expresion.vertir(listaCanon, proto);
                }
            }
            //Si el proto-Estado no esta vacio
            if (!proto.isEmpty()) {

                //Comprueba que no exista un Estado igual en la lista
                if (!Estado.contiene(listaS, proto)) {

                    //Crea el nuevo Estado
                    Estado nuevoS = new Estado();
                    nuevoS.listaE = proto;
                    //Guarda el simbolo de transicion al Estado
                    nuevoS.symOri = st;
                    //Mete el nuevo Estado en la pila
                    pilaS.add(nuevoS);
                }
            }
        }
        //Avanza el punto en los nuevos Estado recursivamente
        while (!pilaS.isEmpty()) {
            Estado sigS = pilaS.get(0);
            listaS.add(sigS);
            pilaS.remove(0);
            transicion(sigS, pilaS, listaS);
        }
    }

    //Comprueba que no exista un Estado igual al proto-Estado en la lista
    private static boolean contiene(ArrayList<Estado> listaS, ArrayList<Expresion> proto) {
        boolean contiene = false;
        int pos = 0;

        while (!contiene && pos < listaS.size()) {

            Estado s = listaS.get(pos);

            if (Expresion.sonIguales(s.listaE, proto)) {

                contiene = true;
            }
            pos++;
        }
        return contiene;
    }

    @Override
    public String toString() {
        String output = "\n";
        if (symOri != null) {
            output = output + symOri + " => ";
        }
        output = output + nombre + ":\n";
        for (int i = 0; i < listaE.size(); i++) {
            output = output + "\t" + listaE.get(i) + "\n";
        }
        return output;
    }

}
