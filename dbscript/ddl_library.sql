
CREATE TABLE t_library (
    id INT PRIMARY KEY,  
    library_name VARCHAR(255)
);

CREATE TABLE t_aisle (
    aisle_id INT PRIMARY KEY,
    aisle_name VARCHAR(255),
    library_id INT
);
alter table t_aisle add constraint fk_aisle_library_id foreign key(library_id) references t_library(id);

CREATE TABLE t_book (
    id INT PRIMARY KEY,  
    book_name VARCHAR(255),
    aisle_id int
);

alter table t_book add constraint fk_book_aisle_id foreign key(aisle_id) references t_aisle(aisle_id);

insert into t_library(id, library_name) values(1, 'CENTRAL LIBRARY');


insert into t_aisle(aisle_id, aisle_name, library_id)
values(1, 'Fiction', 1);

insert into t_aisle(aisle_id, aisle_name, library_id)
values(2, 'Science', 1);

insert into t_aisle(aisle_id, aisle_name, library_id)
values(3, 'Economics', 1);

insert into t_book(id, book_name, aisle_id)
values(1, 'NATURE_HISTORY',1);

insert into t_book(id, book_name, aisle_id)
values(2, 'MAKING MONEY',3);

insert into t_book(id, book_name, aisle_id)
values(3, 'TOM CRUISE ADVENTURES',1);

insert into t_book(id, book_name, aisle_id)
values(4, 'TITANIC',2);

insert into t_book(id, book_name, aisle_id)
values(5, 'THE LORD OF THE RING',1);

insert into t_book(id, book_name, aisle_id)
values(6, 'TITANIC',1);


select tb.id, tb.book_name , ta.aisle_name , tl.library_name 
from t_book tb, t_aisle ta, t_library tl
where  tb.aisle_id = ta.aisle_id
and ta.library_id = tl.id
and ta.aisle_name = 'Fiction'
and tl.library_name = 'CENTRAL LIBRARY';

select ta.*
from t_aisle ta, t_book tb
where ta.aisle_id = tb.aisle_id
and tb.book_name = 'TITANIC';

select * from t_book;

