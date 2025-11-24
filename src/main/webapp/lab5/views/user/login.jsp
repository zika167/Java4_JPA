<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập - Lab 5 Bài 1</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error {
            color: #dc3545;
            background-color: #f8d7da;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 15px;
            border: 1px solid #f5c6cb;
        }
        .success {
            color: #155724;
            background-color: #d4edda;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 15px;
            border: 1px solid #c3e6cb;
        }
        .user-info {
            margin-top: 10px;
        }
        .user-info p {
            margin: 5px 0;
        }
        .logout-btn {
            background-color: #dc3545;
            margin-top: 10px;
        }
        .logout-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="container">
        <div style="background-color: #e7f3ff; padding: 10px; border-radius: 4px; margin-bottom: 20px; text-align: center; border: 1px solid #b3d9ff;">
            <strong>👥 Số lượt khách viếng thăm:</strong> 
            <span style="color: #0066cc; font-size: 18px; font-weight: bold;">${applicationScope.visitors}</span>
        </div>
        
        <h2>Đăng nhập - Lab 5 Bài 1</h2>
        
        <%-- Hiển thị thông tin user nếu đã đăng nhập --%>
        <c:if test="${!empty sessionScope.user}">
            <div class="success">
                <strong>Đăng nhập thành công!</strong>
                <div class="user-info">
                    <p><strong>Username:</strong> ${sessionScope.user.id}</p>
                    <p><strong>Họ và tên:</strong> ${sessionScope.user.fullname}</p>
                    <p><strong>Email:</strong> ${sessionScope.user.email}</p>
                    <p><strong>Quyền:</strong> ${sessionScope.user.admin ? 'Admin' : 'User'}</p>
                </div>
                <form action="${pageContext.request.contextPath}/lab5/logout" method="post">
                    <button type="submit" class="logout-btn">Đăng xuất</button>
                </form>
            </div>
        </c:if>
        
        <%-- Form đăng nhập chỉ hiển thị khi chưa đăng nhập --%>
        <c:if test="${empty sessionScope.user}">
            <%-- Hiển thị thông báo lỗi nếu có --%>
            <c:if test="${!empty errorMessage}">
                <div class="error">
                    ${errorMessage}
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/lab5/login" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required 
                           value="${param.username}">
                </div>
                
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <button type="submit">Đăng nhập</button>
            </form>
        </c:if>
        
        <div style="margin-top: 20px; text-align: center;">
            <a href="${pageContext.request.contextPath}/lab5/index.jsp">← Quay lại trang chủ Lab 5</a>
        </div>
    </div>
</body>
</html>
