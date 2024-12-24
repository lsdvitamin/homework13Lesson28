create table addresses(
    id bigserial primary key,
    client_id int8 not null references clients(id),
    street varchar not null unique
);