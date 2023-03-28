package seedu.address.storage.tank.readings.ammonialevels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTankList;
import seedu.address.model.TankList;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.readings.FullAmmoniaLevels;
import seedu.address.model.tank.readings.ReadOnlyAmmoniaLevels;
import seedu.address.model.tank.readings.UniqueIndividualAmmoniaLevels;
import seedu.address.storage.tank.JsonAdaptedTank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable {@code FullAmmoniaLevels} that is serializable to JSON format.
 */
@JsonRootName(value = "fullAmmoniaLevels")
public class JsonSerializableFullAmmoniaLevels {
    public static final String MESSAGE_DUPLICATE_TANK = "JsonSerializableFullAmmoniaLevels contains " +
            "duplicate tank(s).";

    private final List<JsonAdaptedIndividualAmmoniaLevels> ammoniaLevels = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFullAmmoniaLevels} with the given {@code ammoniaLevels}.
     */
    @JsonCreator
    public JsonSerializableFullAmmoniaLevels(@JsonProperty("ammoniaLevels")
                                                         List<JsonAdaptedIndividualAmmoniaLevels> ammoniaLevels) {
        this.ammoniaLevels.addAll(ammoniaLevels);
    }

    /**
     * Converts a given {@code ReadOnlyAmmoniaLevels} into this class for Jackson use.
     *
     * @param source Future changes to this will not affect the created {@code JsonSerializableFullAmmoniaLevels}.
     */
    public JsonSerializableFullAmmoniaLevels(ReadOnlyAmmoniaLevels source) {
        ammoniaLevels.addAll(source.getAmmoniaLevelLists().stream().map(JsonAdaptedIndividualAmmoniaLevels::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Full Ammonia Levels into the model's {@code FullAmmoniaLevels} object.
     *
     * @throws IllegalValueException If there were any data constraints violated.
     */
    public FullAmmoniaLevels toModelType() throws IllegalValueException {
        FullAmmoniaLevels fullAmmoniaLevels = new FullAmmoniaLevels();

        for (JsonAdaptedIndividualAmmoniaLevels jsonAdaptedAmmoniaList : ammoniaLevels) {
            UniqueIndividualAmmoniaLevels individualAmmoniaLevels = jsonAdaptedAmmoniaList.toModelType();
            fullAmmoniaLevels.addAmmoniaLevelList(individualAmmoniaLevels);
        }
        return fullAmmoniaLevels;
    }
}
