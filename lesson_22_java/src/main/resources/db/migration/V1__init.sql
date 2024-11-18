CREATE TABLE customer (
	id bigserial NOT NULL,
	fio text NULL,
	age int4 NULL,
	CONSTRAINT customer_pk PRIMARY KEY (id)
);

CREATE TABLE product (
	id bigserial NOT NULL,
	title text NULL,
	price int4 NULL,
	CONSTRAINT product_pk PRIMARY KEY (id)
);

INSERT INTO customer (fio, age) VALUES ('Андрей', 22), ('Владимир', 32), ('Иван', 42);
INSERT INTO product (title, price) VALUES ('Ручка', 202), ('Карандаш', 320), ('Папка', 425);