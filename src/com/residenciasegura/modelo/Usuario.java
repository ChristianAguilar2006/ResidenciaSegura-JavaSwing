package com.residenciasegura.modelo;

import java.sql.Timestamp;

public class Usuario {
    
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private Rol rol;
    private String departamento;
    private String telefono;
    private Timestamp fechaRegistro;
    
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
    
    public Usuario() {
    }
    
    public Usuario(String nombre, String correo, String contrasena, Rol rol, String departamento) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.departamento = departamento;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public Rol getRol() {
        return rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", rol=" + rol +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}

