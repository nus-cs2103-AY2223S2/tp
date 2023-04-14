package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Homework;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    //@@author Yufannn-reused
    //Reused from https://github.com/RussellDash332/ip/blob/master/src/main/java/stashy/parser/Parser.java
    //with minor modification, it is a pretty good way to organise and extend the acceptable date format.
    private static final String[] ACCEPTABLE_DATETIME_FORMATS = {
        "MMM dd yyyy HHmm", "MMM dd yyyy HH:mm",
        "yyyy-MM-dd'T'HH:mm", "dd/MM/yyyy HHmm",
        "dd/MM/yyyy HH:mm", "yyyy/MM/dd HHmm",
        "yyyy/MM/dd HH:mm", "yyyy/MM/dd'T'HHmm",
        "yyyy/MM/dd'T'HH:mm", "yyyy-MM-dd HHmm",
        "yyyy-MM-dd HH:mm", "dd MMM yyyy HHmm",
        "dd MMM yyyy HH:mm", "MMM dd, yyyy HHmm",
        "MMM dd, yyyy HH:mm"
    };
    //@@author

    //@@author NBQian-reused
    //Reused from https://github.com/Yufannnn/ip/blob/master/src/main/java/duke/parser/TimeHandler.java
    //with minor modification, it is a pretty good way to organise and extend the acceptable date format.
    private static final String[] ACCEPTABLE_DATE_FORMATS = {
        "MMM dd yyyy", "yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd",
        "dd MMM yyyy", "MMM dd, yyyy", "dd-mm-yyyy"
    };
    //@@author

    /**
     * Parses a string to a LocalDateTime object using the acceptable date time formats defined
     *
     * @param date The date string to be parsed
     * @return The parsed LocalDate object
     * @throws ParseException if the string does not match any supported date formats
     */
    public static LocalDate parseDate(String date) throws ParseException {
        for (String dateFormat : ACCEPTABLE_DATE_FORMATS) {
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
            } catch (Exception e) {
                // Go to the next dateFormat
            }
        }
        throw new ParseException("Invalid date format. Please use one of the following formats:\n"
            + String.join("\n", ACCEPTABLE_DATE_FORMATS));
    }

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
        if (!Tag.isValidTagLength(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_TAG_LENGTH);
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

    //@@author Yufannnn-reused
    //Reused from https://github.com/wweqg/ip/blob/master/src/main/java/duke/parser/Parser.java
    //with minor modification, it is a pretty clean and concise regular expression for general instructions
    /**
     * Parses a string to a LocalDateTime object using the acceptable date time formats defined
     * in {@link #ACCEPTABLE_DATETIME_FORMATS}.
     *
     * @param date The date string to be parsed
     * @return The parsed LocalDateTime object
     * @throws ParseException if the date string does not match any of the acceptable date time formats
     */
    public static LocalDateTime parseDeadline(String date) throws ParseException {
        for (String dateTimeFormat : ACCEPTABLE_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(date,
                        DateTimeFormatter.ofPattern(dateTimeFormat));
            } catch (Exception e) {
                // Go to the next dateTimeFormat
            }
        }

        throw new ParseException("Invalid date format. Please use one of the following formats:\n"
                + String.join("\n", ACCEPTABLE_DATETIME_FORMATS));
    }

    /**
     * Parses a string to a LocalDateTime object using the acceptable date time formats defined
     * in {@link #ACCEPTABLE_DATETIME_FORMATS}.
     *
     * @param date The date string to be parsed
     * @return The parsed LocalDateTime object
     * @throws ParseException if the date string does not match any of the acceptable date time formats
     */
    public static LocalDateTime parseStartTime(String date) throws ParseException {
        for (String dateTimeFormat : ACCEPTABLE_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(date,
                    DateTimeFormatter.ofPattern(dateTimeFormat));
            } catch (Exception e) {
                // Go to the next dateTimeFormat
            }
        }

        throw new ParseException("Invalid date format. Please use one of the following formats:\n"
            + String.join("\n", ACCEPTABLE_DATETIME_FORMATS));
    }
    /**
     * Parses a string to a LocalDateTime object using the acceptable date time formats defined
     * in {@link #ACCEPTABLE_DATETIME_FORMATS}.
     *
     * @param date The date string to be parsed
     * @return The parsed LocalDateTime object
     * @throws ParseException if the date string does not match any of the acceptable date time formats
     */
    public static LocalDateTime parseEndTime(String date) throws ParseException {
        for (String dateTimeFormat : ACCEPTABLE_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(date,
                    DateTimeFormatter.ofPattern(dateTimeFormat));
            } catch (Exception e) {
                // Go to the next dateTimeFormat
            }
        }

        throw new ParseException("Invalid date format. Please use one of the following formats:\n"
            + String.join("\n", ACCEPTABLE_DATETIME_FORMATS));
    }

    /**
     * Parses a string to a LocalDateTime object using the acceptable date time formats defined
     *
     * @param status The status string to be parsed
     * @return The parsed Boolean object
     * @throws ParseException if the status string does not match any of the acceptable status
     */
    public static Boolean parseStatus(String status) throws ParseException {
        if (status.equalsIgnoreCase(Homework.Status.COMPLETED.toString())) {
            return true;
        } else if (status.equalsIgnoreCase(Homework.Status.PENDING.toString())) {
            return false;
        } else {
            throw new ParseException("Invalid status. Please use either 'completed' or 'pending'.");
        }
    }

    /**
     * Checks if the deadline is unique and not null.
     *
     * @param argMultimap the argument multimap to check for deadline.
     * @throws ParseException if the deadline is not unique or null.
     */
    public static void checkUniqueNotNUllDeadline(ArgumentMultimap argMultimap) throws ParseException {
        // only one deadline keyword is allowed
        if (argMultimap.getAllValues(PREFIX_DEADLINE).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_ONLY_ONE_DEADLINE));
        }
        // it cannot be empty
        if (argMultimap.getValue(PREFIX_DEADLINE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_EMPTY_DEADLINE));
        }
    }

    /**
     * Checks if the name is unique and not null.
     *
     * @param argMultimap the argument multimap to check for name.
     * @throws ParseException if the name is not unique or null.
     */
    public static void checkUniqueNotNUllName(ArgumentMultimap argMultimap) throws ParseException {
        // only one name keyword is allowed
        if (argMultimap.getAllValues(PREFIX_NAME).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_ONLY_ONE_STUDENT));
        }
        // it cannot be empty
        if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_EMPTY_STUDENT));
        }
    }

    /**
     * Checks if the homework name is unique and not null.
     *
     * @param argMultimap the argument multimap to check for homework name.
     * @throws ParseException if the homework name is not unique or null.
     */
    public static void checkUniqueNotNUllHomework(ArgumentMultimap argMultimap) throws ParseException {
        // only one homework keyword is allowed
        if (argMultimap.getAllValues(PREFIX_HOMEWORK).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_ONLY_ONE_HOMEWORK));
        }
        // it cannot be empty
        if (argMultimap.getValue(PREFIX_HOMEWORK).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_EMPTY_HOMEWORK));
        }
    }

    /**
     * Checks if the index is unique and not null.
     *
     * @param argMultimap the argument multimap to check for index.
     * @throws ParseException if the index is not unique or null.
     */
    public static void checkUniqueNotNullIndex(ArgumentMultimap argMultimap) throws ParseException {
        // only one index keyword is allowed
        if (argMultimap.getAllValues(PREFIX_INDEX).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_ONLY_ONE_INDEX));
        }
        // it cannot be empty
        if (argMultimap.getValue(PREFIX_INDEX).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_EMPTY_INDEX));
        }
    }
    /**
     * Checks if at most one lesson prefix is present.
     * @param argumentMultimap the argument multimap to check for lesson.
     * @throws ParseException if more than one lesson prefix is present.
     */
    public static void checkMaxOneLesson(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_LESSON).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_LESSON));
        }
    }
    /**
     * Checks if at most one student name prefix is present.
     * @param argumentMultimap the argument multimap to check for name.
     * @throws ParseException if more than one name prefix is present.
     */
    public static void checkMaxOneEmail(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_EMAIL).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_EMAIL));
        }
    }
    /**
     * Checks if at most one start time prefix is present.
     * @param argumentMultimap the argument multimap to check for start time.
     * @throws ParseException if more than one start time prefix is present.
     */
    public static void checkMaxOneStartTime(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_STARTTIME).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_STARTTIME));
        }
    }
    /**
     * Checks if at most one end time prefix is present.
     * @param argumentMultimap the argument multimap to check for end time.
     * @throws ParseException if more than one end time prefix is present.
     */
    public static void checkMaxOneEndTime(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_ENDTIME).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_ENDTIME));
        }
    }
    /**
     * Checks if at most one address prefix is present.
     * @param argumentMultimap the argument multimap to check for address.
     * @throws ParseException if more than one address prefix is present.
     */
    public static void checkMaxOneAddress(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_ADDRESS).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_ADDRESS));
        }
    }
    /**
     * Checks if at most one phone prefix is present.
     * @param argumentMultimap the argument multimap to check for phone.
     * @throws ParseException if more than one phone prefix is present.
     */
    public static void checkMaxOnePhone(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_PHONE).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_PHONE));
        }
    }
    /**
     * Checks if at most one done prefix is present.
     * @param argumentMultimap the argument multimap to check for done.
     * @throws ParseException if more than one done prefix is present.
     */
    public static void checkMaxOneDone(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_DONE).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_DONE));
        }
    }
    /**
     * Checks if at most one date prefix is present.
     * @param argumentMultimap the argument multimap to check for date.
     * @throws ParseException if more than one date prefix is present.
     */
    public static void checkMaxOneDate(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_DATE).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_DATE));
        }
    }
    /**
     * Checks if at most one grade prefix is present.
     * @param argumentMultimap the argument multimap to check for grade.
     * @throws ParseException if more than one grade prefix is present.
     */
    public static void checkMaxOneGrade(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_GRADE).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_GRADE));
        }
    }
    /**
     * Checks if at most one weight prefix is present.
     * @param argumentMultimap the argument multimap to check for weight.
     * @throws ParseException if more than one weight prefix is present.
     */
    public static void checkMaxOneWeight(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_WEIGHT).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_WEIGHTAGE));
        }
    }
    /**
     * Checks if at most one exam prefix is present.
     * @param argumentMultimap the argument multimap to check for exam.
     * @throws ParseException if more than one exam prefix is present.
     */
    public static void checkMaxOneExam(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getAllValues(PREFIX_EXAM).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Messages.MESSAGE_ONLY_ONE_EXAM));
        }
    }


    /**
     * Checks if the index is unique and not null.
     *
     * @param argMultimap the argument multimap to check for index.
     * @throws ParseException if the index is not unique or null.
     */
    public static void checkUniqueNotNullStatus(ArgumentMultimap argMultimap) throws ParseException {
        // only one index keyword is allowed
        if (argMultimap.getAllValues(PREFIX_STATUS).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_ONLY_ONE_STATUS));
        }
        // it cannot be empty
        if (argMultimap.getValue(PREFIX_STATUS).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_EMPTY_STATUS));
        }
    }

    /**
     * Checks if the index is unique and not null.
     *
     * @param argMultimap the argument multimap to check for index.
     * @throws ParseException if the index is not unique or null.
     */
    public static void checkUniqueHomework(ArgumentMultimap argMultimap) throws ParseException {
        // only one homework keyword is allowed
        if (argMultimap.getAllValues(PREFIX_HOMEWORK).size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_ONLY_ONE_HOMEWORK));
        }
    }

    /**
     * Checks if the homework name is not null.
     *
     * @param homework the homework name to check for null.
     * @throws ParseException if the homework name is null.
     */
    public static void checkNotNullHomework(String homework) throws ParseException {
        // it cannot be empty
        if (homework.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Messages.MESSAGE_EMPTY_HOMEWORK));
        }
    }

    /**
     * Checks if the name is not null.
     *
     * @param names the name to check for null.
     * @throws ParseException if the name is null.
     */
    public static void checkNotNullNames(List<String> names) throws ParseException {
        for (String name : names) {
            if (name.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        Messages.MESSAGE_EMPTY_STUDENT));
            }
        }
    }

    /**
     * parses a string and returns a double representing the percentage weightage
     * @param weight string to parse
     * @return Double percentage
     * @throws ParseException
     */
    public static double parseWeightage(String weight) throws ParseException {
        if (!weight.matches("^[0-9]+(?:\\.[0-9]+)?%?$")) {
            throw new ParseException("Weightage is in an invalid format!");
        }
        weight = weight.replace("%", ""); //removes % sign if it exists
        Double res = null;
        try {
            res = Double.parseDouble(weight);
        } catch (NumberFormatException e) {
            throw new ParseException("unexpected error occurred when parsing weightage", e);
        }
        return res;
    }

    /**
     * parses a string and returns a Grade object representing it
     * @param grade string to parse
     * @return Grade representationn
     * @throws ParseException
     */
    public static Grade parseGrade(String grade) throws ParseException {
        if (!grade.matches("^[0-9]+/[0-9]+$")) {
            throw new ParseException("Grade is in an invalid format!");
        }
        Grade res;
        try {
            res = new Grade(Double.parseDouble(grade.split("/")[0]), Double.parseDouble(grade.split("/")[1]));
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
        return res;
    }

}
