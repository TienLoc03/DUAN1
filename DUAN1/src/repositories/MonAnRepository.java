package repositories;

import domainModels.MonAn;
import java.util.ArrayList;
import viewModels.MonAnResponse;

/**
 *
 * @author ASUS
 */
public interface MonAnRepository {

    ArrayList<MonAnResponse> getAll();

    ArrayList<MonAnResponse> ShowMA(String MaLM);

    boolean add(MonAn monAn);

    boolean update(String MaMA, MonAn monAn);

    boolean delete(String MaMA);
}
