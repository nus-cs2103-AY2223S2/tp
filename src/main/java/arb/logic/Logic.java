package arb.logic;

import java.nio.file.Path;

import arb.commons.core.GuiSettings;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.logic.parser.exceptions.ParseException;
import arb.model.ListType;
import arb.model.ProjectStub;
import arb.model.ReadOnlyAddressBook;
import arb.model.client.Client;
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
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Sets the variable that keeps track of which list is currently being shown
     * to the user.
     * @param newListType The new type of list.
     */
    void setListType(ListType newListType);

    /**
     * Returns the AddressBook.
     *
     * @see arb.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of clients */
    ObservableList<Client> getFilteredClientList();

    /** Returns an unmodifiable view of the filtered list of projects */
    ObservableList<ProjectStub> getFilteredProjectList();

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
