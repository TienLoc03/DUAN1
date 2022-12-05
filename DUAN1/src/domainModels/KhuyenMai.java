/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.math.BigDecimal;

/**
 *
 * @author ASUS
 */
public class KhuyenMai {
    private int MaKhuyenMai;
    private String TenSuKien;
    private BigDecimal TienGiam;

    public KhuyenMai() {
    }

    public KhuyenMai(int MaKhuyenMai, String TenSuKien, BigDecimal TienGiam) {
        this.MaKhuyenMai = MaKhuyenMai;
        this.TenSuKien = TenSuKien;
        this.TienGiam = TienGiam;
    }

    public int getMaKhuyenMai() {
        return MaKhuyenMai;
    }

    public void setMaKhuyenMai(int MaKhuyenMai) {
        this.MaKhuyenMai = MaKhuyenMai;
    }

    public String getTenSuKien() {
        return TenSuKien;
    }

    public void setTenSuKien(String TenSuKien) {
        this.TenSuKien = TenSuKien;
    }

    public BigDecimal getTienGiam() {
        return TienGiam;
    }

    public void setTienGiam(BigDecimal TienGiam) {
        this.TienGiam = TienGiam;
    }
    
    
}
