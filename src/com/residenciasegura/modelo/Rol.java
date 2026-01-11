package com.residenciasegura.modelo;

public enum Rol {
    ADMIN,
    RESIDENTE,
    GUARDIA;
    
    public static Rol fromString(String valor) {
        if (valor == null) return null;
        
        if (valor.equalsIgnoreCase("admin")) {
            return ADMIN;
        } else if (valor.equalsIgnoreCase("residente")) {
            return RESIDENTE;
        } else if (valor.equalsIgnoreCase("guardia")) {
            return GUARDIA;
        }
        
        return null;
    }
    
    public String getValor() {
        if (this == ADMIN) return "admin";
        if (this == RESIDENTE) return "residente";
        if (this == GUARDIA) return "guardia";
        return "";
    }
}

