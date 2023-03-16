package seedu.address.storage.pcclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.PCClass;
import seedu.address.model.person.ReadOnlyPCClass;
import seedu.address.model.person.student.Student;
import seedu.address.storage.person.JsonAdaptedStudent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable PCClass that is serializable to JSON format.
 */
@JsonRootName(value = "class")
public class JsonSerializablePCClass {
    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";

    private final JsonAdaptedClass jsonAdaptedClass;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePCClass} with the given students.
     */
    @JsonCreator
    public JsonSerializablePCClass(@JsonProperty("class") JsonAdaptedClass jsonAdaptedClass) {
         this.jsonAdaptedClass = jsonAdaptedClass;
    }

    /**
     * Converts a given {@code ReadOnlyPCClass} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePCClass}.
     */
    public JsonSerializablePCClass(ReadOnlyPCClass source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        jsonAdaptedClass = new JsonAdaptedClass(source.getPcClass().getClassName(), students);
    }

    /**
     * Converts this tea pet into the model's {@code PCClass} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PCClass toModelType() throws IllegalValueException {
        PCClass pcClass = new PCClass();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (pcClass.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            pcClass.addStudent(student);
        }
        return pcClass;
    }
}
