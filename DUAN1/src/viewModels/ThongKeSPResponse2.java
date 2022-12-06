/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModels;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class ThongKeSPResponse2 {
    private Date Ngay;
    private int soLuong;

    public ThongKeSPResponse2() {
    }

    public ThongKeSPResponse2(Date Ngay, int soLuong) {
        this.Ngay = Ngay;
        this.soLuong = soLuong;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date Ngay) {
        this.Ngay = Ngay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
