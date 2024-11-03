<%-- 
    Document   : deleteemployee
    Created on : Nov 4, 2024, 12:55:16 AM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác Nhận Xóa Nhân Viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: #333;
        }
        .container {
            max-width: 400px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            color: #dc3545;
            margin-bottom: 20px;
        }
        p {
            margin-bottom: 20px;
            font-size: 16px;
        }
        .button-group {
            display: flex;
            gap: 10px;
            justify-content: center;
        }
        .btn {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn-confirm {
            background-color: #dc3545;
            color: #ffffff;
        }
        .btn-confirm:hover {
            background-color: #c82333;
        }
        .btn-cancel {
            background-color: #6c757d;
            color: #ffffff;
        }
        .btn-cancel:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Xác Nhận Xóa</h2>
    <p>Bạn có chắc chắn muốn xóa nhân viên có mã: <strong>${eid}</strong>?</p>
    
    <!-- Form xác nhận xóa nhân viên -->
    <form action="deleteemployee" method="POST" class="button-group">
        <input type="hidden" name="eid" value="${eid}"/>
        <button type="submit" class="btn btn-confirm">Xác Nhận</button>
        <a href="employeelist" class="btn btn-cancel">Hủy</a>
    </form>
</div>
</body>
</html>
