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
    public Protoestado listaExp;
    public Tupla[] destinos;

    //Estado vacio
    public Estado(String s) {
        nombre = s;
        listaExp = new Protoestado();
    }

    //Estado inicial
    public Estado(Expresion exp0) {
        nombre = "s0";
        listaExp = new Protoestado(exp0);
    }

    //Estado generico
    public Estado(Protoestado proto) {
        nombre = "s" + NUM;
        NUM++;
        listaExp = new Protoestado(proto.e);
    }

    //Llamada al algoritmo de avanzar un punto
    public static ArrayList<Estado> generarAFD(Estado s0) {
        //Lista de Estado del AFD
        ArrayList<Estado> listaAFD = new ArrayList<>();
        //Busca todos los simbolos no terminales
        ArrayList<String> listaST = new ArrayList<>();
        //Usa una expresion cualquiera
        Expresion exp0 = s0.listaExp.e.get(0);
        for (int i = 0; i < exp0.array.size(); i++) {
            Tupla tp = exp0.array.get(i);
            if (tp.terminal && !listaST.contains(tp.sym)) {
                String sym = exp0.array.get(i).sym;
                listaST.add(sym);
            }
        }
        //Hace la clausura lambda del Estado inicial
        ArrayList<Expresion> listaCanon = new ArrayList<>();
        Protoestado pe = s0.listaExp;
        for (int i = 0; i < pe.e.size(); i++) {
            Expresion exp = pe.e.get(i);
            Expresion.clausuraLambda(exp, listaCanon);
        }
        //Actualiza la lista de Expresion del Estado inicial
        pe.e = listaCanon;
        //Debe mantenerse un orden FIFO en el analisis de Estado
        ArrayList<Estado> pilaS = new ArrayList<>();
        //Añade el Estado inicial a la pila
        pilaS.add(s0);
        //Añade el Estado inicial a la lista del AFD
        listaAFD.add(s0);
        //Avanza el punto
        transicion(listaST, pilaS, listaAFD);
        //Devuelve la lista de Estado del AFD
        return listaAFD;
    }

    //Algoritmo de avanzar el punto
    private static void transicion(ArrayList<String> listaST, ArrayList<Estado> pilaS, ArrayList<Estado> listaAFD) {
        //Mientras haya Estado en la pila
        while (!pilaS.isEmpty()) {
            //Toma el Estado cima
            Estado cima = pilaS.get(0);
            pilaS.remove(0);
            //Inicializa el vector destinos del Estado cima
            cima.destinos = new Tupla[listaST.size()];
            //Para cada simbolo terminal
            for (int numSym = 0; numSym < listaST.size(); numSym++) {
                String st = listaST.get(numSym);
                //Crea un Protoestado vacio
                Protoestado proto = new Protoestado();
                //Guarda el simbolo terminal como su simbolo de transicion
                proto.symT = st;
                //Para cada Expresion del Estado cima
                for (int i = 0; i < cima.listaExp.e.size(); i++) {
                    Expresion exp = cima.listaExp.e.get(i);
                    //Si el punto no esta al final
                    if (exp.posP < exp.array.size()) {
                        //Comprueba el simbolo delante del punto
                        Tupla symP = exp.array.get(exp.posP);
                        //Si es terminal y es el simbolo de transicion analizado
                        if (symP.terminal && symP.sym.equals(st)) {
                            //Avanza el punto
                            Expresion nuevaE = new Expresion(exp);
                            nuevaE.posP++;
                            //Hace la clausura lambda de la nueva Expresion
                            ArrayList<Expresion> listaCanon = new ArrayList<>();
                            Expresion.clausuraLambda(nuevaE, listaCanon);
                            //Guarda las Expresion no repetidas en el Protoestado
                            Expresion.vertir(listaCanon, proto.e);
                        }
                    } else {

                    }
                }
                //Cuando ha comprobado todas las Expresion para un simbolo
                //comprueba si el Protoestado resultado esta vacio
                if (!proto.e.isEmpty()) {
                    //Comprueba si existe un Estado igual al Protoestado resultante
                    //en la lista del AFD
                    int pos = contiene(listaAFD, proto.e);
                    if (pos != -1) {
                        //Anota el Estado existente como destino
                        //del Estado cima para el simbolo analizado
                        Estado exiS = listaAFD.get(pos);
                        cima.destinos[numSym]=new Tupla(proto.symT,exiS.nombre);

                    } else {
                        //Si no, crea un nuevo Estado a partir del Protoestado
                        Estado sigS = new Estado(proto);
                        //Lo guarda en la pila y en la lista del AFD
                        pilaS.add(sigS);
                        listaAFD.add(sigS);
                        //Anota el Estado siguiente como destino
                        //del Estado cima para el simbolo analizado
                        cima.destinos[numSym]=new Tupla(proto.symT,sigS.nombre);
                    }
                }
                //Si esta vacio, la cima es el Estado final
            }
        }
    }

    //Comprueba que no exista un Estado igual al proto-Estado en la lista
    private static int contiene(ArrayList<Estado> listaS, ArrayList<Expresion> proto) {
        int contiene = -1;
        int pos = 0;
        while (contiene == -1 && pos < listaS.size()) {
            Estado s = listaS.get(pos);
            if (Expresion.sonIguales(s.listaExp.e, proto)) {
                contiene = pos;
            }
            pos++;
        }
        return contiene;
    }

    @Override
    public String toString() {
        String output = "\n" + nombre + ":\n";
        for (int i = 0; i < destinos.length; i++) {
            Tupla tp = destinos[i];
            if (tp != null) {
                output = output + tp.sym + " => " + tp.destino + "\n";
            }
        }
        for (int i = 0; i < listaExp.e.size(); i++) {
            output = output + "\t" + listaExp.e.get(i) + "\n";
        }
        return output;
    }

}
