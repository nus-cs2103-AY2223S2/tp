package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.date.DateUtil;
import seedu.address.model.tank.Tank;

/**
 * abstract class for Readings
 */
public abstract class Reading {

    public static final String MESSAGE_CONSTRAINTS =
            "Reading dates should be in the format of dd/mm/yyyy HH:mm";
    public static final String VALIDATION_REGEX =
            "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} (?:[01]\\d|2[0-3]):[0-5]\\d$";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public final String dateString;
    public final LocalDateTime localDateTime;
    public final String alphaNumericDate;
    public final Tank tank;

    /**
     * Constructor for reading
     * @param dateTime dateTime reading was recorded
     * @param tank the tank this reading belongs to
     */
    public Reading(String dateTime, Tank tank) {
        requireNonNull(dateTime);
        checkArgument(isValidReading(dateTime), MESSAGE_CONSTRAINTS);
        dateString = dateTime;
        localDateTime = LocalDateTime.parse(dateTime, formatter);
        alphaNumericDate = DateUtil.getTaskDescriptionDateTimeFormat(localDateTime);
        this.tank = tank;
    }

    public boolean isValidReading(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public abstract boolean equals(Object other);

    public abstract int hashcode();

    @Override
    public String toString() {
        return dateString;
    }

}
