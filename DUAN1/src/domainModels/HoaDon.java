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
public class HoaDon {

    private int MaHoaDon;
    private String MaNhanVien;
    private String TenKhachHang;
    private String NgayTao;
    private String NgayThanhToan;
    private BigDecimal TongTien;
    private String TrangThai;

    public HoaDon() {
    }

    public HoaDon(int MaHoaDon, String MaNhanVien, String TenKhachHang, String NgayTao, String NgayThanhToan, BigDecimal TongTien, String TrangThai) {
        this.MaHoaDon = MaHoaDon;
        this.MaNhanVien = MaNhanVien;
        this.TenKhachHang = TenKhachHang;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }


    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        this.TongTien = TongTien;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }


    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(String NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

}
