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

    public void dalThem(String maHDCT, String maHD, String maMH, int soLuong) throws SQLException {
        String sql = "INSERT INTO ChiTietHoaDon (MaHDCT, MaHD, MaMH, SoLuong) VALUES ("
                + "'" + maHDCT + "', "
                + "'" + maHD + "', "
                + "'" + maMH + "', "
                + "'" + soLuong + "')";
        lopchung.executeNonQuery(sql);
    }

    public void dalXoa(String ma) throws SQLException {
        String sqlXoa = "delete from ChiTietHoaDon where MaHDCT = '" + ma + "'";
        lopchung.executeNonQuery(sqlXoa);
    }

    public void DalSua(String maHDCT, String maHD, String maMH, int soLuong) throws SQLException {
        String sql = "UPDATE ChiTietHoaDon SET "
                + "MaHD = '" + maHD + "', "
                + "MaMH = '" + maMH + "', "
                + "SoLuong = '" + soLuong + "' "
                + "WHERE MaHDCT = '" + maHDCT + "'";
        lopchung.executeNonQuery(sql);
    }

    public int DalDem() throws SQLException {
        String sqlDem = "select count (*) from ChiTietHoaDon";
        return (int) lopchung.executeScalar(sqlDem);
    }

    public TableModel DALcb_MatHang(String MaMatHang) throws SQLException {
        try {
            String sqlcbMatHang = "SELECT * FROM ChiTietHoaDon WHERE MaMh = '" + MaMatHang + "'";
            ResultSet rs = lopchung.loadDL(sqlcbMatHang); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }

    public String DALTimTenMatHang(String MaMatHang) throws SQLException {
        try {
            String sql = "SELECT TenMH FROM MatHang WHERE MaMH = '" + MaMatHang + "'";
            ResultSet rs = lopchung.loadDL(sql);
            if (rs.next()) {
                return rs.getString("TenMH");
            } else {
                return null; // Không tìm thấy mặt hàng
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public TableModel dalTim(String MaHDCT) {
        try {
            String sqlTim = "SELECT * FROM ChiTietHoaDon "
                    + "WHERE MaHDCT LIKE '%" + MaHDCT + "%' "
                    + "OR MaHD LIKE '%" + MaHDCT + "%' "
                    + "OR MaMH LIKE '%" + MaHDCT + "%'";
            ResultSet rs = lopchung.loadDL(sqlTim); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(DALChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int dalTimGiaTien(String maMh)
    {
        try {
            String sql = "SELECT DonGia FROM MatHang WHERE MaMH = '" + maMh + "'";
            ResultSet rs = lopchung.loadDL(sql); // Nếu executeNonQuery trả về ResultSet
            if(rs.next()){
                int giatien=rs.getInt("DonGia");
                return giatien;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DALChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public void dalUpdateSoLuong(String maMH,String soLuongThemBot)
    {
        try {
            String sql ="UPDATE MatHang SET SoLuong = SoLuong + " + soLuongThemBot + " WHERE MaMH = '" + maMH + "'";
            lopchung.executeNonQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DALChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public int dalTimSoLuong(String maMh)
    {
        try {
            String sql = "SELECT SoLuong FROM MatHang WHERE MaMH = '" + maMh + "'";
            ResultSet rs = lopchung.loadDL(sql); // Nếu executeNonQuery trả về ResultSet
            if(rs.next()){
                int soLuong=rs.getInt("SoLuong");
                return soLuong;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DALChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
