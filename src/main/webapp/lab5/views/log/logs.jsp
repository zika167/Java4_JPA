<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logs - AppFilter Demo - Lab 5 Bài 3</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 1200px;
            margin: 20px auto;
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th {
            background-color: #667eea;
            color: white;
            padding: 12px;
            text-align: left;
            font-weight: bold;
        }
        td {
            padding: 10px 12px;
            border-bottom: 1px solid #ddd;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .username {
            color: #28a745;
            font-weight: bold;
        }
        .guest {
            color: #6c757d;
            font-style: italic;
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
        .stats {
            display: flex;
            justify-content: space-around;
            margin: 20px 0;
        }
        .stat-box {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            border-radius: 8px;
            text-align: center;
            flex: 1;
            margin: 0 10px;
        }
        .stat-box h3 {
            margin: 0;
            font-size: 36px;
        }
        .stat-box p {
            margin: 10px 0 0 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>📊 Logs - Thông tin truy cập Website</h2>
        <p style="text-align: center; color: #666;">Lab 5 - Bài 3: AppFilter</p>
        
        <div class="stats">
            <div class="stat-box">
                <h3>${logsList.size()}</h3>
                <p>Tổng số lượt truy cập</p>
            </div>
        </div>
        
        <div class="info-box">
            <h4>📋 AppFilter hoạt động như thế nào?</h4>
            <ul>
                <li><strong>Thiết lập UTF-8:</strong> Mã hóa ký tự cho tất cả request/response</li>
                <li><strong>Ghi nhận thông tin:</strong> Mỗi request đến <code>/lab5/*</code> được ghi vào database</li>
                <li><strong>Thông tin lưu:</strong> Id (tự tăng), URL, Thời gian truy cập, Username (nếu đã đăng nhập)</li>
                <li><strong>Lưu vào CSDL:</strong> Sử dụng <code>logsDAO.create()</code> trong filter</li>
            </ul>
        </div>
        
        <h3>📝 Danh sách Logs:</h3>
        
        <c:choose>
            <c:when test="${empty logsList}">
                <div class="info-box" style="background-color: #fff3cd; border-left-color: #ffc107;">
                    <p style="margin: 0; color: #856404;">Chưa có log nào được ghi nhận. Hãy truy cập các trang khác trong /lab5/* để tạo log.</p>
                </div>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>URL</th>
                            <th>Thời gian truy cập</th>
                            <th>Username</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="log" items="${logsList}">
                            <tr>
                                <td>${log.id}</td>
                                <td>${log.url}</td>
                                <td>${log.time}
                                    <%--<fmt:formatDate value="${log.time}" pattern="dd/MM/yyyy HH:mm:ss" />--%>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty log.username}">
                                            <span class="guest">Khách (chưa đăng nhập)</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="username">${log.username}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
        
        <a href="${pageContext.request.contextPath}/lab5/index.jsp" class="back-link">
            ← Quay lại trang chủ Lab 5
        </a>
    </div>
</body>
</html>
