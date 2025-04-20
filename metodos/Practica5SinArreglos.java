package metodos;

import java.util.Scanner;

public class Practica5SinArreglos {
    static Scanner leer = new Scanner(System.in);
    public static void main(String[] args) {
        double a, b, error, valorReal, dif, aCopia, h, ah, fa, fah, area, valorCalc;
        int nTrapecios, tProcesos, nProcesos = 1, nFinalTrapecios;
        String pregunta, unidad;

        System.out.println("\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN");
        System.out.println("\t\t\tIng. En Sistemas Computacionales.\n");
        System.out.println("Covarrubias Osuna Dairy Karime.");
        System.out.println("Métodos Numéricos.");
        System.out.println("Integración numérica.");
        System.out.println("Método de trapecios.");
        System.out.println("Horario de 12:00 a 13:00 horas.\n");
        System.out.println("\t\tEste programa resuelve problemas de integración");
        System.out.println("\t\tutilizando el método de trapecios.\n");

        System.out.print("PREGUNTA DEL PROBLEMA: ");
        pregunta = Keyboard.readString();

        System.out.print("UNIDAD: ");
        unidad = Keyboard.readString();

        System.out.print("Límite inferior (a): ");
        a = leer.nextDouble();

        System.out.print("Límite superior (b): ");
        b = leer.nextDouble();

        System.out.print("Número de trapecios inicial (n): ");
        nTrapecios = leer.nextInt();

        System.out.print("Valor Real: ");
        valorReal = leer.nextDouble();

        System.out.print("Número total de procesos: ");
        tProcesos = leer.nextInt();

        System.out.print("Error: ");
        error = leer.nextDouble();

        nFinalTrapecios = nTrapecios;

        do {
            h = (b - a) / nFinalTrapecios;
            valorCalc = 0;

            System.out.println("\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN");
            System.out.println("\t\t\tIng. En Sistemas Computacionales.\n");
            System.out.println("Covarrubias Osuna Dairy Karime.");
            System.out.println("Métodos Numéricos.");
            System.out.println("Integración numérica.");
            System.out.println("Método de trapecios.");
            System.out.println("Horario de 12:00 a 13:00 horas.\n");
            System.out.print("PREGUNTA: ¿" + pregunta + "?\n");

            System.out.println(" __________________________________________________________________________________");
            System.out.printf("| %-10s| %-12s| %-12s| %-12s| %-12s| %-12s|\n", "Trapecio", "a", "a+h", "f(a)", "f(a+h)", "Área");
            System.out.println(" __________________________________________________________________________________");

            for (int i = 0; i < nFinalTrapecios; i++) {
                aCopia = a + i * h;
                ah = aCopia + h;
                fa = 2 + Math.sin(2 * Math.sqrt(aCopia));
                fah = 2 + Math.sin(2 * Math.sqrt(ah));
                area = (h / 2) * (fa + fah);
                valorCalc += area;

                System.out.printf("| %-10d| %-12.5f| %-12.5f| %-12.5f| %-12.5f| %-12.5f|\n", (i + 1), aCopia, ah, fa, fah, area);
            }

            dif = Math.abs(valorReal - valorCalc);

            System.out.println(" __________________________________________________________________________________");

            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("\t\t\t\t--- Resultados Finales ---");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Número inicial de trapecios = " + nTrapecios);
            System.out.println("Número final de trapecios = " + nFinalTrapecios);
            System.out.println("Número de procesos = " + nProcesos);
            System.out.printf("Valor real de la integral = %.5f %s.\n", valorReal, unidad);
            System.out.printf("Valor calculado por trapecios = %.5f %s.\n", valorCalc, unidad);
            System.out.printf("Error del problema = %.5f %s.\n", error, unidad);
            System.out.printf("Error del método = %.5f %s.\n", dif, unidad);

            if (dif > error) {
                nFinalTrapecios *= 2;
                System.out.println("El error es mayor al permitido.");
                System.out.println("__________________________________________________________________________________________________\n");
                nProcesos++;
            }
        } while(dif > error && nProcesos <= tProcesos);
    }
}
