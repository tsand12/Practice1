DROP TABLE IF EXISTS USER;
CREATE SEQUENCE SEQ_USER_ID START WITH 1 INCREMENT BY 1;
CREATE TABLE USERS(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    age int(3) not null
);

create sequence users_sequence start with 1 increment by 1;

insert into USERS (first_name, last_name, age) values ('Joseph', 'Tyler', 25);
insert into USERS (first_name, last_name, age) values ('Hayley', 'Paramore', 30);
insert into USERS (first_name, last_name, age) values ('Allen', 'Wagner', 27);
insert into USERS (first_name, last_name, age) values ('Mark', 'Mars', 39);
insert into USERS (first_name, last_name, age) values ('Bruno', 'Ronson', 35);


