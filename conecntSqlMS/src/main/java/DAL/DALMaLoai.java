/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import com.mycompany.conecntsqlms.LopChung;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ACER
 */
public class DALMaLoai {
    LopChung lopchung;

    public DALMaLoai(){
        try {
            lopchung = new LopChung();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DALMaLoai.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DALMaLoai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public TableModel dalMaLoai()
    {
        try {
            String sqlcbMaHoaDon = "SELECT * FROM LoaiHang";
            ResultSet rs = lopchung.loadDL(sqlcbMaHoaDon); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }
    public void dalThem(String maLoai,String tenLoai)
    {
        try {
            String sqlThemLoai = "INSERT INTO LoaiHang (MaLoai, TenLoai) "
                    + "VALUES('" + maLoai + "', N'" + tenLoai + "')";
            lopchung.executeNonQuery(sqlThemLoai);
        } catch (SQLException ex) {
            Logger.getLogger(DALMaLoai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dalXoa(String ma)
    {
        try {
            String sqlXoa = "delete from Loai where MaLoai = '" + ma + "'";
            lopchung.executeNonQuery(sqlXoa);
        } catch (SQLException ex) {
            Logger.getLogger(DALHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dalSua(String maLoai,String tenLoai)
    {
        try {
            String sqlThemLoai = "INSERT INTO LoaiHang (MaLoai, TenLoai) "
                    + "VALUES('" + maLoai + "', N'" + tenLoai + "')";
            lopchung.executeNonQuery(sqlThemLoai);
        } catch (SQLException ex) {
            Logger.getLogger(DALMaLoai.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
