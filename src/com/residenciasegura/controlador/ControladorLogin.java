package com.residenciasegura.controlador;

import com.residenciasegura.modelo.Usuario;
import com.residenciasegura.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControladorLogin {
    
    public Usuario autenticarUsuario(String correo, String contrasena) {
        if (correo == null || contrasena == null || correo.isEmpty() || contrasena.isEmpty()) {
            System.out.println("✗ Error: Correo o contraseña vacíos");
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
                usuario.setRol(Usuario.Rol.fromString(rs.getString("rol")));
                usuario.setDepartamento(rs.getString("departamento"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                
                System.out.println("✓ Usuario autenticado: " + usuario.getNombre() + " (" + usuario.getRol().getValor() + ")");
                return usuario;
            } else {
                System.out.println("✗ No se encontró usuario con esas credenciales");
            }
            
        } catch (Exception e) {
            System.out.println("✗ Error al validar usuario: " + e.getMessage());
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
}
