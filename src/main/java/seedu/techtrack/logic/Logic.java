package seedu.techtrack.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.techtrack.commons.core.GuiSettings;
import seedu.techtrack.logic.commands.CommandResult;
import seedu.techtrack.logic.commands.exceptions.CommandException;
import seedu.techtrack.logic.commands.exceptions.exceptions.ParseException;
import seedu.techtrack.model.ReadOnlyRoleBook;
import seedu.techtrack.model.role.Role;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the RoleBook.
     *
     * @see seedu.techtrack.model.Model#getRoleBook()
     */
    ReadOnlyRoleBook getRoleBook();

    /** Returns an unmodifiable view of the filtered list of roles */
    ObservableList<Role> getFilteredRoleList();

    /**
     * Returns the user prefs' role book file path.
     */
    Path getRoleBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
