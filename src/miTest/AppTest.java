/**
 * 
 */
package paquete;
/**
 * @author lourdesarmenta
 *
 */
import org.junit.jupiter.api.Test;

import datos.Contacto;
import datos.Lista;
import datos.PosicionIlegalException;

import static org.junit.jupiter.api.Assertions.*;

import negocios.ListadeContactos;
import negocios.Principal;




class AppTest {
	
	private  ListadeContactos listadeContactos;
	
	@Test 
    void tieneTamanio() {
        ListadeContactos c = new ListadeContactos();
        assertNotNull(c.tamanio(), "Debe tener un método");
    }
	@Test
	public void test_agregarContacto() throws PosicionIlegalException
	{
			
			listadeContactos = new ListadeContactos();
			
			boolean condicion =listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
			          "larmenta2@hotmail","6677121429","6677511511");
			assertTrue(condicion,"El contacto debe existir");
			
	} 
	@Test
	public void test_tamanio() throws PosicionIlegalException
	{
		 	
			listadeContactos = new ListadeContactos();
			
			listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
			          "larmenta2@hotmail","6677121429","6677511511");
			listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
			          "larmenta2@hotmail","6677121429","6677511511");
			listadeContactos.agregarContacto("Oscar","Armenta","Pirules 16",
			          "oscar_a@hotmail","6677505050","6677600600");
			
			assertEquals(2,listadeContactos.tamanio());
			
	} 
	@Test
	public void test_eliminarContacto() throws PosicionIlegalException
	{
		listadeContactos = new ListadeContactos();
		
		listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
		          "larmenta2@hotmail","6677121429","6677511511");
		listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
		          "larmenta2@hotmail","6677121429","6677511511");
		listadeContactos.agregarContacto("Oscar","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677600600");
		listadeContactos.agregarContacto("Oscar","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677600600");
		listadeContactos.agregarContacto("Daniel","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677611611");
		boolean condicion = listadeContactos.eliminarContacto("Lourdes", "Armenta");
		
		assertTrue(condicion,"El contacto debe ser eliminado");
	}
	@Test
	public void test_modificarContacto() throws PosicionIlegalException
	{
		listadeContactos = new ListadeContactos();
		
		listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
		          "larmenta2@hotmail","6677121429","6677511511");
		listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
		          "larmenta2@hotmail","6677121429","6677511511");
		listadeContactos.agregarContacto("Oscar","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677600600");
		listadeContactos.agregarContacto("Oscar","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677600600");
		listadeContactos.agregarContacto("Daniel","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677611611");
		boolean condicion = listadeContactos.modificarContacto("Lourdes","Armenta","Av.Circunvalacion",
		          "ma.lourdes@gmail","6681121212","6681511511");
		
		assertTrue(condicion,"El contacto debe ser modificado");
	}
	
	@Test
	public void test_mostrarTodos() throws PosicionIlegalException
	{
		listadeContactos = new ListadeContactos();
		Lista<Contacto> lista = new Lista<Contacto>();
		
		
		listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
		          "larmenta2@hotmail","6677121429","6677511511");
		listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
		          "larmenta2@hotmail","6677121429","6677511511");
		listadeContactos.agregarContacto("Oscar","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677600600");
		listadeContactos.agregarContacto("Oscar","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677600600");
		listadeContactos.agregarContacto("Daniel","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677611611");
		boolean condicion = listadeContactos.modificarContacto("Lourdes","Armenta","Av.Circunvalacion",
		          "ma.lourdes@gmail","6681121212","6681511511");
		lista = listadeContactos.mostrarTodosLosContactos();
		
		assertEquals(3,lista.getTamanio());
		
	}
	
	@Test
	public void test_mostrarTodos2() throws PosicionIlegalException
	{
		listadeContactos = new ListadeContactos();
		Lista<Contacto> lista = new Lista<Contacto>();
		
		
		listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
		          "larmenta2@hotmail","6677121429","6677511511");
		listadeContactos.agregarContacto("Lourdes","Armenta","Laguna de Viezca",
		          "larmenta2@hotmail","6677121429","6677511511");
		listadeContactos.agregarContacto("Oscar","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677600600");
		listadeContactos.agregarContacto("Oscar","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677600600");
		listadeContactos.agregarContacto("Daniel","Armenta","Pirules 16",
		          "oscar_a@hotmail","6677505050","6677611611");
		boolean condicion = listadeContactos.modificarContacto("Lourdes","Armenta","Av.Circunvalacion",
		          "ma.lourdes@gmail","6681121212","6681511511");
		lista = listadeContactos.mostrarTodosLosContactos();
		
		String correo=lista.getValor(0).getCorreo();
		boolean sonIguales = correo.equals("ma.lourdes@gmail");
		assertTrue(sonIguales,"El nombre debe ser ma.lourdes@gmail");
		
	}
	@Test
	public void test_agregarContactoDuplicado() throws PosicionIlegalException {
		listadeContactos = new ListadeContactos();
		
		listadeContactos.agregarContacto("Lourdes", "Armenta", "Laguna de Viezca",
				  "larmenta2@hotmail", "6677121429", "6677511511");
		boolean condicion = listadeContactos.agregarContacto("Lourdes", "Armenta", "Laguna de Viezca",
				  "larmenta2@hotmail", "6677121429", "6677511511");
		assertFalse(condicion, "No se debe permitir agregar un contacto duplicado");
	}
	@Test
		public void test_buscarContactoExistente() throws PosicionIlegalException {
			listadeContactos = new ListadeContactos();
			
			listadeContactos.agregarContacto("Lourdes", "Armenta", "Laguna de Viezca",
					  "larmenta2@hotmail", "6677121429", "6677511511");
			Contacto contacto = listadeContactos.buscarContacto("Lourdes", "Armenta");
			
			assertNotNull(contacto, "El contacto debe existir");
			assertEquals("Lourdes", contacto.getNombres());
			assertEquals("Armenta", contacto.getApellidos());
		}

		@Test
		public void test_buscarContactoInexistente() throws PosicionIlegalException {
			listadeContactos = new ListadeContactos();
			
			listadeContactos.agregarContacto("Lourdes", "Armenta", "Laguna de Viezca",
					  "larmenta2@hotmail", "6677121429", "6677511511");
			Contacto contacto = listadeContactos.buscarContacto("Oscar", "Armenta");
			
			assertNull(contacto, "El contacto no debe existir");
		}

		@Test
		public void test_modificarContactoInexistente() throws PosicionIlegalException {
			listadeContactos = new ListadeContactos();
			
			boolean condicion = listadeContactos.modificarContacto("Oscar", "Armenta", "Pirules 16",
					  "oscar_a@hotmail", "6677505050", "6677600600");
			
			assertFalse(condicion, "No se debe permitir modificar un contacto inexistente");
		}

		@Test
		public void test_eliminarContactoInexistente() throws PosicionIlegalException {
			listadeContactos = new ListadeContactos();
			
			boolean condicion = listadeContactos.eliminarContacto("Oscar", "Armenta");
			
			assertFalse(condicion, "No se debe permitir eliminar un contacto inexistente");
		}

		@Test
		public void test_agregarContactoConCamposVacios() throws PosicionIlegalException {
			listadeContactos = new ListadeContactos();
			
			boolean condicion = listadeContactos.agregarContacto("", "", "",
					  "", "", "");
			
			assertFalse(condicion, "No se debe permitir agregar un contacto con campos vacíos");
		}

		@Test
		public void test_modificarContactoConCamposVacios() throws PosicionIlegalException {
			listadeContactos = new ListadeContactos();
			
			listadeContactos.agregarContacto("Lourdes", "Armenta", "Laguna de Viezca",
					  "larmenta2@hotmail", "6677121429", "6677511511");
			boolean condicion = listadeContactos.modificarContacto("Lourdes", "Armenta", "",
					  "", "", "");
			
			assertFalse(condicion, "No se debe permitir modificar un contacto con campos vacíos");
		}


			
	
	
	
}
