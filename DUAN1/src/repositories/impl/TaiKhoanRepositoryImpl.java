/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories.impl;

import domainModels.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import repositories.TaiKhoanRepository;
import utilies.DBConnection;

/**
 *
 * @author ASUS
 */
public class TaiKhoanRepositoryImpl implements TaiKhoanRepository{

    @Override
    public ArrayList<TaiKhoan> getAll() {
        String query = ("SELECT * FROM TaiKhoan ");
        ArrayList<TaiKhoan> listTaiKhoans = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTaiKhoans.add(new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return listTaiKhoans;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
