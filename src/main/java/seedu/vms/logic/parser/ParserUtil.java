package seedu.vms.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_MAIN_KEYWORD;
import static seedu.vms.commons.core.Messages.MESSAGE_INVALID_SUB_KEYWORD;
import static seedu.vms.commons.core.Messages.MESSAGE_KEYWORD_IS_EMPTY;
import static seedu.vms.model.keyword.Keyword.MAIN_APPOINTMENT_STRING;
import static seedu.vms.model.keyword.Keyword.MAIN_BASIC_STRING;
import static seedu.vms.model.keyword.Keyword.MAIN_EXIT_STRING;
import static seedu.vms.model.keyword.Keyword.MAIN_HELP_STRING;
import static seedu.vms.model.keyword.Keyword.MAIN_KEYWORD_STRING;
import static seedu.vms.model.keyword.Keyword.MAIN_PATIENT_STRING;
import static seedu.vms.model.keyword.Keyword.MAIN_VACCINATION_STRING;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import seedu.vms.commons.core.Retriever;
import seedu.vms.commons.core.index.Index;
import seedu.vms.commons.util.StringUtil;
import seedu.vms.logic.parser.exceptions.ParseException;
import seedu.vms.model.GroupName;
import seedu.vms.model.keyword.Keyword;
import seedu.vms.model.patient.BloodType;
import seedu.vms.model.patient.Dob;
import seedu.vms.model.patient.Name;
import seedu.vms.model.patient.Phone;
import seedu.vms.model.vaccination.Requirement;
import seedu.vms.model.vaccination.VaxType;

/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATE = "Date is of an invalid format";
    public static final String MESSAGE_BLANK_ELEMENT = "Trailing or leading delimiters are not allowed";
    public static final String MESSAGE_BLANK_ARGUMENT = "Argument is blank";

    public static final String KEYWORD_EMPTY_LIST = "<EMPTY>";

    public static final String RETRIEVER_TYPE_NAME = "NAME";
    public static final String RETRIEVER_TYPE_INDEX = "INDEX";

    private static final String DEFAULT_DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}";
    private static final String FULL_DATE_REGEX = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}";
    private static final String DATE_ONLY_REGEX = "\\d{4}-\\d{1,2}-\\d{1,2}";

    private static final String FULL_DATE_PATTERN = "yyyy-M-d HHmm";

    private static final String DELIMITER_PART = "::";
    private static final String DELIMITER_LIST = ",";

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
     * Parses a {@code String dob} into an {@code Dob}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dob} is invalid.
     */
    public static Dob parseDob(String dob) throws ParseException {
        requireNonNull(dob);
        String trimmedDob = dob.trim();
        LocalDateTime date = parseDate(trimmedDob);
        if (!Dob.isValidDob(date)) {
            throw new ParseException(Dob.MESSAGE_CONSTRAINTS);
        }
        return new Dob(date);
    }

    /**
     * Parses a {@code String bloodType} into an {@code BloodType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code bloodType} is invalid.
     */
    public static BloodType parseBloodType(String bloodType) throws ParseException {
        requireNonNull(bloodType);
        String trimmedBloodType = bloodType.trim();
        if (!BloodType.isValidBloodType(trimmedBloodType)) {
            throw new ParseException(BloodType.MESSAGE_CONSTRAINTS);
        }
        return new BloodType(trimmedBloodType);
    }

    /**
     * Parses a String date to a {@code LocalDateTime}.
     * <p>
     * The following formats are supported:
     * <ul>
     * <li>{@code yyyy-MM-dd'T'hh:mm}
     * <li>{@code yyyy-M-d hhmm}
     * </ul>
     *
     * @param dateString - the String date to parse.
     * @return the parsed {@code LocalDateTime}.
     * @throws ParseException if the given String cannot be parsed.
     */
    public static LocalDateTime parseDate(String dateString) throws ParseException {
        try {
            return parseCustomDate(dateString);
        } catch (DateTimeParseException dateParseEx) {
            throw new ParseException(dateParseEx.getMessage());
        }
    }

    /**
     * Checks if given string is a valid datetime format
     *
     * @param dateString - the String to check.
     * @return True if {@code dateString} is a valid datetime format
     */
    public static boolean isValidDateTimeFormat(String dateString) {
        try {
            parseDate(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static LocalDateTime parseCustomDate(String dateString) throws ParseException {
        if (dateString.matches(DEFAULT_DATE_REGEX)) {
            // yyyy-MM-dd'T'hh:mm format
            return LocalDateTime.parse(dateString);
        } else if (dateString.matches(FULL_DATE_REGEX)) {
            // yyyy-MM-dd hhmm format
            return LocalDateTime.parse(
                    dateString,
                    DateTimeFormatter.ofPattern(FULL_DATE_PATTERN));
        } else if (dateString.matches(DATE_ONLY_REGEX)) {
            return LocalDateTime.parse(
                    dateString + " 0000",
                    DateTimeFormatter.ofPattern(FULL_DATE_PATTERN));
        }
        throw new ParseException(MESSAGE_INVALID_DATE);
    }

    /*
     * ========================================================================
     * Vaccination
     * ========================================================================
     */

    /**
     * Parses a requirement.
     */
    public static Requirement parseReq(String reqString) throws ParseException {
        List<String> parts = parseParts(reqString);
        if (parts.size() != 2) {
            throw new ParseException("Requirements require 2 and only 2 parts");
        }
        Requirement.RequirementType reqType = parseReqType(parts.get(0));
        HashSet<GroupName> reqSet = new HashSet<>(parseGroups(parts.get(1)));
        if (!Requirement.isValidReqSet(reqSet)) {
            throw new ParseException(Requirement.MESSAGE_CONSTRAINTS);
        }
        return new Requirement(reqType, reqSet);
    }

    /**
     * Parses a requirement type.
     */
    public static Requirement.RequirementType parseReqType(String reqTypeString)
                throws ParseException {
        try {
            return Requirement.RequirementType.valueOf(reqTypeString.toUpperCase());
        } catch (IllegalArgumentException illArgEx) {
            throw new ParseException("Unknown requirement type", illArgEx);
        }
    }


    /**
     * Parses a vaccination retriever.
     */
    public static Retriever<String, VaxType> parseVaxRetriever(String inputString)
                throws ParseException {
        List<String> argParts = parseParts(inputString);
        if (argParts.size() > 2) {
            throw new ParseException("Only a maximum of 2 parts is allowed");
        }

        String retrieverType = RETRIEVER_TYPE_NAME;
        String retrieverValue = argParts.get(0);

        if (retrieverValue.isBlank()) {
            throw new ParseException("Retriever value cannot be empty");
        }

        if (argParts.size() > 1) {
            // if 2 parts
            retrieverType = argParts.get(0);
            retrieverValue = argParts.get(1);
        } else if (retrieverValue.matches("\\d+")) {
            // if one part and is digits
            retrieverType = RETRIEVER_TYPE_INDEX;
        }

        return formVaxRetriever(retrieverType.strip(), retrieverValue.strip());
    }


    private static Retriever<String, VaxType> formVaxRetriever(String retrieverType, String retrieverValue)
                throws ParseException {
        switch (retrieverType.toUpperCase()) {
        case RETRIEVER_TYPE_NAME:
            return Retriever.of(retrieverValue);

        case RETRIEVER_TYPE_INDEX:
            Index index = parseIndex(retrieverValue);
            return Retriever.of(index.getZeroBased());

        default:
            throw new ParseException("Unknown retriever type");
        }
    }

    /*
     * ========================================================================
     * Keyword
     * ========================================================================
     */

    /**
     * Parses a {@code String keyword} into a {@code String keyword}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code keyword} is invalid.
     */
    public static String parseKeyword(String keyword) throws ParseException {
        requireNonNull(keyword);
        String trimmedKeyword = keyword.strip();
        if (trimmedKeyword.isBlank()) {
            throw new ParseException(MESSAGE_KEYWORD_IS_EMPTY);
        }
        if (!Keyword.isNotMainKeyword(trimmedKeyword)) {
            throw new ParseException(String.format(MESSAGE_INVALID_SUB_KEYWORD,
            MAIN_APPOINTMENT_STRING, MAIN_PATIENT_STRING, MAIN_VACCINATION_STRING,
            MAIN_HELP_STRING, MAIN_BASIC_STRING, MAIN_EXIT_STRING, MAIN_KEYWORD_STRING));
        }

        return trimmedKeyword;
    }

    /**
     * Parses a {@code String keyword} into a {@code String keyword}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code keyword} is invalid.
     */
    public static String parseDeleteKeyword(String keyword) throws ParseException {
        requireNonNull(keyword);

        String trimmedKeyword = keyword.strip();
        if (trimmedKeyword.isBlank()) {
            throw new ParseException(MESSAGE_KEYWORD_IS_EMPTY);
        }

        return trimmedKeyword;
    }

    /**
     * Parses a {@code String keyword} into a {@code String keyword}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code keyword} is invalid.
     */
    public static String parseMainKeyword(String mainKeyword) throws ParseException {
        requireNonNull(mainKeyword);
        String trimmedMainKeyword = mainKeyword.strip();
        if (!Keyword.isValidMainKeyword(trimmedMainKeyword)) {
            throw new ParseException(String.format(MESSAGE_INVALID_MAIN_KEYWORD,
            MAIN_APPOINTMENT_STRING, MAIN_PATIENT_STRING, MAIN_VACCINATION_STRING));
        }
        return trimmedMainKeyword;
    }

    /*
     * ========================================================================
     * Basic
     * ========================================================================
     */

    /**
     * Parses an integer.
     */
    public static int parseInteger(String intString) throws ParseException {
        try {
            return Integer.parseInt(intString);
        } catch (NumberFormatException numEx) {
            throw new ParseException("Must be an integer between -2147483647 to 2147483647");
        }
    }

    /**
     * Parses the argument into parts according to {@link #DELIMITER_PART}
     * delimiter pattern.
     */
    public static List<String> parseParts(String arg) throws ParseException {
        return splitArgs(arg, DELIMITER_PART);
    }

    /**
     * Parses the argument into a list according to {@link #DELIMITER_LIST}
     * delimiter pattern.
     */
    public static List<String> parseList(String arg) throws ParseException {
        if (arg.strip().toUpperCase().equals(KEYWORD_EMPTY_LIST)) {
            return List.of();
        }
        return splitArgs(arg, DELIMITER_LIST);
    }

    private static List<String> splitArgs(String arg, String delimiter) throws ParseException {
        if (arg.isBlank()) {
            throw new ParseException(MESSAGE_BLANK_ARGUMENT);
        }
        arg = " " + arg + " ";
        List<String> rawArgs = List.of(arg.split(delimiter));
        ArrayList<String> splitArgs = new ArrayList<>();
        for (String rawArg : rawArgs) {
            if (rawArg.isBlank()) {
                throw new ParseException(MESSAGE_BLANK_ELEMENT);
            }
            splitArgs.add(rawArg.strip());
        }
        return splitArgs;
    }

    /**
     * Parses group names.
     *
     * @param name - name to parse.
     * @throws ParseException if the name cannot be parsed.
     */
    public static GroupName parseGroupName(String name) throws ParseException {
        requireNonNull(name);
        if (!GroupName.isValidName(name)) {
            throw new ParseException(GroupName.MESSAGE_CONSTRAINTS);
        }
        return new GroupName(name);
    }

    /**
     * Parses a String representing a list of groups.
     *
     * @throws ParseException if any of the groups cannot be parsed.
     */
    public static List<GroupName> parseGroups(String args) throws ParseException {
        List<String> grpStrings = parseList(args);

        ArrayList<GroupName> groups = new ArrayList<>();
        for (String grpString : grpStrings) {
            groups.add(parseGroupName(grpString));
        }

        return groups;
    }

    /**
     * Parses a String representing a list of groups.
     *
     * @throws ParseException if any of the groups cannot be parsed.
     */
    public static HashSet<GroupName> parseGroups(Collection<String> args) throws ParseException {
        requireNonNull(args);

        final HashSet<GroupName> groups = new HashSet<>();
        for (String grpString : args) {
            groups.addAll(parseGroups(grpString));
        }

        return groups;
    }


    /**
     * Parses a String to a boolean.
     */
    public static boolean parseBoolean(String input) {
        return Boolean.parseBoolean(input);
    }
}
