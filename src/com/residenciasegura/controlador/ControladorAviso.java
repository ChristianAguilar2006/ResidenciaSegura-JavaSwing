package com.residenciasegura.controlador;

import com.residenciasegura.dao.IAvisoDAO;
import com.residenciasegura.dao.AvisoDAO;
import com.residenciasegura.modelo.Aviso;
import java.util.List;

/**
 * Controlador para la lógica de avisos
 * 
 * @author DARIX
 */
public class ControladorAviso {
    
    private final IAvisoDAO avisoDAO;
    
    public ControladorAviso() {
        this.avisoDAO = new AvisoDAO();
    }
    
    public List<Aviso> obtenerTodos() {
        return avisoDAO.obtenerTodos();
    }
    
    public List<Aviso> obtenerActivos() {
        return avisoDAO.obtenerActivos();
    }
    
    public Aviso obtenerPorId(int idAviso) {
        return avisoDAO.obtenerPorId(idAviso);
    }
    
    public boolean crearAviso(Aviso aviso) {
        return avisoDAO.crearAviso(aviso);
    }
    
    public boolean actualizarAviso(Aviso aviso) {
        return avisoDAO.actualizarAviso(aviso);
    }
    
    public boolean eliminarAviso(int idAviso) {
        return avisoDAO.eliminarAviso(idAviso);
    }
    
    public boolean activarAviso(int idAviso) {
        return avisoDAO.activarAviso(idAviso);
    }
    
    public boolean desactivarAviso(int idAviso) {
        return avisoDAO.desactivarAviso(idAviso);
    }
}

