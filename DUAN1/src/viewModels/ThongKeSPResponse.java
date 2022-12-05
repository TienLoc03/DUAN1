/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModels;

/**
 *
 * @author ASUS
 */
public class ThongKeSPResponse {

    private String tenSP;
    private int soLuongSP;

    public ThongKeSPResponse() {
    }

    public ThongKeSPResponse(String tenSP, int soLuongSP) {
        this.tenSP = tenSP;
        this.soLuongSP = soLuongSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

}
