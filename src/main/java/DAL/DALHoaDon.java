/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import com.mycompany.conecntsqlms.LopChung;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ACER
 */
public class DALHoaDon {

    LopChung lopchung;

    public DALHoaDon() throws ClassNotFoundException, SQLException {
        lopchung = new LopChung();
    }

    public TableModel dalHoaDon() throws SQLException {
        try {
            String sqlcbMaHoaDon = "SELECT * FROM HoaDon";
            ResultSet rs = lopchung.loadDL(sqlcbMaHoaDon); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }

    public void DalThem(String maHD, java.util.Date ngayLap) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(ngayLap);
        String sql = "INSERT INTO HoaDon VALUES ('" + maHD + "', CONVERT(datetime, '" + formattedDate + "', 103))";
        try {
            lopchung.executeNonQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DALHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DalXoa(String Ma) {
        try {
            String sqlXoa = "delete from ChiTietHoaDon where MaHDCT = '" + Ma + "'";
            lopchung.executeNonQuery(sqlXoa);
        } catch (SQLException ex) {
            Logger.getLogger(DALHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DalSua(String maHD, java.util.Date ngayLap) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String ngayLapStr = sdf.format(ngayLap);
            String sqlSua = "UPDATE HoaDon SET "
                    + "NgayLap = CONVERT(datetime, '" + ngayLapStr + "', 103) "
                    + "WHERE MaHD = '" + maHD + "'";
            lopchung.executeNonQuery(sqlSua);
        } catch (SQLException ex) {
            Logger.getLogger(DALHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int DalDem() throws SQLException {
        String sqlDem = "select count (*) from HoaDon";
        return (int) lopchung.executeScalar(sqlDem);
    }

    public String DALTimNgayLapAsString(String MaHoaDon) throws SQLException {
    try {
        String sql = "SELECT NgayLap FROM HoaDon WHERE MaHD = '" + MaHoaDon + "'";
        ResultSet rs = lopchung.loadDL(sql);
        if (rs.next()) {
            Date ngayLap = rs.getDate("NgayLap");

            // Format lại thành chuỗi, ví dụ: "dd/MM/yyyy HH:mm:ss"
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(ngayLap);
        } else {
            return null; // Không có dữ liệu
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

}
