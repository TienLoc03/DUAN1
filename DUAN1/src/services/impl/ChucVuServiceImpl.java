/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainModels.ChucVu;
import java.util.ArrayList;
import repositories.ChucVuRepository;
import repositories.impl.ChucVuRepositoryImpl;
import services.ChucVuService;
import viewModels.ChucVuResponse;

/**
 *
 * @author ASUS
 */
public class ChucVuServiceImpl implements ChucVuService {

    ChucVuRepository CVRepo = new ChucVuRepositoryImpl();

    @Override
    public ArrayList<ChucVuResponse> getAll() {
        return CVRepo.getAll();
    }

    @Override
    public String add(ChucVu chucVu) {
        if (CVRepo.add(chucVu)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String update(String MaCV, ChucVu chucVu) {
        if (CVRepo.update(MaCV, chucVu)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String delete(String MaCV) {
        if (CVRepo.delete(MaCV)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

}
