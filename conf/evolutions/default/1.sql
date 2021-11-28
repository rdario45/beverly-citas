-- Users schema

-- !Ups

CREATE TABLE clients (
  id serial PRIMARY KEY,
  name varchar(45) NOT NULL,
  address varchar(45) DEFAULT NULL,
  telephone varchar(45) NOT NULL,
  referred integer DEFAULT NULL,
  status varchar(45) NOT NULL,
  discount NUMERIC(3,2) DEFAULT 0
);

-- !Downs

DROP TABLE clients;