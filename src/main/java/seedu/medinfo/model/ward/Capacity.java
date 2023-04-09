package seedu.medinfo.model.ward;

/**
 * Represents the capacity and occupancy of a ward.
 */
public class Capacity {
    private static final int MIN_CAPACITY = 1;
    private static final int MAX_CAPACITY = 1000;

    public static final String MESSAGE_CONSTRAINTS = "Capacity should be a"
            + " positive integer (at least " + Integer.valueOf(MIN_CAPACITY) + " and less than "
            + Integer.valueOf(MAX_CAPACITY) + ")"
            + " and it should not be blank";

    private int value;

    public Capacity(int capacity) {
        value = capacity;
    }

    public Capacity(String capacity) {
        value = Integer.parseInt(capacity);
    }

    public void setCapacity(int capacity) {
        value = capacity;
    }

    /**
     * Returns true if a given string is a valid capacity.
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

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
