create table user_role (
  user_id bigint not null,
  role_id bigint not null,
  primary key (user_id, role_id),
  constraint user_id_fk foreign key (user_id) references db.user (user_id),
  constraint role_id_fk foreign key (role_id) references db.role (role_id)
);


insert into db.role (role_id, role_description) VALUES (1, 'ADMIN');
insert into db.role (role_id, role_description) VALUES (2, 'USER');

insert into db.user (user_name, user_login, user_password) VALUES ('Bob', 'bob', 'bob');
insert into db.user (user_name, user_login, user_password) VALUES ('Dog', 'dog', 'dog');
insert into db.user (user_name, user_login, user_password) VALUES ('Cat', 'cat', 'cat');

insert into db.user_role (user_id, role_id) VALUES (1, 1);
insert into db.user_role (user_id, role_id) VALUES (1, 2);
insert into db.user_role (user_id, role_id) VALUES (2, 2);
insert into db.user_role (user_id, role_id) VALUES (3, 2);