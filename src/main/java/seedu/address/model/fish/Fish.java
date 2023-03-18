package seedu.address.model.fish;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.tank.Tank;

/**
 * Represents a Fish in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Fish {

    // Identity fields
    private final Name name;
    private final Species species;

    // Data fields
    private final LastFedDate lastFedDate;
    private final FeedingInterval feedingInterval;
    private final Set<Tag> tags = new HashSet<>();

    private Tank tank;

    /**
     * Every field must be present and not null.
     */
    public Fish(Name name, LastFedDate lastFedDate, Species species, FeedingInterval feedingInterval, Tank tank,
                Set<Tag> tags) {
        requireAllNonNull(name, lastFedDate, species, feedingInterval, tags);
        this.name = name;
        this.lastFedDate = lastFedDate;
        this.species = species;
        this.feedingInterval = feedingInterval;
        this.tank = tank;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public LastFedDate getLastFedDate() {
        return lastFedDate;
    }

    public Species getSpecies() {
        return species;
    }

    public FeedingInterval getFeedingInterval() {
        return feedingInterval;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank t) {
        tank = t;
    }

    public boolean isInTank(Tank t) {
        return tank == t;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both Fishes have the same name.
     * This defines a weaker notion of equality between two Fishes.
     */
    public boolean isSameFish(Fish otherFish) {
        if (otherFish == this) {
            return true;
        }

        return otherFish != null
                && otherFish.getName().equals(getName());
    }

    /**
     * Returns true if both Fishes have the same identity and data fields.
     * This defines a stronger notion of equality between two Fishes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Fish)) {
            return false;
        }

        Fish otherFish = (Fish) other;
        return otherFish.getName().equals(getName())
                && otherFish.getLastFedDate().equals(getLastFedDate())
                && otherFish.getSpecies().equals(getSpecies())
                && otherFish.getFeedingInterval().equals(getFeedingInterval())
                && otherFish.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, lastFedDate, species, feedingInterval, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Last Fed Date: ")
                .append(getLastFedDate())
                .append("; Species: ")
                .append(getSpecies())
                .append("; FeedingInterval: ")
                .append(getFeedingInterval())
                .append("; Tank: ")
                .append(getTank());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
