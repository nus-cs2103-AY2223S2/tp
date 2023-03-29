package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.Fish;
import seedu.address.model.fish.LastFedDateTime;
import seedu.address.model.fish.Name;
import seedu.address.model.fish.Species;
import seedu.address.model.tag.Tag;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Fish objects.
 */
public class FishBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_LAST_FED_DATE = "01/01/2000 00:00";
    public static final String DEFAULT_SPECIES = "Guppy";
    public static final String DEFAULT_FEEDING_INTERVAL = "0d15h";
    public static final String DEFAULT_TANK = "Saltwater Tank 1";

    private Name name;
    private LastFedDateTime lastFedDate;
    private Species species;
    private FeedingInterval feedingInterval;
    private Tank tank;
    private Set<Tag> tags;

    /**
     * Creates a {@code FishBuilder} with the default details.
     */
    public FishBuilder() {
        name = new Name(DEFAULT_NAME);
        lastFedDate = new LastFedDateTime(DEFAULT_LAST_FED_DATE);
        species = new Species(DEFAULT_SPECIES);
        feedingInterval = new FeedingInterval(DEFAULT_FEEDING_INTERVAL);
        tank = new Tank(new TankName(DEFAULT_TANK), new AddressBook(), new UniqueIndividualReadingLevels());
        tags = new HashSet<>();
    }

    /**
     * Initializes the FishBuilder with the data of {@code fishToCopy}.
     */
    public FishBuilder(Fish fishToCopy) {
        name = fishToCopy.getName();
        lastFedDate = fishToCopy.getLastFedDateTime();
        species = fishToCopy.getSpecies();
        feedingInterval = fishToCopy.getFeedingInterval();
        tank = fishToCopy.getTank();
        tags = new HashSet<>(fishToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Fish} that we are building.
     */
    public FishBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Fish} that we are building.
     */
    public FishBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code FeedingInterval} of the {@code Fish} that we are building.
     */
    public FishBuilder withFeedingInterval(String feedingInterval) {
        this.feedingInterval = new FeedingInterval(feedingInterval);
        return this;
    }

    /**
     * Sets the {@code LastFedDate} of the {@code Fish} that we are building.
     */
    public FishBuilder withLastFedDate(String lastFedDate) {
        this.lastFedDate = new LastFedDateTime(lastFedDate);
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code Fish} that we are building.
     * @param species Species
     */
    public FishBuilder withSpecies(String species) {
        this.species = new Species(species);
        return this;
    }

    /**
     * Sets the {@code Tank} of the {@code Fish} that we are building.
     * @param tank tank
     * @return fishbuilder
     */
    public FishBuilder withTank(String tank) {
        this.tank = new Tank(new TankName(tank), new AddressBook(), new UniqueIndividualReadingLevels());
        return this;
    }

    public Fish build() {
        return new Fish(name, lastFedDate, species, feedingInterval, tank, tags);
    }

}
