CREATE TABLE company
(
    id   SERIAL,
    name VARCHAR UNIQUE NOT NULL,
    CONSTRAINT company_pk PRIMARY KEY (id)
);

CREATE TABLE product
(
    id         SERIAL,
    name       VARCHAR NOT NULL,
    company_id INT     NOT NULL REFERENCES company (id) ON UPDATE CASCADE ON DELETE CASCADE,
    count      INT     NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);