--liquibase formatted sql
--changeset DTen:PRESALE-19

insert into vimpelcom.table_a (name_a, cdat_a)
select name_a, cdat_a from vimpelcom.table_a;

insert into vimpelcom.table_a (name_a, cdat_a)
select name_a, cdat_a from vimpelcom.table_a;

insert into vimpelcom.table_a (name_a, cdat_a)
select name_a, cdat_a from vimpelcom.table_a;



-------------------------------------------

insert into vimpelcom.table_b (name_b, cdat_b)
select name_b, cdat_b from vimpelcom.table_b;

insert into vimpelcom.table_b (name_b, cdat_b)
select name_b, cdat_b from vimpelcom.table_b;

insert into vimpelcom.table_b (name_b, cdat_b)
select name_b, cdat_b from vimpelcom.table_b;




insert into vimpelcom.table_c (name_c, cdat_c)
select name_c, cdat_c from vimpelcom.table_c;

insert into vimpelcom.table_c (name_c, cdat_c)
select name_c, cdat_c from vimpelcom.table_c;

insert into vimpelcom.table_c (name_c, cdat_c)
select name_c, cdat_c from vimpelcom.table_c;
