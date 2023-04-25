package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

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
     * Suggest a possible autocomplete value based on the user input.
     * The returned suggestion also contains the user input
     * (ie. user input "ad" returns the suggestion "add ..." which includes the input "ad")
     * @param userInput The current user input.
     * @return An autocomplete suggestion.
     * @throws ParseException If {@code userInput} likely has syntax errors (based on heuristics).
     */
    String suggestCommand(String userInput) throws ParseException;

    /**
     * Autocomplete a user input based on the current command suggestion.
     * @param userInput The current user input.
     * @param commandSuggestion The current command suggestion.
     * @return The new user input after autocompleting.
     */
    String autocompleteCommand(String userInput, String commandSuggestion);

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the person to be shown.
     * @return Person to be shown.
     */
    ObservableList<Person> getShowPerson();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

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
