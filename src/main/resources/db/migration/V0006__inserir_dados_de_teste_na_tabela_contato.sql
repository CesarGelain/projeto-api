INSERT INTO contato(id, nome, email, telefone, pessoa_id) VALUES (6, 'Teste', 'teste@email.com', '44991234321', 1);
INSERT INTO contato(id, nome, email, telefone, pessoa_id) VALUES (7, 'César', 'cesar@email.com', '44991234321', 2);
INSERT INTO contato(id, nome, email, telefone, pessoa_id) VALUES (8, 'Arthur - Filho', 'arthur@email.com', '44994315487', 2);
INSERT INTO contato(id, nome, email, telefone, pessoa_id) VALUES (9, 'Cezar', 'cesar@email.com', '44991234321', 3);
INSERT INTO contato(id, nome, email, telefone, pessoa_id) VALUES (10, 'CESAR', 'cesar@email.com', '44991234321', 4);
INSERT INTO contato(id, nome, email, telefone, pessoa_id) VALUES (11, 'cesar', 'cesar@email.com', '44991234321', 5);

SELECT SETVAL('sequence', MAX(id)) FROM contato;