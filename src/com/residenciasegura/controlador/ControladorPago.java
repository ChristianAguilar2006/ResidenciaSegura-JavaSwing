package com.residenciasegura.controlador;

import com.residenciasegura.modelo.Pago;
import com.residenciasegura.util.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la lógica de pagos (sin DAO, consultas directas)
 * 
 * @author DARIX
 */
public class ControladorPago {
    
    public List<Pago> obtenerTodos() {
        List<Pago> pagos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return pagos;
            
            String sql = "SELECT p.*, u.nombre as nombre_usuario FROM pagos p " +
                         "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                         "ORDER BY p.fecha_pago DESC";
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            for (Pago pago : procesarResultSet(rs)) {
                pagos.add(pago);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener pagos: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return pagos;
    }
    
    public List<Pago> obtenerPorUsuario(int idUsuario) {
        List<Pago> pagos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return pagos;
            
            String sql = "SELECT p.*, u.nombre as nombre_usuario FROM pagos p " +
                         "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                         "WHERE p.id_usuario = ? ORDER BY p.fecha_pago DESC";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdUsuario(rs.getInt("id_usuario"));
                pago.setTipoServicio(Pago.TipoServicio.fromString(rs.getString("tipo_servicio")));
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setFechaPago(rs.getDate("fecha_pago"));
                pago.setEstado(Pago.EstadoPago.fromString(rs.getString("estado")));
                pago.setMetodoPago(Pago.MetodoPago.fromString(rs.getString("metodo_pago")));
                pago.setNombreUsuario(rs.getString("nombre_usuario"));
                pagos.add(pago);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener pagos por usuario: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return pagos;
    }
    
    public Pago obtenerPorId(int idPago) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return null;
            
            String sql = "SELECT p.*, u.nombre as nombre_usuario FROM pagos p " +
                         "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                         "WHERE p.id_pago = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPago);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdUsuario(rs.getInt("id_usuario"));
                pago.setTipoServicio(Pago.TipoServicio.fromString(rs.getString("tipo_servicio")));
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setFechaPago(rs.getDate("fecha_pago"));
                pago.setEstado(Pago.EstadoPago.fromString(rs.getString("estado")));
                pago.setMetodoPago(Pago.MetodoPago.fromString(rs.getString("metodo_pago")));
                pago.setNombreUsuario(rs.getString("nombre_usuario"));
                return pago;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener pago por ID: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return null;
    }
    
    public boolean crearPago(Pago pago) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "INSERT INTO pagos (id_usuario, tipo_servicio, monto, fecha_pago, estado, metodo_pago) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pago.getIdUsuario());
            pstmt.setString(2, pago.getTipoServicio().getValor());
            pstmt.setBigDecimal(3, pago.getMonto());
            pstmt.setDate(4, pago.getFechaPago());
            pstmt.setString(5, pago.getEstado().getValor());
            pstmt.setString(6, pago.getMetodoPago().getValor());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al crear pago: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public boolean actualizarPago(Pago pago) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "UPDATE pagos SET id_usuario = ?, tipo_servicio = ?, monto = ?, fecha_pago = ?, " +
                         "estado = ?, metodo_pago = ? WHERE id_pago = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pago.getIdUsuario());
            pstmt.setString(2, pago.getTipoServicio().getValor());
            pstmt.setBigDecimal(3, pago.getMonto());
            pstmt.setDate(4, pago.getFechaPago());
            pstmt.setString(5, pago.getEstado().getValor());
            pstmt.setString(6, pago.getMetodoPago().getValor());
            pstmt.setInt(7, pago.getIdPago());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar pago: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    /**
     * Marca un pago como pagado (cambia el estado de pendiente a pagado)
     * 
     * @param idPago ID del pago a marcar como pagado
     * @return true si se actualizó correctamente, false en caso contrario
     */
    public boolean marcarComoPagado(int idPago) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtVerificar = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            // Primero verificar que el pago existe y está en estado pendiente
            String sqlVerificar = "SELECT estado FROM pagos WHERE id_pago = ?";
            pstmtVerificar = conn.prepareStatement(sqlVerificar);
            pstmtVerificar.setInt(1, idPago);
            rs = pstmtVerificar.executeQuery();
            
            if (!rs.next()) {
                return false; // El pago no existe
            }
            
            String estadoActual = rs.getString("estado");
            if (!"pendiente".equalsIgnoreCase(estadoActual)) {
                return false; // El pago no está pendiente
            }
            
            // Cerrar el ResultSet y PreparedStatement de verificación antes de continuar
            rs.close();
            pstmtVerificar.close();
            
            // Actualizar el estado a pagado
            String sql = "UPDATE pagos SET estado = 'pagado' WHERE id_pago = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPago);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al marcar pago como pagado: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmtVerificar != null) pstmtVerificar.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos de verificación: " + e.getMessage());
            }
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public boolean eliminarPago(int idPago) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "DELETE FROM pagos WHERE id_pago = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPago);
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar pago: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    private List<Pago> procesarResultSet(ResultSet rs) throws Exception {
        List<Pago> lista = new ArrayList<>();
        while (rs.next()) {
            Pago pago = new Pago();
            pago.setIdPago(rs.getInt("id_pago"));
            pago.setIdUsuario(rs.getInt("id_usuario"));
            pago.setTipoServicio(Pago.TipoServicio.fromString(rs.getString("tipo_servicio")));
            pago.setMonto(rs.getBigDecimal("monto"));
            pago.setFechaPago(rs.getDate("fecha_pago"));
            pago.setEstado(Pago.EstadoPago.fromString(rs.getString("estado")));
            pago.setMetodoPago(Pago.MetodoPago.fromString(rs.getString("metodo_pago")));
            pago.setNombreUsuario(rs.getString("nombre_usuario"));
            lista.add(pago);
        }
        return lista;
    }
    
    private void cerrarRecursos(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
