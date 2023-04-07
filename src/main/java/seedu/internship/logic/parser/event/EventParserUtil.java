package seedu.internship.logic.parser.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.internship.commons.core.index.Index;
import seedu.internship.commons.util.DateTimeUtil;
import seedu.internship.commons.util.StringUtil;
import seedu.internship.logic.parser.ParserUtil;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.event.End;
import seedu.internship.model.event.EventDescription;
import seedu.internship.model.event.Name;
import seedu.internship.model.event.Start;

/**
 * Contains utility methods used for parsing strings in the various EventParser classes.
 */
public class EventParserUtil extends ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseInternIndex(String internIndex) throws ParseException {
        String trimmedIndex = internIndex.trim();
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
    public static Name parseEventName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String start} into a {@code Start}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code start} is invalid.
     */
    public static Start parseEventStart(String start) throws ParseException {
        requireNonNull(start);
        String trimmedDateTime = start.trim();
        if (!DateTimeUtil.isValidLocalDateTimeString(trimmedDateTime, Start.NUMERIC_DATE_TIME_FORMATTER)) {
            throw new ParseException(Start.MESSAGE_CONSTRAINTS);
        }

        return new Start(LocalDateTime.parse(trimmedDateTime, Start.NUMERIC_DATE_TIME_FORMATTER));
    }

    /**
     * Parses a {@code String end} into a {@code end}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code end} is invalid.
     */
    public static End parseEventEnd(String end) throws ParseException {
        requireNonNull(end);
        String trimmedDateTime = end.trim();
        if (!DateTimeUtil.isValidLocalDateTimeString(trimmedDateTime, End.NUMERIC_DATE_TIME_FORMATTER)) {
            throw new ParseException(End.MESSAGE_CONSTRAINTS);
        }

        return new End(LocalDateTime.parse(trimmedDateTime, End.NUMERIC_DATE_TIME_FORMATTER));
    }

    /**
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static EventDescription parseEventDescription(String description) {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        return new EventDescription(trimmedDescription);
    }
}
