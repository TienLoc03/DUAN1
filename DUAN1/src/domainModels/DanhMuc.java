/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

/**
 *
 * @author ASUS
 */
public class DanhMuc {
    private int MaLoaiMon;
    private String TenLoaiMon;

    public DanhMuc() {
    }

    public DanhMuc(int MaLoaiMon, String TenLoaiMon) {
        this.MaLoaiMon = MaLoaiMon;
        this.TenLoaiMon = TenLoaiMon;
    }

    public int getMaLoaiMon() {
        return MaLoaiMon;
    }

    public void setMaLoaiMon(int MaLoaiMon) {
        this.MaLoaiMon = MaLoaiMon;
    }


    public String getTenLoaiMon() {
        return TenLoaiMon;
    }

    public void setTenLoaiMon(String TenLoaiMon) {
        this.TenLoaiMon = TenLoaiMon;
    }
    
    
}
