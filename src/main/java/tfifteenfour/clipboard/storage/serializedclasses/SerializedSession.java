package tfifteenfour.clipboard.storage.serializedclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;

/**
 * Serializes a Session to JSON format.
 */
public class SerializedSession {
    private String sessionName;
    private final List<SerializedStudent> keys = new ArrayList<>();
    private final List<Integer> values = new ArrayList<>();


    /**
     * Constructs a {@code SerializedSession} with the given session.
     */
    public SerializedSession(Session session) {
        this.sessionName = session.getSessionName();
        Map<Student, Integer> sessionAttendance = session.getAttendance();
        for (Student student : sessionAttendance.keySet()) {
            keys.add(new SerializedStudent(student));
            values.add(sessionAttendance.get(student));
        }

    }

    /**
     * Constructs an empty SerializedSession object.
     */
    public SerializedSession() {}

    @JsonProperty("sessionName")
    public String getSessionName() {
        return sessionName;
    }

    @JsonProperty("keys")
    public List<SerializedStudent> getKeys() {
        return this.keys;
    }

    @JsonProperty("values")
    public List<Integer> getValues() {
        return this.values;
    }

    /**
     * Converts current SerializedSession object into a Session object and returns
     * it.
     * @return A Session object that corresponds to this SerializedSession object.
     */
    public Session toModelType() {
        Session newSession = new Session(this.sessionName);

        Map<Student, Integer> newAttendance = newSession.getAttendance();
        for (int i = 0; i < keys.size(); i++) {
            newAttendance.put(
                    keys.get(i).toModelType(),
                    values.get(i)
            );
        }
        return newSession;
    }
}
