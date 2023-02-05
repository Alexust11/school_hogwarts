CREATE TABLE users (
    Id  SERIAL PRIMARY KEY ,
    FirstName VARCHAR(10) DEFAULT '*********',
    SurName VARCHAR (15) NOT NULL UNIQUE,
    Age INTEGER CHECK ( Age>10 ) NOT NULL
);

ALTER TABLE users ADD login  VARCHAR(8) NOT NULL ;/*добавление в пустую таблицу столбца*/

ALTER TABLE users ADD CONSTRAINT add_constaint CHECK (login IS NOT NULL ); /*вводит ограничения в уже заполненную таблицу*/

INSERT INTO public.users (id, firstname, surname, age, login) VALUES (3,'sd' ,'lexxoff',12, 'dsdf');/*вставка записи в таблицу*/

SELECT * FROM users ORDER BY Age ASC; /*сортировка по столбцу asc -по возраст., desc -по убыванию*/

SELECT COUNT(SurName) FROM users /*подсчет не нулевых сторк по колонке или по все таблице (если оставить вместо названия колонки *)*/