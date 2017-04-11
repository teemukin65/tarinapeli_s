



Game(@GameInitiator, @GameInitiationTime, GameDesription, GameStartTime, GameEndTime)

Story(@Game-->(@GameInitiator, @GameInitiationTime), @StoryOrderNumber,
   StoryStatus, CurrentPlayer->(@Email))

Part(@Story-->(@Game, @StoryOrderNumber), @PartOrderNumber, Line1, Line2, Line3
    Writer->Player