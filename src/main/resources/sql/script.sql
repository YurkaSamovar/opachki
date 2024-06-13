CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    mail VARCHAR(128) NOT NULL UNIQUE,
    username VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) DEFAULT '{noop}123',
    firstname VARCHAR(128),
    lastname VARCHAR(128),
    birth_date DATE,
    role VARCHAR(32)

);

ALTER TABLE users ADD avatar VARCHAR(128) DEFAULT 'opachki.jpg';
ALTER TABLE users ALTER COLUMN role SET DEFAULT 'USER';

CREATE TABLE theme
(
    id   SERIAL,
    name VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE card
(
    id          SERIAL,
    image       VARCHAR(128) NOT NULL,
    title       VARCHAR(256) NOT NULL,
    prix        INT          NOT NULL,
    description TEXT         NOT NULL,
    date        DATE         NOT NULL,
    adresse     VARCHAR(256),
    link        TEXT,
    theme_id    INT REFERENCES theme (id),
    user_id     INT REFERENCES users (id)
);

insert into users(mail, username, firstname, lastname, birth_date, role)
VALUES ('zarob@zr.com', 'zarob', 'Oleg', 'Olegovich', '2000-10-10', 'ADMIN'),
       ('ivan@suka.com', 'ivan', 'Ivan', 'Ivanov', '2010-04-20', 'USER'),
       ('petrova@pierre.fr', 'petr', 'Petouha', 'Petrovich', '1999-11-01', 'USER');

INSERT INTO theme(name)
VALUES ('Fêtes'),
       ('Concert'),
       ('Art et culture'),
       ('Pour les enfants');

INSERT INTO card(image, title, prix, description, date, adresse, link, theme_id)
VALUES ('art.jpg', 'Art des images', 30, 'Cest très bonne art pour vous', '2024-05-18', 'Dijon 21 all is goob', 'facbook', '3'),
       ('fete.jpg', 'Fete pour rester', 0, 'Allez chez nous pour boire et rester', '2024-05-17', 'Dijon 21 dont drinc much', 'google',
        '1'),
       ('concert.jpg', 'Concert de Mozart', 100, 'Mosieur Mozart jeurai la music', '2024-05-19', 'Dijon 21 opachki 111', 'facbook',
        '2'),
       ('enfents.jpg', 'Fete pour des enfants', 200, 'Vous pouvez laisser des enfant avec nous', '2024-05-20',
        'Dijon 21 pas monger ca', 'yandex', '3');
