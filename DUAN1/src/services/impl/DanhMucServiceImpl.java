/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainModels.DanhMuc;
import java.util.ArrayList;
import repositories.DanhMucRepository;
import repositories.impl.DanhMucRepositoryImpl;
import services.DanhMucService;
import viewModels.DanhMucResponse;

/**
 *
 * @author ASUS
 */
public class DanhMucServiceImpl implements DanhMucService {

    DanhMucRepository DMRepo = new DanhMucRepositoryImpl();

    @Override
    public ArrayList<DanhMucResponse> getAll() {
        return DMRepo.getAll();
    }

    @Override
    public String add(DanhMuc danhMuc) {
        if (DMRepo.add(danhMuc)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String update(String MaLM, DanhMuc danhMuc) {
        if (DMRepo.update(MaLM, danhMuc)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String delete(String MaLM) {
        if (DMRepo.delete(MaLM)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

}
