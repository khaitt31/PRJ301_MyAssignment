<%-- 
    Document   : login
    Created on : Oct 15, 2024, 9:16:17 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            /* Đặt toàn bộ trang nền */
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                font-family: Arial, sans-serif;
                background-color: #f0f2f5;
            }

            /* Định dạng cho form đăng nhập */
            .login-container {
                width: 100%;
                max-width: 400px;
                padding: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            .login-container h2 {
                margin-bottom: 20px;
                color: #333;
            }

            .login-container input[type="text"],
            .login-container input[type="password"] {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-sizing: border-box;
                font-size: 16px;
            }

            .login-container input[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .login-container input[type="submit"]:hover {
                background-color: #45a049;
            }

            .login-container .footer-text {
                margin-top: 15px;
                font-size: 14px;
                color: #888;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>Login</h2>
            <form action="login" method="POST">
                <input type="text" name="username" placeholder="Username" required /> <br/>
                <input type="password" name="password" placeholder="Password" required /> <br/>
                <input type="submit" value="Login" />
                
            </form>
        </div>
    </body>
</html>

