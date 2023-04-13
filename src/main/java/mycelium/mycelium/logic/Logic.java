package mycelium.mycelium.logic;

import java.nio.file.Path;
import java.util.HashMap;

import javafx.collections.ObservableList;
import mycelium.mycelium.commons.core.GuiSettings;
import mycelium.mycelium.logic.commands.CommandResult;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.ReadOnlyAddressBook;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.project.Project;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of clients
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * Returns an unmodifiable view of the filtered list of projects
     */
    ObservableList<Project> getFilteredProjectList();

    /**
     * Returns an unmodifiable view of all due projects within this or next week
     */
    ObservableList<Project> getDueProjectList();

    /**
     * Returns an unmodifiable view of all overdue projects
     */
    ObservableList<Project> getOverdueProjectList();

    /**
     * Retrieves a hash map of status name and corresponding count of projects with that status.
     */
    HashMap<String, Long> getProjectStatistics();

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
