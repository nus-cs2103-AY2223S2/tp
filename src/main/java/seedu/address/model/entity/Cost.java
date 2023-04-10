package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;

/**
 * Represents an item's costs in Reroll.
 * Guaranteed to be an integer.
 */
public class Cost {
    public static final String CONSTRAINTS = "Cost should be an integer!";

    private static final int BASE_GOLD_COST = 0;

    private final Integer goldCost;

    /**
     * Constructs a {@code goldCost}
     *
     * @param goldCost given goldCost
     */
    public Cost(Integer goldCost) {
        requireNonNull(goldCost);
        this.goldCost = goldCost;
    }

    public Cost() {
        this(BASE_GOLD_COST);
    }

    public int getGoldCost() {
        return this.goldCost;
    }

    @Override
    public String toString() {
        return this.goldCost + "g";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cost // instanceof handles nulls
                && goldCost.equals(((Cost) other).goldCost)); // state check
    }
}
