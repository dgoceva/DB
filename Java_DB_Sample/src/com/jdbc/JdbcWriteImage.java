package com.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcWriteImage {

    public static void main() {

        String cs = "jdbc:mysql://localhost:3306/testdbjava?useSSL=false";
        String user = "testuser";
        String password = "test623";

        String sql = "INSERT INTO Images(id,Data) VALUES(?,?)";

        try (Connection con = DriverManager.getConnection(cs, user, password);
                PreparedStatement pst = con.prepareStatement(sql)) {

            File myFile = new File("src/main/resources/tree.jfif");

            try (FileInputStream fin = new FileInputStream(myFile)) {

            	pst.setInt(1, 1);
                pst.setBinaryStream(2, fin, (int) myFile.length());
                pst.executeUpdate();
                System.out.println("Image is inserted successfully");
                
            } catch (IOException ex) {
                
                Logger lgr = Logger.getLogger(JdbcWriteImage.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            
            Logger lgr = Logger.getLogger(JdbcWriteImage.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}