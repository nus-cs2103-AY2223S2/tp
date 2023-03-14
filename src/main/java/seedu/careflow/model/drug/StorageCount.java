package seedu.careflow.model.drug;
import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.AppUtil.checkArgument;

/**
 * Represents a drug's storage count in the drug inventory
 */
public class StorageCount {
    public static final String MESSAGE_CONSTRAINTS =
            "Storage Count should only contain positive integers,"
                    + "it should be at least 1 digit long but no more than 7 digits";
    public static final String VALIDATION_REGEX = "[\\d][\\d]{0,6}";

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
        count += amount;
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
