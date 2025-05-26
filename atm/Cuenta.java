package atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.text.DecimalFormat;

public class Cuenta {
    DecimalFormat formato = new DecimalFormat("###,###.00");

    private String numeroCuenta;
    private String pin;
    private double saldo;
    private List<String> historial;
    private boolean cuentaBloqueada;
    private int intentos;
    private Cliente cliente;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<String> getHistorial() {
        return historial;
    }

    public void setHistorial(List<String> historial) {
        this.historial = historial;
    }

    public boolean isCuentaBloqueada() {
        return cuentaBloqueada;
    }

    public void setCuentaBloqueada(boolean cuentaBloqueada) {
        this.cuentaBloqueada = cuentaBloqueada;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    @Override
    public String toString() {
        return " Número de Cuenta" + numeroCuenta + "\nSaldo: $" + formato.format(saldo) + "\nHistorial: \n" + historial;
    }

    public boolean autenticar(String pinIngresado) {
        if (cuentaBloqueada) {
            historial.add("[" + fecha() + "]   Intento de acceso. Cuenta bloqueada.");
            return false;
        }
        if (pin.equals(pinIngresado)) {
            intentos = 0;
            historial.add("[" + fecha() + "]   Acceso exitoso.");
            return true;
        } else {
            intentos++;
            historial.add("[" + fecha() + "]   PIN incorrecto. Intento #" + intentos);
            if (intentos >= 3) {
                historial.add("[" + fecha() + "]   Cuenta bloqueada por múltiples intentos fallidos");
                cuentaBloqueada = true;
            }
            return false;
        }
    }

    public void depositar(double montoAdepositar) {
        if (montoAdepositar > 0) {
            saldo += montoAdepositar;
            historial.add("[" + fecha() + "]   | Saldo: " + formato.format(saldo) + "|  Monto: " + formato.format(montoAdepositar) + " |");
        } else {
            System.out.println("Introduzca un monto a depositar mayor a 0.");
        }
    }

    public boolean retirar(double montoAretirar) {
        if (montoAretirar > 0) {
            saldo -= montoAretirar;
            historial.add("[" + fecha() + "]   | Saldo: " + formato.format(saldo) + "|  Monto: " + formato.format(montoAretirar) + " |");
            return true;
        } else {
            System.out.println("Introduzca un monto a retirar mayor a 0.");
            historial.add("[" + fecha() + "]   Intento fallido de retirar");
            return false;
        }
    }

    public boolean cambiarPin(String pinActual, String pinNuevo) {
        if (!pinActual.equals(pin)) {
            System.out.println("PIN incorrecto. Intente de nuevo.");
            return false;
        }

        if (pinNuevo.equals(pin)) {
            System.out.println("El nuevo PIN no tiene que ser igual al anterior. Intente con otro PIN.");
            return false;
        }

        pin = pinNuevo;
        historial.add("[" + fecha() + "]   PIN cambiado exitosamente.");
        System.out.println("PIN cambiado correctamente. Debes volver a iniciar sesión.");
        return true;
    }

    private String fecha() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm::ss");
        return ahora.format(formato);
    }

    public void cargarHistorial() {
        String nombreArchivo = "atm/Historial/Historial_" + numeroCuenta + ".txt";
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try (BufferedReader leer = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = leer.readLine()) != null) {
                historial.add(linea);
            }
            System.out.println("Historial cargado con éxito.");
            } catch (IOException e) {
                System.out.println("Error al cargar al historial: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró el historial.");
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void obtenerSaldo() {
        double sal = saldo;
        historial.add("[" + fecha() + "]   Consulta saldo: " + formato.format(sal));
        System.out.println("💰 Saldo actual: $" + formato.format(sal));
    }

    public Cuenta() {
        this.historial = new ArrayList<>();
        this.saldo = 0;
        this.cuentaBloqueada = false;
        this.intentos = 0;
    }

    // Método principal para inicializar o cargar cuenta según nombre y apellido
    public void inicializarConCliente(String nombre, String apellidos) {
    this.cliente = new Cliente(nombre, apellidos, generarNCliente());
    String claveArchivo = nombre.toLowerCase() + "_" + apellidos.toLowerCase();

    File carpetaHistorial = new File("atm/Historial");
    File carpetaEdoCta = new File("atm/EdoCta");

    if (!carpetaHistorial.exists()) carpetaHistorial.mkdirs();
    if (!carpetaEdoCta.exists()) carpetaEdoCta.mkdirs();

    File archivoHistorial = new File(carpetaHistorial, "Historial_" + claveArchivo + ".txt");
    File archivoEstado = new File(carpetaEdoCta, "EdoCta_" + claveArchivo + ".txt");

    if (archivoEstado.exists()) {
        System.out.println("✅ Se encontró cuenta existente, cargando datos...");
        cargarEstadoCuenta(archivoEstado);
        cargarHistorial(archivoHistorial);
    } else {
        System.out.println("⚠️ No existe cuenta previa, creando nueva cuenta...");
        this.numeroCuenta = generarNumeroCuenta();
        this.pin = "";
        this.saldo = 0;
        this.historial = new ArrayList<>();
        this.cuentaBloqueada = false;
        this.intentos = 0;
    }
}



    private String generarNCliente() {
        Random random = new Random();
        int nClienteR = random.nextInt(100000);
        return String.valueOf(nClienteR);
    }

    // Generar número de cuenta aleatorio 10 dígitos
    private String generarNumeroCuenta() {
        Random random = new Random();
        long min = 1_000_000_000L;
        long max = 9_999_999_999L;
        long nRandom = min + (long)(random.nextDouble() * (max - min + 1));
        return String.valueOf(nRandom);
    }

    // Guardar historial
    public void guardarHistorial() {
        String claveArchivo = cliente.getNombre().toLowerCase() + "_" + cliente.getApellidos().toLowerCase();
        File carpetaHistorial = new File("atm/Historial");
        if (!carpetaHistorial.exists()) carpetaHistorial.mkdirs();

        try (FileWriter escribir = new FileWriter(new File(carpetaHistorial, "Historial_" + claveArchivo + ".txt"))) {
            escribir.write("---------------------------------------------------------------\n");
            escribir.write("\t\t\t---- HISTORIAL --- \n");
            escribir.write("---------------------------------------------------------------\n");
            for (String linea : historial) {
                escribir.write(linea + "\n");
            }
            System.out.println("💾 Historial guardado.");
        } catch (IOException e) {
            System.out.println("Error al guardar el historial: " + e.getMessage());
        }
    }

    // Cargar historial
    public void cargarHistorial(File archivo) {
        if (archivo.exists()) {
            try (BufferedReader leer = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = leer.readLine()) != null) {
                    historial.add(linea);
                }
                System.out.println("Historial cargado con éxito.");
            } catch (IOException e) {
                System.out.println("Error al cargar el historial: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró historial para esta cuenta.");
        }
    }

    // Guardar estado de cuenta
    public void guardarEstadoCuenta() {
        String claveArchivo = cliente.getNombre().toLowerCase() + "_" + cliente.getApellidos().toLowerCase();
        File carpetaEdoCta = new File("atm/EdoCta");
        if (!carpetaEdoCta.exists()) carpetaEdoCta.mkdirs();

        File archivo = new File(carpetaEdoCta, "EdoCta_" + claveArchivo + ".txt");

        try (FileWriter escribir = new FileWriter(archivo)) {
            escribir.write("---------------------------------------------------------------\n");
            escribir.write("\n\t\t---- Estado de Cuenta --- \n");
            escribir.write("---------------------------------------------------------------\n");
            escribir.write("Nombre: " + cliente.getNombre() + "\n");
            escribir.write("Apellidos: " + cliente.getApellidos() + "\n");
            escribir.write("Número de cliente: " + cliente.getnCliente() + "\n");
            escribir.write("Número de Cuenta: " + numeroCuenta + "\n");
            escribir.write("Saldo: " + formato.format(saldo) + "\n");
            escribir.write("¿Cuenta bloqueada? " + cuentaBloqueada + "\n");
            escribir.write("Intentos: " + intentos + "\n");
            escribir.write("PIN: " + pin);
            System.out.println("Estado de cuenta guardado.");
        } catch (IOException e) {
            System.err.println("Error al guardar estado de cuenta: " + e.getMessage());
        }
    }

    // Cargar estado de cuenta
    public void cargarEstadoCuenta(File archivo) {
    if (archivo.exists()) {
        try (BufferedReader leer = new BufferedReader(new FileReader(archivo))) {
            String linea;
            String nombre = null, apellidos = null, nCliente = null;
            String numeroCuenta = null;
            double saldo = 0;
            boolean cuentaBloqueada = false;
            int intentos = 0;
            String pin = null;

            while ((linea = leer.readLine()) != null) {
                if (!linea.contains(": ")) continue; // Ignorar líneas sin ": "
                String[] partes = linea.split(": ", 2);
                if (partes.length < 2) continue;

                String clave = partes[0].trim();
                String valor = partes[1].trim();

                switch (clave) {
                    case "Nombre":
                        nombre = valor;
                        break;
                    case "Apellidos":
                        apellidos = valor;
                        break;
                    case "Número de cliente":
                        nCliente = valor;
                        break;
                    case "Número de Cuenta":
                        numeroCuenta = valor;
                        break;
                    case "Saldo":
                        // Quitar comas y símbolos antes de parsear
                        saldo = Double.parseDouble(valor.replace(",", "").replace("$", ""));
                        break;
                    case "¿Cuenta bloqueada?":
                        cuentaBloqueada = Boolean.parseBoolean(valor);
                        break;
                    case "Intentos":
                        intentos = Integer.parseInt(valor);
                        break;
                    case "PIN":
                        pin = valor;
                        break;
                }
            }

            // Validar que se cargaron datos mínimos
            if (nombre == null || apellidos == null || nCliente == null || numeroCuenta == null || pin == null) {
                System.out.println("❌ Datos incompletos en archivo de estado de cuenta.");
                return;
            }

            this.cliente = new Cliente(nombre, apellidos, nCliente);
            this.numeroCuenta = numeroCuenta;
            this.saldo = saldo;
            this.cuentaBloqueada = cuentaBloqueada;
            this.intentos = intentos;
            this.pin = pin;

            System.out.println("✅ Estado de cuenta cargado correctamente.");

        } catch (IOException | NumberFormatException e) {
            System.out.println("❌ Error al cargar el estado de cuenta: " + e.getMessage());
        }
    } else {
        System.out.println("❌ No existe el archivo: " + archivo.getPath());
    }
}


}