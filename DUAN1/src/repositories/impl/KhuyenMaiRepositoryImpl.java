/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories.impl;

import domainModels.KhuyenMai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import repositories.KhuyenMaiRepository;
import utilies.DBConnection;
import viewModels.KhuyenMaiResponse;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiRepositoryImpl implements KhuyenMaiRepository {

    @Override
    public ArrayList<KhuyenMaiResponse> getAll() {
        String query = ("SELECT [MaKhuyenMai]\n"
                + "      ,[TenSuKien]\n"
                + "      ,[TienGiam]\n"
                + "  FROM [dbo].[KhuyenMai]");
        ArrayList<KhuyenMaiResponse> listKhuyenMaiResponses = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listKhuyenMaiResponses.add(new KhuyenMaiResponse(rs.getInt(1), rs.getString(2), rs.getBigDecimal(3)));
            }
            return listKhuyenMaiResponses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(KhuyenMai khuyenMai) {
        String query = ("INSERT INTO [dbo].[KhuyenMai]\n"
                + "           ([TenSuKien]\n"
                + "           ,[TienGiam])\n"
                + "     VALUES\n"
                + "           (?,?)");
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, khuyenMai.getTenSuKien());
            ps.setObject(2, khuyenMai.getMaKhuyenMai());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(String MaKM, KhuyenMai khuyenMai) {
        String query = "UPDATE [dbo].[KhuyenMai]\n"
                + "   SET [TenSuKien] = ?\n"
                + "      ,[TienGiam] = ?\n"
                + " WHERE MaKhuyenMai = ?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, khuyenMai.getTenSuKien());
            ps.setObject(2, khuyenMai.getMaKhuyenMai());
            ps.setObject(3, MaKM);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(String MaKM) {
        String query = "DELETE FROM [dbo].[KhuyenMai]\n"
                + "      WHERE MaKhuyenMai = ?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, MaKM);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

}
