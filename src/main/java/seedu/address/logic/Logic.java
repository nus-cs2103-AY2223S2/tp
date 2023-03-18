package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPcClass;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.parent.ReadOnlyParents;
import seedu.address.model.person.student.Student;

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
     * Returns the parents.
     *
     * @see Model#getParents()
     */
    ReadOnlyParents getParents();

    /**
     * Returns the PCClass.
     *
     * @see Model#getPcClass()
     */
    ReadOnlyPcClass getPcClass();


    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered list of parents */
    ObservableList<Parent> getFilteredParentList();

    /**
     * Returns the user prefs' PCClass file path.
     */
    Path getPcClassFilePath();

    /**
     * Returns the user prefs' parents file path.
     */
    Path getParentsFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
