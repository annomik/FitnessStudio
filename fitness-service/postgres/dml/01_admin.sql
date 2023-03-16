INSERT INTO fitness."user" ( uuid, dt_create, dt_update, mail, fio, password)
VALUES ('00009807-2f50-4645-b2a0-5937df787111', now() , now() , 'admin@mail.ru', 'admin222', '$2a$10$YmyNOQRjBIaSRU.sDf.hquXolj6ux44j8n.kKINoPEx.ZoC8jyAdm');

INSERT INTO fitness.user_role ( user_uuid, role_id)
VALUES ('00009807-2f50-4645-b2a0-5937df787111', 'ADMIN' );

INSERT INTO fitness.user_status ( user_uuid, status_id)
VALUES ('00009807-2f50-4645-b2a0-5937df787111', 'ACTIVATED' );