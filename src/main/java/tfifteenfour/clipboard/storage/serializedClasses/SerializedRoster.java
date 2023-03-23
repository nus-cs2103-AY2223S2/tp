package tfifteenfour.clipboard.storage.serializedClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.Roster;

public class SerializedRoster {
    private List<SerializedCourse> courses = new ArrayList<>();

    public SerializedRoster(ReadOnlyRoster roster) {
        this.courses = roster.getUnmodifiableCourseList().stream()
                .map(course -> new SerializedCourse(course))
                .collect(Collectors.toList());
    }

    public SerializedRoster() {}

    @JsonProperty("courses")
    public List<SerializedCourse> getCourses() {
        return courses;
    }

    public Roster toModelType() {
        Roster newRoster = new Roster();
        courses.stream().forEach(course -> newRoster.addCourse(course.toModelType()));
        return newRoster;
    }
}