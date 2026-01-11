package com.residenciasegura.modelo;

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

