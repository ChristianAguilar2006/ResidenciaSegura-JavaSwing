package com.residenciasegura.controlador;

import com.residenciasegura.modelo.Usuario;
import com.residenciasegura.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la lógica de usuarios (sin DAO, consultas directas)
 * 
 * @author DARIX
 */
public class ControladorUsuario {
    
    public Usuario obtenerPorId(int idUsuario) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return null;
            
            String sql = "SELECT id_usuario, nombre, correo, contrasena, rol, departamento, telefono, fecha_registro " +
                         "FROM usuarios WHERE id_usuario = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(Usuario.Rol.fromString(rs.getString("rol")));
                usuario.setDepartamento(rs.getString("departamento"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                return usuario;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener usuario: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return null;
    }
    
    public Usuario obtenerPorCorreo(String correo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return null;
            
            String sql = "SELECT id_usuario, nombre, correo, contrasena, rol, departamento, telefono, fecha_registro " +
                         "FROM usuarios WHERE correo = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, correo);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(Usuario.Rol.fromString(rs.getString("rol")));
                usuario.setDepartamento(rs.getString("departamento"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                return usuario;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener usuario por correo: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return null;
    }
    
    public boolean crearUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "INSERT INTO usuarios (nombre, correo, contrasena, rol, departamento, telefono) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getContrasena());
            pstmt.setString(4, usuario.getRol().getValor());
            pstmt.setString(5, usuario.getDepartamento());
            pstmt.setString(6, usuario.getTelefono());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public boolean actualizarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "UPDATE usuarios SET nombre = ?, correo = ?, rol = ?, departamento = ?, telefono = ? " +
                         "WHERE id_usuario = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getRol().getValor());
            pstmt.setString(4, usuario.getDepartamento());
            pstmt.setString(5, usuario.getTelefono());
            pstmt.setInt(6, usuario.getIdUsuario());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public boolean eliminarUsuario(int idUsuario) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return usuarios;
            
            String sql = "SELECT id_usuario, nombre, correo, contrasena, rol, departamento, telefono, fecha_registro " +
                         "FROM usuarios ORDER BY nombre";
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(Usuario.Rol.fromString(rs.getString("rol")));
                usuario.setDepartamento(rs.getString("departamento"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener todos los usuarios: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return usuarios;
    }
    
    public List<Usuario> obtenerPorRol(String rol) {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return usuarios;
            
            String sql = "SELECT id_usuario, nombre, correo, contrasena, rol, departamento, telefono, fecha_registro " +
                         "FROM usuarios WHERE rol = ? ORDER BY nombre";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rol);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(Usuario.Rol.fromString(rs.getString("rol")));
                usuario.setDepartamento(rs.getString("departamento"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener usuarios por rol: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return usuarios;
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
