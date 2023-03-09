package t154.CLIpboard.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import t154.CLIpboard.commons.core.GuiSettings;
import t154.CLIpboard.logic.commands.CommandResult;
import t154.CLIpboard.logic.commands.exceptions.CommandException;
import t154.CLIpboard.logic.parser.exceptions.ParseException;
import t154.CLIpboard.model.ReadOnlyAddressBook;
import t154.CLIpboard.model.student.Student;

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
     * @see t154.CLIpboard.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Student> getFilteredStudentList();

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
