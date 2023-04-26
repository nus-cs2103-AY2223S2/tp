package seedu.medinfo.model.ward;

/**
 * Represents the capacity and occupancy of a ward.
 */
public class Capacity {
    private static final int MIN_CAPACITY = 1;
    private static final int MAX_CAPACITY = 1000;

    public static final String MESSAGE_CONSTRAINTS = "Capacity should be a"
            + " positive integer (at least " + Integer.valueOf(MIN_CAPACITY) + " and less than or equal to "
            + Integer.valueOf(MAX_CAPACITY) + ")"
            + " and it should not be blank";

    private int value;

    /**
     * Constructs a {@code Capacity} with the given capacity.
     *
     * @param capacity Integer value of the capacity.
     */
    public Capacity(int capacity) {
        value = capacity;
    }

    /**
     * Constructs a {@code Capacity} with the given capacity.
     *
     * @param capacity String value of the capacity.
     */
    public Capacity(String capacity) {
        value = Integer.parseInt(capacity);
    }

    /**
     * Sets the Capacity value.
     *
     * @param capacity Integer value to be set.
     */
    public void setCapacity(int capacity) {
        value = capacity;
    }

    /**
     * Returns true if a given string is a valid capacity.
     *
     * @param test String capacity value to be tested.
     * @return If the given string is a valid capacity.
     */
    public static boolean isValidCapacity(String test) {
        try {
            int value = Integer.parseInt(test);
            if (value < MIN_CAPACITY || value > MAX_CAPACITY) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the Integer value of {@code this}.
     *
     * @return
     */
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
