package delimitadores;

public class AppDelimitadores {
    public static void menu() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("\t\tAPLICACIONES CON USO DE PILAS");
        System.out.println("--------------------------------------------------------------");
        System.out.println("1. Correspondencia de delimitadores");
        System.out.println("--------------------------------------------------------------");

        String expr = "(a+b/2)";
        System.out.println("\n" + expr);
        Delimitadores objDel = new Delimitadores();
        if (objDel.evaluacionDelimitadores(expr)) {
            System.out.println("Expresión correcta.");
        } else {
            System.out.println("Expresión incorrecta.");
        }

        expr = "while (m<(n[8]+o)) { " +
        "\n     int p=7; " +
        "\n     /*comentarios*/" +
        "\n               }";
        System.out.println("\n" + expr);
        if (objDel.evaluacionDelimitadores(expr)) {
            System.out.println("Expresión correcta.");
        } else {
            System.out.println("Expresión incorrecta.");
        }

        expr = "while (m<(n[8]+o)) { " +
        "\nint p=7; " +
        "\n/*comentarios*/";
        System.out.println("\n" + expr);
        if (objDel.evaluacionDelimitadores(expr)) {
            System.out.println("Expresión correcta.");
        } else {
            System.out.println("Expresión incorrecta.");
        }

        expr = " ";
        System.out.println("\n" + expr);
        if (objDel.evaluacionDelimitadores(expr)) {
            System.out.println("Expresión correcta.");
        } else {
            System.out.println("Expresión incorrecta.");
        }
    }
}