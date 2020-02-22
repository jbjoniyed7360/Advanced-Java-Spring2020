/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.ajlab1.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Joniyed
 */
public class ConnectionSingleton {

    private static final String HOSTNAME = "localhost:3306";
    private static final String DBNAME = "productdb";
    private static final String DBURL = "jdbc:mysql://" + HOSTNAME + "/" + DBNAME;
    private static final String USERNAME = "root";
    private static final String PASS = "";
    private static Connection con = null;
    private static final ConnectionSingleton CS = new ConnectionSingleton();

    private ConnectionSingleton() {
        try {
            con = DriverManager.getConnection(DBURL, USERNAME, PASS);
            System.out.println("\nSuccessfully connected to the database...");
        } catch (SQLException e) {
            System.out.println("Failed.."+e.toString());
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
