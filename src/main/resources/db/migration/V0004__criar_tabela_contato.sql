CREATE TABLE contato
(
    id bigint NOT NULL,
    nome CHARACTER VARYING(100) NOT NULL,
    email CHARACTER VARYING(200) NOT NULL,
    telefone CHARACTER VARYING(11) NOT NULL,
    pessoa_id bigint NOT NULL,
    CONSTRAINT contato_pkey PRIMARY KEY (id),
    CONSTRAINT uk_id UNIQUE (id),
    CONSTRAINT fk_pessoa_id FOREIGN KEY (pessoa_id)
        REFERENCES public.pessoa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)