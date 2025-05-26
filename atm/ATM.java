package atm;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        int opc;
        System.out.println("--------------------------------------------------------");
        System.out.println("\t--- CREAR O CARGAR CUENTA ---");
        System.out.println("--------------------------------------------------------\n");

        System.out.print("Nombre: ");
        String nombre = Keyboard.readString();
        System.out.print("Apellidos: ");
        String apellidos = Keyboard.readString();

        Cuenta cuenta = new Cuenta();
        cuenta.inicializarConCliente(nombre, apellidos);

        // Si no tiene PIN (usuario nuevo), pedir que cree uno
        if (cuenta.getPin() == null || cuenta.getPin().isEmpty()) {
            System.out.println("⚠️ Usuario nuevo detectado. Debe crear un PIN para su cuenta.");
            System.out.print("Ingrese nuevo PIN: ");
            String pinNuevo = Keyboard.readString();
            cuenta.setPin(pinNuevo);
            // Guardar estado para que quede registrado el PIN
            cuenta.guardarEstadoCuenta();
            System.out.println("✅ PIN creado correctamente. Ahora puede iniciar sesión.");
        }

        boolean autenticado = false;
        while (true) {
            if (!autenticado) {
                System.out.print("🔐 Ingrese su PIN: ");
                String pinIngresado = Keyboard.readString();
                if (cuenta.autenticar(pinIngresado)) {
                    autenticado = true;
                    System.out.println("✅ Autenticación exitosa.");
                } else {
                    System.out.println("❌ PIN incorrecto.");
                    if (cuenta.isCuentaBloqueada()) {
                        System.out.println("⚠️ Cuenta bloqueada. Contacte al banco.");
                        break;
                    }
                    continue;
                }
            }

            System.out.println("\n--------------------------------------------------------");
            System.out.println("\t\t\tMENU");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Depositar saldo");
            System.out.println("2. Retirar saldo");
            System.out.println("3. Mostrar saldo");
            System.out.println("4. Mostrar movimientos");
            System.out.println("5. Cambiar PIN");
            System.out.println("6. Guardar historial");
            System.out.println("7. Guardar estado de cuenta");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opc = Keyboard.readInt();

            switch (opc) {
                case 1:
                    System.out.print("Saldo a depositar: ");
                    double saldo = Keyboard.readDouble();
                    cuenta.depositar(saldo);
                    System.out.println("✅ Depósito exitoso.");
                    break;
                case 2:
                    System.out.print("Saldo a retirar: ");
                    double retirarSaldo = Keyboard.readDouble();
                    
                    cuenta.retirar(retirarSaldo);
                    System.out.println("✅ Retiro exitoso.");
                    
                    break;
                case 3:
                    cuenta.obtenerSaldo();
                    break;
                case 4:
                    System.out.println("📜 Últimos movimientos:");
                    for (String mov : cuenta.getHistorial()) {
                        System.out.println(mov);
                    }
                    break;
                case 5:
                    System.out.print("Nuevo PIN: ");
                    String nuevoPin = Keyboard.readString();
                    cuenta.setPin(nuevoPin);
                    System.out.println("✅ PIN cambiado. Por seguridad, vuelve a iniciar sesión.");
                    autenticado = false;
                    break;
                case 6:
                    cuenta.guardarHistorial();
                    break;
                case 7:
                    cuenta.guardarEstadoCuenta();
                    break;
                case 0:
                    System.out.println("👋 Gracias por usar el cajero. Hasta luego.");
                    return;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        }
    }
}
