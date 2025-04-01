package delimitadores;

import java.util.Scanner;
public class Principal {
   static Scanner consola = new Scanner(System.in);
    public static void main(String[] args)  {
        int opc=0;
        do{
            System.out.println("************************************************");
            System.out.println("\tAPLICACIONES CON USO DE PILAS");
            System.out.println("************************************************");
            System.out.println(" 1) Correspondencia de Delimitadores");
            System.out.println(" 2) Convertir Expresion Infija  a Postfija y Evaluar");
         
            System.out.println();
            System.out.println(" 0) SALIR");
            System.out.print("Seleccione Opcion: ");
            opc= consola.nextInt();
            switch (opc) {
                case 1:
                   AppDelimitadores.menu();
                   break;
                case 2:
                   AppPostFija.menu();
                   break;
                case 0:
                   System.out.println("Hasta Luego");
                   consola.close();
                   break;
                default:
                    System.out.println("Opcion incorrecta, intente de nuevo");
            }
        }while (opc !=0);
    }
}