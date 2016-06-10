
-- CREATE TABLES --

DROP TABLE IF EXISTS order_dishes;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS dish_category;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS orders_status;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS photo;

CREATE TABLE role (
  id   IDENTITY PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE photo (
  id      IDENTITY PRIMARY KEY,
  content BLOB
);

CREATE TABLE employee (
  id        IDENTITY PRIMARY KEY,
  name      VARCHAR(100) NOT NULL,
  email     VARCHAR(100) NOT NULL UNIQUE,
  password  VARCHAR(100) NOT NULL,
  birthDate DATE,
  hireDate  DATE         NOT NULL,
  ready     BOOLEAN      NOT NULL,
  role_id   INT,
  photo_id  INT,
  FOREIGN KEY (role_id) REFERENCES role (id) ON UPDATE CASCADE,
  FOREIGN KEY (photo_id) REFERENCES photo (id) ON DELETE CASCADE
);

CREATE TABLE dish_category (
  id          IDENTITY PRIMARY KEY,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL,
);

CREATE TABLE dish (
  id               IDENTITY PRIMARY KEY,
  name             VARCHAR(100)   NOT NULL,
  description      VARCHAR(500)   NOT NULL,
  price            DECIMAL(15, 2) NOT NULL,
  kitchenmade      BOOLEAN        NOT NULL,
  available        BOOLEAN        NOT NULL,
  photo_id         INT,
  dish_category_id INT,
  FOREIGN KEY (dish_category_id) REFERENCES dish_category (id) ON DELETE CASCADE,
  FOREIGN KEY (photo_id) REFERENCES photo (id) ON DELETE CASCADE
);

CREATE TABLE customer (
  id        IDENTITY PRIMARY KEY,
  name      VARCHAR(100) NOT NULL,
  email     VARCHAR(100) NOT NULL UNIQUE,
  password  VARCHAR(100) NOT NULL,
  address   VARCHAR(500) NOT NULL,
  birthDate DATE,
  blocked   BOOLEAN      NOT NULL DEFAULT FALSE,
  photo_id  INT,
  FOREIGN KEY (photo_id) REFERENCES photo (id) ON DELETE CASCADE
);

CREATE TABLE orders_status (
  id   IDENTITY PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
);

CREATE TABLE orders (
  id                  IDENTITY PRIMARY KEY,
  date_time_taken     TIMESTAMP      NOT NULL,
  date_time_delivered TIMESTAMP,
  total_price         DECIMAL(15, 2) NOT NULL,
  orders_status_id    INT DEFAULT 1,
  customer_id         INT,
  FOREIGN KEY (orders_status_id) REFERENCES orders_status (id) ON DELETE CASCADE,
  FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

CREATE TABLE order_dishes (
  id        IDENTITY PRIMARY KEY,
  quantity  INT            NOT NULL DEFAULT 2,
  price     DECIMAL(15, 2) NOT NULL,
  readiness BOOLEAN        NOT NULL DEFAULT FALSE,
  dish_id   INT,
  orders_id INT,
  FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE,
  FOREIGN KEY (orders_id) REFERENCES orders (id) ON DELETE CASCADE
);

-- FILL TABLES --

INSERT INTO role (name) VALUES ('SUPER_USER');
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('KITCHEN_STAFF');
INSERT INTO role (name) VALUES ('DELIVERY_STAFF');
INSERT INTO role (name) VALUES ('BUSINESS_ANALYST');

INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\01_Tomato-Soup.jpg'));
INSERT INTO photo (content) VALUES
  (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\02_Cream-Of-Mushroom-Soup.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\03_Miso-Soup.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\04_Greek-Salad.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\05_Avocado-Tuna-Tapas.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\06_Caesar-Salad.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\07_Baked-Spaghetti.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\08_Beef-Bourguignon.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\09_Wild-Salmon.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\10_Chocolate-Fondue.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\11_Tapioca-Pudding.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\12_Fruit-Salad.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\13_Latte.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\14_Tea.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\15_Juice.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\16_Cola.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\17_Mineral-Water.jpg'));
INSERT INTO photo (content)
VALUES (FILE_READ('E:\Dima\Information\IT\MyProjects\toGitHub\E-Restaurant\files\images\18_Beer.jpg'));

INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Dmytro Burdyga', 'super@erestaurant.com', 'pass1', '1984-06-27', '2010-01-01', TRUE, 1, NULL);
INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Igor Himchenko', 'admin@erestaurant.com', 'pass2', '1987-07-02', '2011-06-10', TRUE, 2, NULL);
INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Elena Bakhmach', 'kitchen@erestaurant.com', 'pass3', '1991-04-05', '2014-02-03', TRUE, 3, NULL);
INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Alexander Volkov', 'delivery@erestaurant.com', 'pass4', '1993-03-03', '2014-02-28', TRUE, 4, NULL);
INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id, photo_id)
VALUES ('Bogdana Tkachuk', 'business@erestaurant.com', 'pass5', '1979-11-15', '2010-01-20', TRUE, 5, NULL);

INSERT INTO dish_category (name, description)
VALUES ('SOUP',
        'Soup is a primarily liquid food, served warm (some kinds are cool cool or cold), made by combining ingredients such as meat and vegetables, juice, water, or another liquid.');
INSERT INTO dish_category (name, description)
VALUES ('SALAD',
        'Salad is a cold dish of various mixtures of raw and cooked vegetables, seasoned with oil, vinegar, or other dressing and sometimes accompanied by meat, fish, and other ingredients.');
INSERT INTO dish_category (name, description)
VALUES ('MAIN_COURSE',
        'The main course is the featured or primary dish in a meal consisting of several courses, it is the most substantial course of a meal.');
INSERT INTO dish_category (name, description)
VALUES ('DESSERT',
        'Dessert is the sweet course eaten at the end of a meal, such as: cake, pie, fruit, pudding, ice cream, etc. It is the fourth "meal" of the day following breakfast, lunch, and dinner.');
INSERT INTO dish_category (name, description)
VALUES ('DRINK',
        'Here you can order different cold beverages like juices, lemonades, soda, mineral water, and also some hot drinks like tea, espresso, latte, mochaccino.');

-- soups --
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Tomato Soup',
        'Tomato soup is a soup made with tomatoes as the primary ingredient. It may be served hot or cold in a bowl.',
        4.20, TRUE, TRUE, 1, 1);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Cream of mushroom soup',
        'Cream of mushroom soup is a soup where a basic roux is thinned with milk and then mushrooms and mushroom broth are added.',
        5.40, TRUE, TRUE, 2, 1);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Miso soup',
        'Miso soup is a traditional Japanese soup consisting of a stock called "dashi" into which softened miso paste is mixed.',
        3.60, TRUE, TRUE, 3, 1);

-- salads --
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Greek Salad',
        'Greek salad is made with tomatoes, cucumbers, onion, feta cheese and olives, seasoned with salt and oregano and dressed with olive oil.',
        5.50, TRUE, TRUE, 4, 2);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Avocado and Tuna Tapas',
        'This is a light, healthy Spanish tapa that goes best with crisp white wines and crunchy bread.',
        7.30, TRUE, TRUE, 5, 2);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Caesar salad',
        'It is a salad of romaine lettuce and croutons dressed with parmesan cheese, lemon juice, olive oil, egg, garlic, and black pepper.',
        4.30, TRUE, TRUE, 6, 2);

-- main course --
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Baked Spaghetti',
        'Casserole of thin spaghetti pasta, sausage, mushroom, and tomato sauce, ricotta, Parmesan, and Mozzarella cheeses.',
        7.20, TRUE, TRUE, 7, 3);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Beef Bourguignon',
        'Luxurious beef bourguignon, or beef Burgundy, with beef chuck, carrots, pearl onions, mushrooms, and coated with a deeply flavored, silky sauce.',
        8.20, TRUE, TRUE, 8, 3);
INSERT INTO dish (NAME, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Wild Salmon',
        'Grilled whole wild salmon, stuffed with a relish made of preserved lemons, parsley, dill, and shallots.',
        8.90, TRUE, TRUE, 9, 3);

-- desserts --
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Chocolate Fondue',
        'Chocolate fondue is easy and fun to make, a party favorite.  Dip fresh fruit and other dippables into the hot, melted creamy chocolate mixture.',
        5.10, TRUE, TRUE, 10, 4);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Tapioca Pudding',
        'One of our favorite desserts growing up! This tapioca pudding recipe uses small pearl tapioca, milk, sugar, eggs, and vanilla.',
        4.20, TRUE, TRUE, 11, 4);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Fruit Salad',
        'Blueberry, peach, nectarine fruit salad seasoned with fresh thyme, ginger, and lemon juice.',
        4.40, TRUE, TRUE, 12, 4);

-- drinks --
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Latte',
        'A Latte is a type of coffee made with espresso and hot steamed milk, milkier than a cappuccino.',
        4.90, TRUE, TRUE, 13, 5);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Tea',
        'Aromatic beverage prepared by pouring hot water over cured leaves of tea leaves.',
        3.40, TRUE, TRUE, 14, 5);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Fresh Juice',
        'Natural and healthy juices from different fruits and vegetables. Can be mixed in different variations.',
        4.50, TRUE, TRUE, 15, 5);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Cola',
        'A typical brown carbonated drink that is flavoured with an extract of cola nuts, or with a similar flavouring.',
        2.90, FALSE, TRUE, 16, 5);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Mineral water',
        'Water that contains mineral salts and natural carbon dioxide.',
        4.50, FALSE, TRUE, 17, 5);
INSERT INTO dish (name, description, price, kitchenmade, available, photo_id, dish_category_id)
VALUES ('Beer',
        'An alcoholic drink made from yeast-fermented malt flavoured with hops.',
        5.50, FALSE, FALSE, 18, 5);


INSERT INTO customer (name, email, password, address, birthDate, photo_id)
VALUES ('Olga Romanova', 'olga.romanova@gmail.com', 'pass1', '27 Obolonsky Ave., App. 34, Kyiv', '1995-04-05', NULL);
INSERT INTO customer (name, email, password, address, birthDate, photo_id)
VALUES
  ('Igor Shevchenko', 'igor.shevchenko@yahoo.com', 'pass2', '4 Khreschatik Str., App. 12, Kyiv', '1990-01-25', NULL);
INSERT INTO customer (name, email, password, address, birthDate, photo_id)
VALUES ('Kate Belova', 'kate.belova@gmail.com', 'pass3', '3 Verbova Str., App. 7, Kyiv', '1987-01-17', NULL);
INSERT INTO customer (name, email, password, address, birthDate, photo_id)
VALUES
  ('Roman Karetskiy', 'roman.karetskiy@gmail.com', 'pass4', '36 Ivana Lepse Str., App. 14, Kyiv', '1989-03-07', NULL);
INSERT INTO customer (name, email, password, address, birthDate, photo_id)
VALUES
  ('Oksana Alekseeva', 'oksana.alekseeva@gmail.com', 'pass5', '29 Verkhniy Val Str., App. 4, Kyiv', '1982-02-18', NULL);

INSERT INTO orders_status (name)
VALUES ('KITCHEN_DONE');
INSERT INTO orders_status (name)
VALUES ('NON-KITCHEN_DONE');
INSERT INTO orders_status (name)
VALUES ('READY_FOR_SHIPMENT');
INSERT INTO orders_status (name)
VALUES ('DELIVERING');
INSERT INTO orders_status (name)
VALUES ('DELIVERED');

INSERT INTO orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
VALUES ('2015-12-01 15:21:10', '2015-10-17 16:21:10', 22.20, 5, 1);
INSERT INTO orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
VALUES ('2015-12-02 14:11:11', '2015-10-18 15:10:01', 8.00, 5, 2);
INSERT INTO orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
VALUES ('2015-12-03 15:59:50', '2015-10-19 16:25:04', 3.60, 5, 3);
INSERT INTO orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
VALUES ('2015-12-04 15:31:11', '2015-10-21 16:11:02', 51.90, 5, 4);
INSERT INTO orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
VALUES ('2015-12-05 12:11:19', '2015-10-16 13:34:11', 22.40, 5, 5);
INSERT INTO orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
VALUES ('2015-12-06 14:12:19', '2015-10-21 15:33:21', 16.90, 5, 4);
INSERT INTO orders (date_time_taken, date_time_delivered, total_price, orders_status_id, customer_id)
VALUES ('2015-12-07 01:11:09', '2015-10-21 02:03:02', 15.30, 5, 5);

INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (2, 8.40, TRUE, 1, 1);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 5.40, TRUE, 2, 1);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 5.40, TRUE, 2, 2);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 3.60, TRUE, 3, 3);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 5.50, TRUE, 4, 4);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (2, 14.60, TRUE, 8, 4);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (2, 8.60, TRUE, 6, 4);
--
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 4.20, TRUE, 1, 5);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 5.50, TRUE, 4, 5);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 8.20, TRUE, 8, 5);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 4.50, TRUE, 15, 5);
--
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 2.90, TRUE, 16, 6);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 4.30, TRUE, 9, 6);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 4.20, TRUE, 1, 6);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 5.50, TRUE, 4, 6);
--
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 5.50, TRUE, 1, 7);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 5.50, TRUE, 4, 7);
INSERT INTO order_dishes (quantity, price, readiness, dish_id, orders_id)
VALUES (1, 4.30, TRUE, 6, 7);