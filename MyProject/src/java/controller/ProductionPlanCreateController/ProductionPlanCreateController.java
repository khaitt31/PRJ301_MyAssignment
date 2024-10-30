/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.ProductionPlanCreateController;

import dal.DepartmentDBContext;
import dal.PlanDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.crypto.interfaces.PBEKey;
import model.productionplan.Plan;
import model.productionplan.PlanCampain;
import model.productionplan.Product;
import java.sql.Date;
import java.util.ArrayList;
import model.productionplan.Department;

/**
 *
 * @author ADMIN
 */
public class ProductionPlanCreateController extends HttpServlet {

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
        ProductDBContext dbProduct = new ProductDBContext();
        DepartmentDBContext dbDept = new DepartmentDBContext();
        request.setAttribute("products", dbProduct.list());
        request.setAttribute("depts", dbDept.get("workshop"));
        request.getRequestDispatcher("../view/productionplan/create.jsp").forward(request, response);
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
        String[] pids = request.getParameterValues("pid");

        Plan plan = new Plan();
        plan.setStart(Date.valueOf(request.getParameter("from")));
        plan.setEnd(Date.valueOf(request.getParameter("to")));

        Department d = new Department();
        d.setId(Integer.parseInt(request.getParameter("did")));

        plan.setDept(d);
        plan.setCampains(new ArrayList<>());

        for (String pid : pids) {
            Product p = new Product();
            p.setId(Integer.parseInt(pid));

            PlanCampain c = new PlanCampain();
            c.setProduct(p);
            String raw_quantity = request.getParameter("quantity" + pid);
            String raw_effort = request.getParameter("effort" + pid);
            c.setQuantity(raw_quantity != null && raw_quantity.length() > 0 ? Integer.parseInt(raw_quantity) : 0);
            c.setEstimatedeffort(raw_effort != null && raw_effort.length() > 0 ? Float.parseFloat(raw_effort) : 0);
            c.setPlan(plan);
            if (c.getQuantity() != 0 && c.getEstimatedeffort() != 0) {
                plan.getCampains().add(c);
            }
        }

        if (plan.getCampains().size() > 0) {
            PlanDBContext db = new PlanDBContext();
            db.insert(plan);
            request.setAttribute("message", "Created a new plan!");
        } else {
            request.setAttribute("message", "Your plan did not have any campains");
        }
        request.getRequestDispatcher("../view/productionplan/create.jsp").forward(request, response);
    }

}
