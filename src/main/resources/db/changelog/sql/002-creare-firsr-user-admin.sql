insert into roles (name) values ('ROLE_USER');
insert into roles (name) values ('ROLE_ADMIN');

insert into users
(name, email, password, created_at, updated_at)
values ('admin', 'admin@example.com', '$2a$10$fh8vvnbcnG/1NpA5YpUsxO3pP4UhVayC2J5D5jc0ATmgA6fKg.wXK', now(), now());

insert into user_roles(user_id, role_id)
select u.id, r.id
from users u, roles r
where u.name = 'admin' and r.name = 'ROLE_ADMIN';
