package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's income in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Income {
    public static final String MESSAGE_CONSTRAINTS = "Incomes can take any integer values, and it should not be blank";

    public final Double income;

    /**
     * Constructs an {@code Address}.
     *
     * @param income A valid income.
     */
    public Income(String income) {
        requireNonNull(income);
        checkArgument(isValidIncome(income), MESSAGE_CONSTRAINTS);
        this.income = Double.parseDouble(income);
    }

    /**
     * Returns true if a given string is a valid income.
     */
    public static boolean isValidIncome(String test) {
        try {
            Double income = Double.parseDouble(test);
            return income >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.income.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Income // instanceof handles nulls
                && income.equals(((Income) other).income)); // state check
    }

    @Override
    public int hashCode() {
        return income.hashCode();
    }
}
