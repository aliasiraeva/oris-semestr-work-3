create table if not exists "user" (
    id serial primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null ,
    phone_number varchar(20) not null unique,
    role varchar(10) default 'USER',
    password varchar(255) not null ,
    check (role in ('USER', 'ADMIN')),
    check (length(phone_number) > 10 and length(phone_number) < 13)
);
