package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;

/**
 * Represents a mob's challenge rating in Reroll.
 * Guaranteed to be a float.
 */
public class ChallengeRating {
    public static final String CONSTRAINTS = "Challenge rating should be a number!";

    private static final double BASE_RATING = 0.0;

    private final Double rating;

    /**
     * Constructs a {@code ChallengeRating}
     *
     * @param rating given challenge rating
     */
    public ChallengeRating(Double rating) {
        requireNonNull(rating);
        this.rating = rating;
    }

    public ChallengeRating() {
        this(BASE_RATING);
    }

    public double getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return String.valueOf(this.rating);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ChallengeRating // instanceof handles nulls
                && rating.equals(((ChallengeRating) other).rating)); // state check
    }
}
