package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;
import seedu.address.model.category.MiscellaneousCategory;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.model.expense.RecurringExpenseType;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.CommandUtility;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * Enumerates possibilities for timespans indicated: week, month, year.
     */
    public enum Timespan {
        WEEK("Week"),
        MONTH("Month"),
        YEAR("Year"),
        ALL("All");

        private final String stringRep;
        Timespan(String stringRep) {
            this.stringRep = stringRep;
        }
        // Return the string representation of the given timeSpan
        @Override
        public String toString() {
            return stringRep;
        }
    }


    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be
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
     * Parses a {@code String expenseName} into a String.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code expenseName} is invalid.
     */
    public static String parseExpenseName(String expenseName) throws ParseException {
        requireNonNull(expenseName);
        String trimmedName = expenseName.trim();
        if (trimmedName.isEmpty()) {
            throw new ParseException("The expense name should not be empty!");
        }
        return trimmedName;
    }

    /**
     * Parses {@code price} into an {@code Price} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the specified price is invalid (not non-negative and numeric).
     */
    public static Price parsePrice(String price) throws ParseException {
        String trimmedPrice = price.trim();
        if (!Price.isValidPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
        return new Price(trimmedPrice);
    }

    /**
     * Parses {@code categoryName} and creates a {@code Category} instance and returns it.
     * Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified categoryName does not exist
     */
    public static Category parseCategory(String categoryName) throws ParseException {
        String trimmedCategoryName = categoryName.trim();
        if (trimmedCategoryName.isEmpty() || !Category.isValidCategoryName(trimmedCategoryName)) {
            throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
        if (trimmedCategoryName.equals("miscellaneous")) {
            return new MiscellaneousCategory();
        }
        return new UserDefinedCategory(trimmedCategoryName, "");
    }

    /**
     * Parses a {@code String category} into a {@code UserDefinedCategory}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code category} is invalid.
     */
    public static UserDefinedCategory parseCategory(String category, String summary) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        if (!Category.isValidCategoryName(category)) {
            throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
        return new UserDefinedCategory(trimmedCategory, summary);
    }

    /**
     * Parses {@code dateString} into a {@code LocalDate} instance and returns it.
     * @throws ParseException if the date could not be parsed
     */
    public static LocalDate parseDate(String dateString) throws ParseException {
        String trimmedDate = dateString.trim();
        LocalDate parsedDate;
        try {
            parsedDate = CommandUtility.parseDateFromUserInput(trimmedDate);
        } catch (IllegalArgumentException e) {
            throw new ParseException("Date should be of the form D/M/YY");
        }
        return parsedDate;
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
     * Parses {@code String timespan} into a {@code LocalDate}.
     */
    public static Timespan parseTimespan(String timespan) throws ParseException {
        assert timespan != null : "input should not be null";
        requireNonNull(timespan);
        String trimmedTimespan = timespan.trim();
        LocalDate now = LocalDate.now();
        if (trimmedTimespan.equals("week") || trimmedTimespan.equals("w")) {
            return Timespan.WEEK;
        }
        if (trimmedTimespan.equals("month") || trimmedTimespan.equals("m")) {
            return Timespan.MONTH;
        }
        if (trimmedTimespan.equals("year") || trimmedTimespan.equals("y")) {
            return Timespan.YEAR;
        }
        throw new ParseException("Not a valid date format (week, month, year)");
    }

    /**
     * Parses {@code String timespan} into a {@code RecurringExpenseType}.
     */
    public static RecurringExpenseType parseTimeSpanRecurringExpense(String timespan) throws ParseException {
        assert timespan != null : "input should not be null";
        requireNonNull(timespan);
        String trimmedTimespan = timespan.trim();
        if (trimmedTimespan.equals("day") || trimmedTimespan.equals("d")) {
            return RecurringExpenseType.DAILY;
        }
        if (trimmedTimespan.equals("week") || trimmedTimespan.equals("w")) {
            return RecurringExpenseType.WEEKLY;
        }
        if (trimmedTimespan.equals("month") || trimmedTimespan.equals("m")) {
            return RecurringExpenseType.MONTHLY;
        }
        if (trimmedTimespan.equals("year") || trimmedTimespan.equals("y")) {
            return RecurringExpenseType.YEARLY;
        }
        throw new ParseException("Not a valid date format (day, week, month, year)");
    }

    /**
     * Get earliest {@code LocalDate} within the given {@code Timespan}.
     * @param t {@code Timespan} of week, month or year
     * @return LocalDate of the earliest date in this timespan
     */
    public static LocalDate getDateByTimespan(Timespan t) {
        switch (t) {
        case WEEK:
            return LocalDate.now().with(DayOfWeek.MONDAY);
        case MONTH:
            return LocalDate.now().withDayOfMonth(1);
        case YEAR:
            return LocalDate.now().withDayOfYear(1);
        default:
            break;
        }
        assert false : "Line should not be reached";
        return null;
    }
}
