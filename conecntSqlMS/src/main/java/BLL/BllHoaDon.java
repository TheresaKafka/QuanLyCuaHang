/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DALHoaDon;
import GUI.frm_HoaDon;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class BllHoaDon {
    DAL.DALHoaDon dALHoaDon;
    frm_HoaDon frm = null;

    public BllHoaDon(frm_HoaDon frm) throws ClassNotFoundException, SQLException {
        dALHoaDon = new DALHoaDon();
        this.frm =frm;
    }
    public void bllthem(){
            Date selectedDate = frm.jDateChooser1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = sdf.format(selectedDate);
            dALHoaDon.DalThem(frm.txt_maHD.getText(), selectedDate);
    } 
    public void bllXoa()
    {
        dALHoaDon.DalXoa(frm.txt_maHD.getText());
    }
    public void bllSua()
    {
            Date selectedDate = frm.jDateChooser1.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = sdf.format(selectedDate);
            dALHoaDon.DalThem(frm.txt_maHD.getText(), selectedDate);
    }
    public void bllLoadHoaDon()
    {
        try {
            DefaultTableModel model = (DefaultTableModel) dALHoaDon.dalHoaDon(); // Lấy dữ liệu từ DAL
            frm.jTable2.setModel(model); // Đổ dữ liệu lên JTable
        } catch (SQLException ex) {
            Logger.getLogger(BllHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ClickCell()
    {
        int selectedRow = frm.jTable2.getSelectedRow();
        if (selectedRow != -1) {
            try {
                String maHD = frm.jTable2.getValueAt(selectedRow, 0).toString();
                String ngayNhap = frm.jTable2.getValueAt(selectedRow, 1).toString();
                frm.txt_maHD.setText(maHD);
                frm.jDateChooser1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(ngayNhap));
            } catch (ParseException ex) {
                Logger.getLogger(BllHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
