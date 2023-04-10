package arb.model.project;

import static arb.commons.util.AppUtil.checkArgument;
import static java.lang.Double.compare;
import static java.util.Objects.requireNonNull;

import java.text.DecimalFormat;

/**
 * Represents a Project's price in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price implements Comparable<Price> {


    public static final String MESSAGE_CONSTRAINTS =
            "Price must be in a recognisable format, e.g. 4098.09 or 380 or 0.35";

    public static final String VALIDATION_REGEX = "(([1-9]\\d{0,2})|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$";
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private double fullPrice;


    /**
     * Constructs a {@code Price}.
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        this.fullPrice = Double.parseDouble(price);
    }

    /**
     * Returns true if a given string is a valid price.
     * @param test String to test.
     * @return True if valid.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getPrice() {
        return df.format(this.fullPrice);
    }
    @Override
    public String toString() {
        return "Price: $" + getPrice();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price) // handles null
                && fullPrice == (((Price) other).fullPrice); // check price
    }

    @Override
    public int compareTo(Price otherPrice) {
        return compare(this.fullPrice, otherPrice.fullPrice);
    }

    @Override
    public int hashCode() {
        return df.format(fullPrice).hashCode();
    }
}
