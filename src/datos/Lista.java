package datos;

public class Lista <T>{
	//primer elemento de la lista
	private Nodo<T> cabeza;
	//total de elementos de la lista
	private int tamanio;

	/*
	* devuelve el tamaño de la lista
	*/
	public int getTamanio() {
		return tamanio;
	}

	//constructor por defecto
	public Lista() {
		cabeza = null;
		tamanio = 0;
	}

	public boolean esVacia() {
		if (cabeza == null) {
			return true;
        } else {
			return false;
        }
	}

	//Agrega un nuevo nodo al final de la lista
	public void agregar(T valor) {
		Nodo<T> nuevo = new Nodo<T>();
		nuevo.setValor(valor);
		if (esVacia()) {		
			cabeza = nuevo;
		} else {
			//se agrega el nuevo nodo al final de la lista
			Nodo<T> aux = cabeza;
			while (aux.getSiguiente() != null) {
			    aux = aux.getSiguiente();
			}
			aux.setSiguiente(nuevo);	
		}
		tamanio++;	
	}

	/*
    inserta un nuevo nodo en la lista
	* @param valor valor a agregar
	* @param pos posicion donde se insertara el nodo
	* @throws PosicionIlegatException excepcion en caso que la
	*  posición no exista
	*/
	public void insertar(T valor, int pos) throws PosicionIlegalException {
		//para insertar la posicion debe estar entre 0 y el tamanio
		//y ademas, debe haber al menos un dato en la lista
		if (pos >= 0 && pos <= tamanio) {
			Nodo<T> nuevo = new Nodo<T>();
			nuevo.setValor(valor);
			//el nodo a insertar esta al comienzo de la lista
			if (pos == 0) {
				nuevo.setSiguiente(cabeza);
				cabeza = nuevo;
			} else {
				//el nodo a insertar va al final de la lista
				if (pos == tamanio) {
					Nodo<T> aux = cabeza;
					while(aux.getSiguiente() != null) {
					aux = aux.getSiguiente();
				}
				aux.setSiguiente(nuevo);		
				} else { //el Nodo a insertar esta en medio
					Nodo<T> aux = cabeza;
					for (int i = 0; i < pos-2 ; i++) {
						aux = aux.getSiguiente();
					}
				    Nodo<T> siguiente = aux.getSiguiente();
					aux.setSiguiente(nuevo);
					nuevo.setSiguiente(siguiente);	
				}
		    }
			tamanio++;
		} else {
			throw new PosicionIlegalException();
	    }			
    }

	//devuelve el valor de una determinada posicion
	public T getValor(int pos) throws PosicionIlegalException {
		if(pos >= 0 && pos < tamanio) {
			T valor;
			if (pos == 0) {
			    valor = cabeza.getValor();
				return valor;
			} else {
				Nodo<T> aux = cabeza;
				for (int i = 0; i <= pos-1; i++){
					aux = aux.getSiguiente();
				}
				valor = aux.getValor();
			}
			return valor;
		} else {
			throw new PosicionIlegalException();
		}
    }

	//elimina un nodo de una determinada posicion
	public void remover(int pos) throws PosicionIlegalException {
		if (pos >= 0 && pos < tamanio) {
			if (pos == 0) {
				// El nodo a eliminar esta en la primera posicion
				cabeza = cabeza.getSiguiente();
				tamanio --;
            } else {
                Nodo<T> aux = cabeza;
				for (int i = 0; i < pos-2; i++) {
					aux = aux.getSiguiente();
				}	
				Nodo<T> prox = aux.getSiguiente();
                aux.setSiguiente(prox.getSiguiente());
				tamanio--;
            }
        } else {
			throw new PosicionIlegalException();
		}
					
	}

	//elimina todos los nodos de la lista
	public void limpiar() {
		cabeza= null;
		tamanio = 0;
	}
}