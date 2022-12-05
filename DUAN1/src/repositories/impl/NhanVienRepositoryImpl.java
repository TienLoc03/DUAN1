/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories.impl;

import domainModels.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import repositories.NhanVienRepository;
import utilies.DBConnection;
import viewModels.ChucVuResponse;
import viewModels.NhanVienResponse;

/**
 *
 * @author ASUS
 */
public class NhanVienRepositoryImpl implements NhanVienRepository {

    @Override
    public ArrayList<NhanVienResponse> getAll() {
        String query = "SELECT dbo.NhanVien.MaNhanVien, dbo.NhanVien.MaChucVu, dbo.NhanVien.TenNhanVien, dbo.ChucVu.TenChucVu, dbo.NhanVien.GioiTinh, dbo.NhanVien.NgaySinh, dbo.NhanVien.SDT, dbo.NhanVien.DiaChi, dbo.ChucVu.Luong, \n"
                + "                  dbo.NhanVien.imageNV\n"
                + "FROM     dbo.NhanVien INNER JOIN\n"
                + "                  dbo.ChucVu ON dbo.NhanVien.MaChucVu = dbo.ChucVu.MaChucVu";
        ArrayList<NhanVienResponse> listNhanVienResponses = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVuResponse Ten = new ChucVuResponse(rs.getString(4));
                ChucVuResponse Luong = new ChucVuResponse(rs.getBigDecimal(9));
                listNhanVienResponses.add(new NhanVienResponse(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(6), rs.getString(5), rs.getString(7), rs.getString(8), rs.getString(10), Ten, Luong));
            }
            return listNhanVienResponses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(NhanVien nhanVien) {
        String query = "INSERT INTO [dbo].[NhanVien]\n"
                + "           ([MaNhanVien]\n"
                + "           ,[MaChucVu]\n"
                + "           ,[TenNhanVien]\n"
                + "           ,[NgaySinh]\n"
                + "           ,[GioiTinh]\n"
                + "           ,[SDT]\n"
                + "           ,[DiaChi]\n"
                + "           ,[imageNV])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, nhanVien.getMaNhanVien());
            ps.setObject(2, nhanVien.getMaChucVu());
            ps.setObject(3, nhanVien.getTenNhanVien());
            ps.setObject(4, nhanVien.getNgaySinh());
            ps.setObject(5, nhanVien.getGioiTinh());
            ps.setObject(6, nhanVien.getSDT());
            ps.setObject(7, nhanVien.getDiaChi());
            ps.setObject(8, nhanVien.getImageNV());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(String MaNV, NhanVien nhanVien) {
        String query = "UPDATE [dbo].[NhanVien]\n"
                + "   SET \n"
                + "      [MaChucVu] = ?\n"
                + "      ,[TenNhanVien] = ?\n"
                + "      ,[NgaySinh] = ?\n"
                + "      ,[GioiTinh] =?\n"
                + "      ,[SDT] =?\n"
                + "      ,[DiaChi] =?\n"
                + "      ,[imageNV] = ?\n"
                + " WHERE MaNhanVien = ?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, nhanVien.getMaChucVu());
            ps.setObject(2, nhanVien.getTenNhanVien());
            ps.setObject(3, nhanVien.getNgaySinh());
            ps.setObject(4, nhanVien.getGioiTinh());
            ps.setObject(5, nhanVien.getSDT());
            ps.setObject(6, nhanVien.getDiaChi());
            ps.setObject(7, nhanVien.getImageNV());
            ps.setObject(8, MaNV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(String MaNV) {
        String query = "DELETE FROM [dbo].[NhanVien]\n"
                + "      WHERE MaNhanVien = ?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, MaNV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public ArrayList<NhanVienResponse> ShowNV(String MaNV) {
        String query = "select MaNhanVien,TenNhanVien,TenChucVu\n"
                + "				from NhanVien,ChucVu\n"
                + "				where NhanVien.MaChucVu = ChucVu.MaChucVu and ChucVu.MaChucVu=?";
        ArrayList<NhanVienResponse> listNhanVienResponses = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, MaNV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVuResponse chucVuResponse = new ChucVuResponse(rs.getString(3));
                listNhanVienResponses.add(new NhanVienResponse(rs.getString(1), rs.getString(2), chucVuResponse));
            }
            return listNhanVienResponses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
