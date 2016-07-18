SEt SCHEMA ;
DROP TABLE IF EXISTS order_dishes CASCADE;
DROP TABLE IF EXISTS dish CASCADE;
DROP TABLE IF EXISTS dish_category CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS role CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS orders_status CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS photo CASCADE;

CREATE TABLE role (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE photo (
  id      SERIAL PRIMARY KEY,
  content BYTEA
);

CREATE TABLE employee (
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(100) NOT NULL,
  email     VARCHAR(100) NOT NULL UNIQUE,
  password  VARCHAR(100) NOT NULL,
  birthDate TIMESTAMP,
  hireDate  TIMESTAMP    NOT NULL,
  ready     BOOLEAN      NOT NULL,
  role_id   INT REFERENCES role (id) ON UPDATE CASCADE,
  photo_id  INT REFERENCES photo (id) ON UPDATE CASCADE
);

CREATE TABLE dish_category (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL
);

CREATE TABLE dish (
  id               SERIAL PRIMARY KEY,
  name             VARCHAR(100)   NOT NULL,
  description      VARCHAR(500)   NOT NULL,
  price            DECIMAL(15, 2) NOT NULL,
  kitchenmade      BOOLEAN        NOT NULL,
  available        BOOLEAN        NOT NULL,
  photo_id         INT REFERENCES dish_category (id) ON DELETE CASCADE,
  dish_category_id INT REFERENCES photo (id) ON DELETE CASCADE
);

CREATE TABLE customer (
  id        SERIAL PRIMARY KEY,
  name      VARCHAR(100) NOT NULL,
  email     VARCHAR(100) NOT NULL UNIQUE,
  password  VARCHAR(100) NOT NULL,
  address   VARCHAR(500) NOT NULL,
  birthDate TIMESTAMP,
  blocked   BOOLEAN      NOT NULL DEFAULT FALSE,
  photo_id  INT REFERENCES photo (id) ON DELETE CASCADE
);

CREATE TABLE orders_status (
  id   SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE orders (
  id                  SERIAL PRIMARY KEY,
  date_time_taken     TIMESTAMP      NOT NULL,
  date_time_delivered TIMESTAMP,
  total_price         DECIMAL(15, 2) NOT NULL,
  orders_status_id    INT REFERENCES orders_status (id) ON DELETE CASCADE DEFAULT 1,
  customer_id         INT REFERENCES customer (id) ON DELETE CASCADE
);

CREATE TABLE order_dishes (
  id        SERIAL PRIMARY KEY,
  quantity  INT            NOT NULL DEFAULT 2,
  price     DECIMAL(15, 2) NOT NULL,
  readiness BOOLEAN        NOT NULL DEFAULT FALSE,
  dish_id   INT REFERENCES dish (id) ON DELETE CASCADE,
  orders_id INT REFERENCES orders (id) ON DELETE CASCADE
);