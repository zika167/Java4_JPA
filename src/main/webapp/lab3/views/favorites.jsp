<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Video yêu thích</title>

    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            margin: 0;
            padding: 2rem;
            background-color: #f4f7f6;
            color: #333;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 2rem 3rem;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
        }

        /* Tên User (Nguyễn Văn Tèo) */
        .user-name {
            font-size: 2rem;
            font-weight: 600;
            color: #0056b3;
            text-align: center;
            margin-bottom: 0.5rem;
            border-bottom: 2px solid #0056b3;
            padding-bottom: 0.5rem;
        }

        /* "Các video đã yêu thích" */
        .list-title {
            font-size: 1.5rem;
            font-weight: 500;
            color: #555;
            margin-top: 2rem;
            margin-bottom: 1.5rem;
        }

        /* Danh sách video */
        .favorite-list {
            list-style-type: none; /* Bỏ dấu chấm tròn */
            padding-left: 0;
        }

        .favorite-list li {
            font-size: 1.1rem;
            padding: 0.75rem 0.5rem;
            border-bottom: 1px solid #e0e0e0;
            display: flex;
            align-items: center;
            gap: 0.75rem;
        }

        /* Dấu tick (CSS) */
        .favorite-list li::before {
            content: '✓'; /* Dấu tick */
            color: #28a745; /* Màu xanh lá */
            font-weight: bold;
            font-size: 1.2rem;
        }

        .favorite-list li:last-child {
            border-bottom: none;
        }

        /* Dành cho trường hợp không có video nào */
        .empty-list {
            font-style: italic;
            color: #888;
            text-align: center;
            padding: 2rem;
        }

        /* Navigation buttons */
        .nav-buttons {
            display: flex;
            justify-content: space-between;
            gap: 1rem;
            margin-top: 2rem;
            padding-top: 2rem;
            border-top: 2px solid #e0e0e0;
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

    <%-- Yêu cầu: Hiển thị tên (Nguyễn Văn Tèo) --%>
    <%-- Chúng ta lấy từ đối tượng "currentUser" mà Servlet gửi qua --%>
    <h1 class="user-name">${currentUser.fullname}</h1>

    <h2 class="list-title">Các video đã yêu thích</h2>

    <ul class="favorite-list">

        <%-- Kiểm tra nếu danh sách rỗng --%>
        <c:if test="${empty favoritesList}">
            <li class="empty-list">Bạn chưa yêu thích video nào.</li>
        </c:if>

        <%-- Yêu cầu: Lặp qua danh sách --%>
        <%-- "fav" là một đối tượng Favorite --%>
        <c:forEach var="fav" items="${favoritesList}">
            <li>
                    <%-- Yêu cầu: Khai thác thực thể kết hợp --%>
                    <%-- Từ "fav" (Favorite) -> "video" (Video) -> "title" (String) --%>
                <c:out value="${fav.video.title}" />
            </li>
        </c:forEach>

    </ul>

    <%-- Navigation buttons --%>
    <div class="nav-buttons">
        <c:url var="likedVideoUrl" value="/liked-videos"/>
        <a href="${likedVideoUrl}" class="btn btn-primary">📊 Xem tất cả video yêu thích</a>

        <c:url var="logoutUrl" value="/logout"/>
        <a href="${logoutUrl}" class="btn btn-secondary">🚪 Đăng xuất</a>
    </div>
</div>

</body>
</html>