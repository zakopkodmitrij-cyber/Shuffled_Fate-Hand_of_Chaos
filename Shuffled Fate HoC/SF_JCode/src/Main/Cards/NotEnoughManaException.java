package Main.Cards;

public class NotEnoughManaException extends RuntimeException {
    public NotEnoughManaException() {
        super();
    }

    public NotEnoughManaException(String message) {
        super(message);
    }
}
