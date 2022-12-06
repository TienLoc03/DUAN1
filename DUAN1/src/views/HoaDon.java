/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import application.NewClass;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import viewModels.displayvalueModel;

/**
 *
 * @author ASUS
 */
public class HoaDon extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();

    /**
     * Creates new form DatBan
     */
    public HoaDon() {
        initComponents();
        disabled();
        disabled2();
    }
    Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int month = cal.get(Calendar.MONTH) + 1;
    int year = cal.get(Calendar.YEAR);
    int hours = cal.get(Calendar.HOUR);
    int minutes = cal.get(Calendar.MINUTE);
    int second = cal.get(Calendar.SECOND);

    public void LayDuLieuHoaDon() {
        String cautruyvan = "";
        cautruyvan = "SELECT dbo.HoaDon.MaHoaDon, dbo.NhanVien.TenNhanVien, dbo.HoaDon.TenKhachHang, dbo.HoaDon.NgayTao,dbo.HoaDon.NgayThanhToan,dbo.HoaDon.MaKhuyenMai, dbo.HoaDon.TongTien, dbo.HoaDon.TrangThai,dbo.HoaDon.GhiChu\n"
                + "               FROM     dbo.HoaDon INNER JOIN\n"
                + "                                 dbo.NhanVien ON dbo.HoaDon.MaNhanVien = dbo.NhanVien.MaNhanVien\n"
                + "							 where HoaDon.TrangThai =N'Chưa thanh toán'";
        ResultSet rs = NewClass.connection.ExcuteQueryGetTable(cautruyvan);
        Object[] obj = new Object[]{"Mã hóa đơn", "Nhân viên", "Khách hàng", "Ngày tạo", "Ngày thanh toán", "Khuyến mãi", "Tổng tiền", "Trạng thái", "Chú thích"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        tblHoaDon.setModel(tableModel);
        try {
            while (rs.next()) {
                Object[] item = new Object[9];
                item[0] = rs.getInt("MaHoaDon");
                item[1] = rs.getString("TenNhanVien");
                item[2] = rs.getString("TenKhachHang");
                item[3] = rs.getString("NgayTao");
                item[4] = rs.getString("NgayThanhToan");
                item[5] = rs.getString("MaKhuyenMai");
                item[6] = rs.getBigDecimal("TongTien");
                item[7] = rs.getString("TrangThai");
                item[8] = rs.getString("GhiChu");
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void disabled() {
        cbbTenNV.setEnabled(false);
        txtTenKH.setEnabled(false);
        rdoChuaTT.setEnabled(false);
        rdoDaTT.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    private void disabled2() {
        cbb_MonAn.setEnabled(false);
        txt_SoLuong.setEnabled(false);
        btn_Xoa.setEnabled(false);
        btn_Luu.setEnabled(false);
        btn_Sua.setEnabled(false);
    }

    private void enabled() {
        cbbTenNV.setEnabled(true);
        txtTenKH.setEnabled(true);
        rdoChuaTT.setEnabled(true);
        rdoDaTT.setEnabled(true);
        btnLuu.setEnabled(true);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }

    private void enabled2() {
        cbb_MonAn.setEnabled(true);
        txt_SoLuong.setEnabled(true);
        btn_Xoa.setEnabled(true);
        btn_Luu.setEnabled(true);
        btn_Sua.setEnabled(true);
    }

    private void clear_hoadon() {
        txtMaHoaDon.setText("");
        txtNgayTao.setText("");
        txtTenKH.setText("");
        txtTongTien.setText("");
        cbbTenNV.setSelectedIndex(0);
        buttonGroup1.clearSelection();
    }

    public void clear_chitiethoadon() {
        txt_MaCTHD.setText("");

        cbb_MonAn.setSelectedIndex(0);
        txt_SoLuong.setText("");
        txt_ThanhTien.setText("");
    }

    public void LayDuLieuChiTietHoaDon(String MaHoaDon) {
        String cautruyvan = "";
        cautruyvan = "SELECT dbo.ChiTietHoaDon.MaCTHoaDon, dbo.ChiTietHoaDon.MaHoaDon, dbo.MonAn.TenMonAn, dbo.ChiTietHoaDon.SoLuong, dbo.ChiTietHoaDon.ThanhTien\n"
                + "FROM    ChiTietHoaDon,MonAn where ChiTietHoaDon.MaMonAn = MonAn.MaMonAn and ChiTietHoaDon.MaHoaDon=" + MaHoaDon;
        ResultSet rs = NewClass.connection.ExcuteQueryGetTable(cautruyvan);
        Object[] obj = new Object[]{"Mã CTHD", "Mã HD", "Món ăn", "Số Lượng", "Thành tiền"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        tblGoiMon.setModel(tableModel);
        try {
            while (rs.next()) {
                Object[] item = new Object[5];
                item[0] = rs.getString("MaCTHoaDon");
                item[1] = rs.getString("MaHoaDon");
                item[2] = rs.getString("TenMonAn");
                item[3] = rs.getInt("SoLuong");
                item[4] = rs.getBigDecimal("ThanhTien");
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public DefaultComboBoxModel LayDuLieucbb(String bang, String Ten, String Ma) {
        String cautruyvan = "select *from " + bang;
        ResultSet rs = NewClass.connection.ExcuteQueryGetTable(cautruyvan);
        DefaultComboBoxModel cbbmodel = new DefaultComboBoxModel();
        try {
            while (rs.next()) {
                displayvalueModel valueModel = new displayvalueModel(rs.getString(Ten), rs.getString(Ma));
                cbbmodel.addElement(valueModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cbbmodel;
    }

    public void setSelectedCombobox(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            Object obj = cbb.getItemAt(i);
            if (obj != null) {
                displayvalueModel m = (displayvalueModel) obj;
                if (cbbselected.trim().equals(m.displayMember)) {
                    cbb.setSelectedItem(m);
                }
            }
        }
    }

    public String GetCbbSelected(JComboBox cbb) {
        Object[] obj = cbb.getSelectedObjects();
        displayvalueModel item = (displayvalueModel) obj[0];
        return item.displayvalue.toString();

    }

    public void ThongBao(String noiDungThongBao, String tieuDeThongBao, int icon) {
        JOptionPane.showMessageDialog(this, noiDungThongBao,
                tieuDeThongBao, icon);
    }

    public boolean KiemTraNhapHoaDon(int ts) {
        String MaHoaDon, MaNhanVien, KhachHang, NgayTao, TongTien, tt, ThongBao = "";
        boolean kiemtra = false;
        MaHoaDon = txtMaHoaDon.getText();
        MaNhanVien = GetCbbSelected(cbbTenNV);
        KhachHang = txtTenKH.getText();
        NgayTao = txtNgayTao.getText();
        TongTien = txtTongTien.getText();
        if (rdoDaTT.isSelected()) {
            tt = "Đã thanh toán";
        } else {
            tt = "Chưa thanh toán";
        }
        if (MaHoaDon.equals("") && ts == 1) {
            ThongBao += "bạn chưa chọn Hóa Đơn để lấy  Mã Hóa Dơn\n";
        }
        if (MaNhanVien.equals("")) {
            ThongBao += "bạn chưa Chọn Nhân Viên\n";
        }
        if (KhachHang.equals("")) {
            ThongBao += "Bạn chưa nhập Khách Hàng\n";
        }
        if (NgayTao.equals("")) {
            ThongBao += "Bạn chưa Nhập Ngày Lập\n";
        }
        if (TongTien.equals("")) {
            txtTongTien.setText("0");
        }
        if (ThongBao.equals("")) {
            kiemtra = true;

        } else {
            kiemtra = false;
            ThongBao(ThongBao, "lỗi nhập liệu", 2);
        }
        return kiemtra;
    }

    public boolean KiemTraNhapChiTietHoaDon(int ts) {
        String MaHoaDon, MaChiTietHoaDon, MonAn, SoLuong, ThongBao = "";
        boolean kiemtra = false;
        MaHoaDon = txt_MaHD.getText();
        MaChiTietHoaDon = txt_MaCTHD.getText();
        MonAn = GetCbbSelected(cbb_MonAn);
        SoLuong = txt_SoLuong.getText();
        if (MaChiTietHoaDon.equals("") && ts == 1) {
            ThongBao += "bạn chưa chọn Hóa Đơn để lấy  Mã Hóa Dơn\n";
        }
        if (MaHoaDon.equals("")) {
            ThongBao += "bạn chưa chọn trong hóa đơn nào cả\n";
        }
        if (SoLuong.equals("")) {
            ThongBao += "bạn chưa Nhập Số Lượng";
        }
        try {
            int bien = Integer.valueOf(SoLuong);
        } catch (Exception e) {
            ThongBao += "Số lượng phải nhập Bằng số";
        }
        if (ThongBao.equals("")) {
            kiemtra = true;
        } else {
            kiemtra = false;
            ThongBao(ThongBao, "lỗi nhập liệu", 2);
        }
        return kiemtra;
    }

    public void SetTongTien(String MaHoaDon) {
        String cautruyvan = "select sum(ChiTietHoaDon.ThanhTien) as TongTienHienTai,HoaDon.MaHoaDon from HoaDon,ChiTietHoaDon \n"
                + "                where HoaDon.MaHoaDon=ChiTietHoaDon.MaHoaDon\n"
                + "                and HoaDon.MaHoaDon=" + MaHoaDon + " group by HoaDon.MaHoaDon";
        ResultSet rs = application.NewClass.connection.ExcuteQueryGetTable(cautruyvan);
        String ttht = "";
        try {
            if (rs.next()) {
                ttht = rs.getString("TongTienHienTai");
                txt_ThanhTien.setText(ttht);
                String ctv = "update HoaDon set TongTien= " + ttht + "where MaHoaDon=" + MaHoaDon;
                NewClass.connection.ExcuteQueryUpdateDB(ctv);
                LayDuLieuHoaDon();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int GetGiaSanPham(String MaSP) {
        int Gia = 0;
        String cautruyvan = "select * from MonAn where MaMonAn =" + MaSP;
        ResultSet rs = application.NewClass.connection.ExcuteQueryGetTable(cautruyvan);
        try {
            if (rs.next()) {
                Gia = rs.getInt("GiaTien");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Gia;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lbThongtin3 = new javax.swing.JLabel();
        jScollpanel = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        txtMaHoaDon = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        rdoChuaTT = new javax.swing.JRadioButton();
        rdoDaTT = new javax.swing.JRadioButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Them = new javax.swing.JButton();
        btn_Luu = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        lbThongtin4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGoiMon = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        cbbTenNV = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_ThanhTien = new javax.swing.JTextField();
        txt_MaCTHD = new javax.swing.JTextField();
        txt_MaHD = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();
        cbb_MonAn = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbThongtin3.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin3.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin3.setText("Gọi món");
        lbThongtin3.setToolTipText("");
        jPanel1.add(lbThongtin3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 620, 50));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Tên nhân viên", "Tên khách hàng", "Ngày tạo", "Tổng tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScollpanel.setViewportView(tblHoaDon);

        jPanel1.add(jScollpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 790, 250));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(49, 139, 130));
        jLabel3.setText("Tên Nhân viên");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(49, 139, 130));
        jLabel4.setText("Tên khách hàng");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(49, 139, 130));
        jLabel5.setText("Tổng tiền");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, -1, -1));

        txtTongTien.setEditable(false);
        jPanel1.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 160, 180, -1));

        txtMaHoaDon.setEditable(false);
        jPanel1.add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 180, -1));
        jPanel1.add(txtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 180, -1));

        buttonGroup1.add(rdoChuaTT);
        rdoChuaTT.setText("Chưa thanh toán");
        jPanel1.add(rdoChuaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, -1, -1));

        buttonGroup1.add(rdoDaTT);
        rdoDaTT.setText("Đã thanh toán");
        jPanel1.add(rdoDaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 230, -1, -1));

        btn_Xoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Xoa.setForeground(new java.awt.Color(49, 139, 130));
        btn_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 550, 100, -1));

        btn_Them.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Them.setForeground(new java.awt.Color(49, 139, 130));
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add_28px.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Them, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 430, -1, -1));

        btn_Luu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Luu.setForeground(new java.awt.Color(49, 139, 130));
        btn_Luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save_28px.png"))); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Luu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 470, 100, -1));

        btn_Sua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Sua.setForeground(new java.awt.Color(49, 139, 130));
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edit_28px.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 510, 100, -1));

        lbThongtin4.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin4.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin4.setText("Hóa đơn");
        lbThongtin4.setToolTipText("");
        jPanel1.add(lbThongtin4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 620, 50));

        tblGoiMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã CTHD", "Mã HD", "Tên món ăn", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGoiMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGoiMonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGoiMon);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 790, 230));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(49, 139, 130));
        jLabel7.setText("Trạng thái");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, -1, -1));

        txtNgayTao.setEditable(false);
        jPanel1.add(txtNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 260, 180, -1));

        jPanel1.add(cbbTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 180, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(49, 139, 130));
        jLabel9.setText("Mã CTHD");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 430, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(49, 139, 130));
        jLabel10.setText("Món ăn");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 500, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(49, 139, 130));
        jLabel11.setText("Số lượng");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 530, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(49, 139, 130));
        jLabel12.setText("Thành tiền");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 560, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(49, 139, 130));
        jLabel13.setText("Mã hóa đơn");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 460, -1, -1));

        txt_ThanhTien.setEditable(false);
        jPanel1.add(txt_ThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 560, 190, -1));

        txt_MaCTHD.setEditable(false);
        jPanel1.add(txt_MaCTHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 430, 190, -1));

        txt_MaHD.setEditable(false);
        jPanel1.add(txt_MaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, 190, -1));

        txt_SoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_SoLuongKeyReleased(evt);
            }
        });
        jPanel1.add(txt_SoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 530, 190, -1));

        jPanel1.add(cbb_MonAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 500, 190, -1));

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(49, 139, 130));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add_28px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 70, 100, -1));

        btnLuu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(49, 139, 130));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save_28px.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 110, 100, -1));

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(49, 139, 130));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edit_28px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 150, 100, -1));

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(49, 139, 130));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 190, 100, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 139, 130));
        jLabel2.setText("Chú thích");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 310, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(49, 139, 130));
        jLabel14.setText("Mã hóa đơn");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(49, 139, 130));
        jLabel6.setText("Ngày tạo");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 300, -1, -1));

        jTabbedPane1.addTab("tab1", jPanel1);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 680));
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown
        LayDuLieuHoaDon();
        cbbTenNV.setModel(LayDuLieucbb("NhanVien", "TenNhanVien", "MaNhanVien"));
        cbb_MonAn.setModel(LayDuLieucbb("MonAn", "TenMonAn", "MaMonAn"));
    }//GEN-LAST:event_jPanel1ComponentShown

    private void tblGoiMonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGoiMonMouseClicked
        int row = tblGoiMon.getSelectedRow();
        txt_MaCTHD.setText(tblGoiMon.getValueAt(row, 0).toString());
        txt_MaHD.setText(tblGoiMon.getValueAt(row, 1).toString());
        setSelectedCombobox(tblGoiMon.getValueAt(row, 2).toString(), cbb_MonAn);
        txt_SoLuong.setText(tblGoiMon.getValueAt(row, 3).toString());
        txt_ThanhTien.setText(tblGoiMon.getValueAt(row, 4).toString());
        LayDuLieuChiTietHoaDon(txtMaHoaDon.getText());
    }//GEN-LAST:event_tblGoiMonMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int row = tblHoaDon.getSelectedRow();
        txtMaHoaDon.setText(tblHoaDon.getValueAt(row, 0).toString());
        setSelectedCombobox((String) tblHoaDon.getValueAt(row, 1), cbbTenNV);
        txtTenKH.setText(tblHoaDon.getValueAt(row, 2).toString());
        txtNgayTao.setText(tblHoaDon.getValueAt(row, 3).toString());
        txtTongTien.setText(tblHoaDon.getValueAt(row, 6).toString());
        String tt = tblHoaDon.getValueAt(row, 7).toString();
        jTextArea1.setText(tblHoaDon.getValueAt(row, 8).toString());
        if (tt.equals("Chưa thanh toán")) {
            rdoChuaTT.setSelected(true);
            rdoDaTT.setSelected(false);
        } else {
            rdoDaTT.setSelected(true);
            rdoChuaTT.setSelected(false);
        }
        LayDuLieuChiTietHoaDon(txtMaHoaDon.getText());
        if (tblGoiMon.getRowCount() > 0) {
            cbb_MonAn.setModel(LayDuLieucbb("MonAn", "TenMonAn", "MaMonAn"));
            txt_MaCTHD.setText(tblGoiMon.getValueAt(0, 0).toString());
            txt_MaHD.setText(tblGoiMon.getValueAt(0, 1).toString());
            setSelectedCombobox(tblGoiMon.getValueAt(0, 2).toString(), cbb_MonAn);
            txt_SoLuong.setText(tblGoiMon.getValueAt(0, 3).toString());
            txt_ThanhTien.setText(tblGoiMon.getValueAt(0, 4).toString());

        } else {
            clear_chitiethoadon();
            txt_MaHD.setText(txtMaHoaDon.getText());
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        clear_hoadon();
        enabled();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        String maNV, tenKH, tongtien, tt, ngayTao, ngayTT, maKM, ct;
        maNV = GetCbbSelected(cbbTenNV);
        tenKH = txtTenKH.getText();
        txtNgayTao.setText(year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + second);
        ngayTao = txtNgayTao.getText();
        tongtien = txtTongTien.getText();
        ct = jTextArea1.getText();
        if (rdoDaTT.isSelected()) {
            tt = "Đã thanh toán";
        } else {
            tt = "Chưa thanh toán";
        }
        String cautruyvan = "insert into HoaDon(MaNhanVien,TenKhachHang,NgayTao,TongTien,TrangThai,GhiChu) values(N'" + maNV + "',N'" + tenKH + "',N'" + ngayTao + "'," + 0 + ",N'" + tt + "',N'" + ct + "')";
        boolean kiemtra = KiemTraNhapHoaDon(0);
        if (kiemtra) {
            NewClass.connection.ExcuteQueryUpdateDB(cautruyvan);
            JOptionPane.showMessageDialog(this, "Đã lưu thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Lưu thất bại");
        }
        LayDuLieuHoaDon();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String MaHD, TenKH, TongTien, tt, ct;
        MaHD = txtMaHoaDon.getText();
        TenKH = txtTenKH.getText();
        TongTien = txtTongTien.getText();
        ct = jTextArea1.getText();
        if (rdoDaTT.isSelected()) {
            tt = "Đã thanh toán";
        } else {
            tt = "Chưa thanh toán";
        }
        String cautruyvan = "update HoaDon set TenKhachHang=N'" + TenKH + "',TongTien="
                + TongTien + ",TrangThai=N'" + tt + "',GhiChu=N'" + ct + "' where MaHoaDon=" + MaHD;
        boolean kiemtra = KiemTraNhapHoaDon(1);
        if (kiemtra) {
            NewClass.connection.ExcuteQueryUpdateDB(cautruyvan);
            JOptionPane.showMessageDialog(this, "Đã sửa Thành Công");
        } else {
            ThongBao("Không thể ", "Sửa Hóa Đơn", 2);
        }
        LayDuLieuHoaDon();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (!txtMaHoaDon.getText().equals("")) {
            String MaHoaDon = txtMaHoaDon.getText();
            String cautruyvan = "delete HoaDon where MaHoaDon=" + MaHoaDon;
            String ctvKiemThu = "select count(MaCTHoaDon) as SoChiTietPhieuMua\n"
                    + "from HoaDon,ChiTietHoaDon where HoaDon.MaHoaDon=ChiTietHoaDon.MaHoaDon and HoaDon.MaHoaDon=" + MaHoaDon;
            ResultSet rs1 = application.NewClass.connection.ExcuteQueryGetTable(ctvKiemThu);
            int so1 = 0;
            try {
                if (rs1.next()) {
                    so1 = rs1.getInt("SoChiTietPhieuMua");
                    if (rs1.getInt("SoChiTietPhieuMua") == 0) {
                        application.NewClass.connection.ExcuteQueryUpdateDB(cautruyvan);
                        JOptionPane.showMessageDialog(this, "Đã xóa thành công");
                        LayDuLieuHoaDon();
                    } else {
                        ThongBao("không thể xóa bởi hóa đơn đã có " + so1 + " chi tiết hóa đơn!", "báo lỗi", 2);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            ThongBao("bạn chưa chọn hóa đơn để xóa", "xóa bằng niềm tin à!khi không biết xóa cái nào", 2);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        clear_chitiethoadon();
        enabled2();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
        String MaHoaDon, MaMonAn, SoLuong, TongTien;
        MaHoaDon = txt_MaHD.getText();
        MaMonAn = GetCbbSelected(cbb_MonAn);
        SoLuong = txt_SoLuong.getText();
        TongTien = txt_ThanhTien.getText();
        String cautruyvan = "insert into ChiTietHoaDon(MaHoaDon, MaMonAn, SoLuong, ThanhTien) values(" + MaHoaDon + "," + MaMonAn + "," + SoLuong + "," + TongTien + ")";
        boolean kiemtra = KiemTraNhapChiTietHoaDon(0);
        if (kiemtra) {
            NewClass.connection.ExcuteQueryUpdateDB(cautruyvan);
            JOptionPane.showMessageDialog(this, "Đã Lưu thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Lưu thất bại");
        }
        LayDuLieuChiTietHoaDon(MaHoaDon);
        SetTongTien(MaHoaDon);
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        String MaHoaDon, MaChiTietHoaDon, MonAn, SoLuong, TongTien;
        MaChiTietHoaDon = txt_MaCTHD.getText();
        MaHoaDon = txt_MaHD.getText();
        MonAn = GetCbbSelected(cbb_MonAn);
        SoLuong = txt_SoLuong.getText();
        TongTien = txt_ThanhTien.getText();
        String cautruyvan = "update  ChiTietHoaDon set MaHoaDon=" + MaHoaDon + ",MaMonAn=" + MonAn + ",SoLuong=" + SoLuong + ",TongTien="
                + TongTien + " where MaCTHD=" + MaChiTietHoaDon;
        boolean kiemtra = KiemTraNhapChiTietHoaDon(1);
        if (kiemtra && !MaChiTietHoaDon.equals("")) {
            NewClass.connection.ExcuteQueryUpdateDB(cautruyvan);
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
        LayDuLieuChiTietHoaDon(MaHoaDon);
        SetTongTien(MaHoaDon);
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        String MaHoaDon = txt_MaHD.getText();
        String MaChiTietHoaDon = txt_MaCTHD.getText();
        String cautruyvan = "delete ChiTietHoaDon where MaCTHoaDon=" + MaChiTietHoaDon;
        application.NewClass.connection.ExcuteQueryUpdateDB(cautruyvan);
        JOptionPane.showMessageDialog(this, "Xóa thành công");
        LayDuLieuChiTietHoaDon(MaHoaDon);
        SetTongTien(MaHoaDon);
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void txt_SoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_SoLuongKeyReleased
        int SoLuong = 0;
        double Tien = 0;
        try {
            SoLuong = Integer.valueOf(txt_SoLuong.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int Gia = GetGiaSanPham(GetCbbSelected(cbb_MonAn));
        Tien = (double) Gia * SoLuong;
        txt_ThanhTien.setText(String.valueOf(Tien));
    }//GEN-LAST:event_txt_SoLuongKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbTenNV;
    private javax.swing.JComboBox<String> cbb_MonAn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScollpanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbThongtin3;
    private javax.swing.JLabel lbThongtin4;
    private javax.swing.JRadioButton rdoChuaTT;
    private javax.swing.JRadioButton rdoDaTT;
    private javax.swing.JTable tblGoiMon;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txt_MaCTHD;
    private javax.swing.JTextField txt_MaHD;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_ThanhTien;
    // End of variables declaration//GEN-END:variables
}
