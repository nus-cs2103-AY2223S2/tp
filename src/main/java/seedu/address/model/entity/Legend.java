package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;

/**
 * Represents a mob's challenge rating in Reroll.
 * Guaranteed to be a float.
 */
public class Legend {
    public static final String CONSTRAINTS = "Legendary status should be a boolean!";

    private static final boolean BASE_STATUS = false;

    private final Boolean status;

    /**
     * Constructs a {@code Legend}
     *
     * @param status given legendary status
     */
    public Legend(Boolean status) {
        requireNonNull(status);
        this.status = status;
    }

    public Legend() {
        this(BASE_STATUS);
    }

    public Boolean getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return String.valueOf(this.status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Legend // instanceof handles nulls
                && status.equals(((Legend) other).status)); // state check
    }
}
