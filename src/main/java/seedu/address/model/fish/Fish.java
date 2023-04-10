package seedu.address.model.fish;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.date.DateUtil;
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
    private LastFedDateTime lastFedDateTime;
    private final FeedingInterval feedingInterval;
    private final Set<Tag> tags = new HashSet<>();

    private Tank tank;

    /**
     * Every field must be present and not null.
     */
    public Fish(Name name, LastFedDateTime lastFedDateTime, Species species, FeedingInterval feedingInterval, Tank tank,
                Set<Tag> tags) {
        requireAllNonNull(name, lastFedDateTime, species, feedingInterval, tags);
        this.name = name;
        this.lastFedDateTime = lastFedDateTime;
        this.species = species;
        this.feedingInterval = feedingInterval;
        this.tank = tank;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public LastFedDateTime getLastFedDateTime() {
        return lastFedDateTime;
    }

    public void setLastFedDateTime(String dateTime) {
        LastFedDateTime newLastFedDateTime = new LastFedDateTime(dateTime);
        this.lastFedDateTime = newLastFedDateTime;
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
     * Returns true if Fish needs to be fed
     * @return true if Fish needs to be fed
     */
    public boolean needsToBeFed() {
        return DateUtil.checkFishNeedsToBeFed(lastFedDateTime, feedingInterval);
    }

    /**
     * Returns formatted string of the last time this fish was fed on
     * @return formatted string of the last time this fish was fed on
     */
    public String getLastFedString() {
        String ret = this.getName() + " last fed on " + lastFedDateTime.getAlphaNumericDateTime();
        return ret;
    }

    public String getFishImage() {
        return this.species.getFishImageBySpecies();
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
                && otherFish.getLastFedDateTime().equals(getLastFedDateTime())
                && otherFish.getSpecies().equals(getSpecies())
                && otherFish.getFeedingInterval().equals(getFeedingInterval())
                && otherFish.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, lastFedDateTime, species, feedingInterval, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Last Fed Date Time: ")
                .append(getLastFedDateTime())
                .append("; Species: ")
                .append(getSpecies())
                .append("; Feeding Interval: ")
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
