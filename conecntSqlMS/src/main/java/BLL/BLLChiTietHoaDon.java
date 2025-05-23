/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALChiTietHoaDon;
import DAL.DALHoaDon;
import GUI.form_DangNhap;
import GUI.frm_ChiTietHoaDon;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class BLLChiTietHoaDon {

    Vector<Object[]> data = new Vector<>();
    DAL.DALChiTietHoaDon dalChiTietHoaDon;
    DAL.DALHoaDon dalHoaDon;
    frm_ChiTietHoaDon hoadon = null;

    public BLLChiTietHoaDon(frm_ChiTietHoaDon frm) throws SQLException, ClassNotFoundException {
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

    public void BllThem() {
        try {
            Object selectedValue = hoadon.cbn_HoaDon.getSelectedItem();
            String maHD = selectedValue != null ? selectedValue.toString() : "";
            int selectedIndex = hoadon.cb_MatHang.getSelectedIndex();
            
            if (selectedIndex >= 0)
            {
            String maMH = data.get(selectedIndex)[0].toString();
            if( Integer.parseInt(hoadon.txt_SoLuong.getText())<dalChiTietHoaDon.dalTimSoLuong(maMH))
            {
            dalChiTietHoaDon.dalThem(hoadon.txt_Stt.getText(), maHD, maMH, Integer.parseInt(hoadon.txt_SoLuong.getText()));
            dalChiTietHoaDon.dalUpdateSoLuong(maMH,String.valueOf( -1*Integer.parseInt(hoadon.txt_SoLuong.getText())));
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLLChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadNgayLap() throws SQLException {
        Object selectedValue = hoadon.cbn_HoaDon.getSelectedItem();
        String maHD = selectedValue != null ? selectedValue.toString() : "";
        hoadon.txt_NgayLap.setText(dalHoaDon.DALTimNgayLapAsString(maHD));
    }

    public void BllXoa() {
        try {
            dalChiTietHoaDon.dalXoa(hoadon.txt_Stt.getText());
        } catch (SQLException ex) {
            Logger.getLogger(BLLChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BllMatHang() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) dalHoaDon.dalMatHang();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        data.clear();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maMH = model.getValueAt(i, 0) != null ? model.getValueAt(i, 0).toString() : "";
            String tenMH = model.getValueAt(i, 1) != null ? model.getValueAt(i, 1).toString() : "";
            data.add(new Object[]{maMH, tenMH});
            comboBoxModel.addElement(tenMH);
        }
        hoadon.cb_MatHang.setModel(comboBoxModel); // Gán model cho ComboBox
    }

    public void BllSua() {
        Object selectedValueHD = hoadon.cbn_HoaDon.getSelectedItem();
        String maHD = selectedValueHD != null ? selectedValueHD.toString() : "";
        int selectedIndex = hoadon.cb_MatHang.getSelectedIndex();
        if (selectedIndex >= 0) {
            String maMH = data.get(selectedIndex)[0].toString();
            System.out.println("Mã mặt hàng được chọn: " + maMH);
            try {
            dalChiTietHoaDon.DalSua(hoadon.txt_Stt.getText(), maHD,maMH,Integer.parseInt(hoadon.txt_SoLuong.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(BLLChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    public void BllTim()
    {
        DefaultTableModel model = (DefaultTableModel) dalChiTietHoaDon.dalTim(hoadon.txt_Tim.getText()); // Lấy dữ liệu từ DAL
        hoadon.jTable2.setModel(model); // Đổ dữ liệu lên JTable
    }
    public void BllcbMatHang()
    {
        int selectedIndex = hoadon.cb_MatHang.getSelectedIndex();
        if (selectedIndex >= 0) {
            try {
                String maMH = data.get(selectedIndex)[0].toString();
                DefaultTableModel model = (DefaultTableModel) dalChiTietHoaDon.DALcb_MatHang(maMH); // Lấy dữ liệu từ DAL
                hoadon.jTable2.setModel(model); // Đổ dữ liệu lên JTable
            } catch (SQLException ex) {
                Logger.getLogger(BLLChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void BllcbHoaDon()
    {
        Object selectedValueHD = hoadon.cbn_HoaDon.getSelectedItem();
        String maHD = selectedValueHD != null ? selectedValueHD.toString() : "";
            try {
                DefaultTableModel model = (DefaultTableModel) dalChiTietHoaDon.DALcb_MaHoaDon(maHD); // Lấy dữ liệu từ DAL
                hoadon.jTable2.setModel(model); // Đổ dữ liệu lên JTable
            } catch (SQLException ex) {
                Logger.getLogger(BLLChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void BllClickCell() {
        int selectedRow = hoadon.jTable2.getSelectedRow();
        if (selectedRow != -1) {
            String maHDCT = hoadon.jTable2.getValueAt(selectedRow, 0).toString();
            String maHD = hoadon.jTable2.getValueAt(selectedRow, 1).toString();
            String maMH = hoadon.jTable2.getValueAt(selectedRow, 2).toString();
            String soLuong = hoadon.jTable2.getValueAt(selectedRow, 3).toString();
            hoadon.txt_Stt.setText(maHDCT);
            hoadon.cbn_HoaDon.setSelectedItem(maHD);
            hoadon.cb_MatHang.setSelectedItem(maMH);
            hoadon.txt_SoLuong.setText(soLuong);
            int giatien = dalChiTietHoaDon.dalTimGiaTien(maMH);
            int thanhtien = giatien*Integer.parseInt(soLuong);
            hoadon.txt_ThanhTien.setText(String.valueOf(thanhtien));
        }
    }
    public void BllTongTien()
    {
        int rowCount = hoadon.jTable2.getRowCount();
        int tongtien=0;
        for(int i=0;i<rowCount;i++)
        {
            String maMH = hoadon.jTable2.getValueAt(i, 2).toString();
            String soLuong = hoadon.jTable2.getValueAt(i, 3).toString();
            int giatien = dalChiTietHoaDon.dalTimGiaTien(maMH);
            int thanhtien = giatien*Integer.parseInt(soLuong);
            tongtien+=thanhtien;
            hoadon.txt_TongTien.setText(String.valueOf(tongtien));
        }    
    }
}
