package com.residenciasegura.dao;

import com.residenciasegura.modelo.Pago;
import java.util.List;

/**
 * Interfaz para el acceso a datos de pagos
 * 
 * @author DARIX
 */
public interface IPagoDAO {
    
    List<Pago> obtenerTodos();
    List<Pago> obtenerPorUsuario(int idUsuario);
    List<Pago> obtenerPorEstado(String estado);
    Pago obtenerPorId(int idPago);
    boolean crearPago(Pago pago);
    boolean actualizarPago(Pago pago);
    boolean eliminarPago(int idPago);
}

