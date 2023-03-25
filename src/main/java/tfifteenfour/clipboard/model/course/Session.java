package tfifteenfour.clipboard.model.course;

import tfifteenfour.clipboard.model.student.Student;

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
    public Group group;
    private Map<Student, Integer> attendance;

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

    public String getSessionName() {
        return this.sessionName;
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

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return this.sessionName;
    }
}
