package com.residenciasegura.controlador;

import com.residenciasegura.dao.IReporteDAO;
import com.residenciasegura.dao.ReporteDAO;
import com.residenciasegura.modelo.Reporte;
import java.util.List;

/**
 * Controlador para la lógica de reportes
 * 
 * @author DARIX
 */
public class ControladorReporte {
    
    private final IReporteDAO reporteDAO;
    
    public ControladorReporte() {
        this.reporteDAO = new ReporteDAO();
    }
    
    public List<Reporte> obtenerTodos() {
        return reporteDAO.obtenerTodos();
    }
    
    public List<Reporte> obtenerPorUsuario(int idUsuario) {
        return reporteDAO.obtenerPorUsuario(idUsuario);
    }
    
    public List<Reporte> obtenerPorEstado(String estado) {
        return reporteDAO.obtenerPorEstado(estado);
    }
    
    public List<Reporte> obtenerPorGuardia(int idGuardia) {
        return reporteDAO.obtenerPorGuardia(idGuardia);
    }
    
    public Reporte obtenerPorId(int idReporte) {
        return reporteDAO.obtenerPorId(idReporte);
    }
    
    public boolean crearReporte(Reporte reporte) {
        return reporteDAO.crearReporte(reporte);
    }
    
    public boolean actualizarReporte(Reporte reporte) {
        return reporteDAO.actualizarReporte(reporte);
    }
    
    public boolean eliminarReporte(int idReporte) {
        return reporteDAO.eliminarReporte(idReporte);
    }
    
    public boolean asignarGuardia(int idReporte, int idGuardia) {
        return reporteDAO.asignarGuardia(idReporte, idGuardia);
    }
    
    public boolean resolverReporte(int idReporte, int idGuardia) {
        return reporteDAO.resolverReporte(idReporte, idGuardia);
    }
}

