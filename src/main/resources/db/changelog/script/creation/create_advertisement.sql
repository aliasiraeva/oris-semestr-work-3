create table if not exists advertisement (
    id serial primary key,
    image_url varchar(500) not null,
    short_text varchar(100) not null,
    website_url varchar(500)
)