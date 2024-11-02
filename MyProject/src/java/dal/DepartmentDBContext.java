/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.util.ArrayList;
import model.productionplan.Department;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt-local
 */
public class DepartmentDBContext extends DBContext<Department> {

    public ArrayList<Department> get(String type) {
        ArrayList<Department> depts = new ArrayList<>();

        String sql = "SELECT [did]\n"
                + "      ,[dname]\n"
                + "      ,[dtype]\n"
                + "  FROM [Pro2].[dbo].[Department]\n"
                + "  WHERE dtype = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, type);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                d.setType(rs.getString("dtype"));
                depts.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return depts;
    }

    @Override
    public void insert(Department model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Department model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Department model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Department> list() {
        ArrayList<Department> departments = new ArrayList<>();
        String sql = "SELECT did, dname, dtype FROM [dbo].[Department]"; // Câu lệnh SQL để lấy danh sách phòng ban

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Department dept = new Department();
                dept.setId(rs.getInt("did")); // Gán ID
                dept.setName(rs.getString("dname")); // Gán tên
                dept.setType(rs.getString("dtype"));
                // Gán các thuộc tính khác nếu cần
                departments.add(dept);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departments;
    }


    @Override
    public Department get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
