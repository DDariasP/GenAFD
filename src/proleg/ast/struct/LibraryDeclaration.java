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
//                                                                  //
//                  Compilador del lenguaje Tinto                   //
//                                                                  //
//------------------------------------------------------------------//

package proleg.ast.struct;

import java.util.Vector;

import proleg.ast.Access;

/**
 * Clase que almacena la informaci�n sobre la declaraci�n de una biblioteca
 * 
 * @author Francisco Jos� Moreno Velo
 */
public class LibraryDeclaration {

	//----------------------------------------------------------------//
	//                        Miembros privados                       //
	//----------------------------------------------------------------//

	/**
	 * Identificador de la biblioteca
	 */
	private String name;

	/**
	 * Lista de biblitecas importadas
	 */
	private Vector<String> imported;
	
	/**
	 * Lista de funciones
	 */
	private Vector<Function> function;

	/**
	 * Marcador de biblioteca nativa 
	 */
	private boolean isNative;
	
	//----------------------------------------------------------------//
	//                            Constructores                       //
	//----------------------------------------------------------------//

	/**
	 * Constructor de una declaraci�n de biblioteca
	 */
	public LibraryDeclaration(String name) 
	{
		this.name = name.toString();
		this.imported = new Vector<String>();
		this.function = new Vector<Function>();
		this.isNative = false;
	}
	
	//----------------------------------------------------------------//
	//                          M�todos p�blicos                      //
	//----------------------------------------------------------------//

	/**
	 * Verifica si la biblioteca es nativa
	 */
	public boolean isNative()
	{
		return isNative;
	}
	
	/**
	 * Asigna el tipo de biblioteca (nativa o normal)
	 */
	public void setNative(boolean b)
	{
		this.isNative = b;
	}
	
	/**
	 * Obtiene el nombre de la biblioteca
	 */
	public String getName() 
	{
		return this.name;
	}
	
	/**
	 * A�ade una biblioteca a la lista de bibliotecas importadas
	 */
	public void addImportedLibrary(String libname) 
	{
		if(!imported.contains(libname)) this.imported.add(libname);
	}
	
	/**
	 * Asigna la lista de bibliotecas importadas
	 */
	public void setImportedList(Vector<String> imported) 
	{
		this.imported = imported;
	}

	/**
	 * Obtiene la lista de bibliotecas importadas
	 */
	public String[] getImported() 
	{
		Object[] obj = imported.toArray();
		String[] imp = new String[obj.length];
		for(int i=0; i<imp.length; i++) imp[i] = (String) obj[i];
		return imp;
	}

	/**
	 * A�ade la definici�n de una funci�n
	 */
	public void addFunction(Function function) 
	{
		this.function.add(function);
	}
	
	/**
	 * Obtiene la lista de funciones p�blicas y privadas
	 */
	public Function[] getFunctions() 
	{
		Object[] obj = function.toArray();
		Function[] all = new Function[obj.length];
		for(int i=0; i<all.length; i++) all[i] = (Function) obj[i];
		return all;
	}
	
	/**
	 * Busca una funci�n por su nombre y tipos de argumento
	 * @param name Nombre de la funci�n
	 * @param type Lista de tipos de los argumentos
	 */
	public Function getAnyFunction(String name,int[] type) 
	{
		for(Function fun : function)
		{
			if(fun.match(name,type) ) return fun;
		}
		return null;
	}
	
	/**
	 * Busca una funci�n p�blica por su nombre y tipos de argumento
	 * @param name Nombre de la funci�n
	 * @param type Lista de tipos de los argumentos
	 */
	public Function getPublicFunction(String name,int[] type) 
	{
		for(Function fun : function)
		{
			if(fun.getAccess() == Access.PUBLIC_ACCESS && fun.match(name,type) ) return fun;
		}
		return null;
	}
	
	/**
	 * Busca si un cierto nombre se encuentra entre las clases importadas
	 */
	public boolean isImported(String importedname) 
	{
		return imported.contains(importedname);
	}
}
