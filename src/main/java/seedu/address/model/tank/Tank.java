package seedu.address.model.tank;

import java.util.Objects;

/**
 *  Represents a Tank of the user
 */
public class Tank {
    private final TankName name;

    public Tank(TankName tankName) {
        this.name = tankName;
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

        if (!(other instanceof seedu.address.model.tank.Tank)) {
            return false;
        }

        seedu.address.model.tank.Tank otherTank = (seedu.address.model.tank.Tank) other;
        return otherTank.getTankName().equals(name);
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

