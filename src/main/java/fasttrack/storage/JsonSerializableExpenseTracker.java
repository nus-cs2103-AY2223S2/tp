package fasttrack.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import fasttrack.commons.exceptions.IllegalValueException;
import fasttrack.model.ExpenseTracker;
import fasttrack.model.ReadOnlyExpenseTracker;
import fasttrack.model.category.Category;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.RecurringExpenseManager;

/**
 * An Immutable ExpenseTracker that is serializable to JSON format.
 */
@JsonRootName(value = "expenseTracker")
class JsonSerializableExpenseTracker {

    private final List<JsonAdaptedCategory> categories = new ArrayList<>();
    private final List<JsonAdaptedExpense> expenses = new ArrayList<>();
    private final List<JsonAdaptedRecurringExpenseManager> recurringGenerators = new ArrayList<>();
    private final JsonAdaptedBudget budget;

    /**
     * Constructs a {@code JsonSerializableExpenseTracker} with the given expenses
     * and categories.
     * @param listOfCategories list of categories to be added to the ExpenseTracker
     * @param listOfExpenses   list of expenses to be added to the ExpenseTracker
     */
    @JsonCreator
    public JsonSerializableExpenseTracker(@JsonProperty("categories") List<JsonAdaptedCategory> listOfCategories,
            @JsonProperty("expenses") List<JsonAdaptedExpense> listOfExpenses,
            @JsonProperty("budget") JsonAdaptedBudget budget,
            @JsonProperty("recurringGenerators") List<JsonAdaptedRecurringExpenseManager> recurringGenerators) {
        this.categories.addAll(listOfCategories);
        this.expenses.addAll(listOfExpenses);
        this.budget = budget;
        this.recurringGenerators.addAll(recurringGenerators);
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
        this.budget = new JsonAdaptedBudget(source.getBudget());
        this.recurringGenerators.addAll(source.getRecurringExpenseGenerators()
                .stream().map(JsonAdaptedRecurringExpenseManager::new).collect(Collectors.toList()));
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

        for (JsonAdaptedRecurringExpenseManager jsonAdaptedGenerator : recurringGenerators) {
            RecurringExpenseManager expenseGenerator = jsonAdaptedGenerator.toModelType();
            Category associatedCategory = getAssociatedCategoryForRecurring(expenseGenerator, expenseTracker);
            if (associatedCategory == null) {
                if (!(expenseGenerator.getExpenseCategory() instanceof MiscellaneousCategory)) {
                    expenseTracker.addCategory(expenseGenerator.getExpenseCategory());
                }
            } else {
                expenseGenerator.setExpenseCategory(associatedCategory);
            }
            expenseTracker.addRecurringGenerator(expenseGenerator);
        }

        for (JsonAdaptedExpense jsonAdaptedExpense : expenses) {
            Expense expense = jsonAdaptedExpense.toModelType();
            Category associatedCategory = getAssociatedCategory(expense, expenseTracker);
            if (associatedCategory == null) {
                if (!(expense.getCategory() instanceof MiscellaneousCategory)) {
                    expenseTracker.addCategory(expense.getCategory());
                }
            } else {
                expense.setCategory(associatedCategory);
            }
            expenseTracker.addExpense(expense);
        }

        expenseTracker.setBudget(budget.toModelType());

        return expenseTracker;
    }

    private Category getAssociatedCategory(Expense expense, ExpenseTracker expenseTracker) {
        return expenseTracker.getCategoryInstance(expense.getCategory());
    }

    private Category getAssociatedCategoryForRecurring(RecurringExpenseManager recur, ExpenseTracker expenseTracker) {
        return expenseTracker.getCategoryInstance(recur.getExpenseCategory());
    }
}
