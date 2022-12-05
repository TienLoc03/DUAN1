/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import domainModels.NhanVien;
import java.util.ArrayList;
import viewModels.NhanVienResponse;

/**
 *
 * @author ASUS
 */
public interface NhanVienRepository {

    ArrayList<NhanVienResponse> getAll();

    ArrayList<NhanVienResponse> ShowNV(String MaNV);

    boolean add(NhanVien nhanVien);

    boolean update(String MaNV, NhanVien nhanVien);

    boolean delete(String MaNV);
}
