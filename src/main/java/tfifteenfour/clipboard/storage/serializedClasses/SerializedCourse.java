package tfifteenfour.clipboard.storage.serializedClasses;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.course.Course;

public class SerializedCourse {
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