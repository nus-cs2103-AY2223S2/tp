package tfifteenfour.clipboard.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.commons.exceptions.IllegalValueException;
import tfifteenfour.clipboard.model.course.Course;

/**
 * Jackson-friendly version of {@link Course}.
 */
class JsonAdaptedCourse {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Course's %s field is missing!";

    private final String courseCode;
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedCourse(@JsonProperty("courseCode") String courseCode, @JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        this.courseCode = courseCode;
        if (groups != null) {
            this.groups.addAll(groups);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     *
     */
    public JsonAdaptedCourse(Course course) {
        courseCode = course.courseCode;
        groups.addAll(course.getUnmodifiableGroupList().stream()
                .map(JsonAdaptedGroup::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Course toModelType() throws IllegalValueException {
        if (!Course.isValidCourseCode(courseCode)) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        }
        Course savedCourse = new Course(courseCode);

        for (JsonAdaptedGroup group : groups) {
            savedCourse.addGroup(group.toModelType());
        }
        return savedCourse;

    }
}