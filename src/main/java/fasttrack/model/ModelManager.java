package fasttrack.model;

import static fasttrack.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import fasttrack.commons.core.GuiSettings;
import fasttrack.commons.core.LogsCenter;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.RecurringExpenseManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the expense tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ExpenseTracker expenseTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Expense> filteredExpenses;
    private final FilteredList<Category> filteredCategories;
    private final FilteredList<RecurringExpenseManager> filteredRecurringExpense;

    private SimpleObjectProperty<Category> appliedCategoryFilter =
            new SimpleObjectProperty<>(null);
    private SimpleObjectProperty<ParserUtil.Timespan> appliedTimeSpanFilter =
            new SimpleObjectProperty<>(ParserUtil.Timespan.ALL);

    /**
     * Initializes a ModelManager with the given expenseTracker and userPrefs.
     */
    public ModelManager(ReadOnlyExpenseTracker expenseTracker, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(expenseTracker, userPrefs);
        logger.fine("Initializing with expense tracker: " + expenseTracker + " and user prefs " + userPrefs);
        this.expenseTracker = new ExpenseTracker(expenseTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExpenses = new FilteredList<>(this.expenseTracker.getExpenseList());
        filteredCategories = new FilteredList<>(this.expenseTracker.getCategoryList());
        filteredRecurringExpense = new FilteredList<>(this.expenseTracker.getRecurringExpenseGenerators());
    }

    public ModelManager() {
        this(new ExpenseTracker(), new UserPrefs());
    }


    // =========== UserPrefs
    // ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getExpenseTrackerFilePath() {
        return userPrefs.getExpenseTrackerFilePath();
    }

    @Override
    public void setExpenseTrackerFilePath(Path expenseTrackerFilePath) {
        requireNonNull(expenseTrackerFilePath);
        userPrefs.setExpenseTrackerFilePath(expenseTrackerFilePath);
    }

    // =========== ExpenseTracker
    // ================================================================================

    @Override
    public void setExpenseTracker(ReadOnlyExpenseTracker expenseTracker) {
        this.expenseTracker.resetData(expenseTracker);
    }

    @Override
    public ReadOnlyExpenseTracker getExpenseTracker() {
        return expenseTracker;
    }

    // =========== Expenses List Accessors
    // =============================================================
    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return expenseTracker.equals(other.expenseTracker)
                && userPrefs.equals(other.userPrefs)
                && filteredExpenses.equals(other.filteredExpenses)
                && filteredCategories.equals(other.filteredCategories)
                && filteredRecurringExpense.equals(other.filteredRecurringExpense);
    }

    @Override
    public SimpleObjectProperty<ParserUtil.Timespan> getAppliedTimeSpanFilter() {
        return appliedTimeSpanFilter;
    }

    @Override
    public SimpleObjectProperty<Category> getAppliedCategoryFilter() {
        return appliedCategoryFilter;
    }

    @Override
    public void updateTimeSpanFilter(ParserUtil.Timespan timeSpan) {
        appliedTimeSpanFilter.set(timeSpan);
    }

    @Override
    public void updateCategoryFilter(Category category) {
        appliedCategoryFilter.set(category);
    }

    // =========== Category List Accessors
    // =============================================================

    @Override
    public ObservableList<Category> getFilteredCategoryList() {
        return filteredCategories;
    }

    @Override
    public void updateFilteredCategoryList(Predicate<Category> predicate) {
        requireNonNull(predicate);
        filteredCategories.setPredicate(predicate);
    }

    /**
     * Indicates if a category exists in the category list
     * @param category the category to check for
     */
    @Override
    public boolean hasCategory(Category category) {
        requireNonNull(category);
        return expenseTracker.hasCategory(category);
    }

    @Override
    public void addCategory(Category toAdd) {
        expenseTracker.addCategory(toAdd);
    }

    @Override
    public void deleteCategory(Category target) {
        expenseTracker.removeCategory(target);
        updateFilteredCategoryList(PREDICATE_SHOW_ALL_CATEGORY);
        updateFilteredExpensesList(PREDICATE_SHOW_ALL_EXPENSES);
    }

    @Override
    public void clearCategory() {
        expenseTracker.clearCategory();
    }

    @Override
    public Category getCategoryInstance(Category category) {
        if (hasCategory(category)) {
            return expenseTracker.getCategoryInstance(category);
        }
        return null;
    }


    // =========== Filtered Expense List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Expense} backed by the
     * internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return filteredExpenses;
    }

    /**
     * Replaces the given expense {@code target} with {@code editedExpense}.
     * {@code target} must exist in the expense list
     */
    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        expenseTracker.setExpense(target, editedExpense);
    }

    @Override
    public void setExpense(int index, Expense newExpense) {
        expenseTracker.setExpense(index, newExpense);
    }

    @Override
    public void updateFilteredExpensesList(Predicate<Expense> predicate) {
        requireNonNull(predicate);
        filteredExpenses.setPredicate(predicate);
    }

    @Override
    public void addExpense(Expense expense) {
        expenseTracker.addExpense(expense);
        updateFilteredExpensesList(PREDICATE_SHOW_ALL_EXPENSES);
    }

    @Override
    public void deleteExpense(Expense expense) {
        expenseTracker.removeExpense(expense);
        updateFilteredExpensesList(PREDICATE_SHOW_ALL_EXPENSES);
    }

    @Override
    public void clearExpense() {
        expenseTracker.clearExpense();
    }

    /**
     * Indicates if an expense exists in the expense list
     * @param expense the expense to check for
     */
    @Override
    public boolean hasExpense(Expense expense) {
        requireNonNull(expense);
        return expenseTracker.hasExpense(expense);
    }

    @Override
    public void setBudget(Budget budget) {
        expenseTracker.setBudget(budget);
    }

    // =========== Recurring Expense Manager List Accessors
    // =============================================================

    @Override
    public boolean hasRecurringExpense(RecurringExpenseManager recurringExpenseManager) {
        return expenseTracker.hasRecurringExpense(recurringExpenseManager);
    }

    @Override
    public void addRecurringGenerator(RecurringExpenseManager recurringExpenseManager) {
        expenseTracker.addRecurringGenerator(recurringExpenseManager);
    }

    @Override
    public ObservableList<RecurringExpenseManager> getRecurringExpenseGenerators() {
        return expenseTracker.getRecurringExpenseGenerators();
    }

    @Override
    public void updateFilteredRecurringGenerators(Predicate<RecurringExpenseManager> predicate) {
        requireNonNull(predicate);
        filteredRecurringExpense.setPredicate(predicate);
    }

    /**
     * Delete all recurring expense generators.
     */
    @Override
    public void clearRecurringExpenseGenerator() {
        expenseTracker.clearRecurringExpense();
    }

    @Override
    public void deleteRecurringExpense(RecurringExpenseManager recurringExpenseManager) {
        expenseTracker.removeRecurringExpense(recurringExpenseManager);
    }

    @Override
    public void addRetroactiveExpenses() {
        expenseTracker.generateRetroactiveExpenses();
    }
}
