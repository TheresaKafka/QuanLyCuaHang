/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALTaiKhoan;
import GUI.form_DangNhap;
import GUI.frm_ChiTietHoaDon;
import com.mycompany.conecntsqlms.LopChung;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class BLLDangNhap {
    DALTaiKhoan dalTk;
    form_DangNhap Dn=null;
    public BLLDangNhap(form_DangNhap frm) throws SQLException, ClassNotFoundException
    {
        dalTk = new DALTaiKhoan();
        Dn = frm;
    }
    public void BLL_DN() throws SQLException
    {
        int ketqua = dalTk.dalDangNhap(Dn.txt_TenTK.getText(),Dn.txt_MatKhau.getText());
        if (ketqua>=1)
        {
            frm_ChiTietHoaDon HD =new frm_ChiTietHoaDon();
            HD.setVisible(true);
        }
    }
}
