import java.io.IOException;
public class WrongInputException extends IOException {
    private final String message;
    public WrongInputException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
