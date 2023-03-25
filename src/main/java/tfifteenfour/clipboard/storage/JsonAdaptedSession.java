package tfifteenfour.clipboard.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import tfifteenfour.clipboard.commons.exceptions.IllegalValueException;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.UniqueStudentList;

/**
 * Jackson-friendly version of {@link Session}.
 */

// Warning: Unused class, ignore
class JsonAdaptedSession {
    private final String sessionName;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedStudent> keys = new ArrayList<>();
    private final List<Integer> values = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("groupName") String sessionName,
                              @JsonProperty("students") List<JsonAdaptedStudent> students,
                              @JsonProperty("keys") List<JsonAdaptedStudent> keys,
                              @JsonProperty("values") List<Integer> values) {
        this.sessionName = sessionName;

        if (students != null) {
            this.students.addAll(students);
        }

        if (keys != null) {
            this.keys.addAll(keys);
        }

        if (values != null) {
            this.values.addAll(values);
        }
    }

    public JsonAdaptedSession(Session source) {
        this.sessionName = source.getSessionName();
        this.students.addAll(source.getUnmodifiableStudentList()
                .stream().map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
        this.keys.addAll(source.getAttendance().keySet()
                .stream().map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
        this.values.addAll(source.getAttendance().values());
    }

    @JsonValue
    public String getSessionName() {
        return sessionName;
    }

    /**
     * Converts this Jackson-friendly adapted module code object into the model's {@code Session} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module code.
     */
    public Session toModelType() throws IllegalValueException {
        if (!Session.isValidSessionName(sessionName)) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }
        Session savedSession = new Session(sessionName);

        UniqueStudentList uniqueStudents = new UniqueStudentList();
        for (JsonAdaptedStudent student : students) {
            uniqueStudents.add(student.toModelType());
        }
        savedSession.setStudents(uniqueStudents);

        Map<Student, Integer> newAttendance = savedSession.getAttendance();
        for (int i = 0; i < keys.size(); i++) {
            newAttendance.put(
                    keys.get(i).toModelType(),
                    values.get(i)
            );
        }
        return savedSession;
    }

}
