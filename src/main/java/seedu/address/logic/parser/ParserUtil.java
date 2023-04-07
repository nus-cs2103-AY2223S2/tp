package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses a {@code String eventName} into the correct format with no trailing space and
     * check if the eventName is of valid format.
     * @param eventName
     * @return a String object containing the name of the isolated/recurring event.
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static String parseEventName(String eventName) throws ParseException {
        requireNonNull(eventName);

        String trimmedEventName = eventName.trim();
        if (!Event.isValidEventName(eventName)) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS_EVENTNAME);
        }
        return trimmedEventName;
    }

    /**
     * Parses a {@code String dateTime} into a {@code LocalDateTime dateTime} into the correct format.
     * @param dateTime of which the event start/end.
     * @return LocalDateTime object containing the start/end date and time of the event.
     * @throws ParseException if the given {@code String dateTime} is in invalid format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);

        LocalDateTime dueDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dueDate = LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeException e) {
            throw new ParseException(IsolatedEvent.MESSAGE_CONSTRAINTS_DATE);
        }

        checkDateExist(dueDate, dateTime.substring(0, 2));

        if (dueDate.getMinute() != 0) {
            throw new ParseException(Event.MESSAGE_EVENT_NOT_HOURLY);
        }

        return dueDate;
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate date} into the correct format.
     * @param date of which the event start/end.
     * @return LocalDate object containing the start/end date of the event.
     * @throws ParseException if the given {@code String date} is in invalid format.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);

        LocalDate dueDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dueDate = LocalDate.parse(date, formatter);
            checkDateExist(dueDate.atStartOfDay(), date.substring(0, 2));
        } catch (DateTimeException e) {
            throw new ParseException(IsolatedEvent.MESSAGE_CONSTRAINTS_DATE);
        }

        return dueDate;
    }

    /**
     * Check whether the day exists in the month, throw error if it does not exist.
     * @param date to be checked
     * @param day to be checked
     * @throws ParseException to be thrown if there is error.
     */
    public static void checkDateExist(LocalDateTime date, String day) throws ParseException {
        boolean isLeapYear = isLeapYear(date.getYear());
        boolean isFeb = date.getMonth().getValue() == 2;
        int lastDay = date.getMonth().maxLength();

        if (!isLeapYear && isFeb) {
            lastDay--;
        }
        if (Integer.parseInt(day) > lastDay) {
            throw new ParseException(Messages.MESSAGE_NONEXISTENT_DATE);
        }
    }

    /**
     * Check if a year is a leap year
     * @param year to be checked
     * @return true or false
     */
    public static boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else if (year % 400 != 0) {
            return false;
        } else {
            return true;
        }
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
     * Parses a {@code String day} into a {@code DayOfWeek dayOfWeek} that is valid.
     * @param day of which the event takes place during the week
     * @return DayOfWeek object containing the day of the event that took place between Monday to Sunday
     * @throws ParseException if the given {@code String day} is in invalid.
     */
    public static DayOfWeek parseDayOfWeek(String day) throws ParseException {
        requireNonNull(day);
        String trimmedDayOfWeek = day.trim();

        DayOfWeek dayOfWeek;
        try {
            dayOfWeek = DayOfWeek.valueOf(trimmedDayOfWeek.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(RecurringEvent.MESSAGE_CONSTRAINTS_DAYOFWEEK);
        }

        return dayOfWeek;
    }

    /**
     * Parses a {@code String time} into a {@Code LocalTime time} into the correct format.
     * @param time of which the event start/end.
     * @return LocalTime object containing the startt/end time of the event.
     * @throws ParseException if the given {@code String date} is in invalid format.
     */
    public static LocalTime parseTime(String time) throws ParseException {
        requireNonNull(time);

        LocalTime dueDate;
        try {
            dueDate = LocalTime.parse(time);
        } catch (DateTimeException e) {
            throw new ParseException(RecurringEvent.MESSAGE_CONSTRAINTS_TIME);
        }

        if (dueDate.getMinute() != 0) {
            throw new ParseException(Event.MESSAGE_EVENT_NOT_HOURLY);
        }
        return dueDate;
    }

    /**
     * Checks if the start time and the end time of the event is valid for recurring event.
     * @param startTime of which the event start.
     * @param endTime of which the event end.
     * @return true if start time is before the end time.
     * @throws ParseException if start time is after the end time.
     */
    public static boolean parsePeriod(LocalTime startTime, LocalTime endTime) throws ParseException {
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new ParseException(RecurringEvent.MESSAGE_CONSTRAINTS_PERIOD);
        }
        return true;
    }
    /**
     * Parses a {@code String group} into a {@code Group}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code group} is invalid.
     */
    public static Group parseGroup(String group) throws ParseException {
        requireNonNull(group);
        String trimmedGroup = group.trim();
        if (!Group.isValidGroupName(trimmedGroup)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }
        return new Group(trimmedGroup);
    }

    /**
     * Parses {@code Collection<String> grous} into a {@code Set<Group>}.
     */
    public static Set<Group> parseGroups(Collection<String> groups) throws ParseException {
        requireNonNull(groups);
        final Set<Group> groupSet = new HashSet<>();
        for (String groupName : groups) {
            groupSet.add(parseGroup(groupName));
        }
        return groupSet;
    }
}
