package tfifteenfour.clipboard.storage.serializedClasses;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.course.Group;

public class SerializedGroup {
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
