create table categories (
	created_at timestamp(6) not null,
	id bigserial not null,
	updated_at timestamp(6) not null,
	category_code VARCHAR(100) not null UNIQUE,
	category_name VARCHAR(255) not null UNIQUE,
	PRIMARY key (id)
);

create table items (
	price NUMERIC(38, 2) not null,
	category_id BIGINT not null,
	id bigserial not null,
	quantity integer not null,
	updated_at TIMESTAMP(6) not null,
	created_at TIMESTAMP(5) not null,
	fileUrl VARCHAR(255),
	name varchar(255) not null UNIQUE,
	PRIMARY KEY (id)
);

create table order_item (
	price_at_purchase NUMERIC(10, 2) not null check (price_at_purchase >= 0),
	quantity integer not null check (quantity>=1),
	id bigserial not null,
	item_id BIGINT not null,
	order_id bigint not null,
	PRIMARY KEY (id)
);

create table orders (
	created_at TIMESTAMP(6) not null,
	customer_id bigint,
	id bigserial not null,uj
	updated_at timestamp(6) not null,
	PRIMARY KEY (id)
);

create table users (
	created_at TIMESTAMP(6) not null,
	id bigserial not null,
	updated_at TIMESTAMP(6) not null,
	name VARCHAR(50) not null UNIQUE,
	email VARCHAR(255) not null UNIQUE,
	password VARCHAR(255) not null,
	PRIMARY KEY (id)
);

alter table items
add constraint fk_items_category
Foreign Key (category_id) REFERENCES categories(id);

alter table orders
add constraint fk_orders_customer
Foreign Key (customer_id) REFERENCES users(id);

alter table order_item
add constraint fk_order_item_item
Foreign Key (item_id) REFERENCES items(id);

alter table order_item
add constraint fk_order_item_order
Foreign Key (order_id) REFERENCES orders(id);


