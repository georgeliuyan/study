--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE permission (
    id character varying(255) NOT NULL,
    name character varying(100) DEFAULT NULL::character varying,
    descr character varying(100) DEFAULT NULL::character varying
);


ALTER TABLE permission OWNER TO postgres;

--
-- Name: resource; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE resource (
    id character varying(50) NOT NULL,
    url_name character varying(100),
    url_ident character varying(50)
);


ALTER TABLE resource OWNER TO postgres;

--
-- Name: resource_permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE resource_permission (
    id character varying(50) NOT NULL,
    resource_id character varying(50),
    resource_ident character varying(100),
    resource_url character varying(100),
    permission_id character varying(50),
    permission_ident character varying(100)
);


ALTER TABLE resource_permission OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE role (
    id character varying(50) NOT NULL,
    name character varying(100) DEFAULT NULL::character varying
);


ALTER TABLE role OWNER TO postgres;

--
-- Name: role_resource_permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE role_resource_permission (
    id character varying(50) NOT NULL,
    role_id character varying(50),
    role_name character varying(100),
    resource_id character varying(50),
    permission_id character varying(50),
    resource_ident character varying(100),
    permission_ident character varying(100)
);


ALTER TABLE role_resource_permission OWNER TO postgres;

--
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_role (
    id character varying(255) NOT NULL,
    user_id character varying(255) NOT NULL,
    role_id character varying(255) NOT NULL
);


ALTER TABLE user_role OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id character varying(255) NOT NULL,
    name character varying(255) DEFAULT NULL::character varying,
    password character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE users OWNER TO postgres;

--
-- Data for Name: permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY permission (id, name, descr) FROM stdin;
1	用户中心	user:add
2	会员中心	user:vip
\.


--
-- Data for Name: resource; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY resource (id, url_name, url_ident) FROM stdin;
40289fa36c8f88c6016c8f8a63150000	/main	192.168.31.35:/main
40289fa36c8f88c6016c8f8a632e0002	/error/unAuth	192.168.31.35:/error/unAuth
40289fa36c8f88c6016c8f8a63310004	/logout	192.168.31.35:/logout
40289fa36c8f88c6016c8f8a63320006	/login	192.168.31.35:/login
40289fa36c8f88c6016c8f8a63360008	/toLogin	192.168.31.35:/toLogin
40289fa36c8f88c6016c8f8a6338000a	/user/index	192.168.31.35:/user/index
40289fa36c8f88c6016c8f8a633a000c	/vip/index	192.168.31.35:/vip/index
\.


--
-- Data for Name: resource_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY resource_permission (id, resource_id, resource_ident, resource_url, permission_id, permission_ident) FROM stdin;
40289fa36c8f88c6016c8f8a63210001	40289fa36c8f88c6016c8f8a63150000	\N	/main		authc
40289fa36c8f88c6016c8f8a632e0003	40289fa36c8f88c6016c8f8a632e0002	\N	/error/unAuth		authc
40289fa36c8f88c6016c8f8a63310005	40289fa36c8f88c6016c8f8a63310004	\N	/logout		authc
40289fa36c8f88c6016c8f8a63360009	40289fa36c8f88c6016c8f8a63360008	\N	/toLogin		authc
40289fa36c8f88c6016c8f8a6338000b	40289fa36c8f88c6016c8f8a6338000a	\N	/user/index		authc
40289fa36c8f88c6016c8f8a633b000d	40289fa36c8f88c6016c8f8a633a000c	\N	/vip/index		authc
40289fa36c8f88c6016c8f8a63330007	40289fa36c8f88c6016c8f8a63320006	\N	/login		anon
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY role (id, name) FROM stdin;
1	user
2	vip
\.


--
-- Data for Name: role_resource_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY role_resource_permission (id, role_id, role_name, resource_id, permission_id, resource_ident, permission_ident) FROM stdin;
\.


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_role (id, user_id, role_id) FROM stdin;
1	1	1
2	2	1
3	2	2
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, name, password) FROM stdin;
1	rhine	28e5ea71eb6600afb02132dcf27b8e75
2	vip	01ffb6fc48048d105ba5061f8df5a35e
\.


--
-- Name: permission permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);


--
-- Name: resource_permission resource_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resource_permission
    ADD CONSTRAINT resource_permission_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: role_resource_permission system_url_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY role_resource_permission
    ADD CONSTRAINT system_url_permission_pkey PRIMARY KEY (id);


--
-- Name: resource system_url_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resource
    ADD CONSTRAINT system_url_pkey PRIMARY KEY (id);


--
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

