package fasttrack.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import fasttrack.commons.core.GuiSettings;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.RecurringExpenseManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

/**
 * The API of the DataModel component.
 */
public interface Model {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Expense> PREDICATE_SHOW_ALL_EXPENSES = unused -> true;
    Predicate<Category> PREDICATE_SHOW_ALL_CATEGORY = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' expense tracker file path.
     */
    Path getExpenseTrackerFilePath();

    /**
     * Sets the user prefs' expense tracker file path.
     */
    void setExpenseTrackerFilePath(Path expenseTrackerFilePath);

    /**
     * Replaces expenseTracker data with the data in {@code expenseTracker}.
     */
    void setExpenseTracker(ReadOnlyExpenseTracker expenseTracker);

    /** Returns the ExpenseTracker */
    ReadOnlyExpenseTracker getExpenseTracker();

    SimpleObjectProperty<ParserUtil.Timespan> getAppliedTimeSpanFilter();

    SimpleObjectProperty<Category> getAppliedCategoryFilter();

    void updateTimeSpanFilter(ParserUtil.Timespan timeSpan);

    void updateCategoryFilter(Category category);

    // Expense accessor functions

    /**
     * Adds the given expense to the expense tracker
     * @param expense the new expense to add
     */
    void addExpense(Expense expense);

    /**
     * Deletes the given expense.
     * The expense must exist in the ExpenseTracker.
     * @param expense the expense to delete
     */
    void deleteExpense(Expense expense);

    /**
     * Delete all expense.
     */
    void clearExpense();


    /**
     * Replaces the Expense in the expense list at the given index.
     * @param index
     * @param expense
     */
    void setExpense(int index, Expense expense);

    /**
     * Replaces the given expense {@code target} with {@code editedExpense}.
     * {@code target} must exist in the expense list
     */
    void setExpense(Expense target, Expense editedExpense);


    /**
     * Indicates if an expense exists in the expense list
     * @param expense the expense to check for
     */
    boolean hasExpense(Expense expense);

    /**
     * Updates the filter of the filtered expense list to filter by the given
     * {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpensesList(Predicate<Expense> predicate);

    /** Returns an unmodifiable view of the filtered expense list */
    ObservableList<Expense> getFilteredExpenseList();

    // Category accessor functions

    /**
     * Deletes the given expense.
     * @param target the category to delete
     */
    void deleteCategory(Category target);

    /**
     * Delete all Category.
     */
    void clearCategory();

    /**
     * Adds the given category to the category list.
     * @param toAdd the category to add
     */
    void addCategory(Category toAdd);


    /**
     * Indicates if a category exists in the category list
     * @param category the category to check for
     */
    boolean hasCategory(Category category);

    /** Returns an unmodifiable view of the category list */
    ObservableList<Category> getFilteredCategoryList();

    /**
     * Updates the filter of the filtered category list to filter by the given
     * {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCategoryList(Predicate<Category> predicate);

    /**
     * Returns a reference to the instance of category
     * matching the category name in the category list
     * @param category the category to check for
     * @return the category instance if it exists, and null if it does not
     */
    Category getCategoryInstance(Category category);

    /**
     * Sets budget for FastTrack.
     * @param budget
     */
    void setBudget(Budget budget);

    /**
     * Indicates if a RecurringExpense exists in the RecurringExpenseList
     * @param recurringExpense the RecurringExpense to check for
     */
    boolean hasRecurringExpense(RecurringExpenseManager recurringExpense);

    /**
     * Adds a RecurringExpense to the RecurringExpense list.
     * @param recurringExpenseManager the Recurring expense to add.
     */
    void addRecurringGenerator(RecurringExpenseManager recurringExpenseManager);

    /** Returns an unmodifiable view of the recurring expense list */
    ObservableList<RecurringExpenseManager> getRecurringExpenseGenerators();

    /**
     * Updates the filter of the filtered recurring expense manager list to filter by the given
     * {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRecurringGenerators(Predicate<RecurringExpenseManager> predicate);

    /**
     * Delete all recurring expense generators.
     */
    void clearRecurringExpenseGenerator();

    /**
     * Deletes the target {@code RecurringExpense} from the recurring expense list.
     * @param recurringExpenseManager the recurring expense to be deleted.
     */
    void deleteRecurringExpense(RecurringExpenseManager recurringExpenseManager);

    void addRetroactiveExpenses();
}
