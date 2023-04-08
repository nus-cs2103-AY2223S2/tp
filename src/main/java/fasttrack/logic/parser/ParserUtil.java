package fasttrack.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;

import fasttrack.commons.core.index.Index;
import fasttrack.commons.util.StringUtil;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.category.Category;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.model.expense.Price;
import fasttrack.model.expense.RecurringExpenseType;
import fasttrack.model.util.CommandUtility;

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


    public static final String MESSAGE_INVALID_INDEX = "The index provided is invalid.";

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
        requireNonNull(price);
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
     * Parses {@code String timespan} into a {@code LocalDate}.
     */
    public static Timespan parseTimespan(String timespan) throws ParseException {
        assert timespan != null : "input should not be null";
        requireNonNull(timespan);
        String trimmedTimespan = timespan.trim();
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
