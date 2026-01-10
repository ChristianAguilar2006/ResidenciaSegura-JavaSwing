package com.residenciasegura.menu.impl;

import com.residenciasegura.controlador.ControladorAviso;
import com.residenciasegura.controlador.ControladorPago;
import com.residenciasegura.controlador.ControladorReporte;
import com.residenciasegura.controlador.ControladorUsuario;
import com.residenciasegura.menu.MenuAdmin;
import com.residenciasegura.modelo.Aviso;
import com.residenciasegura.modelo.Pago;
import com.residenciasegura.modelo.Reporte;
import com.residenciasegura.modelo.Usuario;
import java.util.List;
import java.util.Scanner;

public class MenuAdminImpl implements MenuAdmin {
    
    private final Scanner scanner = new Scanner(System.in);
    private final Usuario usuarioActual;
    private final ControladorUsuario controladorUsuario;
    private final ControladorPago controladorPago;
    private final ControladorReporte controladorReporte;
    private final ControladorAviso controladorAviso;
    
    public MenuAdminImpl(Usuario usuario) {
        this.usuarioActual = usuario;
        this.controladorUsuario = new ControladorUsuario();
        this.controladorPago = new ControladorPago();
        this.controladorReporte = new ControladorReporte();
        this.controladorAviso = new ControladorAviso();
    }
    
    @Override
    public boolean mostrarMenu() {
        System.out.println("\n=== MENÚ ADMINISTRADOR ===");
        System.out.println("1. Gestión de Usuarios");
        System.out.println("2. Consultar Pagos");
        System.out.println("3. Gestión de Reportes");
        System.out.println("4. Gestión de Avisos");
        System.out.println("5. Cerrar Sesión");
        System.out.print("\nSeleccione una opción: ");
        
        int opcion = leerEntero();
        
        switch (opcion) {
            case 1:
                gestionarUsuarios();
                return true;
            case 2:
                consultarPagos();
                return true;
            case 3:
                gestionarReportes();
                return true;
            case 4:
                gestionarAvisos();
                return true;
            case 5:
                return false;
            default:
                System.out.println("Opción inválida");
                return true;
        }
    }
    
    private void gestionarUsuarios() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== GESTIÓN DE USUARIOS ===");
            System.out.println("1. Listar Usuarios");
            System.out.println("2. Crear Usuario");
            System.out.println("3. Actualizar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("5. Volver");
            System.out.print("\nSeleccione una opción: ");
            
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    listarUsuarios();
                    break;
                case 2:
                    crearUsuario();
                    break;
                case 3:
                    actualizarUsuario();
                    break;
                case 4:
                    eliminarUsuario();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    
    private void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUARIOS ===");
        List<Usuario> usuarios = controladorUsuario.obtenerTodos();
        
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
            return;
        }
        
        System.out.printf("%-5s %-30s %-30s %-15s %-20s %-15s%n", 
            "ID", "Nombre", "Correo", "Rol", "Departamento", "Teléfono");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        
        for (Usuario u : usuarios) {
            System.out.printf("%-5d %-30s %-30s %-15s %-20s %-15s%n",
                u.getIdUsuario(), u.getNombre(), u.getCorreo(), 
                u.getRol().getValor(), u.getDepartamento(), u.getTelefono());
        }
    }
    
    private void crearUsuario() {
        System.out.println("\n=== CREAR USUARIO ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        
        System.out.print("Rol (admin/residente/guardia): ");
        String rolStr = scanner.nextLine().toLowerCase();
        Usuario.Rol rol = Usuario.Rol.fromString(rolStr);
        
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setContrasena(password);
        usuario.setRol(rol);
        usuario.setDepartamento(departamento);
        usuario.setTelefono(telefono);
        
        if (controladorUsuario.crearUsuario(usuario)) {
            System.out.println("✓ Usuario creado exitosamente");
        } else {
            System.out.println("✗ Error al crear usuario");
        }
    }
    
    private void actualizarUsuario() {
        System.out.println("\n=== ACTUALIZAR USUARIO ===");
        System.out.print("ID del usuario a actualizar: ");
        int id = leerEntero();
        
        Usuario usuario = controladorUsuario.obtenerPorId(id);
        if (usuario == null) {
            System.out.println("✗ Usuario no encontrado");
            return;
        }
        
        System.out.println("Usuario actual: " + usuario.getNombre());
        System.out.print("Nuevo nombre (Enter para mantener): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) usuario.setNombre(nombre);
        
        System.out.print("Nuevo correo (Enter para mantener): ");
        String correo = scanner.nextLine();
        if (!correo.isEmpty()) usuario.setCorreo(correo);
        
        System.out.print("Nuevo departamento (Enter para mantener): ");
        String departamento = scanner.nextLine();
        if (!departamento.isEmpty()) usuario.setDepartamento(departamento);
        
        System.out.print("Nuevo teléfono (Enter para mantener): ");
        String telefono = scanner.nextLine();
        if (!telefono.isEmpty()) usuario.setTelefono(telefono);
        
        if (controladorUsuario.actualizarUsuario(usuario)) {
            System.out.println("✓ Usuario actualizado exitosamente");
        } else {
            System.out.println("✗ Error al actualizar usuario");
        }
    }
    
    private void eliminarUsuario() {
        System.out.println("\n=== ELIMINAR USUARIO ===");
        System.out.print("ID del usuario a eliminar: ");
        int id = leerEntero();
        
        if (id == usuarioActual.getIdUsuario()) {
            System.out.println("✗ No puede eliminar su propio usuario");
            return;
        }
        
        System.out.print("¿Está seguro? (s/n): ");
        String confirmacion = scanner.nextLine().toLowerCase();
        
        if (confirmacion.equals("s")) {
            if (controladorUsuario.eliminarUsuario(id)) {
                System.out.println("✓ Usuario eliminado exitosamente");
            } else {
                System.out.println("✗ Error al eliminar usuario");
            }
        }
    }
    
    private void consultarPagos() {
        System.out.println("\n=== CONSULTA DE PAGOS ===");
        List<Pago> pagos = controladorPago.obtenerTodos();
        
        if (pagos.isEmpty()) {
            System.out.println("No hay pagos registrados");
            return;
        }
        
        System.out.printf("%-5s %-20s %-15s %-12s %-12s %-12s %-15s%n",
            "ID", "Usuario", "Tipo", "Monto", "Fecha", "Estado", "Método");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        
        for (Pago p : pagos) {
            System.out.printf("%-5d %-20s %-15s $%-11.2f %-12s %-12s %-15s%n",
                p.getIdPago(), p.getNombreUsuario(), p.getTipoServicio().getValor(),
                p.getMonto(), p.getFechaPago(), p.getEstado().getValor(), 
                p.getMetodoPago().getValor());
        }
        
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
    private void gestionarReportes() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== GESTIÓN DE REPORTES ===");
            System.out.println("1. Listar Reportes");
            System.out.println("2. Ver Detalle de Reporte");
            System.out.println("3. Eliminar Reporte");
            System.out.println("4. Volver");
            System.out.print("\nSeleccione una opción: ");
            
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    listarReportes();
                    break;
                case 2:
                    verDetalleReporte();
                    break;
                case 3:
                    eliminarReporte();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    
    private void listarReportes() {
        System.out.println("\n=== LISTA DE REPORTES ===");
        List<Reporte> reportes = controladorReporte.obtenerTodos();
        
        if (reportes.isEmpty()) {
            System.out.println("No hay reportes registrados");
            return;
        }
        
        System.out.printf("%-5s %-20s %-15s %-12s %-20s %-12s%n",
            "ID", "Usuario", "Tipo", "Prioridad", "Fecha", "Estado");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        
        for (Reporte r : reportes) {
            System.out.printf("%-5d %-20s %-15s %-12s %-20s %-12s%n",
                r.getIdReporte(), r.getNombreUsuario(), r.getTipo().getValor(),
                r.getPrioridad().getValor(), r.getFechaReporte(), r.getEstado().getValor());
        }
    }
    
    private void verDetalleReporte() {
        System.out.print("\nID del reporte: ");
        int id = leerEntero();
        
        Reporte reporte = controladorReporte.obtenerPorId(id);
        if (reporte == null) {
            System.out.println("✗ Reporte no encontrado");
            return;
        }
        
        System.out.println("\n=== DETALLE DEL REPORTE ===");
        System.out.println("ID: " + reporte.getIdReporte());
        System.out.println("Usuario: " + reporte.getNombreUsuario());
        System.out.println("Tipo: " + reporte.getTipo().getValor());
        System.out.println("Descripción: " + reporte.getDescripcion());
        System.out.println("Ubicación: " + reporte.getUbicacion());
        System.out.println("Prioridad: " + reporte.getPrioridad().getValor());
        System.out.println("Estado: " + reporte.getEstado().getValor());
        System.out.println("Fecha: " + reporte.getFechaReporte());
        if (reporte.getNombreGuardia() != null) {
            System.out.println("Guardia asignado: " + reporte.getNombreGuardia());
        }
    }
    
    private void eliminarReporte() {
        System.out.print("\nID del reporte a eliminar: ");
        int id = leerEntero();
        
        System.out.print("¿Está seguro? (s/n): ");
        String confirmacion = scanner.nextLine().toLowerCase();
        
        if (confirmacion.equals("s")) {
            if (controladorReporte.eliminarReporte(id)) {
                System.out.println("✓ Reporte eliminado exitosamente");
            } else {
                System.out.println("✗ Error al eliminar reporte");
            }
        }
    }
    
    private void gestionarAvisos() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== GESTIÓN DE AVISOS ===");
            System.out.println("1. Listar Avisos");
            System.out.println("2. Crear Aviso");
            System.out.println("3. Actualizar Aviso");
            System.out.println("4. Eliminar Aviso");
            System.out.println("5. Volver");
            System.out.print("\nSeleccione una opción: ");
            
            int opcion = leerEntero();
            
            switch (opcion) {
                case 1:
                    listarAvisos();
                    break;
                case 2:
                    crearAviso();
                    break;
                case 3:
                    actualizarAviso();
                    break;
                case 4:
                    eliminarAviso();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    
    private void listarAvisos() {
        System.out.println("\n=== LISTA DE AVISOS ===");
        List<Aviso> avisos = controladorAviso.obtenerTodos();
        
        if (avisos.isEmpty()) {
            System.out.println("No hay avisos registrados");
            return;
        }
        
        System.out.printf("%-5s %-30s %-15s %-10s%n", "ID", "Título", "Tipo", "Activo");
        System.out.println("----------------------------------------------------------------");
        
        for (Aviso a : avisos) {
            System.out.printf("%-5d %-30s %-15s %-10s%n",
                a.getIdAviso(), a.getTitulo(), a.getTipo().getValor(), 
                a.isActivo() ? "Sí" : "No");
        }
    }
    
    private void crearAviso() {
        System.out.println("\n=== CREAR AVISO ===");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Mensaje: ");
        String mensaje = scanner.nextLine();
        
        System.out.print("Tipo (informativo/alerta/mantenimiento/emergencia): ");
        String tipoStr = scanner.nextLine().toLowerCase();
        Aviso.TipoAviso tipo = Aviso.TipoAviso.fromString(tipoStr);
        
        Aviso aviso = new Aviso();
        aviso.setIdAdministrador(usuarioActual.getIdUsuario());
        aviso.setTitulo(titulo);
        aviso.setMensaje(mensaje);
        aviso.setTipo(tipo);
        aviso.setActivo(true);
        
        if (controladorAviso.crearAviso(aviso)) {
            System.out.println("✓ Aviso creado exitosamente");
        } else {
            System.out.println("✗ Error al crear aviso");
        }
    }
    
    private void actualizarAviso() {
        System.out.print("\nID del aviso a actualizar: ");
        int id = leerEntero();
        
        Aviso aviso = controladorAviso.obtenerPorId(id);
        if (aviso == null) {
            System.out.println("✗ Aviso no encontrado");
            return;
        }
        
        System.out.print("Nuevo título (Enter para mantener): ");
        String titulo = scanner.nextLine();
        if (!titulo.isEmpty()) aviso.setTitulo(titulo);
        
        System.out.print("Nuevo mensaje (Enter para mantener): ");
        String mensaje = scanner.nextLine();
        if (!mensaje.isEmpty()) aviso.setMensaje(mensaje);
        
        if (controladorAviso.actualizarAviso(aviso)) {
            System.out.println("✓ Aviso actualizado exitosamente");
        } else {
            System.out.println("✗ Error al actualizar aviso");
        }
    }
    
    private void eliminarAviso() {
        System.out.print("\nID del aviso a eliminar: ");
        int id = leerEntero();
        
        System.out.print("¿Está seguro? (s/n): ");
        String confirmacion = scanner.nextLine().toLowerCase();
        
        if (confirmacion.equals("s")) {
            if (controladorAviso.eliminarAviso(id)) {
                System.out.println("✓ Aviso eliminado exitosamente");
            } else {
                System.out.println("✗ Error al eliminar aviso");
            }
        }
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

