create table food (
    id text not null unique,
    name varchar(254) not null,

    primary key (id)
);

insert into food (id, name) values ('d670166d-076e-4b71-a22b-cb913b9e992d','Lasagna');
insert into food (id, name) values ('e8e70836-0bf4-42df-bac8-51a6a3310745','Pizza');
insert into food (id, name) values ('f64cc9fe-8ad8-4a9a-963e-4c9b872f7e48','Hot dog');
insert into food (id, name) values ('24bccca3-76c8-4981-9d2f-7a2e302f6570','Hamburguers');
insert into food (id, name) values ('fc7e0921-636d-425b-adbc-3065242bf6eb','Spaghetti');
insert into food (id, name) values ('8c124e87-41d4-46bb-9a01-55fb0f786ce7','Cheese');