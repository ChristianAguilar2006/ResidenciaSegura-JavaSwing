package com.residenciasegura.dao;

import com.residenciasegura.modelo.Aviso;
import com.residenciasegura.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO para avisos
 * 
 * @author DARIX
 */
public class AvisoDAO implements IAvisoDAO {
    
    @Override
    public List<Aviso> obtenerTodos() {
        String sql = "SELECT a.*, u.nombre as nombre_administrador FROM avisos a " +
                     "INNER JOIN usuarios u ON a.id_administrador = u.id_usuario " +
                     "ORDER BY a.fecha_publicacion DESC";
        
        return obtenerAvisos(sql);
    }
    
    @Override
    public List<Aviso> obtenerActivos() {
        String sql = "SELECT a.*, u.nombre as nombre_administrador FROM avisos a " +
                     "INNER JOIN usuarios u ON a.id_administrador = u.id_usuario " +
                     "WHERE a.activo = TRUE ORDER BY a.fecha_publicacion DESC";
        
        return obtenerAvisos(sql);
    }
    
    @Override
    public Aviso obtenerPorId(int idAviso) {
        String sql = "SELECT a.*, u.nombre as nombre_administrador FROM avisos a " +
                     "INNER JOIN usuarios u ON a.id_administrador = u.id_usuario " +
                     "WHERE a.id_aviso = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idAviso);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapearAviso(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener aviso por ID: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return null;
    }
    
    @Override
    public boolean crearAviso(Aviso aviso) {
        String sql = "INSERT INTO avisos (id_administrador, titulo, mensaje, tipo, activo) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, aviso.getIdAdministrador());
            pstmt.setString(2, aviso.getTitulo());
            pstmt.setString(3, aviso.getMensaje());
            pstmt.setString(4, aviso.getTipo().getValor());
            pstmt.setBoolean(5, aviso.isActivo());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear aviso: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean actualizarAviso(Aviso aviso) {
        String sql = "UPDATE avisos SET titulo = ?, mensaje = ?, tipo = ?, activo = ? WHERE id_aviso = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aviso.getTitulo());
            pstmt.setString(2, aviso.getMensaje());
            pstmt.setString(3, aviso.getTipo().getValor());
            pstmt.setBoolean(4, aviso.isActivo());
            pstmt.setInt(5, aviso.getIdAviso());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar aviso: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean eliminarAviso(int idAviso) {
        String sql = "DELETE FROM avisos WHERE id_aviso = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idAviso);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar aviso: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean activarAviso(int idAviso) {
        String sql = "UPDATE avisos SET activo = TRUE WHERE id_aviso = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idAviso);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al activar aviso: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean desactivarAviso(int idAviso) {
        String sql = "UPDATE avisos SET activo = FALSE WHERE id_aviso = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idAviso);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al desactivar aviso: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    private List<Aviso> obtenerAvisos(String sql) {
        List<Aviso> avisos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Aviso aviso = mapearAviso(rs);
                avisos.add(aviso);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener avisos: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return avisos;
    }
    
    private Aviso mapearAviso(ResultSet rs) throws SQLException {
        Aviso aviso = new Aviso();
        aviso.setIdAviso(rs.getInt("id_aviso"));
        aviso.setIdAdministrador(rs.getInt("id_administrador"));
        aviso.setTitulo(rs.getString("titulo"));
        aviso.setMensaje(rs.getString("mensaje"));
        aviso.setTipo(Aviso.TipoAviso.fromString(rs.getString("tipo")));
        aviso.setFechaPublicacion(rs.getTimestamp("fecha_publicacion"));
        aviso.setActivo(rs.getBoolean("activo"));
        aviso.setNombreAdministrador(rs.getString("nombre_administrador"));
        return aviso;
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

