DROP TABLE IF EXISTS public.product;

CREATE TABLE public.product (
                       id BIGINT PRIMARY KEY,
                       code VARCHAR(32) NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       price NUMERIC(11,2) NOT NULL,
                       article VARCHAR(100) NOT NULL,
                       productiondate TIMESTAMP DEFAULT NULL,
                       quantity INTEGER DEFAULT NULL);

insert into public.product(id, code, name, price, article, productiondate, quantity)
values
       (1, 'abra#74bra@1', 'product 1', 797.54, 'article 1', '2017-09-07', 7),
       (2, 'cada^I#F2', 'product 2', 33.78, 'article 2', '2005-11-01', 707),
       (3, 'bra!^5r4@ygf', 'product 3', 1.01, 'article 3', '2020-01-01', 1);