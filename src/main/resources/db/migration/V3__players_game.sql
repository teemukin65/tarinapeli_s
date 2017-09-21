CREATE TABLE player_status_values (
  player_status CHAR(10) UNIQUE PRIMARY KEY
);

INSERT INTO player_status_values VALUES ('INVITED');
INSERT INTO player_status_values VALUES ('JOINING');
INSERT INTO player_status_values VALUES ('WAITING');
INSERT INTO player_status_values VALUES ('PLAYING');
INSERT INTO player_status_values VALUES ('AFTERMATH');

CREATE TABLE players_game (
  game          INTEGER REFERENCES game(game_id),
  player        INTEGER REFERENCES player(id),
  order_no      INTEGER NOT NULL,
  player_status CHAR(10) REFERENCES player_status_values (player_status),
  PRIMARY KEY (game, player, order_no)
);