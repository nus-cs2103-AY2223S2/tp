package tfifteenfour.clipboard.storage.serializedClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.course.Course;

public class SerializedCourse {
    private String courseCode;
    private List<SerializedGroup> groups = new ArrayList<>();

    public SerializedCourse(Course course) {
        this.courseCode = course.getCourseCode();
        this.groups = course.getUnmodifiableGroupList().stream()
                .map(group -> new SerializedGroup(group))
                .collect(Collectors.toList());
    }

    public SerializedCourse() {}

    @JsonProperty("courseCode")
    public String getCourseCode() {
        return courseCode;
    }

    @JsonProperty("groups")
    public List<SerializedGroup> getStudents() {
        return groups;
    }

    public Course toModelType() {
        Course newCourse = new Course(courseCode);
        this.groups.stream().forEach(group -> newCourse.addGroup(group.toModelType()));
        return newCourse;
    }
}