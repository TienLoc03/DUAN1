/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories.impl;

import domainModels.ChucVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import repositories.ChucVuRepository;
import utilies.DBConnection;
import viewModels.ChucVuResponse;

/**
 *
 * @author ASUS
 */
public class ChucVuRepositoryImpl implements ChucVuRepository {

    @Override
    public ArrayList<ChucVuResponse> getAll() {
        String query = ("SELECT * FROM ChucVu ");
        ArrayList<ChucVuResponse> listChucVuResponses = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listChucVuResponses.add(new ChucVuResponse(rs.getInt(1), rs.getString(2), rs.getBigDecimal(3)));
            }
            return listChucVuResponses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(ChucVu chucVu) {
        String query = "INSERT INTO [dbo].[ChucVu]\n"        
                + "           ([TenChucVu]\n"
                + "           ,[Luong])\n"
                + "     VALUES\n"
                + "           (?,?)";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, chucVu.getTenChucVu());
            ps.setObject(2, chucVu.getLuong());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(String MaCV, ChucVu chucVu) {
        String query = "UPDATE [dbo].[ChucVu]\n"
                + "   SET \n"
                + "      [TenChucVu] = ?\n"
                + "      ,[Luong] = ?\n"
                + " WHERE MaChucVu=?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, chucVu.getTenChucVu());
            ps.setObject(2, chucVu.getLuong());
            ps.setObject(3, MaCV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(String MaCV) {
        String query = "DELETE FROM [dbo].[ChucVu]\n"
                + "      WHERE MaChucVu=?";
        int check = 0;
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, MaCV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

}
