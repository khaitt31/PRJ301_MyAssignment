/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.ProductionPlan;

import controller.accesscontrol.BaseRBACController;
import dal.DepartmentDBContext;
import dal.PlanDBContext;
import dal.ProductDBContext;
import dal.ScheduleDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.accesscontrol.User;
import model.productionplan.Plan;
import model.productionplan.ScheduleCampain;

/**
 *
 * @author ADMIN
 */
public class ProductionPlanListController extends BaseRBACController {

   
    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
        PlanDBContext db = new PlanDBContext();
        DepartmentDBContext dbp = new DepartmentDBContext();
        ProductDBContext dbd = new ProductDBContext();
        ScheduleDBContext sdb = new ScheduleDBContext();

        ArrayList<Plan> plans = db.list();
        request.setAttribute("plans", plans);

        request.setAttribute("depts", dbp.list());
        request.setAttribute("products", dbd.list());

        // Fetch schedules
        ArrayList<ScheduleCampain> sches = sdb.list();
        if (!sches.isEmpty()) {
            request.setAttribute("ScheduleCampain", sches);
        } else {
            request.setAttribute("error1", "Schedule not found");
        }
        PlanDBContext db1 = new PlanDBContext();
        // Check if a specific plan ID is provided and fetch plan details if available
        String planIdParam = request.getParameter("id");    
        if (planIdParam != null && !planIdParam.isEmpty()) {
            try {
                int planId = Integer.parseInt(planIdParam);
                Plan selectedPlan = db1.get(planId);
                if (selectedPlan != null) {
                    request.setAttribute("plan", selectedPlan);
                } else {
                    request.setAttribute("error", "Plan not found");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Plan ID");
            }
        }

        request.getRequestDispatcher("../view/productionplan/list.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {

    }

}