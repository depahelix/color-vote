SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'hackathon' -- ← change this to your DB
  AND pid <> pg_backend_pid();
drop database hackathon;
create database hackathon;
CREATE USER hackathon;
ALTER USER hackathon with password 'hackathon';
GRANT ALL PRIVILEGES ON DATABASE hackathon TO hackathon;
CREATE SEQUENCE hibernate_sequence
  start 1
  increment 1;
GRANT ALL PRIVILEGES ON SEQUENCE hibernate_sequence to hackathon;
drop table vote;
create table vote (id SERIAL PRIMARY KEY, useruuid uuid UNIQUE, username varchar(50), favoritecolor varchar(50), votetimestamp timestamp);
GRANT ALL PRIVILEGES ON TABLE vote TO hackathon;
select * from vote;
delete from vote;
