package seedu.careflow.model.drug;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.AppUtil.checkArgument;

/**
 * Represents a drug's trade name in the drug inventory
 */
public class TradeName {
    public static final String MESSAGE_CONSTRAINTS =
            "Trade names should only contain alphanumeric characters and spaces, "
                    + "it should not be blank and less than 50 characters";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]{0,49}";

    public final String tradeName;

    /**
     * Constructs a {@code TradeName}.
     *
     * @param tradeName A valid trade name.
     */
    public TradeName(String tradeName) {
        requireNonNull(tradeName);
        checkArgument(isValidTradeName(tradeName), MESSAGE_CONSTRAINTS);
        this.tradeName = tradeName;
    }

    /**
     * Returns true if a given string is a valid trade name.
     */
    public static boolean isValidTradeName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isBlank() {
        return this.tradeName.isBlank();
    }


    @Override
    public String toString() {
        return this.tradeName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TradeName // instanceof handles nulls
                && tradeName.equals(((TradeName) other).tradeName)); // state check
    }

    @Override
    public int hashCode() {
        return tradeName.hashCode();
    }
}
