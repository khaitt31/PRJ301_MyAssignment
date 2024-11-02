/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.ProductionPlan;

import dal.DepartmentDBContext;
import dal.PlanDBContext;
import dal.ProductDBContext;
import dal.ScheduleDBContext;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.productionplan.Plan;
import model.productionplan.ScheduleCampaign;

/**
 *
 * @author ADMIN
 */
public class ProductionPlanListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDBContext planDB = new PlanDBContext();

        // Fetching plans from the database
        ArrayList<Plan> plans = planDB.list();
        if (plans == null) {
            plans = new ArrayList<>();
        }
        request.setAttribute("plans", plans);

        // Handling selected Plan ID
        String planIdParam = request.getParameter("id");
        if (planIdParam != null && !planIdParam.isEmpty()) {
            try {
                int planId = Integer.parseInt(planIdParam);
                Plan selectedPlan = planDB.get(planId);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
