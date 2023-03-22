package arb.model.project;

import static arb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Project's price in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price implements Comparable<Price> {

    public static final String MESSAGE_CONSTRAINTS =
            "Price must be in a recognisable format, e.g. 4,098.09 or 4098.09 or 380";

    public static final String VALIDATION_REGEX = "(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$";
    private double price;

    private String fullPrice;

    /**
     * Constructs a {@code Price}.
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        fullPrice = price;
    }

    /**
     * Returns true if a given string is a valid price.
     * @param test String to test.
     * @return True if valid.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return "Price: $" + fullPrice;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price) // handles null
                && fullPrice.equals(((Price) other).fullPrice); // check price
    }

    @Override
    public int compareTo(Price otherPrice) {
        return this.fullPrice.compareTo(otherPrice.fullPrice);
    }

    @Override
    public int hashCode() {
        return fullPrice.hashCode();
    }
}
