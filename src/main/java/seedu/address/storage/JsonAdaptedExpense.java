package seedu.address.storage;

import java.time.Instant;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;

/**
 * Jackson-friendly version of {@link Expense}.
 */
public class JsonAdaptedExpense {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expense's %s field is missing!";

    private final String name;
    private final double amount;
    private final Date date;
    private final Category category;

    /**
     * Constructs a {@code JsonAdaptedExpense} with the given expense details.
     */
    @JsonCreator
    public JsonAdaptedExpense(@JsonProperty("name") String name, @JsonProperty("amount") double amount,
            @JsonProperty("date") String date, @JsonProperty("category") Category category) {
        this.name = name;
        this.amount = amount;
        Instant instant = Instant.parse(date);
        this.date = Date.from(instant);
        this.category = category;
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     * https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
     */
    public JsonAdaptedExpense(Expense source) {
        name = source.getName();
        amount = source.getAmount();
        date = source.getDate();
        category = source.getCategory();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Expense} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Expense toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }
        if (!Expense.isValidName(name)) {
            throw new IllegalValueException(Expense.MESSAGE_CONSTRAINTS);
        }
        final String modelName = name;

        final double modelAmount = amount;

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }
        final Date modelDate = date;

        if (category == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Category"));
        }
        if (!Expense.isValidCategory(category)) {
            throw new IllegalValueException(Expense.MESSAGE_CONSTRAINTS);
        }
        final Category modelCategory = category;

        return new Expense(modelName, modelAmount, modelDate, modelCategory);
    }
}
