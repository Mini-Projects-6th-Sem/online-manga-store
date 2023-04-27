create trigger stock
after INSERT 
on shopping_basket 
for each row
update S_num set S_num = S_num - num_books where container.ISBN = manga.ISBN; 