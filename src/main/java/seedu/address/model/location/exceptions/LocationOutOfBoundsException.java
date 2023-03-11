package seedu.address.model.location.exceptions;

public class LocationOutOfBoundsException extends RuntimeException {
    public LocationOutOfBoundsException() {
        super("Location lies outside the bounds of Singapore.");
    }
}
