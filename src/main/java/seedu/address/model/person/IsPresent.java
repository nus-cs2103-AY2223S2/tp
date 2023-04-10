package seedu.address.model.person;

/**
 * Represents the attendance status of a person, whether present or absent.
 */
public class IsPresent {

    /**
     * Indicates whether the person is present or not.
     */
    private boolean isPresent;

    /**
     * Constructs a default isPresent object with false as the initial attendance status.
     */
    public IsPresent() {
        this.isPresent = false;
    }

    /**
     * Constructs an isPresent object with the specified attendance status.
     *
     * @param isPresent The attendance status to set.
     */
    public IsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    /**
     * Marks the person as present.
     */
    public void markPresent() {
        this.isPresent = true;
    }

    /**
     * Marks the person as absent.
     */
    public void markAbsent() {
        this.isPresent = false;
    }

    /**
     * Returns a string representation of the attendance status.
     *
     * @return A string representation of the attendance status.
     */
    @Override
    public String toString() {
        if (isPresent) {
            return "[present]";
        } else {
            return "[absent]";
        }
    }

    /**
     * returns the boolean
     * @return
     */
    public boolean getBool() {
        return isPresent;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof IsPresent)) {
            return false;
        }
        return ((IsPresent) other).isPresent == isPresent;
    }
}

