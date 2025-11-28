<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đổi mật khẩu - Lab 6</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lab6/css/form-style.css">
</head>
<body>
    <div class="form-container">
        <h2>🔒 Đổi mật khẩu</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>
        
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/account/change-password" method="post">
            <div class="form-group">
                <label for="oldPassword">Mật khẩu cũ:</label>
                <input type="password" id="oldPassword" name="oldPassword" required>
            </div>
            
            <div class="form-group">
                <label for="newPassword">Mật khẩu mới:</label>
                <input type="password" id="newPassword" name="newPassword" required>
            </div>
            
            <div class="form-group">
                <label for="confirmPassword">Xác nhận mật khẩu mới:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            
            <button type="submit" class="btn">Đổi mật khẩu</button>
        </form>
        
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/lab6/page.jsp">← Quay lại trang chủ</a>
        </div>
    </div>
</body>
</html>
