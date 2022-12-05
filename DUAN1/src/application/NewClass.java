/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import views.Login;

/**
 *
 * @author ASUS
 */
public class NewClass {

    public static utilies.clsConnectDB connection = new utilies.clsConnectDB();
    public static void main(String[] args) {
        Login l = new Login();
        l.setVisible(true);
    }
}
