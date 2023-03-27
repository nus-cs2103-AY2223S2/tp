package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import tfifteenfour.clipboard.model.student.Assignment;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.UniqueStudentList;

/**
 * Represents a Group in the CLIpboard.
 * Guarantees: immutable; name is valid as declared in {@link #isValidGroupName(String)}
 */
public class Group {
    public static final String MESSAGE_CONSTRAINTS = "Module codes should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private String groupName;
    private final UniqueStudentList students;
    private final UniqueSessionsList sessions;

    {
        students = new UniqueStudentList();
    }

    {
        sessions = new UniqueSessionsList();
    }


    // Placeholder example only, dont use arraylist, need to use ObservableList for javafx to recognize
    // Convert to UniqueXXXlist, just like UniqueStudentsList class
    private ArrayList<Assignment> assignments = new ArrayList<>();
    // ##############################################################

    /**
     * Constructs a group with the given group name.
     * @param groupName A valid group name.
     */
    public Group(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Returns an unmodifiable view of the list of students in this group.
     */
    public ObservableList<Student> getUnmodifiableStudentList() {
        return students.asUnmodifiableObservableList();
    }

    /**
     * Returns a modifiable view of the list of students in this group.
     */
    public ObservableList<Student> getModifiableStudentlist() {
        return students.asModifiableObservableList();
    }

    /**
     * Returns an unmodifiable view of the list of sessions in this group.
     */
    public ObservableList<Session> getUnmodifiableSessionList() {
        return sessions.asUnmodifiableObservableList();
    }

    /**
     * Adds the given student to this group.
     * @param student Student to be added.
     */
    public void addStudent(Student student) {
        this.students.add(student);
        for (Session session : sessions) {
            session.setStudents(students);
        }
    }

    /**
     * Adds the given session to this group.
     * @param session Session to be added.
     */
    public void addSession(Session session) {
        this.sessions.add(session);
        session.setStudents(students);
    }

    /**
     * Adds the given assignment to this group.
     * @param assignment Assignment to be added.
     */
    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    /**
     * Deletes the given session from this group.
     * @param session Session to be deleted.
     */
    public void deleteSession(Session session) {
        this.sessions.remove(session);
    }

    /**
     * Deletes the given student from this group.
     * @param student Student to be deleted.
     */
    public void deleteStudent(Student student) {
        this.students.remove(student);
    }

    /**
     * Returns the name of this group.
     */
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
     * Returns true if this group contains the specified student.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Returns true if this group contains the specified session.
     */
    public boolean hasSession(Session session) {
        requireNonNull(session);
        return sessions.contains(session);
    }

    /**
     * Returns true if the given string is a valid group name.
     */
    public static boolean isValidGroupName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Unselects all sessions in a group. For GUI purpose.
     */
    public void unMarkAllSessions() {
        for (Session session: this.getUnmodifiableSessionList()) {
            session.unselectSession();
        }
    }
    @Override
    public String toString() {
        return this.groupName;
    }
}
