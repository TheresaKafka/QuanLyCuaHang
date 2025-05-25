/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALMaLoai;
import GUI.frm_MaLoai;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class BLlMaLoai {
    DAL.DALMaLoai dALMaLoai;
    frm_MaLoai frm;
    public BLlMaLoai(frm_MaLoai frm) {
        this.frm=frm;
        dALMaLoai=new DALMaLoai();
    }
    public void BllLoadMaLoai()
    {
        DefaultTableModel model = (DefaultTableModel) dALMaLoai.dalMaLoai(); // Lấy dữ liệu từ DAL
        frm.jTable1.setModel(model); // Đổ dữ liệu lên JTable
    }
    public void BllThem()
    {
        dALMaLoai.dalThem(frm.txt_MaLoai.getText(),frm.txt_TenLoai.getText());
    }
    public void BllXoa()
    {
        dALMaLoai.dalXoa(frm.txt_MaLoai.getText());
    }
    public void BllSua()
    {
        dALMaLoai.dalSua(frm.txt_MaLoai.getText(), frm.txt_TenLoai.getText());
    }
    public void ClickCell()
    {
        int selectedRow = frm.jTable1.getSelectedRow();
        if (selectedRow != -1) {
            String maHD = frm.jTable1.getValueAt(selectedRow, 0).toString();
            String ngayNhap = frm.jTable1.getValueAt(selectedRow, 1).toString();
            frm.txt_MaLoai.setText(maHD);
            frm.txt_TenLoai.setText(ngayNhap);
        }
    }
}
