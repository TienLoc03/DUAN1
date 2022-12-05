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
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String update(String MaMA, MonAn monAn) {
        if (MARepo.update(MaMA, monAn)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String delete(String MaMA) {
        if (MARepo.delete(MaMA)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public ArrayList<MonAnResponse> ShowMA(String MaLM) {
        return MARepo.ShowMA(MaLM);
    }

}
