create schema if not exists demo2 collate utf8mb4_0900_ai_ci;

create table if not exists permission
(
  id varchar(255) not null
    primary key,
  create_date datetime(6) null,
  create_user bigint null,
  update_date datetime(6) null,
  update_user bigint null,
  enabled int null,
  name varchar(255) null
);

create table if not exists role
(
  id varchar(255) not null
    primary key,
  create_date datetime(6) null,
  create_user bigint null,
  update_date datetime(6) null,
  update_user bigint null,
  enabled varchar(255) null,
  name varchar(255) null
);

create table if not exists user
(
  id varchar(255) not null
    primary key,
  create_date datetime(6) null,
  create_user bigint null,
  update_date datetime(6) null,
  update_user bigint null,
  enabled int null,
  name varchar(255) null,
  password varchar(255) null,
  salt varchar(255) null,
  username varchar(255) null
);

