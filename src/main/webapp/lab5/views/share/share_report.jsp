<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Bài 4: Thông tin chia sẻ tổng hợp</title>
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
            background-color: var(--bg-dark); /* Nền tối */
            min-height: 100vh;
            padding: 20px;
            color: var(--text-light);
        }

        .container {
            max-width: 1400px;
            margin: 0 auto;
            background-color: var(--card-bg); /* Nền thẻ tối */
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 40px var(--shadow-color);
            border: 1px solid #333;
        }

        h1 {
            text-align: center;
            color: var(--primary-accent); /* Màu chữ tiêu đề */
            margin-bottom: 10px;
            font-size: 36px;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 15px;
            border-bottom: 2px solid #333;
            padding-bottom: 10px;
        }

        .subtitle {
            text-align: center;
            color: var(--text-secondary);
            margin-bottom: 30px;
            font-size: 16px;
        }

        /* Thẻ thống kê (Stats Card) */
        .stats-card {
            background-color: #242424; /* Nền tối hơn */
            color: var(--primary-accent);
            padding: 25px;
            border-radius: 8px;
            margin-bottom: 30px;
            text-align: center;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.5);
            border: 1px solid var(--primary-accent); /* Viền accent */
        }

        .stats-card h2 {
            font-size: 42px;
            margin-bottom: 5px;
        }

        .stats-card p {
            font-size: 16px;
            color: var(--text-light);
            opacity: 0.8;
        }

        /* Bảng */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.3);
        }

        table thead {
            background-color: var(--primary-accent);
            color: var(--card-bg); /* Chữ tối trên nền accent */
        }

        table th {
            padding: 18px 15px;
            text-align: left;
            font-weight: bold;
            font-size: 15px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        table td {
            padding: 16px 15px;
            border-bottom: 1px solid #333; /* Viền tối */
            color: var(--text-light);
            background-color: #1e1e1e; /* Nền nội dung tối */
            transition: all 0.2s ease;
        }

        table td.text-center, table th.text-center {
            text-align: center;
        }


        table tbody tr:hover {
            background-color: #333; /* Highlight hover */
            transform: scale(1.005);
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
        }

        .video-title {
            font-weight: bold;
            color: var(--primary-accent); /* Màu chữ accent */
            font-size: 15px;
        }

        .share-count {
            background-color: #008c9e; /* Accent đậm */
            color: var(--text-light);
            padding: 8px 16px;
            border-radius: 20px;
            font-weight: bold;
            font-size: 16px;
            display: inline-block;
            min-width: 50px;
        }

        .date-info {
            color: var(--text-secondary);
            font-size: 14px;
        }

        .date-icon {
            margin-right: 5px;
            color: var(--primary-accent);
        }

        /* Không có dữ liệu */
        .no-data {
            text-align: center;
            padding: 60px 20px;
            color: var(--text-secondary);
        }

        .no-data-icon {
            font-size: 64px;
            margin-bottom: 20px;
            color: var(--primary-accent);
        }

        .no-data h2 {
            font-size: 24px;
            margin-bottom: 10px;
        }

        .no-data p {
            font-size: 16px;
        }

        .table-wrapper {
            overflow-x: auto;
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

        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }

            h1 {
                font-size: 24px;
            }

            table th,
            table td {
                padding: 10px 8px;
                font-size: 13px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/lab4/index.jsp" class="nav-back-button">
            ← Quay về Trang chủ
        </a>

        <h1>
            <span>📊</span> Video Share Report
        </h1>
        <p class="subtitle">Thống kê chia sẻ video tổng hợp</p>

        <c:if test="${not empty reports}">
            <div class="stats-card">
                <h2>${reports.size()}</h2>
                <p>Total Videos Shared</p>
            </div>

            <div class="table-wrapper">
                <table>
                    <thead>
                        <tr>
                            <th>Tiêu đề Video</th>
                            <th class="text-center">Số lượt chia sẻ</th>
                            <th class="text-center">Ngày chia sẻ đầu tiên</th>
                            <th class="text-center">Ngày chia sẻ cuối cùng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="report" items="${reports}">
                            <tr>
                                <td class="video-title">${report.videoTitle}</td>
                                <td class="text-center">
                                    <span class="share-count">
                                        ${report.shareCount}
                                    </span>
                                </td>
                                <td class="text-center">
                                    <span class="date-info">
                                        <span class="date-icon">📅</span>
                                        <fmt:formatDate value="${report.firstShareDate}" pattern="dd/MM/yyyy HH:mm" />
                                    </span>
                                </td>
                                <td class="text-center">
                                    <span class="date-info">
                                        <span class="date-icon">📅</span>
                                        <fmt:formatDate value="${report.lastShareDate}" pattern="dd/MM/yyyy HH:mm" />
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

        <c:if test="${empty reports}">
            <div class="no-data">
                <div class="no-data-icon">📭</div>
                <h2>No Share Data Available</h2>
                <p>There are currently no video shares to display.</p>
            </div>
        </c:if>
    </div>
</body>
</html>
