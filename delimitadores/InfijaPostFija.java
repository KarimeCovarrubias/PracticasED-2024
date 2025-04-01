package delimitadores;

import java.util.Scanner;
import delimitadores.pila.Pila;
public class InfijaPostFija {
    // Precedencia de operadores
    static Scanner entrada = new Scanner(System.in);

    public InfijaPostFija() {
    	
    }

    // Determina la precedencia de los operadores
    public static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            case '(':
            case ')':
                return 0; // Los paréntesis tienen precedencia 0
            default:
                return -1; // Las variables tienen precedencia de -1
        }
    }

    // Convierte expresión infija a postfija usando Pila
    public static String convertirAPostfijo(String expresionInfija) {
        Pila<Character> pila = new Pila<>();
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < expresionInfija.length(); i++) {
            char caracter = expresionInfija.charAt(i);

            if (Character.isLetterOrDigit(caracter)) {
                resultado.append(caracter);
            }
            else if (caracter == '(') {
                pila.apilar(caracter);
            }
            else if (caracter == ')') {
                while (!pila.esVacia() && pila.cima() != '(') {
                    resultado.append(pila.desapilar());
                }
                if (!pila.esVacia() && pila.cima() != '(') {
                    return "Expresión inválida"; // Si queda un paréntesis abierto, es decir, no tiene paréntesis que cierre, la expresión es inválida
                } else {
                    pila.desapilar();
                }
            }
            else if (esOperador(caracter)) {
                while (!pila.esVacia() && precedencia(pila.cima()) >= precedencia(caracter)) {
                    resultado.append(pila.desapilar());
                }
                pila.apilar(caracter);
            }
        }

        while (!pila.esVacia()) {
            if (pila.cima() == '(') {
                return "Expresión inválida";  // Si queda un paréntesis abierto, es decir, no tiene paréntesis que cierre, la expresión es inválida
            }
            resultado.append(pila.desapilar());
        }
        return resultado.toString();
    }

    // Evalúa la expresión postfija
    public static double evaluarPostfija(String expresionPostfija) {
        Pila<Double> pila = new Pila<>();

        for (int i = 0; i < expresionPostfija.length(); i++) {
            char caracter = expresionPostfija.charAt(i);
            if (Character.isLetter(caracter)) {
                System.out.print("Ingresa el valor de " + caracter + ": ");
                double valor = entrada.nextDouble();
                pila.apilar(valor);
            }
            else if (esOperador(caracter)) {
                if (pila.getTamanio() < 2) {
                    throw new IllegalArgumentException("Expresión inválida: faltan valores.");
                }
                double b = pila.desapilar();
                double a = pila.desapilar();
                pila.apilar(calcularOperacion(a, b, caracter));
            }
        }

        // El resultado tiene que ser el único valor en la pila
        if (pila.getTamanio() != 1) {
            throw new IllegalArgumentException("Expresión inválida.");
        }
        return pila.desapilar();
    }

    // Verifica si un carácter es un operador
    private static boolean esOperador(char operador) {
        return operador == '+' || operador == '-' || operador == '*' || operador == '/' || operador == '^';
    }

    // Realiza la operación entre dos números de acuerdo al operador
    public static double calcularOperacion(double a, double b, char operador) {
        switch (operador) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b != 0) {
                    return a / b;
                } else {
                    throw new ArithmeticException("No se puede dividir entre 0");
                }
            case '^':
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Operador no encontrado");
        }
    }
}