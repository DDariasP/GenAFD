
  /* Expresión regular que describe un comentario multilinea: b=barra, a=asterisco, o=otro */

  Comment ::= 'b' 'a' ( ('a')* 'o' | 'b' )* ('a')+ 'b' ;
