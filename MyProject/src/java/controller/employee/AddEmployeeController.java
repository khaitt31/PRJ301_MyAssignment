/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.employee;

import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.employee.Employee;
import model.productionplan.Department;

/**
 *
 * @author ADMIN
 */
public class AddEmployeeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddEmployeeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEmployeeController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DepartmentDBContext departmentDB = new DepartmentDBContext();
        ArrayList<Department> departments = departmentDB.getAllDepartments(); // Hypothetical method to fetch all departments
        request.setAttribute("departments", departments);
        
        request.getRequestDispatcher("../view/employeemanager/addemployee.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eid = request.getParameter("eid");
        String ename = request.getParameter("ename");
        String salaryLevel = request.getParameter("salaryLevel");
        int did = Integer.parseInt(request.getParameter("did"));
        String createdBy = request.getParameter("createdBy");

        // Tạo đối tượng Employee từ dữ liệu form
        Employee employee = new Employee();
        employee.setEid(eid);
        employee.setEname(ename);
        employee.setSalaryLevel(salaryLevel);
        employee.setDid(did);
        employee.setCreatedBy(createdBy);

        // Lưu nhân viên vào cơ sở dữ liệu
        EmployeeDBContext employeeDB = new EmployeeDBContext();
        employeeDB.insert(employee);

        // Đặt thông báo thành công trong session và chuyển hướng về danh sách nhân viên
        request.getSession().setAttribute("message", "Employee added successfully!");
        response.sendRedirect("addemployee"); // Điều chỉnh URL tùy theo cấu trúc dự án
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
