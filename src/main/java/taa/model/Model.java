package taa.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import taa.commons.core.GuiSettings;
import taa.logic.commands.enums.ChartType;
import taa.logic.commands.exceptions.CommandException;
import taa.model.alarm.Alarm;
import taa.model.assignment.Assignment;
import taa.model.assignment.exceptions.AssignmentException;
import taa.model.assignment.exceptions.AssignmentNotFoundException;
import taa.model.assignment.exceptions.DuplicateAssignmentException;
import taa.model.assignment.exceptions.NoGradeVarianceException;
import taa.model.assignment.exceptions.NoSubmissionsFoundException;
import taa.model.student.Student;
import taa.storage.TaaData;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
    Predicate<ClassList> PREDICATE_SHOW_ALL_CLASSES = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' TAA data file path.
     */
    Path getTaaDataFilePath();

    /**
     * Sets the user prefs' TAA data file path.
     */
    void setTaaDataFilePath(Path taaDataFilePath);

    /** Returns the TAA data */
    TaaData getTaaData();

    /**
     * Replaces TAA data with the data in {@code taaData}.
     */
    void setTaaData(TaaData taaData);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student list.
     */
    boolean hasStudent(Student student);


    boolean hasClassList(ClassList tocheck);

    /**
     * Returns the number of students in the active class list.
     */
    int getClassListSize();

    /**
     * Deletes the given student. The student must exist in the student list.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student. {@code student} must not already exist in the student list.
     */
    void addStudent(Student student);

    void addClassList(ClassList toAdd);

    /**
     * Updates the student list to propagate change to the rest of the model.
     *
     * @param student The student to be refreshed.
     */
    void updateStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}. {@code target} must exist in the student
     * list. The student identity of {@code editedStudent} must not be the same as another existing student in the
     * student list.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Returns an unmodifiable view of the filtered student list
     */
    ObservableList<Student> getFilteredStudentList();

    ObservableList<Student> getFilteredClassList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    void updateFilteredClassLists(Predicate<ClassList> predicate);

    /**
     * Adds a student to all the class lists he/she is tagged with. Creates new class lists to add the student into, if
     * required.
     *
     * @param student The student to include in all of his/her tagged classes.
     */
    void addStudentToTaggedClasses(Student student);

    /**
     * Checks if a given assignment already exists.
     */
    boolean hasAssignment(String assignmentName);

    void addAssignment(String assignmentName, int totalMarks) throws DuplicateAssignmentException;

    void deleteAssignment(String assignmentName) throws AssignmentNotFoundException;

    void grade(String assignmentName, int studentId, int marks, boolean isLateSubmission) throws AssignmentException;

    String listAssignments();

    void ungrade(String assignmentName, int studentId) throws AssignmentException;

    void addAlarm(Alarm alarm) throws CommandException;


    void deleteStudentSubmission(Student studentToDelete);

    void initAssignmentsFromStorage(Assignment[] asgnArr);

    void addStudentAssignment(Student toAdd);

    void displayChart(ChartType chartType, String... args)
            throws AssignmentNotFoundException, NoSubmissionsFoundException, NoGradeVarianceException;

    String listAlarms();

    void deleteAlarm(int index) throws CommandException;

}
