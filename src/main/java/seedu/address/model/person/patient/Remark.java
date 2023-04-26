package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents any remarks for a person in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remark should not be blank";
    /*
     * Remark should not be blank
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";
    private final String remark;

    /**
     * Constructs a {@code Remarks}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        this.remark = remark;
    }

    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
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

    public String getValue() {
        return remark;
    }
}
