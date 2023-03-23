package tfifteenfour.clipboard.storage.serializedClasses;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.ReadOnlyRoster;

public class SerializedRoster {
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