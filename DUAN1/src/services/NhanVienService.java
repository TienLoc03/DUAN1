/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainModels.NhanVien;
import java.util.ArrayList;
import viewModels.NhanVienResponse;

/**
 *
 * @author ASUS
 */
public interface NhanVienService {

    ArrayList<NhanVienResponse> getAll();

    ArrayList<NhanVienResponse> ShowNV(String MaNV);

    String add(NhanVien nhanVien);

    String update(String MaNV, NhanVien nhanVien);

    String delete(String MaNV);
}
