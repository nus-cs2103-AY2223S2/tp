package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;

/**
 * Jackson-friendly version of {@link Tank}.
 */
class JsonAdaptedTank {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tank's %s field is missing!";

    private final String tankName;

    /**
     * Constructs a {@code JsonAdaptedTank} with the given details.
     */
    @JsonCreator
    public JsonAdaptedTank(@JsonProperty("tank_name") String tankName) {
        this.tankName = tankName;
    }

    /**
     * Converts a given {@code Tank} into this class for Jackson use.
     */
    public JsonAdaptedTank(Tank source) {
        tankName = source.getTankName().fullTankName;
    }

    /**
     * Converts this Jackson-friendly adapted Tank object into the model's {@code Tank} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Tank.
     */
    public Tank toModelType() throws IllegalValueException {
        if (tankName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TankName.class.getSimpleName()));
        }
        if (!TankName.isValidTankName(tankName)) {
            throw new IllegalValueException(TankName.MESSAGE_CONSTRAINTS);
        }
        final TankName modelTankName = new TankName(tankName);
        return new Tank(modelTankName, null);
    }
}
