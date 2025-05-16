/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conecntsqlms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ACER
 */
public class LopChung {
    public Connection conn;

    public LopChung() throws ClassNotFoundException, SQLException {

        try {
            String hostname = "localhost";
            String sqlInstanceName = "DESKTOP-9VDTUVN"; //computer name 
            String sqlDatabase = "QLCuaHang";  //sql server database name
            String sqlUser = "sa";
            String sqlPassword = "admin"; //passwrod sa account

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //jdbc:sqlserver://localhost:1433;instance=COMPUTERBERRY;databaseName=Database;
            String connectURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyCuaHang;encrypt=true;trustServerCertificate=true;";

            conn = DriverManager.getConnection(connectURL, sqlUser, sqlPassword);
            System.out.println("Connect to database successful!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet loadDL(String sqlDL) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlDL);
        return rs;
    }

    public void executeNonQuery(String sql) throws SQLException {

        Statement stmt = conn.createStatement();
        try {
            int affectedRows = stmt.executeUpdate(sql);
            if (affectedRows >= 1) {
                System.out.println("Thành công");
            } else {
                System.out.println("Thất bại");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

    }

    public int executeScalar(String sql) throws SQLException {
        int result = -1;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        try {

            if (rs.next()) {
                result = rs.getInt(1); // Lấy cột đầu tiên
            }

        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return result;
    }
}
