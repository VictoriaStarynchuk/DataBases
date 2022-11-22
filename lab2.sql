-- 1. БД «Комп. фірма». Знайти номер моделі, об’єм пам’яті та розміри
-- екранів ноутбуків, ціна яких перевищує 1000 дол. Вивести: model, ram,
-- screen, price. Вихідні дані впорядкувати за зростанням за стовпцем
-- ram та за спаданням за стовпцем price. 
SELECT model, ram, screen, price 
FROM Laptop
WHERE price > 1000
ORDER BY ram ASC, price DESC;

-- 2. БД «Кораблі». Знайти всі кораблі, імена класів яких закінчуються
-- літерою 'o', але не на 'go'.
SELECT*
FROM Ships 
WHERE class LIKE '%o' AND class NOT LIKE '%go';

-- 3. БД «Комп. фірма». Вкажіть виробника для тих ноутбуків, що мають
-- жорсткий диск об’ємом не менше 10 Гбайт. Вивести: maker, type,
-- speed, hd.
SELECT DISTINCT maker, type, speed, hd 
FROM Laptop, Product 
WHERE hd >= 10 AND type='Laptop';

-- 4. БД «Комп. фірма». Знайдіть виробників, що випускають ПК, але не
-- ноутбуки (використати ключове слово ALL). Вивести maker.  
SELECT DISTINCT maker 
FROM Product
WHERE type = 'PC' AND maker != All (SELECT maker FROM Product WHERE type = 'Laptop');

-- 5. БД «Комп. фірма». Знайдіть виробників, які б випускали одночасно
-- ПК та ноутбуки зі швидкістю 750 МГц та вище. Виведіть: maker. 
SELECT DISTINCT maker
FROM product, pc
WHERE type='PC' AND speed >= 750 AND  maker IN (SELECT maker 
FROM product, laptop 
WHERE type = 'Laptop' AND speed >= 750);

-- 6. БД «Фірма прий. вторсировини». З таблиці Income виведіть дати в
-- такому форматі: рік.число_місяця.день, наприклад, 2001.02.15 (без
-- формату часу). 
SELECT date_format(date, '%d.%m.%Y') AS date 
FROM Income;

-- 7. БД «Комп. фірма». Знайти тих виробників ПК, усі моделі ПК яких є
-- в наявності в таблиці PC (використовувати засоби групової
-- статистики). Вивести maker.
SELECT maker
FROM Product, PC
WHERE EXISTS (SELECT model FROM PC WHERE PC.model = Product.model) 
GROUP BY maker;


-- 8. БД «Комп. фірма». Для кожного виробника знайдіть середній
-- розмір екрану для ноутбуків, що ним випускається. Вивести: maker,
-- середній розмір екрану. (Підказка: використовувати підзапити в
-- якості обчислювальних стовпців)
SELECT maker, AVG(screen)
FROM (SELECT maker, model FROM Product WHERE type='Laptop') AS makers, laptop
WHERE laptop.model = makers.model
GROUP BY maker
ORDER BY 2;

-- 9. БД «Фірма прий. вторсировини». Приймаючи, що прихід та розхід
-- грошей на кожному пункті прийому фіксується не частіше одного
-- разу на день (лише таблиці Income_o та Outcome_o), написати запит із
-- такими вихідними даними: point (пункт), date (дата), inc (прихід), out
-- (розхід). (Підказка: використовувати зовнішнє з’єднання та оператор
-- CASE)
SELECT point, date, sum(incomes), sum(outcomes) FROM
	(SELECT point, date,
	CASE WHEN inc IS null THEN 0 ELSE inc END incomes,
	CASE WHEN outc.out IS null THEN 0 ELSE outc.out END outcomes 
	FROM income_o LEFT JOIN outcome_o outc USING(point, date)) AS res
	GROUP BY point, date;

-- 10. БД «Комп. фірма». Знайдіть середню ціну ПК та ноутбуків, що
-- випущені виробником 'A'. Вивести: одне число для загальної середньої
-- ціни. (Підказка: використовувати оператор UNION)
SELECT avg(price)
FROM (SELECT model, price FROM PC
UNION 
SELECT model, price FROM Laptop) AS price
WHERE model IN (SELECT model FROM Product WHERE maker = 'A');











