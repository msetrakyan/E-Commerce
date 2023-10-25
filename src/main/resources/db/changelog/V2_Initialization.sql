INSERT INTO roles (role) VALUES ('ADMIN');

INSERT INTO roles (role) VALUES ('USER');

ALTER TABLE if exists users
    ALTER COLUMN role_id SET NOT NULL;