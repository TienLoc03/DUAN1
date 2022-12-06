/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import java.util.ArrayList;
import repositories.ThongKeRepository;
import repositories.impl.ThongKeRepositoryImpl;
import services.ThongKeService;
import viewModels.ThongKeSPResponse;
import viewModels.ThongKeSPResponse2;

/**
 *
 * @author ASUS
 */
public class ThongKeServiceImpl implements ThongKeService {

    private ThongKeRepository TKRepo = new ThongKeRepositoryImpl();

    @Override
    public ArrayList<ThongKeSPResponse> getAll() {
        return TKRepo.getAll();
    }

    @Override
    public ArrayList<ThongKeSPResponse2> getAll2() {
        return TKRepo.getAll2();
    }

}
