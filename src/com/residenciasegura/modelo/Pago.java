package com.residenciasegura.modelo;

import com.residenciasegura.util.ConexionDB;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Pago {
    
    private int idPago;
    private int idUsuario;
    private TipoServicio tipoServicio;
    private BigDecimal monto;
    private Date fechaPago;
    private String estado;
    
    private String nombreUsuario;
    
    public Pago() {
    }
    
    public Pago(int idUsuario, TipoServicio tipoServicio, BigDecimal monto, Date fechaPago, String estado) {
        this.idUsuario = idUsuario;
        this.tipoServicio = tipoServicio;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.estado = estado;
    }
    
    public int getIdPago() {
        return idPago;
    }
    
    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }
    
    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
    
    public BigDecimal getMonto() {
        return monto;
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
    public Date getFechaPago() {
        return fechaPago;
    }
    
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    // Métodos estaticos para operaciones de base de datos (antes en ControladorPago)
    
    public static List<Pago> obtenerTodos() {
        List<Pago> pagos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return pagos;
            
            String sql = "SELECT p.id_pago, p.id_usuario, p.tipo_servicio, p.monto, p.fecha_pago, p.estado, " +
                         "u.nombre AS nombre_usuario " +
                         "FROM pagos p " +
                         "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                         "ORDER BY p.fecha_pago DESC";
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdUsuario(rs.getInt("id_usuario"));
                pago.setTipoServicio(TipoServicio.fromString(rs.getString("tipo_servicio")));
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setFechaPago(rs.getDate("fecha_pago"));
                pago.setEstado(rs.getString("estado"));
                pago.setNombreUsuario(rs.getString("nombre_usuario"));
                pagos.add(pago);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener pagos: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        
        return pagos;
    }
    
    public static List<Pago> obtenerPorUsuario(int idUsuario) {
        List<Pago> pagos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return pagos;
            
            String sql = "SELECT p.id_pago, p.id_usuario, p.tipo_servicio, p.monto, p.fecha_pago, p.estado, " +
                         "u.nombre AS nombre_usuario " +
                         "FROM pagos p " +
                         "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                         "WHERE p.id_usuario = ? " +
                         "ORDER BY p.fecha_pago DESC";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdUsuario(rs.getInt("id_usuario"));
                pago.setTipoServicio(TipoServicio.fromString(rs.getString("tipo_servicio")));
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setFechaPago(rs.getDate("fecha_pago"));
                pago.setEstado(rs.getString("estado"));
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
    
    public static Pago obtenerPorId(int idPago) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return null;
            
            String sql = "SELECT p.id_pago, p.id_usuario, p.tipo_servicio, p.monto, p.fecha_pago, p.estado, " +
                         "u.nombre AS nombre_usuario " +
                         "FROM pagos p " +
                         "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                         "WHERE p.id_pago = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPago);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdUsuario(rs.getInt("id_usuario"));
                pago.setTipoServicio(TipoServicio.fromString(rs.getString("tipo_servicio")));
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setFechaPago(rs.getDate("fecha_pago"));
                pago.setEstado(rs.getString("estado"));
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
    
    public static boolean crearPago(Pago pago) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "INSERT INTO pagos (id_usuario, tipo_servicio, monto, fecha_pago, estado) " +
                         "VALUES (?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pago.getIdUsuario());
            pstmt.setString(2, pago.getTipoServicio().getValor());
            pstmt.setBigDecimal(3, pago.getMonto());
            pstmt.setDate(4, pago.getFechaPago());
            pstmt.setString(5, pago.getEstado());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al crear pago: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public static boolean actualizarPago(Pago pago) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sql = "UPDATE pagos SET id_usuario = ?, tipo_servicio = ?, monto = ?, fecha_pago = ?, " +
                         "estado = ? WHERE id_pago = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pago.getIdUsuario());
            pstmt.setString(2, pago.getTipoServicio().getValor());
            pstmt.setBigDecimal(3, pago.getMonto());
            pstmt.setDate(4, pago.getFechaPago());
            pstmt.setString(5, pago.getEstado());
            pstmt.setInt(6, pago.getIdPago());
            
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar pago: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }
    }
    
    public static boolean marcarComoPagado(int idPago) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtVerificar = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.obtenerConexion();
            if (conn == null) return false;
            
            String sqlVerificar = "SELECT estado FROM pagos WHERE id_pago = ?";
            pstmtVerificar = conn.prepareStatement(sqlVerificar);
            pstmtVerificar.setInt(1, idPago);
            rs = pstmtVerificar.executeQuery();
            
            if (!rs.next()) {
                return false;
            }
            
            String estadoActual = rs.getString("estado");
            if (!"pendiente".equalsIgnoreCase(estadoActual)) {
                return false;
            }
            
            rs.close();
            pstmtVerificar.close();
            
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
    
    public static boolean eliminarPago(int idPago) {
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
    
    
    private static void cerrarRecursos(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}

