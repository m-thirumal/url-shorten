-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 1.0.0
-- PostgreSQL version: 15.0
-- Project Site: pgmodeler.io
-- Model Author: Thirumal

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: shorten_url | type: DATABASE --
-- DROP DATABASE IF EXISTS shorten_url;
CREATE DATABASE shorten_url;
-- ddl-end --
COMMENT ON DATABASE shorten_url IS E'Database to store shorten url';
-- ddl-end --


-- object: public.shorten_url_shorten_url_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.shorten_url_shorten_url_id_seq CASCADE;
CREATE SEQUENCE public.shorten_url_shorten_url_id_seq
	INCREMENT BY 1
	MINVALUE -9223372036854775808
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- ddl-end --

-- object: public.shorten_url | type: TABLE --
-- DROP TABLE IF EXISTS public.shorten_url CASCADE;
CREATE TABLE public.shorten_url (
	shorten_url_id bigint NOT NULL DEFAULT nextval('public.shorten_url_shorten_url_id_seq'::regclass),
	original_url text NOT NULL,
	short_url varchar(30) NOT NULL,
	row_created_on timestamp NOT NULL DEFAULT current_timestamp,
	expire_on timestamp NOT NULL DEFAULT 'infinity'::timestamp,
	CONSTRAINT shorten_url_pk PRIMARY KEY (shorten_url_id)
);
-- ddl-end --
ALTER TABLE public.shorten_url OWNER TO postgres;
-- ddl-end --

-- object: ix_shorten_url_expire_on | type: INDEX --
-- DROP INDEX IF EXISTS public.ix_shorten_url_expire_on CASCADE;
CREATE INDEX ix_shorten_url_expire_on ON public.shorten_url
USING btree
(
	expire_on
);
-- ddl-end --

-- object: ix_shorten_url_short_url | type: INDEX --
-- DROP INDEX IF EXISTS public.ix_shorten_url_short_url CASCADE;
CREATE INDEX ix_shorten_url_short_url ON public.shorten_url
USING btree
(
	short_url
);
-- ddl-end --


