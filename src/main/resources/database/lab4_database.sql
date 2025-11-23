-- Chú ý: Cần chạy các lệnh này trong context của database (Ví dụ: USE PolyOE;)
-- Hoặc đảm bảo database PolyOE đã được tạo.
use Java4_Lab4_PolyOE;
-- T-SQL 2016+
DROP TABLE IF EXISTS Share;
DROP TABLE IF EXISTS Favorite;
DROP TABLE IF EXISTS Video;
DROP TABLE IF EXISTS [Users];

-- =====================================================
-- TABLE: Users
-- =====================================================
CREATE TABLE [Users]
(
    Id       VARCHAR(20) PRIMARY KEY,
    Password VARCHAR(50) NOT NULL,
    Fullname NVARCHAR(50), -- Dùng NVARCHAR để hỗ trợ Unicode (Tiếng Việt)
    Email    VARCHAR(50),
    Admin    BIT -- Thay BOOLEAN bằng BIT (0 hoặc 1)
);

-- =====================================================
-- TABLE: Video
-- =====================================================
CREATE TABLE Video
(
    Id          VARCHAR(20) PRIMARY KEY,
    Title       NVARCHAR(100) NOT NULL, -- Dùng NVARCHAR
    Poster      VARCHAR(255),
    Views       INT DEFAULT 0,
    Description NVARCHAR(255), -- Dùng NVARCHAR
    Active      BIT -- Thay BOOLEAN bằng BIT
);

-- =====================================================
-- TABLE: Favorite
-- =====================================================
CREATE TABLE Favorite
(
    -- Thêm IDENTITY(1,1) để tự tăng, tương tự AUTO_INCREMENT
    Id       INT PRIMARY KEY IDENTITY(1,1),
    UserId   VARCHAR(20) NOT NULL,
    VideoId  VARCHAR(20) NOT NULL,
    LikeDate DATE NOT NULL DEFAULT GETDATE(), -- Thay CURRENT_TIMESTAMP bằng GETDATE()
    FOREIGN KEY (UserId) REFERENCES [Users] (Id),
    FOREIGN KEY (VideoId) REFERENCES Video (Id)
);

-- =====================================================
-- TABLE: Share
-- =====================================================
CREATE TABLE Share
(
    Id        INT PRIMARY KEY IDENTITY(1,1), -- Thay AUTO_INCREMENT bằng IDENTITY(1,1)
    UserId    VARCHAR(20)  NOT NULL,
    VideoId   VARCHAR(20)  NOT NULL,
    Emails    VARCHAR(100) NOT NULL,
    ShareDate DATE NOT NULL DEFAULT GETDATE(), -- Thay CURRENT_TIMESTAMP bằng GETDATE()
    FOREIGN KEY (UserId) REFERENCES [Users] (Id),
    FOREIGN KEY (VideoId) REFERENCES Video (Id)
);

-- =====================================================
-- SAMPLE DATA (T-SQL)
-- =====================================================
-- Thêm N' vào các chuỗi NVARCHAR, đổi TRUE/FALSE sang 1/0
INSERT INTO [Users] (Id, Password, Fullname, Email, Admin)
VALUES ('user01', 'password01', N'Nguyễn Văn A', 'nguyenvana@example.com', 0),
       ('admin01', 'adminpass', N'Trần Thị B', 'tranthib@example.com', 1),
       ('user02', 'userpass02', N'Lê Văn C', 'levanc@example.com', 0);

INSERT INTO Video (Id, Title, Poster, Views, Description, Active)
VALUES ('vdo01', N'Hướng dẫn SQL cơ bản', 'images/sql_basic.jpg', 1500, N'Bài hướng dẫn chi tiết về các lệnh SQL cơ bản.', 1),
       ('vdo02', N'Kỹ thuật lập trình web', 'images/web_dev.png', 850, N'Giới thiệu về các công nghệ và kỹ thuật lập trình web hiện đại.', 1),
       ('vdo03', N'Khám phá vũ trụ', 'images/space_doc.gif', 3200, N'Phim tài liệu hấp dẫn về các thiên hà và hành tinh.', 0);

INSERT INTO Favorite (UserId, VideoId, LikeDate)
VALUES ('user01', 'vdo01', '2025-10-20'),
       ('user02', 'vdo01', '2025-11-01'),
       ('user01', 'vdo02', '2025-11-10');

INSERT INTO Share (UserId, VideoId, Emails, ShareDate)
VALUES ('user01', 'vdo02', 'friend1@mail.com,friend2@mail.com', '2025-11-11'),
       ('admin01', 'vdo01', 'colleague@work.com', '2025-11-12');