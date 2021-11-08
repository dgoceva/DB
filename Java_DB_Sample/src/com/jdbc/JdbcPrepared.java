package com.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcPrepared {

    public static void main() {

        String url = "jdbc:mysql://localhost:3306/testdbjava?useSSL=false";
        String user = "testuser";
        String password = "test623";
        
        String author = "Trygve Gulbranssen";
        String sql = "INSERT INTO Authors(Name) VALUES(?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, author);
            pst.executeUpdate();
            
            System.out.println("A new author has been inserted");

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JdbcPrepared.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        }
    }
}