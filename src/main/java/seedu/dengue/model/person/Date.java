package seedu.dengue.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

/**
 * Represents a Person's dengue case date in the Dengue Hotspot Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Dates should adhere to the following constraints:\n"
            + "1. Dates to contain 4-digit years YYYY, 2-digit days dd and either 2-digit months MM,"
            + " 3-letter months MMM, \n"
            + "or fully-spelled months MMMM.\n"
            + " - If months are given in the MM format and the dates are ambiguous, eg. 20001010 \n "
            + "YYYY will be read first, followed by MM and DD. \n"
            + "2. Dates must be valid. i.e.\n"
            + " - MM must be a number between 01 and 12 inclusive\n"
            + " - dd must be a number between 01 and 31 inclusive\n"
            + " - dd should be within the valid range for the MM given.\n"
            + "Example Dates: Jan-23-2000";

    private static final DateFormat NUM_FORMATS =
            new DateFormat("uuuu", "MM", "dd");
    private static final DateFormat LONG_FORMATS =
            new DateFormat("uuuu", "MMMM", "dd");
    private static final DateFormat SHORT_FORMATS =
            new DateFormat("uuuu", "MMM", "dd");
    private static final Pattern LONG_MONTH_FORMAT_CHECK = Pattern.compile("[a-zA-Z]{4,}");
    private static final Pattern MONTH_FORMAT_CHECK = Pattern.compile("[a-zA-Z]{3}");


    public final String value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A Date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(date, produceValidationFormat(date))
                .toString();
    }

    /**
     * @param test A string which is tested on whether it is a valid date.
     * @return A boolean value.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, produceValidationFormat(test));
        } catch (DateTimeParseException e) {
            return false;
        } catch (IllegalArgumentException err) {
            return false;
        }
        return true;
    }

    /**
     * Produces a validation format, given input date string, to accommodate different date formats.
     * such as yyyy-MM-dd, yyyy MMMM dd or MMM/yyyy/dd.
     * When the order of numbers are ambiguous e.g. "20000101",
     * the first chain of alphabets 3 letters or longer will be parsed as MMM or MMMM.
     * The first 4-digit substring will be read into the year parameter.
     * Then, if month is in MM format,
     * the first 2-digit substring will be read into the month parameter.
     * Lastly, the last 2-digit substring will be read into the day parameter.
     * @param date A string representation of the date.
     * @return A format representation of the date. Eg. "yyyy MMM dd"
     */
    public static DateTimeFormatter produceValidationFormat(String date)
            throws IllegalArgumentException {
        String dateString = date;

        boolean alphabetIsPresent = MONTH_FORMAT_CHECK.matcher(dateString).find();
        boolean longAlphabetIsPresent = LONG_MONTH_FORMAT_CHECK
                .matcher(dateString).find();

        if (longAlphabetIsPresent) {
            dateString = dateString.replaceFirst("[a-zA-Z]{4,}", LONG_FORMATS.getMonth());
            dateString = dateString.replaceFirst("\\d{4}", LONG_FORMATS.getYear());
            dateString = dateString.replaceFirst("\\d{2}", LONG_FORMATS.getDay());

        } else if (alphabetIsPresent) {
            dateString = dateString.replaceFirst("[a-zA-Z]{3}", SHORT_FORMATS.getMonth());
            dateString = dateString.replaceFirst("\\d{4}", SHORT_FORMATS.getYear());
            dateString = dateString.replaceFirst("\\d{2}", SHORT_FORMATS.getDay());
        } else {
            dateString = dateString.replaceFirst("\\d{4}", NUM_FORMATS.getYear());
            dateString = dateString.replaceFirst("\\d{2}", NUM_FORMATS.getMonth());
            dateString = dateString.replaceFirst("\\d{2}", NUM_FORMATS.getDay());
        }


        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder = builder.parseCaseInsensitive();
        builder.parseCaseInsensitive();
        builder.appendPattern(dateString);

        DateTimeFormatter format = builder.toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);


        return format;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
