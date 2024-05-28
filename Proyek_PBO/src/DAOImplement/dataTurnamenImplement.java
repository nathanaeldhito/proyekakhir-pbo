/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOImplement;

import java.util.List;
import model.dataTurnamen;

/**
 *
 * @author LENOVO
 */
public interface dataTurnamenImplement {

    public void insert(dataTurnamen data);

    public void update(dataTurnamen data);

    public void delete(Integer id);

    public List<dataTurnamen> getAll();
}
