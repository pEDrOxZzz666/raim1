//////////////////////////////////////////////////////////////////////
// Autores: Proyecto Grupal                                         //
//    - Ferrer Nieves, Vicenç.                                      //
//    - Martyna Kasperek, Angelika.                                 //
//    - Ruiz Martínez, Pedro A.                                     //
// Versión: 4.0                                                     //        
// Modificaciones (ENUNCIADO):                                      //
//     - Opciones seleccionadas almacenadas en array bidimensional. //
//     - Muestra cantidada participantes y ponentes por género.     //
//                                                                  //
// Modificaciones própias (control duplicaciones y buffer):         //            
//     - Impide que se dupliquen códigos de registro.               //    
//     - Impide que se dupliquen ID Sesión.                         //
//     - Resuelto problema buffer valores NO ENTERO con espacios.   //
//////////////////////////////////////////////////////////////////////

package proyecto;

import java.util.Scanner;

public class Proyecto_JBCN {

    //DECLARACIÓN DE CONSTANTES//

    //Género
    public static final int MUJER = 1;
    public static final int HOMBRE = 2;
    public static final int NO_RESPONDE = 3;
    public static final String OP_MUJER = "Mujer\t\t";
    public static final String OP_HOMBRE = "Hombre\t\t";
    public static final String OP_NO_RESPONDE = "-\t\t";

    //Tipo de participante
    public static final int GENERAL = 0;
    public static final int EDUCACION = 1;
    public static final int EMPRESA = 2;
    public static final int PRENSA = 3;
    public static final String OP_GENERAL = "General\t\t";
    public static final String OP_EDUCACION = "Educación\t";
    public static final String OP_EMPRESA = "Empresa\t\t";
    public static final String OP_PRENSA = "Prensa\t\t";

    //Sesión
    public static final String SI_SESION = "SI:";
    public static final String NO_SESION = "NO";

    //Mínimos y Máximos (control de errores)
    public static final int COD_MIN = 100;
    public static final int COD_MAX = 2100;
    public static final int GENERO_MIN = 1;
    public static final int GENERO_MAX = 3;
    public static final int TIPO_MIN = 0;
    public static final int TIPO_MAX = 3;
    public static final int SESION_MIN = 0;
    public static final int SESION_MAX = 1;
    public static final int ID_MIN = 4;
    public static final int ID_MAX = 27;
    public static final int EXPERIENCIA_MIN = 0;
    public static final int EXPERIENCIA_MAX = 30;
    public static final int MAX_INTENTOS = 3;
    public static final int SEGUIR_MIN = 0;
    public static final int SEGUIR_MAX = 1;
    public static final int MAX_REGISTROS = 10;
    public static final int MIN_REGISTROS = 1;
    public static final int MAX_IMPRIMIR = 2;
    public static final int MIN_IMPRIMIR = 1;
    public static final int REGISTRO_NULO = 0;
    public static final int OP_IMPRIMIR_MIN = 0;
    public static final int OP_IMPRIMIR_MAX = 1;
    public static final int DOS_DIGITOS = 10;
    public static final int PRIMER_REGISTRO = 0;
    public static final int NUM_CAMPOS = 6;
    public static final int POR_GENERO_MIN = 0;
    public static final int POR_GENERO_MAX = 1;
    
    //Posición campos Arrays
    public static final int CODIGO = 0;
    public static final int GENERO = 1;
    public static final int PARTICIPANTE = 2;
    public static final int SESION = 3;
    public static final int ID_SESION = 4;
    public static final int EXPERIENCIA = 5;
    
    //Mensaje de error
    public static final String FUERA_RANGO = "El dato debe estar comprendido entre: ";
    public static final String NO_ENTERO = "El dato no es un entero.\n";
    public static final String REG_DUPLICADO = "Error, registro duplicado.\n";
    
    //Cabecera
    public static final String CABECERA = "Código\t\tGénero\t\tTipo\t\tSesión\t\tExperiencia";

    //FIN DECLARACIÓN CONSTANTES //
    

    public static void main(String[] args){
        
        //Declaración de variables //
        
        //int
        int seguir, imprimir, i, j, contImprimir, contRegistros, intentos;
        seguir = imprimir = i = j = contImprimir = contRegistros = intentos = 0;
        
        //Contadores de género
        int contMujer, contHombre, contNoResponde, contPonenteHombre,
            contPonenteMujer, contPonenteNoResponde, porGenero = 0;;
        contMujer = contHombre = contNoResponde = contPonenteHombre =
        contPonenteMujer = contPonenteNoResponde = porGenero = 0;        
        
        //String
        String generoTipo, opcionParticipante, sesion;
        generoTipo = opcionParticipante = sesion = "";
        
        //boolean
        boolean tipoCorrecto = true, introducirMas, duplicado = false;     
        
        //Fin declaración variables //
        
        // Declaración de arrays //       
        int [][] registros = new int[MAX_REGISTROS][NUM_CAMPOS];
        int [] aux = new int[NUM_CAMPOS];
        // Fin declaración Arrays //
        
        //Lector Entrada de datos
        Scanner entrada = new Scanner(System.in);
        
        
        // INICIAMOS LA EJECUCIÓN //        
        
        do //Se repetirá mientras "introducirMas" sea true y no superemos el maximo de registros
        {
            introducirMas = false; //Por defecto no queremos introducir más registros.
            
            // BLOQUE DE PREGUNTAS //  
            do //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
            {
                //Ejecución del programa (Entrada Código incripción)
                System.out.print("Entrada de datos\n" +
                                 "----------------\n" +
                                 "Código (100-2100):");
                //Evaluamos si el código es entero y si cumple el rango
                tipoCorrecto = entrada.hasNextInt();
                if(tipoCorrecto)
                {           
                    registros[contRegistros][CODIGO] = entrada.nextInt();
                    
                    // CONTTROL REGISTROS DUPLICADOS                    
                    if(contRegistros > PRIMER_REGISTRO) //Evaluamos a partir del segundo registro.
                    {
                        //Recorremos el array desde el principio hasta el último valor entrado
                        for(i = 0; i < contRegistros; i++)
                        {
                            //Si encontramos coincidencia evaluamos 'duplicado' a true
                            if(registros[i][CODIGO] == registros[contRegistros][CODIGO])
                            {
                                duplicado =  true;
                            }
                        }
                    }
                    // FIN CONTROL DUPLICADOS
                    
                    //Si el valor no esta duplicado, evaluamos rango
                    if(!duplicado)
                    {
                        if((registros[contRegistros][CODIGO] < COD_MIN) || (registros[contRegistros][CODIGO] > COD_MAX))
                        {
                            //Si no cumple el rango 
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + COD_MIN + " y " + COD_MAX + "\n");
                            entrada.nextLine(); // liberamos buffer
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else //Si está duplicado mostramos mensaje y reseteamos boolean
                    {
                        entrada.nextLine(); //liberamos buffer
                        System.out.println(REG_DUPLICADO);
                        tipoCorrecto = false;
                        duplicado = false;
                        intentos++; //incrementamos intentos por cada error
                    }
                }else //si el valor no es entero
                {                      
                    entrada.nextLine(); //liberamos buffer
                    System.out.println(NO_ENTERO); //Mensaje error                   
                    intentos++; //incrementamos intentos por cada error
                }                
                
            }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            
            //Si cumple el rango mostramos el Menu Género
            if(tipoCorrecto)
            {                   
                entrada.nextLine(); //liberamos buffer
                intentos = 0; //Reseteamos el valor de "intentos"
                //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                do
                {
                    //Menu 1 (Género)
                    System.out.print("\n" + 
                                     "Elige género\n" +
                                     "1 - Es Mujer.\n" +
                                     "2 - Es Hombre\n" + 
                                     "3 - No responde\n" + 
                                     "Elige una opción entre (1-2-3):");
                     
                    tipoCorrecto = entrada.hasNextInt();
                    //Evaluamos entero y rango
                    if(tipoCorrecto)
                    {
                        registros[contRegistros][GENERO] = entrada.nextInt();
                        if((registros[contRegistros][GENERO] < GENERO_MIN) || 
                           (registros[contRegistros][GENERO] > GENERO_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + GENERO_MIN + 
                                               " y " + GENERO_MAX + "\n");
                            entrada.nextLine(); //liberamos buffer
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else
                    {
                        entrada.nextLine(); //liberamos buffer
                        System.out.println(NO_ENTERO); //Mensaje error                        
                        intentos++; //incrementamos intentos por cada error
                    }
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            }
            
            //Si cumple el rango mostramos el Menu Participante
            if(tipoCorrecto)
            {
                entrada.nextLine(); //liberamos buffer
                intentos = 0; //Reseteamos el valor de "intentos"
                //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                do
                {
                    //Menu 2 (Tipo Participante)
                    System.out.print("\n" + 
                                     "Tipo de participante\n" +
                                     "0 - General\n" +
                                     "1 - Educación\n" +
                                     "2 - Empresa\n" +
                                     "3 - Prensa\n" +
                                     "Elige una opción entre (0-1-2-3):");
                    //Evaluamos entero y rango
                    tipoCorrecto = entrada.hasNextInt();
                    if(tipoCorrecto)
                    {
                        registros[contRegistros][PARTICIPANTE] = entrada.nextInt();
                        if((registros[contRegistros][PARTICIPANTE] < TIPO_MIN) || 
                           (registros[contRegistros][PARTICIPANTE] > TIPO_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + TIPO_MIN + 
                                               " y " + TIPO_MAX + "\n");
                            entrada.nextLine(); //liberamos buffer
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else
                    {
                        entrada.nextLine(); //liberamos buffer
                        System.out.println(NO_ENTERO); //Mensaje error                        
                        intentos++; //incrementamos intentos por cada error
                    }
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            }   
            
            //Si cumple el rango mostramos el Menu Sesión
            if(tipoCorrecto)
            { 
                entrada.nextLine(); //liberamos buffer
                intentos = 0; //Reseteamos el valor de "intentos"                
                //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                do
                {
                    //Menu 3 (Sesión)
                    System.out.print("\n" +
                                     "¿Da sesión?\n" +
                                     "0 - No\n" +
                                     "1 - Si\n" +
                                     "Elige una opción entre (0-1):");
                    
                    tipoCorrecto = entrada.hasNextInt();
                    //Evaluamos entero y rango
                    if(tipoCorrecto)
                    {                    
                        registros[contRegistros][SESION] = entrada.nextInt();
                        if((registros[contRegistros][SESION] < SESION_MIN) || 
                           (registros[contRegistros][SESION] > SESION_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + SESION_MIN + 
                                               " y " + SESION_MAX + "\n");
                            entrada.nextLine(); //liberamos buffer
                            intentos++; //incrementamos intentos por cada error
                        }
                    }else
                    {
                        entrada.nextLine(); //liberamos buffer
                        System.out.println(NO_ENTERO); //Mensaje error                        
                        intentos++; //incrementamos intentos por cada error
                    }
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
            }
            
            //Si cumple el rango y da sesión, Mostramos Menú ID Sesión
            if(tipoCorrecto)
            {                
                intentos = 0; //Reseteamos el valor de "intentos"
                if(registros[contRegistros][SESION] == SESION_MAX) 
                {
                    entrada.nextLine(); //liberamos buffer
                    //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                    do
                    {
                        //Menu 4 - ID Sesión
                        System.out.print("\nID Sesión (" + ID_MIN + "-" + ID_MAX + "):");
                        //Evaluamos entero y rango
                        tipoCorrecto = entrada.hasNextInt();
                        if(tipoCorrecto)
                        {
                            registros[contRegistros][ID_SESION] = entrada.nextInt();
                            
                            //CONTROL DUPLICADOS
                            if(contRegistros > PRIMER_REGISTRO) //Evaluamos a partir del segundo registro
                            {
                                //Recorremos el array desde el principio hasta el último valor entrado
                                for(i = 0; i < contRegistros; i++)
                                {
                                    //Si encontramos coincidencia evaluamos 'duplicado' a true
                                    if(registros[i][ID_SESION] == registros[contRegistros][ID_SESION])
                                    {
                                        duplicado =  true;
                                    }
                                }
                            }
                            //FIN CONTROL DUPLICADOS
                            
                            //Si el valor no está duplicado evaluamos rango.
                            if(!duplicado)
                            {
                                if((registros[contRegistros][ID_SESION] < ID_MIN) || 
                                   (registros[contRegistros][ID_SESION] > ID_MAX))
                                {
                                    tipoCorrecto = false;
                                    System.out.println(FUERA_RANGO + ID_MIN + 
                                                       " y " + ID_MAX + "\n");
                                    entrada.nextLine(); // liberamos buffer
                                    intentos++; //incrementamos intentos por cada error
                                }
                            }else //Si está duplicado, mostramos mensaje de error
                            {
                                entrada.nextLine(); //liberamos buffer
                                System.out.println(REG_DUPLICADO);
                                tipoCorrecto = false;
                                duplicado = false;
                                intentos++; //incrementamos intentos por cada error
                            }
                        }else //Mensaje error, NO ENTERO.
                        {
                            entrada.nextLine(); //liberamos buffer
                            System.out.println(NO_ENTERO); //Mensaje error                            
                            intentos++; //incrementamos intentos por cada error
                        }

                    }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
                      
                }

            }
            
            //Si cumple el rango mostramos menú experiencia
            if(tipoCorrecto)
            {
                entrada.nextLine(); //liberamos buffer
                intentos = 0; //Reseteamos el valor de "intentos"
                //Repite mientras tipoCorrecto = false e intentos < MAX_INTENTOS
                do
                {
                    //Menú 5 - Años Experiencia
                    System.out.print("\nAños de experiencia (0-30):");
                    tipoCorrecto = entrada.hasNextInt();
                    //Evaluamos entero y rango
                    if(tipoCorrecto)
                    {
                        registros[contRegistros][EXPERIENCIA] = entrada.nextInt();
                        if((registros[contRegistros][EXPERIENCIA] < EXPERIENCIA_MIN) || 
                           (registros[contRegistros][EXPERIENCIA] > EXPERIENCIA_MAX))
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + EXPERIENCIA_MIN + 
                                               " y " + EXPERIENCIA_MAX + "\n");
                            entrada.nextLine(); //liberamos buffer
                            intentos++; //incrementamos el valor de intentos por cada error
                        }
                    }else
                    {
                        entrada.nextLine(); //liberamos buffer
                        System.out.println(NO_ENTERO); //Mensaje error                        
                        intentos++; //incrementamos el valor de intentos por cada error
                    }                    
                    // FIN BLOQUE PREGUNTAS //
                    
                    //Se incrementará 'contRegistros' si TODAS  opciones han sido correctas.
                    if(tipoCorrecto)
                    {
                        contRegistros++;                       
                    }                
                    
                }while((!tipoCorrecto) && (intentos < MAX_INTENTOS));
                
            }
            
            //Si se han superado los intentos (3) por pregunta, mostramos mensaje error
            if(intentos >= MAX_INTENTOS)
            {
                System.out.println("\nDato Incorrecto. Has superado " + intentos + " intentos");
            }
            
            //Preguntamos introducir más registros si 'contRegistros < MAXREGISTROS'.
            if(contRegistros < MAX_REGISTROS)
            {                  
                do //Repite mientras tipoCorrecto = false (en este punto, no evaluamos intentos).
                {
                    System.out.print("\nIntroducir más registros SI(1) - NO(0):");
                    tipoCorrecto = entrada.hasNextInt();
                    //Evaluamos ENTERO y RANGO
                    if(tipoCorrecto)
                    {
                        seguir = entrada.nextInt();                        
                        if((seguir < SEGUIR_MIN) || seguir > SEGUIR_MAX)
                        {
                            tipoCorrecto = false;
                            System.out.println(FUERA_RANGO + SEGUIR_MIN + 
                                               " y " + SEGUIR_MAX + "\n"); 
                            entrada.nextLine(); //liberamos buffer
                        }          
                    }else
                    {
                        entrada.nextLine(); //liberamos buffer
                        System.out.println(NO_ENTERO); //Mensaje error                                        
                    }           

                }while(!tipoCorrecto);
                
                //Evaluamos la respuesta del usuario sobre un nuevo registro
                if(seguir == SEGUIR_MAX) //Si "seguir" es = 1 iniciamos nuevo registro
                {                        
                    entrada.nextLine();
                    introducirMas = true;                        
                    intentos = 0; //Reseteamos "intentos" para el siguiente registro"                        
                    System.out.println(); //salto de línea
                }
                
            }                
           
        }while((introducirMas) && (contRegistros < MAX_REGISTROS));                
        
        
        // BLOQUE DE IMPRESIÓN //
        
        //Evaluamos cuantos registros se han completado y mostramos un mensaje al finalizar el programa.
        if(contRegistros > REGISTRO_NULO) //Si al menos hemos completado un registro...
        {   
            do
            {
                //Según el número de vuelta mostramos una cabecera distinta.
                if(contImprimir < MIN_IMPRIMIR) //Vuelta 1 - Sin ordenar
                {
                    System.out.println("\n\t\t\t  REGISTROS ENTRADOS");
                    System.out.println("---------------------------------------"+
                                       "------------------------------------\n" +
                                       CABECERA + "\n" +
                                       "---------------------------------------"+
                                       "------------------------------------");
                }else //Vuelta 2 - Ordenamos por experiencia.
                {
                    System.out.printf("\n%52s\n","REGISTROS ORDENADOS POR EXPERIENCIA");
                    System.out.println("---------------------------------------"+
                                       "------------------------------------\n" +
                                       CABECERA + "\n" +
                                       "---------------------------------------"+
                                       "------------------------------------");
                    
                }                    
                
                //Recorremos opciones elegidas usuario y asignamos valor.
                for(i = 0; i < contRegistros; i++)
                {
                    //Asignamos valor array 'generos' a 'generoTipo'.
                    switch(registros[i][GENERO])
                    {
                        case MUJER:
                            generoTipo = OP_MUJER;
                            break;

                        case HOMBRE:
                            generoTipo = OP_HOMBRE;
                            break;

                        case NO_RESPONDE:
                            generoTipo = OP_NO_RESPONDE;
                            break;
                    } 
   
                    //Asignamos valor array 'participantes' a 'opcionParticipante'.
                    switch(registros[i][PARTICIPANTE])
                    {
                        case GENERAL:
                            opcionParticipante = OP_GENERAL;
                            break;

                        case EDUCACION:
                            opcionParticipante = OP_EDUCACION;
                            break;

                        case EMPRESA:
                            opcionParticipante = OP_EMPRESA;
                            break;

                        case PRENSA:
                            opcionParticipante = OP_PRENSA;
                    }

                    //Asignamos valor array 'daSesiones' a 'sesion'.
                    switch(registros[i][SESION])
                    {
                        case SESION_MIN:
                            sesion = NO_SESION;                      
                            break;

                        case SESION_MAX:
                            sesion = SI_SESION; 
                            break; 
                    }

                    //Imprimimos los resultados                           
                    if(sesion == SI_SESION)//Si da sesión, mostramos los datos CON ID sesión
                    {              
                        // Si idSesión es < 10 añadimos un "0" delante para que muestre 2 dígitos
                        if(registros[i][ID_SESION] < DOS_DIGITOS)
                        {
                            System.out.println(registros[i][CODIGO] + "\t\t" + generoTipo + 
                                               opcionParticipante + sesion + "0" + registros[i][ID_SESION] + 
                                               "\t\t" + registros[i][EXPERIENCIA] + "\n"); 
                        }else //si >10 no añadimos nada...
                        {                            
                            System.out.println(registros[i][CODIGO] + "\t\t" + generoTipo + 
                                               opcionParticipante + sesion + registros[i][ID_SESION] + 
                                               "\t\t" + registros[i][EXPERIENCIA] + "\n");
                        }                    
                    }else //Si NO da sesión, mostramos los datos SIN ID sesión
                    {                        
                        System.out.println(registros[i][CODIGO] + "\t\t" + generoTipo + 
                                           opcionParticipante + sesion + "\t\t" + 
                                           registros[i][EXPERIENCIA] + "\n");                     
                    }
                    
                }
                
                System.out.println(); //salto de línea
                
                //Mostramos mensaje una vez y si hay + de un registro
                if((contImprimir < MIN_IMPRIMIR) && (contRegistros > MIN_REGISTROS))
                {
                    entrada.nextLine();
                    do
                    {
                        //Preguntamos si quiere que se muestre los valores ordenador por experiencia <>
                        System.out.print("¿Quieres mostrar los datos ordenados " +
                                         "por experiencia de <>? Si(1)-No(0): ");
                        tipoCorrecto = entrada.hasNextInt();
                        //Evaluamos Entero y Rango.
                        if(!tipoCorrecto)
                        {
                            entrada.nextLine(); //liberamos buffer
                            System.out.println("Error, el dato debe ser un entero.\n");
                            
                        }else
                        {
                            imprimir = entrada.nextInt();                            
                            if((imprimir < OP_IMPRIMIR_MIN) || (imprimir > OP_IMPRIMIR_MAX))
                            {
                                System.out.println("Error, el valor de estran entre " + 
                                                   OP_IMPRIMIR_MIN + "-" + OP_IMPRIMIR_MAX + "\n");
                                entrada.nextLine(); //Liberamos buffer
                                tipoCorrecto = false;
                            }
                        }
                        
                    }while(!tipoCorrecto);
                    
                }   

                //Evaluamos la respuesta del usuario en el ordenamiento de los registros
                if(imprimir == OP_IMPRIMIR_MIN) // si no quiere ordenarlos, mostramos SIN ORDENAR y salimos bucle.
                {
                   tipoCorrecto = false; 
                }
                else if(imprimir == OP_IMPRIMIR_MAX) //Si quiere ordenarlos, mostramos registros ordenados por experiencia.
                {
                    contImprimir++; //Incrementamos 'contImprimir' para ejecución segunda vuelta.
                    
                    
                    // ORDENAMOS POR EXPERIENCIA 
                    
                    //Recorremos array y comparamos 'anosExperiencia[i]' con 'anosExperiencia[j]'.  
                    for(i = 0; i < contRegistros; i++)
                    {
                        for(j = i + 1; j < contRegistros; j++)
                        {
                            //Evaluamos si 'anosExperiencia[i]' es > que 'anosExperiencia[j], si es así, realizamos el cambio.
                            if(registros[i][EXPERIENCIA] > registros[j][EXPERIENCIA])
                            {
                                aux = registros[i];
                                registros[i] = registros[j];
                                registros[j] = aux;
                            }
                        }
                    }
                } 
                
                // FIN ORDENAMIENTO 
                
            }while((contImprimir < MAX_IMPRIMIR) && (tipoCorrecto)); 
            
            // FIN BLOQUE DE IMPRESIÓN //
            
                      
            //Mostramos los registros entrados
            System.out.println("\nSe han incrito: " + contRegistros + 
                               " participante/s nuev@/s");
            
        }else //Si no se finaliza ningún registro, mostramos el mensaje
        {
           System.out.println("\nNo se ha registrado ningún participante."); 
        }         
        
        //Si se ha introducido mas de un registros preguntamos mostrar registros por género.
        if(contRegistros > MIN_REGISTROS)
        {            
            entrada.nextLine();
            do{
                System.out.print("\n¿Quieres ver los registros por género y ponencia? Si(1)-No(0): ");
                tipoCorrecto = entrada.hasNextInt();
                if(tipoCorrecto)
                {
                    porGenero = entrada.nextInt();
                    if((porGenero >= POR_GENERO_MIN) && (porGenero <= POR_GENERO_MAX))
                    {
                        tipoCorrecto = true;
                    }else
                    {
                        entrada.nextLine();
                        System.out.println(FUERA_RANGO);                        
                        tipoCorrecto = false;
                    }
                }else
                {
                    entrada.nextLine();
                    System.out.println(NO_ENTERO);                    
                }
                
            }while(!tipoCorrecto);
            
            //Si usuario elige mostrar participantes/ponentes por género
            if(porGenero == POR_GENERO_MAX)
            {                
                //Mostramos participantes por género
                for(i = 0; i < registros.length; i++)
                {                    
                    switch(registros[i][GENERO])
                    {
                        //Si es mujer incrementamos 'contMujer'
                        case MUJER: 
                        contMujer++;
                        //Si es mujer y ponente, incrementamos 'contPonenteMujer'
                        if(registros[i][SESION] == SESION_MAX)
                        {
                            contPonenteMujer++;
                        }
                        break;
                        //Si es hombre, incrementamos 'contHombre'    
                        case HOMBRE:
                        contHombre++;
                        //Si es hombre y ponente, incrementamos 'contPonenteHombre'
                        if(registros[i][SESION] == SESION_MAX)
                        {
                            contPonenteHombre++;
                        }
                        break;
                        //Si no responde, incrementamos 'contNoResponde'    
                        case NO_RESPONDE: 
                        contNoResponde++;
                        //Si no responde y es ponente, incrementamos 'contPonenteNoResponde'.
                        if(registros[i][SESION] == SESION_MAX)
                        {
                            contPonenteNoResponde++;
                        }
                        break;
                    }
                }
                //Imprimimos los resultados
                System.out.printf("\n %32s\n %s\n %-15s %-15s %-15s\n %s\n %-15d %-15d %-15d\n",
                                  "PARTICIPANTES POR GÉNERO",  
                                  "-------------------------------------------",
                                  "Hombre", "Mujer", "No responde", 
                                  "-------------------------------------------",
                                  contHombre, contMujer, contNoResponde);
                
                System.out.printf("\n %38S\n %s\n %-15s %-15s %-15s\n %s\n %-15d %-15d %-15d\n",
                                  "Participantes ponentes por género",  
                                  "-------------------------------------------",
                                  "Hombre", "Mujer", "No responde", 
                                  "-------------------------------------------",
                                  contPonenteHombre, contPonenteMujer, contPonenteNoResponde);
            }
            
        }
        
        // FIN DE LA EJECUCIÓN //
        
    }

}
