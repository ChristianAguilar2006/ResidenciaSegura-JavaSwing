package com.residenciasegura.controlador;

import com.residenciasegura.dao.IUsuarioDAO;
import com.residenciasegura.dao.UsuarioDAO;
import com.residenciasegura.modelo.Usuario;
import java.util.List;

/**
 * Controlador para la lógica de usuarios
 * 
 * @author DARIX
 */
public class ControladorUsuario {
    
    private final IUsuarioDAO usuarioDAO;
    
    public ControladorUsuario() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public Usuario obtenerPorId(int idUsuario) {
        return usuarioDAO.obtenerPorId(idUsuario);
    }
    
    public Usuario obtenerPorCorreo(String correo) {
        return usuarioDAO.obtenerPorCorreo(correo);
    }
    
    public boolean crearUsuario(Usuario usuario) {
        return usuarioDAO.crearUsuario(usuario);
    }
    
    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizarUsuario(usuario);
    }
    
    public boolean eliminarUsuario(int idUsuario) {
        return usuarioDAO.eliminarUsuario(idUsuario);
    }
    
    public List<Usuario> obtenerTodos() {
        // Necesitamos agregar este método al DAO
        return new UsuarioDAO().obtenerTodos();
    }
    
    public List<Usuario> obtenerPorRol(String rol) {
        // Necesitamos agregar este método al DAO
        return new UsuarioDAO().obtenerPorRol(rol);
    }
}

