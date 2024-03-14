INSERT INTO categories (name) VALUES ('фрукты');
INSERT INTO categories (name) VALUES ('овощи');
INSERT INTO categories (name) VALUES ('напитки');
INSERT INTO categories (name) VALUES ('молочка');
INSERT INTO categories (name) VALUES ('крупы');

insert into products (name, description, price, category_id)
                values ('яблоки','зеленые',10.20, 1);
insert into products (name, description, price, category_id)
                values ('груши','сезонные',15.10,1);
insert into products (name, description, price, category_id)
                values ('помидоры','черри',20.60,2);
insert into products (name, description, price, category_id)
                values ('огурцы','тепличные',230.00,2);
insert into products (name, description, price, category_id)
                values ('сок','виноградный',50.10,3);
insert into products (name, description, price, category_id)
                values ('вода','минеральная',12.50,3);
insert into products (name, description, price, category_id)
                values ('молоко','жирное',95.20,4);
insert into products (name, description, price, category_id)
                values ('творог','обезжиренный',100.30,4);
insert into products (name, description, price, category_id)
                values ('греча','импортная',60.50,5);
insert into products (name, description, price, category_id)
                values ('рис','белый',70.10,5);