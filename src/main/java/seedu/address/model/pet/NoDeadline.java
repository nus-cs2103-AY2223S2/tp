package seedu.address.model.pet;


public class NoDeadline extends Deadline {
    public NoDeadline() {
        super("N/A", null);
    }

    @Override
    public String toString() {
        return description;
    }
}
