package com.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mariadb.jdbc.MariaDbDataSource;

public class ComLineDSEx {

    public static MariaDbDataSource getMySQLDataSource() {

        Properties props = new Properties();

        String fileName = "src/main/resources/database.properties";

        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
        } catch (IOException ex) {
            Logger lgr = Logger.getLogger(ComLineDSEx.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        MariaDbDataSource ds = new MariaDbDataSource();
        try {
			ds.setUrl(props.getProperty("mysql.url"));
	        ds.setUser(props.getProperty("mysql.username"));
	        ds.setPassword(props.getProperty("mysql.password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return ds;
    }

    public static void main() {

        MariaDbDataSource ds = getMySQLDataSource();

        String query = "SELECT VERSION()";

        try (Connection con = ds.getConnection();
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {

                String version = rs.getString(1);
                System.out.println(version);
            }
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(ComLineDSEx.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}