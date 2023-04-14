package tfifteenfour.clipboard.model.student;

import tfifteenfour.clipboard.model.course.Session;

/**
 * A subclass of Session that adds attendance information to a session.
 */
public class SessionWithAttendance extends Session {
    private final int attendance;

    /**
     * Constructs a new SessionWithAttendance object with the given session name and attendance value.
     * @param session The Session object to which attendance information will be added.
     * @param attendance The attendance value for the session.
     */
    public SessionWithAttendance(Session session, int attendance) {
        super(session.getSessionName());
        this.attendance = attendance;
    }

    public int getSessionAttendance() {
        return attendance;
    }

}
