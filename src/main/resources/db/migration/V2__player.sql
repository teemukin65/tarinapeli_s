create table player (
  id SERIAL PRIMARY KEY ,
  email CHAR(250) NOT NULL ,
  nickname CHAR(20),
  active BOOLEAN
);




CREATE TABLE game (
  game_id SERIAL PRIMARY KEY,
  game_initiation_time TIMESTAMP NOT NULL ,
  game_intiator INTEGER REFERENCES player(id) NOT NULL ,
  game_status VARCHAR,
  game_title CHAR(80),
  game_description CHAR(160),
  game_start_time TIMESTAMP,
  game_end_time TIMESTAMP


);
