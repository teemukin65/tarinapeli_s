StoryGame --- concept dictionary
================================


GameInitiator := Player who initiates the game

Game := GameStatus + (GameTitle) + (GameDescription) + PlayingOrder +
       (EndingCriteria) + StoryList + GameStartTime + (GameEndTime)
GameStatus: [Initiating | Inviting | Playing | Ending | Viewing]

PlayersGame := PlayingOrderNumber + PlayerStatus + PlayersLink

EndingCriteria := (NumberOfTurns) + (PlayingTimeMax)

EMailAddress

GameTitle := 1{M}80 * Title attracting potential players
GameDescription := 1{M}160 * some further narrative to invitation message

InvitationMessage := (GameTitle)+ (GameDescription) + PlayersLink

NumberOfTurns := Number of parts each player writes to each story.

PlayingTime


PlayerStatus:= [Invited * invitation message sent
|Joining * invitation link followed, nickname not confirmed/skipped
|Waiting * Nickname view passed, game not yet started
|Playing * Game started
|Aftermath * Writing completed for all stories, reading only]

PlayersLink := * personal URL link for the player to access the game.

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

CompletedPart := * Part

LastLine

NextStory

SeeStory

StoryList :=  1{Story}NumberOfPlayers



CompletedStory:= * Story after either:
                  1) ending criteria fullfilled
                  2) GameInitiator set the game to Ending GameStatus
                 AND
                  all the Stories change to Waiting status

MAX_LINE_LENGTH = 80 ??