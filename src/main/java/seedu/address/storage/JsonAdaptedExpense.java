package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;
import seedu.address.model.util.StorageUtility;

/**
 * Jackson-friendly version of {@link Expense}.
 */
public class JsonAdaptedExpense {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expense's %s field is missing!";

    private final String name;
    private final String amount;
    private final String date;
    private final JsonAdaptedCategory category;



    /**
     * Constructs a {@code JsonAdaptedExpense} with the given expense details.
     * @param name Name of the expense.
     * @param amount Amount of the expense.
     * @param date Date of the expense.
     * @param category Category of the expense.
     */
    @JsonCreator
    public JsonAdaptedExpense(@JsonProperty("name") String name, @JsonProperty("amount") String amount,
                              @JsonProperty("date") String date,
                              @JsonProperty("category") JsonAdaptedCategory category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     * https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
     * @param source future changes to this will not affect the created {@code JsonAdaptedExpense}.
     */
    public JsonAdaptedExpense(Expense source) {
        name = source.getName();
        amount = Double.toString(source.getAmount());
        date = source.getFormattedDate();
        category = new JsonAdaptedCategory(
                source.getCategory().getCategoryName(), source.getCategory().getSummary());
    }

    /**
     * Converts this Jackson-friendly adapted expense object into the model's {@code Expense} object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted expense.
     */
    public Expense toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }
        if (!Expense.isValidName(name)) {
            throw new IllegalValueException(Expense.MESSAGE_CONSTRAINTS);
        }

        final String modelName = name;

        final double modelAmount = Double.parseDouble(amount);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }

        final LocalDate modelDate = StorageUtility.parseDateFromJson(date);

        final Category modelCategory = category.toModelType();

        return new Expense(modelName, modelAmount, modelDate, modelCategory);
    }
}
