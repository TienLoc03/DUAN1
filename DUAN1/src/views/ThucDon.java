package views;

import domainModels.DanhMuc;
import domainModels.MonAn;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import services.DanhMucService;
import services.MonAnService;
import services.impl.DanhMucServiceImpl;
import services.impl.MonAnServiceImpl;
import utilies.DBConnection;
import viewModels.DanhMucResponse;
import viewModels.MonAnResponse;

/**
 *
 * @author ASUS
 */
public class ThucDon extends javax.swing.JPanel {

    public Connection conn = DBConnection.getConnection();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm1 = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
    private ArrayList<MonAnResponse> listMonAnResponses = new ArrayList<>();
    private ArrayList<DanhMucResponse> listDanhMucResponses = new ArrayList<>();
    private ArrayList<MonAnResponse> listShow = new ArrayList<>();
    private ArrayList<DanhMucResponse> list = new ArrayList<>();
    private String strHinhAnh = null;
    private MonAnService monAnService = new MonAnServiceImpl();
    private DanhMucService danhMucService = new DanhMucServiceImpl();

    /**
     * Creates new form ThucDon
     */
    public ThucDon() {
        initComponents();
        listMonAnResponses = monAnService.getAll();
        listDanhMucResponses = danhMucService.getAll();
        list = danhMucService.getAll();
        showData(listMonAnResponses);
        showData1(listDanhMucResponses);
        showData2(listShow);
        Disable();
        Disable1();
        loadCBB(list);
    }

    private void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dtm);
        tblThucDon.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

    private void loadCBB(ArrayList<DanhMucResponse> list) {
        boxModel = (DefaultComboBoxModel) cbbMaLoaiMon.getModel();
        for (DanhMucResponse o : list) {
            cbbMaLoaiMon.addItem(o.getMaLoaiMon() + "");
        }
    }

    private void Disable() {
        txtTenMon.setEnabled(false);
        txtGiaTien.setEnabled(false);
        txtTenLoaiMon.setEnabled(false);
        txtDonVi.setEnabled(false);
        txtMaMon.setEnabled(false);
        rdoCon.setEnabled(false);
        rdoHet.setEnabled(false);
        cbbMaLoaiMon.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    private void Disable1() {
        txt_MaLoaiMon.setEnabled(false);
        txt_TenLoaiMon.setEnabled(false);
        btn_Luu.setEnabled(false);
        btn_Sua.setEnabled(false);
        btn_Xoa.setEnabled(false);
        btn_XoaSP.setEnabled(false);
    }

    private void Enabled() {
        txtTenMon.setEnabled(true);
        txtGiaTien.setEnabled(true);
        txtDonVi.setEnabled(true);
        txtMaMon.setEnabled(true);
        rdoCon.setEnabled(true);
        rdoHet.setEnabled(true);
        cbbMaLoaiMon.setEnabled(true);
        btnLuu.setEnabled(true);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }

    private void Enable1() {
        txt_MaLoaiMon.setEnabled(true);
        txt_TenLoaiMon.setEnabled(true);
        btn_Luu.setEnabled(true);
        btn_Sua.setEnabled(true);
        btn_Xoa.setEnabled(true);
        btn_XoaSP.setEnabled(true);
    }

    private void Clear() {
        txtTenMon.setText("");
        txtGiaTien.setText("");
        txtGiaTien.setText("");
        txtDonVi.setText("");
        txtMaMon.setText("");
        buttonGroup1.clearSelection();
        cbbMaLoaiMon.setSelectedIndex(0);
        lbImages.setIcon(null);
    }

    private void Clear1() {
        txt_MaLoaiMon.setText("");
        txt_TenLoaiMon.setText("");
    }

    private void showData(ArrayList<MonAnResponse> listMonAnResponses) {
        dtm = (DefaultTableModel) tblThucDon.getModel();
        dtm.setRowCount(0);
        for (MonAnResponse o : listMonAnResponses) {
            dtm.addRow(o.toDataRow());
        }
    }

    private void showData1(ArrayList<DanhMucResponse> listDanhMucResponses) {
        dtm1 = (DefaultTableModel) tbl_LoaiMonAn.getModel();
        dtm1.setRowCount(0);
        for (DanhMucResponse t : listDanhMucResponses) {
            dtm1.addRow(t.toDataRow());
        }
    }

    private void showData2(ArrayList<MonAnResponse> listShow) {
        dtm2 = (DefaultTableModel) tbl_MonAn.getModel();
        dtm2.setRowCount(0);
        for (MonAnResponse o : listShow) {
            dtm2.addRow(o.toDataRow2());
        }
    }

    private void fillData(int index) {
        MonAnResponse m = listMonAnResponses.get(index);
        txtTenMon.setText(m.getTenMonAn());
        cbbMaLoaiMon.setSelectedItem(m.getMaLoaiMon());
        txtTenLoaiMon.setText(m.getDanhMucResponse().getTenLoaiMon());
        txtDonVi.setText(m.getDonVi());
        txtGiaTien.setText(m.getGiaTien() + "");
        txtMaMon.setText(m.getMaMonAn() + "");
        if (m.getTrangThai().equals("Còn")) {
            rdoCon.setSelected(true);
        } else {
            rdoHet.setSelected(true);
        }
        UpdateHinh(m.getImageMonAn());
    }

    private void fillData1(int index) {
        DanhMucResponse d = listDanhMucResponses.get(index);
        txt_TenLoaiMon.setText(d.getTenLoaiMon());
        txt_MaLoaiMon.setText(d.getMaLoaiMon() + "");
    }


    public void UpdateHinh(String image) {
        ImageIcon img = new ImageIcon(getClass().getResource("/FoodPNG/" + image));
        Image anh = img.getImage();
        ImageIcon icon = new ImageIcon(anh.getScaledInstance(lbImages.getWidth(),
                lbImages.getHeight(), anh.SCALE_SMOOTH));
        lbImages.setIcon(icon);
    }

    private MonAn getData() {
        MonAn m = new MonAn();       
        m.setMaLoaiMon(Integer.parseInt((String) cbbMaLoaiMon.getSelectedItem()));
        if(txtTenMon.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Tên món ăn không được để trống");
            return null;
        }
        m.setTenMonAn(txtTenMon.getText());
        if(txtDonVi.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Đơn vị món ăn không được để trống");
            return null;
        }
        m.setDonVi(txtDonVi.getText());
        String tt = "Còn";
        if (rdoCon.isSelected()) {
            m.setTrangThai(tt);
        } else {
            tt = "Hết";
            m.setTrangThai(tt);
        }
        if(txtGiaTien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Giá món ăn không được để trống");
            return null;
        }
        try{
            double bien = Double.valueOf(txtGiaTien.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Giá món ăn không được Nhập chữ");
        }
        if(Double.parseDouble(txtGiaTien.getText()) <=0){
            JOptionPane.showMessageDialog(this, "Giá món ăn không được nhỏ hơn không");
            return null;
        }
        m.setGiaTien(Double.parseDouble(txtGiaTien.getText()));
        if (strHinhAnh == null) {
            m.setImageMonAn("ios_photos_100px.png");
        } else {
            m.setImageMonAn(strHinhAnh);
        }
        return m;
    }
    private void kiemtra(){
        
    }
    private DanhMuc getData2() {
        DanhMuc d = new DanhMuc();
        if(txt_TenLoaiMon.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Tên món ăn không được để trống");
            return null;
        }
        d.setTenLoaiMon(txt_TenLoaiMon.getText());
        return d;
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
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThucDon = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        lbThongtin4 = new javax.swing.JLabel();
        lbMatu3 = new javax.swing.JLabel();
        lbLoai3 = new javax.swing.JLabel();
        lbTen3 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lbGia6 = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        txtDonVi = new javax.swing.JTextField();
        txtTenMon = new javax.swing.JTextField();
        cbbMaLoaiMon = new javax.swing.JComboBox<>();
        txtMaMon = new javax.swing.JTextField();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem3 = new javax.swing.JButton();
        lbGia7 = new javax.swing.JLabel();
        txtTenLoaiMon = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rdoHet = new javax.swing.JRadioButton();
        rdoCon = new javax.swing.JRadioButton();
        lbThongtin1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lbImages = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbThongtin2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_LoaiMonAn = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txt_MaLoaiMon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_TenLoaiMon = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Luu = new javax.swing.JButton();
        lbThongtin3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_MonAn = new javax.swing.JTable();
        btn_XoaSP = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Show = new javax.swing.JButton();
        txt_MMA = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblThucDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblThucDon.setForeground(new java.awt.Color(49, 139, 130));
        tblThucDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Loại món ăn", "Tên món ăn", "Đơn vị", "Trạng thái", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThucDon.setSelectionForeground(new java.awt.Color(49, 139, 130));
        tblThucDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThucDonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThucDon);

        jPanel9.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 77, 540, 560));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        lbThongtin4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbThongtin4.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin4.setText("Thông tin món:");
        lbThongtin4.setToolTipText("");

        lbMatu3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMatu3.setForeground(new java.awt.Color(49, 139, 130));
        lbMatu3.setText("Mã món:");
        lbMatu3.setToolTipText("");

        lbLoai3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbLoai3.setForeground(new java.awt.Color(49, 139, 130));
        lbLoai3.setText("Mã loại món:");
        lbLoai3.setToolTipText("");

        lbTen3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTen3.setForeground(new java.awt.Color(49, 139, 130));
        lbTen3.setText("Tên món:");
        lbTen3.setToolTipText("");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(49, 139, 130));
        jLabel26.setText("Đơn vị tính:");
        jLabel26.setToolTipText("");

        lbGia6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbGia6.setForeground(new java.awt.Color(49, 139, 130));
        lbGia6.setText("Giá tiền:");
        lbGia6.setToolTipText("");

        txtGiaTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtGiaTien.setToolTipText("");

        txtDonVi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDonVi.setToolTipText("");

        txtTenMon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenMon.setToolTipText("");

        cbbMaLoaiMon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbMaLoaiMon.setToolTipText("");
        cbbMaLoaiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaLoaiMonActionPerformed(evt);
            }
        });

        txtMaMon.setEditable(false);
        txtMaMon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaMon.setToolTipText("");

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(49, 139, 130));
        txtTimKiem.setToolTipText("");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnTimKiem3.setForeground(new java.awt.Color(49, 139, 130));
        btnTimKiem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search_28px.png"))); // NOI18N
        btnTimKiem3.setText("Tìm");
        btnTimKiem3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTimKiem3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lbGia7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbGia7.setForeground(new java.awt.Color(49, 139, 130));
        lbGia7.setText("Tên loại:");
        lbGia7.setToolTipText("");

        txtTenLoaiMon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenLoaiMon.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(49, 139, 130));
        jLabel1.setText("Trạng Thái");

        buttonGroup1.add(rdoHet);
        rdoHet.setText("Hết");

        buttonGroup1.add(rdoCon);
        rdoCon.setText("Còn");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbThongtin4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(118, 118, 118))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem3))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbTen3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbMatu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenMon)
                                    .addComponent(txtMaMon)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDonVi))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbGia7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbLoai3))
                                .addGap(13, 18, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbbMaLoaiMon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenLoaiMon, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbGia6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(rdoCon)
                                        .addGap(32, 32, 32)
                                        .addComponent(rdoHet))
                                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbThongtin4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbMatu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaMon, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenMon)
                    .addComponent(lbTen3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbLoai3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbMaLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbGia7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDonVi)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbGia6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoHet)
                    .addComponent(jLabel1)
                    .addComponent(rdoCon))
                .addGap(0, 29, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, 390));

        lbThongtin1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin1.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin1.setText("Quản lý món ăn");
        lbThongtin1.setToolTipText("");
        jPanel9.add(lbThongtin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 510, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setForeground(new java.awt.Color(49, 139, 130));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add_28px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLuu.setForeground(new java.awt.Color(49, 139, 130));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save_28px.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnSua.setForeground(new java.awt.Color(49, 139, 130));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edit_28px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setForeground(new java.awt.Color(49, 139, 130));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setToolTipText("");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa))
        );

        jPanel9.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 464, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbImages.setForeground(new java.awt.Color(49, 139, 130));
        lbImages.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/ios_photos_100px.png"))); // NOI18N
        lbImages.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbImages.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbImages.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lbImages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImagesMouseClicked(evt);
            }
        });
        jPanel6.add(lbImages, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 190, 190));

        jPanel9.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 464, -1, -1));

        jTabbedPane1.addTab("Quản lý món ăn", jPanel9);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        lbThongtin2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin2.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin2.setText("Quản lý loại món ăn");
        lbThongtin2.setToolTipText("");

        jScrollPane1.setForeground(new java.awt.Color(49, 139, 130));

        tbl_LoaiMonAn.setForeground(new java.awt.Color(49, 139, 130));
        tbl_LoaiMonAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã loại món", "Tên Loại Món"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_LoaiMonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_LoaiMonAnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_LoaiMonAn);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 139, 130));
        jLabel2.setText("Mã loại món");

        txt_MaLoaiMon.setEditable(false);
        txt_MaLoaiMon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(49, 139, 130));
        jLabel3.setText("Tên loại món");

        txt_TenLoaiMon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_Them.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Them.setForeground(new java.awt.Color(49, 139, 130));
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add_28px.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Sua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Sua.setForeground(new java.awt.Color(49, 139, 130));
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edit_28px.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Luu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Luu.setForeground(new java.awt.Color(49, 139, 130));
        btn_Luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save_28px.png"))); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        lbThongtin3.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin3.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin3.setText("Món ăn");
        lbThongtin3.setToolTipText("");

        jScrollPane2.setForeground(new java.awt.Color(49, 139, 130));

        tbl_MonAn.setForeground(new java.awt.Color(49, 139, 130));
        tbl_MonAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã món ăn", "Tên món ăn", "Tên loại món"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_MonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_MonAnMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_MonAn);

        btn_XoaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_XoaSP.setForeground(new java.awt.Color(49, 139, 130));
        btn_XoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btn_XoaSP.setText("Xóa sản phẩm trước");
        btn_XoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaSPActionPerformed(evt);
            }
        });

        btn_Xoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Xoa.setForeground(new java.awt.Color(49, 139, 130));
        btn_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btn_Xoa.setText("Xóa nếu rỗng");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Show.setForeground(new java.awt.Color(49, 139, 130));
        btn_Show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/reset_28px.png"))); // NOI18N
        btn_Show.setText("Show Món Ăn");
        btn_Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ShowActionPerformed(evt);
            }
        });

        txt_MMA.setEditable(false);
        txt_MMA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(49, 139, 130));
        jLabel4.setText("Mã món ăn");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_MMA, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(23, 23, 23)
                                .addComponent(txt_MaLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_TenLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(btn_Luu)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_Sua))))
                            .addComponent(btn_Them)
                            .addComponent(btn_Show)
                            .addComponent(btn_XoaSP)
                            .addComponent(btn_Xoa)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbThongtin2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lbThongtin3, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbThongtin2)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(lbThongtin3))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_MaLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_TenLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Them)
                            .addComponent(btn_Sua)
                            .addComponent(btn_Luu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Xoa)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_MMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(btn_Show)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_XoaSP)
                        .addGap(72, 72, 72))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(220, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý loại món ăn", jPanel11);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 810));
        jTabbedPane1.getAccessibleContext().setAccessibleName("Loại món");
    }// </editor-fold>//GEN-END:initComponents

    private void tblThucDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThucDonMouseClicked
        fillData(tblThucDon.getSelectedRow());
    }//GEN-LAST:event_tblThucDonMouseClicked

    private void lbImagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagesMouseClicked
        try {
            JFileChooser jfc = new JFileChooser("C:\\Users\\ASUS\\OneDrive\\Desktop\\PRO1041\\DUAN1\\src\\FoodPNG");
            jfc.showOpenDialog(null);
            File file = jfc.getSelectedFile();
            Image img = ImageIO.read(file);
            strHinhAnh = file.getName();
            lbImages.setText("");
            int width = lbImages.getWidth();
            int height = lbImages.getHeight();
            lbImages.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lbImagesMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Enabled();
        Clear();
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        String mess = monAnService.add(getData());
        JOptionPane.showMessageDialog(this, mess);
        listMonAnResponses = monAnService.getAll();
        showData(listMonAnResponses);
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if(txtMaMon.getText().equals("")){
           JOptionPane.showMessageDialog(this, "chọn mã món để Sửa"); 
           return;
        }
        String mess = monAnService.update(txtMaMon.getText(), getData());
        JOptionPane.showMessageDialog(this, mess);
        listMonAnResponses = monAnService.getAll();
        showData(listMonAnResponses);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if(txtMaMon.getText().equals("")){
           JOptionPane.showMessageDialog(this, "chọn mã món để xóa"); 
           return;
        }
        String mess = monAnService.delete(txtMaMon.getText());
        JOptionPane.showMessageDialog(this, mess);
        listMonAnResponses = monAnService.getAll();
        showData(listMonAnResponses);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void cbbMaLoaiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaLoaiMonActionPerformed
        String sql = "SELECT TenLoaiMon FROM DanhMuc WHERE MaLoaiMon = ?";
        try {
            String maTheLoaiString = (String) cbbMaLoaiMon.getSelectedItem();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maTheLoaiString);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                txtTenLoaiMon.setText(rs.getString("TenLoaiMon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbMaLoaiMonActionPerformed

    private void tbl_LoaiMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LoaiMonAnMouseClicked
        fillData1(tbl_LoaiMonAn.getSelectedRow());
    }//GEN-LAST:event_tbl_LoaiMonAnMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        Enable1();
        Clear1();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ShowActionPerformed
        if(txt_MaLoaiMon.getText().equals("")){
           JOptionPane.showMessageDialog(this, "chọn mã loại món để show"); 
           return;
        }
        String show = txt_MaLoaiMon.getText();
        listShow = monAnService.ShowMA(show);
        showData2(listShow);
    }//GEN-LAST:event_btn_ShowActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
        String mess = danhMucService.add(getData2());
        JOptionPane.showMessageDialog(this, mess);
        listDanhMucResponses = danhMucService.getAll();
        showData1(listDanhMucResponses);
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        if(txt_MaLoaiMon.getText().equals("")){
           JOptionPane.showMessageDialog(this, "chọn mã loại món để sửa"); 
           return;
        }
        String mess = danhMucService.update(txt_MaLoaiMon.getText(), getData2());
        JOptionPane.showMessageDialog(this, mess);
        listDanhMucResponses = danhMucService.getAll();
        showData1(listDanhMucResponses);
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        if(txt_MaLoaiMon.getText().equals("")){
           JOptionPane.showMessageDialog(this, "chọn mã loại món để xóa"); 
           return;
        }
        String mess = danhMucService.delete(txt_MaLoaiMon.getText());
        JOptionPane.showMessageDialog(this, mess);
        listDanhMucResponses = danhMucService.getAll();
        showData1(listDanhMucResponses);
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_XoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaSPActionPerformed
        if(txt_MMA.getText().equals("")){
           JOptionPane.showMessageDialog(this, "chọn mã món ăn để xóa"); 
           return;
        }
        String mess = monAnService.delete(txt_MMA.getText());
        JOptionPane.showMessageDialog(this, mess);
        listShow = monAnService.getAll();
        listMonAnResponses = monAnService.getAll();
        showData2(listShow);
        showData(listMonAnResponses);
    }//GEN-LAST:event_btn_XoaSPActionPerformed

    private void tbl_MonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_MonAnMouseClicked
        int row = tbl_MonAn.getSelectedRow();
        txt_MMA.setText(tbl_MonAn.getValueAt(row, 0).toString());
    }//GEN-LAST:event_tbl_MonAnMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String query = txtTimKiem.getText();
        filter(query);
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem3;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_Show;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_XoaSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbMaLoaiMon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbGia6;
    private javax.swing.JLabel lbGia7;
    private javax.swing.JLabel lbImages;
    private javax.swing.JLabel lbLoai3;
    private javax.swing.JLabel lbMatu3;
    private javax.swing.JLabel lbTen3;
    private javax.swing.JLabel lbThongtin1;
    private javax.swing.JLabel lbThongtin2;
    private javax.swing.JLabel lbThongtin3;
    private javax.swing.JLabel lbThongtin4;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoHet;
    private javax.swing.JTable tblThucDon;
    private javax.swing.JTable tbl_LoaiMonAn;
    private javax.swing.JTable tbl_MonAn;
    private javax.swing.JTextField txtDonVi;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtMaMon;
    private javax.swing.JTextField txtTenLoaiMon;
    private javax.swing.JTextField txtTenMon;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txt_MMA;
    private javax.swing.JTextField txt_MaLoaiMon;
    private javax.swing.JTextField txt_TenLoaiMon;
    // End of variables declaration//GEN-END:variables
}
