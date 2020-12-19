INSERT INTO pessoa(id, nome, cpf, data_nascimento) VALUES (1, 'Teste', '29557684488', '1990-04-02');
INSERT INTO pessoa(id, nome, cpf, data_nascimento) VALUES (2, 'César', '71770129316', '1989-01-21');
INSERT INTO pessoa(id, nome, cpf, data_nascimento) VALUES (3, 'Cezar', '65175520490', '1994-12-18');
INSERT INTO pessoa(id, nome, cpf, data_nascimento) VALUES (4, 'CESAR', '10646019872', '1967-10-12');
INSERT INTO pessoa(id, nome, cpf, data_nascimento) VALUES (5, 'cesar', '42425270981', '1998-08-03');

SELECT SETVAL('sequence', MAX(id)) FROM pessoa;