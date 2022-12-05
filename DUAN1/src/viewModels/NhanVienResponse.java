/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModels;

import domainModels.ChucVu;

/**
 *
 * @author ASUS
 */
public class NhanVienResponse {

    private String MaNhanVien;
    private int MaChucVu;
    private String TenNhanVien;
    private String NgaySinh;
    private String GioiTinh;
    private String SDT;
    private String DiaChi;
    private String imageNV;
    private ChucVuResponse TenChucVu;
    private ChucVuResponse Luong;

    public NhanVienResponse() {
    }

    public NhanVienResponse(String MaNhanVien, int MaChucVu, String TenNhanVien, String NgaySinh, String GioiTinh, String SDT, String DiaChi, String imageNV, ChucVuResponse TenChucVu, ChucVuResponse Luong) {
        this.MaNhanVien = MaNhanVien;
        this.MaChucVu = MaChucVu;
        this.TenNhanVien = TenNhanVien;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.imageNV = imageNV;
        this.TenChucVu = TenChucVu;
        this.Luong = Luong;
    }


    public NhanVienResponse(String MaNhanVien, String TenNhanVien, ChucVuResponse TenChucVu) {
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.TenChucVu = TenChucVu;
    }

    public NhanVienResponse(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public int getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(int MaChucVu) {
        this.MaChucVu = MaChucVu;
    }

    

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getImageNV() {
        return imageNV;
    }

    public void setImageNV(String imageNV) {
        this.imageNV = imageNV;
    }

    public ChucVuResponse getTenChucVu() {
        return TenChucVu;
    }

    public void setTenChucVu(ChucVuResponse TenChucVu) {
        this.TenChucVu = TenChucVu;
    }

    public ChucVuResponse getLuong() {
        return Luong;
    }

    public void setLuong(ChucVuResponse Luong) {
        this.Luong = Luong;
    }

    public Object[] toDataRow() {
        return new Object[]{TenNhanVien, TenChucVu.getTenChucVu(), GioiTinh, NgaySinh, SDT, DiaChi, Luong.getLuong()};
    }

    public Object[] toDataRow3() {
        return new Object[]{MaNhanVien,TenNhanVien,TenChucVu.getTenChucVu()};
    }
}
