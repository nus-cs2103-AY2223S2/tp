package tfifteenfour.clipboard.storage;

import static java.util.Objects.requireNonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.commons.exceptions.DataConversionException;
import tfifteenfour.clipboard.commons.exceptions.IllegalValueException;
import tfifteenfour.clipboard.commons.util.FileUtil;
import tfifteenfour.clipboard.commons.util.JsonUtil;
import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.tag.Tag;

/**
 * A class to access Roster data stored as a json file on the hard disk.
 */
public class JsonRosterStorage implements RosterStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRosterStorage.class);

    private Path filePath;

    public JsonRosterStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRosterFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyRoster> readRoster() throws DataConversionException {
        return readRoster(filePath);
    }

    /**
     * Similar to {@link #readRoster()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyRoster> readRoster(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableRoster> jsonRoster = JsonUtil.readJsonFile(
                filePath, JsonSerializableRoster.class);
        if (!jsonRoster.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRoster.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRoster(ReadOnlyRoster roster) throws IOException {
        saveRoster(roster, filePath);
    }

    /**
     * Similar to {@link #saveRoster(ReadOnlyRoster)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveRoster(ReadOnlyRoster roster, Path filePath) throws IOException {
        requireNonNull(roster);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        //JsonUtil.saveJsonFile(new JsonSerializableRoster(roster), filePath);

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String rosterJson = rosterToJson(mapper, roster);
        writeJsonToFile(rosterJson, filePath);


    }

    private String rosterToJson(ObjectMapper mapper, ReadOnlyRoster roster) throws IOException {
        SerializedRoster wrapper = new SerializedRoster(roster);
        String rosterJson = mapper.writeValueAsString(wrapper);

        return rosterJson;
    }

    private void writeJsonToFile(String json, Path filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath.toString());
        fileWriter.write(json);
        fileWriter.close();
    }

}


class SerializedRoster {
    private List<SerializedCourse> courses;

    public SerializedRoster(ReadOnlyRoster roster) {
        this.courses = roster.getUnmodifiableCourseList().stream()
                .map(course -> new SerializedCourse(course))
                .collect(Collectors.toList());
    }

    @JsonProperty("courses")
    public List<SerializedCourse> getCourses() {
        return courses;
    }
}


class SerializedCourse {
    private String courseCode;
    private List<SerializedGroup> groups;

    public SerializedCourse(Course course) {
        this.courseCode = course.getCourseCode();
        this.groups = course.getUnmodifiableGroupList().stream()
                .map(group -> new SerializedGroup(group))
                .collect(Collectors.toList());
    }

    @JsonProperty("courseCode")
    public String getCourseCode() {
        return courseCode;
    }

    @JsonProperty("groups")
    public List<SerializedGroup> getStudents() {
        return groups;
    }
}

class SerializedGroup {
    private String groupName;
    private List<SerializedStudent> students;

    public SerializedGroup(Group group) {
        this.groupName = group.getGroupName();
        this.students = group.getUnmodifiableStudentList().stream()
                .map(student -> new SerializedStudent(student))
                .collect(Collectors.toList());
    }

    @JsonProperty("groupName")
    public String getGroupName() {
        return groupName;
    }

    @JsonProperty("students")
    public List<SerializedStudent> getStudents() {
        return students;
    }
}

class SerializedStudent {
    private final String name;
    private final String phone;
    private final String email;
    private final String studentId;
    private final String remark;
    private final List<Tag> tags;

    public SerializedStudent(Student student) {
        this.name = student.getName().toString();
        this.phone = student.getPhone().toString();
        this.email = student.getEmail().toString();
        this.studentId = student.getStudentId().toString();
        this.remark = student.getRemark().toString();
        this.tags = new ArrayList<Tag>(student.getTags());
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("studentId")
    public String getStudentId() {
        return studentId;
    }

    @JsonProperty("remark")
    public String getRemark() {
        return remark;
    }

    @JsonProperty("tags")
    public List<Tag> getTagged() {
        return tags;
    }
}
