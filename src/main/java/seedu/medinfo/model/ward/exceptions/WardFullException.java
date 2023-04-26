package seedu.medinfo.model.ward.exceptions;

/**
 * Signals that the ward is full.
 */
public class WardFullException extends RuntimeException {
    private String name;

    /**
     * Constructs a new {@code WardFullException} with the specified ward name {@code name}.
     */
    public WardFullException(String name) {
        super(name + " is full!");
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " is full!";
    }
}
