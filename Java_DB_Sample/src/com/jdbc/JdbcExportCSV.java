package com.jdbc;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcExportCSV {
// before invoking main() execute 
//	GRANT FILE ON *.* TO 'testuser'@'localhost';
    public static void main() {

        String url = "jdbc:mysql://localhost:3306/testdbjava?useSSL=false";
        String user = "testuser";
        String password = "test623";

        String query = "SELECT Name, Title INTO OUTFILE "
                + "'D:\\xampp\\mysql\\data\\authors_books.csv' "
                + "FIELDS TERMINATED BY ',' "
                + "FROM Authors, Books WHERE "
                + "Authors.Id=Books.AuthorId";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.execute();
        } catch (SQLException ex) {
            
            Logger lgr = Logger.getLogger(JdbcExportCSV.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}