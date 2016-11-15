##################################
# MySQL Databases For clouddatabase #
##################################

DROP DATABASE IF EXISTS CloudDatabase;

CREATE DATABASE CloudDatabase;

USE CloudDatabase;

#
# Structure for table healthFiles : 
#

DROP TABLE IF EXISTS healthFiles;

CREATE TABLE healthFiles (
  fileid int(11) NOT NULL auto_increment,
  userid int(11) NOT NULL default '0',
  filename varchar(40) NOT NULL default '',
  fcreationdate date NOT NULL default '0000-00-00',
  secpolicy varchar(100) NOT NULL default '',
  PRIMARY KEY  (fileid),
  UNIQUE KEY UserID (userid)
);


