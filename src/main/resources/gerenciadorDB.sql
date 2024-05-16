-- Crie o banco de dados, se não existir
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'GerenciadorDB')
BEGIN
    CREATE DATABASE GerenciadorDB;
END
GO

-- Use o banco de dados recém-criado ou o existente
USE GerenciadorDB;
GO

-- Crie a tabela "Tasks", se não existir
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'Tasks')
BEGIN
    CREATE TABLE Tasks (
        id INT IDENTITY(1,1) PRIMARY KEY,
        title NVARCHAR(255) NOT NULL,
        description NVARCHAR(MAX) NULL,
        creation_date DATETIME NOT NULL DEFAULT GETDATE(),
        status NVARCHAR(50) NOT NULL
    );
END
GO