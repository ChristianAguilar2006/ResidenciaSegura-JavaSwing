package com.residenciasegura.dao;

import com.residenciasegura.modelo.Aviso;
import java.util.List;

/**
 * Interfaz para el acceso a datos de avisos
 * 
 * @author DARIX
 */
public interface IAvisoDAO {
    
    List<Aviso> obtenerTodos();
    List<Aviso> obtenerActivos();
    Aviso obtenerPorId(int idAviso);
    boolean crearAviso(Aviso aviso);
    boolean actualizarAviso(Aviso aviso);
    boolean eliminarAviso(int idAviso);
    boolean activarAviso(int idAviso);
    boolean desactivarAviso(int idAviso);
}

