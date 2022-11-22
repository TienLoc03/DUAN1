/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import bean.DanhMucBean;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import views.TrangChu;
import javax.swing.JLabel;
import javax.swing.JPanel;
import views.DSBan;
import views.DatBan;
import views.DoanhThu;
import views.HoaDon;
import views.KhuyenMai;
import views.QLNV;
import views.ThucDon;


/**
 *
 * @author ASUS
 */
public class ManHinhController {

    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> ListItem = null;
    public ManHinhController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChu";
//        jpnItem.setBackground(new Color(96, 100, 191));
//        jlbItem.setBackground(new Color(96, 100, 191));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChu());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMucBean> ListItem) {
        this.ListItem = ListItem;
        for (DanhMucBean o : ListItem) {
            o.getJlb().addMouseListener(new LabelEvent(o.getKind(), o.getJpn(), o.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChu();
                    break;
                case"ThucDon":
                    node = new ThucDon();
                    break;
                case"DatBan":
                    node = new DatBan();
                    break;
                case"HoaDon":
                    node = new HoaDon();
                    break;
                case"DoanhThu":
                    node = new DoanhThu();
                    break;
                case"QLNV":
                    node = new QLNV(); 
                    break;
                case"DSBan":
                    node = new DSBan();
                    break;
                case"KhuyenMai":
                    node = new KhuyenMai();
                    break;
                // more
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!kindSelected.equals(kind)){
                jpnItem.setBackground(new Color(51,51,51));
            jlbItem.setBackground(new Color(51,51,51));
            }               
        }
    }
    private void setChangeBackground(String kind){
        for(DanhMucBean o:ListItem){
            if(o.getKind().equalsIgnoreCase(kind)){
                o.getJpn().setBackground(new Color(96, 100, 191));
                o.getJlb().setBackground(new Color(96, 100, 191));
            }else{
                o.getJpn().setBackground(new Color(51,51,51));
                o.getJlb().setBackground(new Color(51,51,51));
            }
        }
    }
}
