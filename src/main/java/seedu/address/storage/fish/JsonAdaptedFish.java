package seedu.address.storage.fish;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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


/**
 * Jackson-friendly version of {@link Fish}.
 */
public class JsonAdaptedFish {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Fish's %s field is missing!";

    private final String name;
    private final String lastFedDateTime;
    private final String species;
    private final String feedingInterval;
    private final String tank;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedFish} with the given fish details.
     */
    @JsonCreator
    public JsonAdaptedFish(@JsonProperty("name") String name, @JsonProperty("lastFedDateTime") String lastFedDateTime,
                           @JsonProperty("species") String species,
                           @JsonProperty("feedingInterval") String feedingInterval,
                           @JsonProperty("tank")String tank, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.lastFedDateTime = lastFedDateTime;
        this.species = species;
        this.feedingInterval = feedingInterval;
        this.tank = tank;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Fish} into this class for Jackson use.
     */
    public JsonAdaptedFish(Fish source) {
        name = source.getName().fullName;
        lastFedDateTime = source.getLastFedDateTime().value;
        species = source.getSpecies().species;
        feedingInterval = source.getFeedingInterval().value;
        tank = source.getTank().getTankName().fullTankName;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted fish object into the model's {@code Fish} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted fish.
     */
    public Fish toModelType() throws IllegalValueException {
        final List<Tag> fishTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            fishTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (lastFedDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LastFedDateTime.class
                    .getSimpleName()));
        }
        if (!LastFedDateTime.isValidLastFedDateTime(lastFedDateTime)) {
            throw new IllegalValueException(LastFedDateTime.MESSAGE_CONSTRAINTS);
        }
        final LastFedDateTime modelLastFedDateTime = new LastFedDateTime(lastFedDateTime);

        if (species == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Species.class.getSimpleName()));
        }
        if (!Species.isValidSpecies(species)) {
            throw new IllegalValueException(Species.MESSAGE_CONSTRAINTS);
        }
        final Species modelSpecies = new Species(species);

        if (feedingInterval == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, FeedingInterval.class
                    .getSimpleName()));
        }
        if (!FeedingInterval.isValidFeedingInterval(feedingInterval)) {
            throw new IllegalValueException(FeedingInterval.MESSAGE_CONSTRAINTS);
        }
        final FeedingInterval modelFeedingInterval = new FeedingInterval(feedingInterval);

        if (tank == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Tank.class.getSimpleName()));
        }
        if (!TankName.isValidTankName(tank)) {
            throw new IllegalValueException(TankName.MESSAGE_CONSTRAINTS);
        }
        //TODO: explore if it is possible to use the real Tank object instead of making new ones with same name
        //final Tank modelTankUnassigned = new UnassignedTank(null, null);
        final Tank modelTankUnassigned = new Tank(new TankName(tank), new AddressBook(),
                new UniqueIndividualReadingLevels());

        final Set<Tag> modelTags = new HashSet<>(fishTags);
        return new Fish(modelName, modelLastFedDateTime, modelSpecies, modelFeedingInterval, modelTankUnassigned,
                modelTags);
    }

}
