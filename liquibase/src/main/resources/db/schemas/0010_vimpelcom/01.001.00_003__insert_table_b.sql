--liquibase formatted sql
--changeset DTen:PRESALE-19

insert into vimpelcom.table_b (table_b_id, "name", create_date)
values ((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc1', '2000-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc2', '2001-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc3', '2002-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc4', '2003-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc5', '2004-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc6', '2005-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc7', '2006-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc8', '2007-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc9', '2008-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc10', '2009-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc11', '2010-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc12', '2011-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc13', '2012-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc14', '2013-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc15', '2014-12-31'::timestamp),
((SELECT md5(random()::text || clock_timestamp()::text)::uuid), 'abc16', '2015-12-31'::timestamp);
