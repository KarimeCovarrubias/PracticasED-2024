package metodos;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Practica1y2 {
    static Scanner leer = new Scanner(System.in);
    static DecimalFormat formato = new DecimalFormat("#,###.##");
    public static void main(String[] args) {
        //
        pantallaInicio();
    }

    public static void pantallaInicio() {
        int orden;
        String pregunta, unidad;
        System.out.println("\t\t\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                            "\n\t\t\t\t\tIng. En Sistemas Computacionales" +
                            "\n\nCovarrubias Osuna Dairy Karime." +
                            "\nSolución de ecuaciones." + 
                            "\nDe 12:00 a 13:00 horas." +
                            "\n\n\t\t\t\t\tEste programa ejecuta la solución de problemas" +
                            "\n\t\t\t\t\tutilizando los procesos lógicos de cálculo de Raíces de" +
                            "\n\t\t\t\t\tuna ecuación con diversos métodos numéricos." +
                            "\n\n");
        System.out.println("Un Fabricante de muebles requiere realizar una investigación sobre los tiempos requeridos para el acabado y terminación" + 
                            "\nde cada mueble que fabrica; Mesas, Sillas, y Bancos. Se supervisan los tiempos incurridos en lijar, pintar y barnizar cada mueble.\n" + 
                            "\nSe registra la siguiente informacion de investigacion.\n" + 
                            "\t- Se necesitan 10 minutos para lijar una Mesa, 6 minutos para pintarla y 12 minutos para barnizarla.\n" + 
                            "\t- Se necesitan 12 minutos para lijar una Silla, 8 minutos para pintarla y 12 minutos para barnizarla.\n" +
                            "\t- Se necesitan 15 minutos para lijar un Banco, 12 minutos para pintarlo y 18 minutos para barnizarlo.\n" +
                            "La mesa de lijado se utilizo 16 horas a la semana, la mesa de pintado se utilizo 11 horas a la semana y la mesa de barnizado se utilizo 18 horas a la semana.");
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

        double[][] matrizA = new double[orden][orden + 1];
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
                    System.out.println("\t\t\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                                        "\n\t\t\t\t\tIng. En Sistemas Computacionales" +
                                        "\n\nCovarrubias Osuna Dairy Karime." +
                                        "\nSolución de sistemas de ecuaciones: Método de Gauss-Jordan." + 
                                        "\nDe 12:00 a 13:00 horas." +
                                        "\n");
                    System.out.print("PREGUNTA: " + pregunta + "\n");
                    metodoGaussJordan(matrizA, concepto, orden, unidad);
                    break;
                case 2:
                    System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
                    System.out.println("\t\t\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                                        "\n\t\t\t\t\tIng. En Sistemas Computacionales" +
                                        "\n\nCovarrubias Osuna Dairy Karime." +
                                        "\nSolución de sistemas de ecuaciones: Método de Gauss-Seidel." + 
                                        "\nDe 12:00 a 13:00 horas." +
                                        "\n");
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
        for (int f = 0; f < orden; f++) {
            for (int c = 0; c < orden + 1; c++) {
                System.out.print("Ingrese el valor de x" + (f + 1) + (c + 1) + ": ");
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
        // Aplicación del método de Gauss-Jordan
        double pivote, ecero;
        //ESTO ESTÁ BIEN
        for (int k = 0; k < orden; k++) {
            pivote = matrizA[k][k];
            // Hacer ceros debajo del pivote
            for (int f = k + 1; f < orden; f++) {
                ecero = matrizA[f][k];
                for (int c = 0; c <= orden; c++) {
                    matrizA[f][c] = (pivote * matrizA[f][c]) - (ecero * matrizA[k][c]);
                }
            }
        }

        /* ESTO ESTÁ MAL
         * for (int k = 1; k < orden-1; k++) {
            double pivote = matrizA[k][k];
            
            // Hacer ceros debajo del pivote
            for (int f = k + 1; f < orden; f++) {
                double ecero = matrizA[f][k];
                for (int c = k; c < orden + 1; c++) {
                    pivote = matrizA[k][k];
                    matrizA[f][c] = (pivote * matrizA[f][c]) - (ecero * matrizA[k][c]);
                }
            }
        }
        */
        
        // Impresión de la matriz transformada
        imprimirMatriz(matrizA, orden);
    }

    static void metodoGaussJordanArriba(double[][] matrizA, int orden) {
        double pivote, factor;

        for (int k = orden - 1; k >= 0; k--) {
            pivote = matrizA[k][k];
            for (int f = k - 1; f >= 0; f--) {
                factor = matrizA[f][k] / pivote;
                for (int c = 0; c <= orden; c++) {
                    matrizA[f][c] -= factor * matrizA[k][c];
                }
            }
        }

        //ESTÁ MAL
        // for (int k = orden; k <= 2; k++) {
        //     pivote = matrizA[k][k];
        //     for (int f = 1; f <= k-1; f++) {
        //         factor = matrizA[f][k] / pivote;
        //         for (int c = k; c <= orden+1; c++) {
        //             matrizA[f][c] =  matrizA[f][c] - (factor * matrizA[k][c]);
        //         }
        //     }
        // }
        imprimirMatriz(matrizA, orden);
    }

    static void metodoGaussJordanUnitaria(double[][] matrizA, int orden) {
        double diagonal;
        for (int i = 0; i < orden; i++) {
            diagonal = matrizA[i][i];
            for (int j = 0; j <= orden; j++) {
                matrizA[i][j] /= diagonal;
            }
        }

        //ESTÁ MAL
        // for (int f = 1; f <= orden; f++) {
        //     matrizA[f][orden+1] = matrizA[f][orden+1] / matrizA[f][f];
        //     matrizA[f][f] = matrizA[f][f] / matrizA[f][f];
        // }
        imprimirMatriz(matrizA, orden);
    }

    static void imprimirMatriz(double[][] matrizA, int orden) {
        System.out.println("-------------------------------------------------------");
        for (int f = 0; f < orden; f++) {
            for (int c = 0; c < orden; c++) {
                System.out.printf("%-10.2f ", matrizA[f][c]);
            }
            System.out.printf("| %-10.2f\n", matrizA[f][orden]);
        }
        System.out.println("-------------------------------------------------------");
    }

    static void resultados(double[][] matrizA, int orden, String[] concepto, String unidad) {
        for (int i = 0; i < orden; i++) {
            System.out.print(concepto[i] + " = " + matrizA[i][orden] + " " + unidad + ". \n");
        }
    }

// ---------------------------------------------- MÉTODO GAUSS-SEIDEL ----------------------------------------------
    static void metodoGaussSeidel() {
        //
    }
}