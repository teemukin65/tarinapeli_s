package fi.teemukin65.hobby.tarinapeli;

public class EmailExistException extends Exception {
    public EmailExistException(String reason) {
        super(reason);
    }
}
