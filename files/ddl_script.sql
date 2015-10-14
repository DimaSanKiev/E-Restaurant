--- CREATING TABLES ---

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
)
