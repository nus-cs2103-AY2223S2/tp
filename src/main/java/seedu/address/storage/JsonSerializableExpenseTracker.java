package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ExpenseTracker;
import seedu.address.model.ReadOnlyExpenseTracker;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;

/**
 * An Immutable ExpenseTracker that is serializable to JSON format.
 */
@JsonRootName(value = "expenseTracker")
class JsonSerializableExpenseTracker {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_CATEGORY = "Category list contains duplicate categories!";

    private final List<JsonAdaptedCategory> categories = new ArrayList<>();
    private final List<JsonAdaptedExpense> expenses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableExpenseTracker} with the given expenses
     * and categories.
     * @param listOfCategories list of categories to be added to the ExpenseTracker
     * @param listOfExpenses   list of expenses to be added to the ExpenseTracker
     */
    @JsonCreator
    public JsonSerializableExpenseTracker(@JsonProperty("categories") List<JsonAdaptedCategory> listOfCategories,
            @JsonProperty("expenses") List<JsonAdaptedExpense> listOfExpenses) {
        this.categories.addAll(listOfCategories);
        this.expenses.addAll(listOfExpenses);
    }

    /**
     * Converts a given {@code ReadOnlyExpenseTracker} into this class for Jackson
     * use.
     * @param source future changes to this will not affect the created
     *               {@code JsonSerializableExpenseTracker}.
     */
    public JsonSerializableExpenseTracker(ReadOnlyExpenseTracker source) {
        this.categories.addAll(source.getCategoryList()
                .stream().map(JsonAdaptedCategory::new).collect(Collectors.toList()));
        this.expenses.addAll(source.getExpenseList()
                .stream().map(JsonAdaptedExpense::new).collect(Collectors.toList()));
    }

    /**
     * Converts this ExpenseTracker into the model's {@code ExpenseTracker} object.
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ExpenseTracker toModelType() throws IllegalValueException {
        ExpenseTracker expenseTracker = new ExpenseTracker();

        for (JsonAdaptedCategory jsonAdaptedCategory : categories) {
            Category category = jsonAdaptedCategory.toModelType();
            expenseTracker.addCategory(category);
        }

        for (JsonAdaptedExpense jsonAdaptedExpense : expenses) {
            Expense expense = jsonAdaptedExpense.toModelType();
            expenseTracker.addExpense(expense);
        }

        return expenseTracker;
    }

}
