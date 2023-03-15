package seedu.wife.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.wife.commons.core.GuiSettings;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.logic.parser.exceptions.ParseException;
import seedu.wife.model.ReadOnlyWife;
import seedu.wife.model.food.Food;

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
     * Returns Wife.
     *
     * @see seedu.wife.model.Model#getWife()
     */
    ReadOnlyWife getWife();

    /** Returns an unmodifiable view of the filtered list of food */
    ObservableList<Food> getFilteredFoodList();

    /**
     * Returns the user prefs' wife file path.
     */
    Path getWifeFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
