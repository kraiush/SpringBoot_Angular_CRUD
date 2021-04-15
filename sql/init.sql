DROP TABLE IF EXISTS public.product;

CREATE TABLE public.product (
                       id BIGINT PRIMARY KEY,
                       code VARCHAR(32) NOT NULL,
                       name VARCHAR(254) NOT NULL,
                       price NUMERIC(11,2) NOT NULL,
                       article VARCHAR(254) NOT NULL,
                       productiondate TIMESTAMP DEFAULT NULL,
                       quantity INTEGER DEFAULT NULL);

insert into public.product(id, code, name, price, article, productiondate, quantity)
values
       (1, 'Abra34&Cada*@1Bra', 'product 1', 797577.54, 'article 1', '2017-09-07', 7),
       (2, 'olivia^I#F2ew', 'product 2', 33.78, 'article 2', '2005-11-01', 7073467),
       (3, 'zevs!^5r4@ygWEf', 'product 3', 1775353.01, 'article 3', '2020-01-01', 1);