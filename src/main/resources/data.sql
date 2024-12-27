INSERT INTO news_schema.users (username, birthday,first_name,last_name, password) VALUES
                                                                  ('ivanov','2000-01-01','Ivan','Ivanov', '$2a$12$S3hV5SBDIGGBxYq3lOwES.RxpYUX3NXJNSLGQnrhGclqA.OxYRD56'), -- pwd: 123
                                                                  ('petrov','2002-03-03','Petr','Petrov', '$2a$12$pTYOhaGObl60AEB47k4axeKSnZ186TF9hzprFAK3quYxv5BCzp5Pe'),
                                                                  ('sidorov','2001-02-02','Sergey','Sidorov', '$2a$12$pTYOhaGObl60AEB47k4axeKSnZ186TF9hzprFAK3quYxv5BCzp5Pe'),
                                                                  ('nikolaev','2003-03-03','Nikolay','Nikolaev' ,'$2a$12$pTYOhaGObl60AEB47k4axeKSnZ186TF9hzprFAK3quYxv5BCzp5Pe'),
                                                                  ('alexandrov','2004-04-04','Alexander','Alexandrov' ,'$2a$12$pTYOhaGObl60AEB47k4axeKSnZ186TF9hzprFAK3quYxv5BCzp5Pe'),
                                                                  ('igorev','2005-05-05','Igor','Igorev' ,'$2a$12$pTYOhaGObl60AEB47k4axeKSnZ186TF9hzprFAK3quYxv5BCzp5Pe'),
                                                                  ('victorov','2006-06-06','Victor','Victorov' ,'$2a$12$pTYOhaGObl60AEB47k4axeKSnZ186TF9hzprFAK3quYxv5BCzp5Pe'),
                                                                  ('ilyushin','2007-07-07','Ilya','Ilyushin' ,'$2a$12$pTYOhaGObl60AEB47k4axeKSnZ186TF9hzprFAK3quYxv5BCzp5Pe'),
                                                                  ('stepanov','2008-08-08','Stepan','Stepanov' ,'$2a$12$pTYOhaGObl60AEB47k4axeKSnZ186TF9hzprFAK3quYxv5BCzp5Pe'),
                                                                  ('konstantinov','2009-09-09','Konstantin','Konstantinov' ,'$2a$12$pTYOhaGObl60AEB47k4axeKSnZ186TF9hzprFAK3quYxv5BCzp5Pe');

INSERT INTO news_schema.roles (authority, user_id) VALUES
                                                       ('ROLE_ADMIN', 1),
                                                       ('ROLE_MODERATOR', 2),
                                                       ('ROLE_USER', 3),
                                                       ('ROLE_USER', 4),
                                                       ('ROLE_USER', 5),
                                                       ('ROLE_USER', 6),
                                                       ('ROLE_USER', 7),
                                                       ('ROLE_USER', 8),
                                                       ('ROLE_USER', 9),
                                                       ('ROLE_ADMIN', 9),
                                                       ('ROLE_USER', 10),
                                                       ('ROLE_MODERATOR', 10);

INSERT INTO news_schema.news_categories (name) VALUES
                                                   ('Sport'),
                                                   ('Politic'),
                                                   ('Economy');

INSERT INTO news_schema.news (category_id,created_at,updated_at,user_id,body,title) VALUES
                                                                                        (1,'2024-07-02 14:07:48.240791+10','2024-07-02 14:07:48.242813+10',1,' Some text....','News from user: FN 1 LN 1'),
                                                                                        (1,'2024-07-02 14:07:48.251319+10','2024-07-02 14:07:48.25224+10',2,' Some text....','News from user: FN 3 LN 3'),
                                                                                        (3,'2024-07-02 14:07:48.259557+10','2024-07-02 14:07:48.260487+10',3,' Some text....','News from user: FN 3 LN 3'),
                                                                                        (1,'2024-07-02 14:07:48.267681+10','2024-07-02 14:07:48.268569+10',4,' Some text....','News from user: FN 4 LN 4'),
                                                                                        (1,'2024-07-02 14:07:48.275523+10','2024-07-02 14:07:48.276379+10',4,' Some text....','News from user: FN 4 LN 4'),
                                                                                        (1,'2024-07-02 14:07:48.283424+10','2024-07-02 14:07:48.284197+10',5,' Some text....','News from user: FN 5 LN 5'),
                                                                                        (2,'2024-07-02 14:07:48.290837+10','2024-07-02 14:07:48.291639+10',5,' Some text....','News from user: FN 5 LN 5'),
                                                                                        (3,'2024-07-02 14:07:48.299681+10','2024-07-02 14:07:48.30067+10',6,' Some text....','News from user: FN 6 LN 6'),
                                                                                        (2,'2024-07-02 14:07:48.308226+10','2024-07-02 14:07:48.309184+10',6,' Some text....','News from user: FN 6 LN 6'),
                                                                                        (2,'2024-07-02 14:07:48.31643+10','2024-07-02 14:07:48.317778+10',6,' Some text....','News from user: FN 6 LN 6'),
                                                                                        (2,'2024-07-02 14:07:48.325016+10','2024-07-02 14:07:48.325892+10',6,' Some text....','News from user: FN 6 LN 6'),
                                                                                        (3,'2024-07-02 14:07:48.331836+10','2024-07-02 14:07:48.332647+10',7,' Some text....','News from user: FN 7 LN 7'),
                                                                                        (2,'2024-07-02 14:07:48.339252+10','2024-07-02 14:07:48.340136+10',7,' Some text....','News from user: FN 7 LN 7'),
                                                                                        (3,'2024-07-02 14:07:48.346468+10','2024-07-02 14:07:48.347559+10',7,' Some text....','News from user: FN 7 LN 7'),
                                                                                        (2,'2024-07-02 14:07:48.354052+10','2024-07-02 14:07:48.354752+10',8,' Some text....','News from user: FN 8 LN 8'),
                                                                                        (1,'2024-07-02 14:07:48.360728+10','2024-07-02 14:07:48.361693+10',8,' Some text....','News from user: FN 8 LN 8'),
                                                                                        (2,'2024-07-02 14:07:48.367784+10','2024-07-02 14:07:48.36862+10',8,' Some text....','News from user: FN 8 LN 8'),
                                                                                        (1,'2024-07-02 14:07:48.374281+10','2024-07-02 14:07:48.375085+10',8,' Some text....','News from user: FN 8 LN 8'),
                                                                                        (2,'2024-07-02 14:07:48.381786+10','2024-07-02 14:07:48.382453+10',9,' Some text....','News from user: FN 9 LN 9'),
                                                                                        (2,'2024-07-02 14:07:48.398434+10','2024-07-02 14:07:48.4012+10',9,' Some text....','News from user: FN 9 LN 9'),
                                                                                        (2,'2024-07-02 14:07:48.416981+10','2024-07-02 14:07:48.419194+10',9,' Some text....','News from user: FN 9 LN 9'),
                                                                                        (1,'2024-07-02 14:07:48.429321+10','2024-07-02 14:07:48.430423+10',10,' Some text....','News from user: FN 10 LN 10'),
                                                                                        (3,'2024-07-02 14:07:48.437929+10','2024-07-02 14:07:48.43898+10',10,' Some text....','News from user: FN 10 LN 10'),
                                                                                        (3,'2024-07-02 14:07:48.445772+10','2024-07-02 14:07:48.446628+10',10,' Some text....','News from user: FN 10 LN 10'),
                                                                                        (3,'2024-07-02 14:07:48.461988+10','2024-07-02 14:07:48.464698+10',10,' Some text....','News from user: FN 10 LN 10');

INSERT INTO news_schema."comments" (created_at,news_id,updated_at,user_id,body) VALUES
                                                                                    ('2024-07-02 14:07:48.487348+10',7,'2024-07-02 14:07:48.48736+10',1,' Some comment from user1 to news 7'),
                                                                                    ('2024-07-02 14:07:48.498246+10',1,'2024-07-02 14:07:48.498255+10',2,' Some comment from user 2 to news 1'),
                                                                                    ('2024-07-02 14:07:48.507277+10',20,'2024-07-02 14:07:48.507286+10',3,' Some comment from user 3 to news 20');
