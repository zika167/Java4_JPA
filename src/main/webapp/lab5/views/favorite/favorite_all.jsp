<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bài tập thêm: Danh sách Video Yêu thích</title>
    <style>
        :root {
            --bg-dark: #121212;
            --card-bg: #1e1e1e;
            --text-light: #e0e0e0;
            --text-secondary: #a0a0a0;
            --primary-accent: #00bcd4; /* Cyan/Teal Accent */
            --shadow-color: rgba(0, 0, 0, 0.5);
            --danger-color: #ff5757;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: var(--bg-dark);
            color: var(--text-light);
            min-height: 100vh;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
            background-color: var(--card-bg);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 40px var(--shadow-color);
            border: 1px solid #333;
        }

        h1 {
            color: var(--primary-accent);
            border-bottom: 2px solid #333;
            padding-bottom: 15px;
            margin-bottom: 30px;
            text-align: center;
            font-size: 28px;
            font-weight: 600;
        }

        /* Bảng */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.3);
            border-radius: 8px;
            overflow: hidden; /* Cắt góc tròn cho bảng */
        }

        table th {
            background-color: var(--primary-accent);
            color: var(--card-bg); /* Chữ tối trên nền accent */
            padding: 15px;
            text-align: left;
            font-weight: bold;
            font-size: 15px;
            text-transform: uppercase;
        }

        table td {
            padding: 15px;
            border-bottom: 1px solid #333;
            background-color: #242424; /* Nền tối cho nội dung */
            color: var(--text-light);
            font-size: 14px;
        }

        table tbody tr:hover {
            background-color: #383838; /* Highlight hover */
            transition: all 0.2s ease;
        }

        .no-data {
            text-align: center;
            color: var(--text-secondary);
            padding: 40px;
            font-style: italic;
            background-color: #242424;
            border-radius: 6px;
        }

        .video-title {
            font-weight: bold;
            color: var(--primary-accent);
        }

        .user-name {
            color: var(--text-light);
        }

        .date {
            color: var(--text-secondary);
            font-size: 14px;
        }

        /* CSS cho nút quay về trang chủ */
        .nav-back-button {
            display: inline-block;
            margin-bottom: 25px;
            padding: 10px 18px;
            background-color: #333; /* Dark gray */
            color: var(--text-light);
            border-radius: 6px;
            text-decoration: none;
            font-weight: 500;
            transition: all 0.3s ease;
            border: 1px solid #555;
        }

        .nav-back-button:hover {
            background-color: #555;
            color: var(--primary-accent);
            transform: translateY(-1px);
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/lab4/index.jsp" class="nav-back-button">
            ← Quay về Trang chủ
        </a>

        <h1>Danh sách Video đã được Yêu thích</h1>
        
        <c:choose>
            <c:when test="${not empty favorites}">
                <table>
                    <thead>
                        <tr>
                            <th>Video Title</th>
                            <th>Người thích</th>
                            <th>Ngày thích</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="favorite" items="${favorites}">
                            <tr>
                                <td class="video-title">${favorite.video.title}</td>
                                <td class="user-name">${favorite.user.fullname}</td>
                                <td class="date">
                                    <fmt:formatDate value="${favorite.likeDate}" pattern="dd/MM/yyyy HH:mm:ss" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="no-data">Chưa có video nào được yêu thích</div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
