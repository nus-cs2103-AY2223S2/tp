package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Mathutoring;
import seedu.address.model.ReadOnlyMathutoring;
import seedu.address.model.student.Student;


/**
 * An Immutable Mathutoring that is serializable to JSON format.
 */
@JsonRootName(value = "mathutoring")
public class JsonSerializableMathutoring {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableMathutoring} with the given students.
     */
    @JsonCreator
    public JsonSerializableMathutoring(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyMathutoring} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMathutoring}.
     */
    public JsonSerializableMathutoring(ReadOnlyMathutoring source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this mathutoring into the model's {@code Mathutoring} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Mathutoring toModelType() throws IllegalValueException {
        Mathutoring mathutoring = new Mathutoring();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (mathutoring.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            mathutoring.addStudent(student);
        }
        return mathutoring;
    }

}
