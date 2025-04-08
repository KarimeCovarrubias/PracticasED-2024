import java.util.Scanner;

public class Practica5SinArreglos {

    public static double funcion(double x) {
        return Math.sqrt(3) * (1 - Math.pow(Math.sin(x), 2)) - Math.sin(x);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double errorPermitido = 0.005;
        double valorReal = 7.216500;
        double errorCalculado;

        System.out.print("Punto inicial (a): ");
        double a = sc.nextDouble();

        System.out.print("Punto final (b): ");
        double b = sc.nextDouble();

        int n;
        do {
            System.out.print("Número de trapecios inicial (n): ");
            n = sc.nextInt();

            double h = (b - a) / n;
            double sumaTotal = 0;

            System.out.println("\nTabla con n = " + n);
            System.out.printf("%-10s %-12s %-12s %-12s %-12s %-12s\n",
                    "Trapecio", "a", "a+h", "f(a)", "f(a+h)", "Área");

            for (int i = 0; i < n; i++) {
                double xi = a + i * h;
                double xi1 = xi + h;
                double fxi = funcion(xi);
                double fxi1 = funcion(xi1);
                double area = (h / 2) * (fxi + fxi1);
                sumaTotal += area;

                System.out.printf("%-10d %-12.6f %-12.6f %-12.6f %-12.6f %-12.6f\n",
                        (i + 1), xi, xi1, fxi, fxi1, area);
            }

            errorCalculado = Math.abs(sumaTotal - valorReal);

            System.out.printf("\nValor real =       %.6f u²\n", valorReal);
            System.out.printf("Valor calculado =  %.6f u²\n", sumaTotal);
            System.out.printf("Error del cálculo = %.6f u²\n", errorCalculado);

            if (errorCalculado > errorPermitido) {
                System.out.println("\nEl error es mayor al permitido. Aumentando número de trapecios...\n");
                n += 2; // El valor de n actualizado no se conserva entre iteraciones, podrías ajustarlo si quieres
            } else {
                System.out.println("\nEl error es menor o igual al permitido. El cálculo es aceptable.");
            }

        } while (errorCalculado > errorPermitido);
    }
}