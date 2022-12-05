/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainModels.NhanVien;
import java.util.ArrayList;
import repositories.NhanVienRepository;
import repositories.impl.NhanVienRepositoryImpl;
import services.NhanVienService;
import viewModels.NhanVienResponse;

/**
 *
 * @author ASUS
 */
public class NhanVienServiceImpl implements NhanVienService {

    NhanVienRepository NVRepo = new NhanVienRepositoryImpl();

    @Override
    public ArrayList<NhanVienResponse> getAll() {
        return NVRepo.getAll();
    }

    @Override
    public String add(NhanVien nhanVien) {
        if (NVRepo.add(nhanVien)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String update(String MaNV, NhanVien nhanVien) {
        if (NVRepo.update(MaNV, nhanVien)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String delete(String MaNV) {
        if (NVRepo.delete(MaNV)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public ArrayList<NhanVienResponse> ShowNV(String MaNV) {
        return NVRepo.ShowNV(MaNV);
    }

}
