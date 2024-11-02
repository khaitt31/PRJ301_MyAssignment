<%-- 
    Document   : update
    Created on : Nov 1, 2024, 11:33:25 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Production Plan</title>
    <style>
        /* Styles for form layout and appearance */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        .container {
            width: 60%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #E27F00; /* Title color */
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="date"], select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }
        input[type="text"]:focus, input[type="date"]:focus, select:focus {
            border-color: #E27F00; /* Focus color */
            outline: none;
        }
        .campaign-group {
            margin-top: 20px;
        }
        .campaign-item {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .btn-group {
            text-align: center;
        }
        button {
            padding: 10px 15px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        button.save-btn {
            background-color: #E27F00; /* Save button color */
            color: white;
        }
        button.save-btn:hover {
            background-color: #d16e00; /* Hover color */
        }
        button.cancel-btn {
            background-color: #f44336;
            color: white;
            margin-left: 10px;
        }
        button.cancel-btn:hover {
            background-color: #e73526;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Update Production Plan</h2>
    <form action="update" method="POST">
        <input type="hidden" name="planId" value="${requestScope.plan.id}">
        
        <!-- Plan General Information -->
        <div class="form-group">
            <label for="startTime">Start Date:</label>
            <input type="date" name="startTime" id="startTime" value="${requestScope.plan.start}" required>
        </div>
        <div class="form-group">
            <label for="endTime">End Date:</label>
            <input type="date" name="endTime" id="endTime" value="${requestScope.plan.end}" required>
        </div>

        <!-- Department Selection -->
        <div class="form-group">
            <label for="did">Department:</label>
            <select name="did" id="did" required>
                <c:if test="${empty requestScope.depts}">
                    <option value="">No departments available</option>
                </c:if>
                <c:forEach var="d" items="${requestScope.depts}">
                    <option value="${d.id}" <c:if test="${d.id == requestScope.plan.dept.id}">selected</c:if>>${d.name}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Plan Campaign Information -->
        <div class="campaign-group">
            <h3>Plan Campaigns</h3>
            <c:if test="${empty requestScope.plan.campains}">
                <p>No campaigns available for this plan.</p>
            </c:if>
            <c:forEach var="pc" items="${requestScope.plan.campains}">
                <div class="campaign-item">
                    <div class="form-group">
                        <label>Product:</label>
                        <select name="prid[]">
                            <c:forEach var="pr" items="${requestScope.products}">
                                <option value="${pr.id}" <c:if test="${pr.id == pc.product.id}">selected</c:if>>${pr.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="quantity${pc.product.id}">Quantity:</label>
                        <input type="text" name="quantity${pc.product.id}" id="quantity${pc.product.id}" 
                            value="${pc.quantity}" placeholder="Enter quantity" required>
                    </div>
                    <div class="form-group">
                        <label for="effort${pc.product.id}">Estimated Effort:</label>
                        <input type="text" name="effort${pc.product.id}" id="effort${pc.product.id}" 
                            value="${pc.estimatedeffort}" placeholder="Enter effort" required>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Form Buttons -->
        <div class="btn-group">
            <button type="submit" class="save-btn">Save Changes</button>
            <button type="button" class="cancel-btn" onclick="window.location.href = 'list'">Cancel</button>
        </div>
    </form>
</div>
</body>
</html>
