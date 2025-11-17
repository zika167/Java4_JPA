<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bài 4 - Danh sách Video yêu thích</title>

    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            margin: 0;
            padding: 2rem;
            background-color: #f4f7f6;
            color: #333;
        }

        .container {
            max-width: 1000px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
        }

        h1 {
            text-align: center;
            color: #0056b3;
            margin-bottom: 2rem;
            font-size: 2rem;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
        }

        thead {
            background-color: #0056b3;
            color: white;
        }

        th, td {
            padding: 1rem;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            font-weight: 600;
        }

        tbody tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        tbody tr:hover {
            background-color: #e9ecef;
        }

        .empty-message {
            text-align: center;
            padding: 2rem;
            color: #888;
            font-style: italic;
        }

        /* Navigation buttons */
        .nav-buttons {
            display: flex;
            justify-content: space-between;
            gap: 1rem;
            margin-top: 2rem;
            padding-top: 2rem;
            border-top: 2px solid #ddd;
        }

        .btn {
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            transition: all 0.2s;
        }

        .btn-primary {
            background-color: #0056b3;
            color: white;
        }

        .btn-primary:hover {
            background-color: #003d82;
        }

        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Danh sách Video đã được yêu thích</h1>

    <c:if test="${empty favorites}">
        <div class="empty-message">Chưa có video nào được yêu thích.</div>
    </c:if>

    <c:if test="${not empty favorites}">
        <table>
            <thead>
                <tr>
                    <th>Video Title</th>
                    <th>Người thích</th>
                    <th>Ngày thích</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="fav" items="${favorites}">
                    <tr>
                        <td>${fav.video.title}</td>
                        <td>${fav.user.fullname}</td>
                        <td><fmt:formatDate value="${fav.likeDate}" pattern="dd/MM/yyyy" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <%-- Navigation buttons --%>
    <div class="nav-buttons">
        <c:url var="favoritesUrl" value="/favorites"/>
        <a href="${favoritesUrl}" class="btn btn-primary">👤 Trang cá nhân</a>

        <c:url var="logoutUrl" value="/logout"/>
        <a href="${logoutUrl}" class="btn btn-secondary">🚪 Đăng xuất</a>
    </div>
</div>

</body>
</html>
