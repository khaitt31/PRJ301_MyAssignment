/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.productionplan.PlanCampain;
import model.productionplan.Product;
import model.productionplan.ScheduleCampain;


/**
 *
 * @author ADMIN
 */
public class ScheduleDBContext extends DBContext<ScheduleCampain> {

    public void insertSchedules(ArrayList<ScheduleCampain> schedules) {
        String sql = "INSERT INTO [dbo].[ScheduleCampaign] ([camid], [date], [K], [quantity]) VALUES (?, ?, ?, ?)";
        PreparedStatement stm = null;

        try {
            connection.setAutoCommit(false);

            stm = connection.prepareStatement(sql);
            for (ScheduleCampain model : schedules) {
                stm.setInt(1, model.getCam().getId());
                stm.setDate(2, model.getDate());
                stm.setString(3, model.getK());
                stm.setInt(4, model.getQuantity());
                stm.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            closeResources(stm, connection);
        }
    }

    @Override
    public void update(ScheduleCampain model) {
        String sql_update = "UPDATE [dbo].[ScheduleCampaign] SET [camid] = ?, [date] = ?, [K] = ?, [quantity] = ? WHERE scid=?";
        PreparedStatement stm_update = null;
        try {
            stm_update = connection.prepareStatement(sql_update);
            stm_update.setInt(1, model.getCam().getId());
            stm_update.setDate(2, model.getDate());
            stm_update.setString(3, model.getK());
            stm_update.setInt(4, model.getQuantity());
            stm_update.setInt(5, model.getId());
            stm_update.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(stm_update, connection);
        }
    }

    @Override
    public void delete(ScheduleCampain model) {
        String sql_update = "DELETE FROM [dbo].[ScheduleCampaign] WHERE scid=?";
        PreparedStatement stm_update = null;
        try {
            stm_update = connection.prepareStatement(sql_update);
            stm_update.setInt(1, model.getId());
            stm_update.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(stm_update, connection);
        }
    }

    @Override
    public ArrayList<ScheduleCampain> list() {
        ArrayList<ScheduleCampain> sches = new ArrayList<>();
        String sql = "SELECT [scid], [camid], [date], [K], [quantity] FROM [dbo].[ScheduleCampaign]";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                ScheduleCampain sche = new ScheduleCampain();
                sche.setId(rs.getInt("scid"));

                PlanCampain pc = new PlanCampain();
                pc.setId(rs.getInt("camid"));
                sche.setCam(pc);

                sche.setDate(rs.getDate("date"));
                sche.setK(rs.getString("K"));
                sche.setQuantity(rs.getInt("quantity"));

                sches.add(sche);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(rs, stm, connection);
        }

        return sches;
    }

    @Override
    public ScheduleCampain get(int id) {
        String sql = "SELECT sc.[scid], sc.[camid], sc.[date], sc.[K], sc.[quantity], pl.[pid] FROM [dbo].[ScheduleCampaign] sc INNER JOIN [dbo].[PlanCampaign] pl ON sc.[camid] = pl.[camid] WHERE sc.[scid] = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                ScheduleCampain sche = new ScheduleCampain();
                sche.setId(rs.getInt("scid"));

                PlanCampain pl = new PlanCampain();
                pl.setId(rs.getInt("camid"));

                Product p = new Product();
                p.setId(rs.getInt("pid"));
                pl.setProduct(p);

                sche.setCam(pl);
                sche.setDate(rs.getDate("date"));
                sche.setK(rs.getString("K"));
                sche.setQuantity(rs.getInt("quantity"));
                return sche;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(rs, stm, connection);
        }
        return null;
    }

    @Override
    public void insert(ScheduleCampain model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void closeResources(AutoCloseable... resources) {
        for (AutoCloseable res : resources) {
            if (res != null) {
                try {
                    res.close();
                } catch (Exception ex) {
                    Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}