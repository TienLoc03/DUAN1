/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainModels.TaiKhoan;
import java.util.ArrayList;
import viewModels.TaiKhoanResponse;

/**
 *
 * @author ASUS
 */
public interface TaiKhoanRepository {
    ArrayList<TaiKhoanResponse> getAll();
    
    boolean add(TaiKhoan taiKhoan);

    boolean update(String MaNV, TaiKhoan taiKhoan);

    boolean delete(String MaNV);
}
