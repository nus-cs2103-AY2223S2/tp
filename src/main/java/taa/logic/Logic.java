package taa.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import taa.commons.core.GuiSettings;
import taa.logic.commands.CommandResult;
import taa.logic.commands.exceptions.CommandException;
import taa.logic.parser.exceptions.ParseException;
import taa.model.Model;
import taa.model.student.Student;
import taa.storage.TaaData;

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
     * Returns the ClassList.
     *
     * @see Model#getTaaData()
     */
    TaaData getTaaData();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' TAA data file path.
     */
    Path getTaaDataFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /** @return previously executed command or null if has none */
    String getPrevCmd();

    /** @return next executed command or null if has none */
    String getNextCmd();
}
