// No venia en el paquete datos
// LISTO
package datos;

public class Nodo<T> {
	private T valor;
	private Nodo <T> siguiente;
	
	public Nodo() {
		valor = null;
		siguiente = null;
	}
	
	public Nodo(T valor, Nodo<T> siguiente) {
		super();
		this.valor = valor;
		this.siguiente = siguiente;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public Nodo<T> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public String toString() {
		return "Nodo [valor=" + valor + ", siguiente=" + siguiente + "]";
	}
}
