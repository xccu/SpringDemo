/*
SQLyog v10.2 
MySQL - 5.7.29-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_user` (
	`id` int (11),
	`username` varchar (60),
	`password` varchar (60),
	`age` int (11),
	`sex` char (3),
	`email` varchar (60)
); 
insert into `t_user` (`id`, `username`, `password`, `age`, `sex`, `email`) values('1','张三','123','23','女','123@test.com');
insert into `t_user` (`id`, `username`, `password`, `age`, `sex`, `email`) values('2','ybc','123','23','男','123@test.com');
insert into `t_user` (`id`, `username`, `password`, `age`, `sex`, `email`) values('3','test','111','2','男','123@test.com');
