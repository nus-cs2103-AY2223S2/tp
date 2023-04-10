package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Region;
import seedu.address.model.person.Region.Regions;
import seedu.address.model.tag.PolicyTag;

/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading
     * and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero
     *                        unsigned integer).
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
     * Parses a {@code String tag} into a {@code PolicyTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static PolicyTag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!PolicyTag.isValidTagName(trimmedTag)) {
            throw new ParseException(PolicyTag.MESSAGE_CONSTRAINTS);
        }
        return new PolicyTag(trimmedTag);
    }


    /**
     * Parses {@code Collection<String> tags} into a {@code Set<PolicyTag>}.
     */
    public static Set<PolicyTag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<PolicyTag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String meeting} into a {@code Meeting}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Meeting parseMeeting(String desc, String start, String end) throws ParseException {
        requireNonNull(desc);
        requireNonNull(start);
        requireNonNull(end);
        String trimmedDesc = desc.trim();

        Pattern dateTimeFormat = Pattern.compile("\\d{2}-\\d{2}-\\d{4}.\\d{2}:\\d{2}");
        Matcher dateTimeStartMatcher = dateTimeFormat.matcher(start.trim());
        Matcher dateTimeEndMatcher = dateTimeFormat.matcher(end.trim());

        if (!dateTimeStartMatcher.find() || !dateTimeEndMatcher.find()) {
            String incorrectDateTimeFormat =
                "Incorrect DateTime format!\n"
                    + "Correct format is: dd-mm-yyyy hh:mm";
            throw new ParseException(incorrectDateTimeFormat);
        }

        try {
            LocalDateTime parsedStart = parseDateTime(start.trim());
            LocalDateTime parsedEnd = parseDateTime(end.trim());

            return new Meeting(trimmedDesc, parsedStart, parsedEnd);
        } catch (DateTimeException dateTimeErr) {
            throw new ParseException(dateTimeErr.getMessage());
        }
    }

    /**
     * Parses a {@code String region} into the correct {@code Regions} enum
     */
    public static Regions parseRegion(String region) throws ParseException {
        requireNonNull(region); // Do we need to throw a ParserException here?
        String processedInputRegion = region.trim().toUpperCase();
        Regions[] allRegions = Regions.values();
        for (Regions r : allRegions) {
            if (r.toString().equals(processedInputRegion)) {
                return r;
            }
        }
        throw new ParseException(Region.MESSAGE_CONSTRAINTS);
    }

    public static String parseMeetingDescription(String desc) {
        return desc.trim();
    }

    /**
     * Parses a {@code String} into a {@code LocalDateTime}
     *
     * @param dateTime String of meeting start or end
     * @return meeting dateTime parsed to LocalDateTime
     * @throws ParseException if the input format is wrong
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        //dd-mm-yyyy
        String[] dateTimes = dateTime.split(" ");
        if (dateTimes.length != 2) {
            throw new ParseException("Please input a date and time!");
        }
        String[] date = dateTimes[0].split("-");
        if (date.length != 3) {
            throw new ParseException("Date format is DD-MM-YYYY");
        }
        String[] time = dateTimes[1].split(":");
        if (time.length != 2) {
            throw new ParseException("Time format is HH:MM");
        }
        try {
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            int startHour = Integer.parseInt(time[0]);
            int startMinute = Integer.parseInt(time[1]);
        } catch (NumberFormatException nfe) {
            throw new ParseException("Please input numbers for date and time!");
        }
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        int startHour = Integer.parseInt(time[0]);
        int startMinute = Integer.parseInt(time[1]);
        try {
            LocalDateTime ldt = LocalDateTime.of(year, month, day, startHour, startMinute);
        } catch (DateTimeException e) {
            throw new ParseException(e.getMessage());
        }
        return LocalDateTime.of(year, month, day, startHour, startMinute);
    }
    /**
     * Parses a {@code String} into a {@code LocalDate}
     *
     * @param date String of meeting start
     * @return meeting date parsed to LocalDate
     */
    public static LocalDate parseDate(String date) {
        String[] input = date.split(" ");
        String[] dates = input[0].split("-");
        int day = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int year = Integer.parseInt(dates[2]);
        return LocalDate.of(year, month, day);
    }
}
