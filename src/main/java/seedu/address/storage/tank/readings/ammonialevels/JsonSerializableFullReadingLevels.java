package seedu.address.storage.tank.readings.ammonialevels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tank.readings.FullReadingLevels;
import seedu.address.model.tank.readings.ReadOnlyReadingLevels;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable {@code FullReadingLevels} that is serializable to JSON format.
 */
@JsonRootName(value = "fullAmmoniaLevels")
public class JsonSerializableFullReadingLevels {
    public static final String MESSAGE_DUPLICATE_TANK = "JsonSerializableFullReadingLevels contains " +
            "duplicate tank(s).";

    private final List<JsonAdaptedIndividualReadingLevels> readingLevels = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFullReadingLevels} with the given {@code readingLevels}.
     */
    @JsonCreator
    public JsonSerializableFullReadingLevels(@JsonProperty("readingLevels")
                                                         List<JsonAdaptedIndividualReadingLevels> readingLevels) {
        this.readingLevels.addAll(readingLevels);
    }

    /**
     * Converts a given {@code ReadOnlyReadingLevels} into this class for Jackson use.
     *
     * @param source Future changes to this will not affect the created {@code JsonSerializableFullReadingLevels}.
     */
    public JsonSerializableFullReadingLevels(ReadOnlyReadingLevels source) {
        readingLevels.addAll(source.getAmmoniaLevelLists().stream().map(JsonAdaptedIndividualReadingLevels::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Full Ammonia Levels into the model's {@code FullReadingLevels} object.
     *
     * @throws IllegalValueException If there were any data constraints violated.
     */
    public FullReadingLevels toModelType() throws IllegalValueException {
        FullReadingLevels fullReadingLevels = new FullReadingLevels();

        for (JsonAdaptedIndividualReadingLevels jsonAdaptedReadingList : readingLevels) {
            UniqueIndividualReadingLevels individualAmmoniaLevels = jsonAdaptedReadingList.toModelType();
            //TODO add dupe tank check
            fullReadingLevels.addAmmoniaLevelList(individualAmmoniaLevels);
        }
        return fullReadingLevels;
    }
}
