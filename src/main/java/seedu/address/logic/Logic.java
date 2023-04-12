package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMathutoring;
import seedu.address.model.student.Student;

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
     * Returns the Mathutoring.
     *
     * @see Model#getMathutoring()
     */
    ReadOnlyMathutoring getMathutoring();

    /**
     * Sets the mathutoring to the given mathutoring.
     */
    void setMathutoring(ReadOnlyMathutoring mathutoring) throws CommandException;

    /**
     * Stores the mathutoring data to the given file path.
     */
    void storeMathutoring(Path filePath) throws IOException;


    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' math tutoring file path.
     */
    Path getMathutoringFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the student that user want to check.
     *
     * @return the student being selected by the user.
     */

    Student findCheckedStudent();

    void exportProgress(Student target, String completePath) throws IOException;

}
