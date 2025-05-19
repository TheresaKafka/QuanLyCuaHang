/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import com.mycompany.conecntsqlms.LopChung;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ACER
 */
public class DALChiTietHoaDon {
    LopChung lopchung;
    public DALChiTietHoaDon() throws SQLException, ClassNotFoundException {
        lopchung = new LopChung();
    }
   public TableModel dalChiTietHoaDon() throws SQLException {
        try {
           String sqlC = "SELECT * FROM ChiTietHoaDon";
            ResultSet rs = lopchung.loadDL(sqlC); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }
    public TableModel DALcb_MaHoaDon(String MaHoaDon) throws SQLException {
        try {
            String sqlcbMaHoaDon = "SELECT * FROM ChiTietHoaDon WHERE MaHD = '" + MaHoaDon + "'";
            ResultSet rs = lopchung.loadDL(sqlcbMaHoaDon); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }
   public void dalThem(String maHDCT,String maHD,String maMH,int soLuong) throws SQLException
   {
        String sql = "INSERT INTO ChiTietHoaDon (MaHDCT, MaHD, MaMH, SoLuong) VALUES (" +
                     "'" + maHDCT + "', " +
                     "'" + maHD + "', " +
                     "'" + maMH + "', " +
                     "'" + soLuong + "')";
        lopchung.executeNonQuery(sql);
   }
   public void dalXoa(String ma) throws SQLException
   {
       String sqlXoa ="delete from ChiTietHoaDon where MaHDCT = '"+ma+"'";
       lopchung.executeNonQuery(sqlXoa);
   }
   public void DalSua(String maHDCT,String maHD,String maMH,int soLuong) throws SQLException
   {
       String sql = "UPDATE ChiTietHoaDon SET " +
                     "MaHD = '" + maHD + "', " +
                     "MaMH = '" + maMH + "', " +
                     "SoLuong = '" + soLuong + "' " +
                     "WHERE MaHDCT = '" + maHDCT + "'";
       lopchung.executeNonQuery(sql);
   }
   public int DalDem() throws SQLException
   {
       String sqlDem = "select count (*) from ChiTietHoaDon";
       return (int)lopchung.executeScalar(sqlDem);
   }
   
}
