package seedu.loyaltylift.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.loyaltylift.commons.core.index.Index;
import seedu.loyaltylift.commons.util.StringUtil;
import seedu.loyaltylift.logic.commands.ListCustomerCommand;
import seedu.loyaltylift.logic.commands.ListOrderCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerType;
import seedu.loyaltylift.model.customer.CustomerTypePredicate;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.customer.Points;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.OrderStatusPredicate;
import seedu.loyaltylift.model.order.Quantity;
import seedu.loyaltylift.model.order.StatusValue;
import seedu.loyaltylift.model.tag.Tag;

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
     * Parses {@code String quantity} into an {@code Quantity} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified quantity is invalid (not positive integer or larger than 1 million).
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        String trimmedQuantity = quantity.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        int parsedInt = Integer.parseInt(trimmedQuantity);
        if (parsedInt > 1000000) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        return new Quantity(parsedInt);
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
     * Parses {@code String customerType} into a {@code CustomerType}.
     */
    public static CustomerType parseCustomerType(String customerType) throws ParseException {
        requireNonNull(customerType);
        CustomerType type;
        try {
            type = CustomerType.fromUserFriendlyString(customerType);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ParseException(CustomerType.MESSAGE_FAIL_CONVERSION);
        }
        return type;
    }

    /**
     * Parses a {@code String points} into a {@code Points}.
     *
     * @throws ParseException if the given {@code points} is invalid.
     */
    public static Points parsePoints(String points) throws ParseException {
        requireNonNull(points);
        String trimmedPoints = points.trim();
        Integer integerTrimmedPoints;
        try {
            integerTrimmedPoints = Integer.valueOf(trimmedPoints);
        } catch (NumberFormatException e) {
            // integerTrimmedPoints is a string that cannot be parsed
            throw new ParseException(Points.MESSAGE_CONSTRAINTS);
        }
        if (!Points.isValidPoints(integerTrimmedPoints) || trimmedPoints.compareTo("-0") == 0) {
            // integerTrimmedPoints is an integer that is not within the range of 0 to 999999
            throw new ParseException(Points.MESSAGE_CONSTRAINTS);
        }
        return new Points(integerTrimmedPoints, integerTrimmedPoints);
    }

    /**
     * Parses a {@code String points} into a {@code Integer}.
     *
     * @throws ParseException if the given {@code points} is invalid.
     */
    public static Integer parseAddPoints(String points) throws ParseException {
        requireNonNull(points);
        String trimmedPoints = points.trim();
        Integer integerTrimmedPoints;
        try {
            integerTrimmedPoints = Integer.valueOf(trimmedPoints);
        } catch (NumberFormatException e) {
            // integerTrimmedPoints is a string that cannot be parsed
            throw new ParseException(Points.MESSAGE_CONSTRAINTS);
        }

        if (!Points.isValidAddition(integerTrimmedPoints)) {
            // integerTrimmedPoints is an integer that is not within the range of 0 to 999999
            throw new ParseException(Points.MESSAGE_CONSTRAINTS_ADDITION);
        }
        return integerTrimmedPoints;
    }

    /**
     * Parses a {@code String note} into an {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Note parseNote(String note) {
        requireNonNull(note);
        String trimmedNote = note.trim();
        return new Note(trimmedNote);
    }

    /**
     * Parses a {@code String statusValue} into a {@code StatusValue}.
     * @throws ParseException if the given {@code statusValue} is invalid.
     */
    public static StatusValue parseStatusValue(String statusValue) throws ParseException {
        requireNonNull(statusValue);
        try {
            return StatusValue.fromString(statusValue.trim());
        } catch (IllegalArgumentException e) {
            throw new ParseException(StatusValue.MESSAGE_FAIL_CONVERSION);
        }
    }

    /**
     * Parses a {@code String sortOption} into a {@code Comparator<Customer>}.
     * @throws ParseException if the given {@code sortOption} is invalid.
     */
    public static Comparator<Customer> parseCustomerSortOption(String sortOption) throws ParseException {
        requireNonNull(sortOption);
        String trimmedSortOption = sortOption.trim().toUpperCase();
        switch (trimmedSortOption) {
        case "NAME":
            return Customer.SORT_NAME;
        case "POINTS":
            return Customer.SORT_POINTS;
        default:
            throw new ParseException(ListCustomerCommand.MESSAGE_INVALID_SORT);
        }
    }

    /**
     * Parses a {@code String filterOption} into a {@code Predicate<Customer>}.
     * @throws ParseException if the given {@code filterOption} is invalid.
     */
    public static Predicate<Customer> parseCustomerFilterOption(String filterOption) throws ParseException {
        requireNonNull(filterOption);
        String trimmedFilterOption = filterOption.trim().toUpperCase();
        switch (trimmedFilterOption) {
        case "MARKED":
            return Customer.FILTER_SHOW_MARKED;
        case "IND":
        case "ENT":
            return new CustomerTypePredicate(parseCustomerType(trimmedFilterOption));
        default:
            throw new ParseException(ListCustomerCommand.MESSAGE_INVALID_FILTER);
        }
    }

    /**
     * Parses a {@code String sortOption} into a {@code Comparator<Order>}.
     * @throws ParseException if the given {@code sortOption} is invalid.
     */
    public static Comparator<Order> parseOrderSortOption(String sortOption) throws ParseException {
        requireNonNull(sortOption);
        String trimmedSortOption = sortOption.trim().toUpperCase();
        switch (trimmedSortOption) {
        case "CREATED":
            return Order.SORT_CREATED_DATE;
        case "NAME":
            return Order.SORT_NAME;
        case "STATUS":
            return Order.SORT_STATUS;
        default:
            throw new ParseException(ListOrderCommand.MESSAGE_INVALID_SORT);
        }
    }

    /**
     * Parses a {@code String filterOption} into a {@code Predicate<Order>}.
     * @throws ParseException if the given {@code filterOption} is invalid.
     */
    public static Predicate<Order> parseOrderFilterOption(String filterOption) throws ParseException {
        requireNonNull(filterOption);
        String trimmedFilterOption = filterOption.trim();
        try {
            StatusValue status = parseStatusValue(trimmedFilterOption);
            return new OrderStatusPredicate(status);
        } catch (ParseException e) {
            throw new ParseException(ListOrderCommand.MESSAGE_INVALID_FILTER);
        }
    }

}
