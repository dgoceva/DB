package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcBatchUpdate {

    public static void main() {

        String url = "jdbc:mysql://localhost:3306/testdbjava?useSSL=false";
        String user = "testuser";
        String password = "test623";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            try (Statement st = con.createStatement()) {

                con.setAutoCommit(false);

                st.addBatch("DROP TABLE IF EXISTS Authors2");
                st.addBatch("CREATE TABLE Authors2(Id INT PRIMARY KEY, "
                        + "Name VARCHAR(100))");
                st.addBatch("INSERT INTO Authors2(Id, Name) "
                        + "VALUES(1, 'Jack London')");
                st.addBatch("INSERT INTO Authors2(Id, Name) "
                        + "VALUES(2, 'Honore de Balzac')");
                st.addBatch("INSERT INTO Authors2(Id, Name) "
                        + "VALUES(3, 'Lion Feuchtwanger')");
                st.addBatch("INSERT INTO Authors2(Id, Name) "
                        + "VALUES(4, 'Emile Zola')");
                st.addBatch("INSERT INTO Authors2(Id, Name) "
                        + "VALUES(5, 'Truman Capote')");
                st.addBatch("INSERT INTO Authors2(Id, Name) "
                        + "VALUES(6, 'Umberto Eco')");

                int counts[] = st.executeBatch();

                con.commit();

                System.out.printf("Committed %d updates", counts.length);
                
            } catch (SQLException ex) {
                try {

                    con.rollback();
                } catch (SQLException ex2) {

                    Logger lgr = Logger.getLogger(JdbcBatchUpdate.class.getName());
                    lgr.log(Level.FINEST, ex2.getMessage(), ex2);
                }

                Logger lgr = Logger.getLogger(JdbcBatchUpdate.class.getName());
                lgr.log(Level.FINEST, ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JdbcBatchUpdate.class.getName());
            lgr.log(Level.FINEST, ex.getMessage(), ex);
        }
    }
}