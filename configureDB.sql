USE enablingkeyword_search;
DROP TABLE IF EXISTS registration;
DROP TABLE IF EXISTS userregistration;
DROP TABLE IF EXISTS fileupload_filters;
DROP TABLE IF EXISTS fileupload;

CREATE TABLE if not exists registration (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  mobile VARCHAR(255) NOT NULL,
  dob VARCHAR(255) NOT NULL,
  pincode VARCHAR(255) NOT NULL,
  PRIMARY KEY (id));
  

CREATE TABLE if not exists userregistration (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  mobile VARCHAR(255) NOT NULL,
  dob VARCHAR(255) NOT NULL,
  pincode VARCHAR(255) NOT NULL,
  PRIMARY KEY (id));
  

CREATE TABLE if not exists fileupload (
  id INT NOT NULL AUTO_INCREMENT,
  uname TEXT NOT NULL,
  filename VARCHAR(255) NOT NULL UNIQUE,
  filesize VARCHAR(255) NOT NULL,
  filePath VARCHAR(255) NOT NULL,
  appkey TEXT NOT NULL,
  indexval VARCHAR(255) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE if not exists fileupload_filters (
  filterid INT NOT NULL AUTO_INCREMENT,
  fileuploadid INT,
  FOREIGN KEY (fileuploadid) REFERENCES fileupload(id),
  trapdoor TEXT,
  PRIMARY KEY(filterid));

CREATE INDEX keyWordIndex on fileupload(appkey);
CREATE INDEX keyWordIndex on fileupload_filters(trapdoor);
