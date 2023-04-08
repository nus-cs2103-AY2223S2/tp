package tfifteenfour.clipboard.model.course;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.UniqueStudentsList;
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.model.task.UniqueTasksList;

/**
 * Represents a Group in the CLIpboard.
 * Guarantees: immutable; name is valid as declared in {@link #isValidGroupName(String)}
 */
public class Group {
    public static final String MESSAGE_CONSTRAINTS = "Group names can only contain alphanumeric and special characters";
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}\\p{Punct}]+$";

    private String groupName;
    private final UniqueStudentsList students;
    private final UniqueSessionsList sessions;
    private final UniqueTasksList tasks;

    {
        students = new UniqueStudentsList();
    }

    {
        sessions = new UniqueSessionsList();
    }

    {
        tasks = new UniqueTasksList();
    }

    /**
     * Constructs a group with the given group name.
     * @param groupName A valid group name.
     */
    public Group(String groupName) {
        requireNonNull(groupName);
        this.groupName = groupName;
    }

    /**
     * Creates a copy of this instance. Used for saving states by undo command.
     * @return a copy of this instance.
     */
    public Group copy() {
        Group copy = new Group(this.groupName);
        UniqueStudentsList studentsCopy = new UniqueStudentsList();
        UniqueSessionsList sessionsCopy = new UniqueSessionsList();
        UniqueTasksList tasksCopy = new UniqueTasksList();

        students.asUnmodifiableObservableList().forEach(student -> studentsCopy.add(student.copy()));
        sessions.asUnmodifiableObservableList().forEach(session -> sessionsCopy.add(session.copy()));
        tasks.asUnmodifiableObservableList().forEach(task -> tasksCopy.add(task.copy()));

        copy.setStudents(studentsCopy);
        copy.setSessions(sessionsCopy);
        copy.setTasks(tasksCopy);
        return copy;

    }

    public void setStudents(UniqueStudentsList students) {
        this.students.setInternalList(students.asUnmodifiableObservableList());;
    }

    public void setSessions(UniqueSessionsList sessions) {
        this.sessions.setInternalList(sessions.asUnmodifiableObservableList());;
    }

    public void setTasks(UniqueTasksList tasks) {
        this.tasks.setInternalList(tasks.asUnmodifiableObservableList());;
    }

    /**
     * Returns the modifiable internal list of students in this group.
     */
    public ObservableList<Student> getModifiableStudentList() {
        return students.asModifiableObservableList();
    }

    /**
     * Returns an unmodifiable view of the list of students in this group.
     */
    public ObservableList<Student> getUnmodifiableStudentList() {
        return students.asUnmodifiableObservableList();
    }

    public ObservableList<Student> getUnmodifiableFilteredStudentList() {
        return students.asUnmodifiableFilteredList();
    }


    /**
     * Returns an unmodifiable view of the list of sessions in this group.
     */
    public ObservableList<Session> getUnmodifiableSessionList() {
        return sessions.asUnmodifiableObservableList();
    }

    public ObservableList<Session> getModifiableSessionList() {
        return sessions.asModifiableObservableList();
    }

    public ObservableList<Session> getUnmodifiableFilteredSessionList() {
        return sessions.asUnmodifiableFilteredList();
    }

    /**
     * Returns an unmodifiable view of the list of tasks in this group.
     */
    public ObservableList<Task> getUnmodifiableTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    public ObservableList<Task> getUnmodifiableFilteredTaskList() {
        return tasks.asUnmodifiableFilteredList();
    }

    /**
     * Returns a modifiable view of the list of tasks in this group.
     */
    public ObservableList<Task> getModifiableTaskList() {
        return tasks.asModifiableObservableList();
    }

    public void updateFilteredSessions(Predicate<Session> predicate) {
        sessions.updateFilterPredicate(predicate);
    }

    public void updateFilteredTasks(Predicate<Task> predicate) {
        tasks.updateFilterPredicate(predicate);
    }

    public void updateFilteredStudents(Predicate<Student> predicate) {
        students.updateFilterPredicate(predicate);
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

        for (Task task : tasks) {
            task.setStudents(students);
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
     * Adds the given task to this group.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        task.setStudents(students);
    }

    /**
     * Deletes the given session from this group.
     * @param session Session to be deleted.
     */
    public void deleteSession(Session session) {
        this.sessions.remove(session);
    }

    public void setSession(Session sessionToReplace, Session newSession) {
        this.sessions.set(sessionToReplace, newSession);
    }

    /**
     * Deletes the given task from this group.
     * @param task Task to be deleted.
     */
    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    public void setTask(Task taskToReplace, Task newTask) {
        this.tasks.set(taskToReplace, newTask);
    }

    /**
     * Deletes the given student from this group.
     * @param student Student to be deleted.
     */
    public void deleteStudent(Student student) {
        this.students.remove(student);
    }

    public void setStudent(Student studentToReplace, Student newStudent) {
        this.students.set(studentToReplace, newStudent);
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
     * Returns true if this group contains the specified task.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
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

    /**
     * Unselects all tasks in a group. For GUI purpose.
     */
    public void unMarkAllTasks() {
        for (Task task: this.getUnmodifiableTaskList()) {
            task.unselectTask();
        }
    }

    @Override
    public String toString() {
        return this.groupName;
    }
}
