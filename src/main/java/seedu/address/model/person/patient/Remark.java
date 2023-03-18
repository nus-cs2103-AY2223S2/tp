package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;

/**
 * Represents any remarks for a person in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Add any remarks for the patient.";

    public final String remark;

    /**
     * Constructs a {@code Remarks}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        this.remark = remark;
    }

    public static boolean isValidRemark(String remark) {
        return true;
    }

    @Override
    public String toString() {
        return remark;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && remark.equals(((Remark) other).remark)); // state check
    }

    @Override
    public int hashCode() {
        return remark.hashCode();
    }
}
