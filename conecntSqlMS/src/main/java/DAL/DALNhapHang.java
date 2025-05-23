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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ACER
 */
public class DALNhapHang {

    LopChung lopchung;

    public DALNhapHang() throws SQLException, ClassNotFoundException {
        lopchung = new LopChung();
    }

    public TableModel DALcb_MatHang(String MaMatHang) throws SQLException {
        try {
            String sqlcbMatHang = "SELECT * FROM NhapHang WHERE MaMh = '" + MaMatHang + "'";
            ResultSet rs = lopchung.loadDL(sqlcbMatHang); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }

    public TableModel dalMatHang() {
        try {
            String sqlcbMatHang = "SELECT * FROM MatHang";
            ResultSet rs = lopchung.loadDL(sqlcbMatHang); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }

    public TableModel dalCbMaMH(String ma) {
        try {
            String sqlcbMaLoai = "SELECT * FROM NhapHang where MaMH = '" + ma + "'";
            ResultSet rs = lopchung.loadDL(sqlcbMaLoai); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
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
    public TableModel dalTenTk() {
        try {
            String sqlMaLoai = "SELECT TenTK, TenDN FROM TaiKhoan;";
            ResultSet rs = lopchung.loadDL(sqlMaLoai); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }
    public TableModel dalCbTenTk(String ma) {
        try {
            String sqlcbMaLoai = "SELECT * FROM NhapHang where TenTk = '" + ma + "'";
            ResultSet rs = lopchung.loadDL(sqlcbMaLoai); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }
    public TableModel dalNhapHang() throws SQLException {
        try {
            String sqlcbMaHoaDon = "SELECT * FROM NhapHang";
            ResultSet rs = lopchung.loadDL(sqlcbMaHoaDon); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    } 
    public void DalThem(String maNH, java.util.Date ngayNhap, String maMH, int soLuong, String tenTk) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = sdf.format(ngayNhap);

    String sql = "INSERT INTO NhapHang (MaNH, NgayNhap, MaMH, SoLuong, TenTk) "
            + "VALUES ('" + maNH + "', CONVERT(datetime, '" + formattedDate + "', 103), "
            + "'" + maMH + "', " + soLuong + ", N'" + tenTk + "')";

    try {
        lopchung.executeNonQuery(sql);
    } catch (SQLException ex) {
        Logger.getLogger(DALNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, 
        "Không thể đọc dữ liệu.\nChi tiết: " + ex.getMessage(),
        "Cảnh báo", 
        JOptionPane.WARNING_MESSAGE);
    }
}
    public void DalXoa(String Ma) {
        try {
            String sqlXoa = "delete from NhapHang where MaNH = '" + Ma + "'";
            lopchung.executeNonQuery(sqlXoa);
        } catch (SQLException ex) {
            Logger.getLogger(DALHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, 
        "Không thể đọc dữ liệu.\nChi tiết: " + ex.getMessage(),
        "Cảnh báo", 
        JOptionPane.WARNING_MESSAGE);
        }
    }
    public void DalSua(String maNH, java.util.Date ngayNhap, String maMH, int soLuong, String tenTk) {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayNhapStr = sdf.format(ngayNhap);

        String sqlSua = "UPDATE NhapHang SET "
                + "NgayNhap = CONVERT(datetime, '" + ngayNhapStr + "', 103), "
                + "MaMH = '" + maMH + "', "
                + "SoLuong = " + soLuong + ", "
                + "TenTk = N'" + tenTk + "' "
                + "WHERE MaNH = '" + maNH + "'";

        lopchung.executeNonQuery(sqlSua);
    } catch (SQLException ex) {
        Logger.getLogger(DALNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, 
        "Không thể đọc dữ liệu.\nChi tiết: " + ex.getMessage(),
        "Cảnh báo", 
        JOptionPane.WARNING_MESSAGE);
    }
}
    public TableModel dalTim(String MaHDCT) {
        try {
            String sqlTim = "SELECT * FROM NhapHang "
                    + "WHERE MaNH LIKE '%" + MaHDCT + "%' ";
            ResultSet rs = lopchung.loadDL(sqlTim); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(DALChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void dalUpdateSoLuong(String maMH,String soLuongThemBot)
    {
        try {
            String sql ="UPDATE MatHang SET SoLuong = SoLuong + " + soLuongThemBot + " WHERE MaMH = '" + maMH + "'";
            lopchung.executeNonQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DALChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, 
        "Không thể đọc dữ liệu.\nChi tiết: " + ex.getMessage(),
        "Cảnh báo", 
        JOptionPane.WARNING_MESSAGE);
        }
        
    }
}
