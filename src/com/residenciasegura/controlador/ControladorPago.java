package com.residenciasegura.controlador;

import com.residenciasegura.dao.IPagoDAO;
import com.residenciasegura.dao.PagoDAO;
import com.residenciasegura.modelo.Pago;
import java.util.List;

/**
 * Controlador para la lógica de pagos
 * 
 * @author DARIX
 */
public class ControladorPago {
    
    private final IPagoDAO pagoDAO;
    
    public ControladorPago() {
        this.pagoDAO = new PagoDAO();
    }
    
    public List<Pago> obtenerTodos() {
        return pagoDAO.obtenerTodos();
    }
    
    public List<Pago> obtenerPorUsuario(int idUsuario) {
        return pagoDAO.obtenerPorUsuario(idUsuario);
    }
    
    public List<Pago> obtenerPorEstado(String estado) {
        return pagoDAO.obtenerPorEstado(estado);
    }
    
    public Pago obtenerPorId(int idPago) {
        return pagoDAO.obtenerPorId(idPago);
    }
    
    public boolean crearPago(Pago pago) {
        return pagoDAO.crearPago(pago);
    }
    
    public boolean actualizarPago(Pago pago) {
        return pagoDAO.actualizarPago(pago);
    }
    
    public boolean eliminarPago(int idPago) {
        return pagoDAO.eliminarPago(idPago);
    }
}

