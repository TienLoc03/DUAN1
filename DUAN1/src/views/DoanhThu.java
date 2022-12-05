/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import application.NewClass;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import services.MonAnService;
import services.NhanVienService;
import services.ThongKeService;
import services.impl.MonAnServiceImpl;
import services.impl.NhanVienServiceImpl;
import services.impl.ThongKeServiceImpl;
import utilies.DBConnection;
import viewModels.MonAnResponse;
import viewModels.NhanVienResponse;
import viewModels.ThongKeSPResponse;

/**
 *
 * @author ASUS
 */
public class DoanhThu extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private ArrayList<MonAnResponse> listMonAnResponses = new ArrayList<>();
    private DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
    private MonAnService monAnService = new MonAnServiceImpl();
    private ThongKeService thongKeService = new ThongKeServiceImpl();
    private ArrayList<ThongKeSPResponse> list = new ArrayList<>();
    public Connection conn = DBConnection.getConnection();

    /**
     * Creates new form DoanhThu
     */
    public DoanhThu() {
        initComponents();
        list = thongKeService.getAll();
        listMonAnResponses = monAnService.getAll();
        loadCBB(listMonAnResponses);
        txtTenMon.disable();
        ShowTongTien();
        ShowHoaDon();
        setDataToChart1(jpnView2);
        top5();
    }

    public void top5() {
        String cautruyvan = "SELECT top(5) MonAn.TenMonAn, count(ChiTietHoaDon.SoLuong) as SoLuongBan\n"
                + "                FROM     dbo.MonAn INNER JOIN\n"
                + "                                 dbo.ChiTietHoaDon ON dbo.MonAn.MaMonAn = dbo.ChiTietHoaDon.MaMonAn INNER JOIN\n"
                + "                                 dbo.HoaDon ON dbo.ChiTietHoaDon.MaHoaDon = dbo.HoaDon.MaHoaDon\n"
                + "                				  where HoaDon.TrangThai = N'Đã thanh toán' group by TenMonAn order by SoLuongBan desc";
        ResultSet rs = NewClass.connection.ExcuteQueryGetTable(cautruyvan);
        Object[] obj = new Object[]{"Tên món ăn", "Số lượng bán được"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        tbl_Top5.setModel(tableModel);
        try {
            while (rs.next()) {
                Object[] item = new Object[2];
                item[0] = rs.getString("TenMonAn");
                item[1] = rs.getInt("SoLuongBan");
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadCBB(ArrayList<MonAnResponse> listMonAnResponses) {
        boxModel = (DefaultComboBoxModel) cbbMaMA.getModel();
        for (MonAnResponse o : listMonAnResponses) {
            cbbMaMA.addItem(o.getMaMonAn() + "");
        }
    }

    public void traCuu(String MaMA) throws SQLException {
        String sql = "SELECT dbo.HoaDon.MaHoaDon, dbo.MonAn.TenMonAn, dbo.ChiTietHoaDon.SoLuong, dbo.ChiTietHoaDon.ThanhTien, dbo.HoaDon.NgayThanhToan\n"
                + "FROM     dbo.HoaDon INNER JOIN\n"
                + "                  dbo.ChiTietHoaDon ON dbo.HoaDon.MaHoaDon = dbo.ChiTietHoaDon.MaHoaDon INNER JOIN\n"
                + "                  dbo.MonAn ON dbo.ChiTietHoaDon.MaMonAn = dbo.MonAn.MaMonAn \n"
                + "		     where MonAn.MaMonAn = " + MaMA + " and HoaDon.NgayThanhToan is not null";
        ResultSet rs = NewClass.connection.ExcuteQueryGetTable(sql);
        Object[] obj = new Object[]{"Mã hóa đơn", "Tên món ăn", "Số lượng", "Thành tiền", "Ngày thanh toán"};
        DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
        tbldoanhThuMon.setModel(tableModel);
        try {
            while (rs.next()) {
                Object[] item = new Object[5];
                item[0] = rs.getInt("MaHoaDon");
                item[1] = rs.getString("TenMonAn");
                item[2] = rs.getString("SoLuong");
                item[3] = rs.getString("ThanhTien");
                item[4] = rs.getString("NgayThanhToan");
                tableModel.addRow(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void ShowTongTien() {
        String cautruyvan = "select sum(HoaDon.TongTien) as TongTien from HoaDon  where NgayThanhToan is not null";
        ResultSet rs = application.NewClass.connection.ExcuteQueryGetTable(cautruyvan);
        String tt = "";
        try {
            if (rs.next()) {
                tt = rs.getString("TongTien");
                lbTongTien.setText(tt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void ShowHoaDon() {
        String cautruyvan = "select count(HoaDon.MaHoaDon) as HoaDon from HoaDon  where NgayThanhToan is not null";
        ResultSet rs = application.NewClass.connection.ExcuteQueryGetTable(cautruyvan);
        String hd = "";
        try {
            if (rs.next()) {
                hd = rs.getString("HoaDon");
                lbSoHD.setText(hd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setDataToChart1(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (list != null) {
            for (ThongKeSPResponse o : list) {
                dataset.addValue(o.getSoLuongSP(), "Sản phẩm", o.getTenSP());
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "thống kê số lượng món ăn bán được".toUpperCase(),
                "Món ăn", "Số lượng",
                dataset);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbbMaMA = new javax.swing.JComboBox<>();
        btnTraCuu = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbSoHD = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        txtTenMon = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldoanhThuMon = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        JscrollPane = new javax.swing.JScrollPane();
        tbl_Top5 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jpnView2 = new javax.swing.JPanel();

        jLabel1.setText("jLabel1");

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(49, 139, 130));
        jLabel3.setText("Chọn tên món");

        cbbMaMA.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbMaMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaMAActionPerformed(evt);
            }
        });

        btnTraCuu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTraCuu.setForeground(new java.awt.Color(49, 139, 130));
        btnTraCuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search_28px.png"))); // NOI18N
        btnTraCuu.setText("Tra cứu");
        btnTraCuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraCuuActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(49, 139, 130));
        jLabel4.setText("Tổng số hóa đơn");

        lbSoHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbSoHD.setText("0 Chiếc");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(49, 139, 130));
        jLabel6.setText("Tổng tiền hóa đơn");

        lbTongTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTongTien.setText("0 VNĐ");

        txtTenMon.setEditable(false);
        txtTenMon.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbMaMA, 0, 273, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenMon)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(btnTraCuu))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbMaMA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTraCuu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbSoHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbTongTien))
                .addGap(62, 62, 62))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tbldoanhThuMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên món ăn", "Số lượng", "Tổng tiền", "Ngày bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbldoanhThuMon);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 139, 130));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/DTfood.png"))); // NOI18N
        jLabel2.setText("Doanh Thu theo món ăn");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(jLabel2)
                .addContainerGap(658, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );

        jTabbedPane1.addTab("Món ăn", jPanel2);

        tbl_Top5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JscrollPane.setViewportView(tbl_Top5);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/KFC.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
            .addComponent(JscrollPane)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JscrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Top 5 món bán chạy", jPanel11);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(49, 139, 130));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Chart.png"))); // NOI18N
        jLabel8.setText("BIỂU ĐỒ SỐ LƯỢNG THỨC ĂN BÁN ĐƯỢC");

        javax.swing.GroupLayout jpnView2Layout = new javax.swing.GroupLayout(jpnView2);
        jpnView2.setLayout(jpnView2Layout);
        jpnView2Layout.setHorizontalGroup(
            jpnView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1070, Short.MAX_VALUE)
        );
        jpnView2Layout.setVerticalGroup(
            jpnView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnView2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(jpnView2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Biểu đồ", jPanel10);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void btnTraCuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraCuuActionPerformed
        try {
            traCuu(cbbMaMA.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTraCuuActionPerformed

    private void cbbMaMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaMAActionPerformed
        String sql = "SELECT TenMonAn FROM MonAn WHERE MaMonAn = ?";
        try {
            String maTheLoaiString = (String) cbbMaMA.getSelectedItem();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maTheLoaiString);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                txtTenMon.setText(rs.getString("TenMonAn"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbMaMAActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JscrollPane;
    private javax.swing.JButton btnTraCuu;
    private javax.swing.JComboBox<String> cbbMaMA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpnView;
    private javax.swing.JPanel jpnView1;
    private javax.swing.JPanel jpnView2;
    private javax.swing.JLabel lbSoHD;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JTable tbl_Top5;
    private javax.swing.JTable tbldoanhThuMon;
    private javax.swing.JTextField txtTenMon;
    // End of variables declaration//GEN-END:variables
}
