package seedu.address.model.tank;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.AddressBook;
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;

/**
 *  Represents a Tank of the user that contains fish.
 */
public class Tank {
    private TankName name;
    private final AddressBook fishList;
    private UniqueIndividualReadingLevels readingLevels;

    /**
     * Creates a Tank.
     * @param tankName Name of tank.
     * @param fishList Fish in tank.
     * @param readingLevels
     */
    public Tank(TankName tankName, AddressBook fishList, UniqueIndividualReadingLevels readingLevels) {
        this.name = tankName;
        this.fishList = fishList;
        this.readingLevels = readingLevels;
    }

    public AddressBook getFishList() {
        return this.fishList;
    }

    public TankName getTankName() {
        return this.name;
    }

    public void setTankName(TankName newName) {
        this.name = newName;
    }

    public UniqueIndividualReadingLevels getReadingLevels() {
        return this.readingLevels;
    }

    public void setIndividualReadingLeves(UniqueIndividualReadingLevels readings) {
        this.readingLevels = readings;
    }
    /**
     * Returns true if this tank has a fish fue to be feed today
     * @return true if this tank has a fish fue to be feed today
     */
    public boolean hasUnfedFish() {
        for (Fish f : fishList.getFishList()) {
            if (f.needsToBeFed()) {
                return true;
            }
        }
        return false;
    }

    public String getUnfedFishesString() {
        String ret = "";
        for (Fish f : fishList.getFishList()) {
            if (f.needsToBeFed()) {
                ret += f.getLastFedString();
                ret += "\n";
            }
        }
        return ret;
    }

    /**
     * Adds a fish to the TankList.
     * @param fish fish to be added.
     */
    public void addFish(Fish fish) {
        fishList.addFish(fish);
    }

    /**
     * Sets the lastFedDate field of all fishes in this Tank's {@code fishList} list
     * with new LastFedDate object with {@code newDateTime}.
     */
    public void setLastFedDateTimeFishes(String newDateTime) {
        requireNonNull(newDateTime);

        fishList.setLastFedDateTimeFishes(newDateTime);
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

