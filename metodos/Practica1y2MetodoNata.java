package metodos;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Practica1y2MetodoNata {
    static Scanner leer = new Scanner(System.in);
    static DecimalFormat formato = new DecimalFormat("#,###.##");
    public static void main(String[] args) {
        //
        pantallaInicio();
    }

    public static void pantallaInicio() {
        int orden;
        String pregunta, unidad;
        
        System.out.print("\nPREGUNTA DEL PROBLEMA: ");
        pregunta = leer.next();
        System.out.print("ORDEN: 3");
        orden = 3; //leer.nextInt();
        System.out.println();

        String[] concepto = new String[orden];
        for (int i = 0; i < orden; i++) {
            System.out.print("CONCEPTO DEL PROBLEMA: ");
            concepto[i] = leer.next();
        }
        System.out.print("UNIDAD: ");
        unidad = leer.next(); 

        double[][] matrizA = new double[orden+1][orden + 2];
        datos(matrizA, orden);

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
                    
                    System.out.print("PREGUNTA: " + pregunta + "\n");
                    metodoGaussJordan(matrizA, concepto, orden, unidad);
                    break;
                case 2:
                    System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
                    
                    System.out.print("PREGUNTA: " + pregunta + "\n");
                    //metodoGaussSeidel();
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

    static void metodoGaussJordan(double[][] matrizA, String[] concepto, int orden, String unidad) {
        // Impresión de la matriz capturada
        System.out.println("\nMatriz de Datos:");
        imprimirMatriz(matrizA, orden);

        //Matriz abajo
        System.out.println("\nMatriz ceros abajo de la diagonal:");
        metodoGaussJordanAbajo(matrizA, orden);

        //Matriz arriba
        System.out.println("\nMatriz ceros abajo y arriba de la diagonal:");
        metodoGaussJordanArriba(matrizA, orden);

        //Matriz diagonal
        System.out.println("\nMatriz identidad:");
        metodoGaussJordanUnitaria(matrizA, orden);

        //Resultados
        System.out.println("\nResultados:");
        resultados(matrizA, orden, concepto, unidad);
    }

    static void metodoGaussJordanAbajo(double[][] matrizA, int orden) {
        // // Aplicación del método de Gauss-Jordan
        double pivote, ecero;
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
        // Impresión de la matriz transformada
        imprimirMatriz(matrizA, orden);
    }

    static void metodoGaussJordanArriba(double[][] matrizA, int orden) {
        double pivote, factor;
        //chatgpt
        for (int k = orden; k >= 2; k--) { // Corrección: debe ser de orden a 1
            pivote = matrizA[k][k];
            for (int f = k - 1; f >= 1; f--) {
                factor = matrizA[f][k] / pivote;
                for (int c = k; c <= orden + 1; c++) {
                    matrizA[f][c] = matrizA[f][c] - factor * matrizA[k][c];
                }
            }
        }

        //nata
        // for (int k = orden; k <= 2; k++) {
        //     pivote = matrizA[k][k];
        //     for (int f = 1; f <= k - 1; f++) {
        //         factor = matrizA[f][k] / pivote;
        //         for (int c = k; c <= orden + 1; c++) {
        //             matrizA[f][c] =  matrizA[f][c] - (factor * matrizA[k][c]);
        //         }
        //     }
        // }
        imprimirMatriz(matrizA, orden);
    }

    static void metodoGaussJordanUnitaria(double[][] matrizA, int orden) {
        for (int f = 1; f <= orden; f++) {
            matrizA[f][orden+1] = matrizA[f][orden+1] / matrizA[f][f];
            matrizA[f][f] = matrizA[f][f] / matrizA[f][f];
        }
        imprimirMatriz(matrizA, orden);
    }

    static void imprimirMatriz(double[][] matrizA, int orden) {
        System.out.println("-------------------------------------------------------");
        for (int f = 1; f <= orden; f++) {
            for (int c = 1; c <= orden + 1; c++) {
                System.out.printf("%-10.2f ", matrizA[f][c]);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------");
    }

    static void resultados(double[][] matrizA, int orden, String[] concepto, String unidad) {
        for (int i = 1; i <= orden; i++) {
            System.out.print(concepto[i-1] + " = " + matrizA[i][orden+1] + " " + unidad + ". \n");
        }
    }

// ---------------------------------------------- MÉTODO GAUSS-SEIDEL ----------------------------------------------
    static void metodoGaussSeidel() {
        //
    }
}