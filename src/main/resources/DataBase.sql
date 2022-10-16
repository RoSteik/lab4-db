CREATE
DATABASE IF NOT EXISTS rostyslavchik;
USE `rostyslavchik`;

DROP TABLE IF EXISTS driver_has_car;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS driver;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    name VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE TABLE driver
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(50) NOT NULL,
    rating           INT         NOT NULL,
    completed_orders INT         NOT NULL,
    is_vacant        TINYINT     NOT NULL,
    user_name        VARCHAR(50) NOT NULL,
    CONSTRAINT FOREIGN KEY (user_name) REFERENCES user (name)
);

CREATE TABLE car
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    clas VARCHAR(50) NOT NULL
);

CREATE TABLE driver_has_car
(
    driver_id BIGINT NOT NULL,
    car_id    BIGINT NOT NULL,
    PRIMARY KEY (driver_id, car_id),
    CONSTRAINT FOREIGN KEY (driver_id) REFERENCES driver (id),
    CONSTRAINT FOREIGN KEY (car_id) REFERENCES car (id)
);


insert into `car`
values (1, 'Toyota1', 'middle'),
       (2, 'Toyota2', 'premium'),
       (3, 'Toyota3', 'classic'),
       (4, 'Toyota4', 'middle'),
       (5, 'Toyota5', 'premium'),
       (6, 'Toyota6', 'middle'),
       (7, 'Toyota7', 'middle');

insert into `user`
values ('Sofia'),
       ('Mary'),
       ('Oksana'),
       ('Lesya'),
       ('Zenoviy'),
       ('Liuda'),
       ('Bogdan'),
       ('Andriana');

insert into `driver`
values (1, 'Roman', 5, 56, 0, 'Sofia'),
       (2, 'Rostyk', 5, 565, 0, 'Sofia'),
       (3, 'John', 5, 576, 0, 'Zenoviy'),
       (4, 'Mike', 5, 596, 0, 'Liuda'),
       (5, 'Steve', 5, 586, 0, 'Zenoviy');

insert into `driver_has_car`
values (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (1, 4);

CREATE PROCEDURE InsertDriverCar(
    IN DriverNameIn varchar(25),
    IN CarBrandIn varchar(45),
    OUT msg varchar(40)
)
BEGIN
    -- checks for present name
    IF NOT EXISTS(SELECT * FROM driver WHERE name = DriverNameIn)
    THEN
        SET msg = 'This name is absent';

        -- checks for present car
    ELSEIF NOT EXISTS(SELECT * FROM car WHERE brand = CarBrandIn)
    THEN
        SET msg = 'This brand is absent';

        -- checks if there are this combination already
    ELSEIF EXISTS(SELECT *
                  FROM driver_has_car
                  WHERE driver_id = (SELECT id FROM driver WHERE name = DriverNameIn)
                    AND car_id = (SELECT id FROM car WHERE brand = CarBrandIn)
        )
    THEN
        SET msg = 'This driver already has this car';

        -- makes insert
    ELSE
        INSERT driver_has_car (driver_id, car_id)
            Value ((SELECT id FROM driver WHERE name = DriverNameIn),
                   (SELECT id FROM car WHERE brand = CarBrandIn));
        SET msg = 'OK';

    END IF;

END