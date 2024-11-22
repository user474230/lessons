CREATE TABLE book_store.book (
	id bigserial NOT NULL,
	title text NULL,
	price int NULL,
	genre text NULL,
	CONSTRAINT book_pk PRIMARY KEY (id)
);
INSERT INTO book_store.book (title,price,genre) VALUES
	 ('Book 1',100,'ACTION'),
	 ('Book 2',200,'ACTION'),
	 ('Book 3',300,'ACTION'),
	 ('Book 4',400,'FANTACY'),
	 ('Book 5',500,'FANTACY'),
	 ('Book 6',600,'FANTACY'),
	 ('Book 7',700,'HISTORY'),
	 ('Book 8',800,'HISTORY'),
	 ('Book 9',900,'HISTORY'),
	 ('Book 10',1000,'HISTORY');

INSERT INTO book_store.book (title,price,genre) VALUES
	 ('Book 11',1100,'TALE'),
	 ('Book 12',1200,'TALE'),
	 ('Book 13',1300,'TALE'),
	 ('Book 14',1400,'TALE'),
	 ('Book 15',1500,'TALE');
