
USE master;
GO
SELECT name 
FROM sys.databases
WHERE name = 'GerenciadorDB';
GO



USE GerenciadorDB;
GO
EXEC sp_addrolemember 'db_owner', 'desenv';
GO