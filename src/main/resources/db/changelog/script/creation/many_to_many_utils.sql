create table if not exists announcement_tag
(
    id              serial primary key,
    announcement_id bigint,
    tag_id          bigint,
    foreign key (announcement_id) references announcement (id),
    foreign key (tag_id) references tag (id)
);

create table if not exists subscription_tag
(
    id              serial primary key,
    subscription_id bigint,
    tag_id          bigint,
    foreign key (subscription_id) references subscription (id),
    foreign key (tag_id) references tag (id)
)
