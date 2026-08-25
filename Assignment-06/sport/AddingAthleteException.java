package sport;

public class AddingAthleteException extends Exception {

    public AddingAthleteException() {
        super("Error while adding athlete!");
    }

    public AddingAthleteException(String message) {
        super(message);
    }
}