CREATE TABLE pessoa
(
    id bigint NOT NULL,
    nome CHARACTER VARYING(100) NOT NULL,
    cpf CHARACTER VARYING(11) NOT NULL,
    data_nascimento DATE NOT NULL,
    CONSTRAINT pessoa_pkey PRIMARY KEY (id),
    CONSTRAINT uk_id UNIQUE (id),
    CONSTRAINT uk_cpf UNIQUE (cpf)
)