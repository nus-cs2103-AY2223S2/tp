package seedu.address.model.ward;

/**
 * Represents the capacity and occupancy of a ward.
 */
public class Capacity {
    private int maxCapacity;
    private int occupancy = 0;

    public Capacity(int capacity) {
        this.maxCapacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.maxCapacity = capacity;
    }

    public boolean isFull() {
        return occupancy >= maxCapacity;
    }
    @Override
    public String toString() {
        return "Current occupancy rate: " + occupancy + "/" + maxCapacity;
    }
}
