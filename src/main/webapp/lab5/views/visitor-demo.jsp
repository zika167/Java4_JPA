<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bộ đếm khách viếng thăm - Lab 5 Bài 2</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
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
        .visitor-counter {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            border-radius: 12px;
            text-align: center;
            margin: 30px 0;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .visitor-counter h3 {
            margin: 0 0 15px 0;
            font-size: 24px;
        }
        .visitor-count {
            font-size: 48px;
            font-weight: bold;
            margin: 20px 0;
        }
        .info-box {
            background-color: #e7f3ff;
            padding: 20px;
            border-radius: 8px;
            margin: 20px 0;
            border-left: 4px solid #0066cc;
        }
        .info-box h4 {
            margin-top: 0;
            color: #0066cc;
        }
        .info-box ul {
            margin: 10px 0;
            padding-left: 20px;
        }
        .info-box li {
            margin: 8px 0;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 30px;
            color: #0066cc;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
        .session-info {
            background-color: #fff3cd;
            padding: 15px;
            border-radius: 8px;
            margin: 20px 0;
            border-left: 4px solid #ffc107;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>🎯 Bộ đếm khách viếng thăm Website</h2>
        <p style="text-align: center; color: #666;">Lab 5 - Bài 2</p>
        
        <div class="visitor-counter">
            <h3>👥 Tổng số lượt khách viếng thăm</h3>
            <div class="visitor-count">${applicationScope.visitors}</div>
            <p style="margin: 0; opacity: 0.9;">Mỗi session chỉ được đếm 1 lần</p>
        </div>
        
        <div class="session-info">
            <strong>ℹ️ Thông tin Session hiện tại:</strong><br>
            Session ID: <%= session.getId() %><br>
            Session mới: <%= session.isNew() ? "Có" : "Không" %>
        </div>
        
        <div class="info-box">
            <h4>📋 Cách hoạt động:</h4>
            <ul>
                <li><strong>WebListener:</strong> Sử dụng <code>ServletContextListener</code> và <code>HttpSessionListener</code></li>
                <li><strong>contextInitialized():</strong> Khởi tạo số đếm = 0 khi ứng dụng khởi động</li>
                <li><strong>sessionCreated():</strong> Tăng số đếm lên 1 mỗi khi có session mới được tạo</li>
                <li><strong>Application Scope:</strong> Lưu số đếm với tên <code>visitors</code></li>
                <li><strong>WebFilter:</strong> Đảm bảo số đếm được hiển thị trên mọi trang trong <code>/lab5/*</code></li>
            </ul>
        </div>
        
        <div class="info-box">
            <h4>🧪 Cách kiểm tra:</h4>
            <ul>
                <li>Mở trình duyệt ẩn danh (Incognito) để tạo session mới</li>
                <li>Mỗi lần mở tab ẩn danh mới, số đếm sẽ tăng lên 1</li>
                <li>Trong cùng 1 session, dù truy cập bao nhiêu trang cũng chỉ đếm 1 lần</li>
                <li>Xóa cookie hoặc đóng trình duyệt để kết thúc session</li>
            </ul>
        </div>
        
        <a href="${pageContext.request.contextPath}/lab5/index.jsp" class="back-link">
            ← Quay lại trang chủ Lab 5
        </a>
    </div>
</body>
</html>
