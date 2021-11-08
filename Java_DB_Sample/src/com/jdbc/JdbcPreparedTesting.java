package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcPreparedTesting {

    public static void main() {

        String cs = "jdbc:mysql://localhost:3306/testdbjava?useSSL=false";
        String user = "testuser";
        String password = "test623";

        String sql = "INSERT INTO Testing(Id) VALUES(?)";

        try (Connection con = DriverManager.getConnection(cs, user, password);
                PreparedStatement pst = con.prepareStatement(sql)) {

        	long start = System.nanoTime();
            for (int i = 1; i <= 500; i++) {

                pst.setInt(1, i * 2);
                pst.executeUpdate();
            }
            long end = System.nanoTime();
            System.out.println(""+(end-start)/1e6);

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JdbcPreparedTesting.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}