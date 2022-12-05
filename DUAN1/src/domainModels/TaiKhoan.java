/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

/**
 *
 * @author ASUS
 */
public class TaiKhoan {
    private String maNV;
    private String tk;
    private String mk;
    private String email;
    private String loaiTK;

    public TaiKhoan() {
    }

    public TaiKhoan(String maNV, String tk, String mk, String email, String loaiTK) {
        this.maNV = maNV;
        this.tk = tk;
        this.mk = mk;
        this.email = email;
        this.loaiTK = loaiTK;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        this.loaiTK = loaiTK;
    }

    

}
