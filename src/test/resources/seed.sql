delete from item;
delete from cart;

insert into cart(cart_id, customer_id, created_at,tenant,total_price) values(1,'C0001', now(), 'testTenant', 50.6);
insert into item(code, name, unit_price, quantity, cart_id) values ('I0001', 'Item1', 10.0, 2, 1);
insert into item(code, name, unit_price, quantity, cart_id) values ('I0002', 'Item2', 10.2, 3, 1);