delete from order_history;
delete from order_status;
delete from order_has_item;	
delete from `order`;
delete from item_in_cart;
delete from item;
delete from item_category;
delete from item_status;
delete from user_has_role;
delete from role;
delete from user;


insert into role(role_id, role_name) values(1, 'ROLE_ADMIN');
insert into role(role_id, role_name) values(2, 'ROLE_USER');

insert into user(user_id, user_login, user_password, card_id, blocked)
  values(1, 'admin', 'admin', 1234123412341234, 0);
  
insert into user(user_id, user_login, user_password, card_id, blocked)
  values(2, 'user', 'user', 1234123412341234, 0);
  
insert into user_has_role(user_id, role_id) values(1, 1);
insert into user_has_role(user_id, role_id) values(2, 2);

insert into item_category(cat_id, cat_name) values(1, 'UNSORTED');
insert into item_category(cat_id, cat_name) values(2, 'FOOD');	

insert into item_status(status_id, status_name) values(1, 'AVAILABLE');
insert into item_status(status_id, status_name) values(2, 'NOT_AVAILABLE');

insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(1, 'item1', 'desc1', 3, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(2, 'item2', 'desc2', 5, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(3, 'item3', 'desc3', 7, 1, 2);


insert into order_status(status_id, status_name)
	values(1, "NEW");
insert into order_status(status_id, status_name)
	values(2, "READY");
insert into order_status(status_id, status_name)
	values(3, "REJECTED");
insert into order_status(status_id, status_name)
	values(4, "ARCHIVED");