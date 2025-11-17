<%--
  File: user-crud.jsp
  Tác giả: wangquockhanh
  Lab 2 - Java 4
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab 2 - Quản lý User</title>

    <%-- Phần CSS thêm vào để trang đẹp hơn --%>
    <style>
        :root {
            --primary-color: #007bff;
            --danger-color: #dc3545;
            --success-color: #28a745;
            --secondary-color: #6c757d;
            --light-bg: #f8f9fa;
            --border-color: #dee2e6;
            --text-color: #212529;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            margin: 0;
            padding: 2rem;
            background-color: var(--light-bg);
            color: var(--text-color);
            line-height: 1.6;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        }

        /* Dành cho thông báo (message) */
        .message {
            font-style: italic;
            font-weight: 600;
            padding: 1rem;
            background-color: #e2e3e5;
            border: 1px solid var(--border-color);
            border-radius: 4px;
            margin-bottom: 1.5rem;
            color: var(--secondary-color);
        }

        /* Cải thiện Form */
        form {
            display: grid;
            grid-template-columns: 1fr 1fr; /* Chia form làm 2 cột */
            gap: 1rem 1.5rem; /* Khoảng cách giữa các hàng và cột */
            margin: 2rem 0;
        }

        .form-group {
            display: flex;
            flex-direction: column;
        }

        /* Dùng cho các mục chiếm trọn 2 cột */
        .form-group-full {
            grid-column: 1 / -1; /* Chiếm từ cột 1 đến cột cuối cùng */
        }

        .form-group label {
            margin-bottom: 0.5rem;
            font-weight: 500;
        }

        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="email"] {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid var(--border-color);
            border-radius: 4px;
            box-sizing: border-box; /* Giúp padding không làm vỡ layout */
        }

        .radio-group {
            display: flex;
            gap: 1.5rem;
            align-items: center;
        }

        .radio-group label {
            margin-bottom: 0; /* Ghi đè label mặc định */
            font-weight: normal;
        }

        /* Cải thiện các nút bấm */
        .button-group {
            grid-column: 1 / -1; /* Chiếm trọn 2 cột */
            display: flex;
            flex-wrap: wrap; /* Cho phép xuống hàng trên di động */
            gap: 0.75rem;
            border-top: 1px solid var(--border-color);
            padding-top: 1rem;
        }

        .button-group button {
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 1rem;
            cursor: pointer;
            transition: opacity 0.2s;
        }

        .button-group button:hover {
            opacity: 0.85;
        }

        /* Phân màu cho nút */
        button[formaction$="/create"] { background-color: var(--primary-color); }
        button[formaction$="/update"] { background-color: var(--success-color); }
        button[formaction$="/delete"] { background-color: var(--danger-color); }
        button[formaction$="/reset"] { background-color: var(--secondary-color); }

        /* Cải thiện Bảng */
        table {
            width: 100%;
            border-collapse: collapse; /* Gộp viền */
            margin-top: 2rem;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
        }

        table th, table td {
            border: 1px solid var(--border-color);
            padding: 0.75rem 1rem;
            text-align: left;
        }

        table thead {
            background-color: #e9ecef;
        }

        table tbody tr:nth-child(even) {
            background-color: var(--light-bg); /* Làm cho bảng dễ đọc hơn */
        }

        table tbody tr:hover {
            background-color: #dbeafe; /* Highlight khi di chuột */
        }

        table td a {
            text-decoration: none;
            color: var(--primary-color);
            font-weight: 500;
        }

        table td a:hover {
            text-decoration: underline;
        }

    </style>
</head>
<body>

<div class="container">

    <%-- Thêm class cho thông báo để CSS nhận diện --%>
    <i class="message">${message}</i>

    <c:url var="url" value="/user/crud"/>

    <form method="post">

        <%-- Bọc mỗi input vào 1 div.form-group để dễ CSS --%>
        <div class="form-group">
            <label for="id">User ID:</label>
            <input name="id" id="id" type="text" value="${user.id}">
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input name="password" id="password" type="password" value="${user.password}">
        </div>

        <div class="form-group">
            <label for="fullname">Full Name:</label>
            <input name="fullname" id="fullname" type="text" value="${user.fullname}">
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input name="email" id="email" type="email" value="${user.email}">
        </div>

        <%-- Nhóm các radio lại --%>
        <div class="form-group form-group-full">
            <label>Role:</label>
            <div class="radio-group">
                <%-- SỬA LỖI: name="gender" thành name="admin" để khớp với Servlet/Entity --%>
                <%-- SỬA LỖI: Điều kiện checked cho 'User' phải là !user.admin --%>
                <input name="admin" id="role_admin" type="radio" value="true" ${user.admin ? 'checked' : ''}>
                <label for="role_admin">Admin</label>

                <input name="admin" id="role_user" type="radio" value="false" ${!user.admin ? 'checked' : ''}>
                <label for="role_user">User</label>
            </div>
        </div>

        <%-- Nhóm các nút bấm lại --%>
        <div class="button-group">
            <button formaction="${url}/create">Create</button>
            <button formaction="${url}/update">Update</button>
            <button formaction="${url}/delete">Delete</button>
            <button formaction="${url}/reset">Reset</button>
        </div>
    </form>

    <hr>

    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Password</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>Role</th>
            <th>Action</th> <%-- Đổi tên cột cho rõ nghĩa --%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${users}">
            <tr>
                <td>${u.id}</td>
                <td>${u.password}</td> <%-- Thường thì không nên hiển thị password --%>
                <td>${u.fullname}</td>
                <td>${u.email}</td>
                <td>${u.admin ? 'Admin' : 'User'}</td>
                <td>
                        <%-- SỬA LỖI: Thêm dấu " ở đầu href --%>
                    <a href="${url}/edit/${u.id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div> <%-- End .container --%>

</body>
</html>