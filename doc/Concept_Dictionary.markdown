StoryGame --- concept dictionary
================================


GameInitiator := Player who initiates the game

Game := @GameInitiator + GameStatus + (GameTitle) + (GameDescription) + PlayingOrder +
       (EndingCriteria) + StoryList + @GameInitiationTime +
       GameStartTime + (GameEndTime)
GameStatus:= [Initiating * GameInitiator is writing invitation and selecting Players
    | Inviting * Invitations are being sent and Answers awaited.
    | Playing * All Invitations has been accepted or
          * GameInitiator forcibly has started the game
    | Ending * Ending criteria has been fullfilled, but incomplete parts
    are being written still.
    | Viewing * ]

GameInitiationTime:= * moment when GameInitiator sends has submitted
        the initiation information

PlayersGame := PlayingOrderNumber + PlayerStatus + /PlayersLink

EndingCriteria := (NumberOfTurns) + (PlayingTimeMax)

EMailAddress :=

GameTitle := 1{M}80 * Title attracting potential players
GameDescription := 1{M}160 * some further narrative to invitation message

InvitationMessage := (GameTitle)+ (GameDescription) + /PlayersLink

PlayersLink := * URL for this user to access the game.
     * Includes authorization for users's email address
     * potentially may be sufficient for authentication (JWTj?)

NumberOfTurns := * Number of parts each player writes to each story.
        * Thereafter user will not get new turn for that story
        * After no stories are left, user move to aftermath- status

PlayingTime : After that time has elapsed from GameStartingTime no
       * Storystatus moves to Reading and


PlayerStatus:= [Invited * invitation message sent
|Joining * invitation link followed, nickname not confirmed/skipped
|Waiting * Nickname view passed, game not yet started
|Playing * Game started
|Aftermath * Writing completed for all stories, reading only]

PlayersLink := * personal URL link for the player to access the game.
        * accessible view depends on game and player status

Player := EMailAddress + (NickName)

NickName:= * If player has not provided nickname, other players
      see his/her eMailAddress for identification

Story:= 1{Part}N +
        CurrentPlayer +
        StoryStatus
       /LastLine +
       /LastStoryPart

StoryStatus:= [Waiting| Writing | Reading]
Line := 1{M}MAX_LINE_LENGTH

Part:= 3{Line}3 +
       Writer + * Player who wrote this part
        /Completed * completed after all 3 lines are provided +
        /LastLine

LastLine

NextStory

StoryList :=  1{Story}NumberOfPlayers



CompletedStory:= * Story after either:
                  1) ending criteria fullfilled
                  2) GameInitiator set the game to Ending GameStatus
                 AND
                  all the Stories change to Waiting status

MAX_LINE_LENGTH = 80 ??