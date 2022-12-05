/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainModels.KhuyenMai;
import java.util.ArrayList;
import repositories.KhuyenMaiRepository;
import repositories.impl.KhuyenMaiRepositoryImpl;
import services.KhuyenMaiService;
import viewModels.KhuyenMaiResponse;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiServiceImpl implements KhuyenMaiService {

    KhuyenMaiRepository KMRepo = new KhuyenMaiRepositoryImpl();

    @Override
    public ArrayList<KhuyenMaiResponse> getAll() {
        return KMRepo.getAll();
    }

    @Override
    public String add(KhuyenMai khuyenMai) {
        if (KMRepo.add(khuyenMai)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String update(String MaKM, KhuyenMai khuyenMai) {
        if (KMRepo.update(MaKM, khuyenMai)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

    @Override
    public String delete(String MaKM) {
        if (KMRepo.delete(MaKM)) {
            return "Thành công";
        } else {
            return "Thất bại";
        }
    }

}
