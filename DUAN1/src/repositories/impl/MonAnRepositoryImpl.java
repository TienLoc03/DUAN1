/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories.impl;

import domainModels.MonAn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import repositories.MonAnRepository;
import utilies.DBConnection;
import viewModels.DanhMucResponse;
import viewModels.MonAnResponse;

/**
 *
 * @author ASUS
 */
public class MonAnRepositoryImpl implements MonAnRepository {

    @Override
    public ArrayList<MonAnResponse> getAll() {
        String query = ("SELECT dbo.MonAn.MaMonAn, dbo.DanhMuc.MaLoaiMon, dbo.DanhMuc.TenLoaiMon, dbo.MonAn.TenMonAn, dbo.MonAn.DonVi, dbo.MonAn.GiaTien, dbo.MonAn.TrangThai, dbo.MonAn.imageMonAn\n"
                + "FROM     dbo.MonAn INNER JOIN\n"
                + "                  dbo.DanhMuc ON dbo.MonAn.MaLoaiMon = dbo.DanhMuc.MaLoaiMon");
        ArrayList<MonAnResponse> listMonAnResponses = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DanhMucResponse danhMucResponse = new DanhMucResponse(rs.getString(3));
                listMonAnResponses.add(new MonAnResponse(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getString(8), danhMucResponse));
            }
            return listMonAnResponses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(MonAn monAn) {
        String query = ("INSERT INTO [dbo].[MonAn]\n"
                + "           ([MaLoaiMon]\n"
                + "           ,[TenMonAn]\n"
                + "           ,[DonVi]\n"
                + "           ,[GiaTien]\n"
                + "           ,[TrangThai]\n"
                + "           ,[imageMonAn])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)");
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, monAn.getMaLoaiMon());
            ps.setObject(2, monAn.getTenMonAn());
            ps.setObject(3, monAn.getDonVi());
            ps.setObject(4, monAn.getGiaTien());
            ps.setObject(5, monAn.getTrangThai());
            ps.setObject(6, monAn.getImageMonAn());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(String MaMA, MonAn monAn) {
        String query = "UPDATE [dbo].[MonAn]\n"
                + "   SET [MaLoaiMon] = ?\n"
                + "      ,[TenMonAn] = ?\n"
                + "      ,[DonVi] = ?\n"
                + "      ,[GiaTien] = ?\n"
                + "      ,[TrangThai] = ?\n"
                + "      ,[imageMonAn] = ?\n"
                + " WHERE MaMonAn = ?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, monAn.getMaLoaiMon());
            ps.setObject(2, monAn.getTenMonAn());
            ps.setObject(3, monAn.getDonVi());
            ps.setObject(4, monAn.getGiaTien());
            ps.setObject(5, monAn.getTrangThai());
            ps.setObject(6, monAn.getImageMonAn());
            ps.setObject(7, MaMA);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(String MaMA) {
        String query = "DELETE FROM [dbo].[MonAn]\n"
                + "      WHERE MaMonAn = ?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, MaMA);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public ArrayList<MonAnResponse> ShowMA(String MaLM) {
        String query = "select MaMonAn,MonAn.TenMonAn,DanhMuc.TenLoaiMon\n"
                + "from MonAn,DanhMuc\n"
                + "where MonAn.MaLoaiMon = DanhMuc.MaLoaiMon and DanhMuc.MaLoaiMon = ?";
        ArrayList<MonAnResponse> listMonAnResponses = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, MaLM);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DanhMucResponse danhMucResponse = new DanhMucResponse(rs.getString(3));
                listMonAnResponses.add(new MonAnResponse(rs.getInt(1), rs.getString(2), danhMucResponse));
            }
            return listMonAnResponses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
