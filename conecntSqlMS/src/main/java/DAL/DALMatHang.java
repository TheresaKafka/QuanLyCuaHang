/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import com.mycompany.conecntsqlms.LopChung;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ACER
 */
public class DALMatHang {

    LopChung lopchung;

    public DALMatHang() throws ClassNotFoundException, SQLException {
        lopchung = new LopChung();
    }

    public TableModel dalMatHang() throws SQLException {
        try {
            String sqlcbMaHoaDon = "SELECT * FROM MatHang";
            ResultSet rs = lopchung.loadDL(sqlcbMaHoaDon); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }

    public void DalThem(String maMH, String tenMH, int donGia, String maLoai, int soLuong, java.util.Date ngayHetHan) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(ngayHetHan);

        String sql = "INSERT INTO MatHang (MaMH, TenMH, DonGia, MaLoai, SoLuong, NgayHetHan) "
                + "VALUES ('" + maMH + "', N'" + tenMH + "', " + donGia + ", '" + maLoai + "', " + soLuong
                + ", CONVERT(datetime, '" + formattedDate + "', 103))";
        try {
            lopchung.executeNonQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DALMatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DalXoa(String Ma) {
        try {
            String sqlXoa = "delete from MatHang where MaMH = '" + Ma + "'";
            lopchung.executeNonQuery(sqlXoa);
        } catch (SQLException ex) {
            Logger.getLogger(DALHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DalSuaMatHang(String maMH, String tenMH, int donGia, String maLoai, int soLuong, java.util.Date ngayHetHan) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String ngayHetHanStr = sdf.format(ngayHetHan);

            String sqlSua = "UPDATE MatHang SET "
                    + "TenMH = N'" + tenMH + "', "
                    + "DonGia = " + donGia + ", "
                    + "MaLoai = '" + maLoai + "', "
                    + "SoLuong = " + soLuong + ", "
                    + "NgayHetHan = CONVERT(datetime, '" + ngayHetHanStr + "', 103) "
                    + "WHERE MaMH = '" + maMH + "'";

            lopchung.executeNonQuery(sqlSua);
        } catch (SQLException ex) {
            Logger.getLogger(DALMatHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int DalDem() throws SQLException {
        String sqlDem = "select count (*) from MatHang";
        return (int) lopchung.executeScalar(sqlDem);
    }

    public TableModel dalMaLoai() {
        try {
            String sqlMaLoai = "SELECT * FROM LoaiHang";
            ResultSet rs = lopchung.loadDL(sqlMaLoai); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }

    public TableModel dalCbMaLoai(String ma) {
        try {
            String sqlcbMaLoai = "SELECT * FROM MatHang where MaLoai = '" + ma + "'";
            ResultSet rs = lopchung.loadDL(sqlcbMaLoai); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }

    public TableModel dalTim(String MaHDCT) {
        try {
            String sqlTim = "SELECT * FROM MatHang "
                    + "WHERE MaMH LIKE '%" + MaHDCT + "%' "
                    + "OR TenMH LIKE N'%" + MaHDCT + "%'";

            ResultSet rs = lopchung.loadDL(sqlTim); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(DALChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
