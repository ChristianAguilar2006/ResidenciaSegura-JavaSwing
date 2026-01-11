package com.residenciasegura.modelo;

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

