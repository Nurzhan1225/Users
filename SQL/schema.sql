insert into city (index, name)
values(01, 'Astana'),
      (02, 'London'),
      (03, 'Tokio'),
      (04, 'Roma');

insert into users (name, surname, login, password, city_id)
values ('Alex', 'Alexovich', 'alex_astana', '01', 1),
       ('Bin', 'Toni', 'Toni_Bin', 'bin', 2);
