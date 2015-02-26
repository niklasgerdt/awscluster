# awscluster


install mysql (debian/ubuntu)
-----------------------------
apt-get install mysql-server mysql-client mysql-workbench
mysqladmin -u root password 'XXX'

mysql -u root -p
grant all privileges on *.* to 'DBADMIN'@'localhost' identified by 'ZZZ' with grant option;
grant all privileges on *.* to 'DBADMIN'@'%' identified by 'ZZZ' with grant option;
flush privileges;
exit;

mysql -u DBADMIN -p
CREATE DATABASE `APP` /*!40100 DEFAULT CHARACTER SET latin1 */$$

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1$$

CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_1` (`userid`),
  CONSTRAINT `fk_order_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1$$


Rest interfaces
---------------
INFO: Mapped "{[/orders/{customerId}],methods=[GET],params=[],headers=[],consumes=[],produces=[application/json],custom=[]}"
INFO: Mapped "{[/orders/{customerId}],methods=[POST],params=[],headers=[],consumes=[application/json],produces=[application/json],custom=[]}" 
INFO: Mapped "{[/orders/{customerId}/{orderId}],methods=[PUT],params=[],headers=[],consumes=[application/json],produces=[application/json],custom=[]}" 
INFO: Mapped "{[/orders/{customerId}/{orderId}],methods=[GET],params=[],headers=[],consumes=[],produces=[application/json],custom=[]}" 
INFO: Mapped "{[/customers/],methods=[GET],params=[],headers=[],consumes=[],produces=[application/json],custom=[]}"
INFO: Mapped "{[/customers/{customerId}],methods=[GET],params=[],headers=[],consumes=[],produces=[application/json],custom=[]}" 
INFO: Mapped "{[/customers/],methods=[POST],params=[],headers=[],consumes=[application/json],produces=[application/json],custom=[]}" 
INFO: Mapped "{[/customers/{customerId}],methods=[PUT],params=[],headers=[],consumes=[application/json],produces=[application/json],custom=[]}"