/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static final String cadena = "jdbc:oracle:thin:@//localhost:1521/XE"; // Cambia XE según tu SID
    /**
     * RAM
     */
    /*private static final String us = "SYSTEM"; // Usuario de Oracle
    private static final String contra = "Sa1"; // Contraseña de Oracle*/
    /**
     * BRYAN
     */
    private static final String us = "system"; // Usuario de Oracle
    private static final String contra = "system"; // Contraseña de Oracle
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(cadena, us, contra);
            
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver de Oracle", e);
        }
        
    }
}
