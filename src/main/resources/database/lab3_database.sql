-- =====================================================
-- SCRIPT KHỞI TẠO CHO SQL SERVER (T-SQL)
-- =====================================================

USE Java4_Lab3_PolyOE;

DROP TABLE IF EXISTS Share;
DROP TABLE IF EXISTS Favorite;
DROP TABLE IF EXISTS Video;
DROP TABLE IF EXISTS [User]; -- Dùng [] thay cho ``

-- =====================================================
-- TABLE: [User]
-- Description: Stores user account information
-- =====================================================
CREATE TABLE [User] -- Dùng [User] vì USER là từ khóa
(
    Id          VARCHAR(50) PRIMARY KEY,
    Password    VARCHAR(255) NOT NULL,
    Email       VARCHAR(100) NOT NULL UNIQUE,
    Fullname    NVARCHAR(100) NOT NULL, -- Dùng NVARCHAR để hỗ trợ tiếng Việt
    Admin       BIT NOT NULL DEFAULT 0, -- Thay BOOLEAN bằng BIT (0 = false)
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(), -- Thay CURRENT_TIMESTAMP bằng GETDATE()
    UpdatedDate DATETIME NULL
    );

-- =====================================================
-- TABLE: Video
-- Description: Stores video information
-- =====================================================
CREATE TABLE Video
(
    Id          VARCHAR(50) PRIMARY KEY,
    Title       NVARCHAR(255) NOT NULL, -- Dùng NVARCHAR
    Poster      VARCHAR(500) NULL,
    Views       INT      NOT NULL DEFAULT 0,
    Description NVARCHAR(MAX) NULL, -- Thay LONGTEXT bằng NVARCHAR(MAX)
    Active      BIT  NOT NULL DEFAULT 1, -- Thay BOOLEAN bằng BIT (1 = true)
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(),
    UpdatedDate DATETIME NULL
);

-- =====================================================
-- TABLE: Favorite
-- Description: Stores user's favorite videos
-- =====================================================
CREATE TABLE Favorite
(
    Id       BIGINT PRIMARY KEY IDENTITY(1,1), -- Thay AUTO_INCREMENT bằng IDENTITY(1,1)
    UserId   VARCHAR(50) NOT NULL,
    VideoId  VARCHAR(50) NOT NULL,
    LikeDate DATETIME NOT NULL DEFAULT GETDATE(),
    UNIQUE (UserId, VideoId),
    FOREIGN KEY (UserId) REFERENCES [User](Id) ON DELETE CASCADE,
    FOREIGN KEY (VideoId) REFERENCES Video(Id) ON DELETE CASCADE
);

-- =====================================================
-- TABLE: Share
-- Description: Stores video sharing information
-- =====================================================
CREATE TABLE Share
(
    Id        BIGINT PRIMARY KEY IDENTITY(1,1), -- Thay IDENTITY(1,1)
    UserId    VARCHAR(50) NOT NULL,
    VideoId   VARCHAR(50) NOT NULL,
    Emails    NVARCHAR(MAX) NOT NULL, -- Thay LONGTEXT bằng NVARCHAR(MAX)
    ShareDate DATETIME NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (UserId) REFERENCES [User](Id) ON DELETE CASCADE,
    FOREIGN KEY (VideoId) REFERENCES Video(Id) ON DELETE CASCADE
);

-- Insert sample users
-- Thêm N' vào trước chuỗi NVARCHAR để hỗ trợ tiếng Việt
INSERT INTO [User] (Id, Password, Email, Fullname, Admin)
VALUES
    ('user001', 'hashed_password_123', 'john@example.com', N'John Doe', 0),
    ('user002', 'hashed_password_456', 'jane@example.com', N'Jane Smith', 0),
    ('admin001', 'hashed_admin_password', 'admin@example.com', N'Admin User', 1);

-- Insert sample videos
-- Sửa TRUE thành 1, và thêm N' cho chuỗi
INSERT INTO Video (Id, Title, Poster, Views, Description, Active)
VALUES (N'vid001', N'Introduction to Java', 'poster1.jpg', 150, N'Learn Java basics', 1),
       (N'vid002', N'Advanced SQL Techniques', 'poster2.jpg', 200, N'Master SQL queries', 1),
       (N'vid003', N'Web Development with HTML5', 'poster3.jpg', 120, N'Build modern websites', 1);

-- Insert sample favorites (Không cần đổi)
INSERT INTO Favorite (UserId, VideoId)
VALUES ('user001', 'vid001'),
       ('user001', 'vid002'),
       ('user002', 'vid003');

-- Insert sample shares (Thêm N')
INSERT INTO Share (UserId, VideoId, Emails)
VALUES ('user001', 'vid001', N'friend1@example.com;friend2@example.com'),
       ('user002', 'vid002', N'colleague@example.com');

-- =====================================================
-- VERIFY TABLES CREATED SUCCESSFULLY
-- =====================================================
SELECT 'Database setup completed successfully!' AS Status;

-- Cú pháp T-SQL để kiểm tra bảng trong database 'Java4_Lab3_PolyOE' và schema 'dbo'
SELECT TABLE_NAME
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_CATALOG = 'Java4_Lab3_PolyOE' AND TABLE_SCHEMA = 'dbo'
ORDER BY TABLE_NAME;

INSERT INTO Video (Id, Title, Poster, Views, Description, Active)
VALUES
    (N'vid004', N'CSS Nâng cao với Grid và Flexbox', 'poster4.jpg', 350, N'Học CSS Grid và Flexbox để xây dựng layout hiện đại.', 1),
    (N'vid005', N'JavaScript ES6+ Toàn tập', 'poster5.jpg', 500, N'Các tính năng mới nhất của JavaScript (ES6, ES7, ES8).', 1),
    (N'vid006', N'Xây dựng ứng dụng với ReactJS', 'poster6.jpg', 420, N'Giới thiệu về React và cách tạo component.', 1),
    (N'vid007', N'Spring Boot cho người mới bắt đầu', 'poster7.jpg', 600, N'Làm quen với Spring Boot và xây dựng REST API.', 1),
    (N'vid008', N'Lập trình Hướng đối tượng (OOP)', 'poster8.jpg', 210, N'Các nguyên lý OOP cơ bản: Đa hình, Kế thừa, Đóng gói.', 1);

INSERT INTO Favorite (UserId, VideoId)
VALUES
    ('user002', 'vid001'), -- user002 cũng thích vid001
    ('user002', 'vid005'), -- user002 thích vid005 (JS)
    ('admin001', 'vid007'), -- admin thích vid007 (Spring Boot)
    ('admin001', 'vid006'), -- admin thích vid006 (React)
    ('user001', 'vid004'), -- user001 thích vid004 (CSS)
    ('user001', 'vid007'); -- user001 cũng thích vid007 (Spring Boot)
