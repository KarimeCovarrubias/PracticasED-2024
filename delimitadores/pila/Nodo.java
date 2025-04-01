package delimitadores.pila;
public class Nodo <T> {
    //Atributo valor de tipo T. Almacena la referencia al
    //objeto que se guarda en el nodo
    private T valor;
    //Referencia al siguiente nodo enlazado
    Nodo<T> siguiente;
    
    //constructor por defector
    public Nodo(){
        valor =null;
        siguiente=null;
    }
    //constructor llenp
    public Nodo(T valor, Nodo<T> siguiente) {
        this.valor = valor;
        this.siguiente = siguiente;
    }

    //devuelve el valor
    public T getValor(){
        return valor;
    }

    //modificar el valor
    public void setValor(T valor){
        this.valor=valor;
    }

    //devolver el atributo siguiente
    public Nodo<T> getSiguiente(){
        return siguiente;
    }

    //modificar el valor de siguiente
    public void setSiguiente(Nodo<T> siguiente){
        this.siguiente = siguiente;
    }
  
    
}
