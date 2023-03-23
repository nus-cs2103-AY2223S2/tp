package tfifteenfour.clipboard.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import tfifteenfour.clipboard.commons.exceptions.IllegalValueException;
import tfifteenfour.clipboard.model.course.Course;

/**
 * Jackson-friendly version of {@link Course}.
 */
class JsonAdaptedModuleCode {

    private final String courseCode;

    /**
     * Constructs a {@code JsonAdaptedModuleCode} with the given {@code courseCode}.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Converts a given {@code ModuleCode} into this class for Jackson use.
     */
    public JsonAdaptedModuleCode(Course source) {
        courseCode = source.courseCode;
    }

    @JsonValue
    public String getModuleCode() {
        return courseCode;
    }

    /**
     * Converts this Jackson-friendly adapted module code object into the model's {@code ModuleCode} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module code.
     */
    public Course toModelType() throws IllegalValueException {
        if (!Course.isValidModuleCode(courseCode)) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        }
        return new Course(courseCode);
    }

}
