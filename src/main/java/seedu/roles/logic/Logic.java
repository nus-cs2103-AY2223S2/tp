package seedu.roles.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.roles.commons.core.GuiSettings;
import seedu.roles.logic.commands.CommandResult;
import seedu.roles.logic.commands.exceptions.CommandException;
import seedu.roles.logic.commands.exceptions.exceptions.ParseException;
import seedu.roles.model.ReadOnlyAddressBook;
import seedu.roles.model.job.Role;

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
     * Returns the AddressBook.
     *
     * @see seedu.roles.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of roles */
    ObservableList<Role> getFilteredRoleList();

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
