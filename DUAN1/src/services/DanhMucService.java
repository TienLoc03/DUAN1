/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainModels.DanhMuc;
import java.util.ArrayList;
import viewModels.DanhMucResponse;

/**
 *
 * @author ASUS
 */
public interface DanhMucService {

    ArrayList<DanhMucResponse> getAll();

    String add(DanhMuc danhMuc);

    String update(String MaLM, DanhMuc danhMuc);

    String delete(String MaLM);
}
