/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainModels.MonAn;
import java.util.ArrayList;
import repositories.MonAnRepository;
import repositories.impl.MonAnRepositoryImpl;
import services.MonAnService;
import viewModels.MonAnResponse;

/**
 *
 * @author ASUS
 */
public class MonAnServiceImpl implements MonAnService {

    MonAnRepository MARepo = new MonAnRepositoryImpl();

    @Override
    public ArrayList<MonAnResponse> getAll() {
        return MARepo.getAll();
    }

    @Override
    public String add(MonAn monAn) { 
        if (MARepo.add(monAn)) {
            return "Lưu thành công";
        } else {
            return "Lưu thất bại";
        }
    }

    @Override
    public String update(String MaMA, MonAn monAn) {
        if (MARepo.update(MaMA, monAn)) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String delete(String MaMA) {
        if (MARepo.delete(MaMA)) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public ArrayList<MonAnResponse> ShowMA(String MaLM) {
        return MARepo.ShowMA(MaLM);
    }

}
