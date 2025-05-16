/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conecntsqlms;

import GUI.form_DangNhap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class ConecntSqlMS {

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        LopChung lopchung=new LopChung();
        form_DangNhap frm = new form_DangNhap();
        frm.setVisible(true);
    }
    
}
