package com.residenciasegura.modelo;

import com.residenciasegura.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private Rol rol;
    private String departamento;
    private String telefono;
    private Timestamp fechaRegistro;
    
    public Usuario() {
    }
    
    public Usuario(String nombre, String correo, String contrasena, Rol rol, String departamento) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.departamento = departamento;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public Rol getRol() {
        return rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", rol=" + rol +
                ", departamento='" + departamento + '\'' +
                '}';
    }
    
    // Métodos estaticos para operaciones de base de datos (antes en ControladorLogin y ControladorUsuario)
    
    public static Usuario autenticarUsuario(String correo, String contrasena) {
        if (correo == null || contrasena == null || correo.isEmpty() || contrasena.isEmpty()) {
            System.out.println("Error: Correo o contrasena vacios");
            return null;
        }
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            System.out.println("Intentando conectar a la base de datos...");
            conn = ConexionDB.obtenerConexion();
            if (conn == null) {
                System.out.println("✗ Error: No se pudo conectar a la base de datos");
                return null;
            }
            
            String sql = "SELECT id_usuario, nombre, correo, contrasena, rol, departamento, telefono, fecha_registro " +
                         "FROM usuarios WHERE correo = ? AND contrasena = ?";
            
            System.out.println("Buscando usuario con correo: " + correo);
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasena);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(Rol.fromString(rs.getString("rol")));
                usuario.setDepartamento(rs.getString("departamento"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                
                System.out.println("Usuario autenticado: " + usuario.getNombre() + " (" + usuario.getRol().getValor() + ")");
                return usuario;
            } else {
                System.out.println("No se encontro usuario con esas credenciales");
            }
            
        } catch (Exception e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        return null;
    }
    
    public static Usuario obtenerPorId(int idUsuario) {
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
                usuario.setRol(Rol.fromString(rs.getString("rol")));
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
    
    public static Usuario obtenerPorCorreo(String correo) {
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
                usuario.setRol(Rol.fromString(rs.getString("rol")));
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
    
    public static boolean crearUsuario(Usuario usuario) {
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
    
    public static boolean actualizarUsuario(Usuario usuario) {
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
    
    public static boolean eliminarUsuario(int idUsuario) {
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
    
    public static List<Usuario> obtenerTodos() {
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
                usuario.setRol(Rol.fromString(rs.getString("rol")));
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
    
    public static List<Usuario> obtenerPorRol(String rol) {
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
                usuario.setRol(Rol.fromString(rs.getString("rol")));
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

