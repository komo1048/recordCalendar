drop table if exists calendar;
drop table if exists member;

CREATE TABLE calendar(
	id int auto_increment,
	title varchar(50),
	content varchar(2000),
	start varchar(50),
	end varchar(50),
	primary key (id)
);