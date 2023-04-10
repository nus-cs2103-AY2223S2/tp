package seedu.address.model.person.fields;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Person's race in the address book.
 */
public class Race extends Field {

    public static final String MESSAGE_CONSTRAINTS = "Race should only contain alphabets and spaces";
    public static final String VALIDATION_REGEX = "^[A-Za-z\\s]+$";

    /**
     * Constructs a {@code Race}.
     *
     * @param race A valid race.
     */
    public Race(String race) {
        super(race);
        checkArgument(isValidRace(race), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns if a given string is a valid race.
     */
    public static boolean isValidRace(String trimmedRace) {
        if (Objects.equals(trimmedRace, "")) {
            return true;
        }
        return trimmedRace.matches(VALIDATION_REGEX);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Race // instanceof handles nulls
                && this.value.equals(((Race) other).value)); // state check
    }
}
