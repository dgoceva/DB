package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcAutoGenKey {

    public static void main() {

        String url = "jdbc:mysql://localhost:3306/testdbjava?useSSL=false";
        String user = "testuser";
        String password = "test623";

        String author = "Oscar Wilde";
        String sql = "INSERT INTO Authors(Name) VALUES(?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, author);
            pst.executeUpdate();

            try (ResultSet rs = pst.getGeneratedKeys()) {

                if (rs.first()) {

                    System.out.printf("The ID of new author: %d", rs.getLong(1));
                }
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JdbcAutoGenKey.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}