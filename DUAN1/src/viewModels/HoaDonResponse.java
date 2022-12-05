/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModels;

import java.math.BigDecimal;

/**
 *
 * @author ASUS
 */
public class HoaDonResponse {
    private int MaHoaDon;
    private String MaNhanVien;
    private String TenKhachHang;
    private String NgayTao;
    private String NgayThanhToan;
    private BigDecimal TongTien;
    private String TrangThai;
    private NhanVienResponse TenNV;

    public HoaDonResponse() {
    }

    public HoaDonResponse(int MaHoaDon, String MaNhanVien, String TenKhachHang, String NgayTao, String NgayThanhToan, BigDecimal TongTien, String TrangThai, NhanVienResponse TenNV) {
        this.MaHoaDon = MaHoaDon;
        this.MaNhanVien = MaNhanVien;
        this.TenKhachHang = TenKhachHang;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
        this.TenNV = TenNV;
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

    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        this.TongTien = TongTien;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

   

    public NhanVienResponse getTenNV() {
        return TenNV;
    }

    public void setTenNV(NhanVienResponse TenNV) {
        this.TenNV = TenNV;
    }
    
   public Object[] toDataRow(){
       return new Object[]{MaHoaDon,TenNV,TenKhachHang,NgayTao,NgayThanhToan,TongTien,TrangThai};
   }
}
