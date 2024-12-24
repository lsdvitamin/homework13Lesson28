create table phones(
    id bigserial primary key,
    client_id int8 not null references clients(id),
    number_ varchar not null unique
);
