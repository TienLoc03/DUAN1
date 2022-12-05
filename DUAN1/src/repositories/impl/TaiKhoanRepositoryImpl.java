/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories.impl;

import domainModels.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import repositories.TaiKhoanRepository;
import utilies.DBConnection;
import viewModels.NhanVienResponse;
import viewModels.TaiKhoanResponse;

/**
 *
 * @author ASUS
 */
public class TaiKhoanRepositoryImpl implements TaiKhoanRepository {

    @Override
    public ArrayList<TaiKhoanResponse> getAll() {
        String query = ("SELECT dbo.NhanVien.TenNhanVien, dbo.TaiKhoan.MaNhanVien, dbo.TaiKhoan.TK, dbo.TaiKhoan.MK, dbo.TaiKhoan.Email, dbo.TaiKhoan.LoaiTK\n"
                + "FROM     dbo.TaiKhoan INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.TaiKhoan.MaNhanVien = dbo.NhanVien.MaNhanVien");
        ArrayList<TaiKhoanResponse> listTaiKhoanResponses = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienResponse nhanVienResponse = new NhanVienResponse(rs.getString(1));
                listTaiKhoanResponses.add(new TaiKhoanResponse(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), nhanVienResponse));
            }
            return listTaiKhoanResponses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(TaiKhoan taiKhoan) {
        String query = "INSERT INTO [dbo].[TaiKhoan]\n"
                + "           ([MaNhanVien]\n"
                + "           ,[TK]\n"
                + "           ,[MK]\n"
                + "           ,[Email]\n"
                + "           ,[LoaiTK])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, taiKhoan.getMaNV());
            ps.setObject(2, taiKhoan.getTk());
            ps.setObject(3, taiKhoan.getMk());
            ps.setObject(4, taiKhoan.getEmail());
            ps.setObject(5, taiKhoan.getLoaiTK());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(String MaNV, TaiKhoan taiKhoan) {
        String query = "UPDATE [dbo].[TaiKhoan]    \n"
                + "   SET [TK] = ?\n"
                + "      ,[MK] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[LoaiTK] = ?\n"
                + " WHERE MaNhanVien = ?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, taiKhoan.getTk());
            ps.setObject(2, taiKhoan.getMk());
            ps.setObject(3, taiKhoan.getEmail());
            ps.setObject(4, taiKhoan.getLoaiTK());
            ps.setObject(5, MaNV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(String MaNV) {
        String query = "DELETE FROM [dbo].[TaiKhoan]\n"
                + "      WHERE MaNhanVien=?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, MaNV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

}
