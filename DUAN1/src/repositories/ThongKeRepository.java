/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import java.util.ArrayList;
import viewModels.ThongKeSPResponse;

/**
 *
 * @author ASUS
 */
public interface ThongKeRepository {
    ArrayList<ThongKeSPResponse> getAll();
}
