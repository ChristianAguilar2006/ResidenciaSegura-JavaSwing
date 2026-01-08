package com.residenciasegura.dao;

import com.residenciasegura.modelo.Reporte;
import java.util.List;

/**
 * Interfaz para el acceso a datos de reportes
 * 
 * @author DARIX
 */
public interface IReporteDAO {
    
    List<Reporte> obtenerTodos();
    List<Reporte> obtenerPorUsuario(int idUsuario);
    List<Reporte> obtenerPorEstado(String estado);
    List<Reporte> obtenerPorGuardia(int idGuardia);
    Reporte obtenerPorId(int idReporte);
    boolean crearReporte(Reporte reporte);
    boolean actualizarReporte(Reporte reporte);
    boolean eliminarReporte(int idReporte);
    boolean asignarGuardia(int idReporte, int idGuardia);
    boolean resolverReporte(int idReporte, int idGuardia);
}

