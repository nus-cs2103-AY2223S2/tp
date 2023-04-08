package tfifteenfour.clipboard.model.task;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tfifteenfour.clipboard.model.course.exceptions.StudentNotInSessionException;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentWithGrades;
import tfifteenfour.clipboard.model.student.UniqueStudentList;
import tfifteenfour.clipboard.model.task.exceptions.StudentNotInPageException;


/**
 * Represents a Task in the CLIpboard.
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS =
            "Tasks should only contain alphanumeric/special characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} \\p{Punct}]*";

    private final String taskName;
    private Boolean isSelected;

    /**
     * A map that stores the attendance record for each student in the session.
     * The key is the Student object, and the value is an integer indicating the attendance status:
     * 0 for absent, 1 for present.
     */
    private Map<Student, Integer> gradeList;

    /**
     * Constructs a {@code Task} with the given session name.
     * @param taskName The name of the session.
     */
    public Task(String taskName) {
        requireNonNull(taskName);
        this.taskName = taskName;
        gradeList = new HashMap<>();
        isSelected = false;
    }

    /**
     * Creates a copy of this instance. Used for saving states for undo command.
     * @return a copy of this instance.
     */
    public Task copy() {
        Task copy = new Task(this.taskName);
        copy.setGrades(new HashMap<>(gradeList));

        return copy;
    }

    /**
     * Returns an unmodifiable list of the students in the group with the task.
     */
    public ObservableList<StudentWithGrades> getUnmodifiableStudentList() {
        ObservableList<StudentWithGrades> students = FXCollections.observableArrayList();
        for (Student student : gradeList.keySet()) {
            students.add(new StudentWithGrades(student, gradeList.get(student)));
        }
        return students;
    }

    /**
     * Returns the name of the task.
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the grade list for each student in the current group for the current task.
     * @return A map that stores the grade list in the current group for the current task.
     */
    public Map<Student, Integer> getGrades() {
        return this.gradeList;
    }

    /**
     * Checks if the given Task object is the same as this Task object.
     * @param otherTask The Task object to be compared with this Task object.
     * @return True if the given Task object is the same as this Task object, false otherwise.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTaskName().equals(getTaskName());
    }

    /**
     * Checks if the given string is a valid session name.
     * @param test The string to be tested.
     * @return True if the given string is a valid task name, false otherwise.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Sets the list of students who are in the tasks student page.This will create a new hashMap and assign it
     * to {@code gradeList} instead of modifying {@code gradeList}.
     *
     * @param students The list of students who are in the task students page.
     */
    public void setStudents(UniqueStudentList students) {
        assert gradeList != null : "Grade List should not be null!";
        Map<Student, Integer> newGradeList = new HashMap<>();
        for (Student student : students) {
            newGradeList.put(student, gradeList.getOrDefault(student, 0));
        }
        gradeList = newGradeList;
    }

    public void setGrades(Map<Student, Integer> gradeList) {
        this.gradeList = new HashMap<>(gradeList);
    }

    /**
     * Replaces a student in current tasks page with a new student.
     * @param oldStudent Student to be replaced.
     * @param newStudent New student to replace with.
     */
    public void replaceStudent(Student oldStudent, Student newStudent) {
        if (!gradeList.containsKey(oldStudent)) {
            throw new StudentNotInSessionException();
        } else {
            int value = gradeList.get(oldStudent);
            gradeList.remove(oldStudent);
            gradeList.put(newStudent, value);
        }
    }

    /**
     * Assigns a grade to the student for the current task.
     * @param student The student to assign the mark to.
     * @throws StudentNotInPageException If the given student is not present in the page.
     */
    public void assignGrade(Student student, Integer grade) throws StudentNotInPageException {
        requireNonNull(student);
        assert gradeList != null : "Grades should not be null!";

        if (!gradeList.containsKey(student)) {
            throw new StudentNotInSessionException();
        }
        gradeList.put(student, grade);
        System.out.println("Student " + student.getName() + " has obtained grade " + grade + " for task " + taskName);
    }

    public void selectTask() {
        isSelected = true;
    }

    public void unselectTask() {
        isSelected = false;
    }

    public boolean getSelectionStatus() {
        return isSelected;
    }

    @Override
    public String toString() {
        return this.taskName;
    }

}
