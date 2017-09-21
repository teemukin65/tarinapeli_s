create table player (
  id       SERIAL PRIMARY KEY NOT NULL,
  email    CHAR(250)          NOT NULL ,
  nickname CHAR(20),
  active   BOOLEAN
);

CREATE TABLE game_status (
  game_status CHAR(11) UNIQUE PRIMARY KEY
);

INSERT INTO game_status VALUES ('INITIATING');
INSERT INTO game_status VALUES ('INVITING');
INSERT INTO game_status VALUES ('PLAYING');
INSERT INTO game_status VALUES ('ENDING');
INSERT INTO game_status VALUES ('VIEWING');


CREATE TABLE game (
  game_id              SERIAL PRIMARY KEY              NOT NULL,
  game_initiation_time TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  game_initiator       INTEGER REFERENCES player (id)  NOT NULL,
  game_status          CHAR(15) REFERENCES game_status NOT NULL DEFAULT 'INITIATING',
  game_title           CHAR(80),
  game_description     CHAR(160),
  game_start_time      TIMESTAMP,
  game_end_time        TIMESTAMP,
  CONSTRAINT game_initiator_and_time_must_be_unique UNIQUE (game_initiator, game_initiation_time)
);
