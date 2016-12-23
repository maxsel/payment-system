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
  values(1, 'Breakfast Casserole from Borden', 'This hearty breakfast casserole with sausage, bacon, potatoes, eggs, and shredded cheese will serve a crowd.', 3, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(2, 'Merry Christmukkah', 'Set out a plate of these crispy golden latkes for Santa.', 5, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(3, 'Lasagne Verdi al Forno', 'Homemade sheets of spinach pasta are layered with a rich meat ragu, bechamel sauce, ricotta, and Parmesan and baked until golden and bubbly. A delicious recipe from the Emilia-Romagna region of Italy.', 7, 1, 1);
 insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(4, 'Garlic Prime Rib', 'A garlic, thyme, and olive oil marinade covers your prime rib roast for 5-star results. This “secret” recipe is a secret no more!', 3, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(5, 'Cabbage Fat-Burning Soup', 'A package of onion soup mix flavors the tomato broth in which six different vegetables are combined with shredded cabbage in this fat free, low-calorie soup.', 5, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(6, 'Best Big, Fat, Chewy Chocolate Chip Cookie', 'Make bakery-style chocolate chip cookies with these tips.', 7, 1, 1);
  
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(7, 'The Best Parmesan Chicken Bake', 'This chicken Parmesan is done casserole style (so, no breading or frying!), but still offers up that irresistible combination of tender chicken, crunchy/cheesy coating, and flavorful sauce.', 3, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(8, 'Garlic Shrimp Linguine', 'Impress your guests with this deceptively simple saute of butter, garlic, wine, Parmesan cheese and shrimp. Serve over hot linguine and garnish with chopped parsley.', 5, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(9, 'Fluffy Pancakes', 'Tall, fluffy pancakes make the best breakfast, especially when theres plenty of butter and syrup. Make it extra special with berries and whipped cream!', 7, 1, 1);
 insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(10, 'Shabbat Challah', 'My Shabbat Challah is something out of this world. I made it up on my own, because the ones I tasted, I just didnt like. Try it you will love it!! This can make 6 regular sized loaves, or two large braided loaves.', 3, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(11, 'The Best Rolled Sugar Cookies', 'Perfect for decorating! These classic sugar cookies are great for cookie-cutting and decorating during the holidays or anytime you feel festive.', 5, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(12, 'Honey-Garlic Slow Cooker Chicken Thighs', 'This is an easy slow cooker recipe for chicken thighs in a sauce made with soy sauce, ketchup, and honey.', 7, 1, 1);
  
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(13, 'The Best Sweet and Sour Meatballs', 'Beef meatballs are browned, then simmered in a sweet and sour sauce. Great as an appetizer or as a main dish served over rice.', 3, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(14, 'Simple Stromboli', 'This is a baked sandwich using frozen bread dough, meat, and cheese. My picky eaters love this for an easy weeknight meal and its great served with a soup. My family fights over the leftovers!', 5, 1, 1);
insert into item(item_id, item_title, item_desc, item_price, item_cat_id, item_status_id)
  values(15, 'Raspberry and Almond Shortbread Thumbprints', 'Shortbread thumbprint cookie filled with raspberry jam, and drizzled with glaze.', 7, 1, 1);
  



insert into order_status(status_id, status_name)
	values(1, "NEW");
insert into order_status(status_id, status_name)
	values(2, "READY");
insert into order_status(status_id, status_name)
	values(3, "REJECTED");
insert into order_status(status_id, status_name)
	values(4, "ARCHIVED");