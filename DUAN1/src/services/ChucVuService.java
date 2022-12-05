/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainModels.ChucVu;
import java.util.ArrayList;
import viewModels.ChucVuResponse;

/**
 *
 * @author ASUS
 */
public interface ChucVuService {

    ArrayList<ChucVuResponse> getAll();

    String add(ChucVu chucVu);

    String update(String MaCV, ChucVu chucVu);

    String delete(String MaCV);
}
