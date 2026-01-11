package com.residenciasegura.menu.impl;

import com.residenciasegura.menu.MenuResidente;
import com.residenciasegura.modelo.Aviso;
import com.residenciasegura.modelo.TipoReporte;
import com.residenciasegura.modelo.TipoServicio;
import com.residenciasegura.modelo.Pago;
import com.residenciasegura.modelo.Reporte;
import com.residenciasegura.modelo.Usuario;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MenuResidenteImpl implements MenuResidente {
    
    private final Scanner scanner = new Scanner(System.in);
    private final Usuario usuarioActual;
    
    public MenuResidenteImpl(Usuario usuario) {
        this.usuarioActual = usuario;
    }
    
    @Override
    public boolean mostrarMenu() {
        System.out.println("\n=== MENU RESIDENTE ===");
        System.out.println("1. Pagar Servicios");
        System.out.println("2. Crear Reporte");
        System.out.println("3. Ver Avisos");
        System.out.println("4. Cerrar Sesion");
        System.out.print("\nSeleccione una opcion: ");
        
        int opcion = leerEntero();
        
        switch (opcion) {
            case 1:
                pagarServicios();
                return true;
            case 2:
                crearReporte();
                return true;
            case 3:
                verAvisos();
                return true;
            case 4:
                return false;
            default:
                System.out.println("Opción invalida");
                return true;
        }
    }
    
    private void pagarServicios() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== PAGAR SERVICIOS ===");
            System.out.println("1. Ver Mis Pagos");
            System.out.println("2. Crear Nuevo Pago");
            System.out.println("3. Pagar Servicio");
            System.out.println("4. Volver");
            System.out.print("\nSeleccione una opcion: ");
            
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    verMisPagos();
                    break;
                case 2:
                    crearPago();
                    break;
                case 3:
                    pagarServicio();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        }
    }
    
    private void verMisPagos() {
        System.out.println("\n=== MIS PAGOS ===");
        List<Pago> pagos = Pago.obtenerPorUsuario(usuarioActual.getIdUsuario());
        
        if (pagos.isEmpty()) {
            System.out.println("No tiene pagos registrados");
            return;
        }
        
        System.out.printf("%-5s %-15s %-12s %-12s %-12s%n",
            "ID", "Tipo", "Monto", "Fecha", "Estado");
        System.out.println("----------------------------------------------------------------------------------------");
        
        for (Pago p : pagos) {
            System.out.printf("%-5d %-15s $%-11.2f %-12s %-12s%n",
                p.getIdPago(), p.getTipoServicio().getValor(), p.getMonto(),
                p.getFechaPago(), p.getEstado());
        }
    }
    
    private void crearPago() {
        System.out.println("\n=== CREAR NUEVO PAGO ===");
        System.out.print("Tipo de servicio (alicuota/agua/luz/internet/mantenimiento/otro): ");
        String tipoStr = scanner.nextLine().toLowerCase();
        TipoServicio tipo = TipoServicio.fromString(tipoStr);
        
        System.out.print("Monto: ");
        BigDecimal monto = leerBigDecimal();
        
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        Date fecha = Date.valueOf(fechaStr);
        
        Pago pago = new Pago();
        pago.setIdUsuario(usuarioActual.getIdUsuario());
        pago.setTipoServicio(tipo);
        pago.setMonto(monto);
        pago.setFechaPago(fecha);
        pago.setEstado("pendiente");
        
        if (Pago.crearPago(pago)) {
            System.out.println(" Pago creado exitosamente");
        } else {
            System.out.println(" Error al crear pago");
        }
    }
    
    private void pagarServicio() {
        System.out.println("\n=== PAGAR SERVICIO ===");
        verMisPagos();
        
        System.out.print("\nID del pago a pagar: ");
        int idPago = leerEntero();
        
        Pago pago = Pago.obtenerPorId(idPago);
        if (pago == null) {
            System.out.println(" Pago no encontrado");
            return;
        }
        
        if (pago.getIdUsuario() != usuarioActual.getIdUsuario()) {
            System.out.println(" Solo puede pagar sus propios servicios");
            return;
        }
        
        if (!"pendiente".equalsIgnoreCase(pago.getEstado())) {
            System.out.println(" Solo se pueden pagar servicios pendientes");
            return;
        }
        
        if (Pago.marcarComoPagado(idPago)) {
            System.out.println(" Servicio pagado exitosamente");
        } else {
            System.out.println(" Error al procesar el pago");
        }
    }
    
    private void crearReporte() {
        System.out.println("\n=== CREAR REPORTE ===");
        System.out.print("Tipo (mantenimiento/seguridad/limpieza/otro): ");
        String tipoStr = scanner.nextLine().toLowerCase();
        TipoReporte tipo = TipoReporte.fromString(tipoStr);
        
        System.out.print("Ubicacion: ");
        String ubicacion = scanner.nextLine();
        
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Prioridad (baja/media/alta): ");
        String prioridadStr = scanner.nextLine().toLowerCase();
        
        Reporte reporte = new Reporte();
        reporte.setIdUsuario(usuarioActual.getIdUsuario());
        reporte.setTipo(tipo);
        reporte.setUbicacion(ubicacion);
        reporte.setDescripcion(descripcion);
        reporte.setPrioridad(prioridadStr);
        reporte.setEstado("pendiente");
        
        if (Reporte.crearReporte(reporte)) {
            System.out.println(" Reporte creado exitosamente");
        } else {
            System.out.println(" Error al crear reporte");
        }
    }
    
    private void verAvisos() {
        System.out.println("\n=== AVISOS ===");
        List<Aviso> avisos = Aviso.obtenerActivos();
        
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
                System.out.print("Por favor ingrese un número valido: ");
            }
        }
    }
    
    private BigDecimal leerBigDecimal() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingrese un monto valido: ");
            }
        }
    }
}

