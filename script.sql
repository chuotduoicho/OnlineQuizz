create database OnlineQuizLab

use OnlineQuizLab



create table [Users] (
userName varchar(50) primary key,
[password] varchar(50) not null,
RoleUser int not null, -- 1 for teacher, 0 for student
Email varchar(100) not null
)

create table Questions(
questionCode int identity(1,1) primary key,
questionContent nvarchar(255) not null,
createdAt date default getdate(),
)

create table Options(
questionCode int foreign key references Questions(questionCode),
optionCode int not null,
primary key (questionCode,optionCode),
optionContent nvarchar(255) not null
)

create table Answers(
questionCode int foreign key references Questions(questionCode),
optionCode int not null,
primary key (questionCode,optionCode),
)


insert into Questions(questionContent)
values(N'What is Long`s name?')
SELECT @@IDENTITY 


insert into Options(questionCode,optionCode,optionContent)
values(1,1,'Nam')
insert into Options(questionCode,optionCode,optionContent)
values(1,2,'Hoang')
insert into Options(questionCode,optionCode,optionContent)
values(1,3,'Hai')

insert into Answers(questionCode,optionCode)
values(1,2)

select * from Questions

create procedure getQuestionsWithPagging(
@pageNumber int,
@pageSize int
)
as
begin
 SELECT * FROM ( 
  SELECT q.questionCode, q.questionContent,q.createdAt
  , ROW_NUMBER() OVER(ORDER by q.questionCode) as row 
  FROM Questions q
 ) q WHERE q.row >= (@pageNumber-1)*@pageSize+1 and q.row <= @pageSize*@pageNumber 
end

exec getQuestionsWithPagging
@pageNumber = 1,
@pageSize = 2

select COUNT(questionCode) from Questions

SELECT * FROM ( 
  SELECT q.questionCode, q.questionContent,q.createdAt
  , ROW_NUMBER() OVER(ORDER by q.questionCode) as row 
  FROM Questions q
 ) q WHERE q.row >= (1-1)*3+1 and q.row <= 1*3 


 -- code get Random result 
 select top 10 * from Questions
 ORDER BY NEWID()

 delete from Answers
 where questionCode= 26

 delete from Options
 where questionCode=26

 delete from Questions
 where questionCode = 1


 select top 4 * from Options
 where questionCode=23

 select top 4 * from Answers
  where questionCode=23

  select top 1 * from Questions
  where questionCode=23

  SELECT * FROM ( 
  SELECT q.questionCode, q.questionContent,q.createdAt
  , ROW_NUMBER() OVER(ORDER by NEWID()) as row 
  FROM Questions q
 ) q WHERE  q.row <= 100


 create table Result (
 resultCode int identity(1,1) primary key,
 userName varchar(50) foreign key references [Users](userName),
 score float,
 submitTime datetime default getdate()
 )

 insert into Result(userName,score)
 values('sa',6.3)

 select * from Result