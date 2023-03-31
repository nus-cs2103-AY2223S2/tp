package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Performance;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Photo;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_RANGE = "Only one range is allowed.";
    public static final List<LocalDateTime[]> MASTER_TIME = new ArrayList<>();

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
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index[] parseIndexRange(String oneBasedIndexRange) throws ParseException {
        String trimmedIndex = oneBasedIndexRange.trim();
        String[] startEndRange = trimmedIndex.split("-");

        if (startEndRange.length > 2) {
            throw new ParseException(MESSAGE_INVALID_RANGE);
        }

        if (startEndRange.length == 1) {
            Index fixedIndex = parseIndex(oneBasedIndexRange);
            return new Index[]{fixedIndex, fixedIndex};
        }

        String startIndex = startEndRange[0];
        startIndex = startIndex.trim();

        String endIndex = startEndRange[1];
        endIndex = endIndex.trim();

        if (!StringUtil.isNonZeroUnsignedInteger(startIndex)
                && !StringUtil.isNonZeroUnsignedInteger(endIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        if (!StringUtil.isNonZeroUnsignedInteger(startIndex)
                || !StringUtil.isNonZeroUnsignedInteger(endIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        if (Integer.parseInt(startIndex) > Integer.parseInt(endIndex)) {
            throw new ParseException("Start index cannot be greater than end index");
        }

        Index startOneBased = Index.fromOneBased(Integer.parseInt(startIndex));
        Index endOneBased = Index.fromOneBased(Integer.parseInt(endIndex));

        return new Index[]{startOneBased, endOneBased};
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
     * Simulates retriving student photo from NUS backend / database
     * @return Photo
     * @throws ParseException
     */
    public static Photo parsePhoto() throws ParseException {
        GuiSettings guiSettings = new GuiSettings();
        int startIndex = guiSettings.getPhotoStartIndex();
        int endIndex = guiSettings.getPhotoEndIndex();
        int randomStudentPhotoIndex = ThreadLocalRandom.current().nextInt(startIndex, endIndex);
        String path = guiSettings.getPhoto() + randomStudentPhotoIndex + guiSettings.getPhotoFormat();
        return new Photo(path);
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

    //Parse event commands
    /**
     * Parses a {@code String name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseTutorialName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (name.toLowerCase().contains("lab")) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        if (name.toLowerCase().contains("consultation")) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        if (name.toLowerCase().equals("tutorial")) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return trimmedName;
    }

    /**
     * Parses a {@code String name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseLabName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (name.toLowerCase().contains("tutorial")) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        if (name.toLowerCase().contains("consultation")) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        if (name.toLowerCase().equals("lab")) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return trimmedName;
    }

    /**
     * Parses a {@code String name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseConsultationName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (name.toLowerCase().contains("lab")) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        if (name.toLowerCase().contains("tutorial")) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        if (name.toLowerCase().equals("consultation")) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return trimmedName;
    }


    /**
     * Parses a {@code String name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseEventName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return trimmedName;
    }


    /**
     * Parses a {@code String name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseRecurName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        return trimmedName;
    }

    /**
     * Parses a {@code String date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDateTime parseEventDate(String date, int hours) throws ParseException {
        //date can be null or empty as it is optional
        String trimmedDate = date.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if (!trimmedDate.matches(
                "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d\\d)\\s([0-1]?[0-9]|2?[0-3]):([0-5]\\d)")) {
            throw new ParseException("Invalid date format!");
        }
        LocalDateTime newDateStart = LocalDateTime.parse(trimmedDate, formatter);
        LocalDateTime newDateEnd = LocalDateTime.parse(trimmedDate, formatter).plusHours(hours);
        LocalDateTime[] newRange = new LocalDateTime[]{newDateStart, newDateEnd};
        for (int i = 0; i < MASTER_TIME.size(); i++) {
            if (MASTER_TIME.size() == 0) {
                break;
            }
            LocalDateTime[] currentRange = MASTER_TIME.get(i);
            if (newDateStart.isAfter(currentRange[0]) && newDateStart.isBefore(currentRange[1])) {
                throw new ParseException("You are already busy during that period!");
            }
            if (newDateStart.isEqual(currentRange[0]) || newDateStart.isEqual(currentRange[1])) {
                throw new ParseException("You are already busy during that period!");
            }
            if (newDateEnd.isAfter(currentRange[0]) && newDateEnd.isBefore(currentRange[1])) {
                throw new ParseException("You are already busy during that period!");
            }
            if (newDateEnd.isEqual(currentRange[0]) && newDateEnd.isEqual(currentRange[1])) {
                throw new ParseException("You are already busy during that period!");
            }
        }

        //Ensures user cannot create event in the past
        if (newDateStart.isBefore(LocalDateTime.now()) || newDateEnd.isBefore(LocalDateTime.now())) {
            throw new ParseException("You cannot create a historical event!");
        }

        MASTER_TIME.add(newRange);
        Collections.sort(MASTER_TIME, (range1, range2) -> {
            if (range1[0].isBefore(range2[0])) {
                return -1;
            } else if (range1[0].isAfter(range2[0])) {
                return 1;
            } else {
                return 0;
            }
        });
        return LocalDateTime.parse(trimmedDate, formatter);
    }

    /**
     * Checks if a new event can be added by checking if the TA already has a scheduled event
     * @param range LocalDateTime[]
     * @return whether the TA is busy or not
     */
    public static boolean isBusy(LocalDateTime[] range) {
        LocalDateTime start = range[0];
        LocalDateTime end = range[1];
        for (int i = 0; i < MASTER_TIME.size(); i++) {
            if (MASTER_TIME.size() == 0) {
                return false;
            }
            LocalDateTime[] currentRange = MASTER_TIME.get(i);
            if (start.isAfter(currentRange[0]) && start.isBefore(currentRange[1])) {
                return true;
            }
            if (start.isEqual(currentRange[0]) || start.isEqual(currentRange[1])) {
                return true;
            }
            if (end.isAfter(currentRange[0]) && end.isBefore(currentRange[1])) {
                return true;
            }
            if (end.isEqual(currentRange[0]) && end.isEqual(currentRange[1])) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if a new event with current time can be added by checking if the TA already has a scheduled event
     * @param range LocalDateTime[]
     */
    public static void makeBusy(LocalDateTime[] range) {
        MASTER_TIME.add(range);
    }


    /**
     * Parses localDateTime of an event and add it into MASTER_TIME
     * to ensure even if the user does not input a date field, the timing is registered as busy
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static void parseDefaultEventDate(LocalDateTime time, int hours) throws ParseException {
        LocalDateTime startTime = time;
        LocalDateTime endTime = time.plusHours(hours);
        MASTER_TIME.add(new LocalDateTime[]{startTime, endTime});
    }


    /**
     * Parses a {@code String filePath}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code filePath} is invalid.
     */
    public static File parseEventFile(String filePath) throws ParseException {
        //date can be null or empty as it is optional
        String trimmedFilePath = filePath.trim();
        File file = new File(trimmedFilePath);
        if (!file.exists()) {
            throw new ParseException("File not found!");
        }
        if (!trimmedFilePath.matches("^.*\\.pdf$")) {
            throw new ParseException("Only pdf files are allowed!");
        }
        return file;
    }

    /**
     * Parses a {@code String note}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseEventNote(String note) {
        //date can be null or empty as it is optional
        String trimmedNote = note.trim();
        return trimmedNote;
    }

    /**
     * Parses a {@code String note}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseNoteContent(String note) {
        String trimmedNote = note.trim();
        return trimmedNote;
    }

    /**
     * Parses a {@code String count}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code count} is invalid.
     */
    public static int parseRecurCount(String count) throws ParseException {
        requireNonNull(count);
        String trimmedCount = count.trim();
        if (!Event.isValidCount(trimmedCount)) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        return Integer.parseInt(trimmedCount);
    }

    /**
     * Parses {@code String performance} into a {@code Performance} object.
     */
    public static Performance parsePerformance(String performance) throws ParseException {
        requireNonNull(performance);
        String trimmedPerformance = performance.trim();
        if (!Performance.isValidPerformance(trimmedPerformance)) {
            throw new ParseException(Performance.MESSAGE_CONSTRAINTS);
        }
        return new Performance(trimmedPerformance);
    }

    /**
     * Parses a {@code String remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

}
