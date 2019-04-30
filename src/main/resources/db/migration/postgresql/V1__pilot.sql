--
-- Name: item; Type: TABLE;
--

CREATE TABLE item (
    item_id  BIGSERIAL NOT NULL,
    code CHARACTER VARYING(255) NOT NULL,
    unit_price NUMERIC NOT NULL,
    quantity INTEGER NOT NULL,
    name CHARACTER VARYING(255) NOT NULL
    );

ALTER TABLE ONLY item ADD CONSTRAINT item_id_pkey PRIMARY KEY (item_id);

--
-- Name: cart; Type: TABLE;
--

CREATE TABLE cart (
    cart_id  BIGSERIAL NOT NULL,
    customer_id character varying(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    tenant CHARACTER VARYING(255) NOT NULL,
    total_price NUMERIC
);

ALTER TABLE ONLY cart ADD CONSTRAINT cart_id_pkey PRIMARY KEY (cart_id);