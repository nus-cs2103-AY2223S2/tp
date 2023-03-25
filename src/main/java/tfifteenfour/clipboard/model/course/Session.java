package tfifteenfour.clipboard.model.course;

import javafx.collections.ObservableList;
import tfifteenfour.clipboard.model.course.exceptions.StudentNotInSessionException;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.UniqueStudentList;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Session in the CLIpboard.
 */
public class Session {

    public static final String MESSAGE_CONSTRAINTS = "Session names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public String sessionName;
//    public Group group;
//    private ObservableList<Student> students;
    private final Map<Student, Integer> attendance;
    private final UniqueStudentList students;

    {
        students = new UniqueStudentList();
    }


    /**
     * Constructs a {@code Session}.
     *
     * @param sessionName A valid session name.
     */
    public Session(String sessionName) {
        requireNonNull(sessionName);
        this.sessionName = sessionName;
        attendance = new HashMap<>();
    }

    public ObservableList<Student> getUnmodifiableStudentList() {
        return students.asUnmodifiableObservableList();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public String getSessionName() {
        System.out.println("########\n " +
                "ATTENDANCE for" + sessionName);
        System.out.println(attendance.toString());
        System.out.println("########");
        return this.sessionName;
    }

    public Map<Student, Integer> getAttendance() {
        return this.attendance;
    }

    public boolean isSameSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        return otherSession != null
                && otherSession.getSessionName().equals(getSessionName());
    }

    public static boolean isValidSessionName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public void setStudents(UniqueStudentList students) {
//        this.group = group;
        for (Student student : students) {
            if (!attendance.containsKey(student)) {
                attendance.put(student, 0);
            }
            addStudent(student);
        }
        System.out.println(attendance.toString());
    }

    public void markPresent(Student student) {
        requireNonNull(student);

        if (!attendance.containsKey(student)) {
            throw new StudentNotInSessionException();
        }
        attendance.put(student, 1);
        System.out.println("marked: " + student + "\n" + attendance.get(student));
    }

    public void markAbsent(Student student) {
        requireNonNull(student);

        if (!attendance.containsKey(student)) {
            throw new StudentNotInSessionException();
        }
        attendance.put(student, 0);
        System.out.println("marked: " + student + "\n" + attendance.get(student));
    }

    @Override
    public String toString() {
        return this.sessionName;
    }
}
