-- Drop existing data
DELETE FROM auth_users;

-- Insert users with a known working password hash for 'password123'
INSERT INTO auth_users (name, email, password_hash) VALUES
('Leanne Graham', 'Sincere@april.biz', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG'),
('Ervin Howell', 'Shanna@melissa.tv', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG'),
('Clementine Bauch', 'Nathan@yesenia.net', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG'),
('Patricia Lebsack', 'Julianne.OConner@kory.org', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG'),
('Chelsey Dietrich', 'Lucio_Hettinger@annie.ca', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG'); 