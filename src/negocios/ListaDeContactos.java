package negocios;
import datos.Lista;
import datos.Contacto;
import datos.PosicionIlegalException;

/**
 * Clase que representa una lista de contacos personales
 * @author lourdesarmenta
 *
 */

public class ListadeContactos {
	//Atributo
	private Lista<Contacto> contactos;
	
	//Metodos
	//Constructor
	public ListadeContactos() {
		contactos = new Lista<Contacto>();
	}

	/**
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

	/*
	 * Agregar un nuevo contacto a la lista, si todavia no hay ningun contacto
	 * con los nombres y los apellidos dados
	 */
	public boolean agregarContacto(String nombres, 
		String apellidos, String direccion, String correo,
		String telefono,String celular) throws PosicionIlegalException {

		// Verificar si alguno de los campos está vacío o es nulo
		if (nombres == null || nombres.isEmpty() || apellidos == null || apellidos.isEmpty() ||
				direccion == null || direccion.isEmpty() ||
				correo == null || correo.isEmpty() ||
				telefono == null || telefono.isEmpty() ||
				celular == null || celular.isEmpty()) {
			return false;
		}

		Contacto con = buscarContacto(nombres, apellidos);
		if (con == null){ //se puede agregar el contacto
			Contacto nuevo = new Contacto(nombres, apellidos, direccion, correo, telefono,celular);
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
	public boolean eliminarContacto(String nombres, String apellidos) throws PosicionIlegalException {
		Contacto con = buscarContacto(nombres, apellidos);
		if(con != null) {
			for(int i = 0; i < this.contactos.getTamanio(); i++) {
				Contacto contAux = contactos.getValor(i);
				if(contAux.getNombres().equals(nombres) && contAux.getApellidos().equals(apellidos)) {
					contactos.remover(i);
				}
			}
			return true;
		} else {
			return false;	
		}
	}

	/**
	 * busca un contactp dado sus nombres y apellido
	 * 
	 */
	public Contacto buscarContacto(String nombres, String apellidos) throws PosicionIlegalException {
		for(int i = 0; i < contactos.getTamanio(); i++) {
			Contacto con = contactos.getValor(i);
			if(nombres.equals(con.getNombres()) && apellidos.equals(con.getApellidos())) {
				return con;
			}
		}
		return null; //No lo encontró, devuelve nulo
	}

	public boolean modificarContacto(String nombres, String apellidos, String direccion, 
									String correo, String telefono, String celular) 
									throws PosicionIlegalException {
		// Verificar si alguno de los campos está vacío o es nulo
		if (direccion == null || direccion.isEmpty() ||
				correo == null || correo.isEmpty() ||
				telefono == null || telefono.isEmpty() ||
				celular == null || celular.isEmpty()) {
        	return false;
    	}
		//Verificar que el contacto exista
		Contacto con = buscarContacto(nombres, apellidos);
		if(con == null) {
			return false;
		}

		//Modificar el contacto con los nombres nuevos
		con.setNombres(nombres);
		con.setApellidos(apellidos);
		con.setDireccion(direccion);
		con.setCorreo(correo);
		con.setTelefono(telefono);
		con.setCelular(celular);

		return true;	
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
