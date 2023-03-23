package seedu.address.model.ward;

public class Capacity {
    public int maxCapacity;
    public int occupancy = 0;

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
