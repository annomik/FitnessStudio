INSERT INTO fitness."user" ( uuid, dt_create, dt_update, mail, fio, password)
VALUES (
'33f09807-2f50-4645-b2a0-5937df787111', '2023-03-13 17:41:35.72',
 '2023-03-13 17:41:35.72', 'admin@mail.ru', 'adminAA', '123')
 returning uuid;