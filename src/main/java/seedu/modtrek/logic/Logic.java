package seedu.modtrek.logic;

import java.nio.file.Path;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import seedu.modtrek.commons.core.GuiSettings;
import seedu.modtrek.logic.commands.CommandResult;
import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.ReadOnlyDegreeProgression;
import seedu.modtrek.model.module.Module;

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
     * @see Model#getDegreeProgression()
     */
    ReadOnlyDegreeProgression getDegreeProgression();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getDegreeProgressionFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    TreeMap<? extends Object, ObservableList<Module>> sortModuleGroups(CommandResult.Sort sort);
}
