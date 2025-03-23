package metodos;

import java.text.DecimalFormat; 
import java.util.Scanner;

public class Practica3 {
	public static void main(String []args) {
		Scanner leer=new Scanner(System.in);
		System.out.println("\n"+"Instituto Tecnologico de Culiacan");
		System.out.println("\n"+"Ingenieria en Sistemas Computacionales");
		System.out.println("Valenzuela Duarte Hannia Lucero");
		System.out.println("Solucion de Sistemas de Ecuaciones");
		System.out.println("De 12:00/13:00 horas");
		System.out.println("\t"+"Este programa ejecuta la solucion de problemas utilizando"+
						   "los procesos logicos de la solucion de sistemas de ecuaciones"+
				           "utilizando diversos metodos numericos. ");
		
		System.out.println("------------------------------MENÚ-----------------------------------");
		int opcion;
        do {
            System.out.println("\t1. Gauss- Jordan" +
                    "\t2. Seidel" +
                    "\t3. Salir");
            System.out.print("Ingresa la opción con la que deseas resolver el problema: ");
            opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                	System.out.println("===========================================");
            		System.out.println("\n"+"Instituto Tecnologico de Culiacan");
            		System.out.println("\n"+"Ingenieria en Sistemas Computacionales");
            		System.out.println("Valenzuela Duarte Hannia Lucero");
            		System.out.println("Solucion de Sistemas de Ecuaciones");
            		System.out.println("De 12:00/13:00 horas");
            		System.out.println("Pregunta: ");
            	    String pregunta=leer.nextLine();
            		System.out.println("===========================================");
                    Gauss();
                    break;
                case 2:
                	System.out.println("===========================================");
            		System.out.println("\n"+"Instituto Tecnologico de Culiacan");
            		System.out.println("\n"+"Ingenieria en Sistemas Computacionales");
            		System.out.println("Valenzuela Duarte Hannia Lucero");
            		System.out.println("Solucion de Sistemas de Ecuaciones");
            		System.out.println("De 12:00/13:00 horas");
            		System.out.println("Pregunta: ");
            		String Pregunta=leer.nextLine();
            		System.out.println("===========================================");
                    Seidel();
                    break;
                case 10:
                    System.out.println("Salir");
                break;
                default:
                    System.out.println("Ingresa una opcion correcta");
            }
        } while (opcion != 3);
    }
	//Metodo de Gauss
	public static void Gauss() {
		Scanner leer=new Scanner (System.in);
		double[][] MatrizA; 
	    double[][] MatrizB; 
	    String[] Conceptos;
	    double ecero, factor;
	       
	    System.out.println("Unidad de medida a utilizar: "); 
	    String unidad = leer.nextLine(); 
	    System.out.println("Teclee el orden de la matriz: "); 
	    int orden = leer.nextInt();

	    if (orden < 2 || orden > 6) { 
	        System.out.println("Orden fuera de rango"); 
	    }
	    Conceptos = new String[orden];

	    for (int f = 0; f < orden; f++) { 
	        System.out.println("Ingresa los conceptos: " + (f + 1)); 
	        Conceptos[f] = leer.next(); 
	        leer.nextLine();
	    }

	    MatrizA = new double[orden][orden + 1]; 
	    MatrizB = new double[orden][orden + 1]; 
	    System.out.println("Ingrese los valores de la matriz:"); 
	    for (int f = 0; f < orden; f++) { 
	        for (int c = 0; c < orden + 1; c++) { 
	            System.out.print("(" + (f + 1) + "," + (c + 1) + "):"); 
	            MatrizA[f][c] = leer.nextDouble();
	            MatrizB[f][c] = MatrizA[f][c];   
	        } 
	    }
	    double piv = 1.0; 
	    for (int k = 0; k < orden; k++) { 
	        for (int f = 0; f < orden; f++) { 
	            for (int c = 0; c <= orden; c++) { 
	                if (f != k && c != k) { 
	                    MatrizB[f][c] = ((MatrizB[k][k] * MatrizB[f][c]) - (MatrizB[f][k] * MatrizB[k][c])) / piv;  
	                } 
	            } 
	        }
			piv = MatrizB[k][k]; 
			for (int f = 0; f < orden; f++) { 
				if (f != k) { 
					MatrizB[f][k] = 0.0; 
				}	
			}
	    }

	    for (int k = 0; k < orden; k++) { 
	        MatrizB[k][orden] = MatrizB[k][orden] / MatrizB[k][k]; 
	        MatrizB[k][k] = MatrizB[k][k] / MatrizB[k][k];
		}
	    System.out.println("-------------------------------------------------");
	        
	    System.out.println("Matriz de Datos:"); 
	    System.out.printf("%-10s %-10s %-10s %-10s%n", "x1", "x2", "x3", "x4");
	    for (int f = 0; f < orden; f++) { 
	        for (int c = 0; c < orden + 1; c++) { 
	            System.out.printf("%-10s", MatrizA[f][c]); 
	        }
	        System.out.println();
	    }

	    //Matriz abajo
	    System.out.println("\nMatriz ceros abajo de la diagonal:");
	    for (int k = 1; k <= orden-1; k++) {
	        piv = MatrizA[k][k];
	        for (int f = k + 1; f <= orden; f++) {
	            ecero = MatrizA[f][k];
	            for (int c = k; c <= orden + 1; c++) {
	                piv = MatrizA[k][k];
	                MatrizA[f][c] = (piv * MatrizA[f][c]) - (ecero * MatrizA[k][c]);
	            }
	        }
	    }
	    imprimirMatriz(MatrizA, orden);

	    //Matriz arriba
	    System.out.println("\nMatriz ceros abajo y arriba de la diagonal:");
	    for (int k = orden; k >= 2; k--) {
	        piv = MatrizA[k][k];
	        for (int f = k - 1; f >= 1; f--) {
	            factor = MatrizA[f][k] / piv;
	            	for (int c = k; c <= orden + 1; c++) {
	                MatrizA[f][c] = MatrizA[f][c] - factor * MatrizA[k][c];
	            }
	        }
	    }
	    imprimirMatriz(MatrizA, orden);

	    //Matriz diagonal
	    System.out.println("\nMatriz identidad:");
	    for (int f = 1; f <= orden; f++) {
	        MatrizA[f][orden+1] = MatrizA[f][orden+1] / MatrizA[f][f];
	        MatrizA[f][f] = MatrizA[f][f] / MatrizA[f][f];
	    }
	    imprimirMatriz(MatrizA, orden);
	        
	    System.out.println("-------------------------------------------------"); 
	    System.out.println();
	    System.out.println("Matriz Transformada:"); 
	    System.out.printf("%-10s %-10s %-10s %-10s%n", "x1", "x2", "x3", "x4"); 
	    for (int f = 0; f < orden; f++) {
	        for (int c = 0; c < orden + 1; c++) { 
	        	System.out.printf("%-10s", MatrizB[f][c]); 
	        }
	        System.out.println(); 
	    }

	    System.out.println("------------------------------------------------"); 
	    System.out.println("Resultados:"); 
	    for (int f = 0; f < orden; f++) { 
	        System.out.println(Conceptos[f] + " = " + MatrizB[f][orden] + " " +unidad); 
	    }
	}

	public static void imprimirMatriz(double[][] MatrizA, int orden) {
        System.out.println("-------------------------------------------------------");
        for (int f = 1; f <= orden; f++) {
            for (int c = 1; c <= orden; c++) {
                System.out.printf("%-10.2f ", MatrizA[f][c]);
            }
            System.out.print("| ");
            System.out.printf("%-20.2f\n", MatrizA[f][orden + 1]);
        }
        System.out.println("-------------------------------------------------------");
    }

	////////////////////////////////////////////////////////////////////////////////////////////
	public static void Seidel() {
		Scanner leer=new Scanner(System.in);
		DecimalFormat formato = new DecimalFormat("#0.000000"); 
		System.out.println(" "); 
		System.out.println("Ingrese los siguientes datos: "); 
		int orden;
        
		do { 
			System.out.println("Elija el orden de la matriz:"); 
			orden = leer.nextInt(); 
		} while (orden < 2 || orden > 6);

		double[] Vant = new double[orden]; 
		double[] Vact = new double[orden]; 
		String[] Concepto = new String[orden]; 
		double[][] Matriz = new double[orden][orden + 1];

		for (int i = 0; i < orden; i++) { 
			System.out.println("Ingrese el concepto de: " + (i +1)); 
			Concepto[i] = leer.next();
		}

        for (int i = 0; i < orden; i++) { 
            System.out.println("Ingrese el valor inicial " + (i + 1) +":");
            Vant[i] = leer.nextDouble(); 
        }

        System.out.print("Ingrese el Error: "); 
        Double Error = leer.nextDouble();
        System.out.print("Ingrese el maximo de calculos: "); 
        int Tc = leer.nextInt();
		System.out.println("------------------------------------------------------------------");

		for (int i = 0; i < orden; i++) {
            for (int n = 0; n < orden + 1; n++) { 
                System.out.println("Inserte el dato " + "[" + (i + 1) + "]" + "[" + (n + 1) + "]"); 
                Matriz[i][n] = leer.nextDouble(); 
            } 
        }

        int m = 0; 
        double ErrorTotal = 0; 
        for (int L = 0; L < orden; L++) { 
            Vact[L] = 0; 
		}

		System.out.println(""); 
		do { 
		    for (int O = 0; O < orden; O++) {
		        double Suma = 0; 
		        double Coef = Matriz[O][O]; 
		        Suma = Suma + Matriz[O][orden]; 
		        for (int S = 0; S < orden; S++) { 
		            if (O == S) {
		            } else if (S < O) { 
		                Suma = Suma + ((Matriz[O][S] * -1) * Vact[S]); 
		                } else { 
		                    Suma = Suma + ((Matriz[O][S] * -1) * Vant[S]); 
		                } 
		        }
		        Suma = Suma / Coef; 
		        Vact[O] = Suma; 
		    }

		    ErrorTotal = 0; 
		    for (int E = 0; E < orden; E++) { 
		        ErrorTotal = ErrorTotal + Math.abs(Math.abs(Vact[E]) - Math.abs(Vant[E])); 
		    }

		    if (m == 0) { 
		        for (int i = 0; i < orden; i++) { 
		            System.out.print("\t|\t\t" + Concepto[i]); 
		        } 
		    }

		    System.out.println(""); 
		    m = m + 1; 
		    System.out.print(m + "\t|");
		    for (int p = 0; p < orden; p++) { 
		        System.out.print("\t" + formato.format(Vant[p]) + "\t|"); 
                Vant[p] = Vact[p]; 
		    } 
		    System.out.println("\t" + formato.format(ErrorTotal) + "\t|"); 
		} while (ErrorTotal > Error && m < Tc);

		if (ErrorTotal <= Error) { 
		    System.out.println("\nResultados:"); 
		    for (int p = 0; p < orden; p++) { 
		        System.out.println(Concepto[p] + "=   " +formato.format(Vant[p])); 
		    } 
		} else { 
		    System.out.println("El proceso supero el numero maximo de calculos y no encontro la mejor aproximacion."); 
		}
	}
}