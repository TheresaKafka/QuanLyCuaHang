
import maytinh.XuLyDuLieu;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author ACER
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        XuLyDuLieu test= new XuLyDuLieu("10;+;20;*;5;");
        test.xulytaoketqua();
        System.out.println(test.HienThiDuLieu());
    }
    
}
