package com.residenciasegura;

import com.residenciasegura.menu.MenuAdmin;
import com.residenciasegura.menu.MenuGuardia;
import com.residenciasegura.menu.MenuResidente;
import com.residenciasegura.menu.impl.MenuAdminImpl;
import com.residenciasegura.menu.impl.MenuGuardiaImpl;
import com.residenciasegura.menu.impl.MenuResidenteImpl;
import com.residenciasegura.modelo.Usuario;
import java.util.Scanner;

public class ResidenciaSegura {
    
    private static Scanner scanner = new Scanner(System.in);
    private static Usuario usuarioActual = null;
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   RESIDENCIA SEGURA - Sistema de Gestion");
        System.out.println("========================================\n");
        
        boolean continuar = true;
        while (continuar) {
            if (usuarioActual == null) {
                continuar = mostrarMenuLogin();
            } else {
                continuar = mostrarMenuPrincipal();
            }
        }
        
        System.out.println("\nGracias por usar Residencia Segura. Hasta pronto!");
        scanner.close();
    }
    
    private static boolean mostrarMenuLogin() {
        System.out.println("\n=== INICIO DE SESION ===");
        System.out.print("Correo electronico: ");
        String correo = scanner.nextLine();
        
        System.out.print("Contrasena: ");
        String password = scanner.nextLine();
        
        usuarioActual = Usuario.autenticarUsuario(correo, password);
        
        if (usuarioActual != null) {
            System.out.println("\nBienvenido " + usuarioActual.getNombre() + "!");
            System.out.println("Rol: " + usuarioActual.getRol().getValor());
            return true;
        } else {
            System.out.println("\n Credenciales invalidas");
            System.out.print("¿Desea intentar de nuevo? (s/n): ");
            String respuesta = scanner.nextLine().toLowerCase();
            return respuesta.equals("s");
        }
    }
    
    private static boolean mostrarMenuPrincipal() {
        switch (usuarioActual.getRol()) {
            case ADMIN:
                MenuAdmin menuAdmin = new MenuAdminImpl(usuarioActual);
                boolean continuarAdmin = menuAdmin.mostrarMenu();
                if (!continuarAdmin) {
                    usuarioActual = null;
                }
                return true;
            case RESIDENTE:
                MenuResidente menuResidente = new MenuResidenteImpl(usuarioActual);
                boolean continuarResidente = menuResidente.mostrarMenu();
                if (!continuarResidente) {
                    usuarioActual = null;
                }
                return true;
            case GUARDIA:
                MenuGuardia menuGuardia = new MenuGuardiaImpl(usuarioActual);
                boolean continuarGuardia = menuGuardia.mostrarMenu();
                if (!continuarGuardia) {
                    usuarioActual = null;
                }
                return true;
            default:
                return false;
        }
    }
}
