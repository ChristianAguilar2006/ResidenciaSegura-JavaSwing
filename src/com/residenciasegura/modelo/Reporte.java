package com.residenciasegura.modelo;

import java.sql.Timestamp;

/**
 * Clase modelo que representa un reporte en el sistema
 * 
 * @author DARIX
 */
public class Reporte {
    
    private int idReporte;
    private int idUsuario;
    private TipoReporte tipo;
    private String descripcion;
    private String ubicacion;
    private Timestamp fechaReporte;
    private EstadoReporte estado;
    private Prioridad prioridad;
    private Integer idGuardiaAtendio;
    private Timestamp fechaResolucion;
    
    // Datos adicionales para mostrar
    private String nombreUsuario;
    private String nombreGuardia;
    
    /**
     * Enum para los tipos de reporte
     */
    public enum TipoReporte {
        SEGURIDAD("seguridad"),
        MANTENIMIENTO("mantenimiento"),
        RUIDO("ruido"),
        EMERGENCIA("emergencia"),
        OTRO("otro");
        
        private final String valor;
        
        TipoReporte(String valor) {
            this.valor = valor;
        }
        
        public String getValor() {
            return valor;
        }
        
        public static TipoReporte fromString(String valor) {
            if (valor == null) return null;
            for (TipoReporte tipo : TipoReporte.values()) {
                if (tipo.valor.equalsIgnoreCase(valor)) {
                    return tipo;
                }
            }
            return null;
        }
    }
    
    /**
     * Enum para los estados del reporte
     */
    public enum EstadoReporte {
        PENDIENTE("pendiente"),
        EN_PROCESO("en_proceso"),
        RESUELTO("resuelto"),
        CANCELADO("cancelado");
        
        private final String valor;
        
        EstadoReporte(String valor) {
            this.valor = valor;
        }
        
        public String getValor() {
            return valor;
        }
        
        public static EstadoReporte fromString(String valor) {
            if (valor == null) return null;
            for (EstadoReporte estado : EstadoReporte.values()) {
                if (estado.valor.equalsIgnoreCase(valor)) {
                    return estado;
                }
            }
            return null;
        }
    }
    
    /**
     * Enum para las prioridades
     */
    public enum Prioridad {
        BAJA("baja"),
        MEDIA("media"),
        ALTA("alta"),
        CRITICA("critica");
        
        private final String valor;
        
        Prioridad(String valor) {
            this.valor = valor;
        }
        
        public String getValor() {
            return valor;
        }
        
        public static Prioridad fromString(String valor) {
            if (valor == null) return null;
            for (Prioridad prioridad : Prioridad.values()) {
                if (prioridad.valor.equalsIgnoreCase(valor)) {
                    return prioridad;
                }
            }
            return null;
        }
    }
    
    // Constructores
    public Reporte() {
    }
    
    public Reporte(int idUsuario, TipoReporte tipo, String descripcion, String ubicacion, EstadoReporte estado, Prioridad prioridad) {
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.prioridad = prioridad;
    }
    
    // Getters y Setters
    public int getIdReporte() {
        return idReporte;
    }
    
    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public TipoReporte getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoReporte tipo) {
        this.tipo = tipo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public Timestamp getFechaReporte() {
        return fechaReporte;
    }
    
    public void setFechaReporte(Timestamp fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
    
    public EstadoReporte getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoReporte estado) {
        this.estado = estado;
    }
    
    public Prioridad getPrioridad() {
        return prioridad;
    }
    
    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
    
    public Integer getIdGuardiaAtendio() {
        return idGuardiaAtendio;
    }
    
    public void setIdGuardiaAtendio(Integer idGuardiaAtendio) {
        this.idGuardiaAtendio = idGuardiaAtendio;
    }
    
    public Timestamp getFechaResolucion() {
        return fechaResolucion;
    }
    
    public void setFechaResolucion(Timestamp fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getNombreGuardia() {
        return nombreGuardia;
    }
    
    public void setNombreGuardia(String nombreGuardia) {
        this.nombreGuardia = nombreGuardia;
    }
}

