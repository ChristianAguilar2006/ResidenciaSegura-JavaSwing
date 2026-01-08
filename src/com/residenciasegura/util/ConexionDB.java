package com.residenciasegura.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase simple para conectar con MySQL
 * 
 * @author DARIX
 */
public class ConexionDB {
    
    // Datos de conexión - Cambia estos valores si es necesario
    private static final String URL = "jdbc:mysql://localhost:3306/ResidenciaSegura";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    /**
     * Obtiene una conexión a la base de datos MySQL
     * 
     * @return Connection - La conexión a la base de datos
     * @throws SQLException si hay error al conectar
     */
    public static Connection obtenerConexion() throws SQLException {
        try {
            // Cargar el driver de MySQL
            Class.forName(DRIVER);
            
            // Crear y retornar la conexión
            Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("¡Conexión exitosa a la base de datos!");
            return conexion;
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL");
            throw new SQLException("Driver no encontrado", e);
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            throw e;
        }
    }
}

