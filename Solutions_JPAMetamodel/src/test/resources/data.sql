INSERT INTO author (id, firstname, lastname, version) VALUES (1, 'John', 'Doe', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (2, 'Another', 'Author', 0);

INSERT INTO book (id, publishingdate, title, version) VALUES (1, '2016-01-01', 'A great book about Java', 0);

INSERT INTO bookauthor (bookid, authorid) VALUES (1, 1);
INSERT INTO bookauthor (bookid, authorid) VALUES (1, 2);
