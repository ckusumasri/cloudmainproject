CREATE TABLE vendor (
  vendor_name varchar(30) DEFAULT NULL,
  vendor_id int(11) NOT NULL AUTO_INCREMENT,
  email varchar(60) DEFAULT NULL,
  contact bigint(15) DEFAULT NULL,
  PRIMARY KEY (vendor_id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8




INSERT INTO `vendor` (`vendor_name`,`email`,`contact`) VALUES ('kusuma','kusuma@gmail.com ',87659568);
INSERT INTO `vendor` (`vendor_name`,`email`,`contact`) VALUES ('archana','archana@gmail.com ',99959568);



