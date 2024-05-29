/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAOData.dataTurnamenDAO;
import DAOImplement.dataTurnamenImplement;
import java.util.List;
import javax.swing.JOptionPane;
import model.dataTurnamen;
import model.modelTabelDataTurnamen;
import view.mainView;

/**
 *
 * @author LENOVO
 */
public class dataTurnamenController {

    mainView frame;
    dataTurnamenImplement implDataTurnamen;
    List<dataTurnamen> dt;

    private Integer selectedId;
    private boolean insertError;
    private boolean updateError;
    private boolean deleteError;

    public dataTurnamenController(mainView frame) {
        this.frame = frame;
        implDataTurnamen = new dataTurnamenDAO();
        dt = implDataTurnamen.getAll();
    }

    // cek validasi form
    private boolean isFormValid() {
        if (frame.getTxtNama().getText().isEmpty() || frame.getTxtManajer().getText().isEmpty() || frame.getTxtPelatih().getText().isEmpty() || frame.getTxtJPemain().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mohon isi data", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            int jPemain = Integer.parseInt(frame.getTxtJPemain().getText());
            if (jPemain < 1 || jPemain > 25) {
                JOptionPane.showMessageDialog(null, "Mohon input sesuai ketentuan", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Mohon input jumlah pemain dengan angka yang valid", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public void isiTabel() {
        dt = implDataTurnamen.getAll();
        modelTabelDataTurnamen mb = new modelTabelDataTurnamen(dt);
        frame.getTabelData().setModel(mb);
    }

    public void insert() {
        if (isFormValid()) {
            try {
                dataTurnamen data = new dataTurnamen();
                data.setNama_tim(frame.getTxtNama().getText());
                data.setManajer(frame.getTxtManajer().getText());
                data.setPelatih(frame.getTxtPelatih().getText());
                data.setJml_pemain(Integer.parseInt(frame.getTxtJPemain().getText()));

                implDataTurnamen.insert(data);
                frame.clearForm();
                insertError = false;
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Mohon isi data", "Warning", JOptionPane.WARNING_MESSAGE);
                insertError = true;
            }
        } else {
            insertError = true;
        }
    }

    public void update() {
        if (isFormValid()) {
            try {
                dataTurnamen data = new dataTurnamen();
                data.setId(selectedId);
                data.setNama_tim(frame.getTxtNama().getText());
                data.setManajer(frame.getTxtManajer().getText());
                data.setPelatih(frame.getTxtPelatih().getText());
                data.setJml_pemain(Integer.parseInt(frame.getTxtJPemain().getText()));

                implDataTurnamen.update(data);
                frame.clearForm();
                updateError = false;
                JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Mohon isi data", "Warning", JOptionPane.WARNING_MESSAGE);
                updateError = true;
            }
        } else {
            updateError = true;
        }
    }

    public void delete() {
        if (isFormValid()) {
            try {
                implDataTurnamen.delete(selectedId);
                frame.clearForm();
                isiTabel();
                deleteError = false;
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Mohon isi data", "Warning", JOptionPane.WARNING_MESSAGE);
                deleteError = true;
            }
        } else {
            deleteError = true;
        }
    }

    public void selectRow(int rowIndex) {
        if (rowIndex != -1 && rowIndex < dt.size()) {
            dataTurnamen data = dt.get(rowIndex);
            selectedId = data.getId();
            frame.getTxtNama().setText(data.getNama_tim());
            frame.getTxtManajer().setText(data.getManajer());
            frame.getTxtPelatih().setText(String.valueOf(data.getPelatih()));
            frame.getTxtJPemain().setText(String.valueOf(data.getJml_pemain()));
        }
    }

    public boolean isInsertError() {
        return insertError;
    }

    public boolean isUpdateError() {
        return updateError;
    }

    public boolean isDeleteError() {
        return deleteError;
    }
}
