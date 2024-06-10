package proleg.semantico;

import proleg.ast.*;
import proleg.lexico.*;
import proleg.sintactico.*;
import java.io.File;
import java.io.IOException;

/**
 * Analizador sintactico basado en una gramatica BNF y LL(1)
 *
 * @author Diego Francisco Darias Pino
 *
 */
public class MyETDSDesc implements MyConstants {

    /**
     * Analizador lexico
     */
    private MyLexer lexer;

    /**
     * Siguiente token de la cadena de entrada
     */
    private Token nextToken;

    /**
     * Metodo de analisis de un fichero
     *
     * @param file Fichero a analizar
     * @return Resultado del analisis sintactico
     * @throws java.io.IOException
     * @throws proleg.sintactico.SintaxException
     */
    public boolean parse(File file) throws IOException, SintaxException {
        AST ast = new AST();
        INodo F_h = ast.arbol;
        this.lexer = new MyLexer(file);
        this.nextToken = lexer.getNextToken();
        INodo F_s = parseF(F_h);
        if (nextToken.getKind() == EOF) {
            ast.arbol = F_s;
            ast.print();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que consume un token de la cadena de entrada
     *
     * @param kind Tipo de token a consumir
     * @throws SintaxException Si el tipo no coincide con el token
     */
    private Token match(int kind) throws SintaxException {
        Token tk = nextToken;
        if (nextToken.getKind() == kind) {
            nextToken = lexer.getNextToken();
        } else {
            throw new SintaxException(nextToken, kind);
        }
        return tk;
    }

    /**
     * Analiza el simbolo <F>
     *
     * @throws SintaxException
     */
    private INodo parseF(INodo F_h) throws SintaxException {
        System.out.println("F");
        INodo F_s = null;
        int[] expected = {ID};
        switch (nextToken.getKind()) {
            case ID:
                Token tk1 = match(ID);
                Fichero F1_h = (Fichero) F_h;
                F1_h.setID(tk1.getKind());
                F1_h.setNombre(tk1.getLexeme());
                match(EQ);
                INodo E_s = parseE(F1_h);
                F_s = E_s;
                match(SEMI);
                break;
            default:
                throw new SintaxException(nextToken, expected);
        }
        return F_s;
    }

    /**
     * Analiza el simbolo <E>
     *
     * @throws SintaxException
     */
    private INodo parseE(INodo E_h) throws SintaxException {
        System.out.println("E");
        INodo E_s = null;
        int[] expected = {SYMBOL, RPAREN, SEMI, LPAREN};
        switch (nextToken.getKind()) {
            case SYMBOL:
            case RPAREN:
            case SEMI:
            case LPAREN:
                INodo D_s = parseD(E_h);
                INodo AA_s = parseAA(D_s);
                E_s = AA_s;
                break;
            default:
                throw new SintaxException(nextToken, expected);
        }
        return E_s;
    }

    /**
     * Analiza el simbolo <D>
     *
     * @throws SintaxException
     */
    private INodo parseD(INodo D_h) throws SintaxException {
        System.out.println("D");
        INodo D_s = null;
        int[] expected = {SYMBOL, LPAREN};
        switch (nextToken.getKind()) {
            case SYMBOL:
            case LPAREN:
                INodo B_s = parseB(D_h);
                INodo BB_s = parseBB(B_s);
                D_s = BB_s;
                break;
            default:
                throw new SintaxException(nextToken, expected);
        }
        return D_s;
    }

    /**
     * Analiza el simbolo <B>
     *
     * @throws SintaxException
     */
    private INodo parseB(INodo B_h) throws SintaxException {
        INodo B_s = null;
        int[] expected = {SYMBOL, LPAREN};
        switch (nextToken.getKind()) {
            case SYMBOL:
                System.out.println("B-sym");
                Token tk1 = match(SYMBOL);
                Base B1_s = new Base();
                B1_s.setID(tk1.getKind());
                B1_s.setSimbolo(tk1.getLexeme());
                B_h.addHijo(B1_s);
                B_s = B_h;
                break;
            case LPAREN:
                System.out.println("B-E");
                Token tk2 = match(LPAREN);
                Operacion B2_s = new Operacion();
                B2_s.setID(tk2.getKind());
                B2_s.setTipo(tk2.getLexeme());
                INodo E_s = parseE(B2_s);
                match(RPAREN);
                INodo O_s = parseO(E_s);
                B_h.addHijo(O_s);
                B_s = B_h;
                break;
            default:
                throw new SintaxException(nextToken, expected);
        }
        return B_s;
    }

    /**
     * Analiza el simbolo <BB>
     *
     * @throws SintaxException
     */
    private INodo parseBB(INodo BB_h) throws SintaxException {
        INodo BB_s = null;
        int[] expected = {SYMBOL, LPAREN, OR, SEMI, RPAREN};
        switch (nextToken.getKind()) {
            case SYMBOL:
            case LPAREN:
                System.out.println("BB-sigue");
                INodo B_s = parseB(BB_h);
                BB_s = parseBB(B_s);
                break;
            case OR:
            case SEMI:
            case RPAREN:
                System.out.println("BB-fin");
                BB_s = BB_h;
                break;
            default:
                throw new SintaxException(nextToken, expected);
        }
        return BB_s;
    }

    /**
     * Analiza el simbolo <O>
     *
     * @throws SintaxException
     */
    private INodo parseO(INodo O_h) throws SintaxException {
        INodo O_s = null;
        int[] expected = {STAR, PLUS, HOOK, SYMBOL, LPAREN, OR, SEMI, RPAREN};
        switch (nextToken.getKind()) {
            case STAR:
                System.out.println("O-*");
                Token tk1 = match(STAR);
                Operacion O1_s = (Operacion) O_h;
                O1_s.setID(tk1.getKind());
                O1_s.setTipo(tk1.getLexeme());
                O_s = O1_s;
                break;
            case PLUS:
                System.out.println("O-+");
                Token tk2 = match(PLUS);
                Operacion O2_s = (Operacion) O_h;
                O2_s.setID(tk2.getKind());
                O2_s.setTipo(tk2.getLexeme());
                O_s = O2_s;
                break;
            case HOOK:
                System.out.println("O-?");
                Token tk3 = match(HOOK);
                Operacion O3_s = (Operacion) O_h;
                O3_s.setID(tk3.getKind());
                O3_s.setTipo(tk3.getLexeme());
                O_s = O3_s;
                break;
            case OR:
            case SYMBOL:
            case LPAREN:
            case SEMI:
            case RPAREN:
                System.out.println("O-ninguno");
                O_s = O_h;
                break;
            default:
                throw new SintaxException(nextToken, expected);
        }
        return O_s;
    }

    /**
     * Analiza el simbolo <AA>
     *
     * @throws SintaxException
     */
    private INodo parseAA(INodo AA_h) throws SintaxException {
        INodo AA_s = null;
        int[] expected = {OR, SEMI, RPAREN};
        switch (nextToken.getKind()) {
            case OR:
                System.out.println("AA-or");
                Token tk1 = match(OR);
                Operacion AA1_s = new Operacion();
                AA1_s.setID(tk1.getKind());
                AA1_s.setTipo(tk1.getLexeme());
                AA_h.addHijo(AA1_s);
                INodo D_s = parseD(AA_h);
                INodo AA2_s = parseAA(D_s);
                AA_s = AA2_s;
                break;
            case SEMI:
            case RPAREN:
                System.out.println("AA-fin");
                AA_s = AA_h;
                break;
            default:
                throw new SintaxException(nextToken, expected);
        }
        return AA_s;
    }

}
