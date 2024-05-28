/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOData;

import DAOImplement.dataTurnamenImplement;
import connection.connector;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dataTurnamen;

/**
 *
 * @author LENOVO
 */
public class dataTurnamenDAO implements dataTurnamenImplement {

    Connection connection;

    final String select = "SELECT * FROM tim";
    final String insert = "INSERT INTO tim (nama_tim, manajer, pelatih, jml_pemain) VALUES (?, ?, ?, ?)";
    final String update = "UPDATE tim SET nama_tim = ?, manajer = ?, pelatih = ?, jml_pemain = ? WHERE id = ?";
    final String delete = "DELETE FROM tim WHERE id = ?";

    public dataTurnamenDAO() {
        connection = connector.connection();
    }

    @Override
    public void insert(dataTurnamen data) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, data.getNama_tim());
            statement.setString(2, data.getManajer());
            statement.setString(3, data.getPelatih());
            statement.setInt(4, data.getJml_pemain());

            statement.executeUpdate();

            // set id
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                data.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataTurnamenDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    Logger.getLogger(dataTurnamenDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    @Override
    public void update(dataTurnamen data) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, data.getNama_tim());
            statement.setString(2, data.getManajer());
            statement.setString(3, data.getPelatih());
            statement.setInt(4, data.getJml_pemain());
            statement.setInt(5, data.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(dataTurnamenDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(dataTurnamenDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(dataTurnamenDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(dataTurnamenDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<dataTurnamen> getAll() {
        List<dataTurnamen> dt = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);

            while (rs.next()) {
                dataTurnamen tim = new dataTurnamen();
                tim.setNama_tim(rs.getString("nama_tim"));
                tim.setManajer(rs.getString("manajer"));
                tim.setPelatih(rs.getString("pelatih"));
                tim.setJml_pemain(rs.getInt("jml_pemain"));
                tim.setId(rs.getInt("id"));
                dt.add(tim);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataTurnamenDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(dataTurnamenDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dt;
    }

}
