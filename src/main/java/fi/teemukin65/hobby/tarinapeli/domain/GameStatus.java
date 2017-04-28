package fi.teemukin65.hobby.tarinapeli.domain;

/**
 * Created by teemu on 27.4.2017.
 */
public enum GameStatus {
    // * GameInitiator is writing invitation and selecting Players
    INITIATING,
    // * Invitations are being sent and Answers awaited.
    INVITING,
    // * All Invitations has been accepted or
    //  GameInitiator forcibly has started the game
    PLAYING,
    // * Ending criteria has been fullfilled, but incomplete parts
    //   are being written still.
    ENDING,
    // * ]
    VIEWING
}
