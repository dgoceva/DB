package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcColumnHeaders {

    public static void main() {

        String cs = "jdbc:mysql://localhost:3306/testdbjava?useSSL=false";
        String user = "testuser";
        String password = "test623";

        String query = "SELECT Name, Title From Authors, "
                + "Books WHERE Authors.Id=Books.AuthorId";

        try (Connection con = DriverManager.getConnection(cs, user, password);
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();

            String colname1 = meta.getColumnName(1);
            String colname2 = meta.getColumnName(2);

            String header = String.format("%-21s%s", colname1, colname2);
            System.out.println(header);

            while (rs.next()) {

                String row = String.format("%-21s", rs.getString(1));
                System.out.print(row);
                System.out.println(rs.getString(2));
            }
        } catch (SQLException ex) {
            
            Logger lgr = Logger.getLogger(JdbcColumnHeaders.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}