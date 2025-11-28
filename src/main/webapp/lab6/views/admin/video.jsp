<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Video - Admin</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
        }
        h2 {
            color: #667eea;
            text-align: center;
            margin-bottom: 30px;
        }
        .admin-badge {
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            color: white;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
            font-weight: bold;
        }
        .user-info {
            background: #e7f3ff;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
        }
        .back-link {
            text-align: center;
            margin-top: 30px;
        }
        .back-link a {
            color: #667eea;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="admin-badge">
            ⚙️ TRANG QUẢN TRỊ - CHỈ ADMIN MỚI TRUY CẬP ĐƯỢC
        </div>
        
        <h2>📹 Quản lý Video</h2>
        
        <div class="user-info">
            <strong>Admin:</strong> ${sessionScope.user.fullname} (${sessionScope.user.id})
            <br>
            <strong>Quyền:</strong> ${sessionScope.user.admin ? '✅ Admin' : '❌ User'}
        </div>
        
        <p style="text-align: center; color: #6c757d;">
            Trang này yêu cầu đăng nhập VÀ có quyền Admin.<br>
            AuthFilter đã kiểm tra và cho phép truy cập.
        </p>
        
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/lab6/page.jsp">← Quay lại trang chủ</a>
        </div>
    </div>
</body>
</html>
