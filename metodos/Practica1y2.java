package metodos;

import java.util.Scanner;

public class Practica1y2 {
    static Scanner scanner = new Scanner(System.in);
    static int orden;
    static String pregunta, concepto, unidad;
    public static void main(String[] args) {
        //
        metodoGaussJordan();
    }

    public static void pantallaInicio() {
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
                            "de cada mueble que fabrica; Mesas, Sillas, y Bancos. Se supervisan los tiempos incurridos en lijar, pintar y barnizar cada mueble.\n" + 
                            "Se registra la siguiente informacion de investigacion.\n" + 
                            "\t- Se necesitan 10 minutos para lijar una Mesa, 6 minutos para pintarla y 12 minutos para barnizarla.\n" + 
                            "\t- Se necesitan 12 minutos para lijar una Silla, 8 minutos para pintarla y 12 minutos para barnizarla.\n" +
                            "\t- Se necesitan 15 minutos para lijar un Banco, 12 minutos para pintarlo y 18 minutos para barnizarlo.\n" +
                            "La mesa de lijado se utilizo 16 horas a la semana, la mesa de pintado se utilizo 11 horas a la semana y la mesa de barnizado se utilizo 18 horas a la semana.");
        System.out.print("\nPREGUNTA DEL PROBLEMA: ");
        pregunta = Keyboard.readString();
        System.out.print("\nORDEN: ");
        orden = Keyboard.readInt();
        for (int i = 1; i < orden; i++) {
            System.out.print("\nCONCEPTO DEL PROBLEMA: ");
            concepto = Keyboard.readString();
            System.out.print("UNIDAD: ");
            unidad = Keyboard.readString();
        }
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
        menu();
    }

    static void menu() {
        int opc;
        do {
            System.out.println("1.MÉTODO DE GAUSS-JORDAN \n2.MÉTODO DE JACOBI \n10.FIN");
            System.out.print("ESCOJA UNA OPCIÓN: ");
            opc = Keyboard.readInt();
            switch (opc) {
                case 1:
                    metodoGaussJordan();
                    break;
                case 2:
                    //metodoJacobi();
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

    static void metodoGaussJordan() {
        // Captura de datos de la matriz
        System.out.print("Ingrese el orden de la matriz: ");
        int orden = scanner.nextInt();
        double[][] matrizA = new double[orden][orden + 1];
        
        // Llenado de la matriz
        for (int f = 0; f < orden; f++) {
            for (int c = 0; c < orden + 1; c++) {
                System.out.print("Ingrese el valor de MatrizA[" + (f + 1) + "][" + (c + 1) + "]: ");
                matrizA[f][c] = scanner.nextDouble();
            }
        }
        
        // Cierre del scanner
        scanner.close();
        
        // Impresión de la matriz capturada
        System.out.println("\nMatriz de Datos:");
        imprimirMatriz(matrizA, orden);

        //Matriz abajo
        System.out.println("\nMatriz ceros abajo de la diagonal:");
        metodoGaussJordanAbajo(matrizA, orden);

        //Matriz arriba
        //System.out.println("\nMatriz ceros abajo y arriba de la diagonal:");
        //metodoGaussJordanArriba(matrizA, orden);

        //Matriz diagonal
        //System.out.println("\nMatriz identidad:");
        //metodoGaussJordanDiagonal(matrizA, orden);
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
                    pivote = matrizA[k][k];
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
        //imprimirMatriz(matrizA, orden);
    }

    static void metodoGaussJordanDiagonal(double[][] matrizA, int orden) {
        //imprimirMatriz(matrizA, orden);
    }

    static void imprimirMatriz(double[][] matrizA, int orden) {
        System.out.println("-------------------------------------------------------");
        for (int f = 0; f < orden; f++) {
            for (int c = 0; c < orden + 1; c++) {
                System.out.printf("%-10.2f ", matrizA[f][c]);
            }
            System.out.printf("| %-10.2f\n", matrizA[f][orden]);
        }
        System.out.println("-------------------------------------------------------");
    }
}
