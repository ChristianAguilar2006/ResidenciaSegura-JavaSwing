package com.residenciasegura.modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Pago {
    
    private int idPago;
    private int idUsuario;
    private TipoServicio tipoServicio;
    private BigDecimal monto;
    private Date fechaPago;
    private EstadoPago estado;
    private MetodoPago metodoPago;
    
    private String nombreUsuario;
    
    public enum TipoServicio {
        ALICUOTA("alicuota"),
        AGUA("agua"),
        LUZ("luz"),
        INTERNET("internet"),
        MANTENIMIENTO("mantenimiento"),
        OTRO("otro");
        
        private final String valor;
        
        TipoServicio(String valor) {
            this.valor = valor;
        }
        
        public String getValor() {
            return valor;
        }
        
        public static TipoServicio fromString(String valor) {
            if (valor == null) return null;
            for (TipoServicio tipo : TipoServicio.values()) {
                if (tipo.valor.equalsIgnoreCase(valor)) {
                    return tipo;
                }
            }
            return null;
        }
    }
    
    public enum EstadoPago {
        PENDIENTE("pendiente"),
        PAGADO("pagado"),
        RECHAZADO("rechazado");
        
        private final String valor;
        
        EstadoPago(String valor) {
            this.valor = valor;
        }
        
        public String getValor() {
            return valor;
        }
        
        public static EstadoPago fromString(String valor) {
            if (valor == null) return null;
            for (EstadoPago estado : EstadoPago.values()) {
                if (estado.valor.equalsIgnoreCase(valor)) {
                    return estado;
                }
            }
            return null;
        }
    }
    
    public enum MetodoPago {
        EFECTIVO("efectivo"),
        TRANSFERENCIA("transferencia"),
        TARJETA("tarjeta");
        
        private final String valor;
        
        MetodoPago(String valor) {
            this.valor = valor;
        }
        
        public String getValor() {
            return valor;
        }
        
        public static MetodoPago fromString(String valor) {
            if (valor == null) return null;
            for (MetodoPago metodo : MetodoPago.values()) {
                if (metodo.valor.equalsIgnoreCase(valor)) {
                    return metodo;
                }
            }
            return null;
        }
    }
    
    public Pago() {
    }
    
    public Pago(int idUsuario, TipoServicio tipoServicio, BigDecimal monto, Date fechaPago, EstadoPago estado, MetodoPago metodoPago) {
        this.idUsuario = idUsuario;
        this.tipoServicio = tipoServicio;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.estado = estado;
        this.metodoPago = metodoPago;
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
    
    public EstadoPago getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }
    
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
    
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}

