--- CREATE TABLES ---

DROP TABLE order_dishes;
DROP TABLE dish;
DROP TABLE dish_category;
DROP TABLE orders;
DROP TABLE customer;
DROP TABLE employee;
DROP TABLE role;

CREATE TABLE role (
  id   INT          NOT NULL GENERATED BY DEFAULT AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE employee (
  id        INT          NOT NULL GENERATED BY DEFAULT AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  name      VARCHAR(100) NOT NULL,
  -- todo unique email
  email     VARCHAR(100) NOT NULL UNIQUE,
  password  VARCHAR(100) NOT NULL,
  birthDate DATE,
  hireDate  DATE         NOT NULL,
  ready     CHAR(1)      NOT NULL,
  PRIMARY KEY (id),
  role_id   INT CONSTRAINT role_fk REFERENCES role
);

CREATE TABLE dish_category (
  id          INT          NOT NULL GENERATED BY DEFAULT AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE dish (
  id               INT            NOT NULL GENERATED BY DEFAULT AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  name             VARCHAR(100)   NOT NULL,
  description      VARCHAR(500)   NOT NULL,
  price            DECIMAL(15, 2) NOT NULL,
  kitchenmade      CHAR(1)        NOT NULL,
  --   photo            BLOB(1M),
  photo            VARCHAR(500),
  PRIMARY KEY (id),
  dish_category_id INT CONSTRAINT dish_category_fk REFERENCES dish_category
);

CREATE TABLE customer (
  id        INT          NOT NULL GENERATED BY DEFAULT AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  name      VARCHAR(100) NOT NULL,
  email     VARCHAR(100) NOT NULL UNIQUE,
  password  VARCHAR(100) NOT NULL,
  address   VARCHAR(500) NOT NULL,
  birthDate DATE,
  PRIMARY KEY (id)
);

CREATE TABLE orders (
  id                  INT            NOT NULL GENERATED BY DEFAULT AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  date_time_taken     TIMESTAMP      NOT NULL,
  date_time_delivered TIMESTAMP      NOT NULL,
  total_price         DECIMAL(15, 2) NOT NULL,
  order_status    CHAR(1)        NOT NULL,
  PRIMARY KEY (id),
  customer_id         INT CONSTRAINT customer_fk REFERENCES customer
);

CREATE TABLE order_dishes (
  id       INT            NOT NULL GENERATED BY DEFAULT AS IDENTITY ( START WITH 1, INCREMENT BY 1),
  price    DECIMAL(15, 2) NOT NULL,
  PRIMARY KEY (id),
  dish_id  INT CONSTRAINT dish_fk REFERENCES dish,
  order_id INT CONSTRAINT order_fk REFERENCES orders
);

-- todo unique - value in table or with indexes?
--- UNIQUE INDEXES ---
-- CREATE UNIQUE INDEX email ON customer (email);
-- CREATE UNIQUE INDEX email ON employee (email);


--- FILL TABLES ---

INSERT INTO role (name) VALUES ('SUPER_USER');
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('KITCHEN_STAFF');
INSERT INTO role (name) VALUES ('DELIVERY_STAFF');
INSERT INTO role (name) VALUES ('BUSINESS_ANALYST');

INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id)
VALUES ('Dmytro Arkheev', 'superuser@gmail.com', 'pass1', '1984-06-27', '2013-10-01', '1', 1);
INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id)
VALUES ('Igor Himchenko', 'igor.himchenko@yahoo.com', 'pass2', '1987-07-02', '2011-06-10', '1', 2);
INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id)
VALUES ('Elena Bakhmach', 'elena.bakhmach@gmail.com', 'pass3', '1991-04-05', '2014-02-03', '1', 3);
INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id)
VALUES ('Alexander Volkov', 'alexander.volkov@gmail.com', 'pass4', '1993-03-03', '2014-02-28', '1', 4);
INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id)
VALUES ('Khristina Tkachuk', 'khristina.tkachuk@gmail.com', 'pass5', '1979-11-15', '2010-01-20', '1', 5);

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

-- todo store BLOBs
-- soups --
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Tomato Soup',
        'Tomato soup is a soup made with tomatoes as the primary ingredient. It may be served hot or cold in a bowl.',
        4.20, '1', 'photo', 1);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Cream of mushroom soup',
        'Cream of mushroom soup is a soup where a basic roux is thinned with milk and then mushrooms and mushroom broth are added',
        5.40, '1', 'photo', 1);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Miso soup',
        'Miso soup is a traditional Japanese soup consisting of a stock called "dashi" into which softened miso paste is mixed.',
        3.60, '1', 'photo', 1);

-- salads --
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Greek Salad',
        'Greek salad is made with tomatoes, cucumbers, onion, feta cheese and olives, seasoned with salt and oregano and dressed with olive oil.',
        5.50, '1', 'photo', 2);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Avocado and Tuna Tapas',
        'This is a light, healthy Spanish tapa that goes best with crisp white wines and crunchy bread.',
        7.30, '1', 'photo', 2);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Caesar salad',
        'It is a salad of romaine lettuce and croutons dressed with parmesan cheese, lemon juice, olive oil, egg, garlic, and black pepper.',
        4.30, '1', 'photo', 2);

-- main course --
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Baked Spaghetti',
        'Casserole of thin spaghetti pasta, sausage, mushroom, and tomato sauce, ricotta, Parmesan, and Mozzarella cheeses.',
        7.20, '1', 'photo', 3);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Beef Bourguignon',
        'Luxurious beef bourguignon, or beef Burgundy, with beef chuck, carrots, pearl onions, mushrooms, and coated with a deeply flavored, silky sauce.',
        8.20, '1', 'photo', 3);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Wild Salmon',
        'Grilled whole wild salmon, stuffed with a relish made of preserved lemons, parsley, dill, and shallots.',
        8.90, '1', 'photo', 3);

-- desserts --
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Chocolate Fondue',
        'Chocolate fondue is easy and fun to make, a party favorite.  Dip fresh fruit and other dippables into the hot, melted creamy chocolate mixture.',
        5.10, '1', 'photo', 4);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Tapioca Pudding',
        'One of our favorite desserts growing up! This tapioca pudding recipe uses small pearl tapioca, milk, sugar, eggs, and vanilla.',
        4.20, '1', 'photo', 4);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Fruit Salad',
        'Blueberry, peach, nectarine fruit salad seasoned with fresh thyme, ginger, and lemon juice.',
        4.40, '1', 'photo', 4);

-- drinks --
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Latte',
        'A Latte is a type of coffee made with espresso and hot steamed milk, milkier than a cappuccino.',
        4.90, '1', 'photo', 5);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Tea',
        'Aromatic beverage prepared by pouring hot water over cured leaves of tea leaves.',
        3.40, '1', 'photo', 5);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Juice',
        'Natural and healthy juices from different fruits and vegetables. Can be mixed in different variations.',
        4.50, '1', 'photo', 5);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Cola',
        'A typical brown carbonated drink that is flavoured with an extract of cola nuts, or with a similar flavouring.',
        2.90, '0', 'photo', 5);
INSERT INTO dish (name, description, price, kitchenmade, photo, dish_category_id)
VALUES ('Mineral water',
        'Water that contains mineral salts and natural carbon dioxide).',
        4.50, '0', 'photo', 5);


INSERT INTO customer (name, email, password, address, birthDate)
VALUES ('Olga Romanova', 'olga.romanova@gmail.com', 'pass1', '27 Obolonsky Ave., App. 34, Kyiv', '1995-04-05');
INSERT INTO customer (name, email, password, address, birthDate)
VALUES ('Igor Shevchenko', 'igor.shevchenko@yahoo.com', 'pass2', '4 Khreschatik Str., App. 12, Kyiv', '1990-01-25');
INSERT INTO customer (name, email, password, address, birthDate)
VALUES ('Kate Belova', 'kate.belova@gmail.com', 'pass3', '3 Verbova Str., App. 7, Kyiv', '1987-01-17');
INSERT INTO customer (name, email, password, address, birthDate)
VALUES ('Roman Karetskiy', 'roman.karetskiy@gmail.com', 'pass4', '36 Ivana Lepse Str., App. 14, Kyiv', '1989-03-07');
INSERT INTO customer (name, email, password, address, birthDate)
VALUES ('Oksana Alekseeva', 'oksana.alekseeva@gmail.com', 'pass5', '29 Verkhniy Val Str., App. 4, Kyiv', '1982-02-18');

INSERT INTO orders (date_time_taken, date_time_delivered, total_price, order_status, customer_id)
VALUES ('2015-10-17 15:21:10', '2015-10-17 16:21:10', 5.50, '1', 1);
INSERT INTO orders (date_time_taken, date_time_delivered, total_price, order_status, customer_id)
VALUES ('2015-10-17 14:11:11', '2015-10-17 15:10:01', 6.05, '1', 2);
INSERT INTO orders (date_time_taken, date_time_delivered, total_price, order_status, customer_id)
VALUES ('2015-10-17 15:59:50', '2015-10-17 16:25:04', 6.40, '1', 3);
INSERT INTO orders (date_time_taken, date_time_delivered, total_price, order_status, customer_id)
VALUES ('2015-10-17 15:31:11', '2015-10-17 16:11:02', 7.50, '1', 4);
INSERT INTO orders (date_time_taken, date_time_delivered, total_price, order_status, customer_id)
VALUES ('2015-10-17 12:11:19', '2015-10-17 13:34:11', 7.15, '1', 5);

INSERT INTO order_dishes (price, dish_id, order_id)
VALUES (2.20, 1, 1);
INSERT INTO order_dishes (price, dish_id, order_id)
VALUES (3.30, 2, 2);
INSERT INTO order_dishes (price, dish_id, order_id)
VALUES (4.20, 3, 3);
INSERT INTO order_dishes (price, dish_id, order_id)
VALUES (3.10, 4, 5);
INSERT INTO order_dishes (price, dish_id, order_id)
VALUES (2.95, 5, 4);