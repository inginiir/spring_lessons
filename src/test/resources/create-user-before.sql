delete from user_role;
delete from usr;

insert into usr(id, is_active, password, username) values
(1, true, '$2a$08$UZarcvUJPsTdRszVskVKHeOuFwEf/ISjGpygBatNCBka9.vICE8Tq', 'inginiir'),
(2, true, '$2a$08$UZarcvUJPsTdRszVskVKHeOuFwEf/ISjGpygBatNCBka9.vICE8Tq', 'pavel');

insert into user_role(user_id, roles) values
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER');