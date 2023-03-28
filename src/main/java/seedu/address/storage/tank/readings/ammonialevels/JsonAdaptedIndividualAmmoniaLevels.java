package seedu.address.storage.tank.readings.ammonialevels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;
import seedu.address.model.tank.readings.UniqueIndividualAmmoniaLevels;

/**
 * Jackson-friendly version of {@code IndividualAmmoniaLevels}.
 */
public class JsonAdaptedIndividualAmmoniaLevels {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tank's %s field is missing!";

    private final String tankName;

    private final String commaSeperatedValues;

    private final String commaSeperatedDates;

    /**
     * Constructs a {@code JsonAdaptedTank} with the given details.
     */
    @JsonCreator
    public JsonAdaptedIndividualAmmoniaLevels(@JsonProperty("tankName") String tankName,
                                              @JsonProperty("commaSeperatedValues") String commaSeperatedValues,
                                              @JsonProperty("commaSeperatedDates") String commaSeperatedDates) {
        this.tankName = tankName;
        this.commaSeperatedValues = commaSeperatedValues;
        this.commaSeperatedDates = commaSeperatedDates;
    }

    /**
     * Converts a given {@code Tank} into this class for Jackson use.
     */
    public JsonAdaptedIndividualAmmoniaLevels(UniqueIndividualAmmoniaLevels source) {
        tankName = source.getTank().getTankName().fullTankName;
        commaSeperatedValues = source.getCommaSeperatedValuesString();
        commaSeperatedDates = source.getCommaSeperatedDatesString();
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
        Tank tank = new Tank(modelTankName, new AddressBook());

        //create unique adapted indi list

        //for loop, create ammonia levels, add to list

        //set list's tank

        //return list
    }
}
