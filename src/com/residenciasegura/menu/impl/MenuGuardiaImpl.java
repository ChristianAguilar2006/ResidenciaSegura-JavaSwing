package com.residenciasegura.menu.impl;

import com.residenciasegura.controlador.ControladorAviso;
import com.residenciasegura.controlador.ControladorReporte;
import com.residenciasegura.menu.MenuGuardia;
import com.residenciasegura.modelo.Aviso;
import com.residenciasegura.modelo.Reporte;
import com.residenciasegura.modelo.Usuario;
import java.util.List;
import java.util.Scanner;

public class MenuGuardiaImpl implements MenuGuardia {
    
    private final Scanner scanner = new Scanner(System.in);
    private final Usuario usuarioActual;
    private final ControladorReporte controladorReporte;
    private final ControladorAviso controladorAviso;
    
    public MenuGuardiaImpl(Usuario usuario) {
        this.usuarioActual = usuario;
        this.controladorReporte = new ControladorReporte();
        this.controladorAviso = new ControladorAviso();
    }
    
    @Override
    public boolean mostrarMenu() {
        System.out.println("\n=== MENÚ GUARDIA ===");
        System.out.println("1. Ver Reportes");
        System.out.println("2. Atender Reporte");
        System.out.println("3. Ver Avisos");
        System.out.println("4. Cerrar Sesión");
        System.out.print("\nSeleccione una opción: ");
        
        int opcion = leerEntero();
        
        switch (opcion) {
            case 1:
                verReportes();
                return true;
            case 2:
                atenderReporte();
                return true;
            case 3:
                verAvisos();
                return true;
            case 4:
                return false;
            default:
                System.out.println("Opción inválida");
                return true;
        }
    }
    
    private void verReportes() {
        System.out.println("\n=== REPORTES PENDIENTES ===");
        List<Reporte> reportes = controladorReporte.obtenerPendientes();
        
        if (reportes.isEmpty()) {
            System.out.println("No hay reportes pendientes");
            return;
        }
        
        System.out.printf("%-5s %-20s %-15s %-12s %-20s%n",
            "ID", "Usuario", "Tipo", "Prioridad", "Fecha");
        System.out.println("----------------------------------------------------------------------------------------");
        
        for (Reporte r : reportes) {
            System.out.printf("%-5d %-20s %-15s %-12s %-20s%n",
                r.getIdReporte(), r.getNombreUsuario(), r.getTipo().getValor(),
                r.getPrioridad().getValor(), r.getFechaReporte());
        }
        
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    private void atenderReporte() {
        System.out.println("\n=== ATENDER REPORTE ===");
        verReportes();
        
        System.out.print("\nID del reporte a atender: ");
        int idReporte = leerEntero();
        
        if (controladorReporte.asignarGuardia(idReporte, usuarioActual.getIdUsuario())) {
            System.out.println("✓ Reporte asignado exitosamente. Estado cambiado a 'En Proceso'");
        } else {
            System.out.println("✗ Error al atender el reporte");
        }
    }
    
    private void verAvisos() {
        System.out.println("\n=== AVISOS ===");
        List<Aviso> avisos = controladorAviso.obtenerActivos();
        
        if (avisos.isEmpty()) {
            System.out.println("No hay avisos disponibles");
            return;
        }
        
        for (Aviso a : avisos) {
            System.out.println("\n--- " + a.getTitulo() + " ---");
            System.out.println("Tipo: " + a.getTipo().getValor());
            System.out.println("Mensaje: " + a.getMensaje());
            if (a.getFechaPublicacion() != null) {
                System.out.println("Fecha: " + a.getFechaPublicacion());
            }
        }
        
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    private int leerEntero() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingrese un número válido: ");
            }
        }
    }
}

