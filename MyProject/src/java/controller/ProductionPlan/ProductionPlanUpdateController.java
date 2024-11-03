/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.ProductionPlan;

import dal.DepartmentDBContext;
import dal.PlanDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.productionplan.Department;
import model.productionplan.Plan;
import model.productionplan.Product;
import java.sql.Date;
import model.productionplan.PlanCampaign;

/**
 *  
 * @author ADMIN
 */
public class ProductionPlanUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PlanDBContext db = new PlanDBContext();
        DepartmentDBContext d = new DepartmentDBContext();
        ProductDBContext pr = new ProductDBContext();
        Plan p = db.get(id);
        if (p != null) {
            request.setAttribute("products", pr.list());
            request.setAttribute("depts", d.list());
            request.setAttribute("plan", p);
            if (p.getCampains() != null) {
                request.setAttribute("planCampaign", p.getCampains());
            }
            request.getRequestDispatcher("../view/productionplan/update.jsp").forward(request, response);
        } else {
            response.sendError(404, "plan does not exist!");
        }
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] pids = request.getParameterValues("prid");
    // Kiểm tra xem pids có bị null không trước khi xử lý
    if (pids == null) {
        pids = new String[0]; // Gán giá trị mảng rỗng để tránh lỗi NullPointerException
    }

    // Read parameters
    String raw_id = request.getParameter("planId");
    String raw_start = request.getParameter("startTime");
    String raw_end = request.getParameter("endTime");
    String raw_did = request.getParameter("did");

    // Validate parameters (do it yourself)
    // Object binding
    Plan p = new Plan();
    p.setId(Integer.parseInt(raw_id));
    p.setStart(Date.valueOf(raw_start));
    p.setEnd(Date.valueOf(raw_end));

    Department d = new Department();
    d.setId(Integer.parseInt(raw_did));
    p.setDept(d);

    p.setCampains(new ArrayList<>());

    for (String pid : pids) {
        Product pr = new Product();
        pr.setId(Integer.parseInt(pid));
        PlanCampaign pc = new PlanCampaign();
        pc.setProduct(pr);
        String raw_quantity = request.getParameter("quantity" + pid);
        String raw_effort = request.getParameter("effort" + pid);

        pc.setQuantity(raw_quantity != null && raw_quantity.length() > 0 ? Integer.parseInt(raw_quantity) : 0);
        pc.setEstimatedeffort(raw_effort != null && raw_effort.length() > 0 ? Float.parseFloat(raw_effort) : 0);
        pc.setPlan(p);

        if (pc.getQuantity() != 0 && pc.getEstimatedeffort() != 0) {
            p.getCampains().add(pc);
        }
    }

    PlanDBContext db = new PlanDBContext();
    db.update(p);

    response.sendRedirect("list");
}
}