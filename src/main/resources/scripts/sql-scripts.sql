select * from categories;
insert into categories (created_at, update_at, category_code, category_name)
values
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ELEC', 'Electronic'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BOOK', 'Book'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'CLOT', 'Cloth'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'FOOD', 'Food'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TOYS', 'Toys');


select * from items;

insert into items (price, category_id, created_at, update_at, name) values
  (499.99, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Смартфон'),
  (29.90, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Роман "Война и мир"'),
  (79.50, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Футболка'),
  (12.30, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Батон хлеба'),
  (150.00, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Конструктор LEGO');


select i.id, i.name, i.price, i.created_at, i.update_at, c.category_name
from items i
join categories c on i.category_id = c.id;

select * from users;

insert into users (created_at, update_at, name, email, password) values
  (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Иван Иванов', 'ivan@example.com', 'password1'),
  (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Мария Петрова', 'maria@example.com', 'password2'),
  (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Алексей Смирнов', 'alexey@example.com', 'password3'),
  (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Ольга Кузнецова', 'olga@example.com', 'password4'),
  (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Дмитрий Орлов', 'dmitry@example.com', 'password5');

insert into orders (customer_id) values (1), (2), (3);


insert into order_item (price_at_purchase, quantity, item_id, order_id) values
  (1000.00, 1, 1, 1),  
  (500.00, 2, 2, 1),   
  (200.00, 1, 3, 2),   
  (150.00, 5, 4, 3),   
  (50.00, 10, 5, 3);   


select * from order_item;

select oi.price_at_purchase, oi.quantity, i.name, order_id
from order_item oi
join items i on oi.item_id = i.id;

-- example combinate join
select 
  oi.price_at_purchase,
  oi.quantity,
  i.name as item_name,
  u.name as user_name
from order_item oi
join items i on oi.item_id = i.id
join orders o on oi.order_id = o.id
join users u on o.customer_id = u.id
limit 1;


-- get all user items_order where user_id = 1 and order_id = 1
SELECT
  oi.price_at_purchase,
  oi.quantity,
  i.name as item_name,
  o.id as order_id,
  u.name as user_name
from order_item oi
join items i on oi.item_id = i.id
join orders o on oi.order_id = o.id
join users u on o.customer_id = u.id
where o.id = 1
  and u.id = 1;
