<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${empty product ? 'Thêm mới' : 'Cập nhật'} sản phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h2 { color: #333; }
        form { max-width: 400px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="number"] {
            width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;
        }
        .btn { padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; }
        .btn-save { background-color: #4CAF50; color: white; }
        .btn-cancel { background-color: #ccc; color: #333; margin-left: 10px; }
    </style>
</head>
<body>
    <h2>${empty product ? 'Thêm mới' : 'Cập nhật'} sản phẩm</h2>
    
    <form action="${pageContext.request.contextPath}/finaltest/product" method="post">
        <c:if test="${not empty product}">
            <input type="hidden" name="id" value="${product.id}">
        </c:if>
        
        <div class="form-group">
            <label for="name">Tên sản phẩm:</label>
            <input type="text" id="name" name="name" value="${product.name}" required>
        </div>
        
        <div class="form-group">
            <label for="price">Giá:</label>
            <input type="number" id="price" name="price" value="${product.price}" step="0.01" required>
        </div>
        
        <div class="form-group">
            <label for="quantity">Số lượng:</label>
            <input type="number" id="quantity" name="quantity" value="${product.quantity}" required>
        </div>
        
        <button type="submit" class="btn btn-save">Lưu</button>
        <a href="${pageContext.request.contextPath}/finaltest/product/list" class="btn btn-cancel">Hủy</a>
    </form>
</body>
</html>
