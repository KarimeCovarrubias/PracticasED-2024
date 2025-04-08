import java.util.Scanner;

public class Practica5 {

    // Función proporcionada
    public static double funcion(double x) {
        return Math.sqrt(3) * (1 - Math.pow(Math.sin(x), 2)) - Math.sin(x);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double errorCalculado, error = 0.005, valorReal = 7.216500;

        System.out.print("Punto inicial (a): ");
        double a = sc.nextDouble();

        System.out.print("Punto final (b): ");
        double b = sc.nextDouble();

        do {
            System.out.print("Número de trapecios inicial (n): ");
            int n = sc.nextInt();

            double h = (b - a) / n;
            double sumaTotal = 0;

            // Arrays para almacenar los valores de cada trapecio
            double[] puntosA = new double[n];
            double[] puntosAh = new double[n];
            double[] fa = new double[n];
            double[] fah = new double[n];
            double[] areas = new double[n];

            // Cálculo de las áreas de los trapecios
            for (int i = 0; i < n; i++) {
                double xi = a + i * h;        // Calcular xi
                double xi1 = xi + h;          // Calcular xi+h
                double fxi = funcion(xi);     // Calcular f(xi)
                double fxi1 = funcion(xi1);   // Calcular f(xi+h)
                double area = (h / 2) * (fxi + fxi1);  // Cálculo del área del trapecio

                // Almacenar los valores en los arrays
                puntosA[i] = xi;
                puntosAh[i] = xi1;
                fa[i] = fxi;
                fah[i] = fxi1;
                areas[i] = area;
                sumaTotal += area;  // Acumular el área total
            }

            // Mostrar la tabla con los resultados
            System.out.println("\nTabla con n = " + n);
            System.out.printf("%-10s %-12s %-12s %-12s %-12s %-12s\n",
                    "Trapecio", "a", "a+h", "f(a)", "f(a+h)", "Área");

            for (int i = 0; i < n; i++) {
                System.out.printf("%-10d %-12.6f %-12.6f %-12.6f %-12.6f %-12.6f\n",
                        (i + 1), puntosA[i], puntosAh[i], fa[i], fah[i], areas[i]);
            }

            // Cálculo del error entre el valor real y el valor calculado
            errorCalculado = Math.abs(sumaTotal - valorReal);

            // Mostrar el valor real, el valor calculado y el error
            System.out.printf("\nValor real =       %.6f u²\n", valorReal);
            System.out.printf("Valor calculado =  %.6f u²\n", sumaTotal);
            System.out.printf("Error del cálculo = %.6f u²\n", errorCalculado);

            // Comprobar si el error es mayor al permitido
            if (errorCalculado > error) {
                System.out.println("\nEl error es mayor al permitido. Aumentando número de trapecios...\n");
                n += 2; // Aumenta el número de trapecios para reducir el error
            } else {
                System.out.println("\nEl error es menor o igual al permitido. El cálculo es aceptable.");
            }

        } while (errorCalculado > error);
    }
}