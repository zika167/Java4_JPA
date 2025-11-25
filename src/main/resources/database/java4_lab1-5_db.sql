-- Chú ý: Cần chạy các lệnh này trong context của database (Ví dụ: USE PolyOE;)
-- Hoặc đảm bảo database PolyOE đã được tạo.
create database java4_PolyOE;

use Java4_PolyOE;
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
VALUES
    ('vdo01', N'Hướng dẫn SQL cơ bản', 'images/sql_basic.jpg', 1500, N'Bài hướng dẫn chi tiết về các lệnh SQL cơ bản.', 1),
    ('vdo02', N'Kỹ thuật lập trình web', 'images/web_dev.png', 850, N'Giới thiệu về các công nghệ và kỹ thuật lập trình web hiện đại.', 1),
    ('vdo03', N'Khám phá vũ trụ', 'images/space_doc.gif', 3200, N'Phim tài liệu hấp dẫn về các thiên hà và hành tinh.', 0),
    ('vdo04', N'Tuyển tập các thuật toán cơ bản', 'images/algo.jpg', 450, N'Tổng hợp kiến thức về Cấu trúc Dữ liệu và giải thuật.', 1),
    ('vdo05', N'10 sai lầm khi dùng JPA và Hibernate', 'images/jpa.png', 120, N'Các lỗi thường gặp nhất trong JPA.', 1),
    ('vdo06', N'Lập trình di động: Cài đặt và môi trường', 'images/mobile.png', 780, N'Bài giảng về môi trường lập trình di động.', 1),
    ('vdo07', N'Hướng dẫn Debug trong IntelliJ IDEA', 'images/idea.png', 200, N'Các bước gỡ lỗi hiệu quả trong Java.', 1),
    ('vdo08', N'Tìm hiểu về Design Pattern Singleton', 'images/singleton.png', 350, N'Áp dụng Singleton trong lập trình.', 1),
    ('vdo09', N'Giới thiệu về API Gateway', 'images/api_gw.png', 150, N'Vai trò của API Gateway trong Microservices.', 1),
    ('vdo10', N'Lập trình Bất đồng bộ với JavaScript', 'images/async_js.png', 550, N'Sử dụng Promises và Async/Await.', 1),
    ('vdo11', N'Công nghệ AI và tương lai', 'images/ai_fut.png', 50, N'Tài liệu về AI', 1),
    ('vdo12', N'Bóng đá và chiến thuật', 'images/football.png', 100, N'Phân tích chiến thuật', 1),
    ('vdo13', N'Lập trình hướng sự kiện trong Vue.js', 'images/vue_event.png', 650, N'Hướng dẫn xử lý sự kiện trong các component Vue.js.', 1),
    ('vdo14', N'Bảo mật cơ sở dữ liệu SQL Server', 'images/sql_sec.png', 900, N'Các kỹ thuật nâng cao để bảo mật dữ liệu T-SQL.', 1),
    ('vdo15', N'Giới thiệu về Kubernetes cơ bản', 'images/k8s_intro.png', 400, N'Hướng dẫn cài đặt và sử dụng Kubernetes cơ bản (K8s).', 1);

INSERT INTO Favorite (UserId, VideoId, LikeDate)
VALUES
    ('user01', 'vdo01', '2025-10-20'),
    ('user02', 'vdo01', '2025-11-01'),
    ('user01', 'vdo02', '2025-11-10'),
    ('user01', 'vdo04', GETDATE()),
    ('admin01', 'vdo05', GETDATE()),
    ('user02', 'vdo06', GETDATE()),
    ('user02', 'vdo04', GETDATE()),
    ('user01', 'vdo06', GETDATE()),
    ('admin01', 'vdo02', GETDATE()),
    ('user02', 'vdo05', GETDATE()),
    ('user01', 'vdo03', GETDATE()),
    ('admin01', 'vdo04', GETDATE()),
    ('user02', 'vdo02', GETDATE()),
    ('user01', 'vdo07', GETDATE()),
    ('admin01', 'vdo08', GETDATE()),
    ('user02', 'vdo09', GETDATE()),
    ('user01', 'vdo10', GETDATE()),
    ('user02', 'vdo07', GETDATE()),
    ('admin01', 'vdo07', GETDATE()),
    ('user02', 'vdo08', GETDATE()),
    ('user01', 'vdo09', GETDATE()),
    ('admin01', 'vdo10', GETDATE()),
    ('user02', 'vdo10', GETDATE());

INSERT INTO Share (UserId, VideoId, Emails, ShareDate)
VALUES
    ('user01', 'vdo02', 'friend1@mail.com,friend2@mail.com', '2025-11-11'),
    ('admin01', 'vdo01', 'colleague@work.com', '2025-11-12'),
    ('user02', 'vdo02', 'new_share_2024@mail.com', '2024-12-05'),
    ('admin01', 'vdo01', 'manager@work.com', '2024-11-14'),
    ('admin01', 'vdo01', 'ceo@work.com', '2024-11-14'),
    ('user01', 'vdo04', 'friend_css@mail.com', '2025-11-15');


-- Xóa bảng nếu đã tồn tại
IF OBJECT_ID('Logs', 'U') IS NOT NULL
    DROP TABLE Logs;
GO

-- Tạo bảng Logs
CREATE TABLE Logs (
                      Id BIGINT IDENTITY(1,1) PRIMARY KEY,
                      Url NVARCHAR(500) NOT NULL,
                      Time DATETIME2 NOT NULL,
                      Username NVARCHAR(50) NULL
);
GO

-- Thêm index để tăng tốc truy vấn
CREATE INDEX IX_Logs_Username ON Logs(Username);
CREATE INDEX IX_Logs_Time ON Logs(Time DESC);
GO

-- Xem dữ liệu
SELECT * FROM Logs ORDER BY Time DESC;
GO


