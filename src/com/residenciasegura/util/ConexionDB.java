package com.residenciasegura.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {
    
    private static final String URL = "jdbc:mysql://localhost:3306/ResidenciaSegura";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";
    
    public static Connection obtenerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexion exitosa a la base de datos");
            return conexion;
            
        } catch (ClassNotFoundException e) {
            System.out.println(" ERROR: No se encontro el driver de MySQL");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("ERROR al conectar a la base de datos:");
            System.out.println("  Mensaje: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
