<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lab 6 - Video Management System</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            /*background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);*/
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            max-width: 1200px;
            margin: auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            overflow: hidden;
        }
        .header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }
        .header h1 {
            font-size: 36px;
            margin-bottom: 10px;
        }
        .header p {
            font-size: 18px;
            opacity: 0.9;
        }
        .nav {
            background: #f8f9fa;
            padding: 15px 30px;
            border-bottom: 2px solid #e9ecef;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
        }
        .nav-links {
            display: flex;
            gap: 15px;
            flex-wrap: wrap;
        }
        .nav-links a {
            color: #495057;
            text-decoration: none;
            padding: 8px 16px;
            border-radius: 5px;
            transition: all 0.3s;
        }
        .nav-links a:hover {
            background: #667eea;
            color: white;
        }
        .user-info {
            color: #495057;
            font-weight: bold;
        }
        .content {
            padding: 40px;
            min-height: 400px;
        }
        .welcome-box {
            text-align: center;
            padding: 60px 20px;
        }
        .welcome-box h2 {
            color: #667eea;
            font-size: 32px;
            margin-bottom: 20px;
        }
        .welcome-box p {
            color: #6c757d;
            font-size: 18px;
            margin-bottom: 30px;
        }
        .feature-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 40px;
        }
        .feature-card {
            background: #f8f9fa;
            padding: 30px;
            border-radius: 10px;
            text-align: center;
            transition: transform 0.3s;
        }
        .feature-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        .feature-card h3 {
            color: #667eea;
            margin-bottom: 15px;
        }
        .feature-card p {
            color: #6c757d;
        }
        .btn {
            display: inline-block;
            padding: 12px 30px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 25px;
            transition: all 0.3s;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        .footer {
            background: #f8f9fa;
            padding: 20px;
            text-align: center;
            color: #6c757d;
            border-top: 2px solid #e9ecef;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🎬 Video Management System</h1>
            <p>Lab 6 - Quản lý Video & Tài khoản</p>
        </div>

        <div class="nav">
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/lab6/page.jsp">🏠 Trang chủ</a>

                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <a href="${pageContext.request.contextPath}/account/sign-up">📝 Đăng ký</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/video/list">📹 Danh sách Video</a>
                        <a href="${pageContext.request.contextPath}/account/edit-profile">👤 Sửa thông tin</a>
                        <a href="${pageContext.request.contextPath}/account/change-password">🔒 Đổi mật khẩu</a>

                        <c:if test="${sessionScope.user.admin}">
                            <a href="${pageContext.request.contextPath}/admin/video">⚙️ Quản lý Video</a>
                            <a href="${pageContext.request.contextPath}/admin/user">👥 Quản lý User</a>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="user-info">
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <a href="${pageContext.request.contextPath}/auth/login" style="color: #495057; text-decoration: none;">
                            🔐 Đăng nhập
                        </a>
                    </c:when>
                    <c:otherwise>
                        <span>👋 ${sessionScope.user.fullname}</span>
                        <a href="${pageContext.request.contextPath}/auth/logout" 
                           style="color: #dc3545; text-decoration: none; margin-left: 15px;">
                            🚪 Đăng xuất
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="content">
            <div class="welcome-box">
                <h2>Xin chào! Chào mừng đến với Lab 6</h2>
                <p>Hệ thống quản lý video với đầy đủ tính năng</p>

                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <p style="margin-top: 20px;">
                            <a href="${pageContext.request.contextPath}/account/sign-up" class="btn">
                                Đăng ký ngay
                            </a>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <div class="feature-grid">
                            <div class="feature-card">
                                <h3>📹 Xem Video</h3>
                                <p>Danh sách video đầy đủ</p>
                                <a href="${pageContext.request.contextPath}/video/list" class="btn" style="margin-top: 15px;">
                                    Xem ngay
                                </a>
                            </div>

                            <div class="feature-card">
                                <h3>👤 Tài khoản</h3>
                                <p>Quản lý thông tin cá nhân</p>
                                <a href="${pageContext.request.contextPath}/account/edit-profile" class="btn" style="margin-top: 15px;">
                                    Cập nhật
                                </a>
                            </div>

                            <c:if test="${sessionScope.user.admin}">
                                <div class="feature-card">
                                    <h3>⚙️ Admin</h3>
                                    <p>Quản trị hệ thống</p>
                                    <a href="${pageContext.request.contextPath}/admin/video" class="btn" style="margin-top: 15px;">
                                        Quản lý
                                    </a>
                                </div>
                            </c:if>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="footer">
            <p>&copy; 2025 Lab 6 - Video Management System. All rights reserved.</p>
        </div>
    </div>
</body>
</html>
