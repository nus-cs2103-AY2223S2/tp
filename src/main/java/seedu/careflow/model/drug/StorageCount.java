package seedu.careflow.model.drug;
import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.AppUtil.checkArgument;

/**
 * Represents a drug's storage count in the drug inventory
 */
public class StorageCount {
    public static final String MESSAGE_CONSTRAINTS =
            "Storage Count should only contain positive integers, "
                    + "it should be at least 1 digit long but no more than 3 digits and be less than 500";
    public static final String VALIDATION_REGEX = "[1-4]?[0-9]?[0-9]";

    private static final int THRESHOLD_VALUE = 10;
    private static final int UPPER_LIMIT = 499;
    private Integer count;

    /**
     * Constructs a {@code StorageCount}.
     *
     * @param count A valid storage count.
     */
    public StorageCount(String count) {
        requireNonNull(count);
        checkArgument(isValidStorageCount(count), MESSAGE_CONSTRAINTS);
        this.count = Integer.parseInt(count);
    }

    /**
     * Returns true if a given string is a valid storage count.
     */
    public static boolean isValidStorageCount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Decreases storage count by {@code amount}
     * @param amount the decrease value
     */
    public void decrStorage(Integer amount) {
        if (amount > count) {
            count = 0;
        } else {
            count -= amount;
        }
    }
    /**
     * Increases storage count by {@code amount}
     * @param amount the increase value
     */
    public void incStorage(Integer amount) {
        if (isAboveUpperLimit(amount)) {
            count = UPPER_LIMIT;
        } else {
            count += amount;
        }
    }

    /**
     * Checks whether the current storage count is below a set threshold value
     */
    public boolean isBelowThreshold() {
        return getCount() < THRESHOLD_VALUE;
    }

    public boolean isAboveUpperLimit(int increment) {
        return increment + getCount() > UPPER_LIMIT;
    }

    @Override
    public String toString() {
        return count.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StorageCount // instanceof handles nulls
                && count.equals(((StorageCount) other).count)); // state check
    }

    public Integer getCount() {
        return this.count;
    }

    @Override
    public int hashCode() {
        return count.hashCode();
    }
}
