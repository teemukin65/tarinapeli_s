
CREATE TABLE players_game (
  game INTEGER REFERENCES game(game_id),
  player INTEGER REFERENCES player(id),
  order_no INTEGER NOT NULL,
  player_status VARCHAR( 14),
  PRIMARY KEY (game,player, order_no)
);