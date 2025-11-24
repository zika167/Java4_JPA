<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Demo Filter Chain - Lab 5 Bài 4</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 900px;
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
        .result-box {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            border-radius: 8px;
            margin: 20px 0;
            text-align: center;
        }
        .result-box h3 {
            margin: 0 0 10px 0;
        }
        .result-box p {
            font-size: 20px;
            margin: 10px 0;
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
        .flow-diagram {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin: 20px 0;
            font-family: monospace;
        }
        .flow-step {
            padding: 10px;
            margin: 5px 0;
            background-color: white;
            border-left: 3px solid #28a745;
            border-radius: 4px;
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
        code {
            background-color: #f4f4f4;
            padding: 2px 6px;
            border-radius: 3px;
            color: #c7254e;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>🔗 Demo Filter Chain - Lab 5 Bài 4</h2>
        
        <div class="result-box">
            <h3>📨 Giá trị nhận được từ Filter1:</h3>
            <p><strong>"${messageFromFilter}"</strong></p>
        </div>
        
        <div class="info-box">
            <h4>📋 Cách hoạt động:</h4>
            <ol>
                <li><strong>Filter1</strong> chạy trước:
                    <ul>
                        <li>Set attribute: <code>req.setAttribute("hello", "Tôi là filter 1")</code></li>
                        <li>Gọi <code>chain.doFilter()</code> để chuyển sang filter tiếp theo</li>
                    </ul>
                </li>
                <li><strong>Filter2</strong> chạy sau:
                    <ul>
                        <li>Lấy attribute: <code>req.getAttribute("hello")</code></li>
                        <li>In ra console: <code>System.out.println()</code></li>
                        <li>Gọi <code>chain.doFilter()</code> để chuyển đến servlet</li>
                    </ul>
                </li>
                <li><strong>Servlet</strong> xử lý request và forward đến JSP</li>
            </ol>
        </div>
        
        <div class="flow-diagram">
            <h4 style="margin-top: 0;">🔄 Luồng xử lý (Filter Chain):</h4>
            <div class="flow-step">1️⃣ Request → Filter1 (BEFORE chain.doFilter)</div>
            <div class="flow-step">2️⃣ Filter1 → Filter2 (BEFORE chain.doFilter)</div>
            <div class="flow-step">3️⃣ Filter2 → Servlet</div>
            <div class="flow-step">4️⃣ Servlet → JSP</div>
            <div class="flow-step">5️⃣ JSP → Filter2 (AFTER chain.doFilter)</div>
            <div class="flow-step">6️⃣ Filter2 → Filter1 (AFTER chain.doFilter)</div>
            <div class="flow-step">7️⃣ Filter1 → Response</div>
        </div>
        
        <div class="info-box">
            <h4>⚙️ Cấu hình trong web.xml:</h4>
            <p>Thứ tự khai báo <code>&lt;filter-mapping&gt;</code> quyết định thứ tự chạy:</p>
            <ul>
                <li>Filter1 được khai báo trước → chạy trước</li>
                <li>Filter2 được khai báo sau → chạy sau</li>
            </ul>
            <p><strong>Lưu ý:</strong> Nếu dùng <code>@WebFilter</code> annotation, không thể đảm bảo thứ tự chạy!</p>
        </div>
        
        <div class="info-box" style="background-color: #fff3cd; border-left-color: #ffc107;">
            <h4 style="color: #856404;">💡 Kiểm tra Console Log:</h4>
            <p>Mở console của server để xem log chi tiết:</p>
            <ul>
                <li>🔵 Filter1 BEFORE chain.doFilter()</li>
                <li>🟢 Filter2 BEFORE chain.doFilter()</li>
                <li>🎯 Servlet đang xử lý request</li>
                <li>🟢 Filter2 AFTER chain.doFilter()</li>
                <li>🔵 Filter1 AFTER chain.doFilter()</li>
            </ul>
        </div>
        
        <a href="${pageContext.request.contextPath}/lab5/index.jsp" class="back-link">
            ← Quay lại trang chủ Lab 5
        </a>
    </div>
</body>
</html>
