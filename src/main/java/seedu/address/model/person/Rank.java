package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Represents a Person's rank in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRank(String)}
 */
public class Rank {

    public static final String MESSAGE_CONSTRAINTS =
            "Rank should not be blank and can only be from the following list:\nREC, PTE, CPL, CFC"
                    + "\n3SG, 2SG, 1SG, SSG, MSG, 3WO\n2LT, LTA, CPT, MAJ\nCIV";

    public static final HashSet<String> VALID_RANKS = new HashSet<>(Arrays.asList("REC", "PTE", "LCP", "CPL", "CFC",
            "3SG", "2SG", "1SG", "SSG", "MSG", "3WO", "2LT", "LTA", "CPT", "MAJ", "CIV"));

    public final String value;

    /**
     * Constructs a {@code Rank}.
     *
     * @param rank A valid rank.
     */
    public Rank(String rank) {
        requireNonNull(rank);
        String upperCaseRank = rank.toUpperCase();
        checkArgument(isValidRank(upperCaseRank), MESSAGE_CONSTRAINTS);
        value = upperCaseRank;
    }

    /**
     * Returns true if a given string is a valid rank.
     */
    public static boolean isValidRank(String test) {
        requireNonNull(test);
        return VALID_RANKS.contains(test.toUpperCase());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rank // instanceof handles nulls
                && value.equals(((Rank) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
