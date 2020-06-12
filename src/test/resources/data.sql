-- initial data population

INSERT INTO USERS (ID, USER_NAME) VALUES (1, 'Tomas');
INSERT INTO USERS (ID, USER_NAME) VALUES (2, 'Zilvinas');
INSERT INTO USERS (ID, USER_NAME) VALUES (3, 'Jankunas');

INSERT into artist (artist_id, artist_name, amg_artist_id) values (2715720, 'Kanye West', 353484);
INSERT into artist (artist_id, artist_name, amg_artist_id) values (538474829, 'IDLES', 3252083);
INSERT into artist (artist_id, artist_name, amg_artist_id) values (405563985, 'Jamie xx', 2305861);

INSERT into USERS_FAVOURITES (USERS_ID, FAVOURITES_AMG_ARTIST_ID) values (1, 353484);
INSERT into USERS_FAVOURITES (USERS_ID, FAVOURITES_AMG_ARTIST_ID) values (2, 3252083);
INSERT into USERS_FAVOURITES (USERS_ID, FAVOURITES_AMG_ARTIST_ID) values (3, 2305861);

