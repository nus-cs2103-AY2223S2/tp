package seedu.internship.logic.parser.event;

import static java.util.Objects.requireNonNull;

import seedu.internship.commons.core.index.Index;
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
        String trimmedStart = name.trim();
        Name n = new Name(trimmedStart);
        if (!n.isValidName(name)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return n;

    }
    /**
     * Parses a {@code String start} into a {@code Start}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code start} is invalid.
     */
    public static Start parseEventStart(String start) throws ParseException {
        requireNonNull(start);
        String trimmedStart = start.trim();
        Start s = new Start(trimmedStart);
        if (!s.isValidStart()) {
            throw new ParseException(Start.MESSAGE_CONSTRAINTS);
        }
        return s;

    }

    /**
     * Parses a {@code String end} into a {@code end}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code end} is invalid.
     */
    public static End parseEventEnd(String end) throws ParseException {
        requireNonNull(end);
        String trimmedEnd = end.trim();
        End e = new End(trimmedEnd);
        if (!e.isValidEnd()) {
            throw new ParseException(End.MESSAGE_CONSTRAINTS);
        }
        return e;
    }

    /**
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static EventDescription parseEventDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        // No need ot check valid descripiton , as anything an be in description
        // if (!Description.isValidDescription(trimmedDescription)) {
        //   throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        // }
        return new EventDescription(trimmedDescription);
    }
}
