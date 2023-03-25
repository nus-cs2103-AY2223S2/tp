package seedu.address.model.ward;

/**
 * Represents the capacity and occupancy of a ward.
 */
public class Capacity {

    public static final String MESSAGE_CONSTRAINTS = "Capacity should be a valid integer"
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
            Integer.parseInt(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getValue() {
        return value;
    }

}
