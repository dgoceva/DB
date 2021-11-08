package com.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcNotPreparedTesting {

    public static void main() {

        String cs = "jdbc:mysql://localhost:3306/testdbjava?useSSL=false";
        String user = "testuser";
        String password = "test623";

        try (Connection con = DriverManager.getConnection(cs, user, password);
                Statement st = con.createStatement()) {

        	long start = System.nanoTime();
            for (int i = 1; i <= 500; i++) {

                String sql = "INSERT INTO Testing(Id) VALUES(" + 2 * i + ")";
                st.executeUpdate(sql);
            }
            long end = System.nanoTime();
            System.out.println(""+(end-start)/1e6);

        } catch (SQLException ex) {
        	System.out.println(ex);

            Logger lgr = Logger.getLogger(JdbcNotPreparedTesting.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}