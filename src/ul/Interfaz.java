/**
 * The `Interfaz` class represents the user interface for managing a list of contacts.
 * It provides options for adding, modifying, and deleting contacts, as well as displaying the list of contacts.
 * The user can interact with the interface by selecting options from a menu.
 * 
 * This class uses the `ListadeContactos` class to store and manage the contacts.
 * It also uses the `Email` class to validate email addresses.
 * 
 * To use this class, create an instance of `Interfaz` and call the `lectura` method to start the user interface.
 * The user can then select options from the menu until they choose to exit.
 * 
 * Example usage:
 * ```
 * Interfaz interfaz = new Interfaz();
 * interfaz.lectura();
 * ```
 * 
 * @see ListadeContactos
 * @see Email
 */
package ul;
import java.util.Scanner;

import negocios.ListadeContactos;
import datos.Contacto;
import datos.Email;
import datos.Lista;
import datos.PosicionIlegalException;

public class Interfaz {
	static Scanner entrada = new  Scanner(System.in);
	static ListadeContactos ldc = new ListadeContactos();

	public static void lectura() throws PosicionIlegalException{
		System.out.println("Bienvenido");
		System.out.println("Por favor, escoja una de las siguientes opciones");
		System.out.println("Para acceder a la opcion, escriba el número");
		imprimirMenu();			
	}

	private static void imprimirMenu() throws PosicionIlegalException{
		int opcion = 0;
		do{
			System.out.println("Por favor, escoja una de las siguientes opciones ");
			System.out.println("Para acceder a la opcion, escriba el número ");
			System.out.println("****** MENU PRINCIPAL ******");
			System.out.println("1.- Ingresar Nuevo Contacto");
			System.out.println("2.- Mostrar Contactos");
			System.out.println("3.- Modificar Contacto");
			System.out.println("4.- Eliminar Contacto");
			System.out.println("5.- Buscar Contacto");
			System.out.println("0.- Salir");
			System.out.println("*****************************");
			opcion = entrada.nextInt();

			switch  (opcion) {
			case 1:
				entrada.nextLine(); // solo limpia la cadena Scanner
				ldc.agregarContacto("Fernando", "Castro", 
									"Laguna de catemaco 150 Ote. Las Quintas, Culiacán Sinaloa", 
									"fernando@gmail.com", "6671234567", "6671234567");
				//Agregar otro contacto
				
				ldc.mostrarTodosLosContactos();
				Lista<Contacto> lista = new Lista<Contacto>();
				System.out.println("\nMostrar todos los contactos: ");
				for (int i = 0; i < lista.getTamanio(); i++) {
					System.out.println("Nombre de Contacto: " + lista.getValor(i).getNombres());
				}
				//validarAlta();
				break;
			case 2:
				mostrarContactos();
				break;
			case 3:
				entrada.nextLine(); // solo limpia la cadena Scanner
				validarCambio();
				break;
			case 4:
				entrada.nextLine(); // solo limpia la cadena Scanner
				validarBaja();
				break;
			case 5:
				buscarContacto1();
				break;
			case 0:
				salir();
				break;
			default:
				System.out.println("Opción invalida");
				break;		
			}
		} while (opcion != 0 );
	}

	private static void salir() {
		System.out.println("Sesion Finalizada");
		System.out.println("Adios!");
		System.exit(0);
	}

	private static void validarAlta() throws PosicionIlegalException{
		try {
			String nombres, apellidos, direccion, correo, telefono, celular;
			while (true) {
				System.out.println("Ingresar un nuevo contacto");
				System.out.println("\nPor favor ingrese la información del contacto");
				System.out.println("\t- El nombre y apellido no puede repetirse");
				System.out.println("\t- y el celular debe tener los caracteres válidos");
				System.out.println("\t- Los nombres y apellidos no pueden repetirse");
				
				System.out.print("NOMBRE: ");
				
				nombres = entrada.nextLine();
				
				System.out.print("APELLIDO: ");
				apellidos = entrada.nextLine();
	
				if (ldc.buscarContacto(nombres, apellidos) != null) //se puede agregar el contacto
				{
					System.out.println("\nYa existe un contacto con ese nombre y apellido");
					System.out.println("Tamaño de la lista: " + ldc.tamanio());
					System.out.println("\nSi desea agregar nuevamente un contacto: ");
					System.out.println("Ingrese un nuevo nombre y apellido");
					System.out.println("--------------------------------------------------");
				} else {
					break;
				}
			}
			System.out.print("DOMICILIO: ");
			direccion = entrada.nextLine();
			try {
				System.out.print("CORREO: ");
				correo = entrada.nextLine();
				new Email(correo);
			}
			catch (Exception e) {
				System.out.println("\nCorreo no válido");
				System.out.println("Si desea agregar nuevamente un contacto: ");
				System.out.println("Seleccione la opción 1");
				System.out.println("--------------------------------------------------");
				return;
			}
			
			System.out.print("TELÉFONO: ");
			telefono = entrada.nextLine();
			System.out.print("CELULAR: ");
			celular = entrada.nextLine();

			if (ldc.agregarContacto(nombres, apellidos, direccion, correo, telefono, celular)) {
				System.out.println("\nExito. Agregado a la lista de contacto");
				System.out.println("Tamaño de la lista: " + ldc.tamanio());
				System.out.println("--------------------------------------------------\n");	
			} else {
				System.out.println("\nNo se Agregó");
				System.out.println("Tamaño de la lista: " + ldc.tamanio());
				System.out.println("\nSi desea agregar nuevamente un contacto");
				System.out.println("Seleccione la opción 1");	
				System.out.println("--------------------------------------------------\n");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    imprimirMenu();
	}

	private static void mostrarContactos() {
		try {
			ldc.mostrarTodosLosContactos();
			System.out.println("Tamaño de la lista: " + ldc.tamanio());
			System.out.println("--------------------------------------------------\n");
		} catch (Exception e) {
			System.out.println("\nOcurrió un error al mostrar los contactos.");
			System.out.println("Tamaño de la lista: " + ldc.tamanio());
			System.out.println("--------------------------------------------------\n");
			e.printStackTrace();
		}
	}

	private static void validarCambio() throws PosicionIlegalException{
		try {
			String nombres, apellidos, direccion, correo, telefono, celular;
			while (true) {
				System.out.println("Modificar un contacto");
				System.out.println("Por favor ingrese la información del contacto.");
				System.out.println("El nombre y apellido deben existir.");
				System.out.print("NOMBRES: ");
				nombres = entrada.nextLine();
				System.out.print("APELLIDOS: ");
				apellidos = entrada.nextLine();
		
				if (ldc.buscarContacto(nombres, apellidos) == null) {
					System.out.println("\nContacto no existe.");
				}
		
				System.out.print("DOMICILIO: ");
				direccion = entrada.nextLine();
		
				try {
					System.out.print("CORREO: ");
					correo = entrada.nextLine();
					new Email(correo);
				} catch (Exception e) {
					System.out.println("Correo no válido.");
					System.out.println("Si desea agregar nuevamente un contacto");
					System.out.println("Seleccione la opción 1.");
					return;
				}
		
				System.out.print("TELÉFONO: ");
				telefono = entrada.nextLine();
				System.out.print("CELULAR: ");
				celular = entrada.nextLine();

				if (ldc.modificarContacto(nombres, apellidos, direccion, correo, telefono, celular)) {
					System.out.println("Modificación Exitosa");
					System.out.println("Lista de contactos");
					ldc.mostrarTodosLosContactos();
				} else {
					System.out.println("No se pudo modificar el contacto"); //AQUI ME QUEDÉ, YO PUSE EL SYSOUT
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		imprimirMenu();
	}
	
	private static void validarBaja() throws PosicionIlegalException{
		String nombres, apellidos;
		try {
			while (true) {
				System.out.println("\nEliminar Contacto");
				System.out.println("Por favor ingrese la información del contacto.");
				System.out.println("El nombre y apellido deben existir.");
				System.out.print("NOMBRES: ");
				nombres = entrada.nextLine();
				System.out.print("APELLIDOS: ");
				apellidos = entrada.nextLine();
		
				if (ldc.eliminarContacto(nombres, apellidos)) {
					System.out.println("\nContacto eliminado.");
					System.out.println("Lista de contactos: ");
					ldc.mostrarTodosLosContactos();
					System.out.println("--------------------------------------------------\n");
				} else {
					System.out.println("\nNo existe un Contacto con ese nombre y apellido.");
					System.out.println("Si desea eliminar un contacto");
					System.out.println("Ingrese un contacto con nombre y apellido.");
					System.out.println("--------------------------------------------------\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		imprimirMenu();
	}

	public static void buscarContacto1() throws PosicionIlegalException{
		String nombre, apellido;
		try {
			System.out.print("NOMBRE: ");
			nombre = entrada.next();
			System.out.print("APELLIDO: ");
			apellido = entrada.next();

			Contacto contacto = ldc.buscarContacto(nombre, apellido);

			if (contacto != null) {
				System.out.println("\nContacto encontrado:");
				System.out.println(contacto);
				System.out.println("\nTamaño de la lista: " + ldc.tamanio());
				System.out.println("--------------------------------------------------\n");
			} else {
				System.out.println("El contacto no existe en la lista.");
				System.out.println("\nTamaño de la lista: " + ldc.tamanio());
				System.out.println("--------------------------------------------------\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		imprimirMenu();
	}
}