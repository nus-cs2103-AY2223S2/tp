package tfifteenfour.clipboard.storage.serializedclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.course.Group;

/**
 * Serializes a group to JSON format.
 */
public class SerializedGroup {
    private String groupName;
    private List<SerializedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code SerializedGroup} with the given group.
     */
    public SerializedGroup(Group group) {
        this.groupName = group.getGroupName();
        this.students = group.getUnmodifiableStudentList().stream()
                .map(student -> new SerializedStudent(student))
                .collect(Collectors.toList());
    }

    public SerializedGroup() {}

    @JsonProperty("groupName")
    public String getGroupName() {
        return groupName;
    }

    @JsonProperty("students")
    public List<SerializedStudent> getStudents() {
        return students;
    }

    /**
     * Converts this serialized group to a {@code Group} object
     */
    public Group toModelType() {
        Group newGroup = new Group(this.groupName);
        this.students.stream().forEach(student -> newGroup.addStudent(student.toModelType()));
        return newGroup;
    }
}
