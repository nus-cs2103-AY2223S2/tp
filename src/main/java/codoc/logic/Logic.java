package codoc.logic;

import java.nio.file.Path;

import codoc.commons.core.GuiSettings;
import codoc.logic.commands.CommandResult;
import codoc.logic.commands.exceptions.CommandException;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.Model;
import codoc.model.ReadOnlyCodoc;
import codoc.model.person.Person;
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
     * Returns the Codoc.
     *
     * @see Model#getCodoc()
     */
    ReadOnlyCodoc getCodoc();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    Person getProtagonist();

    String getCurrentTab();

    void setCurrentTab(String tab);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getCodocFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
