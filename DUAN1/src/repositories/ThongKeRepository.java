/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import java.util.ArrayList;
import viewModels.ThongKeSPResponse;
import viewModels.ThongKeSPResponse2;

/**
 *
 * @author ASUS
 */
public interface ThongKeRepository {
    ArrayList<ThongKeSPResponse> getAll();
    ArrayList<ThongKeSPResponse2> getAll2();
}
