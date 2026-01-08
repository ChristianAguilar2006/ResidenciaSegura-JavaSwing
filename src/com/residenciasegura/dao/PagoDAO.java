package com.residenciasegura.dao;

import com.residenciasegura.modelo.Pago;
import com.residenciasegura.util.ConexionDB;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO para pagos
 * 
 * @author DARIX
 */
public class PagoDAO implements IPagoDAO {
    
    @Override
    public List<Pago> obtenerTodos() {
        String sql = "SELECT p.*, u.nombre as nombre_usuario FROM pagos p " +
                     "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                     "ORDER BY p.fecha_pago DESC";
        
        List<Pago> pagos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pago pago = mapearPago(rs);
                pago.setNombreUsuario(rs.getString("nombre_usuario"));
                pagos.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pagos: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return pagos;
    }
    
    @Override
    public List<Pago> obtenerPorUsuario(int idUsuario) {
        String sql = "SELECT p.*, u.nombre as nombre_usuario FROM pagos p " +
                     "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                     "WHERE p.id_usuario = ? ORDER BY p.fecha_pago DESC";
        
        List<Pago> pagos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pago pago = mapearPago(rs);
                pago.setNombreUsuario(rs.getString("nombre_usuario"));
                pagos.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pagos por usuario: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return pagos;
    }
    
    @Override
    public List<Pago> obtenerPorEstado(String estado) {
        String sql = "SELECT p.*, u.nombre as nombre_usuario FROM pagos p " +
                     "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                     "WHERE p.estado = ? ORDER BY p.fecha_pago DESC";
        
        List<Pago> pagos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, estado);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pago pago = mapearPago(rs);
                pago.setNombreUsuario(rs.getString("nombre_usuario"));
                pagos.add(pago);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pagos por estado: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return pagos;
    }
    
    @Override
    public Pago obtenerPorId(int idPago) {
        String sql = "SELECT p.*, u.nombre as nombre_usuario FROM pagos p " +
                     "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                     "WHERE p.id_pago = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPago);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Pago pago = mapearPago(rs);
                pago.setNombreUsuario(rs.getString("nombre_usuario"));
                return pago;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pago por ID: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return null;
    }
    
    @Override
    public boolean crearPago(Pago pago) {
        String sql = "INSERT INTO pagos (id_usuario, tipo_servicio, monto, fecha_pago, estado, metodo_pago, comprobante, observaciones) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pago.getIdUsuario());
            pstmt.setString(2, pago.getTipoServicio().getValor());
            pstmt.setBigDecimal(3, pago.getMonto());
            pstmt.setDate(4, pago.getFechaPago());
            pstmt.setString(5, pago.getEstado().getValor());
            pstmt.setString(6, pago.getMetodoPago().getValor());
            pstmt.setString(7, pago.getComprobante());
            pstmt.setString(8, pago.getObservaciones());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear pago: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean actualizarPago(Pago pago) {
        String sql = "UPDATE pagos SET id_usuario = ?, tipo_servicio = ?, monto = ?, fecha_pago = ?, " +
                     "estado = ?, metodo_pago = ?, comprobante = ?, observaciones = ? WHERE id_pago = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pago.getIdUsuario());
            pstmt.setString(2, pago.getTipoServicio().getValor());
            pstmt.setBigDecimal(3, pago.getMonto());
            pstmt.setDate(4, pago.getFechaPago());
            pstmt.setString(5, pago.getEstado().getValor());
            pstmt.setString(6, pago.getMetodoPago().getValor());
            pstmt.setString(7, pago.getComprobante());
            pstmt.setString(8, pago.getObservaciones());
            pstmt.setInt(9, pago.getIdPago());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar pago: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    @Override
    public boolean eliminarPago(int idPago) {
        String sql = "DELETE FROM pagos WHERE id_pago = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPago);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar pago: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    private Pago mapearPago(ResultSet rs) throws SQLException {
        Pago pago = new Pago();
        pago.setIdPago(rs.getInt("id_pago"));
        pago.setIdUsuario(rs.getInt("id_usuario"));
        pago.setTipoServicio(Pago.TipoServicio.fromString(rs.getString("tipo_servicio")));
        pago.setMonto(rs.getBigDecimal("monto"));
        pago.setFechaPago(rs.getDate("fecha_pago"));
        pago.setEstado(Pago.EstadoPago.fromString(rs.getString("estado")));
        pago.setMetodoPago(Pago.MetodoPago.fromString(rs.getString("metodo_pago")));
        pago.setComprobante(rs.getString("comprobante"));
        pago.setObservaciones(rs.getString("observaciones"));
        return pago;
    }
    
    private void cerrarRecursos(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}

