package proleg.sintactico;

import proleg.arpv.*;
import java.util.ArrayList;

/**
 *
 * @author Diego Francisco Darias Pino
 */
public class MyEspecificacion {

    public static ArrayList<Regla> listaR;

    public static ArrayList<Regla> crear() {
        listaR = new ArrayList<>();

        Simbolo F = new Simbolo(false, "F");
        Simbolo E = new Simbolo(false, "E");
        Simbolo D = new Simbolo(false, "D");
        Simbolo A = new Simbolo(false, "A");
        Simbolo B = new Simbolo(false, "B");
        Simbolo BB = new Simbolo(false, "BB");
        Simbolo O = new Simbolo(false, "O");

        Simbolo id = new Simbolo(true, "id");
        Simbolo eq = new Simbolo(true, "eq");
        Simbolo semi = new Simbolo(true, "semi");
        Simbolo or = new Simbolo(true, "or");
        Simbolo lambda = new Simbolo(true, "lambda");
        Simbolo sym = new Simbolo(true, "sym");
        Simbolo lparen = new Simbolo(true, "lparen");
        Simbolo rparen = new Simbolo(true, "rparen");
        Simbolo star = new Simbolo(true, "star");
        Simbolo plus = new Simbolo(true, "plus");
        Simbolo hook = new Simbolo(true, "hook");

        Regla R0 = new Regla("R0");
        R0.simL = F;
        R0.simR.add(id);
        R0.simR.add(eq);
        R0.simR.add(E);
        R0.simR.add(semi);
        listaR.add(R0);

        Regla R1 = new Regla("R1");
        R1.simL = E;
        R1.simR.add(D);
        R1.simR.add(A);
        listaR.add(R1);

        Regla R2 = new Regla("R2");
        R2.simL = A;
        R2.simR.add(or);
        R2.simR.add(D);
        R2.simR.add(A);
        listaR.add(R2);

        Regla R3 = new Regla("R3");
        R3.simL = A;
        R3.simR.add(lambda);
        listaR.add(R3);

        Regla R4 = new Regla("R4");
        R4.simL = D;
        R4.simR.add(B);
        R4.simR.add(BB);
        listaR.add(R4);

        Regla R5 = new Regla("R5");
        R5.simL = B;
        R5.simR.add(B);
        R5.simR.add(BB);
        listaR.add(R5);

        Regla R6 = new Regla("R6");
        R6.simL = BB;
        R6.simR.add(lambda);
        listaR.add(R6);

        Regla R7 = new Regla("R7");
        R7.simL = B;
        R7.simR.add(sym);
        listaR.add(R7);

        Regla R8 = new Regla("R8");
        R8.simL = B;
        R8.simR.add(lparen);
        R8.simR.add(E);
        R8.simR.add(rparen);
        R8.simR.add(O);
        listaR.add(R8);

        Regla R9 = new Regla("R9");
        R9.simL = O;
        R9.simR.add(star);
        listaR.add(R9);

        Regla R10 = new Regla("R10");
        R10.simL = O;
        R10.simR.add(plus);
        listaR.add(R10);

        Regla R11 = new Regla("R11");
        R11.simL = O;
        R11.simR.add(hook);
        listaR.add(R11);

        Regla R12 = new Regla("R12");
        R12.simL = O;
        R12.simR.add(lambda);
        listaR.add(R12);

        System.out.println("");
        for (int i = 0; i < listaR.size(); i++) {
            System.out.println(listaR.get(i));
        }
        System.out.println("");

        return listaR;
    }

}
