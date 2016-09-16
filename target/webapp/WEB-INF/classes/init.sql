create database oa_20160901 DEFAULT CHARACTER set utf8;

DROP USER  'wxn'@'localhost' ;
CREATE user 'wxn'@'localhost' identified by 'wxn';
GRANT all on os_20160901.* TO  'wxn'@'localhost';

