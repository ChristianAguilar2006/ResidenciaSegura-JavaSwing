package com.residenciasegura.controlador;

import com.residenciasegura.modelo.Reporte;
import com.residenciasegura.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ControladorReporte {
    
    public List<Reporte> obtenerTodos() {
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
    
    public List<Reporte> obtenerPorUsuario(int idUsuario) {
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
    
    public List<Reporte> obtenerPorEstado(String estado) {
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
    
    public List<Reporte> obtenerPendientes() {
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
    
    public Reporte obtenerPorId(int idReporte) {
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
    
    public boolean crearReporte(Reporte reporte) {
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
            pstmt.setString(5, reporte.getEstado().getValor());
            pstmt.setString(6, reporte.getPrioridad().getValor());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al crear reporte: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public boolean actualizarReporte(Reporte reporte) {
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
            pstmt.setString(4, reporte.getEstado().getValor());
            pstmt.setString(5, reporte.getPrioridad().getValor());
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
    
    public boolean eliminarReporte(int idReporte) {
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
    
    public boolean asignarGuardia(int idReporte, int idGuardia) {
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
    
    public boolean resolverReporte(int idReporte, int idGuardia) {
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
    
    private Reporte mapearReporte(ResultSet rs) throws Exception {
        Reporte reporte = new Reporte();
        
        reporte.setIdReporte(rs.getInt("id_reporte"));
        reporte.setIdUsuario(rs.getInt("id_usuario"));
        reporte.setTipo(Reporte.TipoReporte.fromString(rs.getString("tipo")));
        reporte.setDescripcion(rs.getString("descripcion"));
        reporte.setUbicacion(rs.getString("ubicacion"));
        reporte.setFechaReporte(rs.getTimestamp("fecha_reporte"));
        reporte.setEstado(Reporte.EstadoReporte.fromString(rs.getString("estado")));
        reporte.setPrioridad(Reporte.Prioridad.fromString(rs.getString("prioridad")));
        
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
    
    private String obtenerNombreUsuario(int idUsuario) {
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
    
    private void cerrarRecursos(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
