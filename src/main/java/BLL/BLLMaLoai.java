package BLL;

import DAL.MaLoaiDAL;
import java.util.ArrayList;

public class BLLMaLoai {
    MaLoaiDAL dal = new MaLoaiDAL();

    public ArrayList<String[]> getAllMaLoai() {
        return dal.getAllMaLoai();
    }

    public boolean themMaLoai(int maLoai, int maMH) {
        return dal.insertMaLoai(maLoai, maMH);
    }

    public boolean xoaMaLoai(int maLoai) {
        return dal.deleteMaLoai(maLoai);
    }
}
