/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainModels.KhuyenMai;
import java.util.ArrayList;
import viewModels.KhuyenMaiResponse;

/**
 *
 * @author ASUS
 */
public interface KhuyenMaiService {

    ArrayList<KhuyenMaiResponse> getAll();

    String add(KhuyenMai khuyenMai);

    String update(String MaKM, KhuyenMai khuyenMai);

    String delete(String MaKM);
}
