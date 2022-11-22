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
import views.ThucDon;


/**
 *
 * @author ASUS
 */
public class ManHinhController2 {

    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> ListItem2 = null;
    public ManHinhController2(JPanel jpnRoot) {
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

    public void setEvent(List<DanhMucBean> ListItem2) {
        this.ListItem2 = ListItem2;
        for (DanhMucBean o : ListItem2) {
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
        for(DanhMucBean o:ListItem2){
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
