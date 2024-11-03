/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import model.employee.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class EmployeeDBContext extends DBContext<Employee> {

    @Override
    public void insert(Employee model) {
        String sql = "INSERT INTO [Employee] ([eid], [ename], [salaryLevel], [did], [createdby]) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, model.getEid());
            stm.setString(2, model.getEname());
            stm.setString(3, model.getSalaryLevel());
            stm.setInt(4, model.getDid());
            stm.setString(5, model.getCreatedBy());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Phương thức cập nhật thông tin một nhân viên
    @Override
    public void update(Employee model) {
        String sql = "UPDATE [Employee] SET [ename] = ?, [salaryLevel] = ?, [did] = ?, [createdby] = ? WHERE [eid] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, model.getEname());
            stm.setString(2, model.getSalaryLevel());
            stm.setInt(3, model.getDid());
            stm.setString(4, model.getCreatedBy());
            stm.setString(5, model.getEid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Phương thức xóa một nhân viên
    @Override
    public void delete(Employee model) {
        String sql = "DELETE FROM [Employee] WHERE [eid] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, model.getEid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Phương thức lấy danh sách tất cả các nhân viên
    @Override
    public ArrayList<Employee> list() {
        ArrayList<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM [Employee]";
        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setEid(rs.getString("eid"));
                e.setEname(rs.getString("ename"));
                e.setSalaryLevel(rs.getString("salaryLevel"));
                e.setDid(rs.getInt("did"));
                e.setCreatedBy(rs.getString("createdby"));
                employees.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    // Phương thức lấy thông tin một nhân viên theo ID
    @Override
    public Employee get(int id) {
        String sql = "SELECT * FROM [Employee] WHERE [eid] = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Employee e = new Employee();
                    e.setEid(rs.getString("eid"));
                    e.setEname(rs.getString("ename"));
                    e.setSalaryLevel(rs.getString("salaryLevel"));
                    e.setDid(rs.getInt("did"));
                    e.setCreatedBy(rs.getString("createdby"));
                    return e;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Employee getEmployeeById(String eid) {
        Employee employee = null;
        String sql = "SELECT * FROM Employee WHERE eid = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, eid);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee();
                    employee.setEid(rs.getString("eid"));
                    employee.setEname(rs.getString("ename"));
                    employee.setSalaryLevel(rs.getString("salaryLevel"));
                    employee.setDid(rs.getInt("did"));
                    employee.setCreatedBy(rs.getString("createdby"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void delete(String eid) {
        String sql = "DELETE FROM Employee WHERE eid = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, eid);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
