INSERT INTO PersonType(person_type_name) VALUES('Admin');
INSERT INTO PersonType(person_type_name) VALUES('Customer');



INSERT INTO TrainType(train_type_name) VALUES('Locomotive');
INSERT INTO TrainType(train_type_name) VALUES('Bogies');



INSERT INTO Person(person_type_id, first_name, middle_name, last_name, email, password)
VALUES(2, 'Mahmoud', 'Mamdouh', 'Abdelaziz', 'mahmoudaziz@test.com', '123mm1234');
INSERT INTO PhoneNumber(person_id, phone_number) VALUES(1, '38448770');

INSERT INTO Person(person_type_id, first_name, middle_name, last_name, email, password)
VALUES(2, 'Aly', 'Essam', 'Achraf', 'alyachraf@test.com', '@alesac23');
INSERT INTO PhoneNumber(person_id, phone_number) VALUES(2, '66848992');

INSERT INTO Person(person_type_id, first_name, middle_name, last_name, email, password)
VALUES(2, 'Mohammad', 'Barakat', 'Fathy', 'mohammadfathy@test.com', 'mabafa12@43');
INSERT INTO PhoneNumber(person_id, phone_number) VALUES(3, '38478000');

INSERT INTO Person(person_type_id, first_name, middle_name, last_name, email, password)
VALUES(2, 'Moubarak', 'Ahmed', 'Abdullah', 'moubarakahmed@test.com', '#moahab45&6');
INSERT INTO PhoneNumber(person_id, phone_number) VALUES(4, '38493970');

INSERT INTO Person(person_type_id, first_name, middle_name, last_name, email, password)
VALUES(2, 'Shahd', 'Moustafa', 'Hashem', 'shahdmoustafa@test.com', 'shmoha##1&4');
INSERT INTO PhoneNumber(person_id, phone_number) VALUES(5, '38909804');

INSERT INTO Person(person_type_id, first_name, middle_name, last_name, email, password)
VALUES(2, 'Walaa', 'Khaled', 'Yousuf', 'walaakhaled@test.com', 'wakhyo##123');
INSERT INTO PhoneNumber(person_id, phone_number) VALUES(6, '38439002');

INSERT INTO Person(person_type_id, first_name, middle_name, last_name, email, password)
VALUES(1, 'Ahmed', 'Aly', 'Ghareb', 'ahmedghareb@test.com', '123MM##23')



INSERT INTO Train(train_type_id, capacity) VALUES(1, 200, 0);

INSERT INTO Train(train_type_id, capacity) VALUES(2, 150, 0);

INSERT INTO Train(train_type_id, capacity) VALUES(1, 250, 0);

INSERT INTO Train(train_type_id, capacity) VALUES(1, 230, 0);

INSERT INTO Train(train_type_id, capacity) VALUES(2, 190, 0);

INSERT INTO Train(train_type_id, capacity) VALUES(1, 200, 0);




INSERT INTO Trip(train_id, trip_duration, trip_date, trip_source, trip_destination)
VALUES(1, '12:00:00', '2019-08-18 09:00:00', 'Giza', 'Aswan', 119.99);



INSERT INTO Trip(train_id, trip_duration, trip_date, trip_source, trip_destination)
VALUES(2, '06:00:00', '2019-10-02 06:00:00', 'Cairo', 'Alexandria', 89.99);


INSERT INTO Trip(train_id, trip_duration, trip_date, trip_source, trip_destination)
VALUES(3, '08:00:00', '2019-07-11 07:00:00', 'Giza', 'Hurghada', 75.99);



INSERT INTO Trip(train_id, trip_duration, trip_date, trip_source, trip_destination)
VALUES(4, '13:00:00', '2019-10-11 07:30:00', 'Giza', 'Luxor', 89.99);



INSERT INTO Reserves(reservation_seats, person_id, trip_id)
VALUES(5, 1, 1);

INSERT INTO Reserves(reservation_seats, person_id, trip_id)
VALUES(3, 1, 2);

INSERT INTO Reserves(reservation_seats, person_id, trip_id)
VALUES(5, 2, 3);

INSERT INTO Reserves(reservation_seats, person_id, trip_id)
VALUES(4, 1, 4);


INSERT INTO Station(trip_id, station_name, duration) VALUES(1, 'Asyut', '01:00:00');
INSERT INTO Station(trip_id, station_name, duration) VALUES(1, 'Qena', '01:00:00');

INSERT INTO Station(trip_id, station_name, duration) VALUES(2, 'Tanta', '01:00:00');
INSERT INTO Station(trip_id, station_name, duration) VALUES(2, 'Damanhour', '01:00:00');

INSERT INTO Station(trip_id, station_name, duration) VALUES(3, 'Ain Sokhna', '01:00:00');
INSERT INTO Station(trip_id, station_name, duration) VALUES(3, 'Ras Ghareb', '01:00:00');

INSERT INTO Station(trip_id, station_name, duration) VALUES(4, 'Asyut', '01:00:00');
INSERT INTO Station(trip_id, station_name, duration) VALUES(4, 'Qena', '01:00:00');