USE athletics_db;

-- Insert Athletics disciplines
INSERT INTO disciplines (discipline_name) VALUES
                                              ('100m Dash'),
                                              ('Long Jump'),
                                              ('Shot Put'),
                                              ('High Jump'),
                                              ('400m Dash'),
                                              ('Discus Throw'),
                                              ('Pole Vault'),
                                              ('110m Hurdles'),
                                              ('Hammer Throw'),
                                              ('Triple Jump'),
                                              ('800m Run'),
                                              ('Javelin Throw');
INSERT INTO results (result_type, date, result) VALUES
                                                    ('TIME', '2023-05-15', 10.5),
                                                    ('DISTANCE', '2023-05-15', 7.2),
                                                    ('DISTANCE', '2023-05-16', 15.5),
                                                    ('DISTANCE', '2023-05-16', 1.85),
                                                    ('TIME', '2023-05-17', 50.2),
                                                    ('DISTANCE', '2023-05-17', 45.8),
                                                    ('DISTANCE', '2023-05-18', 4.8),
                                                    ('TIME', '2023-05-18', 14.3),
                                                    ('DISTANCE', '2023-05-19', 62.1),
                                                    ('DISTANCE', '2023-05-19', 14.9),
                                                    ('TIME', '2023-05-20', 115.5),
                                                    ('DISTANCE', '2023-05-20', 75.4);

INSERT INTO participants (first_name, last_name, age, gender, club)
VALUES ('John', 'Doe', 25, 'MALE', 'Swimming Club');

INSERT INTO participants (first_name, last_name, age, gender, club)
VALUES ('Jane', 'Doe', 30, 'FEMALE', 'Running Club');
