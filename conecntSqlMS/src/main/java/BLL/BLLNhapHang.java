/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALNhapHang;
import GUI.frm_NhapHang;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class BLLNhapHang {

    DAL.DALNhapHang dalNhapHang;
    frm_NhapHang frm;
    Vector<Object[]> data = new Vector<>();
    Vector<Object[]> data1 = new Vector<>();

    public BLLNhapHang(frm_NhapHang frm) {
        try {
            dalNhapHang = new DALNhapHang();
            this.frm = frm;
        } catch (SQLException ex) {
            Logger.getLogger(BLLNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BLLNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BllLoadNhapHang() {
        try {
            DefaultTableModel model = (DefaultTableModel) dalNhapHang.dalNhapHang(); // Lấy dữ liệu từ DAL
            frm.jTable2.setModel(model); // Đổ dữ liệu lên JTable 
        } catch (SQLException ex) {
            Logger.getLogger(BLLNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BllMatHang() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) dalNhapHang.dalMatHang();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        data.clear();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maMH = model.getValueAt(i, 0) != null ? model.getValueAt(i, 0).toString() : "";
            String tenMH = model.getValueAt(i, 1) != null ? model.getValueAt(i, 1).toString() : "";
            data.add(new Object[]{maMH, tenMH});
            comboBoxModel.addElement(tenMH);
        }
        frm.cb_MatHang.setModel(comboBoxModel); // Gán model cho ComboBox
    }

    public void bllLoadTenTK() {
        DefaultTableModel model = (DefaultTableModel) dalNhapHang.dalTenTk();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        data1.clear();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maMH = model.getValueAt(i, 0) != null ? model.getValueAt(i, 0).toString() : "";
            String tenMH = model.getValueAt(i, 1) != null ? model.getValueAt(i, 1).toString() : "";
            data1.add(new Object[]{maMH, tenMH});
            comboBoxModel.addElement(tenMH);
        }
        frm.cb_TenDN.setModel(comboBoxModel); // Gán model cho ComboBox
    }

    public void bllThem() {
        int selectedIndex = frm.cb_MatHang.getSelectedIndex();
        int selectedTenTK = frm.cb_TenDN.getSelectedIndex();
        if (selectedIndex >= 0 && selectedTenTK >= 0&& Integer.parseInt(frm.txt_SoLuong.getText())>=0) {
            String maHang = data.get(selectedIndex)[0].toString();
            String TenTK = data1.get(selectedIndex)[0].toString();
            Date selectedDate = frm.jDateChooser1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = sdf.format(selectedDate);
            dalNhapHang.DalThem(frm.txt_MaNhapHang.getText(), selectedDate, maHang, Integer.parseInt(frm.txt_SoLuong.getText()), TenTK);
            //dalMatHang.dalThem(frm.txt_MaMH.getText(), frm.txt_TenMH.getText(), maLoai,formattedDate);
            dalNhapHang.dalUpdateSoLuong(maHang, frm.txt_SoLuong.getText());
        }
    }

    public void bllXoa() {
        dalNhapHang.DalXoa(frm.txt_MaNhapHang.getText());
    }

    public void bllSua() {
        int selectedIndex = frm.cb_MatHang.getSelectedIndex();
        int selectedTenTK = frm.cb_TenDN.getSelectedIndex();
        if (selectedIndex >= 0 && selectedTenTK >= 0) {
            String maHang = data.get(selectedIndex)[0].toString();
            String TenTK = data1.get(selectedIndex)[0].toString();
            Date selectedDate = frm.jDateChooser1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = sdf.format(selectedDate);
            dalNhapHang.DalSua(frm.txt_MaNhapHang.getText(), selectedDate, maHang, Integer.parseInt(frm.txt_SoLuong.getText()), TenTK);
            //dalMatHang.dalThem(frm.txt_MaMH.getText(), frm.txt_TenMH.getText(), maLoai,formattedDate);
        }
    }

    public void bllcb_MaLoai() {
        int selectedIndex = frm.cb_MatHang.getSelectedIndex();
        if (selectedIndex >= 0) {
            String maLoai = data.get(selectedIndex)[0].toString();
            try {
                DefaultTableModel model = (DefaultTableModel) dalNhapHang.dalCbMaMH(maLoai);
                frm.jTable2.setModel(model);
            } catch (Exception e) {
            }
        }
    }

    public void bllcb_TenTK() {
        int selectedIndex = frm.cb_TenDN.getSelectedIndex();
        if (selectedIndex >= 0) {
            String maLoai = data1.get(selectedIndex)[0].toString();
            try {
                DefaultTableModel model = (DefaultTableModel) dalNhapHang.dalCbTenTk(maLoai);
                frm.jTable2.setModel(model);
            } catch (Exception e) {
            }
        }
    }

    public void clickcell() throws ParseException {
        int selectedRow = frm.jTable2.getSelectedRow();
        if (selectedRow != -1) {
            String maNH = frm.jTable2.getValueAt(selectedRow, 0).toString();
            String ngayNhap = frm.jTable2.getValueAt(selectedRow, 1).toString();
            String maMH = frm.jTable2.getValueAt(selectedRow, 2).toString();
            String soLuong = frm.jTable2.getValueAt(selectedRow, 3).toString();
            String tenTk = frm.jTable2.getValueAt(selectedRow, 4).toString();

            frm.txt_MaNhapHang.setText(maNH);
            frm.jDateChooser1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(ngayNhap));
            frm.cb_MatHang.setSelectedItem(maMH);
            frm.txt_SoLuong.setText(soLuong);
            frm.cb_TenDN.setSelectedItem(tenTk);
        }
    }
    public void bllTim()
    {
        DefaultTableModel model = (DefaultTableModel) dalNhapHang.dalTim(frm.txt_Tim.getText()); // Lấy dữ liệu từ DAL
        frm.jTable2.setModel(model); // Đổ dữ liệu lên JTable
    }
}
