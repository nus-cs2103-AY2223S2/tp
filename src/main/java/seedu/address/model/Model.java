package seedu.address.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' mathutoring file path.
     */
    Path getMathutoringFilePath();

    /**
     * Sets the user prefs' mathutoring file path.
     */
    void setMathutoringFilePath(Path mathutoringFilePath);

    /**
     * Replaces mathutoring data with the data in {@code mathutoring}.
     */
    void setMathutoring(ReadOnlyMathutoring mathutoring);

    /** Returns the Mathutoring */
    ReadOnlyMathutoring getMathutoring();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the mathutoring.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the mathutoring.
     */
    void deleteStudent(Student target);

    /**
     * Check the given student.
     * The student must exist in the mathutoring.
     */
    void checkStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the mathutoring.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the mathutoring.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in the mathutoring.
     */
    void setStudent(Student target, Student editedStudent);


    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    void exportProgress(Student target, String completePath) throws IOException;


    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    Student findSelectedStudent();
}
