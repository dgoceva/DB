package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcNoTransaction {

    public static void main() {

        String url = "jdbc:mysql://localhost:3306/testdbjava?useSSL=false";
        String user = "testuser";
        String password = "test623";

        try (Connection con = DriverManager.getConnection(url, user, password);
                Statement st = con.createStatement()) {

            st.executeUpdate("UPDATE Authors SET Name = 'Leo Tolstoy'"
                    + "WHERE Id = 1");
            st.executeUpdate("UPDATE Books SET Title = 'War and Peace'"
                    + "WHERE Id = 1");
            st.executeUpdate("UPDATE Books SET Titl = 'Anna Karenina'"
                    + "WHERE Id = 2");

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JdbcNoTransaction.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}