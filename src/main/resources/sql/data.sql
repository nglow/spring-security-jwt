INSERT INTO user (user_id, username , password, nickname, state) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'ACTIVATED');
INSERT INTO user (user_id, username , password, nickname, state) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'ACTIVATED');

INSERT INTO authority (authority_id, authority_name) values (1, 'ROLE_USER');
INSERT INTO authority (authority_id, authority_name) values (2, 'ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_id) values (1, 1);
INSERT INTO user_authority (user_id, authority_id) values (1, 2);
INSERT INTO user_authority (user_id, authority_id) values (2, 1);