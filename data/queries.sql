-- Query 1
-- Select manga written by kentaro mirua OR published by crunchyroll
SELECT title, price from manga 
WHERE published = "Shueisha"
UNION
SELECT title, price from manga
where author = "Kentaro Mirua";

-- Query 2
-- Find mangas whose quantity is equal to or more than 3

SELECT title, author, price from container
LEFT JOIN manga on manga.ISBN = container.ISBN
WHERE num_books >= 3;

-- Query 3
-- Find contact details of publisher who published manga Jujutsu Kaisen

SELECT p_phone, p_address from publisher
WHERE p_name in (
    SELECT published FROM manga
    WHERE title = "Jujutsu Kaisen"
);

-- Query 4
-- Find all manga where it is stored in lot A1

SELECT title from manga
LEFT JOIN warehouse ON manga.s_id = warehouse.s_id
WHERE S_lot = "A1";

-- Query 5
-- Retrieve customer delivery details from their basket ID 1PN33
SELECT c_name, c_address, c_mail FROM shopping_basket
LEFT JOIN customer ON shopping_basket.c_id = customer.c_id
WHERE shopping_basket.c_id = "1PN33";

SELECT COUNT(publisher), price
FROM manga
group by publisher
HAVING COUNT(publisher) = 2;

SELECT * FROM MANGA
ORDER BY PRICE;


SELECT title, price from manga 
WHERE published = "Crunchyroll"
INTERSECT
SELECT title, price from manga
where author = "Kentaro Mirua";



-- Display auth name, title and price fro a manga where manga is in more than 4 shopping baskets
-- input shopping basket