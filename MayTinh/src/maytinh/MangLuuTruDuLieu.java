/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maytinh;

import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class MangLuuTruDuLieu {
    ArrayList <MayTinh_DB> mang =new ArrayList<>();
    public void them(MayTinh_DB a)
    {
        mang.add(a);
    }
    public MayTinh_DB timkiem(MayTinh_DB a)
    {
        MayTinh_DB temp =null;
        for (MayTinh_DB it : mang)
            if(it.getDuLieuPhepTinh().equals(a))
                   temp =it;
        return temp;
    }
}
