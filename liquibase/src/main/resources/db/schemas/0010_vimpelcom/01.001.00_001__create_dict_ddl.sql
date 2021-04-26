--liquibase formatted sql
--changeset DTen:PRESALE-19


CREATE SEQUENCE IF NOT EXISTS vimpelcom.table_a_seq
    INCREMENT 1
    START 98
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS vimpelcom.table_b_seq
    INCREMENT 1
    START 98
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE IF NOT EXISTS vimpelcom.table_c_seq
    INCREMENT 1
    START 98
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;




CREATE TABLE vimpelcom.table_a (
table_a_id int4 NOT null DEFAULT nextval('vimpelcom.table_a_seq'::regclass),
name_a varchar(40) NULL,
cdat_a timestamp NOT NULL,
CONSTRAINT table_a_pk PRIMARY KEY (table_a_id)
);


CREATE TABLE vimpelcom.table_b (
table_b_id int8 NOT null DEFAULT nextval('vimpelcom.table_b_seq'::regclass),
name_b varchar(40) NULL,
cdat_b timestamp NOT NULL,
CONSTRAINT table_b_pk PRIMARY KEY (table_b_id)
);


CREATE TABLE vimpelcom.table_c (
table_c_id int8 NOT null DEFAULT nextval('vimpelcom.table_c_seq'::regclass),
name_c varchar(40) NULL,
cdat_c timestamp NOT NULL,
CONSTRAINT table_c_pk PRIMARY KEY (table_c_id)
);


