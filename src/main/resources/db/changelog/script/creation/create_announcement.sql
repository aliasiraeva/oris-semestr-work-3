--liquibase formatted sql
--changeset aliya:script01

create table if not exists announcement
(
    id           serial primary key,
    user_id      bigint,
    category     varchar(10) not null,
    text         varchar(500) not null,
    image_url    varchar(500),
    lat          decimal,
    lng          decimal,
    address      varchar(200),
    phone_number varchar(20),
    date         timestamp not null,
    check (length(phone_number) > 10 and length(phone_number) < 13),
    foreign key (user_id) references "user" (id)
);

