/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import domainModels.DanhMuc;
import java.util.ArrayList;
import viewModels.DanhMucResponse;

/**
 *
 * @author ASUS
 */
public interface DanhMucRepository {

    ArrayList<DanhMucResponse> getAll();


    boolean add(DanhMuc danhMuc);

    boolean update(String MaLM, DanhMuc danhMuc);

    boolean delete(String MaLM);
}
