package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import tfifteenfour.clipboard.model.student.Assignment;
import tfifteenfour.clipboard.model.student.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.UniqueStudentList;

/**
 * Represents a Course in the CLIpboard.
 * Guarantees: immutable; name is valid as declared in {@link #isValidGroupName(String)}
 */
public class Group {
    public static final String MESSAGE_CONSTRAINTS = "Module codes should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private String groupName;
    private final UniqueStudentList students;

    {
        students = new UniqueStudentList();
    }

    // Placeholder example only, dont use arraylist, need to use ObservableList for javafx to recognize
    // Convert to UniqueXXXlist, just like UniqueStudentsList class
    private ArrayList<Session> sessions = new ArrayList<>();
    private ArrayList<Assignment> assignments = new ArrayList<>();
    // ##############################################################

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public ObservableList<Student> getUnmodifiableStudentList() {
        return students.asUnmodifiableObservableList();
    }

    public ObservableList<Student> getModifiableGrouplist() {
        return students.asModifiableObservableList();
    }


    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    public String getGroupName() {
        return this.groupName;
    }

    /**
     * Returns true if both groups are the same.
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.getGroupName().equals(getGroupName());
    }

    /**
     * Returns true if course has specified student.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    public static boolean isValidGroupName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.groupName;
    }
}
