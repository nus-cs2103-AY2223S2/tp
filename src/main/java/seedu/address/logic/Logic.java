package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyFriendlyLink;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.model.person.Volunteer;

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
     * Returns the FriendlyLink.
     *
     * @see seedu.address.model.Model#getFriendlyLink()
     */
    ReadOnlyFriendlyLink getFriendlyLink();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    // NOTE: Add to UI if needed (MainWindow)
    /** Returns an unmodifiable view of the filtered list of elderly */
    ObservableList<Elderly> getFilteredElderlyList();

    /** Returns an unmodifiable view of the filtered list of volunteers */
    ObservableList<Volunteer> getFilteredVolunteerList();

    /** Returns an unmodifiable view of the filtered list of pairs */
    ObservableList<Pair> getFilteredPairList();

    /**
     * Returns the user prefs' friendly link file path.
     */
    Path getFriendlyLinkFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
