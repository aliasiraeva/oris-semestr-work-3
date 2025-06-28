create table if not exists subscription (
    id serial primary key,
    user_id bigint,
    title varchar(50),
    announcement_category varchar(10) not null,
    status varchar(20) not null,
    check (status in ('AVAILABLE', 'UNAVAILABLE', 'EXPIRED')),
    foreign key (user_id) references "user"(id)
)