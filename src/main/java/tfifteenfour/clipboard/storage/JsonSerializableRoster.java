package tfifteenfour.clipboard.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import tfifteenfour.clipboard.commons.exceptions.IllegalValueException;
import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.model.course.Course;

/**
 * An Immutable Roster that is serializable to JSON format.
 */
@JsonRootName(value = "clipboard")
class JsonSerializableRoster {

    public static final String MESSAGE_DUPLICATE_COURSE = "Course list contains duplicate course(s).";

    private final List<JsonAdaptedCourse> courses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRoster} with the given persons.
     */
    @JsonCreator
    public JsonSerializableRoster(@JsonProperty("courses") List<JsonAdaptedCourse> courses) {
        this.courses.addAll(courses);
    }

    /**
     * Converts a given {@code ReadOnlyRoster} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRoster}.
     */
    public JsonSerializableRoster(ReadOnlyRoster source) {
        courses.addAll(source.getUnmodifiableCourseList()
                .stream().map(JsonAdaptedCourse::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Roster} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Roster toModelType() throws IllegalValueException {
        Roster roster = new Roster();
        for (JsonAdaptedCourse jsonAdaptedCourse : courses) {
            Course course = jsonAdaptedCourse.toModelType();
            if (roster.hasCourse(course)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COURSE);
            }
            roster.addCourse(course);
        }
        return roster;
    }

}
