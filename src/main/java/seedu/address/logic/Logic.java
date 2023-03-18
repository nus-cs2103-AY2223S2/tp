package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyEduMate;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;

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
     * Returns the EduMate.
     *
     * @see seedu.address.model.Model#getEduMate()
     */
    ReadOnlyEduMate getEduMate();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getObservablePersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getEduMateFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Get user information
     */
    User getUser();
}
