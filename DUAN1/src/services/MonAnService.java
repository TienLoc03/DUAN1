/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainModels.MonAn;
import java.util.ArrayList;
import viewModels.MonAnResponse;

/**
 *
 * @author ASUS
 */
public interface MonAnService {

    ArrayList<MonAnResponse> getAll();

    ArrayList<MonAnResponse> ShowMA(String MaLM);

    String add(MonAn monAn);

    String update(String MaMA, MonAn monAn);

    String delete(String MaMA);
}
