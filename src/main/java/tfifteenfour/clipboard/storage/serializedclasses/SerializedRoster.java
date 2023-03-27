package tfifteenfour.clipboard.storage.serializedclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.Roster;

/**
 * Serializes a Roster to JSON format.
 */
public class SerializedRoster {
    private List<SerializedCourse> courses = new ArrayList<>();

    /**
     * Constructs a {@code SerializedRoster} with the given roster.
     */
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

    /**
     * Converts current {@code SerializedRoster} object into a {@code Roster} object
     * and returns it.
     * @return A {@code Roster} object that corresponds to this {@code SerializedRoster} object.
     */
    public Roster toModelType() {
        Roster newRoster = new Roster();
        courses.stream().forEach(course -> newRoster.addCourse(course.toModelType()));
        return newRoster;
    }
}
