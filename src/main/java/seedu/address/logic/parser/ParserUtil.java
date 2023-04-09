package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.LocalTime;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.location.Location;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Station;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.util.TimeUtil;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String MESSAGE_INVALID_HOUR = "HOUR is not valid.";

    public static final String MESSAGE_INVALID_START_HOUR = "Start hours start at 8am and ends at 10pm!";

    public static final String MESSAGE_INVALID_END_HOUR = "End hours can only range from 9am to 11pm!";

    public static final String MESSAGE_INVALID_INT = "No integer entered!";

    private static final Logger logger = LogsCenter.getLogger(ParserUtil.class);

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Index>}.
     */
    public static Set<ContactIndex> parseIndices(Collection<String> indices) throws ParseException {
        requireNonNull(indices);
        final Set<ContactIndex> indexSet = new HashSet<>();
        for (String index : indices) {
            indexSet.add(parseContactIndex(index));
        }
        return indexSet;
    }

    /**
     * Returns a non-zero ContactIndex from the string index.
     */
    public static ContactIndex parseContactIndex(String contactIndex) throws ParseException {
        String trimmedIndex = contactIndex.trim();
        if (trimmedIndex.isEmpty()) {
            return null;
        }
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return new ContactIndex(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses an {@code intString} into an {@code Integer} and returns it.
     * @throws ParseException if the string cannot be converted into an integer.
     */
    public static int parseInt(String intString) throws ParseException {
        String trimmedInt = intString.trim();
        if (trimmedInt.isEmpty()) {
            throw new ParseException(MESSAGE_INVALID_INT);
        }
        Integer integer;
        try {
            integer = Integer.parseInt(trimmedInt);
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_INVALID_INT);
        }
        return integer;
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
     * Parses a {@code String station} into an {@code Station}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code station} is invalid.
     */
    public static Station parseStation(String station) throws ParseException {
        requireNonNull(station);
        String trimmedStation = station.trim();
        if (!Station.isValidStation(trimmedStation)) {
            throw new ParseException(Station.MESSAGE_CONSTRAINTS);
        }
        return new Station(trimmedStation);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String handle} into an {@code TelegramHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code handle} is invalid.
     */
    public static TelegramHandle parseTelegramHandle(String handle) throws ParseException {
        requireNonNull(handle);
        String trimmedTelegramHandle = handle.trim();
        if (!TelegramHandle.isValidTelegramHandle(trimmedTelegramHandle)) {
            throw new ParseException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        return new TelegramHandle(trimmedTelegramHandle);
    }

    /**
     * Parses a {@code String tag} into a {@code GroupTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static GroupTag parseGroupTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!GroupTag.isValidTagName(trimmedTag)) {
            throw new ParseException(GroupTag.MESSAGE_CONSTRAINTS);
        }
        return new GroupTag(trimmedTag);
    }

    /**
     * To parse additional arguments for TagCommand.
     * @param tags String to be parsed.
     * @return ArrayList for arguments.
     * @throws ParseException
     */
    public static ArrayList<String> parseMoreModules(String tags) throws ParseException {
        String trimmedArgs = tags.trim();
        //if (trimmedArgs.isEmpty()) {
        //    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        //}
        // I can just set them as null and exceptions will be throw in TagCommand.
        return new ArrayList<String>(Arrays.asList(trimmedArgs.split("\\s+")));
    }

    /**
     * Parses a {@code String tag} into a {@code GroupTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static ModuleTag parseModuleTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        ArrayList<String> args = parseMoreModules(trimmedTag);

        logger.log(Level.FINE,
                String.format("Parsing tag: %s with %d arguments", args, args.size()));

        if (args.size() == 1) {
            return parseModuleTagFromSingle(args.get(0));
        }

        if (args.size() != 4) {
            throw new ParseException(ModuleTag.MESSAGE_CONSTRAINTS);
        }

        String moduleCode = args.get(0);
        Day day = parseDay(args.get(1));
        LocalTime startTime = parseLocalTime(args.get(2), true);
        LocalTime endTime = parseLocalTime(args.get(3), false);

        TimeBlock timeBlock = parseTimeBlock(startTime, endTime, day);

        Lesson lesson = new Lesson(moduleCode, Location.NUS, timeBlock);

        logger.log(Level.FINE, String.format("Lesson parsed: %s", lesson));

        return new ModuleTag(moduleCode, lesson);
    }

    private static ModuleTag parseModuleTagFromSingle(String tag) throws ParseException {
        if (!ModuleTag.isValidTagName(tag)) {
            throw new ParseException(ModuleTag.MESSAGE_CONSTRAINTS);
        }
        return new ModuleTag(tag);
    }

    /**
     * Tries to partial match a day string.
     * MON will output MONDAY.
     */
    public static Day parseDay(String dayAsStr) throws ParseException {
        if (dayAsStr.isEmpty()) {
            throw new ParseException("Day is missing!");
        }

        String upperDayAsStr = dayAsStr.toUpperCase();

        if (upperDayAsStr.equals("T")) {
            throw new ParseException("Did you mean Tuesday or Thursday?");
        }

        for (Day day : Day.values()) {
            if (day.toString().startsWith(upperDayAsStr)) {
                return day;
            }
        }
        throw new ParseException("Day is invalid!");
    }

    /**
     * Parses a local time and checks whether it is valid.
     */
    public static LocalTime parseLocalTime(String localTimeAsStr, boolean isStartHour) throws ParseException {
        try {
            int hour = Integer.parseInt(localTimeAsStr);
            if (!TimeUtil.isValidHour(hour)) {
                throw new ParseException(MESSAGE_INVALID_HOUR);
            }
            return isStartHour
                ? parseStartHour(hour)
                : parseEndHour(hour);
        } catch (NumberFormatException nfe) {
            throw new ParseException("Invalid time");
        }
    }

    private static TimeBlock parseTimeBlock(LocalTime startTime, LocalTime endTime, Day day) throws ParseException {
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime)) {
            throw new ParseException("Start Time must be STRICTLY BEFORE End Time!");
        }

        return new TimeBlock(startTime, endTime, day);
    }

    /**
     * Parses a start time.
     */
    public static LocalTime parseStartHour(int hour) throws ParseException {
        if (TimeUtil.isValidStartHour(hour)) {
            return new LocalTime(hour, 0);
        } else {
            throw new ParseException(MESSAGE_INVALID_START_HOUR);
        }
    }

    /**
     * Parses an end time.
     */
    public static LocalTime parseEndHour(int hour) throws ParseException {
        if (TimeUtil.isValidEndHour(hour)) {
            return new LocalTime(hour, 0);
        } else {
            throw new ParseException(MESSAGE_INVALID_END_HOUR);
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<GroupTag>}.
     */
    public static Set<GroupTag> parseGroupTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<GroupTag> groupTagSet = new HashSet<>();
        for (String tagName : tags) {
            groupTagSet.add(parseGroupTag(tagName));
        }
        return groupTagSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<ModuleTag>}.
     */
    public static Set<ModuleTag> parseModuleTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<ModuleTag> moduleTagSet = new HashSet<>();
        for (String tagName : tags) {
            moduleTagSet.add(parseModuleTag(tagName));
        }
        return moduleTagSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<GroupTag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<GroupTag>} containing zero tags.
     */
    public static Optional<Set<GroupTag>> parseGroupTagsForCommands(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseGroupTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<ModuleTag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<ModuleTag>} containing zero tags.
     */
    public static Optional<Set<ModuleTag>> parseModuleTagsForCommands(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(parseModuleTags(tagSet));
    }
}

