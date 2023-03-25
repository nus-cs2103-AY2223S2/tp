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

/**
 * Jackson-friendly version of {@link Session}.
 */
class JsonAdaptedSession {
    // TODO: Figure out how to convert attendance
    private final String sessionName;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final Map<JsonAdaptedStudent, Integer> attendance = new HashMap<>();

    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("groupName") String sessionName,
                            @JsonProperty("students") List<JsonAdaptedStudent> students,
                              @JsonProperty("attendance") Map<JsonAdaptedStudent, Integer> attendance) {
        this.sessionName = sessionName;
        if (students != null) {
            this.students.addAll(students);
        }
    }

    public JsonAdaptedSession(Session source) {
        this.sessionName = source.getSessionName();
        this.students.addAll(source.getUnmodifiableStudentList()
                .stream().map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
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

        // TODO: Figure out how to convert attendance
//        for (JsonAdaptedStudent student : students) {
//            savedGroup.addStudent(student.toModelType());
//        }
        return savedSession;
    }

}
