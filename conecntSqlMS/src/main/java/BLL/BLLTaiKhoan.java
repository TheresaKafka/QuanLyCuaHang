/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALTaiKhoan;
import GUI.frm_TaiKhoan;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class BLLTaiKhoan {
    DAL.DALTaiKhoan dalTaiKhoan;
    frm_TaiKhoan frm;

    public BLLTaiKhoan(frm_TaiKhoan frm) {
        try {
            dalTaiKhoan = new DALTaiKhoan();
            this.frm=frm;
        } catch (SQLException ex) {
            Logger.getLogger(BLLTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BLLTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void bllThem()
    {
        try {
            dalTaiKhoan.DalThem(frm.txt_MaTK.getText(),frm.txt_TenTK.getText(),frm.txt_MK.getText(),frm.txt_TenDN.getText());
        } catch (SQLException ex) {
            Logger.getLogger(BLLTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void bllSua() throws SQLException
    {
        dalTaiKhoan.dalSua(frm.txt_MaTK.getText(),frm.txt_TenTK.getText(),frm.txt_MK.getText(),frm.txt_TenDN.getText());
    }
    public void bllXoa() throws SQLException
    {
        dalTaiKhoan.dalXoa(frm.txt_MaTK.getText());
    }
    public void bllLoadTaiKhoan()
    {
        DefaultTableModel model = (DefaultTableModel) dalTaiKhoan.dalTaiKhoan(); // Lấy dữ liệu từ DAL
        frm.jTable2.setModel(model); // Đổ dữ liệu lên JTable
    }
    public void ClickCell()
    {
        int selectedRow = frm.jTable2.getSelectedRow();
        if (selectedRow != -1) {
        String maTK = frm.jTable2.getValueAt(selectedRow, 0).toString();
        String tenTK = frm.jTable2.getValueAt(selectedRow, 1).toString();
        String matKhau = frm.jTable2.getValueAt(selectedRow, 2).toString();
        String tenDN = frm.jTable2.getValueAt(selectedRow, 3).toString(); // Giả sử cột TenDN là cột 3
        frm.txt_MaTK.setText(maTK);
        frm.txt_TenTK.setText(tenTK);
        frm.txt_MK.setText(matKhau);
        frm.txt_TenDN.setText(tenDN); // ComboBox nếu có danh sách chọn
        }
    }
    public void bllTim(){
        DefaultTableModel model = (DefaultTableModel) dalTaiKhoan.dalTim(frm.txt_Tim1.getText()); // Lấy dữ liệu từ DAL
        frm.jTable2.setModel(model); // Đổ dữ liệu lên JTable
    }
}
