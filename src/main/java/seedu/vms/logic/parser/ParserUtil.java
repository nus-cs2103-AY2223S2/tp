package seedu.vms.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.vms.commons.core.index.Index;
import seedu.vms.commons.util.StringUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Phone;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATE = "Date is of an invalid format";

    private static final String DEFAULT_DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}";
    private static final String FULL_DATE_REGEX = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}";
    private static final String DATE_ONLY_REGEX = "\\d{4}-\\d{1,2}-\\d{1,2}";

    private static final String FULL_DATE_PATTERN = "yyyy-M-d HHmm";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a String date to a {@code LocalDateTime}.
     * <p>
     * The following formats are supported:
     * <ul>
     * <li>{@code yyyy-MM-dd'T'hh:mm}
     * <li>{@code yyyy-M-d hhmm}
     * </ul>
     *
     * @param dateString - the String date to parse.
     * @return the parsed {@code LocalDateTime}.
     * @throws ParseException if the given String cannot be parsed.
     */
    public static LocalDateTime parseDate(String dateString) throws ParseException {
        try {
            return parseCustomDate(dateString);
        } catch (DateTimeParseException dateParseEx) {
            throw new ParseException(dateParseEx.getMessage());
        }
    }

    private static LocalDateTime parseCustomDate(String dateString) throws ParseException {
        if (dateString.matches(DEFAULT_DATE_REGEX)) {
            // yyyy-MM-dd'T'hh:mm format
            return LocalDateTime.parse(dateString);
        } else if (dateString.matches(FULL_DATE_REGEX)) {
            // yyyy-MM-dd hhmm format
            return LocalDateTime.parse(
                    dateString,
                    DateTimeFormatter.ofPattern(FULL_DATE_PATTERN));
        } else if (dateString.matches(DATE_ONLY_REGEX)) {
            return LocalDateTime.parse(
                    dateString + " 0000",
                    DateTimeFormatter.ofPattern(FULL_DATE_PATTERN));
        }
        throw new ParseException(MESSAGE_INVALID_DATE);
    }
}
