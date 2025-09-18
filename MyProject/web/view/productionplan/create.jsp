<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Production Plan</title>

        <style>
            /* Reset and setup */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                color: #333;
            }

            /* Main container */
            .container {
                width: 100%;
                max-width: 600px;
                padding: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            h2 {
                margin-bottom: 20px;
                font-size: 24px;
                color: #333;
            }

            /* Notification message */
            .message {
                color: green;
                margin-bottom: 15px;
                font-size: 14px;
                text-align: center;
            }

            /* Form elements */
            form label {
                font-size: 14px;
                color: #555;
                display: block;
                margin-top: 15px;
                text-align: left;
            }

            form input[type="date"],
            form input[type="text"],
            form input[type="number"],
            form select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
            }

            /* Submit button styling */
            .button-group {
                display: flex;
                gap: 10px;
                margin-top: 20px;
            }
            .btn-submit, .btn-back {
                flex: 1;
                padding: 10px;
                font-size: 16px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-submit {
                background-color: #4CAF50;
                color: #fff;
            }

            .btn-submit:hover {
                background-color: #45a049;
            }

            .btn-back {
                background-color: #6c757d;
                color: #fff;
                text-decoration: none;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .btn-back:hover {
                background-color: #5a6268;
            }

            /* Table styling */
            .product-table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 15px;
            }

            .product-table th, .product-table td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: left;
            }

            .product-table th {
                background-color: #f2f2f2;
                color: #333;
                font-weight: bold;
            }

            .product-table tr:nth-child(even) {
                background-color: #f9f9f9;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Create New Production Plan</h2>

            <%-- Display session message if available --%>
            <c:if test="${not empty sessionScope.message}">
                <div class="message">
                    ${sessionScope.message}
                </div>
                <c:remove var="message" scope="session"/>
            </c:if>

            <form action="create" method="POST">
                <label>From:</label>
                <input type="date" name="from" required />
                
                <label>To:</label>
                <input type="date" name="to" required/>
                
                <label>Workshop:</label>
                <select name="did" required>
                    <c:forEach items="${depts}" var="d">
                        <option value="${d.id}">${d.name}</option>
                    </c:forEach>
                </select>
                
                <table class="product-table">
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Estimated Effort (hours)</th>
                    </tr>
                    <c:forEach items="${products}" var="p">
                        <tr>
                            <td>${p.name}<input type="hidden" name="pid" value="${p.id}"/></td>
                            <td><input type="number" name="quantity${p.id}" min="0" /></td>
                            <td><input type="number" step="0.1" name="effort${p.id}" min="0" /></td>
                        </tr>   
                    </c:forEach>
                </table>

                <div class="button-group">
                    <input type="submit" class="btn-submit" value="Save"/>
                    <a href="list" class="btn-back">Quay Về Trang Danh Sách</a>
                </div>
            </form>
        </div>
    </body>
</html>
