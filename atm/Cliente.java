package atm;

import java.util.*;

public class Cliente {
    private String nombre;
    private String apellidos;
    private String nCliente;
    private String cuenta;
    
    public Cliente(String nombre, String apellidos, String cuenta) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nApellidos: " + apellidos + "\nCliente: " + nCliente;
    }

    public String getnCliente() {
        return nCliente;
    }

    public void setnCliente(String nCliente) {
        this.nCliente = nCliente;
    }
}