CREATE DATABASE BookTrain;

USE BookTrain;

CREATE TABLE PersonType (
    person_type_id INT PRIMARY KEY IDENTITY(1,1),
    person_type_name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE Person (
    person_id INT PRIMARY KEY IDENTITY(1,1),
    person_type_id INT NOT NULL FOREIGN KEY REFERENCES PersonType(person_type_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    first_name VARCHAR(30) NOT NULL,
    middle_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(80) UNIQUE NOT NULL,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE PhoneNumber (
    person_id INT NOT NULL FOREIGN KEY REFERENCES Person(person_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    phone_number VARCHAR(30) NOT NULL,

    CONSTRAINT chk_phone UNIQUE(person_id, phone_number)
);

CREATE TABLE TrainType (
    train_type_id INT PRIMARY KEY IDENTITY(1,1),
    train_type_name VARCHAR(30)
);

CREATE TABLE Train (
    train_id INT PRIMARY KEY IDENTITY(1,1),
    train_type_id INT NOT NULL FOREIGN KEY REFERENCES TrainType(train_type_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    capacity INT NOT NULL,
);

CREATE TABLE Trip (
    trip_id INT PRIMARY KEY IDENTITY(1,1),
    train_id INT NOT NULL FOREIGN KEY REFERENCES Train(train_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    trip_duration TIME NOT NULL,
    trip_date DATETIME NOT NULL,
    trip_source VARCHAR(30) NOT NULL,
    trip_destination VARCHAR(30) NOT NULL,
    trip_price_unit DECIMAL NOT NULL,
    trip_seats INT NOT NULL DEFAULT 0,

    CONSTRAINT chk_trip UNIQUE(trip_date, trip_source, trip_destination)
);


CREATE TABLE Station (
    trip_id INT NOT NULL FOREIGN KEY REFERENCES Trip(trip_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    station_name VARCHAR(30) NOT NULL,
    duration TIME NOT NULL,

    CONSTRAINT chk_station UNIQUE(trip_id, station_name, duration)
);

CREATE TABLE Reserves (
    reservation_id INT PRIMARY KEY IDENTITY(1,1),
    reservation_date DATETIME NOT NULL DEFAULT GETDATE(),
    reservation_seats INT NOT NULL,
    person_id INT NOT NULL FOREIGN KEY REFERENCES Person(person_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    trip_id INT NOT NULL FOREIGN KEY REFERENCES Trip(trip_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    CONSTRAINT chk_reservation UNIQUE(person_id, trip_id)
);
