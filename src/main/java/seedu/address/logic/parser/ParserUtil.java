package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.MedicalCondition;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String TIME_FORMAT_INVALID = "Time format should be yyyy-MM-dd HHmm.";
    public static final String DATE_FORMAT_INVALID = "Date format should be yyyy-MM-dd";
    public static final String ADD_APPOINTMENT_COMMAND_INVALID = "Invalid add appointment command";

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
     * Parses {@code oneBasedIndex(s)} into an {@code Index} and returns List of Index.
     * Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static ArrayList<Index> parseindexs(String multiIndex, String separator) throws ParseException {
        //assume input is 2 3 4 5 6 ....
        String trimmedIndex = multiIndex.trim();
        ArrayList<Index> indices = new ArrayList<>();
        String[] tokens = trimmedIndex.split(separator);

        for (String token : tokens) {
            if (!StringUtil.isNonZeroUnsignedInteger(token)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            indices.add(Index.fromOneBased(Integer.parseInt(token)));
        }
        return indices;
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
     * Parses {@code Collection<String> time} into a {@code LocalDateTime}.
     */
    public static LocalDateTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(trimmedTime, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(TIME_FORMAT_INVALID);
        }
    }

    /**
     * Parses {@code String appointment} into a {@code Appointment}.
     */
    //"Next appointment time from: 2002-11-21T14:30 to: 2002-11-21T16:30"
    public static Appointment parseTimeFromAddressbook(String appointment) throws ParseException {
        requireNonNull(appointment);
        String trimmedAppointment = appointment.trim();
        int idxFrom = trimmedAppointment.indexOf("from:");
        int idxTo = trimmedAppointment.indexOf("to:");
        String startTimeStr = trimmedAppointment.substring(idxFrom + 5, idxTo).trim();
        String endTimeStr = trimmedAppointment.substring(idxTo + 3).trim();
        try {
            LocalDateTime startTime = LocalDateTime.parse(startTimeStr);
            LocalDateTime endTime = LocalDateTime.parse(endTimeStr);
            return new Appointment(startTime, endTime);
        } catch (DateTimeParseException e) {
            throw new ParseException(TIME_FORMAT_INVALID);
        }
    }

    /**
     * Parses {@code String date} into a {@code LocalDate}.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedTime = date.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(trimmedTime, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(DATE_FORMAT_INVALID);
        }
    }

    /**
     * Parses {@code String command} into a {@code String}.
     */
    // command: makeApp {index} /from {startTime} /to {endTime}
    public static String parseAddAppointmentCommand(String command) throws ParseException {
        int idxFrom = command.indexOf("/from");
        int idxTo = command.indexOf("/to");
        if (idxFrom == -1 || idxTo == -1 || idxTo <= idxFrom || idxFrom == 0) {
            throw new ParseException(ADD_APPOINTMENT_COMMAND_INVALID);
        }
        String index = command.substring(0, idxFrom).trim();
        if (index.isEmpty()) {
            throw new ParseException(ADD_APPOINTMENT_COMMAND_INVALID);
        }
        String startTime = command.substring(idxFrom + 5, idxTo).trim();
        String endTime = command.substring(idxTo + 3).trim();
        return index + ',' + startTime + '|' + endTime;
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
     * @param medicalCondition string message
     * @return MedicalCondition type
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static MedicalCondition parseMedicalCond(String medicalCondition) throws ParseException {
        requireNonNull(medicalCondition);
        if (!MedicalCondition.isValidCondition(medicalCondition)) {
            throw new ParseException(MedicalCondition.MESSAGE_CONSTRAINTS);
        }
        return new MedicalCondition(medicalCondition);
    }

    /**
     * Parses a {@code int age} into an {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code age} is invalid.
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        if (!Age.isValidAge(age)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(age);
    }

    /**
     * Parse day int.
     *
     * @param input the input
     * @return the int
     * @throws ParseException the parse exception
     */
    public static int parseDay(String input) throws ParseException {
        requireNonNull(input);
        //add is valid days
        input.trim();
        return Integer.parseInt(input);
    }

    /**
     * @param number the NRIC number of the patient's
     * @return the Nric
     * @throws ParseException the parse exception
     */
    public static Nric parseNric(String number) throws ParseException {
        requireNonNull(number);
        if (!Nric.isValidNumber(number)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(number);
    }
}
