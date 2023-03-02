package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

/**
 * Represents a patient's date of birth in the patient record
 */
public class DateOfBirth {
    public static final String MESSAGE_CONSTRAINTS =
            "Date of birth should only contain numeric characters and spaces, "
                    + "and the date should fall between 01/01/1900 and current date";
    private static final DateTimeFormatter[] FORMATS =
            Stream.of("dd.MM.yyyy", "dd/MM/yyyy", "dd-MM-yyyy",
                            "dd.MM.yy", "dd/MM/yy", "dd-MM-yy")
                    .map(DateTimeFormatter::ofPattern)
                    .toArray(DateTimeFormatter[]::new);
    private static final LocalDate minRangeOfBirth = LocalDate.of(1900, 1, 1);
    private static final LocalDate maxRangeOfBirth = LocalDate.now();
    private final String birthDate;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param birthDate A valid birth date.
     */
    public DateOfBirth(String birthDate) {
        requireNonNull(birthDate);
        checkArgument(isValidBirthDate(birthDate), MESSAGE_CONSTRAINTS);
        this.birthDate = birthDate;
    }

    /**
     * Returns true if a given string is in valid date format,
     * i.e.: dd.mm.yyyy -> 01.01.2023
     *       dd/mm/yyyy -> 01/01/2023
     *       dd-mm-yyyy -> 01-01-2023
     *       dd.mm.yy -> 01.01.2023
     *       dd/mm/yy -> 01/01/23
     *       dd-mm-yy -> 01-01-23
     */
    private static LocalDate formatDateIfValid(String test) {
        for (DateTimeFormatter format: FORMATS) {
            try {
                return LocalDate.parse(test, format);
            } catch (DateTimeParseException e) {
                // do nothing, continue check whether given string input match any other date time format
            }
        }
        return null;
    }

    /**
     * Returns true if a given string is a valid date of birth, or more
     * specifically the date of birth fall between year of 1900 and current date.
     */
    public static boolean isValidBirthDate(String test) {
        // check whether one's date of birth is fall between 1900 and current year.
        LocalDate formattedDate = formatDateIfValid(test);
        if (formattedDate == null) {
            return false;
        }
        return formattedDate.isBefore(maxRangeOfBirth) && formattedDate.isAfter(minRangeOfBirth);
    }

    @Override
    public String toString() {
        return birthDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && birthDate.equals(((DateOfBirth) other).birthDate)); // state check
    }

    @Override
    public int hashCode() {
        return birthDate.hashCode();
    }

}
