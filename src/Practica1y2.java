package Ejemplo1;
import java.text.DecimalFormat;
public class Practica1y2 {
    static DecimalFormat formato = new DecimalFormat("#,###.######");
    public static void main(String[] args) {
        System.out.println("\t\t\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                            "\n\t\t\t\t\tIng. En Sistemas Computacionales" +
                            "\n\nCovarrubias Osuna Dairy Karime." +
                            "\nSolución de ecuaciones." + 
                            "\nDe 12:00 a 13:00 horas." +
                            "\n\n\t\t\t\t\tEste programa ejecuta la solución de problemas" +
                            "\n\t\t\t\t\tutilizando los procesos lógicos de cálculo de Raíces de" +
                            "\n\t\t\t\t\tuna ecuación con diversos métodos numéricos." +
                            "\n\nPREGUNTA DEL PROBLEMA: ¿Calcular la concentración del ion de hidrógeno para una solución saturada?");
        System.out.println("CONCEPTO: Iones de Hidrógeno" +
                             "\nUNIDAD: Iones");
        System.out.println("\n--------------------------------------------------------------------- ----------------------------------------------------------------------\n");
        int opc;
        do {
            System.out.println("\t\t\t\t-----------------------------------MENÚ-----------------------------------");
            System.out.println("1. MÉTODO DE LA SECANTE" + "\n2. MÉTODO DE NEWTON RAPHSON" + "\n10. FIN");
            System.out.print("SELECCIONE UNA OPCIÓN: ");
            opc = Keyboard.readInt();
            switch (opc) {
                case 1:
                    System.out.println("\t\t\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                                        "\n\t\t\t\t\tIng. En Sistemas Computacionales" +
                                        "\n\nCovarrubias Osuna Dairy Karime." +
                                        "\nRaíces de una ecuación: Método de la Secante." + 
                                        "\nDe 12:00 a 13:00 horas." +
                                        "\nPREGUNTA: ¿Calcular la concentración del ion de hidrógeno para una solución saturada?");
                    metodoSecante();
                    break;
                case 2:
                    System.out.println("\t\t\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                                        "\n\t\t\t\t\tIng. En Sistemas Computacionales" +
                                        "\n\nCovarrubias Osuna Dairy Karime." +
                                        "\nRaíces de una ecuación: Método de Newton Raphson." + 
                                        "\nDe 12:00 a 13:00 horas." +
                                        "\n\nPREGUNTA: ¿Calcular la concentración del ion de hidrógeno para una solución saturada?");
                    metodoNewtonRaphson();
                    break;
                case 10:
                    System.out.println("ADIOS");
                break;
                default:
                    System.out.println("ELIJA UNA OPCIÓN VÁLIDA");
                break;
            }
        } while (opc != 10);
    }

    public static double funcion(double x) {
        return (Math.pow(x, 3) + (3.5 * Math.pow(x, 2)) - 40);
    }

    public static double funcionDerivada (double x) {
        return 3 * (Math.pow(x, 2)) + 7 * x;
    }

    public static void metodoSecante() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        double x1 = 0, x2 = 5, x3, fx1, fx2, fx3, error = 0.00001, absfx3;
        int nCalculos = 100;
        fx1 = funcion(x1);
        fx2 = funcion(x2);
        System.out.printf("%-3s %-18s %-18s %-18s %-18s %-18s %-18s %-18s%n", "N", "X1", "X2", 
        "f(X1)", "f(X2)", "X3", "f(X3)", "abs f(X3)");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 1; i <= nCalculos; i++) {
            x3 = x1 - (((x1 - x2) * fx1) / (fx1 - fx2));
            fx3 = funcion(x3);
            absfx3 =Math.abs(fx3);
            System.out.printf("%-3d %-18.6f %-18.6f %-18.6f %-18.6f %-18.6f %-18.6f %-18.6f%n", i, 
            x1, x2, fx1, fx2, x3, fx3, absfx3);
            if (Math.abs(fx3) <= error) {
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("\nLa raíz de la ecuación es: " + formato.format(x3) + " iones.\n");
                break;
            } else {
                x1 = x2;
                x2 = x3;
                fx1 = fx2;
                fx2 = fx3;
            }
        }
    }

    public static void metodoNewtonRaphson() {
        double x1 = 0, x2, fx1, d_dx_fx1, fx2, error = 0.00001, absfx2;
        int nCalculo = 100;
        for (int i = 1; i <= nCalculo; i++) {
            d_dx_fx1 = funcionDerivada(x1);
            while (d_dx_fx1 == 0) {
                System.out.println("\nx = 0 \nDerivada = 0. INVÁLIDO \nx = 1:");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-3s %-18s %-18s %-18s %-18s %-30s %-15s%n", "N", "X1", "f(X1)", 
                                    "f'(X1)", "X2", "f(X2)", "abs f(X2)");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                x1 = 1;
                d_dx_fx1 = funcionDerivada(x1);
            }
            fx1 = funcion(x1);
            x2 = x1 - (fx1 / d_dx_fx1);
            fx2 = funcion(x2);
            absfx2 = Math.abs(fx2);

            System.out.printf("%-3d %-18.6f %-18.6f %-18.6f %-18.6f %-30.15f %-25.15f%n", i, x1, 
            fx1, d_dx_fx1, x2, fx2, absfx2);
            if (Math.abs(fx2) <= error) {
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("\nLa raíz de la ecuación es: " + formato.format(x2) + " iones.\n");
                break;
                } else {
                    x1 = x2;
            }
        }
    }
}