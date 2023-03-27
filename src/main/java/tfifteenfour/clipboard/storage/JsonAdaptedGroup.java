package tfifteenfour.clipboard.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import tfifteenfour.clipboard.commons.exceptions.IllegalValueException;
import tfifteenfour.clipboard.model.course.Group;

/**
 * Jackson-friendly version of {@link Group}.
 */
class JsonAdaptedGroup {

    private final String groupName;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedSession> sessions = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("groupName") String groupName,
                            @JsonProperty("students") List<JsonAdaptedStudent> students,
                            @JsonProperty("sessions") List<JsonAdaptedSession> sessions) {
        this.groupName = groupName;
        if (students != null) {
            this.students.addAll(students);
        }

        if (sessions != null) {
            this.sessions.addAll(sessions);
        }
    }

    public JsonAdaptedGroup(Group source) {
        this.groupName = source.getGroupName();
        this.students.addAll(source.getUnmodifiableStudentList()
                .stream().map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
        this.sessions.addAll(source.getUnmodifiableSessionList()
                .stream().map(JsonAdaptedSession::new)
                .collect(Collectors.toList()));
    }

    @JsonValue
    public String getGroupName() {
        return groupName;
    }

    /**
     * Converts this Jackson-friendly adapted module code object into the model's {@code ModuleCode} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module code.
     */
    public Group toModelType() throws IllegalValueException {
        if (!Group.isValidGroupName(groupName)) {
            throw new IllegalValueException(Group.MESSAGE_CONSTRAINTS);
        }
        Group savedGroup = new Group(groupName);

        for (JsonAdaptedStudent student : students) {
            savedGroup.addStudent(student.toModelType());
        }

        for (JsonAdaptedSession session : sessions) {
            savedGroup.addSession(session.toModelType());
        }
        return savedGroup;
    }

}
