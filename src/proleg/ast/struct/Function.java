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

package proleg.ast.struct;

import java.util.Vector;
import proleg.ast.statement.BlockStatement;

/**
 * Clase que almacena la informaci�n sobre una funci�n
 * 
 * @author Francisco Jos� Moreno Velo
 */
public class Function  {

	//----------------------------------------------------------------//
	//                        Miembros privados                       //
	//----------------------------------------------------------------//

	/**
	 * Identificador de la biblioteca a la que pertenece la funci�n
	 */
	private String libname;
	
	/**
	 * Identificador de la funci�n
	 */
	private String name;
	
	/**
	 * Modificador de acceso
	 */
	private int access;
	
	/**
	 * Tipo de dato que devuelve la funci�n
	 */
	private int type;
	
	/**
	 * Lista de argumentos de la funci�n
	 */
	private Variable[] argument;
	
	/**
	 * Lista de variables locales definidas en la funci�n
	 */
	private Variable[] localVar;
	
	/**
	 * Conjunto de instrucciones del cuerpo de la funci�n
	 */
	private BlockStatement body;
	
	//----------------------------------------------------------------//
	//                            Constructores                       //
	//----------------------------------------------------------------//

	/**
	 * Constructor
	 * 
	 * @param name Nombre de la funci�n
	 * @param classname Nombre de la biblioteca a la que pertenece
	 */
	public Function(int access, int type, String name, String libname) 
	{
		this.access = access;
		this.type = type;
		this.name = name.toString();
		this.libname = libname.toString();
		this.argument = new Variable[0];
		this.localVar = new Variable[0];
		this.body = null;
	}
	
	//----------------------------------------------------------------//
	//                          M�todos p�blicos                      //
	//----------------------------------------------------------------//

	/**
	 * Obtiene el nombre de la funci�n
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	
	/**
	 * Obtiene el modificador de acceso de la funci�n
	 * 
	 * @return
	 */
	public int getAccess() 
	{
		return this.access;
	}
	
	/**
	 * Obtiene el tipo de dato que devuelve la funci�n
	 * @return
	 */
	public int getType() 
	{
		return this.type;
	}
	
	/**
	 * A�ade un argumento a la funci�n
	 * @param var
	 */
	public void addArgument(Variable var) 
	{
		Variable[] aux = new Variable[argument.length+1];
		System.arraycopy(argument,0,aux,0,argument.length);
		aux[argument.length] = var;
		argument = aux;
	}
	
	/**
	 * Introduce la lista de argumentos
	 * @param vector
	 */
	public void addArgumentList(Vector<Variable> vector) 
	{
		Variable[] varg = new Variable[vector.size()];
		vector.copyInto(varg);
		
		Variable[] aux = new Variable[argument.length + varg.length];
		System.arraycopy(argument, 0, aux, 0, argument.length);
		System.arraycopy(varg, 0, aux, argument.length, varg.length);
		this.argument = aux;
	}
	
	/**
	 * Obtiene la lista de argumentos de la funci�n
	 * @return
	 */
	public Variable[] getArguments() 
	{
		return this.argument;
	}
	
	/**
	 * Obtiene la lista de tipos de argumentos
	 */	
	public int[] getArgumentTypes() 
	{
		int[] type = new int[argument.length];
		for (int i = 0; i < argument.length; i++) 
		{
			type[i] = argument[i].getType();
		}
		return type;
	}
	
	/**
	 * Asigna la lista de argumentos (s�lo los tipos)
	 */
	public void setArgumentTypes(int[] argTypes) 
	{
		int size = argTypes.length;
		this.argument = new Variable[size];
		for(int i=0; i<size; i++) 
		{
			argument[i] = new Variable(argTypes[i],"arg"+i);
		}
	}
	
	/**
	 * Compara los tipos de datos con los de la funci�n,
	 * devolviendo true si los tipos de datos coinciden
	 * 
	 * @param name
	 * @param arg
	 * @return 
	 */
	public boolean match(String name, int[] argTypes) 
	{
		if(argTypes.length != argument.length) return false;
		if(!this.name.equals(name)) return false;
		for(int i=0; i<argument.length; i++) 
		{
			if(argument[i].getType() != argTypes[i]) return false;
		}
		return true;
	}
	
	/**
	 * A�ade una declaraci�n de variable local a la funci�n
	 * @param var
	 */
	public void addLocalVariable(Variable var) 
	{
		Variable[] aux = new Variable[localVar.length+1];
		System.arraycopy(localVar,0,aux,0,localVar.length);
		aux[localVar.length] = var;
		localVar = aux;
	}
	
	/**
	 * Obtiene la lista de variables locales
	 * @return
	 */
	public Variable[] getLocalVariables() 
	{
		return this.localVar;
	}
	
	/**
	 * Asigna el bloque de instrucciones de la funci�n
	 * @param blockInst
	 */
	public void setBody(BlockStatement blockInst) 
	{
		this.body = blockInst;
	}
	
	/**
	 * Obtiene el bloque de instrucciones de la funci�n
	 * @return
	 */
	public BlockStatement getBody() 
	{
		return this.body;
	}
	
	/**
	 * Obtiene el nombre de la etiqueta que identifica a la funci�n 
	 * en el c�digo intermedio
	 * @return
	 */
	public String getLabel() 
	{
		String label = libname+"_"+name;
		for(int i=0; i<argument.length;i++) 
		{
			label += "_"+argument[i].getType();
		}
		return label;
	}
}
