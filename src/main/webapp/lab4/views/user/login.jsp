<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Bài 2: Check đăng nhập</title>
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
    background-color: var(--bg-dark);
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 20px;
    color: var(--text-light);
    }

    .login-container {
    background-color: var(--card-bg);
    padding: 40px;
    border-radius: 10px;
    box-shadow: 0 15px 30px var(--shadow-color);
    width: 100%;
    max-width: 400px;
    border: 1px solid #333;
    }

    h2 {
    text-align: center;
    color: var(--primary-accent);
    margin-bottom: 30px;
    font-size: 30px;
    letter-spacing: 1px;
    border-bottom: 2px solid #333;
    padding-bottom: 15px;
    }

    .message {
    padding: 12px 15px;
    margin-bottom: 20px;
    border-radius: 4px;
    font-weight: 500;
    text-align: center;
    font-size: 14px;
    }

    .message.success {
    background-color: #1a472a;
    color: #72ef9e;
    border: 1px solid #38761d;
    }

    .message.error {
    background-color: #58151c;
    color: #ff9999;
    border: 1px solid #721c24;
    }

    .form-group {
    margin-bottom: 20px;
    }

    label {
    display: block;
    margin-bottom: 8px;
    color: var(--text-secondary);
    font-weight: 500;
    font-size: 15px;
    }

    input[type="text"],
    input[type="password"] {
    width: 100%;
    padding: 14px 15px;
    background-color: #2a2a2a;
    border: 1px solid #333;
    border-radius: 4px;
    font-size: 15px;
    color: var(--text-light);
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
    }

    input[type="text"]:focus,
    input[type="password"]:focus {
    outline: none;
    border-color: var(--primary-accent);
    box-shadow: 0 0 10px rgba(0, 188, 212, 0.4);
    }

    .btn-login {
    width: 100%;
    padding: 12px;
    background-color: var(--primary-accent);
    color: var(--card-bg);
    border: none;
    border-radius: 4px;
    font-size: 16px;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.2s ease;
    }

    .btn-login:hover {
    background-color: #00e5ff;
    transform: translateY(-1px);
    }

    .helper-text {
    text-align: center;
    margin-top: 20px;
    color: var(--text-secondary);
    font-size: 14px;
    }

    /* Phần hiển thị thông tin người dùng (sau khi login) */
    .user-info {
    background-color: #333;
    padding: 15px;
    border-radius: 4px;
    margin-bottom: 25px;
    border-left: 4px solid var(--primary-accent);
    }

    .user-info p {
    margin: 5px 0;
    color: var(--text-light);
    }

    .user-info strong {
    color: var(--primary-accent);
    }

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
    <div class="login-container">
        <a href="${pageContext.request.contextPath}/lab4/index.jsp" class="nav-back-button">
            ← Quay về Trang chủ
        </a>

        <h2>🔐 User Login</h2>

        <c:if test="${not empty message}">
            <div class="message ${messageType}">${message}</div>
        </c:if>

        <c:if test="${not empty user}">
            <div class="user-info">
                <p><strong>User ID:</strong> ${user.id}</p>
                <p><strong>Fullname:</strong> ${user.fullname}</p>
                <p><strong>Email:</strong> ${user.email}</p>
                <p><strong>Role:</strong> ${user.admin ? 'Admin' : 'User'}</p>
            </div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/user/login">
            <div class="form-group">
                <label for="username">Username or Email</label>
                <input type="text" id="username" name="username"
                       placeholder="Enter your username or email"
                       value="${username}"
                       required />
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password"
                       placeholder="Enter your password"
                       required />
            </div>

            <button type="submit" class="btn-login">Login</button>

            <p class="helper-text">
                You can login with your <strong>Username (ID)</strong> or <strong>Email</strong>
            </p>
        </form>
    </div>
</body>
</html>
