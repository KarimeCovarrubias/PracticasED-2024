// FALTA VER LO DEL ERROR (SUMA EN VEZ DE RESTAR, ES DECIR, IMPRIME CIEN Y TANTOS EN VEZ DE 0.000)
// FALTA VER LO DEL VALOR CALCULADO (IMPRIME LOS VALORES EN NEGATIVO CUANDO SE DEBEN IMPRIMIR POSITIVO, LO QUE TAMBIÉN AFECTA AL ERROR)
package metodos;

import java.text.DecimalFormat;
public class MetodoSimpson {
    static DecimalFormat formato = new DecimalFormat("#,###.00000");
    public static void main(String[] args) {
        String pregunta, unidad;
        double vReal, a, b;
        int n;

        System.out.println("\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN");
        System.out.println("\t\t\tIng. En Sistemas Computacionales.\n");
        System.out.println("Covarrubias Osuna Dairy Karime.");
        System.out.println("Métodos Numéricos.");
        System.out.println("Integración numérica.");
        System.out.println("Método de Simpson.");
        System.out.println("Horario de 12:00 a 13:00 horas.\n");
        System.out.println("\t\tEste programa resuelve problemas de integración" + 
                            "\n\t\tutilizando el método de Simpson.\n");

        //System.out.println("ECUACIÓN: 2 + Sen\u221a2\n");
        System.out.println("ECUACIÓN: x^2 - 6x - 8\n");
        System.out.print("Pregunta del problema: ");
        pregunta = Keyboard.readString();

        System.out.print("Unidad: ");
        unidad = Keyboard.readString();

        System.out.print("Valor real: ");
        vReal = Keyboard.readDouble();

        System.out.print("Límite inferior (a): ");
        a = Keyboard.readDouble();

        System.out.print("Límite superior (b): ");
        b = Keyboard.readDouble();

        do {
            System.out.println("\n---------------------------------------------------------------------------------------------------");
            System.out.println("\tRegla 1: n = 2" +
                                "\n\tRegla 1: n = 3" +
                                "\n\tRegla 1: n > 2  y  n = par" + 
                                "\n\tRegla 1:  n > 3, n = impar  y  n = 3" +
                                "\n\tFIN. n = 0");
            System.out.print("\n¿Cuál es el número de divisiones? ");
            n = Keyboard.readInt();

            if (n == 0) {
                break;
            } else if (n == 2) { // REGLA 1
                System.out.println("\n________________________________________________________________________________________\n");
                System.out.println("Simpson de 1/3 fórmula simple\n");
                encabezado(pregunta);
                simpsonUnTercioSimple(a, b, n, vReal, unidad);
            } else if (n == 3) { //REGLA 2
                System.out.println("\n________________________________________________________________________________________\n");
                System.out.println("Simpson de 3/8 fórmula simple\n");
                encabezado(pregunta);
                simpsonTresOctavosSimple(a, b, n, vReal, unidad);
            } else if ((n > 2) && (n % 2 == 0)) { // REGLA 3
                System.out.println("\n________________________________________________________________________________________\n");
                System.out.println("Simpson de 1/3 fórmula compleja\n");
                encabezado(pregunta);
                simpsonUnTercioComplejo(a, b, n, vReal, unidad);
            } else if ((n > 3) && (n % 3 == 0)) { // REGLA 4
                System.out.println("\n________________________________________________________________________________________\n");
                System.out.println("Simpson de 3/8 fórmula compleja\n");
                encabezado(pregunta);
                simpsonTresOctavosComplejo(a, b, n, vReal, unidad);
            } else {
                System.out.println("Simpson no aplica para este número de divisiones");
            }
        } while (n != 0);
    }

    public static double funcion(double x) {
        return (Math.pow(x, 3) - Math.sin(x + 1)); // f(x) = x^3 - sen(x + 1)
    }

    public static void encabezado(String pregunta) {
        System.out.println("\t\t\tINSTITUTO TECNOLÓGICO DE CULIACÁN");
            System.out.println("\t\t\tIng. En Sistemas Computacionales.\n");
            System.out.println("Covarrubias Osuna Dairy Karime.");
            System.out.println("Métodos Numéricos.");
            System.out.println("Integración numérica.");
            System.out.println("Método de Simpson.");
            System.out.println("Horario de 12:00 a 13:00 horas.\n");
            System.out.print("PREGUNTA: ¿" + pregunta + "?\n");
    }

    public static void simpsonUnTercioSimple(double a, double b, int n, double vReal, String unidad) {
        double h, fa, x1, fx1, fb, vCalc, error;
        h = (b - a) / n;

        fa = funcion(a); // ECUACION
        x1 = a + h;
        fx1 = funcion(x1);
        fb = funcion(b);

        vCalc = ((1 * h) / 3) * (fa + (4 * fx1) + fb);
        error = Math.abs(vReal - Math.abs(vCalc));

        System.out.println(" ________________________________________________________________________________");
        System.out.printf("| %-10s| %-15s| %-15s| %-15s| %-15s|\n", "pxy", "x", "f(x)", "Factor", "Área");
        System.out.println(" ________________________________________________________________________________");
        System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", 1, a, formato.format(Math.abs(fa)), 1, formato.format(1*Math.abs(fa)));
        System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", 2, x1, formato.format(Math.abs(fx1)), 4, formato.format(4*Math.abs(fx1)));
        System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", 3, b, formato.format(Math.abs(fb)), 1, formato.format(1*Math.abs(fb)));
        System.out.println(" ________________________________________________________________________________");

        System.out.printf("\nNo. de divisiones = %d", n);
        System.out.printf("\nValor Real de la Integral = %s %s", formato.format(vReal), unidad);
        System.out.printf("\nValor por el Método = %s %s", formato.format(vCalc), unidad);
        System.out.printf("\nError del Método = %.6f %s\n", error, unidad);
    }

    public static void simpsonTresOctavosSimple(double a, double b, int n, double vReal, String unidad) {
        double h, fa, x1, x2, fx1, fx2, fb, vCalc, error;
        h = (b - a) / n;

        fa = funcion(a); // ECUACION
        x1 = a + (1 * h);
        x2 = a + (2 * h);
        fx1 = funcion(x1);
        fx2 = funcion(x2);
        fb = funcion(b);

        vCalc = ((3 * h) / 8) * (fa + (3 * fx1) + (3 * fx2) + fb);
        error = Math.abs(vReal - Math.abs(vCalc));

        System.out.println(" ________________________________________________________________________________");
        System.out.printf("| %-10s| %-15s| %-15s| %-15s| %-15s|\n", "pxy", "x", "f(x)", "Factor", "Área");
        System.out.println(" ________________________________________________________________________________");
        
        System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", 1, a, formato.format(Math.abs(fa)), 1, formato.format(1*Math.abs(fa)));
        System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", 2, x1, formato.format(Math.abs(fx1)), 3, formato.format(3*Math.abs(fx1)));
        System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", 3, x2, formato.format(Math.abs(fx2)), 3, formato.format(3*Math.abs(fx2)));
        System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", 4, b, formato.format(Math.abs(fb)), 1, formato.format(1*Math.abs(fb)));
        System.out.println(" ________________________________________________________________________________");
        
        System.out.printf("\nNo. de divisiones = %d", n);
        System.out.printf("\nValor Real de la Integral = %s %s", formato.format(vReal), unidad);
        System.out.printf("\nValor por el Método = %s %s", formato.format(vCalc), unidad);
        System.out.printf("\nError del Método = %.6f %s\n", error, unidad);
    }

    public static void simpsonUnTercioComplejo(double a, double b, int n, double vReal, String unidad) {
        double vCalc, h, ca, cb, pto, fpto, valor, pos, res, error;
        int factor;
        vCalc = 0;
        h = (b - a) / n;
        ca = a;
        cb = b;

        System.out.println(" ________________________________________________________________________________");
        System.out.printf("| %-10s| %-15s| %-15s| %-15s| %-15s|\n", "xx", "pto", "fpto", "factor", "valor");
        System.out.println(" ________________________________________________________________________________");
        for (int x = 1; x <= n + 1; x++) {
            if (x == 1) {
                factor = 1;;
                pto = ca;
                fpto = funcion(pto); // ECUACIÓN
                valor = factor * fpto;
                vCalc = vCalc + valor;

                System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", x, formato.format(pto), formato.format(Math.abs(fpto)), factor, formato.format(Math.abs(valor)));
            } 
            if (x == n + 1) {
                factor = 1;
                pto = cb;
                fpto = funcion(pto);
                valor = factor * fpto;
                vCalc = vCalc + valor;

                System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", x, formato.format(pto), formato.format(Math.abs(fpto)), factor, formato.format(Math.abs(valor)));
            } 
            if ((x > 1) && (x < n + 1)) {
                pos = x - 1;
                res = (x % 2);
                if (res == 0) {
                    factor = 4;
                    pto = ca + (pos * h);
                    fpto = funcion(pto);
                    valor = factor * fpto;
                    vCalc = vCalc + valor;

                    System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", x, formato.format(pto), formato.format(Math.abs(fpto)), factor, formato.format(Math.abs(valor)));
                } else {
                    factor = 2;
                    pto = ca + (pos * h);
                    fpto = funcion(pto);
                    valor = factor * fpto;
                    vCalc = vCalc + valor;

                    System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", x, formato.format(pto), formato.format(Math.abs(fpto)), factor, formato.format(Math.abs(valor)));
                }
            }
        }
        System.out.println(" ________________________________________________________________________________");
        vCalc = ((1 * h) / 3) * vCalc;
        error = Math.abs(vReal - Math.abs(vCalc));

        System.out.printf("\nNo. de divisiones = %d", n);
        System.out.printf("\nValor Real de la Integral = %s %s", formato.format(vReal), unidad);
        System.out.printf("\nValor por el Método = %s %s", formato.format(vCalc), unidad);
        System.out.printf("\nError del Método = %.6f %s\n", error, unidad);
    }

    public static void simpsonTresOctavosComplejo(double a, double b, int n, double vReal, String unidad) {
        double vCalc, h, ca, cb, pto, fpto, valor, pos, res, error;
        int factor;
        vCalc = 0;
        h = (b - a) / n;
        ca = a;
        cb = b;

        System.out.println(" ________________________________________________________________________________");
        System.out.printf("| %-10s| %-15s| %-15s| %-15s| %-15s|\n", "xx", "pto", "fpto", "factor", "valor");
        System.out.println(" ________________________________________________________________________________");
        for (int xx = 1; xx <= n + 1; xx++) {
            if (xx == 1) {
                factor = 1;
                pto = ca;
                fpto = funcion(pto);
                valor = factor * fpto;
                vCalc = vCalc + valor;

                System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", xx, formato.format(pto), formato.format(Math.abs(fpto)), factor, formato.format(Math.abs(valor)));
            }
            if (xx == n + 1) {
                factor = 1;
                pto = cb;
                fpto = funcion(pto);
                valor = factor * fpto;
                vCalc = vCalc + valor;

                System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", xx, formato.format(pto), formato.format(Math.abs(fpto)), factor, formato.format(Math.abs(valor)));
            }
            if (xx > 1 && xx < n + 1) {
                pos = xx - 1;
                res = (xx - 1) % 3;
                if (res == 0) {
                    factor = 2;
                    pto = ca + (pos * h);
                    fpto = funcion(pto);
                    valor = factor * fpto;
                    vCalc = vCalc + valor;

                    System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", xx, formato.format(pto), formato.format(Math.abs(fpto)), factor, formato.format(Math.abs(valor)));
                } else {
                    factor = 3;
                    pto = ca + (pos * h);
                    fpto = funcion(pto);
                    valor = factor * fpto;
                    vCalc = vCalc + valor;

                    System.out.printf("| %-10d| %-15s| %-15s| %-15d| %-15s|\n", xx, formato.format(pto), formato.format(Math.abs(fpto)), factor, formato.format(Math.abs(valor)));
                }
            }
        }
        System.out.println(" ________________________________________________________________________________");
        vCalc = (3 * h / 8) * vCalc;
        error = Math.abs(vReal - Math.abs(vCalc));

        System.out.printf("\nNo. de divisiones = %d", n);
        System.out.printf("\nValor Real de la Integral = %s %s", formato.format(vReal), unidad);
        System.out.printf("\nValor por el Método = %s %s", formato.format(vCalc), unidad);
        System.out.printf("\nError del Método = %.6f %s\n", error, unidad);
    }
}