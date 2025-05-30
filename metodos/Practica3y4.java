package metodos;

import java.util.Scanner;
import java.text.DecimalFormat;
import metodos.Keyboard;

public class Practica3y4 {
    static Scanner leer = new Scanner(System.in);
    static DecimalFormat formato = new DecimalFormat("###,##0.000");
    public static void main(String[] args) {
        System.out.println("\n\n\t\t\t\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                            "\n\t\t\t\t\t\tIng. En Sistemas Computacionales" +
                            "\n\nCovarrubias Osuna Dairy Karime." +
                            "\nSolución de ecuaciones." + 
                            "\nDe 12:00 a 13:00 horas. \n");
        System.out.printf("\t\t\t\t\t%s%n \t\t\t\t\t%s%n \t\t\t\t\t%s%n", "Este programa ejecuta la solución de problemas",
                            "utilizando los procesos lógicos de Solución de sistemas",
                            "de Ecuaciones utilizando diversos métodos numéricos.");
        pantallaInicio();
    }

    public static void pantallaInicio() {
        int orden;
        String pregunta, unidad;
        
        System.out.print("\nPREGUNTA DEL PROBLEMA: ");
        pregunta = Keyboard.readString();
        System.out.print("ORDEN: ");
        orden = leer.nextInt();
        System.out.println();

        String[] concepto = new String[orden + 1];
        for (int i = 1; i <= orden; i++) {
            System.out.print("CONCEPTO DEL PROBLEMA: ");
            concepto[i] = Keyboard.readString();
        }
        System.out.print("UNIDAD: ");
        unidad = Keyboard.readString(); 

        double[][] matrizA = new double[orden+1][orden + 2];

        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
        menu(matrizA, concepto, orden, unidad, pregunta);
    }

    static void menu(double[][] matrizA, String[] concepto, int orden, String unidad, String pregunta) {
        int opc;
        do {
            System.out.println("\n\t\t\t\t-----------------------------------MENÚ-----------------------------------");
            System.out.println("1.MÉTODO DE GAUSS-JORDAN \n2.MÉTODO DE GAUSS-SEIDEL \n10.FIN");
            System.out.print("ESCOJA UNA OPCIÓN: ");
            opc = leer.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
                    metodoGaussJordan(matrizA, concepto, orden, unidad, pregunta);
                    break;
                case 2:
                    System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
                    metodoGaussSeidel(orden, pregunta, concepto, unidad);
                    break;
                case 10:
                    System.out.println("¡ADIÓS!");
                    break;
                default:
                    System.out.println("OPCIÓN INVÁLIDA. ESCOJA OTRA OPCIÓN.");
                break;
            }
        } while (opc != 10);
    }

    static void datos(double[][] matrizA, int orden) {
        // Llenado de la matriz
        System.out.println();
        for (int f = 1; f <= orden; f++) {
            for (int c = 1; c <= orden + 1; c++) {
                System.out.print("Ingrese el valor de x" + (f) + (c) + ": ");
                matrizA[f][c] = leer.nextDouble();
            }
        }
    }

// ---------------------------------------------- MÉTODO GAUSS-JORDAN ----------------------------------------------    
    static void metodoGaussJordan(double[][] matrizA, String[] concepto, int orden, String unidad, String pregunta) {
        double pivote, ecero, factor;

        // Introducir datos
        datos(matrizA, orden);

        System.out.println("\n\n\t\t\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                            "\n\t\t\t\t\tIng. En Sistemas Computacionales" +
                            "\n\nCovarrubias Osuna Dairy Karime." +
                            "\nSolución de ecuaciones." + 
                            "\nDe 12:00 a 13:00 horas." + "\nMétodo de Gauss-Jordan.\n");
        System.out.printf("\t\t\t\t\t%s%n \t\t\t\t\t%s%n \t\t\t\t\t%s%n", "Este programa ejecuta la solución de problemas",
                            "utilizando los procesos lógicos de Solución de sistemas",
                            "de Ecuaciones utilizando diversos métodos numéricos.\n");
        System.out.print("PREGUNTA: ¿" + pregunta + "?\n");

        // Impresión de la matriz capturada
        System.out.println("\nMatriz de Datos:");
        imprimirMatriz(matrizA, orden);

        //Matriz abajo
        System.out.println("\nMatriz ceros abajo de la diagonal:");
        for (int k = 1; k <= orden-1; k++) {
            pivote = matrizA[k][k];
            for (int f = k + 1; f <= orden; f++) {
                ecero = matrizA[f][k];
                for (int c = k; c <= orden + 1; c++) {
                    pivote = matrizA[k][k];
                    matrizA[f][c] = (pivote * matrizA[f][c]) - (ecero * matrizA[k][c]);
                }
            }
        }
        imprimirMatriz(matrizA, orden);

        //Matriz arriba
        System.out.println("\nMatriz ceros abajo y arriba de la diagonal:");
        for (int k = orden; k >= 2; k--) {
            pivote = matrizA[k][k];
            for (int f = k - 1; f >= 1; f--) {
                factor = matrizA[f][k] / pivote;
                for (int c = k; c <= orden + 1; c++) {
                    matrizA[f][c] = matrizA[f][c] - factor * matrizA[k][c];
                }
            }
        }
        imprimirMatriz(matrizA, orden);

        //Matriz diagonal
        System.out.println("\nMatriz identidad:");
        for (int f = 1; f <= orden; f++) {
            matrizA[f][orden+1] = matrizA[f][orden+1] / matrizA[f][f];
            matrizA[f][f] = matrizA[f][f] / matrizA[f][f];
        }
        imprimirMatriz(matrizA, orden);

        //Resultados
        System.out.println("\nResultados:");
        for (int i = 1; i <= orden; i++) {
            System.out.printf("%s = %s %s. \n", concepto[i], formato.format(matrizA[i][orden+1]), unidad);
        }
    }

    static void imprimirMatriz(double[][] matrizA, int orden) {
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for (int f = 1; f <= orden; f++) {
            for (int c = 1; c <= orden; c++) {
                System.out.printf("%-20s ", formato.format(matrizA[f][c]));
            }
            System.out.print("| ");
            System.out.printf("%-20s\n", formato.format(matrizA[f][orden + 1]));
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------");
    }

// ---------------------------------------------- MÉTODO GAUSS-SEIDEL ----------------------------------------------
    static void metodoGaussSeidel(int orden, String pregunta, String[] concepto, String unidad) {
        double[] vant = new double[orden + 1];
        double[] vact = new double[orden + 1];
        double[][] matriz = new double[orden + 1][orden + 2];

        for (int f = 1; f <= orden; f++) {
            System.out.print("Ingrese el valor inicial " + f + ": ");
            vant[f] = leer.nextDouble();
            vact[f] = 0;
        }
        System.out.print("Ingrese el error: ");
        double error = leer.nextDouble();
        System.out.print("Ingrese el total de cálculos: ");
        int tc = leer.nextInt();
        System.out.println("------------------------------------------------------------------");

        datos(matriz, orden);

        // Intercambiar filas para hacer dominante la diagonal
        for (int i = 1; i <= orden; i++) {
            int dominante = i;
            for (int j = i + 1; j <= orden; j++) {
                if (Math.abs(matriz[j][i]) > Math.abs(matriz[dominante][i])) {
                    dominante = j;
                }
            }
            if (dominante != i) {
                double[] temp = matriz[i];
                matriz[i] = matriz[dominante];
                matriz[dominante] = temp;
            }
        }

        System.out.println("\n\n\t\t\t\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                            "\n\t\t\t\t\t\tIng. En Sistemas Computacionales" +
                            "\n\nCovarrubias Osuna Dairy Karime." +
                            "\nSolución de ecuaciones." + 
                            "\nDe 12:00 a 13:00 horas." + "\nMétodo de Gauss-Seidel.\n");
        System.out.printf("\t\t\t\t\t%s%n \t\t\t\t\t%s%n \t\t\t\t\t%s%n", "Este programa ejecuta la solución de problemas",
                            "utilizando los procesos lógicos de Solución de sistemas",
                            "de Ecuaciones utilizando diversos métodos numéricos.\n");
        System.out.print("PREGUNTA: ¿" + pregunta + "?\n");

        System.out.println();
        int nc = 0;
        double errorTotal = 0;

        System.out.println("Valores iniciales:");
        for (int f = 1; f <= orden; f++) {
            System.out.println("x"+ f + " = " + vant[f]);
        }
        System.out.println("Error Total = " + errorTotal);

        do {
            for (int f = 1; f <= orden; f++) {
                double suma = 0;
                double coef = matriz[f][f];
                suma = suma + matriz[f][orden + 1];
                for (int c = 1; c <= orden; c++) {
                    if (f == c) {
                    } else if (c < f) {
                        suma = suma + ((matriz[f][c] * -1.0) * vact[c]);
                    } else {
                        suma = suma + ((matriz[f][c] * -1.0) * vant[c]);
                    }
                }
                suma = suma / coef;
                vact[f] = suma;
            }

            errorTotal = 0;
            for (int p = 1; p <= orden; p++) {
                errorTotal = errorTotal + Math.abs(Math.abs(vact[p]) - Math.abs(vant[p]));
            }

            if (nc == 0) {
                System.out.println("\n-------------------------------------------------------------------------------------------------");
                System.out.printf("%-5s", "#");
                for (int i = 1; i <= orden; i++) {
                    System.out.printf(" | %-15s", concepto[i]);
                } 
                System.out.printf(" | %-15s |\n", "Error Total");
                System.out.println("-------------------------------------------------------------------------------------------------");
            }

            System.out.println("");
            nc = nc + 1;

            System.out.printf("%-5d", nc);
            for (int p = 1; p <= orden; p++) {
                System.out.printf(" | %-15s", formato.format(vact[p]));
                vant[p] = vact[p];
            }
            System.out.printf(" | %-15s |\n", formato.format(errorTotal));
        } while (errorTotal > error && nc <= tc);
        System.out.println("-------------------------------------------------------------------------------------------------");

        if (errorTotal <= error) {
            System.out.println("\nResultados:");
            for (int p = 1; p <= orden; p++) {
                System.out.println(concepto[p] + " = " + formato.format(vact[p]) + " = " + formato.format(Math.round(vact[p])) + " " + unidad+ ".");
            }
        } else {
            System.out.println("El proceso superó el número máximo de cálculos y no encontró la mejor aproximación.");
        }
    }
}