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
public class DALTaiKhoan {

    LopChung lopchung;

    public DALTaiKhoan() throws SQLException, ClassNotFoundException {
        lopchung = new LopChung();
    }

    public void DalThem(String matk, String tentk, String mK, String tenDN) throws SQLException {
        String sqlthem = "INSERT INTO TaiKhoan (MaTK, TenTK, MatKhau, TenDN) "
                + "VALUES('" + matk + "', N'" + tentk + "', '" + mK + "', N'" + tenDN + "')";
        lopchung.executeNonQuery(sqlthem);
    }

    public int dalDangNhap(String tenTK, String matKhau) throws SQLException {
        String sqlDn = "SELECT COUNT(*) FROM TaiKhoan WHERE TenTK = '" + tenTK + "' AND MatKhau = '" + matKhau + "'";
        return lopchung.executeScalar(sqlDn);
    }

    public void dalSua(String maTK, String tenTK, String matKhau, String tenDN) throws SQLException {
        String sqlSua = "UPDATE TaiKhoan SET "
                + "TenTK = N'" + tenTK + "', "
                + "MatKhau = N'" + matKhau + "', "
                + "TenDN = N'" + tenDN + "' "
                + "WHERE MaTK = N'" + maTK + "'";
        lopchung.executeNonQuery(sqlSua);
    }

    public void dalXoa(String tenDN) throws SQLException {
        String sqlXoa = "DELETE FROM TaiKhoan WHERE MaTK = '" + tenDN + "'";
        lopchung.executeNonQuery(sqlXoa);
    }

    public DefaultTableModel dalTaiKhoan() {
        try {
            String sqlMaLoai = "SELECT *FROM TaiKhoan;";
            ResultSet rs = lopchung.loadDL(sqlMaLoai); // Nếu executeNonQuery trả về ResultSet
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException e) {
            return null;
        }
    }

    public TableModel dalTim(String tuKhoa) {
        try {
            String sqlTim = "SELECT * FROM TaiKhoan "
                    + "WHERE MaTK LIKE N'%" + tuKhoa + "%' "
                    + "OR TenTK LIKE N'%" + tuKhoa + "%'";
            ResultSet rs = lopchung.loadDL(sqlTim);
            DefaultTableModel model = ResultSetConverter.buildTableModel(rs);
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(DALTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
