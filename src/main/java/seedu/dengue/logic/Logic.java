package seedu.dengue.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.dengue.commons.core.GuiSettings;
import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.logic.commands.CommandResult;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.overview.Overview;
import seedu.dengue.model.person.Person;

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
     * Returns the DengueHotspotTracker.
     *
     * @see seedu.dengue.model.Model#getDengueHotspotTracker()
     */
    ReadOnlyDengueHotspotTracker getDengueHotspotTracker();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' Dengue Hotspot Tracker file path.
     */
    Path getDengueHotspotTrackerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the overview of the model.
     */
    Overview getOverview();

    ObservableList<DataBin> getOverviewContent();
}
