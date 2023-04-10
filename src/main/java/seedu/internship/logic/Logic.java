package seedu.internship.logic;


import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.internship.commons.core.GuiSettings;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.model.ReadOnlyEventCatalogue;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
import seedu.internship.model.event.Event;
import seedu.internship.model.internship.Internship;

/**
 * API of the Logic component.
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
     * Returns the InternshipCatalogue.
     *
     * @see seedu.internship.model.Model#getInternshipCatalogue()
     */
    ReadOnlyInternshipCatalogue getInternshipCatalogue();

    /**
     * Returns the EventCatalogue.
     *
     * @see seedu.internship.model.Model#getEventCatalogue()
     */
    ReadOnlyEventCatalogue getEventCatalogue();

    /** Returns an unmodifiable view of the filtered list of internships */
    ObservableList<Internship> getFilteredInternshipList();

    /** Returns an unmodifiable view of the filtered list of events */
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns the user prefs' internship catalogue file path.
     */
    Path getInternshipCatalogueFilePath();

    /**
     * Returns the user prefs' event catalogue file path.
     */
    Path getEventCatalogueFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
