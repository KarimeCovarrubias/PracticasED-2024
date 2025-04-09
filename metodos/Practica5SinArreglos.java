package metodos;

import java.util.Scanner;

public class Practica5SinArreglos {
    static Scanner leer = new Scanner(System.in);
    public static double funcion(double x) {
        return Math.sqrt(3) * (1 - Math.pow(Math.sin(x), 2)) - Math.sin(x);
    }

    public static void main(String[] args) {
        double error, valorReal, errorCalculado;
        int tProcesos, nProcesos = 1;
        String pregunta;

        System.out.println("\n\n\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                            "\n\t\t\tIng. En Sistemas Computacionales" +
                            "\n\nCovarrubias Osuna Dairy Karime." +
                            "\nSolución de ecuaciones." + 
                            "\nDe 12:00 a 13:00 horas." + "\nMétodo de Trapecios.\n");
        System.out.printf("\t\t%s%n \t\t%s%n \t\t%s%n", "Este programa ejecuta la solución de problemas",
                        "utilizando los procesos lógicos de Solución de sistemas",
                        "de Ecuaciones utilizando diversos métodos numéricos.\n");

        System.out.print("PREGUNTA DEL PROBLEMA: ");
        pregunta = Keyboard.readString();

        System.out.print("Punto inicial (a): ");
        double aInicial = leer.nextDouble();

        System.out.print("Punto final (b): ");
        double b = leer.nextDouble();

        System.out.print("Número de trapecios inicial (n): ");
        int n = leer.nextInt();

        System.out.print("Valor Real: ");
        valorReal = leer.nextDouble();

        System.out.print("Número total de procesos: ");
        tProcesos = leer.nextInt();

        System.out.print("Error: ");
        error = leer.nextDouble();

        do {
            double h = (b - aInicial) / n;
            double sumaTotal = 0;

            // Calculamos la suma sin imprimir
            for (int i = 0; i < n; i++) {
                double a = aInicial + i * h;
                double ah = a + h;
                double fa = funcion(a);
                double fah = funcion(ah);
                double area = (h / 2) * (fa + fah);
                sumaTotal += area;
            }

            errorCalculado = Math.abs(sumaTotal - valorReal);

            if (errorCalculado > error) {
                System.out.println("\nProceso #" + nProcesos);
                System.out.println("Número de trapecios = " + n);
                System.out.printf("Valor calculado = %.6f u²\n", sumaTotal);
                System.out.printf("Error calculado = %.6f u²\n", errorCalculado);
                n *= 2;
                System.out.println("El error es mayor al permitido. Aumentando número de trapecios a " + n);
                nProcesos++;
            } else {
                System.out.println("\n\n\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN" + 
                            "\n\t\t\tIng. En Sistemas Computacionales" +
                            "\n\nCovarrubias Osuna Dairy Karime." +
                            "\nSolución de ecuaciones." + 
                            "\nDe 12:00 a 13:00 horas." + "\nMétodo de Trapecios.\n");
                System.out.printf("\t\t%s%n \t\t%s%n \t\t%s%n", "Este programa ejecuta la solución de problemas",
                                "utilizando los procesos lógicos de Solución de sistemas",
                                "de Ecuaciones utilizando diversos métodos numéricos.\n");
                System.out.print("PREGUNTA: ¿" + pregunta + "?\n");
                // Imprimir tabla
                System.out.println(" __________________________________________________________________________________");
                System.out.printf("| %-10s| %-12s| %-12s| %-12s| %-12s| %-12s|\n",
                        "Trapecio", "a", "a+h", "f(a)", "f(a+h)", "Área");
                        System.out.println(" __________________________________________________________________________________");

                double sumaVerificada = 0;
                for (int i = 0; i < n; i++) {
                    double a = aInicial + i * h;
                    double ah = a + h;
                    double fa = funcion(a);
                    double fah = funcion(ah);
                    double area = (h / 2) * (fa + fah);
                    sumaVerificada += area;

                    System.out.printf("| %-10d| %-12.6f| %-12.6f| %-12.6f| %-12.6f| %-12.6f|\n",
                            (i + 1), a, ah, fa, fah, area);
                }
                System.out.println(" __________________________________________________________________________________");

                // Resultados finales
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("\t\t\t\t--- Resultados Finales ---");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("Punto inicial = " + aInicial);
                System.out.println("Punto final = " + b);
                System.out.println("Número de procesos = " + nProcesos);
                System.out.printf("Valor real de la integral = %.6f u²\n", valorReal);
                System.out.printf("Valor calculado por trapecios =  %.6f u²\n", sumaVerificada);
                System.out.printf("Error permitido = %f u²\n", error);
                System.out.printf("Error calculado = %.6f u²\n", errorCalculado);
                break;
            }

        } while (errorCalculado > error && nProcesos < tProcesos);
    }
}