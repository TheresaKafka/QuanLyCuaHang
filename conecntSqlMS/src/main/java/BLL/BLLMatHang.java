/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALMatHang;
import GUI.frm_MatHang;
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
public class BLLMatHang {
    Vector<Object[]> data = new Vector<>();
    DAL.DALMatHang dalMatHang;
    frm_MatHang frm = null;
    public BLLMatHang(frm_MatHang frm)
    {
        try {
            dalMatHang = new DALMatHang();
            this.frm = frm; 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BLLMatHang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BLLMatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BLLLoadMatHang()
    {
        try {
            DefaultTableModel model = (DefaultTableModel) dalMatHang.dalMatHang(); // Lấy dữ liệu từ DAL
            frm.jTable2.setModel(model); // Đổ dữ liệu lên JTable
        } catch (SQLException ex) {
            Logger.getLogger(BLLMatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void BllLoaiHang()
    {
        DefaultTableModel model = (DefaultTableModel) dalMatHang.dalMaLoai();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        data.clear();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maLoai = model.getValueAt(i, 0) != null ? model.getValueAt(i, 0).toString() : "";
            String tenLoai = model.getValueAt(i, 1) != null ? model.getValueAt(i, 1).toString() : "";
            data.add(new Object[]{maLoai, tenLoai});
            comboBoxModel.addElement(tenLoai);
        }
        frm.cbn_LoaiHang.setModel(comboBoxModel); // Gán model cho ComboBox
    }
    public void BllThem()
    {
        int selectedIndex = frm.cbn_LoaiHang.getSelectedIndex();
        if (selectedIndex >= 0)
            {
            String maLoai = data.get(selectedIndex)[0].toString(); 
            Date selectedDate = frm.jDateChooser1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = sdf.format(selectedDate);
            dalMatHang.DalThem(frm.txt_MaMH.getText(),frm.txt_TenMH.getText(),Integer.parseInt(frm.txt_DonGia.getText()), maLoai,Integer.parseInt(frm.txt_SoLuong.getText()), selectedDate);
            //dalMatHang.dalThem(frm.txt_MaMH.getText(), frm.txt_TenMH.getText(), maLoai,formattedDate);
            }
    }
    public void BllXoa()
    {
        dalMatHang.DalXoa(frm.txt_MaMH.getText());
    }
    public void BllSua()
    {
        int selectedIndex = frm.cbn_LoaiHang.getSelectedIndex();
        if (selectedIndex >= 0)
            {
            String maLoai = data.get(selectedIndex)[0].toString(); 
            Date selectedDate = frm.jDateChooser1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = sdf.format(selectedDate);
                try {
                   dalMatHang.DalSuaMatHang(frm.txt_MaMH.getText(),frm.txt_TenMH.getText(),Integer.parseInt(frm.txt_DonGia.getText()), maLoai,Integer.parseInt(frm.txt_SoLuong.getText()), selectedDate); 
                } catch (Exception e) {
                }
            }
    }
    public void BllCbLoaiHang()
    {
        int selectedIndex = frm.cbn_LoaiHang.getSelectedIndex();
        if (selectedIndex >= 0)
        {
            String maLoai = data.get(selectedIndex)[0].toString(); 
            try {
               DefaultTableModel model =(DefaultTableModel) dalMatHang.dalCbMaLoai(maLoai);
               frm.jTable2.setModel(model);
            } catch (Exception e) {
            }
        }
    }
    public void clickcell() throws ParseException
    {
        int selectedRow = frm.jTable2.getSelectedRow();
        if (selectedRow != -1) {
            String maMH = frm.jTable2.getValueAt(selectedRow, 0).toString();
            String tenMH = frm.jTable2.getValueAt(selectedRow, 1).toString();
            String donGia = frm.jTable2.getValueAt(selectedRow, 2).toString();
            String maLoai = frm.jTable2.getValueAt(selectedRow, 3).toString();
            String soLuong = frm.jTable2.getValueAt(selectedRow, 4).toString();
            String ngayHetHan = frm.jTable2.getValueAt(selectedRow, 5).toString();

            frm.txt_MaMH.setText(maMH);
            frm.txt_TenMH.setText(tenMH);
            frm.txt_DonGia.setText(donGia);
            frm.cbn_LoaiHang.setSelectedItem(maLoai);
            frm.txt_SoLuong.setText(soLuong);
            frm.jDateChooser1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(ngayHetHan));
        }
        
    }
    public void BllTim()
    {
        DefaultTableModel model = (DefaultTableModel) dalMatHang.dalTim(frm.txt_Tim.getText()); // Lấy dữ liệu từ DAL
        frm.jTable2.setModel(model); // Đổ dữ liệu lên JTable
    }
}
