/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import domainModels.ChucVu;
import java.util.ArrayList;
import viewModels.ChucVuResponse;

/**
 *
 * @author ASUS
 */
public interface ChucVuRepository {

    ArrayList<ChucVuResponse> getAll();

    boolean add(ChucVu chucVu);

    boolean update(String MaCV, ChucVu chucVu);

    boolean delete(String MaCV);
}
