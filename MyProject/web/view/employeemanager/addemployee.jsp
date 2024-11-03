<%-- 
    Document   : addEmployee
    Created on : Nov 3, 2024, 11:14:35 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm Nhân Viên</title>
        <style>
            /* Reset và thiết lập chung */
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

            .container {
                width: 100%;
                max-width: 500px;
                padding: 30px;
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
                text-align: center;
            }

            h2 {
                margin-bottom: 25px;
                font-size: 24px;
                color: #333;
            }

            .message {
                color: green;
                margin-bottom: 15px;
                font-size: 14px;
                text-align: center;
            }

            form label {
                font-size: 14px;
                color: #555;
                display: block;
                margin-top: 15px;
                text-align: left;
            }

            form input[type="text"],
            form input[type="number"],
            form select {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 4px;
                font-size: 14px;
            }

            .button-group {
                display: flex;
                gap: 10px;
                margin-top: 20px;
            }

            .btn-submit, .btn-back {
                flex: 1;
                padding: 12px;
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
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Thêm Nhân Viên Mới</h2>

            <!-- Hiển thị thông báo nếu có -->
            <c:if test="${not empty sessionScope.message}">
                <div class="message">
                    ${sessionScope.message}
                </div>
                <c:remove var="message" scope="session"/>
            </c:if>

            <form action="addemployee" method="POST">
                <label for="eid">Mã Nhân Viên:</label>
                <input type="text" id="eid" name="eid" required />
                
                <label for="ename">Tên Nhân Viên:</label>
                <input type="text" id="ename" name="ename" required />
                
                <label for="salaryLevel">Cấp Bậc Lương:</label>
                <input type="text" id="salaryLevel" name="salaryLevel" required />
                
                <label for="did">Phòng Ban:</label>
                <select id="did" name="did" required>
                    <c:forEach items="${departments}" var="dept">
                        <option value="${dept.id}">${dept.name}</option>
                    </c:forEach>
                </select>
                
                <label for="createdBy">Người Tạo:</label>
                <input type="text" id="createdBy" name="createdBy" required />

                <div class="button-group">
                    <input type="submit" class="btn-submit" value="Thêm Nhân Viên"/>
                    <a href="employeelist" class="btn-back">Quay Về Trang Danh Sách</a>
                </div>
            </form>
        </div>
    </body>
</html>
