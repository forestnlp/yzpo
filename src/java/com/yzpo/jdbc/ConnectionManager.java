package com.yzpo.jdbc;

import java.sql.*;

public class ConnectionManager {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin://localhost:1521/orcl",
                    "paos",
                    "paos");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String [] args) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select sysdate from dual");
        if(rs.next())
            System.out.println(rs.getString(1));
        rs.close();
        stmt.close();
        conn.close();
    }

}
