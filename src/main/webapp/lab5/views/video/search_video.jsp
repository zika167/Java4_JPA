<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Bài 3: Tìm kiếm video</title>
    <style>
        :root {
            --bg-dark: #121212;
            --card-bg: #1e1e1e;
            --text-light: #e0e0e0;
            --text-secondary: #a0a0a0;
            --primary-accent: #00bcd4; /* Cyan/Teal Accent */
            --shadow-color: rgba(0, 0, 0, 0.5);
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--bg-dark); /* Màu nền tối */
            min-height: 100vh;
            padding: 20px;
            color: var(--text-light); /* Màu chữ mặc định sáng */
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: var(--card-bg); /* Màu nền thẻ tối */
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 40px var(--shadow-color);
            border: 1px solid #333;
        }

        h1 {
            text-align: center;
            color: var(--primary-accent); /* Màu chữ tiêu đề */
            margin-bottom: 30px;
            font-size: 32px;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
            border-bottom: 2px solid #333;
            padding-bottom: 10px;
        }

        .search-box {
            background-color: #2a2a2a; /* Nền tối hơn cho box tìm kiếm */
            padding: 25px;
            border-radius: 8px;
            margin-bottom: 30px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
        }

        .search-form {
            display: flex;
            gap: 10px;
        }

        .search-input {
            flex: 1;
            padding: 12px 20px;
            background-color: #333; /* Nền tối cho input */
            border: 2px solid #444;
            border-radius: 6px;
            font-size: 16px;
            color: var(--text-light); /* Màu chữ sáng */
            transition: all 0.3s ease;
        }

        .search-input:focus {
            outline: none;
            border-color: var(--primary-accent);
            box-shadow: 0 0 10px rgba(0, 188, 212, 0.4);
        }

        .btn-search {
            padding: 12px 30px;
            background-color: var(--primary-accent);
            color: var(--card-bg); /* Màu chữ tối trên nền sáng */
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .btn-search:hover {
            transform: translateY(-2px);
            background-color: #00e5ff;
            box-shadow: 0 5px 15px rgba(0, 188, 212, 0.4);
        }

        .btn-search:active {
            transform: translateY(0);
        }

        .message {
            padding: 15px 20px;
            background-color: #1a472a; /* Nền thành công tối */
            color: #72ef9e; /* Chữ thành công sáng */
            border: 1px solid #38761d;
            border-radius: 6px;
            margin-bottom: 20px;
            font-weight: bold;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
        }

        table thead {
            background-color: var(--primary-accent); /* Màu nền tiêu đề */
            color: var(--card-bg); /* Màu chữ tối */
        }

        table th {
            padding: 15px;
            text-align: left;
            font-weight: bold;
            font-size: 16px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        table td {
            padding: 15px;
            border-bottom: 1px solid #333;
            color: var(--text-light); /* Màu chữ sáng */
            background-color: #242424; /* Nền tối cho nội dung */
        }

        table tbody tr:hover {
            background-color: #383838; /* Highlight hover */
            transform: scale(1.01);
        }

        .video-title {
            font-weight: bold;
            color: var(--primary-accent); /* Màu tiêu đề video */
            font-size: 15px;
        }

        .likes-count {
            color: #ff5757; /* Màu đỏ sáng hơn */
            font-weight: bold;
            font-size: 16px;
        }

        .badge {
            display: inline-block;
            padding: 6px 12px;
            border-radius: 20px;
            font-size: 13px;
            font-weight: bold;
            text-transform: uppercase;
        }

        .badge-active {
            background-color: #2a5f45; /* Nền xanh đậm */
            color: #72ef9e; /* Chữ xanh sáng */
            border: 1px solid #38761d;
        }

        .badge-inactive {
            background-color: #58151c; /* Nền đỏ đậm */
            color: #ff9999; /* Chữ đỏ sáng */
            border: 1px solid #721c24;
        }

        .no-results {
            text-align: center;
            padding: 40px;
            color: var(--text-secondary);
            font-size: 18px;
        }

        .no-results-icon {
            font-size: 48px;
            margin-bottom: 15px;
            color: var(--primary-accent);
        }

        .text-center {
            text-align: center;
        }

        .like-icon {
            color: #ff5757;
            margin-right: 5px;
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

        <h1>
            <span>🔍</span> Search Videos
        </h1>

        <div class="search-box">
            <form method="get" action="${pageContext.request.contextPath}/video/search" class="search-form">
                <input type="text" 
                       name="keyword" 
                       class="search-input" 
                       placeholder="Enter keyword to search videos..." 
                       value="${keyword}"
                       required />
                <button type="submit" class="btn-search">🔍 Search</button>
            </form>
        </div>

        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>

        <c:choose>
            <c:when test="${not empty videos}">
                <table>
                    <thead>
                        <tr>
                            <th>Tiêu đề Video</th>
                            <th class="text-center">Số lượt thích</th>
                            <th class="text-center">Còn hiệu lực</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="video" items="${videos}">
                            <tr>
                                <td class="video-title">${video.title}</td>
                                <td class="text-center">
                                    <span class="likes-count">
                                        <span class="like-icon">❤️</span>
                                        ${fn:length(video.favorites)}
                                    </span>
                                </td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${video.active}">
                                            <span class="badge badge-active">✓ Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-inactive">✗ Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${not empty keyword}">
                <div class="no-results">
                    <div class="no-results-icon">🔍</div>
                    <div>No videos found for keyword: "<strong>${keyword}</strong>"</div>
                    <div style="margin-top: 10px; font-size: 14px;">Try searching with different keywords.</div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="no-results">
                    <div class="no-results-icon">🎬</div>
                    <div>Enter a keyword to search for videos</div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
