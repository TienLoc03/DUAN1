/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainModels.TaiKhoan;
import java.util.ArrayList;
import repositories.TaiKhoanRepository;
import repositories.impl.TaiKhoanRepositoryImpl;
import services.TaiKhoanService;
import viewModels.TaiKhoanResponse;

/**
 *
 * @author ASUS
 */
public class TaiKhoanServiceImpl implements TaiKhoanService {

    TaiKhoanRepository TKRepo = new TaiKhoanRepositoryImpl();

    @Override
    public ArrayList<TaiKhoanResponse> getAll() {
        return TKRepo.getAll();
    }

    @Override
    public String add(TaiKhoan taiKhoan) {
        if (TKRepo.add(taiKhoan)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String update(String MaNV, TaiKhoan taiKhoan) {
        if (TKRepo.update(MaNV, taiKhoan)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String delete(String MaNV) {
        if (TKRepo.delete(MaNV)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

}
