package negocios;
import datos.Lista;
import datos.Contacto;
import datos.PosicionIlegalException;

public class ListadeContactos {
	//Atributo
	private Lista<Contacto> contactos;
	
	//Constructor
	public ListadeContactos() {
		contactos = new Lista<Contacto>();
	}

	/*
	 * Retorna una lista de todos los contactos
	 */
	public Lista<Contacto> mostrarTodosLosContactos() {
		try {
			System.out.println("NUM \t\tNOMBRES\tAPELLIDOS \tDIRECCIÓN \t\tCORREO \t\t\tTELÉFONO \tCELULAR ");
			for (int i = 0; i < contactos.getTamanio(); i++) {
			   System.out.print("Num: " + (i + 1) + "-> ");
			//    System.out.println(contactos.getValor(i).getNombres() + "    " +
			// 	                   contactos.getValor(i).getApellidos() + "   " +
			// 			           contactos.getValor(i).getDireccion() + "   " +
			// 	                   contactos.getValor(i).getCorreo() + "   " +
			// 			           contactos.getValor(i).getTelefono() + "   " +
			// 	                   contactos.getValor(i).getCelular());
			System.out.printf("%13s %10s %20s %20s %20s %20s\n", contactos.getValor(i).getNombres(), 
							contactos.getValor(i).getApellidos(), contactos.getValor(i).getDireccion(), contactos.getValor(i).getCorreo(), 
							contactos.getValor(i).getTelefono(), contactos.getValor(i).getCelular());
		   }		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return contactos;
	}

	/* Modifica los datos de un contacto dado sus nombres y apellidos
	 * Si el usuario existe se modifican sus datos y regresa true
	 * Si no existe no puede modificarlo y regresa false
	 * @param nombres
	 * @param apellidos
	 * @param direccion
	 * @param correo
	 * @param telefono
	 * @param celular
	 */

	/*
	 * Agregar un nuevo contacto a la lista, si todavia no hay ningun contacto
	 * con los nombres y los apellidos dados
	 * 
	 */
	public boolean agregarContacto(String nombres, String apellidos, String direccion, String correo, String telefono, String celular) throws PosicionIlegalException {
		// Verificar si alguno de los campos está vacío o es nulo
		Contacto con = buscarContacto(nombres, apellidos);
		if (con == null) { // Se puede agreagar el contacto
			Contacto nuevo = new Contacto(nombres, apellidos, direccion, correo, telefono, celular);
			contactos.agregar(nuevo);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Elimina un contacto dados sus nombres y apellidos
	 * si el usuario existe en la lista se elimina y regresa true
	 * falso si no lo encuentra
	 * @param nombres
	 * @param apellidos
	 * @return
	 * @throws PosicionIlegalException
	 */

	// Elimina un contacto si este existe en la lista y retorna un true en caso contrario de que no exista
	public boolean eliminarContacto(String nombres, String apellidos) throws PosicionIlegalException {
		Contacto con = buscarContacto(nombres, apellidos);
		if(con != null) {
			for(int i = 0; i < contactos.getTamanio(); i++) {
				Contacto conAux = contactos.getValor(i);
				if(nombres.equals(conAux.getNombres()) && apellidos.equals(conAux.getApellidos())) {
					contactos.remover(i);
				}
			}
			return true;
		} else {
			return false;	
		}
	}

	// Busca un contacto
	public Contacto buscarContacto(String nombres, String apellidos) throws PosicionIlegalException {
		for(int i = 0; i < contactos.getTamanio(); i++) {
			Contacto con = contactos.getValor(i);
			if(nombres.equals(con.getNombres()) && apellidos.equals(con.getApellidos())) {
				return con;
			}
		}
		return null; //No lo encontró, devuelve nulo
	}

	public boolean modificarContacto(String nombres, String apellidos, String direccion, String correo, String telefono, String celular) throws PosicionIlegalException {
		Contacto con = buscarContacto(nombres, apellidos);
		if(con == null) {
			return false;
		} else {
			//Modificar el contacto con los nombres nuevos
			con.setNombres(nombres);
			con.setApellidos(apellidos);
			con.setDireccion(direccion);
			con.setCorreo(correo);
			con.setTelefono(telefono);
			con.setCelular(celular);

			return true;	
		}
	}

	/**
	 * 
	 * @return el tamaño de la lista
	*/
	public int tamanio() {
		return contactos.getTamanio();
	}
	
	//Obtener contacto
	public Contacto obtenerContacto(int index) throws PosicionIlegalException {
		if (index >= 0 && index < contactos.getTamanio()) {
			return contactos.getValor(index);
		}
		throw new PosicionIlegalException();
	}
}
