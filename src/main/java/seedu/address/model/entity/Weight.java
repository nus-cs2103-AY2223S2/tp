package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;

/**
 * Represents an item's weight in Reroll.
 * Guaranteed to be a double.
 */
public class Weight {
    public static final String CONSTRAINTS = "Weight should be an integer!";

    private static final double BASE_WEIGHT = 0.5;

    private final Double weight;

    /**
     * Constructs a {@code weight}
     *
     * @param weight given weight
     */
    public Weight(Double weight) {
        requireNonNull(weight);
        this.weight = weight;
    }

    public Weight() {
        this(BASE_WEIGHT);
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return String.valueOf(this.weight);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && weight.equals(((Weight) other).weight)); // state check
    }


}
