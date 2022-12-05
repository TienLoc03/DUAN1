/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories.impl;

import domainModels.DanhMuc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import repositories.DanhMucRepository;
import utilies.DBConnection;
import viewModels.DanhMucResponse;

/**
 *
 * @author ASUS
 */
public class DanhMucRepositoryImpl implements DanhMucRepository {

    @Override
    public ArrayList<DanhMucResponse> getAll() {
        String query = ("SELECT * FROM DanhMuc ");
        ArrayList<DanhMucResponse> listDanhMucResponses = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listDanhMucResponses.add(new DanhMucResponse(rs.getInt(1), rs.getString(2)));
            }
            return listDanhMucResponses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(DanhMuc danhMuc) {
        String query = "INSERT INTO [dbo].[DanhMuc]\n"       
                + "           ([TenLoaiMon])\n"
                + "     VALUES\n"
                + "           (?)";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, danhMuc.getTenLoaiMon());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(String MaLM, DanhMuc danhMuc) {
        String query = "UPDATE [dbo].[DanhMuc]\n"
                + "   SET \n"
                + "      [TenLoaiMon] = ?\n"
                + " WHERE MaLoaiMon = ?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, danhMuc.getTenLoaiMon());
            ps.setObject(2, MaLM);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(String MaLM) {
        String query = "DELETE FROM [dbo].[DanhMuc]\n"
                + "      WHERE MaLoaiMon=?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, MaLM);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }



}
