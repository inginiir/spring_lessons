delete from message;

insert into message(id, tag, text, user_id) values
(1, 'test1', 't1', 1),
(2, 'test2', 't2', 2),
(3, 'test3', 't3', 1),
(4, 'test4', 't4', 2);

alter sequence hibernate_sequence restart with 10;
