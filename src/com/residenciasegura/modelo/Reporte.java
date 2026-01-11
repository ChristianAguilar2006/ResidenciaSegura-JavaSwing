package com.residenciasegura.modelo;

import com.residenciasegura.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Reporte {
    
    private int idReporte;
    private int idUsuario;
    private TipoReporte tipo;
    private String descripcion;
    private String ubicacion;
    private Timestamp fechaReporte;
    private String estado;
    private String prioridad;
    private Integer idGuardiaAtendio;
    private Timestamp fechaResolucion;
    
    private String nombreUsuario;
    private String nombreGuardia;
    
    public Reporte() {
    }
    
    public Reporte(int idUsuario, TipoReporte tipo, String descripcion, String ubicacion, String estado, String prioridad) {
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.prioridad = prioridad;
    }
    
    public int getIdReporte() {
        return idReporte;
    }
    
    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public TipoReporte getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoReporte tipo) {
        this.tipo = tipo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public Timestamp getFechaReporte() {
        return fechaReporte;
    }
    
    public void setFechaReporte(Timestamp fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getPrioridad() {
        return prioridad;
    }
    
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    
    public Integer getIdGuardiaAtendio() {
        return idGuardiaAtendio;
    }
    
    public void setIdGuardiaAtendio(Integer idGuardiaAtendio) {
        this.idGuardiaAtendio = idGuardiaAtendio;
    }
    
    public Timestamp getFechaResolucion() {
        return fechaResolucion;
    }
    
    public void setFechaResolucion(Timestamp fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getNombreGuardia() {
        return nombreGuardia;
    }
    
    public void setNombreGuardia(String nombreGuardia) {
        this.nombreGuardia = nombreGuardia;
    }
    
    // Métodos estaticos para operaciones de base de datos (antes en ControladorReporte)
    
    public static List<Reporte> obtenerTodos() {
        List<Reporte> reportes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return reportes;
            
            String sql = "SELECT * FROM reportes ORDER BY fecha_reporte DESC";
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = mapearReporte(rs);
                reportes.add(reporte);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener reportes: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return reportes;
    }
    
    public static List<Reporte> obtenerPorUsuario(int idUsuario) {
        List<Reporte> reportes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return reportes;
            
            String sql = "SELECT * FROM reportes WHERE id_usuario = ? ORDER BY fecha_reporte DESC";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = mapearReporte(rs);
                reportes.add(reporte);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener reportes por usuario: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return reportes;
    }
    
    public static List<Reporte> obtenerPorEstado(String estado) {
        List<Reporte> reportes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return reportes;
            
            String sql = "SELECT * FROM reportes WHERE estado = ? ORDER BY fecha_reporte DESC";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, estado);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = mapearReporte(rs);
                reportes.add(reporte);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener reportes por estado: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return reportes;
    }
    
    public static List<Reporte> obtenerPendientes() {
        List<Reporte> reportes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return reportes;
            
            String sql = "SELECT * FROM reportes WHERE estado = 'pendiente' ORDER BY fecha_reporte DESC";
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = mapearReporte(rs);
                reportes.add(reporte);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener reportes pendientes: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return reportes;
    }
    
    public static Reporte obtenerPorId(int idReporte) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return null;
            
            String sql = "SELECT * FROM reportes WHERE id_reporte = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idReporte);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapearReporte(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener reporte por ID: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return null;
    }
    
    public static boolean crearReporte(Reporte reporte) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "INSERT INTO reportes (id_usuario, tipo, descripcion, ubicacion, estado, prioridad) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reporte.getIdUsuario());
            pstmt.setString(2, reporte.getTipo().getValor());
            pstmt.setString(3, reporte.getDescripcion());
            pstmt.setString(4, reporte.getUbicacion());
            pstmt.setString(5, reporte.getEstado());
            pstmt.setString(6, reporte.getPrioridad());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al crear reporte: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public static boolean actualizarReporte(Reporte reporte) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "UPDATE reportes SET tipo = ?, descripcion = ?, ubicacion = ?, estado = ?, " +
                         "prioridad = ?, id_guardia_atendio = ?, fecha_resolucion = ? WHERE id_reporte = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reporte.getTipo().getValor());
            pstmt.setString(2, reporte.getDescripcion());
            pstmt.setString(3, reporte.getUbicacion());
            pstmt.setString(4, reporte.getEstado());
            pstmt.setString(5, reporte.getPrioridad());
            if (reporte.getIdGuardiaAtendio() != null) {
                pstmt.setInt(6, reporte.getIdGuardiaAtendio());
            } else {
                pstmt.setNull(6, java.sql.Types.INTEGER);
            }
            if (reporte.getFechaResolucion() != null) {
                pstmt.setTimestamp(7, reporte.getFechaResolucion());
            } else {
                pstmt.setNull(7, java.sql.Types.TIMESTAMP);
            }
            pstmt.setInt(8, reporte.getIdReporte());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar reporte: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public static boolean eliminarReporte(int idReporte) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "DELETE FROM reportes WHERE id_reporte = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idReporte);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar reporte: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public static boolean asignarGuardia(int idReporte, int idGuardia) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "UPDATE reportes SET id_guardia_atendio = ?, estado = 'en_proceso' WHERE id_reporte = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idGuardia);
            pstmt.setInt(2, idReporte);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al asignar guardia: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public static boolean resolverReporte(int idReporte, int idGuardia) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "UPDATE reportes SET estado = 'resuelto', fecha_resolucion = CURRENT_TIMESTAMP WHERE id_reporte = ? AND id_guardia_atendio = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idReporte);
            pstmt.setInt(2, idGuardia);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al resolver reporte: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    private static Reporte mapearReporte(ResultSet rs) throws Exception {
        Reporte reporte = new Reporte();
        
        reporte.setIdReporte(rs.getInt("id_reporte"));
        reporte.setIdUsuario(rs.getInt("id_usuario"));
        reporte.setTipo(TipoReporte.fromString(rs.getString("tipo")));
        reporte.setDescripcion(rs.getString("descripcion"));
        reporte.setUbicacion(rs.getString("ubicacion"));
        reporte.setFechaReporte(rs.getTimestamp("fecha_reporte"));
        reporte.setEstado(rs.getString("estado"));
        reporte.setPrioridad(rs.getString("prioridad"));
        
        int idGuardia = rs.getInt("id_guardia_atendio");
        if (!rs.wasNull()) {
            reporte.setIdGuardiaAtendio(idGuardia);
            reporte.setNombreGuardia(obtenerNombreUsuario(idGuardia));
        }
        
        Timestamp fechaResolucion = rs.getTimestamp("fecha_resolucion");
        if (!rs.wasNull()) {
            reporte.setFechaResolucion(fechaResolucion);
        }
        
        reporte.setNombreUsuario(obtenerNombreUsuario(reporte.getIdUsuario()));
        
        return reporte;
    }
    
    private static String obtenerNombreUsuario(int idUsuario) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return null;
            
            String sql = "SELECT nombre FROM usuarios WHERE id_usuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener nombre de usuario: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return null;
    }
    
    private static void cerrarRecursos(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}

