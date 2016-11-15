CREATE TABLE User (
  User_name varchar(30) DEFAULT NULL,
  User_id int(11) NOT NULL AUTO_INCREMENT,
  uemail varchar(60) DEFAULT NULL,
  ucontact bigint(15) DEFAULT NULL,
  PRIMARY KEY (User_id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8


INSERT INTO `User` (`User_name`,`uemail`,`ucontact`) VALUES ('Sindhya','ss@gmail.com ',87659568);
INSERT INTO `User` (`User_name`,`uemail`,`ucontact`) VALUES ('Nisha','nn@gmail.com ',99959568);

select * from test.User;