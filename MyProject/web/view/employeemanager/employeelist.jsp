<%-- 
    Document   : employeelist
    Created on : Nov 3, 2024, 10:23:01 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh Sách Nhân Viên</title>
    <style>
        /* Reset CSS */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            width: 90%;
            max-width: 1200px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            font-size: 24px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            overflow: hidden;
            border-radius: 8px;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            font-size: 16px;
        }
        th {
            background-color: #007bff;
            color: #ffffff;
            font-weight: 600;
        }
        tr:nth-child(even) {
            background-color: #f4f6f9;
        }
        tr:hover {
            background-color: #e9ecef;
        }
        td {
            color: #555;
        }
        .action-buttons {
            display: flex;
            gap: 10px;
        }
        .action-buttons a {
            text-decoration: none;
            padding: 8px 12px;
            font-size: 14px;
            font-weight: bold;
            border-radius: 5px;
            display: inline-flex;
            align-items: center;
            gap: 5px;
            background-color: #f4f6f9;
            color: #333;
            border: 1px solid #ddd;
            transition: background-color 0.3s ease, color 0.3s ease, transform 0.2s ease;
        }
        .action-buttons a:hover {
            background-color: #007bff;
            color: #ffffff;
            transform: translateY(-2px);
        }
        .button-group {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .button-group a {
            text-decoration: none;
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
            transition: background-color 0.3s ease, transform 0.2s ease;
            display: inline-block;
            text-align: center;
            min-width: 100px;
            color: white;
        }
        .button-group a:hover {
            transform: translateY(-3px);
        }
        .add {
            background-color: #28a745;
        }
        .add:hover {
            background-color: #218838;
        }
        .home {
            background-color: #6c757d;
        }
        .home:hover {
            background-color: #5a6268;
        }
        /* Responsive Design */
        @media (max-width: 768px) {
            th, td {
                font-size: 14px;
            }
            .button-group {
                flex-direction: column;
                align-items: center;
            }
            .button-group a {
                width: 100%;
                margin-bottom: 10px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Danh Sách Nhân Viên</h2>
    
    <!-- Bảng danh sách nhân viên -->
    <table>
        <tr>
            <th>Mã NV</th>
            <th>Tên NV</th>
            <th>Cấp Bậc Lương</th>
            <th>Phòng Ban</th>
            <th>Người Tạo</th>
            <th>Hành Động</th>
        </tr>
        <c:forEach var="emp" items="${employees}">
            <tr>
                <td>${emp.eid}</td>
                <td>${emp.ename}</td>
                <td>${emp.salaryLevel}</td>
                <td>${emp.did}</td>
                <td>${emp.createdBy}</td>
                <td>
                    
                    <div class="action-buttons">
                        <a href="editemployee?id=${emp.eid}" class="edit">
                            Sửa
                        </a>
                        <a href="deleteemployee?id=${emp.eid}" class="delete" onclick="return confirm('Bạn có chắc chắn muốn xóa nhân viên này?');">
                            Xóa
                        </a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="button-group">
        <a href="addemployee" class="add">Thêm Nhân Viên</a>
        <a href="employeemanager.html" class="home">Quay Lại Trang Chủ</a>
    </div>
</div>
</body>
</html>
