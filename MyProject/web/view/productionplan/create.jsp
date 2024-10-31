<%-- 
    Document   : create
    Created on : Oct 21, 2024, 10:01:48 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Production Plan</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productionplan.css">
    </head>
    <body>
        <h2>Create New Production Plan</h2>
        
        <%-- Hiển thị thông báo nếu có trong session --%>
        <c:if test="${not empty sessionScope.message}">
            <div style="color: green;">
                ${sessionScope.message}
            </div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <form action="create" method="POST"> 
            From: <input type="date" name="from" required /> 
            To: <input type="date" name="to" required/>
            <br/>
            Workshop: 
            <select name="did" required>
                <c:forEach items="${depts}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
            <br/>
            <table border="1">
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Estimated Effort</th>
                </tr>
                <c:forEach items="${products}" var="p">
                    <tr>
                        <td>${p.name}<input type="hidden" name="pid" value="${p.id}"/></td>
                        <td><input type="number" name="quantity${p.id}" min="0" required/></td>
                        <td><input type="number" step="0.1" name="effort${p.id}" min="0" required/></td>
                    </tr>   
                </c:forEach>
            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
