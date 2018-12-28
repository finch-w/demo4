create schema if not exists demo1 collate utf8mb4_0900_ai_ci;

create table if not exists permission
(
  ID varchar(255) not null
    primary key,
  CREATEDATE datetime null,
  CREATEUSER varchar(255) null,
  ENABLED int null,
  NAME varchar(255) null,
  UPDATEDATE datetime null,
  UPDATEUSER varchar(255) null
);

create table if not exists role
(
  ID varchar(255) not null
    primary key,
  CREATEDATE datetime null,
  CREATEUSER varchar(255) null,
  ENABLED varchar(255) null,
  NAME varchar(255) null,
  UPDATEDATE datetime null,
  UPDATEUSER varchar(255) null
);

create table if not exists user
(
  ID varchar(255) not null
    primary key,
  CREATEDATE datetime null,
  CREATEUSER varchar(255) null,
  ENABLED int null,
  NAME varchar(255) null,
  PASSWORD varchar(255) null,
  SALT varchar(255) null,
  UPDATEDATE datetime null,
  UPDATEUSER varchar(255) null,
  USERNAME varchar(255) null
);

