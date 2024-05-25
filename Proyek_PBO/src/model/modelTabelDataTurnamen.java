/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Loq Gaming
 */
public class modelTabelDataTurnamen extends AbstractTableModel {
    List<dataTurnamen> dt;

    public modelTabelDataTurnamen(List<dataTurnamen> dt) {
        this.dt = dt;
    }
    
    @Override
    public int getRowCount() {
        return dt.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "Nama Tim";
            case 1:
                return "Manajer";
            case 2:
                return "Pelatih";
            case 3:
                return "Jumlah Pemain";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return dt.get(rowIndex).getNama_tim();
            case 1:
                return dt.get(rowIndex).getManajer();
            case 2:
                return dt.get(rowIndex).getPelatih();
            case 3:
                return dt.get(rowIndex).getJml_pemain();
            default:
                return null;
        }
    }
    
}
