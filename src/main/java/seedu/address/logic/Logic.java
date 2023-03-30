package seedu.address.logic;

import java.nio.file.Path;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyExpenseTracker;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.RecurringExpenseManager;

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
     * Returns the user prefs' address book file path.
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
