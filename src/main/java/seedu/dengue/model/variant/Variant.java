package seedu.dengue.model.variant;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;

/**
 * Represents a Variant in the Dengue Hotspot Tracker.
 * Guarantees: immutable; name is valid as declared in {@link #isValidVariantName(String)}
 */
public class Variant {

    public static final String MESSAGE_CONSTRAINTS = "Variants should be one of the four dengue variants: "
            + "DENV1, DENV2, DENV3, or DENV4.";
    public final DengueVariant variantName;

    /**
     * Constructs a {@code Variant}.
     *
     * @param variantName A valid dengue variant name.
     */
    public Variant(String variantName) {
        requireNonNull(variantName);
        checkArgument(isValidVariantName(variantName), MESSAGE_CONSTRAINTS);
        this.variantName = DengueVariant.valueOf(variantName.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid dengue variant name.
     */
    public static boolean isValidVariantName(String test) {
        try {
            DengueVariant.valueOf(test.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Variant // instanceof handles nulls
                && variantName.equals(((Variant) other).variantName)); // state check
    }

    @Override
    public int hashCode() {
        return variantName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return variantName.toString();
    }

}
