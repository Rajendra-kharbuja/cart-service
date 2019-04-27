--
-- Name: item; Type: TABLE;
--

CREATE TABLE item (
    item_id  BIGSERIAL NOT NULL,
    cart_id BIGSERIAL NOT NULL,
    code CHARACTER VARYING(255) NOT NULL,
    unit_price NUMERIC NOT NULL,
    name CHARACTER VARYING(255) NOT NULL,
    description CHARACTER VARYING(255)
);

ALTER TABLE ONLY item ADD CONSTRAINT item_id_pkey PRIMARY KEY (item_id);
ALTER TABLE ONLY item ADD CONSTRAINT cartId_code_unique UNIQUE (cart_id, code);

--
-- Name: cart; Type: TABLE;
--

CREATE TABLE cart (
    cart_id  BIGSERIAL NOT NULL,
    tenant_id character varying(255) NOT NULL,
    customer_id character varying(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    total_price NUMERIC
);

ALTER TABLE ONLY cart ADD CONSTRAINT cart_id_pkey PRIMARY KEY (cart_id);