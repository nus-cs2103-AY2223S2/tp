package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.Field;
import seedu.address.logic.commands.SortCommand.Order;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Discharge;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Status;
import seedu.address.model.ward.Capacity;
import seedu.address.model.ward.Ward;
import seedu.address.model.ward.WardName;

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
     * Parses a {@code String nric} into a {@code Nric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        if (!Nric.isValidNric(trimmedNric)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(trimmedNric);
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
     * Parses a {@code String status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(trimmedStatus);
    }

    /**
     * Parses a {@code String ward} into a {@code Ward}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ward} is invalid.
     */
    public static WardName parseWardName(String ward) throws ParseException {
        requireNonNull(ward);
        String trimmedWard = ward.trim();
        if (!Ward.isValidWard(trimmedWard)) {
            throw new ParseException(Ward.MESSAGE_CONSTRAINTS);
        }
        return new WardName(trimmedWard);
    }

    /**
     * Parses a {@code String ward} into a {@code Ward}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ward} is invalid.
     */
    public static Capacity parseCapacity(String capacity) throws ParseException {
        requireNonNull(capacity);
        String trimmedCapacity = capacity.trim();
        if (!Capacity.isValidCapacity(trimmedCapacity)) {
            throw new ParseException(Capacity.MESSAGE_CONSTRAINTS);
        }
        return new Capacity(trimmedCapacity);
    }

    /**
     * Parses a {@code String discharge} into a {@code Discharge}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code discharge} is invalid.
     */
    public static Discharge parseDischarge(String discharge) throws ParseException {
        requireNonNull(discharge);
        String trimmedDischarge = discharge.trim();
        if (!Discharge.isValidDischarge(trimmedDischarge)) {
            throw new ParseException(Discharge.MESSAGE_CONSTRAINTS);
        }
        return new Discharge(trimmedDischarge);
    }


    /**
     * Parses {@code String arg} into a {@code Order}.
     *
     * @throws ParseException if the given {@code arg} is invalid.
     */
    public static Order parseSortOrder(String arg) throws ParseException {
        switch (arg.toUpperCase()) {
        case "ASC":
            return Order.ASC;
        case "DESC":
            return Order.DESC;
        default:
            throw new ParseException(SortCommand.MESSAGE_UNKNOWN_ORDER_KEYWORD);
        }
    }

    /**
     * Parses {@code String arg} into a {@code Type}.
     *
     * @throws ParseException if the given {@code arg} is invalid.
     */
    public static Field parseSortType(String arg) throws ParseException {
        switch (arg.toUpperCase()) {
        case "NAME":
            return Field.NAME;
        case "STATUS":
            return Field.STATUS;
        case "WARD":
            return Field.WARD;
        case "DISCHARGE":
            return Field.DISCHARGE;
        default:
            throw new ParseException(SortCommand.MESSAGE_UNKNOWN_TYPE_KEYWORD);
        }
    }
}
