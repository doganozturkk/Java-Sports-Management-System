package sport;

public class AddingMatchException extends Exception {

    public AddingMatchException() {
        super("Error while adding match!");
    }

    public AddingMatchException(String message) {
        super(message);
    }
}