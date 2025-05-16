/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import com.mycompany.conecntsqlms.LopChung;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class DALTaiKhoan {

    LopChung lopchung;

    public DALTaiKhoan() throws SQLException, ClassNotFoundException {
        lopchung = new LopChung();
    }

    public void DalThem(String matk,String tentk,String mK) throws SQLException {
        String sqlthem = "INSERT INTO TaiKhoan VALUES('" + matk + "', N'" + tentk + "', '" + mK + "')";
        lopchung.executeNonQuery(sqlthem);
    }
    public int dalDangNhap(String tenTK, String matKhau) throws SQLException {
        String sqlDn = "SELECT COUNT(*) FROM TaiKhoan WHERE TenTK = '" + tenTK + "' AND MatKhau = '" + matKhau + "'";
        return lopchung.executeScalar(sqlDn);
    }
    public void dalSua(String maTK, String tenTK, String matKhau) throws SQLException {
        String sqlSua = "UPDATE TaiKhoan SET " +
            "TenTK = N'" + tenTK + "', " +
            "MatKhau = N'" + matKhau + "' " +
            "WHERE MaTK = N'" + maTK + "'";
        lopchung.executeNonQuery(sqlSua);
    }
     public void dalXoa(String tenDN) throws SQLException {
        String sqlXoa = "DELETE FROM TaiKhoan WHERE TenDangNhap = '" + tenDN + "'";
        lopchung.executeNonQuery(sqlXoa);
    }
}
