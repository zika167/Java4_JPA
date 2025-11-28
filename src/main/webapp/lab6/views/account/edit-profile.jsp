<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa thông tin - Lab 6</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lab6/css/form-style.css">
</head>
<body>
    <div class="form-container">
        <h2>👤 Sửa thông tin cá nhân</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>
        
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/account/edit-profile" method="post">
            <div class="form-group">
                <label>Username:</label>
                <input type="text" value="${sessionScope.user.id}" disabled>
            </div>
            
            <div class="form-group">
                <label for="fullname">Họ và tên:</label>
                <input type="text" id="fullname" name="fullname" required 
                       value="${sessionScope.user.fullname}">
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required 
                       value="${sessionScope.user.email}">
            </div>
            
            <button type="submit" class="btn">Cập nhật</button>
        </form>
        
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/lab6/page.jsp">← Quay lại trang chủ</a>
        </div>
    </div>
</body>
</html>
