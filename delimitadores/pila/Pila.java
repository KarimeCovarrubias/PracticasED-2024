package delimitadores.pila;

import java.util.EmptyStackException;

public class Pila<T> {
    // Atributo cabeza, que apunta al tope de la pila
    private Nodo<T> cabeza;
    // Atributo que almacena el total de elementos de la pila
    private int tamanio;

    // Constructor por defecto
    public Pila() {
        cabeza = null;
        tamanio = 0;
    }

    // Devuelve el total de elementos de la pila
    public int getTamanio() {
        return tamanio;
    }

    // Verificar si la pila está vacía
    public boolean esVacia() {
        return (cabeza == null);
    }

    // Apilar un nuevo elemento
    public void apilar(T valor) {
        // Crear un nuevo nodo
        Nodo<T> nuevo = new Nodo<T>();
        // Fijar el valor dentro del nodo
        nuevo.setValor(valor);
        if (esVacia()) {
            // Cabeza apunta al nuevo nodo
            cabeza = nuevo;
        } else {
            // Se enlaza el campo siguiente de nuevo con la cabeza
            nuevo.setSiguiente(cabeza);
            // La nueva cabeza de la pila pasa a ser nuevo
            cabeza = nuevo;
        }
        // Incrementar el tamaño porque hay un nuevo nodo
        tamanio++;
    }

    // Eliminar un elemento de la pila
    public void retirar() {
        if (!esVacia()) {
            cabeza = cabeza.getSiguiente();
            tamanio--;
        }
    }

    // Devuelve el elemento almacenado en el tope de la pila
    public T cima() {
        if (!esVacia()) {
            return cabeza.getValor();
        } else {
            return null;
        }
    }

    // Busca un objeto en la pila y devuelve su posición
    public int buscar(Object obj) {
        Nodo<T> siguiente = cabeza;
        int pos = 0;

        // Recorremos desde la cima de la pila (cabeza)
        while (siguiente != null) {
            if (siguiente.getValor().equals(obj)) {
                return pos;  // Retornamos la posición actual (cima = 0)
            }
            siguiente = siguiente.getSiguiente();
            pos++;
        }

        return -1;  // Si no encontramos el objeto, retornamos -1
    }

    // Desapilar un elemento de la pila
    public T desapilar() {
        if (esVacia()) {
            throw new EmptyStackException();
        }
        T valor = cabeza.getValor();
        cabeza = cabeza.getSiguiente();
        tamanio--;
        return valor;
    }
}