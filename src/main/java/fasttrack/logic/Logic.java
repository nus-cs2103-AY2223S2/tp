package fasttrack.logic;

import java.nio.file.Path;

import fasttrack.commons.core.GuiSettings;
import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.logic.parser.ParserUtil;
import fasttrack.logic.parser.exceptions.ParseException;
import fasttrack.model.Model;
import fasttrack.model.ReadOnlyExpenseTracker;
import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.RecurringExpenseManager;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ExpenseTracker.
     * @see Model#getExpenseTracker()
     */
    ReadOnlyExpenseTracker getExpenseTracker();

    /** Returns an unmodifiable view of the list of categories */
    ObservableList<Category> getFilteredCategoryList();

    /** Returns an unmodifiable view of the filtered list of expenses */
    ObservableList<Expense> getFilteredExpenseList();

    /** Returns an unmodifiable view of the list of recurring expenses */
    ObservableList<RecurringExpenseManager> getRecurringExpenseManagerList();

    SimpleObjectProperty<ParserUtil.Timespan> getAppliedTimeSpanFilter();

    SimpleObjectProperty<Category> getAppliedCategoryFilter();

    /**
     * Returns the user prefs' file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
