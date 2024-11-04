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
package uI;
import java.util.Scanner;

import negocios.ListadeContactos;
import datos.Contacto;
import datos.Email;

public class Interfaz {
	static Scanner entrada = new  Scanner(System.in);
	static ListadeContactos ldc = new ListadeContactos();

	public static void lectura() {
		System.out.println("Bienvenido");
		System.out.println("Por favor, escoja una de las siguientes opciones");
		System.out.println("Para acceder a la opcion, escriba el número");
		imprimirMenu();			
	}

	private static void imprimirMenu() {
		int opcion = 0;
		do {
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
				validarAlta();
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

	private static void validarAlta() {
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

	private static void validarCambio() {
		try {
			System.out.println("Modificar Contacto");
			System.out.print("Ingrese el nombre del contacto a modificar: ");
			String nombres = entrada.nextLine();
			System.out.print("Ingrese el apellido del contacto a modificar: ");
			String apellidos = entrada.nextLine();
	
			if (ldc.buscarContacto(nombres, apellidos) == null) {
				System.out.println("\nNo se encontró el contacto.");
				System.out.println("Tamaño de la lista: " + ldc.tamanio());
				System.out.println("--------------------------------------------------\n");
				return;
			}
	
			System.out.print("Nuevo domicilio: ");
			String direccion = entrada.nextLine();
			System.out.print("Nuevo correo: ");
			String correo = entrada.nextLine();
	
			try {
				new Email(correo);
			} catch (Exception e) {
				System.out.println("Correo no válido.");
				return;
			}
	
			System.out.print("Nuevo teléfono: ");
			String telefono = entrada.nextLine();
			System.out.print("Nuevo celular: ");
			String celular = entrada.nextLine();
	
			boolean modificado = ldc.modificarContacto(
				nombres, apellidos, direccion, correo, telefono, celular
			);
	
			if (modificado) {
				System.out.println("\nEl contacto ha sido modificado.");
				System.out.println("Tamaño de la lista: " + ldc.tamanio());
				System.out.println("--------------------------------------------------\n");
			} else {
				System.out.println("No se pudo modificar el contacto.");
				System.out.println("Tamaño de la lista: " + ldc.tamanio());
				System.out.println("--------------------------------------------------\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		imprimirMenu();
	}
	
	private static void validarBaja() {
		try {
			System.out.println("\nEliminar Contacto");
			System.out.print("Ingrese el nombre del contacto a eliminar: ");
			String nombres = entrada.nextLine();
			System.out.print("Ingrese el apellido del contacto a eliminar: ");
			String apellidos = entrada.nextLine();
	
			boolean eliminado = ldc.eliminarContacto(nombres, apellidos);
	
			if (eliminado) {
				System.out.println("\nEl contacto ha sido eliminado.");
				System.out.println("Tamaño de la lista: " + ldc.tamanio());
				System.out.println("--------------------------------------------------\n");
			} else {
				System.out.println("\nNo se encontró el contacto.");
				System.out.println("Tamaño de la lista: " + ldc.tamanio());
				System.out.println("--------------------------------------------------\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		imprimirMenu();
	}

	public static void buscarContacto1() {
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