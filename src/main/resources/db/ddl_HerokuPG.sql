INSERT INTO public.role (name) VALUES ('SUPER_USER');
INSERT INTO public.role (name) VALUES ('ADMIN');
INSERT INTO public.role (name) VALUES ('KITCHEN_STAFF');
INSERT INTO public.role (name) VALUES ('DELIVERY_STAFF');
INSERT INTO public.role (name) VALUES ('BUSINESS_ANALYST');

INSERT INTO public.employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Dmytro Burdyga', 'super@erestaurant.com', '8FePHnF0saQcTqjG4X96ijuIySo=', '1984-06-27', '2010-01-01', TRUE, 1, NULL);
INSERT INTO public.employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Bogdana Tishkina', 'admin@erestaurant.com', 'i+UhJqb95FCnFio2UdWJu1HpV50=', '1987-07-02', '2011-06-10', TRUE, 2, NULL);
INSERT INTO public.employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Elena Bakhmach', 'kitchen@erestaurant.com', '3ipNV1GrBtxPmHFC21fCbVCSXIo=', '1991-04-05', '2014-02-03', TRUE, 3, NULL);
INSERT INTO public.employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Igor Himchenko', 'delivery@erestaurant.com', 'LbTBgR9CRYKpD41+53mVzwGNlEM=', '1993-03-03', '2014-02-28', TRUE, 4, NULL);
INSERT INTO public.employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Olga Tkachuk', 'business@erestaurant.com', 'nlymsP+0F5l/+4RMdvnCS7wg/og=', '1979-11-15', '2010-01-20', TRUE, 5, NULL);
INSERT INTO public.employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Larisa Dmitrieva', 'business2@erestaurant.com', 'uN5t8VYc16tq820MtwayFotPTGk=', '1989-01-27', '2011-11-20', FALSE, 5, NULL);

INSERT INTO public.dish_category (name, description)
VALUES ('SOUP',
        'Soup is a primarily liquid food, served warm (some kinds are cool cool or cold), made by combining ingredients such as meat and vegetables, juice, water, or another liquid.');
INSERT INTO public.dish_category (name, description)
VALUES ('SALAD',
        'Salad is a cold dish of various mixtures of raw and cooked vegetables, seasoned with oil, vinegar, or other dressing and sometimes accompanied by meat, fish, and other ingredients.');
INSERT INTO public.dish_category (name, description)
VALUES ('MAIN_COURSE',
        'The main course is the featured or primary dish in a meal consisting of several courses, it is the most substantial course of a meal.');
INSERT INTO public.dish_category (name, description)
VALUES ('DESSERT',
        'Dessert is the sweet course eaten at the end of a meal, such as: cake, pie, fruit, pudding, ice cream, etc. It is the fourth "meal" of the day following breakfast, lunch, and dinner.');
INSERT INTO public.dish_category (name, description)
VALUES ('DRINK',
        'Here you can order different cold beverages like juices, lemonades, soda, mineral water, and also some hot drinks like tea, espresso, latte, mochaccino.');

-- soups --
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Tomato Soup',
        'Tomato soup is a soup made with tomatoes as the primary ingredient. It may be served hot or cold in a bowl.',
        4.25, TRUE, TRUE, 1, 1);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Cream of mushroom soup',
        'Cream of mushroom soup is a soup where a basic roux is thinned with milk and then mushrooms and mushroom broth are added.',
        5.40, TRUE, TRUE, 2, 1);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Miso soup',
        'Miso soup is a traditional Japanese soup consisting of a stock called "dashi" into which softened miso paste is mixed.',
        3.65, TRUE, TRUE, 3, 1);

-- salads --
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Greek Salad',
        'Greek salad is made with tomatoes, cucumbers, onion, feta cheese and olives, seasoned with salt and oregano and dressed with olive oil.',
        5.50, TRUE, TRUE, 4, 2);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Avocado and Tuna Tapas',
        'This is a light, healthy Spanish tapa that goes best with crisp white wines and crunchy bread.',
        7.35, TRUE, TRUE, 5, 2);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Caesar salad',
        'It is a salad of romaine lettuce and croutons dressed with parmesan cheese, lemon juice, olive oil, egg, garlic, and black pepper.',
        4.30, TRUE, TRUE, 6, 2);

-- main course --
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Baked Spaghetti',
        'Casserole of thin spaghetti pasta, sausage, mushroom, and tomato sauce, ricotta, Parmesan, and Mozzarella cheeses.',
        7.25, TRUE, TRUE, 7, 3);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Beef Bourguignon',
        'Luxurious beef bourguignon, or beef Burgundy, with beef chuck, carrots, pearl onions, mushrooms, and coated with a deeply flavored, silky sauce.',
        8.25, TRUE, TRUE, 8, 3);
INSERT INTO public.dish (NAME, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Wild Salmon',
        'Grilled whole wild salmon, stuffed with a relish made of preserved lemons, parsley, dill, and shallots.',
        8.90, TRUE, TRUE, 9, 3);

-- desserts --
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Chocolate Fondue',
        'Chocolate fondue is easy and fun to make, a party favorite.  Dip fresh fruit and other dippables into the hot, melted creamy chocolate mixture.',
        5.15, TRUE, TRUE, 10, 4);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Tapioca Pudding',
        'One of our favorite desserts growing up! This tapioca pudding recipe uses small pearl tapioca, milk, sugar, eggs, and vanilla.',
        4.20, TRUE, TRUE, 11, 4);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Fruit Salad',
        'Blueberry, peach, nectarine fruit salad seasoned with fresh thyme, ginger, and lemon juice.',
        4.40, TRUE, TRUE, 12, 4);

-- drinks --
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Latte',
        'A Latte is a type of coffee made with espresso and hot steamed milk, milkier than a cappuccino.',
        4.90, TRUE, TRUE, 13, 5);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Tea',
        'Aromatic beverage prepared by pouring hot water over cured leaves of tea leaves.',
        3.45, TRUE, TRUE, 14, 5);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Fresh Juice',
        'Natural and healthy juices from different fruits and vegetables. Can be mixed in different variations.',
        4.50, TRUE, TRUE, 15, 5);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Cola',
        'A typical brown carbonated drink that is flavoured with an extract of cola nuts, or with a similar flavouring.',
        2.95, FALSE, TRUE, 16, 5);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Mineral water',
        'Water that contains mineral salts and natural carbon dioxide.',
        4.50, FALSE, TRUE, 17, 5);
INSERT INTO public.dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Beer',
        'An alcoholic drink made from yeast-fermented malt flavoured with hops.',
        5.50, FALSE, FALSE, 18, 5);

INSERT INTO public.customer (name, email, password, address, birthDate, photo_id)
VALUES ('Olga Romanova', 'olga.romanova@gmail.com', '8FePHnF0saQcTqjG4X96ijuIySo=', '27 Obolonsky Ave., App. 34, Kyiv', '1995-04-05', NULL);
INSERT INTO public.customer (name, email, password, address, birthDate, photo_id)
VALUES ('Igor Shevchenko', 'igor.shevchenko@yahoo.com', 'i+UhJqb95FCnFio2UdWJu1HpV50=', '4 Khreschatik Str., App. 12, Kyiv', '1990-01-25', NULL);
INSERT INTO public.customer (name, email, password, address, birthDate, photo_id)
VALUES ('Kate Belova', 'kate.belova@gmail.com', '3ipNV1GrBtxPmHFC21fCbVCSXIo=', '3 Verbova Str., App. 7, Kyiv', '1987-01-17', NULL);
INSERT INTO public.customer (name, email, password, address, birthDate, photo_id)
VALUES ('Roman Karetskiy', 'roman.karetskiy@gmail.com', 'LbTBgR9CRYKpD41+53mVzwGNlEM=', '36 Ivana Lepse Str., App. 14, Kyiv', '1989-03-07', NULL);
INSERT INTO public.customer (name, email, password, address, birthDate, photo_id)
VALUES ('Oksana Alekseeva', 'oksana.alekseeva@gmail.com', 'nlymsP+0F5l/+4RMdvnCS7wg/og=', '29 Verkhniy Val Str., App. 4, Kyiv', '1982-02-18', NULL);
INSERT INTO public.customer (name, email, password, address, birthDate, blocked, photo_id)
VALUES ('Sergey Butenko', 'sergey.butenko@gmail.com', 'uN5t8VYc16tq820MtwayFotPTGk=', '2 Polarna Str., App. 67, Kyiv', '1991-08-08', TRUE, NULL);

INSERT INTO public.orders_status (name)
VALUES ('KITCHEN_DONE');
INSERT INTO public.orders_status (name)
VALUES ('NON-KITCHEN_DONE');
INSERT INTO public.orders_status (name)
VALUES ('READY_FOR_SHIPMENT');
INSERT INTO public.orders_status (name)
VALUES ('DELIVERING');
INSERT INTO public.orders_status (name)
VALUES ('DELIVERED');

INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '7 days', localtimestamp - INTERVAL '7 days' + INTERVAL '45 minutes', 22.20, 5, 1);
INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '6 days', localtimestamp - INTERVAL '6 days' + INTERVAL '35 minutes', 8.00, 5, 2);
INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '5 days', localtimestamp - INTERVAL '5 days' + INTERVAL '30 minutes', 3.60, 5, 3);
INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '4 days', localtimestamp - INTERVAL '4 days' + INTERVAL '40 minutes', 51.90, 5, 4);
INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '3 days', localtimestamp - INTERVAL '3 days' + INTERVAL '35 minutes', 22.40, 4, 5);
INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '2 days', localtimestamp - INTERVAL '2 days' + INTERVAL '40 minutes', 16.90, 3, 4);
INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '1 days', localtimestamp - INTERVAL '1 days' + INTERVAL '30 minutes', 15.30, 2, 5);

INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '9 days', localtimestamp - INTERVAL '1 days' + INTERVAL '30 minutes', 50.25, 5, 5);
INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '8 days', localtimestamp - INTERVAL '1 days' + INTERVAL '30 minutes', 33.1, 5, 5);
INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '3 days', localtimestamp - INTERVAL '1 days' + INTERVAL '30 minutes', 33.25, 5, 5);
INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '2 days', localtimestamp - INTERVAL '1 days' + INTERVAL '30 minutes', 17.70, 5, 5);
INSERT INTO public.orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
  VALUES (localtimestamp - INTERVAL '1 days', localtimestamp - INTERVAL '1 days' + INTERVAL '30 minutes', 19.90, 5, 5);


INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (2, 8.40, TRUE, 1, 1);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 5.40, FALSE, 2, 1);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 5.40, FALSE, 2, 2);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 3.60, TRUE, 3, 3);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 5.50, TRUE, 4, 4);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (2, 14.60, TRUE, 8, 4);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (2, 8.60, TRUE, 6, 4);
--
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 4.20, TRUE, 1, 5);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 5.50, TRUE, 4, 5);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 8.20, TRUE, 8, 5);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 3.50, FALSE, 14, 5);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 4.50, FALSE, 18, 5);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 5.50, FALSE, 15, 5);
--
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 2.90, TRUE, 16, 6);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 4.30, TRUE, 9, 6);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 4.20, TRUE, 1, 6);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 5.50, TRUE, 4, 6);
--
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 5.50, TRUE, 1, 7);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 5.50, TRUE, 4, 7);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 4.30, FALSE, 6, 7);
--
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (3, 26.70, TRUE, 9, 8);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (2, 9.80, TRUE, 13, 8);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 3.45, TRUE, 14, 8);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (2, 10.30, TRUE, 10, 8);
--
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (2, 8.60, TRUE, 6, 9);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 7.25, TRUE, 7, 9);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 8.25, TRUE, 8, 9);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (2, 9.00, TRUE, 15, 9);
--
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (4, 16.80, TRUE, 11, 10);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (2, 9.00, TRUE, 15, 10);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 2.95, TRUE, 16, 10);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 4.50, TRUE, 17, 10);
--
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 5.40, TRUE, 2, 11);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 7.35, TRUE, 5, 11);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 4.95, TRUE, 13, 11);
--
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (2, 8.80, TRUE, 12, 12);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (1, 4.20, TRUE, 11, 12);
INSERT INTO public.order_dishes (quantity, price, readiness, dish_id, orders_id)
  VALUES (2, 6.90, TRUE, 14, 12);