/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.productionplan.Plan;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import model.productionplan.Department;
import model.productionplan.PlanCampaign;
import model.productionplan.Product;

;

/**
 *
 * @author sonnt-local
 */
public class PlanDBContext extends DBContext<Plan> {

    @Override
    public void insert(Plan model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_plan = "INSERT INTO [Plan]\n"
                    + "           ([startd]\n"
                    + "           ,[endd]\n"
                    + "           ,[did])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            String sql_select_plan = "SELECT @@IDENTITY as plid";
            String sql_insert_campain = "INSERT INTO [PlanCampaign]\n"
                    + "           ([plid]\n"
                    + "           ,[pid]\n"
                    + "           ,[quantity]\n"
                    + "           ,[estimatedeffort])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setDate(1, model.getStart());
            stm_insert_plan.setDate(2, model.getEnd());
            stm_insert_plan.setInt(3, model.getDept().getId());
            stm_insert_plan.executeUpdate();

            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("plid"));
            }
            for (PlanCampaign campain : model.getCampains()) {
                PreparedStatement stm_insert_campain = connection.prepareStatement(sql_insert_campain);
                stm_insert_campain.setInt(1, model.getId());
                stm_insert_campain.setInt(2, campain.getProduct().getId());
                stm_insert_campain.setInt(3, campain.getQuantity());
                stm_insert_campain.setFloat(4, campain.getEstimatedeffort());
                stm_insert_campain.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

     @Override
    public void update(Plan model) {
        try {
            connection.setAutoCommit(false);

            // Cập nhật bảng Plan
            String sql_update_plan = "UPDATE [Plan] SET [startd] = ?, [endd] = ?, [did] = ? WHERE [plid] = ?";
            PreparedStatement stm_update_plan = connection.prepareStatement(sql_update_plan);
            stm_update_plan.setDate(1, model.getStart());
            stm_update_plan.setDate(2, model.getEnd());
            stm_update_plan.setInt(3, model.getDept().getId());
            stm_update_plan.setInt(4, model.getId());
            stm_update_plan.executeUpdate();

            // Xóa các PlanCampaign cũ liên quan đến Plan
            String sql_delete_campaigns = "DELETE FROM [PlanCampaign] WHERE [plid] = ?";
            PreparedStatement stm_delete_campaigns = connection.prepareStatement(sql_delete_campaigns);
            stm_delete_campaigns.setInt(1, model.getId());
            stm_delete_campaigns.executeUpdate();

            // Thêm các PlanCampaign mới
            String sql_insert_campaign = "INSERT INTO [PlanCampaign] ([plid], [pid], [quantity], [estimatedeffort]) VALUES (?, ?, ?, ?)";
            PreparedStatement stm_insert_campaign = connection.prepareStatement(sql_insert_campaign);
            for (PlanCampaign campaign : model.getCampains()) {
                stm_insert_campaign.setInt(1, model.getId());
                stm_insert_campaign.setInt(2, campaign.getProduct().getId());
                stm_insert_campaign.setInt(3, campaign.getQuantity());
                stm_insert_campaign.setFloat(4, campaign.getEstimatedeffort());
                stm_insert_campaign.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    @Override
    public void delete(Plan model) {
        String sqlDeletePlan = "DELETE FROM [Plan] WHERE [plid] = ?";
        try (PreparedStatement stmDeletePlan = connection.prepareStatement(sqlDeletePlan)) {
            stmDeletePlan.setInt(1, model.getId());
            stmDeletePlan.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Plan> list() {
        ArrayList<Plan> plans = new ArrayList<>();
        String sql = "SELECT p.plid, p.startd, p.endd, p.did, pc.pid, pc.quantity, pc.estimatedeffort, pr.pname\n"
                + "FROM [dbo].[Plan] p\n"
                + "LEFT JOIN PlanCampaign pc ON p.plid = pc.plid\n"
                + "LEFT JOIN Product pr ON pc.pid = pr.pid;";
        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            Plan currentPlan = null;
            int currentPlanId = -1;
            while (rs.next()) {
                int planId = rs.getInt("plid");
                if (planId != currentPlanId) {
                    currentPlan = new Plan();
                    currentPlan.setId(planId);
                    currentPlan.setStart(rs.getDate("startd"));
                    currentPlan.setEnd(rs.getDate("endd"));

                    Department dept = new Department();
                    dept.setId(rs.getInt("did"));
                    currentPlan.setDept(dept);

                    currentPlan.setCampains(new ArrayList<>());
                    plans.add(currentPlan);
                    currentPlanId = planId;
                }

                if (rs.getInt("pid") != 0) {
                    PlanCampaign campain = new PlanCampaign();
                    Product product = new Product();
                    product.setId(rs.getInt("pid"));
                    product.setName(rs.getString("pname"));

                    campain.setProduct(product);
                    campain.setQuantity(rs.getInt("quantity"));
                    campain.setEstimatedeffort(rs.getFloat("estimatedeffort"));

                    currentPlan.getCampains().add(campain);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plans;
    }

    @Override
    public Plan get(int id) {
        String sql = "SELECT p.plid, p.startd, p.endd, p.did, pc.pid, pc.quantity, pc.estimatedeffort, pr.pname\n"
                + "FROM [dbo].[Plan] p\n"
                + "LEFT JOIN PlanCampaign pc ON p.plid = pc.plid\n"
                + "LEFT JOIN Product pr ON pc.pid = pr.pid\n"
                + "WHERE p.plid = ?;";
        Plan plan = null;
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (plan == null) {
                    plan = new Plan();
                    plan.setId(rs.getInt("plid"));
                    plan.setStart(rs.getDate("startd"));
                    plan.setEnd(rs.getDate("endd"));

                    Department dept = new Department();
                    dept.setId(rs.getInt("did"));
                    plan.setDept(dept);

                    plan.setCampains(new ArrayList<>());
                }

                if (rs.getInt("pid") != 0) {
                    PlanCampaign campain = new PlanCampaign();
                    Product product = new Product();
                    product.setId(rs.getInt("pid"));
                    product.setName(rs.getString("pname"));

                    campain.setProduct(product);
                    campain.setQuantity(rs.getInt("quantity"));
                    campain.setEstimatedeffort(rs.getFloat("estimatedeffort"));

                    plan.getCampains().add(campain);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plan;
    }
}
