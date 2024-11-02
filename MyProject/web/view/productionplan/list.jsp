<%-- 
    Document   : list
    Created on : Nov 1, 2024, 1:31:27 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Production Plan List</title>
        <style>
            /* Reset các thuộc tính mặc định */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                color: #333;
                line-height: 1.6;
            }

            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
                background: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            h1 {
                margin-bottom: 20px;
                text-align: center;
                color: #4a4a4a;
            }

            .alert {
                padding: 10px;
                margin-bottom: 20px;
                border-radius: 5px;
            }

            .alert-danger {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }

            .table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            .table th, .table td {
                padding: 12px 15px;
                text-align: left;
                border: 1px solid #dddddd;
            }

            .table th {
                background-color: #007bff;
                color: white;
            }

            .table tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            .table tr:hover {
                background-color: #e9ecef;
            }

            .table a {
                color: #007bff;
                text-decoration: none;
                font-weight: bold;
            }

            .table a:hover {
                text-decoration: underline;
            }

            /* Responsive */
            @media (max-width: 768px) {
                .container {
                    width: 90%;
                }

                .table th, .table td {
                    padding: 10px;
                }
            }

        </style>
    </head>
    <body>
        <div class="container">
            <h1>Production Plan List</h1>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:if>

            <table class="table">
                <thead>
                    <tr>
                        <th>Plan ID</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Department ID</th>
                        <th>Campaigns</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="plan" items="${plans}">
                        <tr>
                            <td>${plan.id}</td>
                            <td>${plan.start}</td>
                            <td>${plan.end}</td>
                            <td>${plan.dept.id}</td>
                            <td>
                                <c:forEach var="campaign" items="${plan.campains}">
                                    <div>
                                        <strong>Product ID:</strong> ${campaign.product.id} <br>
                                        <strong>Product Name:</strong> ${campaign.product.name} <br>
                                        <strong>Quantity:</strong> ${campaign.quantity} <br>
                                        <strong>Estimated Effort:</strong> ${campaign.estimatedeffort} <br>
                                    </div>
                                </c:forEach>
                            </td>
                            <td>
                                <a href="production-plan?id=${plan.id}">View</a>
                                <a href="update?id=${plan.id}">Edit</a>
                                <form action="delete" method="post" >
                                    <input type="hidden" name="id" value="${plan.id}">
                                    <button type="submit" onclick="return confirm('Are you sure you want to delete this plan?');">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
