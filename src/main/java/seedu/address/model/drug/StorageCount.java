package seedu.address.model.drug;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a drug's storage count in the drug inventory
 */
public class StorageCount {
    public static final String MESSAGE_CONSTRAINTS =
            "Storage Count should only contain numbers, and it should be at least 1 digit long";
    public static final String VALIDATION_REGEX = "[\\d][\\d]*";

    public final String count;

    /**
     * Constructs a {@code StorageCount}.
     *
     * @param count A valid storage count.
     */
    public StorageCount(String count) {
        requireNonNull(count);
        checkArgument(isValidStorageCount(count), MESSAGE_CONSTRAINTS);
        this.count = count;
    }

    /**
     * Returns true if a given string is a valid storage count.
     */
    public static boolean isValidStorageCount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return count;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StorageCount // instanceof handles nulls
                && count.equals(((StorageCount) other).count)); // state check
    }

    @Override
    public int hashCode() {
        return count.hashCode();
    }
}
