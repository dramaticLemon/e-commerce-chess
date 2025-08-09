create table categories (
	created_at timestamp(6) not null,
	id bigserial not null,
	update_at timestamp(6) not null,
	category_code varchar(100) not null unique,
	category_name varchar(255) not null,
	primary key (id)
);

create table items (
	price numeric(38,2) not null,
	category_id bigint not null,
	created_at timestamp(6) not null,
	id bigserial not null,
	update_at timestamp(6) not null,
	name varchar(255) not null,
	primary key (id)
);

create table order_item (
	price_at_purchase numeric(10,2) not null check (price_at_purchase>=0),
	quantity integer not null check (quantity>=1),
	id bigserial not null,
	item_id bigint not null,
	order_id bigint not null,
	primary key (id)
);

create table orders (
	created_at timestamp(6) not null,
	customer_id bigint,
	update_at timestamp(6) not null,
	id bigserial not null,
	primary key (id)
);

create table users (
	created_at timestamp(6) not null,
	id bigserial not null,
	update_at timestamp(6) not null,
	name varchar(50) not null unique,
	email varchar(255) not null unique,
	password varchar(255) not null,
	primary key (id)
);

-- create FOREIGN key 

alter table items
add constraint fk_items_category
Foreign Key (category_id) REFERENCES categofries(id);

alter table orders
add constraint fk_orders_customer
Foreign Key (customer_id) REFERENCES users(id);

alter table order_item
add constraint fk_order_item_item
Foreign Key (item_id) REFERENCES items(id);

alter table order_item
add constraint fk_order_item_order
Foreign Key (order_id) REFERENCES orders(id);
