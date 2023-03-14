package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTankList;
import seedu.address.model.TankList;
import seedu.address.model.tank.Tank;

/**
 * An Immutable {@code TankList} that is serializable to JSON format.
 */
@JsonRootName(value = "tanklist")
class JsonSerializableTankList {

    public static final String MESSAGE_DUPLICATE_TANK = "tank List contains duplicate tank(s).";

    private final List<JsonAdaptedTank> tanks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTankList} with the given {@code Tanks}.
     */
    @JsonCreator
    public JsonSerializableTankList(@JsonProperty("tanks") List<JsonAdaptedTank> tanks) {
        this.tanks.addAll(tanks);
    }

    /**
     * Converts a given {@code ReadOnlyTankList} into this class for Jackson use.
     *
     * @param source Future changes to this will not affect the created {@code JsonSerializableTankList}.
     */
    public JsonSerializableTankList(ReadOnlyTankList source) {
        tanks.addAll(source.getTankList().stream().map(JsonAdaptedTank::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Tank list into the model's {@code TankList} object.
     *
     * @throws IllegalValueException If there were any data constraints violated.
     */
    public TankList toModelType() throws IllegalValueException {
        TankList tankList = new TankList();

        for (JsonAdaptedTank jsonAdaptedTank : tanks) {
            Tank tank = jsonAdaptedTank.toModelType();
            if (tankList.hasTank(tank)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TANK);
            }
            Tank newTank = new Tank(tank.getTankName());
            tankList.addTank(newTank);
        }
        return tankList;
    }

}
