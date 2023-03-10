package seedu.address.model.tank;

import java.util.Objects;

import seedu.address.model.AddressBook;

/**
 *  Represents a Tank of the user that contains fish
 */
public class Tank {
    private final TankName name;
    private final AddressBook fishList;

    public Tank(TankName tankName, AddressBook fishList) {
        this.name = tankName;
        this.fishList = fishList;
    }

    public AddressBook getFishList() {
        return this.fishList;
    }

    public TankName getTankName() {
        return this.name;
    }

    /**
     * Returns true if both Tanks have the same tank name.
     * This defines a weaker notion of equality between two Tanks.
     */
    public boolean isSameTank(Tank otherTank) {
        if (otherTank == this) {
            return true;
        }

        return otherTank != null
                && otherTank.getTankName().equals(getTankName());
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.address.model.task.Task)) {
            return false;
        }

        seedu.address.model.task.Task otherTask = (seedu.address.model.task.Task) other;
        return otherTask.getDescription().equals(name);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTankName());

        return builder.toString();
    }
}

