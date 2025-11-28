# Lab 6 - Video Management System

## 📋 Đã hoàn thành

### 1. Entity Layer
- ✅ User.java
- ✅ Video.java
- ✅ Favorite.java
- ✅ Share.java

### 2. DAO Layer
- ✅ UserDAO & UserDAOImpl
- ✅ XJPA utility

### 3. Servlet - Account Management
- ✅ SignUpServlet (`/account/sign-up`) - Đăng ký
- ✅ LoginServlet (`/auth/login`) - Đăng nhập
- ✅ LogoutServlet (`/auth/logout`) - Đăng xuất
- ✅ ChangePasswordServlet (`/account/change-password`) - Đổi mật khẩu
- ✅ EditProfileServlet (`/account/edit-profile`) - Sửa thông tin

### 4. AuthFilter (Bài 2)
- ✅ `poly.filter.AuthFilter` - Bộ lọc phân quyền
- Áp dụng cho:
  - `/admin/*` - Yêu cầu đăng nhập + quyền Admin
  - `/account/change-password` - Yêu cầu đăng nhập
  - `/account/edit-profile` - Yêu cầu đăng nhập
  - `/video/like/*` - Yêu cầu đăng nhập
  - `/video/share/*` - Yêu cầu đăng nhập

### 5. JSP Views
- ✅ page.jsp - Trang chủ chung
- ✅ login.jsp - Trang đăng nhập
- ✅ sign-up.jsp - Trang đăng ký
- ✅ change-password.jsp - Đổi mật khẩu
- ✅ edit-profile.jsp - Sửa thông tin
- ✅ video/list.jsp - Demo trang video
- ✅ admin/video.jsp - Demo trang admin

## 🧪 Cách test AuthFilter

### Test 1: Truy cập trang yêu cầu đăng nhập (chưa đăng nhập)
1. Truy cập: `http://localhost:8080/account/change-password`
2. **Kết quả:** Redirect đến `/lab6/views/auth/login.jsp`
3. **Thông báo:** "⚠️ Bạn cần đăng nhập để truy cập trang này"

### Test 2: Đăng nhập và redirect về trang ban đầu
1. Nhập username/password
2. **Kết quả:** Sau khi đăng nhập thành công, tự động redirect về `/account/change-password`

### Test 3: Truy cập trang Admin (user thường)
1. Đăng nhập với user thường (admin = false)
2. Truy cập: `http://localhost:8080/admin/video`
3. **Kết quả:** Redirect đến login (vì không có quyền admin)

### Test 4: Truy cập trang Admin (admin)
1. Đăng nhập với user admin (admin = true)
2. Truy cập: `http://localhost:8080/admin/video`
3. **Kết quả:** Hiển thị trang quản trị

## 🔐 Cách hoạt động của AuthFilter

```java
// 1. Lấy user từ session
User user = (User) session.getAttribute("user");

// 2. Lấy URI hiện tại
String uri = req.getRequestURI();

// 3. Kiểm tra điều kiện
if (user == null || (uri.contains("/admin/") && !user.getAdmin())) {
    // Lưu URI để redirect sau khi đăng nhập
    session.setAttribute(AuthFilter.SECURITY_URI, uri);
    
    // Redirect đến login
    resp.sendRedirect("/lab6/views/auth/login.jsp");
} else {
    // Cho phép tiếp tục
    chain.doFilter(request, response);
}
```

## 📊 Luồng hoạt động

```
User truy cập /admin/video (chưa đăng nhập)
    ↓
AuthFilter chặn request
    ↓
Kiểm tra: user == null? → YES
    ↓
Lưu URI vào session: securityUri = "/admin/video"
    ↓
Redirect → /lab6/views/auth/login.jsp
    ↓
User đăng nhập thành công
    ↓
LoginServlet kiểm tra securityUri
    ↓
Redirect về /admin/video
    ↓
AuthFilter kiểm tra lại: user != null && user.admin == true? → YES
    ↓
Cho phép truy cập → Hiển thị trang admin
```

## 🎯 URL để test

1. **Trang chủ:** `http://localhost:8080/lab6/`
2. **Đăng ký:** `http://localhost:8080/account/sign-up`
3. **Đăng nhập:** `http://localhost:8080/auth/login`
4. **Đổi mật khẩu:** `http://localhost:8080/account/change-password` (cần login)
5. **Sửa thông tin:** `http://localhost:8080/account/edit-profile` (cần login)
6. **Danh sách video:** `http://localhost:8080/video/list` (public)
7. **Quản lý video:** `http://localhost:8080/admin/video` (cần admin)

## 📝 Lưu ý

- Cần tạo user trong database để test
- User admin cần có field `Admin = 1` (true)
- AuthFilter sử dụng `@WebFilter` annotation để tự động đăng ký
