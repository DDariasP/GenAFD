//------------------------------------------------------------------//
//                        COPYRIGHT NOTICE                          //
//------------------------------------------------------------------//
// Copyright (c) 2017, Francisco Jos� Moreno Velo                   //
// All rights reserved.                                             //
//                                                                  //
// Redistribution and use in source and binary forms, with or       //
// without modification, are permitted provided that the following  //
// conditions are met:                                              //
//                                                                  //
// * Redistributions of source code must retain the above copyright //
//   notice, this list of conditions and the following disclaimer.  // 
//                                                                  //
// * Redistributions in binary form must reproduce the above        // 
//   copyright notice, this list of conditions and the following    // 
//   disclaimer in the documentation and/or other materials         // 
//   provided with the distribution.                                //
//                                                                  //
// * Neither the name of the University of Huelva nor the names of  //
//   its contributors may be used to endorse or promote products    //
//   derived from this software without specific prior written      // 
//   permission.                                                    //
//                                                                  //
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND           // 
// CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,      // 
// INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF         // 
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE         // 
// DISCLAIMED. IN NO EVENT SHALL THE COPRIGHT OWNER OR CONTRIBUTORS //
// BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,         // 
// EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED  //
// TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,    //
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND   // 
// ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT          //
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING   //
// IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF   //
// THE POSSIBILITY OF SUCH DAMAGE.                                  //
//------------------------------------------------------------------//

//------------------------------------------------------------------//
//                      Universidad de Huelva                       //
//          Departamento de Tecnolog�as de la Informaci�n           //
//   �rea de Ciencias de la Computaci�n e Inteligencia Artificial   //
//------------------------------------------------------------------//
//                     PROCESADORES DE LENGUAJE                     //
//------------------------------------------------------------------//
//                                                                  //
//                  Compilador del lenguaje Tinto                   //
//                                                                  //
//------------------------------------------------------------------//

package proleg.ast.expression;

import proleg.ast.Type;

/**
 * Clase que describe un operaci�n binaria
 * 
 * @author Francisco Jos� Moreno Velo
 */
public class BinaryExpression extends OperatorExpression {

	//----------------------------------------------------------------//
	//                       Constantes p�blicas                      //
	//----------------------------------------------------------------//

	/**
	 * Operador: AND
	 */
	public static final int AND = 1;

	/**
	 * Operador: OR
	 */
	public static final int OR = 2;

	/**
	 * Operador: PROD
	 */
	public static final int PROD = 3;

	/**
	 * Operador: DIV
	 */
	public static final int DIV = 4;

	/**
	 * Operador: MOD
	 */
	public static final int MOD = 5;

	/**
	 * Operador: PLUS
	 */
	public static final int PLUS = 6;

	/**
	 * Operador: MINUS
	 */
	public static final int MINUS = 7;

	/**
	 * Operador: EQ
	 */
	public static final int EQ = 8;

	/**
	 * Operador: NEQ
	 */
	public static final int NEQ = 9;

	/**
	 * Operador: GT
	 */
	public static final int GT = 10;

	/**
	 * Operador: GE
	 */
	public static final int GE = 11;

	/**
	 * Operador: LT
	 */
	public static final int LT = 12;

	/**
	 * Operador: LE
	 */
	public static final int LE = 13;
	
	//----------------------------------------------------------------//
	//                        Miembros privados                       //
	//----------------------------------------------------------------//

	/**
	 * C�digo del operador
	 */
	private int op;
	
	/**
	 * Primer operando
	 */
	private Expression left;
	
	/**
	 * Segundo operando
	 */
	private Expression right;
	
	//----------------------------------------------------------------//
	//                            Constructores                       //
	//----------------------------------------------------------------//

	/**
	 * Constructor
	 */
	public BinaryExpression(int op, Expression left, Expression right) 
	{
		super(computeType(op,left,right));
		this.op = op;
		this.left = left;
		this.right = right;
	}
	
	//----------------------------------------------------------------//
	//                          M�todos p�blicos                      //
	//----------------------------------------------------------------//

	/**
	 * Obtiene el c�digo del operador
	 * @return
	 */
	public int getOperator() 
	{
		return this.op;
	}
	
	/**
	 * Obtiene la referencia a la expresi�n de la izquierda
	 * @return
	 */
	public Expression getLeftExpression() 
	{
		return this.left;
	}
	
	/**
	 * Obtiene la referencia a la expresi�n de la derecha
	 * @return
	 */
	public Expression getRightExpression() 
	{
		return this.right;
	}

	//----------------------------------------------------------------//
	//                          M�todos privados                      //
	//----------------------------------------------------------------//

	/**
	 * Calcula el tipo de dato de la expresi�n binaria
	 * @param op
	 * @param left
	 * @param right
	 * @return
	 */
	private static int computeType(int op, Expression left, Expression right)
	{
		switch(op)
		{
		case AND:
		case OR:
		case EQ:
		case NEQ:
		case LT:
		case LE:
		case GT:
		case GE:
			return Type.BOOLEAN_TYPE;
		case PLUS:
		case MINUS:
		case PROD:
		case DIV:
		case MOD:
			return left.getType();
		default:
			return Type.MISMATCH_TYPE;
		}
	}
}
