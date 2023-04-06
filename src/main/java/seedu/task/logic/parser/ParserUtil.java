package seedu.task.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.task.commons.core.index.Index;
import seedu.task.commons.core.index.IndexList;
import seedu.task.commons.util.StringUtil;
import seedu.task.logic.parser.exceptions.ParseException;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.Date;
import seedu.task.model.task.Description;
import seedu.task.model.task.Effort;
import seedu.task.model.task.Name;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_TIME = "Timeframe is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_LOCAL_DATE = "Format of date entered should be yyyy-mm-dd";

    /**
     * Parses {@code oneBasedIndexList} into an {@code IndexList} and returns it. Leading and trailing whitespaces
     * will be trimmed.
     * @throws ParseException if any of the specified index is invalid (not non-zero unsigned integer).
     */
    public static IndexList parseIndexList(String oneBasedIndexList) throws ParseException {
        String trimmedIndices = oneBasedIndexList.trim();
        String[] indices = trimmedIndices.split(" ");

        int num = indices.length;
        IndexList list = new IndexList();

        for (int i = 0; i < num; i++) {
            list.add(parseIndex(indices[i]));
        }

        return list;
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex) || hasIntegerOverflow(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code hours} into an {@code hours} and returns it.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static int parseTimeFrame(String hours) throws ParseException {
        String trimmedHours = hours.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedHours) || hasIntegerOverflow(trimmedHours)) {
            throw new ParseException(MESSAGE_INVALID_TIME);
        }
        return Integer.parseInt(trimmedHours);
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
     * Parses {@code Collection<String> names} into a {@code Set<Name>}.
     */
    public static Set<Name> parseNames(Collection<String> names) throws ParseException {
        requireNonNull(names);
        final Set<Name> nameSet = new HashSet<>();
        for (String name : names) {
            nameSet.add(parseName(name));
        }
        return nameSet;
    }

    /**
     * Changes a set of names to a list of names
     * @param names The set of names
     * @return The corresponding list of names
     */
    public static List<String> parseNamesToList(Set<Name> names) {
        requireNonNull(names);
        List<String> nameList = new ArrayList<>();
        for (Name cur: names) {
            nameList.add(cur.toString());
        }
        return nameList;
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(description);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     * This function is to be used by the edit command, allowing differentiation of behaviour for empty strings.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseEditedDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (trimmedDescription.length() == 0) {
            return new Description();
        }
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(description);
    }

    /**
     * Parses a {@code Collection string} into a {@code Set description}
     * @param descriptions The collection of descriptions
     * @return The descriptions as a set
     * @throws ParseException if the given{@code descriptions is invalid}
     */
    public static Set<Description> parseDescriptions(Collection<String> descriptions) throws ParseException {
        requireNonNull(descriptions);
        final Set<Description> descriptionSet = new HashSet<>();
        for (String cur: descriptions) {
            descriptionSet.add(parseDescription(cur));
        }
        return descriptionSet;
    }

    /**
     * Returns a list of strings from the set of descriptions
     * @param descriptions The set of descriptions
     * @return A list of string
     */
    public static List<String> parseDescriptionsToList(Set<Description> descriptions) {
        requireNonNull(descriptions);
        final List<String> descriptionList = new ArrayList<>();
        for (Description cur: descriptions) {
            descriptionList.add(cur.toString());
        }
        return descriptionList;
    }

    /**
     * Parses a {@code String Date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseLocalDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            LocalDate localDate = LocalDate.parse(trimmedDate);
            return localDate;
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_LOCAL_DATE);
        }
    }

    /**
     * Parses a {@code String Effort} into a {@code Effort}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Effort} is invalid.
     */
    public static Effort parseEffort(String effort) throws ParseException {
        requireNonNull(effort);
        String trimmedEffort = effort.trim();
        if (!Effort.isValidEffort(trimmedEffort) || hasIntegerOverflow(trimmedEffort)) {
            throw new ParseException(Effort.MESSAGE_CONSTRAINTS);
        }
        long taskEffort = Long.parseLong(trimmedEffort);
        return new Effort(taskEffort);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
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
     * Parses {@code Collection<String> tags} into a {@code List<String>}.
     */
    public static List<String> parseTagsToList(Set<Tag> tags) {
        requireNonNull(tags);
        List<String> tagList = new ArrayList<>();
        for (Tag cur: tags) {
            tagList.add(cur.getTagName());
        }
        System.out.println(tagList);
        return tagList;
    }

    private static boolean hasIntegerOverflow(String value) {
        if (value.length() > 10) {
            return true;
        } else if (value.length() < 10) {
            return false;
        } else {
            return Integer.parseInt(value.substring(0, 1)) > 2 || Integer.parseInt(value.substring(1, 2)) > 1
                    || Integer.parseInt(value.substring(2, 3)) > 4 || Integer.parseInt(value.substring(3, 4)) > 7
                    || Integer.parseInt(value.substring(4, 5)) > 4 || Integer.parseInt(value.substring(5, 6)) > 8
                    || Integer.parseInt(value.substring(6, 7)) > 3 || Integer.parseInt(value.substring(7, 8)) > 6
                    || Integer.parseInt(value.substring(8, 9)) > 4 || Integer.parseInt(value.substring(9)) > 7;
        }
    }

}
