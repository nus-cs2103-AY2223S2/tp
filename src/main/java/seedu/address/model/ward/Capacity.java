package seedu.address.model.ward;

/**
 * Represents the capacity and occupancy of a ward.
 */
public class Capacity {
    private int value;

    public Capacity(int capacity) {
        value = capacity;
    }

    public void setCapacity(int capacity) {
        value = capacity;
    }

    public int getValue() {
        return value;
    }

}
