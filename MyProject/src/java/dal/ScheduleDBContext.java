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
import model.productionplan.PlanCampaign;
import model.productionplan.Product;
import model.productionplan.ScheduleCampaign;

/**
 *
 * @author ADMIN
 */
public class ScheduleDBContext extends DBContext<ScheduleCampaign> {

    public void insertSchedules(ArrayList<ScheduleCampaign> schedules) {
        String sql = "INSERT INTO [dbo].[ScheduleCampaign] ([canid], [date], [shift], [quantity]) VALUES (?, ?, ?, ?)";
        PreparedStatement stm = null;

        try {
            connection.setAutoCommit(false);

            stm = connection.prepareStatement(sql);
            for (ScheduleCampaign model : schedules) {
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
    public void update(ScheduleCampaign model) {
        String sql_update = "UPDATE [dbo].[ScheduleCampaign]\n"
                + "   SET [canid] = ?\n"
                + "      ,[date] = ?\n"
                + "      ,[shift] = ?\n"
                + "      ,[quantity] = ?\n"
                + " WHERE scid=?";

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
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(ScheduleCampaign model) {

    }

    @Override
    public ArrayList<ScheduleCampaign> list() {
        ArrayList<ScheduleCampaign> sches = new ArrayList<>();
        String sql = "SELECT [scid], [canid], [date], [shift], [quantity] FROM [dbo].[ScheduleCampaign]";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                ScheduleCampaign sche = new ScheduleCampaign();
                sche.setId(rs.getInt("scid"));

                PlanCampaign pc = new PlanCampaign();
                pc.setId(rs.getInt("canid"));
                sche.setCam(pc);

                sche.setDate(rs.getDate("date"));
                sche.setK(rs.getString("shift"));
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
    public ScheduleCampaign get(int id) {
        String sql = "SELECT sc.scid, sc.canid, sc.date, sc.shift, sc.quantity, pl.pid \n"
                + "FROM dbo.ScheduleCampaign sc \n"
                + "INNER JOIN dbo.PlanCampaign pl ON sc.canid = pl.canid \n"
                + "WHERE sc.scid = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                ScheduleCampaign sche = new ScheduleCampaign();
                sche.setId(rs.getInt("scid"));

                PlanCampaign pl = new PlanCampaign();
                pl.setId(rs.getInt("canid"));

                Product p = new Product();
                p.setId(rs.getInt("pid"));
                pl.setProduct(p);

                sche.setCam(pl);
                sche.setDate(rs.getDate("date"));
                sche.setK(rs.getString("shift"));
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
    public void insert(ScheduleCampaign model) {
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
