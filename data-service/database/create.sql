create database if not exists `test-db`;
use `test-db`;

drop table if exists `Employee`;
create table if not exists `Employee` (
	`id` int not null auto_increment,
    `name` varchar(100),
    `age` int default 0,
    `gender` enum('M', 'W'),
    `hireDate` date,
    `department_id` INT not null,
    primary key(`id`)
);

drop table if exists `Department`;
create table `Department` (
	`id` int not null auto_increment,
    `name` varchar(100) not null,
    primary key(`id`)
);

drop table if exists `EmployeeDepartment`;



insert into `Department`
values (1, 'Sales'), (2, 'Managment'), (3, 'QA');


insert into `Employee`
(`id`, `name`, `age`, `gender`, `hireDate`, `department_id`)
values
	(1, 'Vasya', 25, 'M', '2006-04-21', 1),
    (2, 'Petya', 25, 'M', '2016-05-04', 1),
    (3, 'Sveta', 25, 'W', '2010-04-10', 2),
    (4, 'Kostya', 25, 'M', '2010-04-10', 3);
