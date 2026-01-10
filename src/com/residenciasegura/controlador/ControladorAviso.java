package com.residenciasegura.controlador;

import com.residenciasegura.modelo.Aviso;
import com.residenciasegura.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ControladorAviso {
    
    public List<Aviso> obtenerTodos() {
        List<Aviso> avisos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return avisos;
            
            String sql = "SELECT * FROM avisos ORDER BY fecha_publicacion DESC";
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Aviso aviso = mapearAviso(rs);
                avisos.add(aviso);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener avisos: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return avisos;
    }
    
    public List<Aviso> obtenerActivos() {
        List<Aviso> avisos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return avisos;
            
            String sql = "SELECT * FROM avisos WHERE activo = TRUE ORDER BY fecha_publicacion DESC";
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Aviso aviso = mapearAviso(rs);
                avisos.add(aviso);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener avisos activos: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return avisos;
    }
    
    public Aviso obtenerPorId(int idAviso) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return null;
            
            String sql = "SELECT * FROM avisos WHERE id_aviso = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idAviso);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapearAviso(rs);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener aviso por ID: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return null;
    }
    
    public boolean crearAviso(Aviso aviso) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "INSERT INTO avisos (id_administrador, titulo, mensaje, tipo, activo) " +
                         "VALUES (?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, aviso.getIdAdministrador());
            pstmt.setString(2, aviso.getTitulo());
            pstmt.setString(3, aviso.getMensaje());
            pstmt.setString(4, aviso.getTipo().getValor());
            pstmt.setBoolean(5, aviso.isActivo());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al crear aviso: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public boolean actualizarAviso(Aviso aviso) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "UPDATE avisos SET titulo = ?, mensaje = ?, tipo = ?, activo = ? WHERE id_aviso = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, aviso.getTitulo());
            pstmt.setString(2, aviso.getMensaje());
            pstmt.setString(3, aviso.getTipo().getValor());
            pstmt.setBoolean(4, aviso.isActivo());
            pstmt.setInt(5, aviso.getIdAviso());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar aviso: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public boolean eliminarAviso(int idAviso) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "DELETE FROM avisos WHERE id_aviso = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idAviso);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar aviso: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public boolean activarAviso(int idAviso) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "UPDATE avisos SET activo = TRUE WHERE id_aviso = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idAviso);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al activar aviso: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public boolean desactivarAviso(int idAviso) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "UPDATE avisos SET activo = FALSE WHERE id_aviso = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idAviso);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al desactivar aviso: " + e.getMessage());
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
        
        return false;
    }
    
    private Aviso mapearAviso(ResultSet rs) throws Exception {
        Aviso aviso = new Aviso();
        aviso.setIdAviso(rs.getInt("id_aviso"));
        aviso.setIdAdministrador(rs.getInt("id_administrador"));
        aviso.setTitulo(rs.getString("titulo"));
        aviso.setMensaje(rs.getString("mensaje"));
        aviso.setTipo(Aviso.TipoAviso.fromString(rs.getString("tipo")));
        aviso.setFechaPublicacion(rs.getTimestamp("fecha_publicacion"));
        aviso.setActivo(rs.getBoolean("activo"));
        aviso.setNombreAdministrador(obtenerNombreUsuario(aviso.getIdAdministrador()));
        return aviso;
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
