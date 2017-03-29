StoryGame --- concept dictionary
================================


GameInitiator := Player who initiates the game

Game := GameStatus + (GameTitle) + (GameDescription) + PlayingOrder +
       (EndingCriteria) + StoryList + GameStartTime + (GameEndTime)
GameStatus: [Initiating | Inviting | Playing | Ending | Viewing]

PlayingOrder := 1{Player}NumberOfPlayers

EndingCriteria := (NumberOfTurns) + (PlayingTimeMax)

EMailAddress

GameTitle := 1{M}80 * Title attracting potential players
GameDescription := 1{M}160 * some further narrative to invitation message

InvitationMessage := (GameTitle)+ (GameDescription) + InvitationLink

NumberOfTurns := Number of parts each player writes to each story.

PlayingTime

GameInformation = GAmeTitle

InvitationLink := * personal URL link for the player to access the game.

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
       Player + * who wrote this part
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