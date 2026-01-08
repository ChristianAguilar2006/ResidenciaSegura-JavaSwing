package com.residenciasegura.controlador;

import com.residenciasegura.dao.IUsuarioDAO;
import com.residenciasegura.dao.UsuarioDAO;
import com.residenciasegura.modelo.Usuario;

/**
 * Controlador para la lógica de autenticación
 * 
 * @author DARIX
 */
public class ControladorLogin {
    
    private final IUsuarioDAO usuarioDAO;
    
    public ControladorLogin() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    /**
     * Autentica un usuario con correo y contraseña
     * 
     * @param correo correo electrónico del usuario
     * @param contrasena contraseña del usuario
     * @return Usuario autenticado o null si las credenciales son incorrectas
     */
    public Usuario autenticarUsuario(String correo, String contrasena) {
        if (correo == null || contrasena == null || correo.trim().isEmpty() || contrasena.trim().isEmpty()) {
            return null;
        }
        
        return usuarioDAO.validarUsuario(correo.trim(), contrasena.trim());
    }
}

