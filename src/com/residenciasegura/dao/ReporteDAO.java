package com.residenciasegura.dao;

import com.residenciasegura.modelo.Reporte;
import com.residenciasegura.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO para reportes
 * 
 * @author DARIX
 */
public class ReporteDAO implements IReporteDAO {
    
    @Override
    public List<Reporte> obtenerTodos() {
        String sql = "SELECT r.*, u.nombre as nombre_usuario, g.nombre as nombre_guardia " +
                     "FROM reportes r " +
                     "INNER JOIN usuarios u ON r.id_usuario = u.id_usuario " +
                     "LEFT JOIN usuarios g ON r.id_guardia_atendio = g.id_usuario " +
                     "ORDER BY r.fecha_reporte DESC";
        
        return obtenerReportes(sql);
    }
    
    @Override
    public List<Reporte> obtenerPorUsuario(int idUsuario) {
        String sql = "SELECT r.*, u.nombre as nombre_usuario, g.nombre as nombre_guardia " +
                     "FROM reportes r " +
                     "INNER JOIN usuarios u ON r.id_usuario = u.id_usuario " +
                     "LEFT JOIN usuarios g ON r.id_guardia_atendio = g.id_usuario " +
                     "WHERE r.id_usuario = ? ORDER BY r.fecha_reporte DESC";
        
        List<Reporte> reportes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = mapearReporte(rs);
                reportes.add(reporte);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reportes por usuario: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return reportes;
    }
    
    @Override
    public List<Reporte> obtenerPorEstado(String estado) {
        String sql = "SELECT r.*, u.nombre as nombre_usuario, g.nombre as nombre_guardia " +
                     "FROM reportes r " +
                     "INNER JOIN usuarios u ON r.id_usuario = u.id_usuario " +
                     "LEFT JOIN usuarios g ON r.id_guardia_atendio = g.id_usuario " +
                     "WHERE r.estado = ? ORDER BY r.fecha_reporte DESC";
        
        List<Reporte> reportes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, estado);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = mapearReporte(rs);
                reportes.add(reporte);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reportes por estado: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return reportes;
    }
    
    @Override
    public List<Reporte> obtenerPorGuardia(int idGuardia) {
        String sql = "SELECT r.*, u.nombre as nombre_usuario, g.nombre as nombre_guardia " +
                     "FROM reportes r " +
                     "INNER JOIN usuarios u ON r.id_usuario = u.id_usuario " +
                     "LEFT JOIN usuarios g ON r.id_guardia_atendio = g.id_usuario " +
                     "WHERE r.id_guardia_atendio = ? ORDER BY r.fecha_reporte DESC";
        
        List<Reporte> reportes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idGuardia);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = mapearReporte(rs);
                reportes.add(reporte);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reportes por guardia: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return reportes;
    }
    
    @Override
    public Reporte obtenerPorId(int idReporte) {
        String sql = "SELECT r.*, u.nombre as nombre_usuario, g.nombre as nombre_guardia " +
                     "FROM reportes r " +
                     "INNER JOIN usuarios u ON r.id_usuario = u.id_usuario " +
                     "LEFT JOIN usuarios g ON r.id_guardia_atendio = g.id_usuario " +
                     "WHERE r.id_reporte = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idReporte);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapearReporte(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reporte por ID: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return null;
    }
    
    @Override
    public boolean crearReporte(Reporte reporte) {
        String sql = "INSERT INTO reportes (id_usuario, tipo, descripcion, ubicacion, estado, prioridad) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reporte.getIdUsuario());
            pstmt.setString(2, reporte.getTipo().getValor());
            pstmt.setString(3, reporte.getDescripcion());
            pstmt.setString(4, reporte.getUbicacion());
            pstmt.setString(5, reporte.getEstado().getValor());
            pstmt.setString(6, reporte.getPrioridad().getValor());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear reporte: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean actualizarReporte(Reporte reporte) {
        String sql = "UPDATE reportes SET tipo = ?, descripcion = ?, ubicacion = ?, estado = ?, " +
                     "prioridad = ?, id_guardia_atendio = ?, fecha_resolucion = ? WHERE id_reporte = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
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
        } catch (SQLException e) {
            System.out.println("Error al actualizar reporte: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean eliminarReporte(int idReporte) {
        String sql = "DELETE FROM reportes WHERE id_reporte = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idReporte);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar reporte: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean asignarGuardia(int idReporte, int idGuardia) {
        String sql = "UPDATE reportes SET id_guardia_atendio = ?, estado = 'en_proceso' WHERE id_reporte = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idGuardia);
            pstmt.setInt(2, idReporte);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al asignar guardia: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean resolverReporte(int idReporte, int idGuardia) {
        String sql = "UPDATE reportes SET estado = 'resuelto', fecha_resolucion = CURRENT_TIMESTAMP WHERE id_reporte = ? AND id_guardia_atendio = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idReporte);
            pstmt.setInt(2, idGuardia);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al resolver reporte: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    private List<Reporte> obtenerReportes(String sql) {
        List<Reporte> reportes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = mapearReporte(rs);
                reportes.add(reporte);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reportes: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return reportes;
    }
    
    private Reporte mapearReporte(ResultSet rs) throws SQLException {
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
        }
        Timestamp fechaResolucion = rs.getTimestamp("fecha_resolucion");
        if (!rs.wasNull()) {
            reporte.setFechaResolucion(fechaResolucion);
        }
        reporte.setNombreUsuario(rs.getString("nombre_usuario"));
        String nombreGuardia = rs.getString("nombre_guardia");
        if (nombreGuardia != null) {
            reporte.setNombreGuardia(nombreGuardia);
        }
        return reporte;
    }
    
    private void cerrarRecursos(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}

