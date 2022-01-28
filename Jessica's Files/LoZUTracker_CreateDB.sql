USE master;
go 
Create database [LoZUTracker] on primary
(Name = Lab_Database,
	Size = 6MB, 
	Filegrowth = 15%,
	Maxsize = 30MB,
	Filename = 'D:\Database\MSSQL15.MSSQLSERVER\MSSQL\DATA\LoZUTracker.mdf')

log on
(Name = Lab_Log,
	Size = 3MB,
	Filegrowth = 15%,
	Maxsize = 30MB,
	Filename = 'D:\Database\MSSQL15.MSSQLSERVER\MSSQL\DATA\LoZUTracker.ldf');
go