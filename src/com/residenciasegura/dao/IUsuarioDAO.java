package com.residenciasegura.dao;

import com.residenciasegura.modelo.Usuario;

/**
 * Interfaz para el acceso a datos de usuarios
 * 
 * @author DARIX
 */
public interface IUsuarioDAO {
    
    /**
     * Valida las credenciales de un usuario
     * 
     * @param correo correo electrónico del usuario
     * @param contrasena contraseña del usuario
     * @return Usuario si las credenciales son válidas, null en caso contrario
     */
    Usuario validarUsuario(String correo, String contrasena);
    
    /**
     * Obtiene un usuario por su correo electrónico
     * 
     * @param correo correo electrónico del usuario
     * @return Usuario encontrado o null si no existe
     */
    Usuario obtenerPorCorreo(String correo);
    
    /**
     * Obtiene un usuario por su ID
     * 
     * @param idUsuario ID del usuario
     * @return Usuario encontrado o null si no existe
     */
    Usuario obtenerPorId(int idUsuario);
    
    /**
     * Crea un nuevo usuario en la base de datos
     * 
     * @param usuario usuario a crear
     * @return true si se creó correctamente, false en caso contrario
     */
    boolean crearUsuario(Usuario usuario);
    
    /**
     * Actualiza la información de un usuario
     * 
     * @param usuario usuario a actualizar
     * @return true si se actualizó correctamente, false en caso contrario
     */
    boolean actualizarUsuario(Usuario usuario);
    
    /**
     * Elimina un usuario de la base de datos
     * 
     * @param idUsuario ID del usuario a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    boolean eliminarUsuario(int idUsuario);
}

