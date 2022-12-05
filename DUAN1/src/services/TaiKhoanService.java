/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainModels.TaiKhoan;
import java.util.ArrayList;
import viewModels.TaiKhoanResponse;

/**
 *
 * @author ASUS
 */
public interface TaiKhoanService {

    ArrayList<TaiKhoanResponse> getAll();

    String add(TaiKhoan taiKhoan);

    String update(String MaNV, TaiKhoan taiKhoan);

    String delete(String MaNV);
}
