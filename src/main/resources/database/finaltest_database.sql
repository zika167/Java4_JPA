-- Tạo database cho Final Test
CREATE DATABASE Java4_FinalTest;
GO

USE Java4_FinalTest;
GO

-- Tạo bảng Product
CREATE TABLE Product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    quantity INT NOT NULL
);
GO

-- Thêm dữ liệu mẫu
INSERT INTO Product (name, price, quantity) VALUES
(N'Laptop Dell', 15000000, 10),
(N'iPhone 15', 25000000, 20),
(N'Samsung Galaxy S24', 22000000, 15),
(N'MacBook Pro', 35000000, 5),
(N'iPad Air', 18000000, 12);
GO
