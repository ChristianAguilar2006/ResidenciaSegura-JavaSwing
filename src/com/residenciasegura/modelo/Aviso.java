package com.residenciasegura.modelo;

import java.sql.Timestamp;

public class Aviso {
    
    private int idAviso;
    private int idAdministrador;
    private String titulo;
    private String mensaje;
    private TipoAviso tipo;
    private Timestamp fechaPublicacion;
    private boolean activo;
    
    private String nombreAdministrador;
    
    public enum TipoAviso {
        INFORMATIVO("informativo"),
        ALERTA("alerta"),
        MANTENIMIENTO("mantenimiento"),
        EMERGENCIA("emergencia");
        
        private final String valor;
        
        TipoAviso(String valor) {
            this.valor = valor;
        }
        
        public String getValor() {
            return valor;
        }
        
        public static TipoAviso fromString(String valor) {
            if (valor == null) return null;
            for (TipoAviso tipo : TipoAviso.values()) {
                if (tipo.valor.equalsIgnoreCase(valor)) {
                    return tipo;
                }
            }
            return null;
        }
    }
    
    public Aviso() {
    }
    
    public Aviso(int idAdministrador, String titulo, String mensaje, TipoAviso tipo) {
        this.idAdministrador = idAdministrador;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.activo = true;
    }
    
    public int getIdAviso() {
        return idAviso;
    }
    
    public void setIdAviso(int idAviso) {
        this.idAviso = idAviso;
    }
    
    public int getIdAdministrador() {
        return idAdministrador;
    }
    
    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public TipoAviso getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoAviso tipo) {
        this.tipo = tipo;
    }
    
    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }
    
    public void setFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getNombreAdministrador() {
        return nombreAdministrador;
    }
    
    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }
}

