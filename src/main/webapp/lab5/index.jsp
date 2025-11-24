<%--
  Created by IntelliJ IDEA.
  User: wangquockhanh
  Date: 2/10/25
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab5</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .visitor-counter {
            background-color: #e7f3ff;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 30px;
            text-align: center;
            border: 2px solid #b3d9ff;
        }
        .visitor-counter strong {
            font-size: 18px;
        }
        .visitor-counter span {
            color: #0066cc;
            font-size: 24px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="visitor-counter">
    <strong>👥 Số lượt khách viếng thăm website:</strong> 
    <span>${applicationScope.visitors}</span>
</div>

<h1>Tổng hợp các bài tập của Lab 5</h1>
<br/>
<h2><a href="${pageContext.request.contextPath}/lab5/login">Bài 1: Đăng nhập với Session</a></h2>
<br/>
<hr/>
<h2><a href="${pageContext.request.contextPath}/lab5/visitor-demo">Bài 2: Bộ đếm khách viếng thăm</a></h2>
<br/>
<hr/>
<h2><a href="${pageContext.request.contextPath}/lab5/logs">Bài 3: AppFilter - Ghi nhận thông tin truy cập</a></h2>
<br/>
<hr/>
<h2><a href="${pageContext.request.contextPath}/lab5/filter-demo/test">Bài 4: Demo Filter Chain (Filter1 & Filter2)</a></h2>
<br/>
<hr/>
</body>
</html>
