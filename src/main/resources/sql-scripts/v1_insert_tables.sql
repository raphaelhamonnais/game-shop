INSERT INTO category 
VALUES 
(1, 'action'), 
(2, 'adventure'), 
(3, 'reflextion'), 
(4, 'role-playing'), 
(5, 'simulation'), 
(6, 'sports'), 
(7, 'strategy');

SELECT * FROM category;

DELETE FROM category;



INSERT INTO console VALUES 
(1, 'PC'),
(2, 'PS4'),
(3, 'Switch'),
(4, 'Wii U'),
(5, 'Xbox 360');

SELECT * FROM console;

DELETE FROM console;



INSERT INTO game 
VALUES 
(1, 2, 1, 'Assassin\'s Creed', 0, 'Game 1', 'E:', FALSE, 1.00, FALSE, FALSE, FALSE, '2017-04-09 16:13:22'),
(2, 1, 1, 'Contra', 0, 'Game 2', 'E:', FALSE, 1.00, FALSE, FALSE, FALSE, '2017-04-09 16:13:22'),
(3, 6, 1, 'FIFA 16', 0, 'Game 3', 'E:', FALSE, 1.00, FALSE, FALSE, FALSE, '2017-04-09 16:13:22'),
(4, 7, 1, 'League of Legends', 0, 'Game 4', 'E:', FALSE, 1.00, FALSE, FALSE, FALSE, '2017-04-09 16:13:22'),
(5, 5, 1, 'Red Alert', 0, 'Game 5', 'E:', FALSE, 1.00, FALSE, FALSE, FALSE, '2017-04-09 16:13:22'),
(6, 3, 1, 'Tetris', 0, 'Game 6', 'E:', FALSE, 1.00, FALSE, FALSE, FALSE, '2017-04-09 16:13:22'),
(7, 4, 1, 'The Battle for Wesnoth', 0, 'Game 7', 'E:', FALSE, 1.00, FALSE, FALSE, FALSE, '2017-04-09 16:13:22'),
(8, 2, 1, 'The Walking Dead', 0, 'Game 8', 'E:', FALSE, 1.00, FALSE, FALSE, FALSE, '2017-04-09 16:13:22'),
(9, 4, 1, 'Star Wars', 0, 'Game 9', 'E:', FALSE, 1.00, FALSE, FALSE, FALSE, '2017-04-09 16:13:22'),
(10, 3, 1, 'Super Mario Bros', 0, 'Game 10', 'E:', FALSE, 1.00, FALSE, FALSE, FALSE, '2017-04-09 16:13:22');

SELECT * FROM game;

DELETE FROM game;



INSERT INTO category_game 
VALUES 
(1, 2),
(2, 1),
(3, 6),
(4, 7),
(5, 5),
(6, 3),
(7, 4),
(8, 2),
(9, 4),
(10, 3);

SELECT * FROM category_game;

DELETE FROM category_game;



INSERT INTO stock_game 
VALUES 
(1, 1, 0, 0.00),
(2, 1, 0, 0.00),
(3, 1, 0, 0.00),
(4, 1, 0, 0.00),
(5, 1, 0, 0.00),
(6, 1, 0, 0.00),
(7, 1, 0, 0.00),
(8, 1, 0, 0.00),
(9, 1, 0, 0.00),
(10, 1, 0, 0.00);

SELECT * FROM stock_game;

DELETE FROM stock_game;


