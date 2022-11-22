/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import domainModels.TaiKhoan;
import java.util.ArrayList;
import repositories.TaiKhoanRepository;
import repositories.impl.TaiKhoanRepositoryImpl;
import services.TaiKhoanService;

/**
 *
 * @author ASUS
 */
public class TaiKhoanServiceImpl implements TaiKhoanService{
    TaiKhoanRepository TKRepo = new TaiKhoanRepositoryImpl();

    @Override
    public ArrayList<TaiKhoan> getAll() {
        return TKRepo.getAll();
    }
    
}
