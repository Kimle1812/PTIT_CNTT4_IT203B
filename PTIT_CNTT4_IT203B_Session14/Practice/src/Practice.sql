CREATE DATABASE thuc_hanh_session14;
use thuc_hanh_session14;


CREATE TABLE Accounts(
AccountId VARCHAR(10) PRIMARY KEY,
FullName NVARCHAR(50),
Balance DECIMAL(18, 2)
);

INSERT INTO Accounts VALUES
('ACC01', 'NguyenVanA', 5000), ('ACC02', 'Tran Thi B',2000);

DELIMITER //
CREATE PROCEDURE sp_UpdateBalance(
	Id VARCHAR(10),
	Amount DECIMAL(18,2)
)
BEGIN
UPDATE Accounts SET Balance = Balance + Amount WHERE AccountId = Id;
END//