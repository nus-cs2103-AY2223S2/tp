package tfifteenfour.clipboard.model.student;

import tfifteenfour.clipboard.model.course.Session;

/**
 * A class representing a student with their attendance record.
 * Inherits from the Student class.
 */
public class SessionWithAttendance extends Session {
    private final int attendance;

    public SessionWithAttendance(Session session, int attendance) {
        super(session.getSessionName());
        this.attendance = attendance;
    }
}
