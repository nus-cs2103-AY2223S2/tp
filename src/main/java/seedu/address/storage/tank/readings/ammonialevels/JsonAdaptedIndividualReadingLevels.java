package seedu.address.storage.tank.readings.ammonialevels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;
import seedu.address.model.tank.readings.AmmoniaLevel;
import seedu.address.model.tank.readings.PH;
import seedu.address.model.tank.readings.Temperature;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;

/**
 * Jackson-friendly version of {@code IndividualAmmoniaLevels}.
 */
public class JsonAdaptedIndividualReadingLevels {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tank's %s field is missing!";

    private final String tankName;

    private final String commaSeperatedValuesAmmonia;

    private final String commaSeperatedDatesAmmonia;

    private final String commaSeperatedValuesPH;

    private final String commaSeperatedDatesPH;

    private final String commaSeperatedValuesTemp;

    private final String commaSeperatedDatesTemp;

    /**
     * Constructs a {@code JsonAdaptedTank} with the given details.
     */
    @JsonCreator
    public JsonAdaptedIndividualReadingLevels(@JsonProperty("tankName") String tankName,
                                              @JsonProperty("commaSeperatedValuesAmmonia")
                                                      String commaSeperatedValuesAmmonia,
                                              @JsonProperty("commaSeperatedDatesAmmonia")
                                                          String commaSeperatedDatesAmmonia,
                                              @JsonProperty("commaSeperatedValuesPH")
                                                          String commaSeperatedValuesPH,
                                              @JsonProperty("commaSeperatedDatesPH")
                                                          String commaSeperatedDatesPH,
                                              @JsonProperty("commaSeperatedValuesTemp")
                                                          String commaSeperatedValuesTemp,
                                              @JsonProperty("commaSeperatedDatesTemp")
                                                          String commaSeperatedDatesTemp) {
        this.tankName = tankName;
        this.commaSeperatedValuesAmmonia = commaSeperatedValuesAmmonia;
        this.commaSeperatedDatesAmmonia = commaSeperatedDatesAmmonia;
        this.commaSeperatedValuesPH = commaSeperatedValuesPH;
        this.commaSeperatedDatesPH = commaSeperatedDatesPH;
        this.commaSeperatedValuesTemp = commaSeperatedValuesTemp;
        this.commaSeperatedDatesTemp = commaSeperatedDatesTemp;
    }

    /**
     * Converts a given {@code Tank} into this class for Jackson use.
     */
    public JsonAdaptedIndividualReadingLevels(UniqueIndividualReadingLevels source) {
        tankName = source.getTank().getTankName().fullTankName;
        commaSeperatedValuesAmmonia = source.getCommaSeperatedValuesStringAmmonia();
        commaSeperatedDatesAmmonia = source.getCommaSeperatedDatesStringAmmonia();
        commaSeperatedValuesPH = source.getCommaSeperatedValuesStringPH();
        commaSeperatedDatesPH = source.getCommaSeperatedDatesStringPH();
        commaSeperatedValuesTemp = source.getCommaSeperatedValuesStringTemp();
        commaSeperatedDatesTemp = source.getCommaSeperatedDatesStringTemp();
    }

    /**
     * Converts this Jackson-friendly adapted IndividualReadingsList object into the model's
     * {@code UniqueIndividualReadingLevels} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Tank.
     */
    public UniqueIndividualReadingLevels toModelType() throws IllegalValueException {
        if (tankName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TankName.class.getSimpleName()));
        }
        if (!TankName.isValidTankName(tankName)) {
            throw new IllegalValueException(TankName.MESSAGE_CONSTRAINTS);
        }
        final TankName modelTankName = new TankName(tankName);
        Tank tank = new Tank(modelTankName, new AddressBook(), new UniqueIndividualReadingLevels());

        String[] valuesAmmonia = commaSeperatedValuesAmmonia.split(",");
        String[] datesAmmonia = commaSeperatedDatesAmmonia.split(",");
        String[] valuesPH = commaSeperatedValuesPH.split(",");
        String[] datesPH = commaSeperatedDatesPH.split(",");
        String[] valuesTemp = commaSeperatedValuesTemp.split(",");
        String[] datesTemp = commaSeperatedDatesTemp.split(",");
        //create unique adapted indi list
        UniqueIndividualReadingLevels ret = new UniqueIndividualReadingLevels();
        //for loop, create ammonia levels, add to list
        for (int i = 0; i < valuesAmmonia.length; i++) {
            String curValueAmmonia = valuesAmmonia[i];
            String curDateAmmonia = datesAmmonia[i];
            String curValuePH = valuesPH[i];
            String curDatePH = datesPH[i];
            String curValueTemp = valuesTemp[i];
            String curDateTemp = datesTemp[i];
            if (curValueAmmonia == null || curDateAmmonia == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        AmmoniaLevel.class.getSimpleName()));
            }
            if (!AmmoniaLevel.isValidAmmoniaLevel(curValueAmmonia, curDateAmmonia)) {
                throw new IllegalValueException(AmmoniaLevel.MESSAGE_CONSTRAINTS);
            }
            if (curValuePH == null || curDatePH == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        PH.class.getSimpleName()));
            }
            if (!PH.isValidPH(curValuePH, curDatePH)) {
                throw new IllegalValueException(PH.MESSAGE_CONSTRAINTS);
            }
            if (curValueTemp == null || curDateTemp == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        Temperature.class.getSimpleName()));
            }
            if (!Temperature.isValidTemperature(curValueTemp, curDateTemp)) {
                throw new IllegalValueException(Temperature.MESSAGE_CONSTRAINTS);
            }
            AmmoniaLevel curLevel = new AmmoniaLevel(curValueAmmonia, curDateAmmonia, tank);
            PH curPH = new PH(curValuePH, curDatePH, tank);
            Temperature curTemp = new Temperature(curValueTemp, curDateTemp, tank);
            ret.add(curLevel, curPH, curTemp);
        }
        //set list's tank
        ret.setTank(tank);
        //return list
        return ret;
    }
}
