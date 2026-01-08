package com.residenciasegura.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase simple para conectar con MySQL
 * 
 * @author DARIX
 */
public class ConexionDB {
    
    // Datos de conexión - Cambia estos valores si es necesario
    private static final String URL = "jdbc:mysql://localhost:3306/ResidenciaSegura";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456"; // Contraseña de MySQL
    
    /**
     * Obtiene una conexión a la base de datos MySQL
     * 
     * @return Connection - La conexión a la base de datos
     */
    public static Connection obtenerConexion() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Crear y retornar la conexión
            Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("✓ Conexión exitosa a la base de datos");
            return conexion;
            
        } catch (ClassNotFoundException e) {
            System.out.println("✗ ERROR: No se encontró el driver de MySQL");
            System.out.println("  Asegúrate de tener mysql-connector-j en tu pom.xml");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("✗ ERROR al conectar a la base de datos:");
            System.out.println("  Mensaje: " + e.getMessage());
            System.out.println("  Verifica que:");
            System.out.println("  1. MySQL esté ejecutándose");
            System.out.println("  2. La base de datos 'ResidenciaSegura' exista");
            System.out.println("  3. El usuario y contraseña sean correctos");
            System.out.println("  4. La URL sea correcta: " + URL);
            e.printStackTrace();
            return null;
        }
    }
}
