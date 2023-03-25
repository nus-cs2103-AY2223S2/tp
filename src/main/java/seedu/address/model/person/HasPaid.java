package seedu.address.model.person;

/**
 * The HasPaid class represents a boolean value indicating whether a person has paid or not.
 */
public class HasPaid {
    private boolean hasPaid;

    /**
     * Creates a new HasPaid object with a default value of false.
     */
    public HasPaid() {
        this.hasPaid = false;
    }

    /**
     * Creates a new HasPaid object with the specified value.
     * @param hasPaid the value to set for the HasPaid object
     */
    public HasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    /**
     * Marks the HasPaid object as present by setting its value to true.
     */
    public void markPaid() {
        this.hasPaid = true;
    }

    /**
     * Marks the HasPaid object as absent by setting its value to false.
     */
    public void markUnpaid() {
        this.hasPaid = false;
    }

    /**
     * Returns a string representation of the HasPaid object.
     * @return a string representation of the HasPaid object, with [X] if the object is marked present, [ ] otherwise
     */
    @Override
    public String toString() {
        if (hasPaid) {
            return "[has paid]";
        } else {
            return "[not yet paid]";
        }
    }

    /**
     * returns the boolean
     * @return
     */
    public boolean getBool() {
        return hasPaid;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof HasPaid)) {
            return false;
        }
        return ((HasPaid) other).hasPaid == hasPaid;
    }
}

