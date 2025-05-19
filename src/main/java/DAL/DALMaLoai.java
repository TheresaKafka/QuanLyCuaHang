package DAL;

import com.mycompany.conecntsqlms.LopChung;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
public class DALMaLoai {
    LopChung lc;

    public DALMaLoai() throws SQLException, ClassNotFoundException {
        lc = new LopChung();
    }

    public void themLoai(String ma, String ten) throws SQLException {
        String sql = "INSERT INTO MaLoai VALUES('" + ma + "', N'" + ten + "')";
        lc.executeNonQuery(sql);
    }

    public void xoaLoai(String ma) throws SQLException {
        String sql = "DELETE FROM MaLoai WHERE MaLoai = '" + ma + "'";
        lc.executeNonQuery(sql);
    }

    public void suaLoai(String ma, String ten) throws SQLException {
        String sql = "UPDATE MaLoai SET TenLoai = N'" + ten + "' WHERE MaLoai = '" + ma + "'";
        lc.executeNonQuery(sql);
    }
   }