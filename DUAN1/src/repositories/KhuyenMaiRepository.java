/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import domainModels.KhuyenMai;
import java.util.ArrayList;
import viewModels.KhuyenMaiResponse;

/**
 *
 * @author ASUS
 */
public interface KhuyenMaiRepository {

    ArrayList<KhuyenMaiResponse> getAll();

    boolean add(KhuyenMai khuyenMai);

    boolean update(String MaKM, KhuyenMai khuyenMai);

    boolean delete(String MaKM);
}
