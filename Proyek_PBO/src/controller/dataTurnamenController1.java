/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAOData.dataTurnamenDAO;
import DAOImplement.dataTurnamenImplement;
import java.util.List;
import model.dataTurnamen;
import model.modelTabelDataTurnamen;
import view.userView;

/**
 *
 * @author LENOVO
 */
public class dataTurnamenController1 {
    userView frame1;
    dataTurnamenImplement implDataTurnamen;
    List<dataTurnamen> dt;
    
    public dataTurnamenController1(userView frame1) {
        this.frame1 = frame1;
        implDataTurnamen = new dataTurnamenDAO();
        dt = implDataTurnamen.getAll();
    }

    public void isiTabel() {
        dt = implDataTurnamen.getAll();
        modelTabelDataTurnamen mb = new modelTabelDataTurnamen(dt);
        frame1.getjTable1().setModel(mb);
    }
}
