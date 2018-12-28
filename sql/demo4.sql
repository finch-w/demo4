create schema if not exists demo4 collate utf8mb4_0900_ai_ci;

create table if not exists permission
(
  id tinytext null,
  create_date timestamp null,
  create_user bigint null,
  update_date timestamp null,
  update_user bigint null,
  enabled int null,
  name tinytext null
);

create table if not exists role
(
  id tinytext null,
  create_date timestamp null,
  create_user bigint null,
  update_date timestamp null,
  update_user bigint null,
  enabled tinytext null,
  name tinytext null
);

create table if not exists user
(
  id tinytext null,
  create_date timestamp null,
  create_user bigint null,
  update_date timestamp null,
  update_user bigint null,
  enabled int null,
  name tinytext null,
  password tinytext null,
  salt tinytext null,
  username tinytext null
);

