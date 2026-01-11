package com.residenciasegura.modelo;

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

