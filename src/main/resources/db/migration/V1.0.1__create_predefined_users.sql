

insert into roles (name)
values  ('ROLE_USER'),
        ('ROLE_MANAGER'),
        ('ROLE_ADMIN');

-- admin - admin
-- user - user
insert into users (username, password, email)
values ('user', '$2y$10$6OeU.RxbWSZyKHwss.eFNuR4fkiNutu2u1.KucH6kQley3C2P35va', 'user@gmail.com'),
       ('admin', '$2y$10$Y4B07FIej.1fsFOFj9Vqi.6ILnGQplijbCAst1YQTGcFP/PmC333C', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 3);