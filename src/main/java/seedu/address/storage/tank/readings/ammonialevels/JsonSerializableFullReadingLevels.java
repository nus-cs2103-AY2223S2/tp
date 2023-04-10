package seedu.address.storage.tank.readings.ammonialevels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tank.readings.FullReadingLevels;
import seedu.address.model.tank.readings.ReadOnlyReadingLevels;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;

/**
 * An Immutable {@code FullReadingLevels} that is serializable to JSON format.
 */
@JsonRootName(value = "fullReadingLevels")
public class JsonSerializableFullReadingLevels {
    public static final String MESSAGE_DUPLICATE_TANK = "JsonSerializableFullReadingLevels contains "
            + "duplicate tank(s).";

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
        readingLevels.addAll(source.getFullReadingLevels().stream().map(JsonAdaptedIndividualReadingLevels::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Full Reading Levels into the model's {@code FullReadingLevels} object.
     *
     * @throws IllegalValueException If there were any data constraints violated.
     */
    public FullReadingLevels toModelType() throws IllegalValueException {
        FullReadingLevels fullReadingLevels = new FullReadingLevels();

        for (JsonAdaptedIndividualReadingLevels jsonAdaptedReadingList : readingLevels) {
            UniqueIndividualReadingLevels individualReadingLevels = jsonAdaptedReadingList.toModelType();
            if (fullReadingLevels.hasIndividualReadingLevels(individualReadingLevels)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TANK);
            }
            fullReadingLevels.addIndividualReadingLevel(individualReadingLevels);
        }
        return fullReadingLevels;
    }
}
