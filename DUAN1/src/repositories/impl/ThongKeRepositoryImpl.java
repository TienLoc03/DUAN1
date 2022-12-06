/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import repositories.ThongKeRepository;
import utilies.DBConnection;
import viewModels.ThongKeSPResponse;
import viewModels.ThongKeSPResponse2;

/**
 *
 * @author ASUS
 */
public class ThongKeRepositoryImpl implements ThongKeRepository {

    @Override
    public ArrayList<ThongKeSPResponse> getAll() {
        String sql = "SELECT MonAn.TenMonAn, count(ChiTietHoaDon.SoLuong) as SoLuong\n"
                + "FROM     dbo.MonAn INNER JOIN\n"
                + "                  dbo.ChiTietHoaDon ON dbo.MonAn.MaMonAn = dbo.ChiTietHoaDon.MaMonAn INNER JOIN\n"
                + "                  dbo.HoaDon ON dbo.ChiTietHoaDon.MaHoaDon = dbo.HoaDon.MaHoaDon\n"
                + "				  where HoaDon.TrangThai =N'Đã thanh toán' group by TenMonAn";
        ArrayList<ThongKeSPResponse> listThongKeSPResponses = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listThongKeSPResponses.add(new ThongKeSPResponse(rs.getString(1), rs.getInt(2)));
            }
            return listThongKeSPResponses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ThongKeSPResponse2> getAll2() {
        String sql = "SELECT Ngay,count(TenKhachHang) as SoLuong\n"
                + "FROM     HoaDon\n"
                + "where HoaDon.TrangThai = N'Đã thanh toán' group by HoaDon.Ngay";
        ArrayList<ThongKeSPResponse2> listThongKeSP = new ArrayList<>();
        try ( Connection con = DBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeSPResponse2 tk = new ThongKeSPResponse2();
                tk.setNgay(rs.getDate("Ngay"));
                tk.setSoLuong(rs.getInt("SoLuong"));
                listThongKeSP.add(tk);
            }
            return listThongKeSP;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
