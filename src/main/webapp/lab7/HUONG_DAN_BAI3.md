# 📚 HƯỚNG DẪN CHI TIẾT BÀI 3 - REST API

## 📖 MỤC LỤC
1. [Employee.java - Entity](#1-employeejava---entity)
2. [RestIO.java - Utility Class](#2-restiojava---utility-class)
3. [EmployeeRestServlet.java - REST API](#3-employeerestservletjava---rest-api)
4. [bai3.html - Client Test](#4-bai3html---client-test)

---

## 1. Employee.java - Entity

### 📝 **Mục đích:**
Class đại diện cho một nhân viên (Employee) trong hệ thống.

### 🔍 **Giải thích từng phần:**

```java
package com.wangquocthai.java4.lab7.entity;
```
- Khai báo package chứa class này
- `entity` = thực thể, đại diện cho dữ liệu

```java
public class Employee {
    private String id;        // Mã nhân viên (VD: "NV01")
    private String name;      // Tên nhân viên (VD: "Nguyễn Văn A")
    private Boolean gender;   // Giới tính (true = Nam, false = Nữ)
    private Double salary;    // Lương (VD: 5000.0)
```
- **private**: Chỉ truy cập được trong class này
- **String id**: Kiểu chuỗi, lưu mã nhân viên
- **Boolean gender**: Kiểu boolean object (có thể null)
- **Double salary**: Kiểu số thực object (có thể null)

```java
    public Employee() {
    }
```
- **Constructor không tham số**: Cần thiết để Jackson (thư viện JSON) có thể tạo object từ JSON

```java
    public Employee(String id, String name, Boolean gender, Double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
    }
```
- **Constructor có tham số**: Tạo object Employee với đầy đủ thông tin
- **this.id = id**: `this.id` là biến của class, `id` là tham số

```java
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
```
- **Getter**: Lấy giá trị của thuộc tính
- **Setter**: Gán giá trị cho thuộc tính
- **Tại sao cần?** Vì thuộc tính là `private`, cần getter/setter để truy cập từ bên ngoài

---

## 2. RestIO.java - Utility Class

### 📝 **Mục đích:**
Class tiện ích giúp chuyển đổi giữa JSON và Java Object.

### 🔍 **Giải thích từng phương thức:**

#### **2.1. ObjectMapper**

```java
private static ObjectMapper mapper = new ObjectMapper();
```
- **ObjectMapper**: Class của thư viện Jackson
- **Chức năng**: Chuyển đổi giữa JSON ↔ Java Object
- **static**: Dùng chung cho tất cả, không cần tạo object mới

---

#### **2.2. readJson() - Đọc chuỗi JSON từ client**

```java
public static String readJson(HttpServletRequest req) throws IOException {
```
- **static**: Gọi trực tiếp qua class: `RestIO.readJson(req)`
- **String**: Trả về chuỗi JSON
- **HttpServletRequest req**: Request từ client
- **throws IOException**: Có thể xảy ra lỗi đọc file

```java
    req.setCharacterEncoding("utf-8");
```
- **Mục đích**: Đảm bảo đọc đúng tiếng Việt
- **utf-8**: Bảng mã hỗ trợ tiếng Việt

```java
    BufferedReader reader = req.getReader();
```
- **BufferedReader**: Đọc dữ liệu từ request theo dòng
- **req.getReader()**: Lấy reader từ request body

```java
    StringBuilder buffer = new StringBuilder();
    String line;
```
- **StringBuilder**: Nối chuỗi hiệu quả (nhanh hơn String +)
- **String line**: Biến lưu từng dòng đọc được

```java
    while ((line = reader.readLine()) != null) {
        buffer.append(line);
    }
```
- **while loop**: Đọc từng dòng cho đến khi hết (null)
- **reader.readLine()**: Đọc 1 dòng
- **buffer.append(line)**: Nối dòng vào buffer

```java
    reader.close();
    return buffer.toString();
```
- **reader.close()**: Đóng reader (giải phóng tài nguyên)
- **buffer.toString()**: Chuyển StringBuilder thành String

**📊 Ví dụ:**
```
Client gửi: {"id":"NV01","name":"Tèo"}
→ readJson() đọc từng dòng
→ Trả về: {"id":"NV01","name":"Tèo"}
```

---

#### **2.3. writeJson() - Gửi chuỗi JSON về client**

```java
public static void writeJson(HttpServletResponse resp, String json) throws IOException {
```
- **HttpServletResponse resp**: Response gửi về client
- **String json**: Chuỗi JSON cần gửi

```java
    resp.setCharacterEncoding("utf-8");
    resp.setContentType("application/json");
```
- **setCharacterEncoding**: Mã hóa UTF-8
- **setContentType**: Báo cho browser biết đây là JSON

```java
    resp.getWriter().print(json);
    resp.getWriter().flush();
```
- **getWriter()**: Lấy writer để ghi dữ liệu
- **print(json)**: Ghi chuỗi JSON
- **flush()**: Đẩy dữ liệu đi ngay (không đợi buffer đầy)

**📊 Ví dụ:**
```
json = {"id":"NV01","name":"Tèo"}
→ writeJson() gửi về client
→ Client nhận được JSON
```

---

#### **2.4. readObject() - Đọc JSON và chuyển sang Java Object**

```java
public static <T> T readObject(HttpServletRequest req, Class<T> clazz) throws IOException {
```
- **<T>**: Generic type (kiểu tổng quát)
- **T**: Có thể là Employee, User, Product, v.v.
- **Class<T> clazz**: Class của object cần tạo (VD: Employee.class)

```java
    String json = RestIO.readJson(req);
```
- Gọi phương thức `readJson()` để đọc chuỗi JSON

```java
    T bean = mapper.readValue(json, clazz);
```
- **mapper.readValue()**: Chuyển JSON thành Object
- **json**: Chuỗi JSON đầu vào
- **clazz**: Class cần chuyển đổi
- **bean**: Object kết quả

```java
    return bean;
```
- Trả về object đã chuyển đổi

**📊 Ví dụ:**
```
Client gửi JSON: {"id":"NV01","name":"Tèo","gender":true,"salary":5000.0}
↓
readObject(req, Employee.class)
↓
Jackson parse JSON → Employee object
↓
Trả về: Employee{id="NV01", name="Tèo", gender=true, salary=5000.0}
```

---

#### **2.5. writeObject() - Chuyển Java Object sang JSON và gửi về client**

```java
public static void writeObject(HttpServletResponse resp, Object data) throws IOException {
```
- **Object data**: Bất kỳ object nào (Employee, List, Map, v.v.)

```java
    String json = mapper.writeValueAsString(data);
```
- **mapper.writeValueAsString()**: Chuyển Object thành chuỗi JSON
- **data**: Object cần chuyển đổi
- **json**: Chuỗi JSON kết quả

```java
    RestIO.writeJson(resp, json);
```
- Gọi `writeJson()` để gửi JSON về client

**📊 Ví dụ:**
```
Employee emp = new Employee("NV01", "Tèo", true, 5000.0);
↓
writeObject(resp, emp)
↓
Jackson chuyển Object → JSON
↓
Gửi về client: {"id":"NV01","name":"Tèo","gender":true,"salary":5000.0}
```

---

#### **2.6. writeEmptyObject() - Gửi đối tượng rỗng**

```java
public static void writeEmptyObject(HttpServletResponse resp) throws IOException {
    RestIO.writeObject(resp, java.util.Map.of());
}
```
- **Map.of()**: Tạo Map rỗng (Java 9+)
- **Kết quả**: Gửi về client `{}`

**📊 Khi nào dùng?**
- Sau khi DELETE thành công
- Không có dữ liệu trả về nhưng cần báo thành công

---

## 3. EmployeeRestServlet.java - REST API

### 📝 **Mục đích:**
Servlet xử lý các request REST API để quản lý nhân viên.

### 🔍 **Giải thích từng phần:**

#### **3.1. Khai báo và khởi tạo**

```java
@WebServlet("/employees/*")
```
- **@WebServlet**: Annotation đăng ký servlet
- **/employees/***: URL pattern
  - `/employees` → Lấy tất cả
  - `/employees/NV01` → Lấy theo ID
  - `*` = wildcard (bất kỳ gì sau `/employees/`)

```java
public class EmployeeRestServlet extends HttpServlet {
```
- **extends HttpServlet**: Kế thừa từ HttpServlet để xử lý HTTP request

```java
    private Map<String, Employee> map = new HashMap<>();
```
- **Map<String, Employee>**: Cấu trúc dữ liệu key-value
  - **Key**: String (mã nhân viên "NV01")
  - **Value**: Employee object
- **HashMap**: Implementation của Map
- **Thay cho database**: Lưu dữ liệu trong bộ nhớ (RAM)

```java
    public EmployeeRestServlet() {
        map.put("NV01", new Employee("NV01", "Nhân viên 01", true, 500.0));
        map.put("NV02", new Employee("NV02", "Nhân viên 02", false, 600.0));
        // ...
    }
```
- **Constructor**: Chạy khi servlet được tạo
- **map.put(key, value)**: Thêm nhân viên vào map
- **Dữ liệu mẫu**: 5 nhân viên để test

---

#### **3.2. doGet() - Xử lý GET request**

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
```
- **@Override**: Ghi đè phương thức của HttpServlet
- **doGet**: Xử lý HTTP GET request
- **throws**: Khai báo có thể xảy ra exception

```java
    String info = req.getPathInfo();
```
- **getPathInfo()**: Lấy phần sau URL pattern
  - URL: `/employees/NV01`
  - Pattern: `/employees/*`
  - PathInfo: `/NV01`

```java
    if (info == null || info.length() == 0) {
```
- **Kiểm tra**: PathInfo có rỗng không?
- **null**: URL là `/employees` (không có gì sau)
- **length() == 0**: URL là `/employees/` (có `/` nhưng không có ID)

```java
        RestIO.writeObject(resp, map.values());
```
- **map.values()**: Lấy tất cả Employee trong map
- **Kết quả**: Trả về danh sách tất cả nhân viên

```java
    } else {
        String id = info.substring(1).trim();
```
- **substring(1)**: Bỏ ký tự đầu tiên (dấu `/`)
  - `/NV01` → `NV01`
- **trim()**: Xóa khoảng trắng đầu cuối

```java
        RestIO.writeObject(resp, map.get(id));
```
- **map.get(id)**: Lấy Employee theo ID
- **Kết quả**: Trả về 1 nhân viên hoặc null (nếu không tìm thấy)

**📊 Ví dụ:**
```
GET /employees
→ info = null
→ Trả về: [Employee1, Employee2, Employee3, ...]

GET /employees/NV03
→ info = "/NV03"
→ id = "NV03"
→ Trả về: Employee{id="NV03", ...}
```

---

#### **3.3. doPost() - Xử lý POST request (Thêm mới)**

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
```
- **doPost**: Xử lý HTTP POST request
- **Mục đích**: Thêm nhân viên mới

```java
    Employee employee = RestIO.readObject(req, Employee.class);
```
- **Đọc JSON từ client**: 
  ```json
  {"id":"NV06","name":"Tèo","gender":true,"salary":9500.0}
  ```
- **Chuyển thành Employee object**:
  ```java
  Employee{id="NV06", name="Tèo", gender=true, salary=9500.0}
  ```

```java
    map.put(employee.getId(), employee);
```
- **Thêm vào map**: 
  - Key: `employee.getId()` → "NV06"
  - Value: `employee` object

```java
    RestIO.writeObject(resp, employee);
```
- **Trả về employee vừa thêm**: Xác nhận thành công

**📊 Luồng hoạt động:**
```
Client gửi POST /employees
Body: {"id":"NV06","name":"Tèo","gender":true,"salary":9500.0}
↓
readObject() → Employee object
↓
map.put("NV06", employee)
↓
writeObject() → Trả về JSON
↓
Client nhận: {"id":"NV06","name":"Tèo","gender":true,"salary":9500.0}
```

---

#### **3.4. doPut() - Xử lý PUT request (Cập nhật)**

```java
@Override
protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
```
- **doPut**: Xử lý HTTP PUT request
- **Mục đích**: Cập nhật thông tin nhân viên

```java
    Employee employee = RestIO.readObject(req, Employee.class);
```
- Đọc JSON từ client (giống POST)

```java
    map.put(employee.getId(), employee);
```
- **Cập nhật trong map**:
  - Nếu ID đã tồn tại → Ghi đè (update)
  - Nếu ID chưa tồn tại → Thêm mới

```java
    RestIO.writeObject(resp, employee);
```
- Trả về employee đã cập nhật

**📊 Ví dụ:**
```
PUT /employees/NV06
Body: {"id":"NV06","name":"Tèo Updated","gender":true,"salary":12000.0}
↓
map.put("NV06", employee) → Ghi đè employee cũ
↓
Trả về: {"id":"NV06","name":"Tèo Updated","gender":true,"salary":12000.0}
```

---

#### **3.5. doDelete() - Xử lý DELETE request (Xóa)**

```java
@Override
protected void doDelete(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
```
- **doDelete**: Xử lý HTTP DELETE request
- **Mục đích**: Xóa nhân viên

```java
    String info = req.getPathInfo();
    String id = info.substring(1).trim();
```
- Lấy ID từ URL (giống GET)

```java
    map.remove(id);
```
- **map.remove(id)**: Xóa employee có key = id
- **Kết quả**: Employee bị xóa khỏi map

```java
    RestIO.writeEmptyObject(resp);
```
- Trả về object rỗng `{}` để báo thành công

**📊 Ví dụ:**
```
DELETE /employees/NV06
↓
id = "NV06"
↓
map.remove("NV06") → Xóa khỏi map
↓
Trả về: {}
```

---

## 4. bai3.html - Client Test

### 📝 **Mục đích:**
Trang HTML test REST API bằng Fetch API.

### 🔍 **Giải thích từng function:**

#### **4.1. getAllEmployees() - GET tất cả**

```javascript
async function getAllEmployees() {
```
- **async**: Function bất đồng bộ (có thể dùng await)
- **Mục đích**: Lấy danh sách tất cả nhân viên

```javascript
    try {
```
- **try-catch**: Bắt lỗi nếu có

```javascript
        const response = await fetch(API_URL);
```
- **fetch(API_URL)**: Gọi API GET /employees
- **await**: Đợi response trả về
- **const response**: Lưu response

```javascript
        const data = await response.json();
```
- **response.json()**: Parse response thành JSON
- **await**: Đợi parse xong
- **const data**: Lưu dữ liệu JSON

```javascript
        console.log('GET /employees:', data);
        displayResult('GET /employees', data);
```
- **console.log**: In ra console để debug
- **displayResult**: Hiển thị kết quả lên trang

**📊 Luồng:**
```
Click button
↓
fetch('/employees')
↓
Server xử lý → Trả về JSON
↓
response.json() → Parse JSON
↓
Hiển thị kết quả
```

---

#### **4.2. createEmployee() - POST thêm mới**

```javascript
async function createEmployee() {
    try {
        const newEmployee = {
            id: "NV06",
            name: "Nguyễn Văn Tèo",
            gender: true,
            salary: 9500.0
        };
```
- **Tạo object JavaScript**: Dữ liệu nhân viên mới

```javascript
        const response = await fetch(API_URL, {
            method: 'POST',
```
- **method: 'POST'**: Chỉ định HTTP method

```javascript
            headers: {
                'Content-Type': 'application/json'
            },
```
- **headers**: Thông tin header
- **Content-Type**: Báo server đây là JSON

```javascript
            body: JSON.stringify(newEmployee)
```
- **JSON.stringify()**: Chuyển object JavaScript → chuỗi JSON
- **body**: Dữ liệu gửi đi

```javascript
        });
        
        const data = await response.json();
```
- Parse response JSON

**📊 Luồng:**
```
Click button
↓
Tạo object newEmployee
↓
JSON.stringify() → Chuyển thành chuỗi JSON
↓
fetch() POST → Gửi đến server
↓
Server xử lý → Trả về JSON
↓
Hiển thị kết quả
```

---

#### **4.3. updateEmployee() - PUT cập nhật**

```javascript
async function updateEmployee() {
    try {
        const updatedEmployee = {
            id: "NV06",
            name: "Nguyễn Văn Tèo (Updated)",
            gender: true,
            salary: 12000.0
        };
        
        const response = await fetch(API_URL + '/NV06', {
            method: 'PUT',
```
- **method: 'PUT'**: HTTP PUT method
- **API_URL + '/NV06'**: URL đầy đủ `/employees/NV06`

**📊 Khác với POST:**
- POST: Thêm mới (không cần ID trong URL)
- PUT: Cập nhật (cần ID trong URL)

---

#### **4.4. deleteEmployee() - DELETE xóa**

```javascript
async function deleteEmployee() {
    try {
        const response = await fetch(API_URL + '/NV06', {
            method: 'DELETE'
        });
```
- **method: 'DELETE'**: HTTP DELETE method
- **Không cần body**: Chỉ cần ID trong URL

---

## 📊 TỔNG KẾT LUỒNG HOẠT ĐỘNG

### **GET /employees**
```
Browser → fetch('/employees')
         ↓
Servlet → doGet()
         → info = null
         → map.values()
         → RestIO.writeObject(list)
         ↓
Browser ← JSON array
```

### **POST /employees**
```
Browser → fetch('/employees', {method:'POST', body:JSON})
         ↓
Servlet → doPost()
         → RestIO.readObject() → Employee
         → map.put(id, employee)
         → RestIO.writeObject(employee)
         ↓
Browser ← JSON object
```

### **PUT /employees/NV06**
```
Browser → fetch('/employees/NV06', {method:'PUT', body:JSON})
         ↓
Servlet → doPut()
         → RestIO.readObject() → Employee
         → map.put(id, employee) [update]
         → RestIO.writeObject(employee)
         ↓
Browser ← JSON object
```

### **DELETE /employees/NV06**
```
Browser → fetch('/employees/NV06', {method:'DELETE'})
         ↓
Servlet → doDelete()
         → info = "/NV06"
         → id = "NV06"
         → map.remove(id)
         → RestIO.writeEmptyObject()
         ↓
Browser ← {}
```

---

## 🎯 ĐIỂM QUAN TRỌNG CẦN NHỚ

1. **REST API**: Sử dụng HTTP methods (GET, POST, PUT, DELETE)
2. **JSON**: Format dữ liệu trao đổi giữa client-server
3. **Jackson**: Thư viện chuyển đổi JSON ↔ Java Object
4. **PathInfo**: Lấy phần sau URL pattern để xác định ID
5. **Map**: Lưu trữ dữ liệu dạng key-value (thay database)
6. **Fetch API**: JavaScript gọi REST API bất đồng bộ

---

## 🚀 CÁCH TEST

1. Mở `http://localhost:8080/lab7/views/bai3.html`
2. Mở Console (F12)
3. Click từng button và xem kết quả
4. Kiểm tra Console log để hiểu luồng dữ liệu

---

**Chúc bạn học tốt! 🎉**
