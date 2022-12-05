/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModels;

/**
 *
 * @author ASUS
 */
public class TaiKhoanResponse {
    private String maNV;
    private String tk;
    private String mk;
    private String email;
    private String loaiTK;
    private NhanVienResponse nhanVienResponse;
    public TaiKhoanResponse() {
    }

    public TaiKhoanResponse(String maNV, String tk, String mk, String email, String loaiTK, NhanVienResponse nhanVienResponse) {
        this.maNV = maNV;
        this.tk = tk;
        this.mk = mk;
        this.email = email;
        this.loaiTK = loaiTK;
        this.nhanVienResponse = nhanVienResponse;
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

    public NhanVienResponse getNhanVienResponse() {
        return nhanVienResponse;
    }

    public void setNhanVienResponse(NhanVienResponse nhanVienResponse) {
        this.nhanVienResponse = nhanVienResponse;
    }
    
    public Object[] toDataRow(){
        return new Object[]{nhanVienResponse.getTenNhanVien(),tk,mk,email,loaiTK};
    }
}
