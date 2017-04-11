



Game(@GameInitiator -->PLAYER(@Email),
     @GameInitiationTime,
     GameDesription, GameStartTime, GameEndTime)

Story((@GameInitiator, @GameInitiationTime)-->GAME, @StoryOrderNumber,
   StoryStatus, (@Email)-CurrentPlayer->PLAYER)

Part((@GameInitiator, @GameInitiationTime, @StoryOrderNumber)-->STORY,
      @PartOrderNumber,
    (@Email)-Writer->Player,
    Line1, Line2, Line3)

PlayersGame((@GameInitiator, @GameInitiationTime)-->GAME,
            (@Email)-->PLAYER,
            OrderNo, PlayerStatus, PlayersLink)