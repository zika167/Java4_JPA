<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle} - Admin</title>
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
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            overflow: hidden;
        }
        .admin-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .admin-header h1 {
            font-size: 24px;
        }
        .admin-info {
            font-size: 14px;
        }
        .nav-tabs {
            display: flex;
            background: #f8f9fa;
            border-bottom: 2px solid #e9ecef;
        }
        .nav-tab {
            flex: 1;
            padding: 15px;
            text-align: center;
            text-decoration: none;
            color: #495057;
            transition: all 0.3s;
            border-bottom: 3px solid transparent;
        }
        .nav-tab:hover {
            background: #e9ecef;
        }
        .nav-tab.active {
            background: white;
            color: #667eea;
            border-bottom-color: #667eea;
            font-weight: bold;
        }
        .content {
            padding: 40px;
            min-height: 400px;
        }
        .welcome-box {
            background: #e7f3ff;
            padding: 30px;
            border-radius: 10px;
            text-align: center;
            margin-bottom: 30px;
        }
        .welcome-box h2 {
            color: #667eea;
            margin-bottom: 15px;
        }
        .admin-badge {
            display: inline-block;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 8px 20px;
            border-radius: 20px;
            font-weight: bold;
            margin-top: 10px;
        }
        .info-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }
        .info-card {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
        }
        .info-card h3 {
            color: #667eea;
            margin-bottom: 10px;
        }
        .back-link {
            text-align: center;
            margin-top: 30px;
        }
        .back-link a {
            color: #667eea;
            text-decoration: none;
            font-size: 16px;
        }
        .back-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="admin-header">
            <h1>⚙️ TRANG QUẢN TRỊ</h1>
            <div class="admin-info">
                <strong>Admin:</strong> ${sessionScope.user.fullname} (${sessionScope.user.id})
            </div>
        </div>
        
        <div class="nav-tabs">
            <a href="${pageContext.request.contextPath}/admin/video" 
               class="nav-tab ${currentUri.contains('/admin/video') ? 'active' : ''}">
                📹 Quản lý Video
            </a>
            <a href="${pageContext.request.contextPath}/admin/user" 
               class="nav-tab ${currentUri.contains('/admin/user') ? 'active' : ''}">
                👥 Quản lý User
            </a>
            <a href="${pageContext.request.contextPath}/admin/like" 
               class="nav-tab ${currentUri.contains('/admin/like') ? 'active' : ''}">
                ❤️ Quản lý Favorite
            </a>
            <a href="${pageContext.request.contextPath}/admin/share" 
               class="nav-tab ${currentUri.contains('/admin/share') ? 'active' : ''}">
                📤 Quản lý Share
            </a>
        </div>
        
        <div class="content">
            <div class="welcome-box">
                <h2>${pageIcon} ${pageTitle}</h2>
                <p style="color: #6c757d; margin-top: 10px;">
                    Trang này yêu cầu đăng nhập VÀ có quyền Admin
                </p>
                <div class="admin-badge">
                    ✅ Bạn đã đăng nhập với quyền Admin
                </div>
            </div>
            
<%--            <div class="info-grid">--%>
<%--                <div class="info-card">--%>
<%--                    <h3>👤 Thông tin Admin</h3>--%>
<%--                    <p><strong>Username:</strong> ${sessionScope.user.id}</p>--%>
<%--                    <p><strong>Họ tên:</strong> ${sessionScope.user.fullname}</p>--%>
<%--                    <p><strong>Email:</strong> ${sessionScope.user.email}</p>--%>
<%--                </div>--%>
<%--                --%>
<%--                <div class="info-card">--%>
<%--                    <h3>🔐 Quyền truy cập</h3>--%>
<%--                    <p><strong>Role:</strong> Administrator</p>--%>
<%--                    <p><strong>Admin:</strong> ${sessionScope.user.admin ? 'Yes' : 'No'}</p>--%>
<%--                    <p><strong>Status:</strong> Active</p>--%>
<%--                </div>--%>
<%--                --%>
<%--                <div class="info-card">--%>
<%--                    <h3>📍 Trang hiện tại</h3>--%>
<%--                    <p><strong>URI:</strong> ${currentUri}</p>--%>
<%--                    <p><strong>Module:</strong> ${pageTitle}</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            --%>
<%--            <div style="background: #fff3cd; padding: 20px; border-radius: 10px; margin-top: 30px; border-left: 4px solid #ffc107;">--%>
<%--                <h4 style="color: #856404; margin-bottom: 10px;">💡 Lưu ý:</h4>--%>
<%--                <p style="color: #856404;">--%>
<%--                    Đây là trang demo. Nội dung chi tiết quản lý sẽ được bổ sung sau.<br>--%>
<%--                    AuthFilter đã kiểm tra và xác nhận bạn có quyền truy cập trang này.--%>
<%--                </p>--%>
<%--            </div>--%>
            
            <div class="back-link">
                <a href="${pageContext.request.contextPath}/lab6/page.jsp">← Quay lại trang chủ</a>
            </div>
        </div>
    </div>
</body>
</html>
