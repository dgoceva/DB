package com.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcReadImage {

    public static void main() {

        String url = "jdbc:mysql://localhost:3306/testdbjava?useSSL=false";
        String user = "testuser";
        String password = "test623";

        String query = "SELECT Data FROM Images LIMIT 1";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet result = pst.executeQuery()) {

            if (result.next()) {

                String fileName = "src/main/resources/tree2.jfif";
                
                try (FileOutputStream fos = new FileOutputStream(fileName)) {

                    Blob blob = result.getBlob("Data");
                    int len = (int) blob.length();

                    byte[] buf = blob.getBytes(1, len);

                    fos.write(buf, 0, len);
                    
                    System.out.println("Image is selected successfully");
                } catch (IOException ex) {
                
                    Logger lgr = Logger.getLogger(JdbcReadImage.class.getName());
                    lgr.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JdbcReadImage.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}