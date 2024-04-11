insert into client (name) values
                                  ('Lili'),
                                  ('Sisi'),
                                  ('Susy'),
                                  ('Mark'),
                                  ('Bob'),
                                  ('John'),
                                  ('Jack'),
                                  ('Luise'),
                                  ('Jim'),
                                  ('Bella');
insert into planet (id, name) values
                                  ('MER1', 'Mercury'),
                                  ('MARS4', 'Mars'),
                                  ('VEN2', 'Venus'),
                                  ('SAT6', 'Saturn'),
                                  ('JUP5', 'Jupiter');
insert into ticket (client_id, from_planet_id, to_planet_id) values
                                                                 (1, 'MER1', 'VEN2'),
                                                                 (2, 'SAT6', 'MARS4'),
                                                                 (3, 'JUP5', 'VEN2'),
                                                                 (4, 'VEN2', 'SAT6'),
                                                                 (5, 'MARS4','SAT6'),
                                                                 (6, 'MARS4', 'JUP5'),
                                                                 (7, 'JUP5', 'SAT6'),
                                                                 (8, 'VEN2', 'MARS4'),
                                                                 (9, 'SAT6','VEN2'),
                                                                 (10, 'VEN2','SAT6');