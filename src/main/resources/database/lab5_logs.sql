-- Lab 5: Tạo bảng Logs cho Bài 3
USE Java4_Lab4_PolyOE;
GO

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
