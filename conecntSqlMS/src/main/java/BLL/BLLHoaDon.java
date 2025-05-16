/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALChiTietHoaDon;
import DAL.DALHoaDon;
import GUI.form_DangNhap;
import GUI.frm_HoaDon;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class BLLHoaDon {

    DAL.DALChiTietHoaDon dalChiTietHoaDon;
    DAL.DALHoaDon dalHoaDon;
    frm_HoaDon hoadon = null;

    public BLLHoaDon(frm_HoaDon frm) throws SQLException, ClassNotFoundException {
        dalChiTietHoaDon = new DALChiTietHoaDon();
        dalHoaDon = new DALHoaDon();
        hoadon = frm;
    }

    public void bllHD() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) dalChiTietHoaDon.dalChiTietHoaDon(); // Lấy dữ liệu từ DAL
        hoadon.jTable2.setModel(model); // Đổ dữ liệu lên JTable
    }
    public void BllCbHoaDon() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) dalHoaDon.dalHoaDon();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maHD = model.getValueAt(i, 0).toString(); // Cột 0: MaHD
            comboBoxModel.addElement(maHD);
        }
        hoadon.cbn_HoaDon.setModel(comboBoxModel); // Gán model cho ComboBox
    }
    public void BllThem()
    {
        try {
            Object selectedValue = hoadon.cbn_HoaDon.getSelectedItem();
            String maHD = selectedValue != null ? selectedValue.toString() : "";
            dalChiTietHoaDon.dalThem(Integer.toString(dalChiTietHoaDon.DalDem()+1), maHD,hoadon.txt_MaMatHang.getText(),Integer.parseInt(hoadon.txt_SoLuong.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(BLLHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadNgayLap() throws SQLException
    {
        Object selectedValue = hoadon.cbn_HoaDon.getSelectedItem();
        String maHD = selectedValue != null ? selectedValue.toString() : "";
        hoadon.txt_NgayLap.setText(dalHoaDon.DALTimNgayLapAsString(maHD));
    }
}
