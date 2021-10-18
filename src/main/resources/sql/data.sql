INSERT INTO user (user_id, email , password, name, state) VALUES (1, 'admin@test.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'ACTIVATED');
INSERT INTO user (user_id, email , password, name, state) VALUES (2, 'user@test.com', '$2a$12$ddc3Yh1PGW8tR1ImLisJju1l67a8Aa8hEZl72ku8WCiVanglZ6lBe', 'user', 'ACTIVATED');

INSERT INTO authority (authority_id, authority_name) values (1, 'ROLE_USER');
INSERT INTO authority (authority_id, authority_name) values (2, 'ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_id) values (1, 1);
INSERT INTO user_authority (user_id, authority_id) values (1, 2);
INSERT INTO user_authority (user_id, authority_id) values (2, 1);