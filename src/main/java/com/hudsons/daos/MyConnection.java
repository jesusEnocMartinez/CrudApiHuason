
package com.hudsons.daos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyConnection {

    private static Connection CONNECTION_INSTANCE = null;

    public static Connection getInstance() {
        if (CONNECTION_INSTANCE == null) {
            try {
                Class.forName("org.postgresql.Driver");
                CONNECTION_INSTANCE = DriverManager.getConnection("jdbc:postgresql://localhost:54320/hudsons", "postgres", "12345");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return CONNECTION_INSTANCE;
    }

}
