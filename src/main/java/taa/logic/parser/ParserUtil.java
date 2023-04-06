package taa.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import taa.commons.core.index.Index;
import taa.commons.util.StringUtil;
import taa.logic.parser.exceptions.ParseException;
import taa.model.student.Attendance;
import taa.model.student.Name;
import taa.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a valid non-zero unsigned integer.";

    /**
     * Checks if a string is either 1 or 0 after trimming.
     *
     * @param points String version of mark to be checked
     * @return boolean if points is valid or not
     */
    public static final Predicate<String> IS_BIN_INT = s -> {
        s = s.trim();
        return "0".equals(s) || "1".equals(s);
    };

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
     * Parses a {@code String name} into a {@code Name}. Leading and trailing whitespaces will be trimmed.
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
     * Parses a {@code String week} into an int
     *
     * @param week String value of week
     * @return int value of week if valid
     * @throws ParseException if the given {@code week} is invalid
     */
    public static int parseWeek(String week) throws ParseException {
        requireNonNull(week);
        String trimmedWeek = week.trim();
        try {
            int value = Integer.parseInt(trimmedWeek);
        } catch (NumberFormatException e) {
            throw new ParseException(Attendance.WEEK_ERROR_MSG);
        }
        if (!Attendance.isValidWeek(trimmedWeek)) {
            throw new ParseException(Attendance.WEEK_ERROR_MSG);
        }
        return Attendance.convertToIntegerWeek(trimmedWeek);
    }

    /**
     * Parses a {@code String points} into a {@code int}. Leading and trailing whitespaces will be trimmed.
     *
     * @param points String version of the points to be parsed
     * @return int version of points if is valid
     * @throws ParseException if points are not integers between 0-700
     */
    public static int parsePartPoints(String points) throws ParseException {
        requireNonNull(points);
        final String trimmedPoints = points.trim();
        if (!Attendance.isValidParticipationPoints(trimmedPoints)) {
            throw new ParseException(Attendance.POINTS_ERROR_MSG);
        }
        return Integer.parseInt(trimmedPoints);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}. Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses time for countdown
     */
    public static Integer parseTime(String time) throws ParseException {
        requireNonNull(time);
        final int value;
        try {
            value = Integer.parseInt(time.trim());
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid time for alarm");
        }
        return value;
    }

    /**
     * Parse int input given
     */
    public static int parseInt(String s) throws ParseException {
        int theInt;
        try {
            theInt = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format("Invalid input: %s", s));
        }
        return theInt;
    }
}
