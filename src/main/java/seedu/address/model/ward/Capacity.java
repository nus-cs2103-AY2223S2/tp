package seedu.address.model.ward;

public class Capacity {
    public int capacity;
    public int occupancy = 0;

    public Capacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return occupancy >= capacity;
    }
    @Override
    public String toString() {
        return "Current occupancy rate: " + occupancy + "/" + capacity;
    }
}
