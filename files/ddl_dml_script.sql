--- CREATE TABLES ---

--DROP TABLE dish_category;
--DROP TABLE dish;
--DROP TABLE customer;
--DROP TABLE orders;
--DROP TABLE order_dishes;
--DROP TABLE role;
--DROP TABLE employee;

CREATE TABLE role (
  id   INT          NOT NULL GENERATED ALWAYS AS IDENTITY,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE employee (
  id        INT          NOT NULL GENERATED ALWAYS AS IDENTITY,
  name      VARCHAR(100) NOT NULL,
  email     VARCHAR(100) NOT NULL,
  password  VARCHAR(100) NOT NULL,
  birthDate DATE,
  hireDate  DATE         NOT NULL,
  ready     CHAR(1)      NOT NULL,
  PRIMARY KEY (id),
  role_id   INT CONSTRAINT role_fk REFERENCES role
);

CREATE TABLE dish_category (
  id          INT          NOT NULL GENERATED ALWAYS AS IDENTITY,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE dish (
  id               INT            NOT NULL GENERATED ALWAYS AS IDENTITY,
  name             VARCHAR(100)   NOT NULL,
  description      VARCHAR(500)   NOT NULL,
  price            DECIMAL(15, 2) NOT NULL,
  kitchenmade      CHAR(1)        NOT NULL,
  photo            BLOB(1M),
  PRIMARY KEY (id),
  dish_category_id INT CONSTRAINT dish_category_fk REFERENCES dish_category
);

CREATE TABLE customer (
  id        INT          NOT NULL GENERATED ALWAYS AS IDENTITY,
  name      VARCHAR(100) NOT NULL,
  email     VARCHAR(100) NOT NULL,
  password  VARCHAR(100) NOT NULL,
  address   VARCHAR(500) NOT NULL,
  birthDate DATE,
  PRIMARY KEY (id)
);

CREATE TABLE orders (
  id                  INT            NOT NULL GENERATED ALWAYS AS IDENTITY,
  date_time_taken     TIMESTAMP      NOT NULL,
  date_time_delivered TIMESTAMP      NOT NULL,
  total_price         DECIMAL(15, 2) NOT NULL,
  delivery_status     CHAR(1)        NOT NULL,
  PRIMARY KEY (id),
  customer_id         INT CONSTRAINT customer_fk REFERENCES customer
);

CREATE TABLE order_dishes (
  id       INT            NOT NULL GENERATED ALWAYS AS IDENTITY,
  price    DECIMAL(15, 2) NOT NULL,
  PRIMARY KEY (id),
  dish_id  INT CONSTRAINT dish_fk REFERENCES dish,
  order_id INT CONSTRAINT order_fk REFERENCES orders
);


--- FILL TABLES ---

INSERT INTO role (name) VALUES ('SUPER_USER');
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('KITCHEN_STAFF');
INSERT INTO role (name) VALUES ('DELIVERY_STAFF');
INSERT INTO role (name) VALUES ('BUSINESS_ANALYST');

INSERT INTO dish_category (NAME, DESCRIPTION)
  VALUES ('SOUP', 'Soup is a primarily liquid food, served warm (some kinds are cool cool or cold), that is made by combining ingredients such as meat and vegetables with stock, juice, water, or another liquid.');
INSERT INTO dish_category (NAME, DESCRIPTION)
  VALUES ('SALAD', 'Salad is a cold dish of various mixtures of raw and cooked vegetables, seasoned with oil, vinegar, or other dressing and sometimes accompanied by meat, fish, and other ingredients.');
INSERT INTO dish_category (NAME, DESCRIPTION)
  VALUES ('MAIN_COURSE', 'The main course is primary dish, it is the most substantial course of a meal.');
INSERT INTO dish_category (NAME, DESCRIPTION)
  VALUES ('DESSERT', 'Dessert id the sweet course eaten at the end of a meal. It is the fourth "meal" of the day following breakfast, lunch, and dinner.');
INSERT INTO dish_category (NAME, DESCRIPTION)
  VALUES ('DRINK', 'Different cold beverages such as: juices, lemonades, soda, and also some hot drinks like tea, espresso, latte, mochaccino.');

INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id)
  VALUES ('Dan Nelis', 'dan.nelis@erestaurant.com', 'password1', '1987-07-02', '2012-06-01', '1', 3);
INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id)
  VALUES ('Mark Wolf', 'mark.wolf@erestaurant.com', 'password2', '1979-11-15', '2010-01-20', '1', 5);
INSERT INTO employee (name, email, password, birthdate, hiredate, ready, role_id)
  VALUES ('Stein Brown', 'stein.brown@erestaurant.com', 'password3', '1993-03-03', '2014-02-28', '1', 4);